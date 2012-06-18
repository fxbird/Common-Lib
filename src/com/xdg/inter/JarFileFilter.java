package com.xdg.inter;

import java.util.jar.JarEntry;

public interface JarFileFilter {
    boolean accept(JarEntry entry);
}
