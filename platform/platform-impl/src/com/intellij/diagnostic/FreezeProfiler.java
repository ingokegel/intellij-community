// Copyright 2000-2021 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.diagnostic;

import com.intellij.openapi.diagnostic.Attachment;
import com.intellij.openapi.extensions.ExtensionPointName;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

@ApiStatus.Internal
public interface FreezeProfiler {
  ExtensionPointName<FreezeProfiler> EP_NAME = new ExtensionPointName<>("com.intellij.diagnostic.freezeProfiler");

  void start(@NotNull File dir);

  void stop();

  @NotNull List<Attachment> getAttachments(@NotNull File reportDir);
}
