package com.qinpr.follow.messageQueue.store.util;

import com.sun.jna.*;

/**
 * Created by qinpr on 2019/2/19.
 */
public interface LibC extends Library {
    LibC INSTANCE = (LibC) Native.loadLibrary(Platform.isWindows() ? "msvcrt" : "c", LibC.class);

    int mlock(Pointer var1, NativeLong var2);

    int munlock(Pointer var1, NativeLong var2);
}
