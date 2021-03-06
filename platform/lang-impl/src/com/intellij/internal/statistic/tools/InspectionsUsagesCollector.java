// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.internal.statistic.tools;

import com.intellij.codeInspection.InspectionEP;
import com.intellij.codeInspection.InspectionProfileEntry;
import com.intellij.codeInspection.ex.InspectionProfileImpl;
import com.intellij.codeInspection.ex.InspectionToolWrapper;
import com.intellij.codeInspection.ex.ScopeToolState;
import com.intellij.internal.statistic.beans.MetricEvent;
import com.intellij.internal.statistic.beans.MetricEventFactoryKt;
import com.intellij.internal.statistic.eventLog.EventLogGroup;
import com.intellij.internal.statistic.eventLog.FeatureUsageData;
import com.intellij.internal.statistic.eventLog.events.EventFields;
import com.intellij.internal.statistic.eventLog.events.EventId1;
import com.intellij.internal.statistic.eventLog.events.EventId3;
import com.intellij.internal.statistic.eventLog.validator.ValidationResultType;
import com.intellij.internal.statistic.eventLog.validator.rules.EventContext;
import com.intellij.internal.statistic.eventLog.validator.rules.impl.CustomValidationRule;
import com.intellij.internal.statistic.service.fus.collectors.ProjectUsagesCollector;
import com.intellij.internal.statistic.utils.PluginInfo;
import com.intellij.internal.statistic.utils.PluginInfoDetectorKt;
import com.intellij.lang.Language;
import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.profile.codeInspection.InspectionProjectProfileManager;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jdom.Attribute;
import org.jdom.Content;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class InspectionsUsagesCollector extends ProjectUsagesCollector {
  private static final Predicate<ScopeToolState> ENABLED = state -> !state.getTool().isEnabledByDefault() && state.isEnabled();
  private static final Predicate<ScopeToolState> DISABLED = state -> state.getTool().isEnabledByDefault() && !state.isEnabled();

  private static final String OPTION_VALUE = "option_value";
  private static final String OPTION_TYPE = "option_type";
  private static final String OPTION_NAME = "option_name";
  private static final String INSPECTION_ID = "inspection_id";

  private static final EventLogGroup GROUP = new EventLogGroup("inspections", 6);
  private static final EventId1<Integer> PROFILES =
    GROUP.registerEvent("profiles",
                        EventFields.Int("amount"));
  private static final EventId3<Boolean, Boolean, Boolean> PROFILE =
    GROUP.registerEvent("used.profile",
                        EventFields.Boolean("project_level"),
                        EventFields.Boolean("default"),
                        EventFields.Boolean("locked"));

  @Override
  public EventLogGroup getGroup() {
    return GROUP;
  }

  @NotNull
  @Override
  public Set<MetricEvent> getMetrics(@NotNull final Project project) {
    final Set<MetricEvent> result = new HashSet<>();

    final var profileManager = InspectionProjectProfileManager.getInstance(project);
    result.add(PROFILES.metric(profileManager.getProfiles().size()));
    final var profile = profileManager.getCurrentProfile();
    result.add(create(profile));

    final List<ScopeToolState> tools = profile.getAllTools();
    for (ScopeToolState state : tools) {
      InspectionToolWrapper<?, ?> tool = state.getTool();
      PluginInfo pluginInfo = getInfo(tool);
      if (ENABLED.test(state)) {
        result.add(create(tool, pluginInfo, true));
      }
      else if (DISABLED.test(state)) {
        result.add(create(tool, pluginInfo, false));
      }

      result.addAll(getChangedSettingsEvents(tool, pluginInfo, state.isEnabled()));
    }
    return result;
  }

  private static Collection<MetricEvent> getChangedSettingsEvents(InspectionToolWrapper<?, ?> tool,
                                                                  PluginInfo pluginInfo,
                                                                  boolean inspectionEnabled) {
    if (!isSafeToReport(pluginInfo)) {
      return Collections.emptyList();
    }

    InspectionProfileEntry entry = tool.getTool();
    Map<String, Attribute> options = getOptions(entry);
    if (options.isEmpty()) {
      return Collections.emptyList();
    }

    Set<String> fields = ContainerUtil.map2Set(ReflectionUtil.collectFields(entry.getClass()), f -> f.getName());
    Map<String, Attribute> defaultOptions = getOptions(ReflectionUtil.newInstance(entry.getClass()));

    Collection<MetricEvent> result = new ArrayList<>();
    String inspectionId = tool.getID();
    for (Map.Entry<String, Attribute> option : options.entrySet()) {
      String name = option.getKey();
      Attribute value = option.getValue();
      if (fields.contains(name) && value != null) {
        Attribute defaultValue = defaultOptions.get(name);
        if (defaultValue == null || !StringUtil.equals(value.getValue(), defaultValue.getValue())) {
          FeatureUsageData data = new FeatureUsageData();
          data.addData(OPTION_NAME, name);
          data.addData(INSPECTION_ID, inspectionId);
          data.addData("inspection_enabled", inspectionEnabled);
          data.addPluginInfo(pluginInfo);
          if (addSettingValue(value, data)) {
            result.add(MetricEventFactoryKt.newMetric("setting.non.default.state", data));
          }
        }
      }
    }
    return result;
  }

  private static boolean addSettingValue(Attribute value, FeatureUsageData data) {
    try {
      boolean booleanValue = value.getBooleanValue();
      data.addData(OPTION_VALUE, booleanValue);
      data.addData(OPTION_TYPE, "boolean");
      return true;
    }
    catch (DataConversionException e) {
      return addIntValue(value, data);
    }
  }

  private static boolean addIntValue(Attribute value, FeatureUsageData data) {
    try {
      int intValue = value.getIntValue();
      data.addData(OPTION_VALUE, intValue);
      data.addData(OPTION_TYPE, "integer");
      return true;
    }
    catch (DataConversionException e) {
      return false;
    }
  }

  @NotNull
  private static MetricEvent create(InspectionToolWrapper<?, ?> tool, PluginInfo info, boolean enabled) {
    final FeatureUsageData data = new FeatureUsageData().addData("enabled", enabled);
    final String language = tool.getLanguage();
    if (StringUtil.isNotEmpty(language)) {
      data.addLanguage(Language.findLanguageByID(language));
    }
    if (info != null) {
      data.addPluginInfo(info);
    }
    data.addData(INSPECTION_ID, isSafeToReport(info) ? tool.getID() : "third.party");
    return MetricEventFactoryKt.newMetric("not.default.state", data);
  }

  @NotNull
  private static MetricEvent create(InspectionProfileImpl profile) {
    return PROFILE.metric(
      profile.isProjectLevel(),
      profile.toString().equals(profile.isProjectLevel() ? "Project Default" : "Default"),
      profile.isProfileLocked()
    );
  }

  private static boolean isSafeToReport(PluginInfo info) {
    return info != null && info.isSafeToReport();
  }

  @Nullable
  private static PluginInfo getInfo(InspectionToolWrapper<?, ?> tool) {
    InspectionEP extension = tool.getExtension();
    PluginDescriptor pluginDescriptor = extension == null ? null : extension.getPluginDescriptor();
    return pluginDescriptor != null ? PluginInfoDetectorKt.getPluginInfoByDescriptor(pluginDescriptor) : null;
  }

  private static Map<String, Attribute> getOptions(InspectionProfileEntry entry) {
    Element element = new Element("options");
    try {
      ScopeToolState.tryWriteSettings(entry, element);
      List<Content> options = element.getContent();
      if (options.isEmpty()) {
        return Collections.emptyMap();
      }

      return ContainerUtil.map2MapNotNull(options, option -> {
        if (option instanceof Element) {
          Attribute nameAttr = ((Element)option).getAttribute("name");
          Attribute valueAttr = ((Element)option).getAttribute("value");
          if (nameAttr != null && valueAttr != null) {
            return Pair.create(nameAttr.getValue(), valueAttr);
          }
        }
        return null;
      });
    }
    catch (Exception e) {
      return Collections.emptyMap();
    }
  }

  public static class InspectionToolValidator extends CustomValidationRule {

    @Override
    public boolean acceptRuleId(@Nullable String ruleId) {
      return "tool".equals(ruleId);
    }

    @NotNull
    @Override
    protected ValidationResultType doValidate(@NotNull String data, @NotNull EventContext context) {
      if (isThirdPartyValue(data)) return ValidationResultType.ACCEPTED;
      return acceptWhenReportedByPluginFromPluginRepository(context);
    }
  }
}