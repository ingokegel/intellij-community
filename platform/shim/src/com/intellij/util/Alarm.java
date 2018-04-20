package com.intellij.util;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.Application;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class Alarm {

    public Alarm(ThreadToUse pooledThread, Disposable disposable) {

    }

    public void cancelAllRequests() {

    }

    public enum ThreadToUse {SWING_THREAD, SHARED_THREAD, POOLED_THREAD}

    public void addRequest(@NotNull Runnable request, int delay) {

    }
}
