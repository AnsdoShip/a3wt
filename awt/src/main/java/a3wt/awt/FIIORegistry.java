package a3wt.awt;

import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public final class FIIORegistry {

    private static final Map<Class<? extends FIIOServiceProvider>, FIIOServiceProvider> providers = new ConcurrentHashMap<>();

    private FIIORegistry(){}

    static {
        registerBasicServiceProviders();
    }

    public static void registerBasicServiceProviders() {
        registerServiceProvider(new GifFIIOSpi());
        registerServiceProvider(new TiffFIIOSpi());
    }

    public static boolean registerServiceProvider(FIIOServiceProvider provider) {
        if (!providers.containsKey(provider.getClass())) {
            providers.put(provider.getClass(), provider);
            return true;
        }
        return false;
    }

    public static void registerServiceProviders(Iterator<FIIOServiceProvider> providers) {
        while (providers.hasNext()) {
            FIIOServiceProvider provider = providers.next();
            registerServiceProvider(provider);
        }
    }

    public static boolean deregisterServiceProvider(Class<? extends FIIOServiceProvider> clazz) {
        return providers.remove(clazz) != null;
    }

    public static void deregisterServiceProviders(Iterator<Class<? extends FIIOServiceProvider>> clazz) {
        while (clazz.hasNext()) {
            Class<? extends FIIOServiceProvider> provider = clazz.next();
            deregisterServiceProvider(provider);
        }
    }

    public static Collection<FIIOServiceProvider> getServiceProviders() {
        return providers.values();
    }

    public static FIIOServiceProvider getReader(String readerFormat) {
        for (FIIOServiceProvider provider : getServiceProviders()) {
            for (String mReaderFormat : provider.getReaderFormatNames()) {
                if (mReaderFormat.equalsIgnoreCase(readerFormat)) return provider;
            }
        }
        return null;
    }

    public static FIIOServiceProvider getWriter(String writerFormat) {
        for (FIIOServiceProvider provider : getServiceProviders()) {
            for (String mWriterFormat : provider.getWriterFormatNames()) {
                if (mWriterFormat.equalsIgnoreCase(writerFormat)) return provider;
            }
        }
        return null;
    }

    public static boolean contains(FIIOServiceProvider provider) {
        return providers.containsValue(provider);
    }

    public static boolean contains(Class<? extends FIIOServiceProvider> clazz) {
        return providers.containsKey(clazz);
    }

    public static void clear() {
        providers.clear();
    }

}
