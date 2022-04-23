package com.liyuliang.sphelper.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * SharedPreferences实现类
 * Created at 2018/12/14 20:27
 *
 * @author LiYuliang
 * @version 1.0
 */

public class SPHelperImpl {

    public static final String DEFAULT_NAME = "default";

    private static final HashMap<String, SoftReference<Map<String, Object>>> sCacheMap = new HashMap<>();

    private static SharedPreferences getSP(Context context, String spName) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSP(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(DEFAULT_NAME, Context.MODE_PRIVATE);
    }

    private static Object getCachedValue(String spName, String name) {
        SoftReference<Map<String, Object>> softReference = sCacheMap.get(spName);
        if (softReference != null) {
            Map<String, Object> map = softReference.get();
            if (map != null) {
                return map.get(name);
            }
        }
        return null;
    }

    private static void setValueToCached(String spName, String name, Object value) {
        if (sCacheMap.containsKey(spName)) {
            SoftReference<Map<String, Object>> softReference = sCacheMap.get(spName);
            Map<String, Object> map;
            if (softReference == null) {
                map = new HashMap<>();
                softReference = new SoftReference<>(map);
            } else {
                map = softReference.get();
                if (map == null) {
                    map = new HashMap<>();
                    softReference = new SoftReference<>(map);
                }
            }
            map.put(name, value);
            sCacheMap.put(spName, softReference);
        } else {
            Map<String, Object> map = new HashMap<>();
            SoftReference<Map<String, Object>> softReference = new SoftReference<>(map);
            map.put(name, value);
            sCacheMap.put(spName, softReference);
        }
    }

    private static void cleanCachedValue(String spName, String key) {
        SoftReference<Map<String, Object>> softReference = sCacheMap.get(spName);
        if (softReference != null) {
            Map<String, Object> map = softReference.get();
            if (map != null) {
                if (key != null) {
                    map.remove(key);
                } else {
                    map.clear();
                }
            }
        }
    }

    public synchronized static <T> boolean save(Context context, String spName, String name, T t) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return false;
        }
        if (t.equals(getCachedValue(spName, name))) {
            return true;
        }
        SharedPreferences.Editor editor = sp.edit();
        if (t instanceof Boolean) {
            editor.putBoolean(name, (Boolean) t);
        }
        if (t instanceof String) {
            editor.putString(name, (String) t);
        }
        if (t instanceof Integer) {
            editor.putInt(name, (Integer) t);
        }
        if (t instanceof Long) {
            editor.putLong(name, (Long) t);
        }
        if (t instanceof Float) {
            editor.putFloat(name, (Float) t);
        }
        setValueToCached(spName, name, t);
        return editor.commit();
    }

    public static String get(Context context, String spName, String name, String type) {
        Object value = getCachedValue(spName, name);
        if (value == null) {
            value = get_impl(context, spName, name, type);
            setValueToCached(spName, name, value);
        }
        return value + "";
    }

    private static Object get_impl(Context context, String spName, String name, String type) {
        if (contains(context, spName, name)) {
            if (type.equalsIgnoreCase(ConstantUtil.TYPE_STRING)) {
                return getString(context, spName, name, null);
            } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_BOOLEAN)) {
                return getBoolean(context, spName, name, false);
            } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_INT)) {
                return getInt(context, spName, name, 0);
            } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_LONG)) {
                return getLong(context, spName, name, 0L);
            } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_FLOAT)) {
                return getFloat(context, spName, name, 0f);
            } else if (type.equalsIgnoreCase(ConstantUtil.TYPE_STRING_SET)) {
                return getString(context, spName, name, null);
            }
        }
        return null;
    }

    public static String getString(Context context, String spName, String name, String defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getString(name, defaultValue);
    }

    public static int getInt(Context context, String spName, String name, int defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getInt(name, defaultValue);
    }

    public static float getFloat(Context context, String spName, String name, float defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getFloat(name, defaultValue);
    }

    public static boolean getBoolean(Context context, String spName, String name, boolean defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getBoolean(name, defaultValue);
    }

    public static long getLong(Context context, String spName, String name, long defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getLong(name, defaultValue);
    }

    public static boolean contains(Context context, String spName, String name) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return false;
        }
        return sp.contains(name);
    }

    public static boolean remove(Context context, String spName, String key) {
        cleanCachedValue(spName, key);
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return false;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        return editor.commit();
    }

    public static boolean clear(Context context, String spName) {
        cleanCachedValue(spName, null);
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return false;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }

    public static Map<String, ?> getAll(Context context, String spName) {
        SharedPreferences sp = getSP(context, spName);
        return sp.getAll();
    }
}