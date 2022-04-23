package com.liyuliang.sphelper.contentprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    synchronized static <T> void save(Context context, String spName, String name, T t) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return;
        }
        if (t.equals(getCachedValue(spName, name))) {
            return;
        }
        SharedPreferences.Editor editor = sp.edit();
        Log.d("SPHelperImpl", "文件名：" + spName + "，保存数据：" + name + "," + t);
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
        editor.apply();
        setValueToCached(spName, name, t);
    }

    static String get(Context context, String spName, String name, String type) {
        Object value = getCachedValue(spName, name);
        if (value == null) {
            value = get_impl(context, spName, name, type);
            setValueToCached(spName, name, value);
        }
        return value + "";
    }

    private static Object get_impl(Context context, String spName, String name, String type) {
        if (contains(context, name)) {
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

    static String getString(Context context, String spName, String name, String defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getString(name, defaultValue);
    }

    static int getInt(Context context, String spName, String name, int defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getInt(name, defaultValue);
    }

    static float getFloat(Context context, String spName, String name, float defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getFloat(name, defaultValue);
    }

    static boolean getBoolean(Context context, String spName, String name, boolean defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getBoolean(name, defaultValue);
    }

    static long getLong(Context context, String spName, String name, long defaultValue) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return defaultValue;
        }
        return sp.getLong(name, defaultValue);
    }

    static boolean contains(Context context, String name) {
        Log.d("SPHelperImpl", "contains");
        SharedPreferences sp = getSP(context);
        if (sp == null) {
            return false;
        }
        return sp.contains(name);
    }

    static boolean contains(Context context, String spName, String name) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return false;
        }
        return sp.contains(name);
    }

    static void remove(Context context, String spName, String key) {
        SharedPreferences sp = getSP(context, spName);
        if (sp == null) {
            return;
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
        cleanCachedValue(spName, key);
    }

    static void clear(Context context, String spName) {
        SharedPreferences sp = getSP(context, spName);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        cleanCachedValue(spName, null);
    }

    static Map<String, ?> getAll(Context context, String spName) {
        SharedPreferences sp = getSP(context, spName);
        return sp.getAll();
    }
}