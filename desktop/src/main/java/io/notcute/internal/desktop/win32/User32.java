package io.notcute.internal.desktop.win32;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Platform;
import jnr.ffi.annotations.In;

public interface User32 {

    User32 INSTANCE = Platform.getNativePlatform().getOS() == Platform.OS.WINDOWS ?
            LibraryLoader.create(User32.class).load("User32") : null;

    int MONITOR_DEFAULTTONULL = 0x00000000;
    int MONITOR_DEFAULTTOPRIMARY = 0x00000001;
    int MONITOR_DEFAULTTONEAREST = 0x00000002;

    long MonitorFromWindow(
            @In long hwnd,
            @In int dwFlags
    );

    int SPI_GETNONCLIENTMETRICS = 41;


    boolean SystemParametersInfoW(
            @In long uiAction,
            @In long uiParam,
            LOGFONTW pvParam,
            @In long fWinIni
    );

}
