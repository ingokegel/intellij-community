package com.intellij.openapi.progress.util;

public class PotemkinProgress {
    public static void invokeLaterNotBlocking(Object source, Runnable r) {
        r.run();
    }
}
