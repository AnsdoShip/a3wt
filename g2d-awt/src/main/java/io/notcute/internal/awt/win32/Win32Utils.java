package io.notcute.internal.awt.win32;

import jnr.ffi.*;
import jnr.ffi.Runtime;
import jnr.ffi.byref.IntByReference;
import sun.awt.AWTAccessor;

import java.awt.*;
import java.awt.peer.ComponentPeer;

public final class Win32Utils {

    private static final long NULL = 0L;

    private Win32Utils() {
        throw new UnsupportedOperationException();
    }

    public static long getHWnd(Component component) {
        if (component == null) return NULL;
        try {
            ComponentPeer peer = AWTAccessor.getComponentAccessor().getPeer(component);
            return (long) peer.getClass().getMethod("getHWnd").invoke(peer);
        }
        catch (Exception ignored) {
            return NULL;
        }
    }

    public static int getDpiForComponent(Component component) {
        if (component == null) return Toolkit.getDefaultToolkit().getScreenResolution();
        Shcore SHCORE = Shcore.INSTANCE;
        if (SHCORE != null) {
            long hWnd = getHWnd(component);
            if (hWnd != NULL) {
                User32 USER32 = User32.INSTANCE;
                long monitor = USER32.MonitorFromWindow(hWnd, User32.MONITOR_DEFAULTTOPRIMARY);
                IntByReference dpiX = new IntByReference();
                IntByReference dpiY = new IntByReference();
                int hresult = SHCORE.GetDpiForMonitor(monitor, MONITOR_DPI_TYPE.MDT_EFFECTIVE_DPI, dpiX, dpiY);
                if (hresult == Win32ErrorConstants.S_OK) return dpiX.intValue();
            }
        }
        return component.getToolkit().getScreenResolution();
    }

    static Shcore initShcore() {
        try {
            return Platform.getNativePlatform().getOS() == Platform.OS.WINDOWS ?
                    LibraryLoader.create(Shcore.class).load("Shcore") : null;
        }
        catch (Exception e) {
            return null;
        }
    }

    public static int getTextScaleFactor() {
        Advapi32 ADVAPI32 = Advapi32.INSTANCE;
        if (ADVAPI32 == null) return 100;
        else {
            IntByReference phkResult = new IntByReference();
            int lstatus = ADVAPI32.RegOpenKeyExW(Advapi32.HKEY_CURRENT_USER,
                    "SOFTWARE\\Microsoft\\Accessibility",
                    0, Advapi32.KEY_READ, phkResult);
            if (lstatus != Win32ErrorConstants.ERROR_SUCCESS) return 100;
            else {
                int hKey = phkResult.getValue();
                Pointer dwBuffer = Memory.allocate(jnr.ffi.Runtime.getRuntime(ADVAPI32), NativeType.SINT);
                Pointer dwBufferSize = Memory.allocate(Runtime.getRuntime(ADVAPI32), NativeType.SINT);
                dwBufferSize.putInt(0, 4);
                lstatus = ADVAPI32.RegQueryValueExW(hKey, "TextScaleFactor", NULL,
                        null, dwBuffer, dwBufferSize);
                if (lstatus != Win32ErrorConstants.ERROR_SUCCESS) return 100;
                else return dwBuffer.getInt(0);
            }
        }
    }

    public static String getDefaultFontName() {
        User32 USER32 = User32.INSTANCE;
        if (USER32 == null) return null;
        else {
            LOGFONTW logfontw = new LOGFONTW(Runtime.getRuntime(USER32));
            if (USER32.SystemParametersInfoW(User32.SPI_GETNONCLIENTMETRICS,
                    0, logfontw, 0)) {
                StringBuilder faceName = new StringBuilder();
                for (int i = 0; i < logfontw.lfFaceName.length; i ++) {
                    faceName.append(logfontw.lfFaceName[i].get());
                }
                faceName.append(' ');
                if (logfontw.lfWeight.get() > 500) faceName.append("BOLD");
                if (logfontw.lfItalic.get() != 0) faceName.append("ITALIC");
                if (faceName.charAt(faceName.length() - 1) == ' ') return faceName.substring(0, faceName.length() - 1);
                else return faceName.toString();
            }
            else return null;
        }
    }

}
