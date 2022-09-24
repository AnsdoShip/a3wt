package com.ansdoship.a3wt.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.io.Closeable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

import static com.ansdoship.a3wt.util.A3Asserts.checkArgNotEmpty;
import static com.ansdoship.a3wt.util.A3Asserts.checkArgNotNull;

public class A3Files {

    private A3Files(){}

    private static final int BUFFER_SIZE = 8192;

    public static boolean createFileIfNotExist(final File file) {
        checkArgNotNull(file, "file");
        try {
            if (file.exists() && file.isFile()) return true;
            else {
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    if (!parentFile.mkdirs()) return false;
                }
                return file.createNewFile();
            }
        }
        catch (IOException e) {
            return false;
        }
    }

    public static boolean createDirIfNotExist(final File dir) {
        checkArgNotNull(dir, "dir");
        if (dir.exists()) {
            return dir.isDirectory();
        }
        else return dir.mkdirs();
    }

    public static void transferTo(final InputStream source, final OutputStream target) throws IOException {
        checkArgNotNull(source, "source");
        checkArgNotNull(target, "target");
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = source.read(buffer)) != -1) {
            target.write(buffer, 0, length);
        }
    }

    public static void copyTo(final File source, final File target) throws IOException {
        checkArgNotNull(source, "source");
        checkArgNotNull(target, "target");
        try (FileInputStream input = new FileInputStream(source); FileOutputStream output = new FileOutputStream(target)){
            transferTo(input, output);
            output.flush();
        }
    }

    public static boolean isStartsWithSeparator(final String path) {
        checkArgNotNull(path, "path");
        return path.startsWith("\\") || path.startsWith("/");
    }

    public static boolean isEndsWithSeparator(final String path) {
        checkArgNotNull(path, "path");
        return path.endsWith("\\") || path.endsWith("/");
    }

    public static String normalizeSeparatorsUNIX(final String path) {
        checkArgNotNull(path, "path");
        return path.replaceAll("\\\\", "/");
    }

    public static String normalizeSeparatorsDOS(final String path) {
        checkArgNotNull(path, "path");
        return path.replaceAll("/", "\\");
    }

    public static String normalizeSeparators(final String path) {
        checkArgNotNull(path, "path");
        return normalizeSeparatorsUNIX(path).replaceAll("/", File.separator);
    }

    public static String removeStartSeparator(final String path) {
        checkArgNotNull(path, "path");
        if (isStartsWithSeparator(path)) return path.substring(1);
        else return path;
    }

    public static String removeEndSeparator(final String path) {
        checkArgNotNull(path, "path");
        if (isEndsWithSeparator(path)) return path.substring(0, path.length() - 1);
        else return path;
    }

    public static String ensureStartSeparatorUNIX(final String path) {
        checkArgNotNull(path, "path");
        if (isStartsWithSeparator(path)) return path;
        else return "/" + path;
    }

    public static String ensureStartSeparatorDOS(final String path) {
        checkArgNotNull(path, "path");
        if (isStartsWithSeparator(path)) return path;
        else return "\\" + path;
    }

    public static String ensureStartSeparator(final String path) {
        checkArgNotNull(path, "path");
        if (isStartsWithSeparator(path)) return path;
        else return File.separator + path;
    }

    public static String ensureEndSeparatorUNIX(final String path) {
        checkArgNotNull(path, "path");
        if (isEndsWithSeparator(path)) return path;
        else return path + "/";
    }

    public static String ensureEndSeparatorDOS(final String path) {
        checkArgNotNull(path, "path");
        if (isEndsWithSeparator(path)) return path;
        else return path + "\\";
    }

    public static String ensureEndSeparator(final String path) {
        checkArgNotNull(path, "path");
        if (isEndsWithSeparator(path)) return path;
        else return path + File.separator;
    }

    public static URL[] files2URLs(final File[] files) throws MalformedURLException {
        checkArgNotEmpty(files, "files");
        final URL[] urls = new URL[files.length];
        for (int i = 0; i < urls.length; i ++) {
            urls[i] = files[i].toURI().toURL();
        }
        return urls;
    }

    public static URI[] files2URIs(final File[] files) {
        checkArgNotEmpty(files, "files");
        final URI[] uris = new URI[files.length];
        for (int i = 0; i < uris.length; i ++) {
            uris[i] = files[i].toURI();
        }
        return uris;
    }

    public static String[] files2URIStrings(final File[] files) {
        checkArgNotEmpty(files, "files");
        final String[] strings = new String[files.length];
        for (int i = 0; i < strings.length; i ++) {
            strings[i] = "file://" + files[i].getAbsolutePath();
        }
        return strings;
    }

    public static String readStringAndClose(final Reader reader) throws IOException {
        checkArgNotNull(reader, "reader");
        try {
            final StringWriter writer = new StringWriter();
            final char[] buffer = new char[BUFFER_SIZE / 2];
            int count;
            while ((count = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, count);
            }
            return writer.toString();
        } finally {
            reader.close();
        }
    }

    public static byte[] readBytesAndClose(final InputStream stream) throws IOException {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            final byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = stream.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            return output.toByteArray();
        }
        finally {
            stream.close();
        }
    }

    public static int readNBytes(final InputStream stream, final byte[] b, final int off, final int len) throws IOException {
        int n = 0;
        while (n < len) {
            int count = stream.read(b, off + n, len - n);
            if (count < 0)
                return count;
            n += count;
        }
        return n;
    }

    /**
     * Deletes the contents of {@code dir}. Throws an IOException if any file
     * could not be deleted, or if {@code dir} is not a readable directory.
     */
    public static void deleteContents(final File dir) throws IOException {
        checkArgNotNull(dir, "dir");
        final File[] files = dir.listFiles();
        if (files == null) {
            throw new IOException("not a readable directory: " + dir);
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (!file.delete()) {
                throw new IOException("failed to delete file: " + file);
            }
        }
    }

    public static void closeQuietly(final /*Auto*/Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception ignored) {
            }
        }
    }

}
