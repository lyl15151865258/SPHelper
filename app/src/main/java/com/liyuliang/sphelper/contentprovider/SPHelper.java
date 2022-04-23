package com.liyuliang.sphelper.contentprovider;

import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CONTENT_URI;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_NAME;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_TYPE;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_VALUE;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.NULL_STRING;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.SEPARATOR;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_BOOLEAN;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_CLEAN;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_CONTAIN;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_DELETE;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_FLOAT;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_GET_ALL;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_INT;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_LONG;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_STRING;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.TYPE_STRING_SET;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.VALUE;

import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * SharedPreferences工具类
 * Created at 2018/12/14 20:27
 *
 * @author LiYuliang
 * @version 1.0
 */

public class SPHelper {

    public static final String COMMA_REPLACEMENT = "__COMMA__";

    public static Context context;

    public static void checkContext() {
        if (context == null) {
            throw new IllegalStateException("context has not been initialed");
        }
    }

    public static void init(Application application) {
        context = application.getApplicationContext();
    }

    public synchronized static void save(String name, Boolean t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, Boolean t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String name, String t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_STRING + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, String t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_STRING + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String name, Integer t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_INT + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, Integer t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_INT + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String name, Long t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_LONG + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, Long t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_LONG + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String name, Float t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, Float t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        cv.put(VALUE, t);
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String name, Set<String> t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        Set<String> convert = new HashSet<>();
        for (String string : t) {
            convert.add(string.replace(",", COMMA_REPLACEMENT));
        }
        cv.put(VALUE, convert.toString());
        cr.update(uri, cv, null, null);
    }

    public synchronized static void save(String spName, String name, Set<String> t) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
        ContentValues cv = new ContentValues();
        Set<String> convert = new HashSet<>();
        for (String string : t) {
            convert.add(string.replace(",", COMMA_REPLACEMENT));
        }
        cv.put(VALUE, convert.toString());
        cr.update(uri, cv, null, null);
    }

    public static String getString(String name, String defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_STRING + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return rtn;
    }

    public static String getString(String spName, String name, String defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_STRING + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return rtn;
    }

    public static int getInt(String name, int defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_INT + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Integer.parseInt(rtn);
    }

    public static int getInt(String spName, String name, int defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_INT + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Integer.parseInt(rtn);
    }

    public static float getFloat(String name, float defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_FLOAT + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Float.parseFloat(rtn);
    }

    public static float getFloat(String spName, String name, float defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_FLOAT + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Float.parseFloat(rtn);
    }

    public static boolean getBoolean(String name, boolean defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Boolean.parseBoolean(rtn);
    }

    public static boolean getBoolean(String spName, String name, boolean defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_BOOLEAN + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Boolean.parseBoolean(rtn);
    }

    public static long getLong(String name, long defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_LONG + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Long.parseLong(rtn);
    }

    public static long getLong(String spName, String name, long defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_LONG + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        return Long.parseLong(rtn);
    }

    public static Set<String> getStringSet(String name, Set<String> defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        if (!rtn.matches("\\[.*\\]")) {
            return defaultValue;
        }
        String sub = rtn.substring(1, rtn.length() - 1);
        String[] spl = sub.split(", ");
        Set<String> returns = new HashSet<>();
        for (String t : spl) {
            returns.add(t.replace(COMMA_REPLACEMENT, ", "));
        }
        return returns;
    }

    public static Set<String> getStringSet(String spName, String name, Set<String> defaultValue) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_STRING_SET + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return defaultValue;
        }
        if (!rtn.matches("\\[.*\\]")) {
            return defaultValue;
        }
        String sub = rtn.substring(1, rtn.length() - 1);
        String[] spl = sub.split(", ");
        Set<String> returns = new HashSet<>();
        for (String t : spl) {
            returns.add(t.replace(COMMA_REPLACEMENT, ", "));
        }
        return returns;
    }

    public static boolean contains(String name) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_CONTAIN + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return false;
        } else {
            return Boolean.parseBoolean(rtn);
        }
    }

    public static boolean contains(String spName, String name) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_CONTAIN + SEPARATOR + name);
        String rtn = cr.getType(uri);
        if (rtn == null || rtn.equals(NULL_STRING)) {
            return false;
        } else {
            return Boolean.parseBoolean(rtn);
        }
    }

    public static void remove(String name) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_DELETE + SEPARATOR + name);
        cr.delete(uri, null, null);
    }

    public static void remove(String spName, String name) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_DELETE + SEPARATOR + name);
        cr.delete(uri, null, null);
    }

    public static void clear() {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_CLEAN);
        cr.delete(uri, null, null);
    }

    public static void clear(String spName) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_CLEAN);
        cr.delete(uri, null, null);
    }

    public static HashMap<String, ?> getAll() {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + SPHelperImpl.DEFAULT_NAME + SEPARATOR + TYPE_GET_ALL);
        Cursor cursor = cr.query(uri, null, null, null, null);
        HashMap resultMap = new HashMap();
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(CURSOR_COLUMN_NAME);
            int typeIndex = cursor.getColumnIndex(CURSOR_COLUMN_TYPE);
            int valueIndex = cursor.getColumnIndex(CURSOR_COLUMN_VALUE);
            do {
                String key = cursor.getString(nameIndex);
                String type = cursor.getString(typeIndex);
                Object value = null;
                if (type.equalsIgnoreCase(TYPE_STRING)) {
                    value = cursor.getString(valueIndex);
                    if (((String) value).contains(COMMA_REPLACEMENT)) {
                        String str = (String) value;
                        if (str.matches("\\[.*\\]")) {
                            String sub = str.substring(1, str.length() - 1);
                            String[] spl = sub.split(", ");
                            Set<String> returns = new HashSet<>();
                            for (String t : spl) {
                                returns.add(t.replace(COMMA_REPLACEMENT, ", "));
                            }
                            value = returns;
                        }
                    }
                } else if (type.equalsIgnoreCase(TYPE_BOOLEAN)) {
                    value = cursor.getString(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_INT)) {
                    value = cursor.getInt(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_LONG)) {
                    value = cursor.getLong(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_FLOAT)) {
                    value = cursor.getFloat(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_STRING_SET)) {
                    value = cursor.getString(valueIndex);
                }
                resultMap.put(key, value);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return resultMap;
    }

    public static Map<String, ?> getAll(String spName) {
        checkContext();
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.parse(CONTENT_URI + SEPARATOR + spName + SEPARATOR + TYPE_GET_ALL);
        Cursor cursor = cr.query(uri, null, null, null, null);
        HashMap resultMap = new HashMap();
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(CURSOR_COLUMN_NAME);
            int typeIndex = cursor.getColumnIndex(CURSOR_COLUMN_TYPE);
            int valueIndex = cursor.getColumnIndex(CURSOR_COLUMN_VALUE);
            do {
                String key = cursor.getString(nameIndex);
                String type = cursor.getString(typeIndex);
                Object value = null;
                if (type.equalsIgnoreCase(TYPE_STRING)) {
                    value = cursor.getString(valueIndex);
                    if (((String) value).contains(COMMA_REPLACEMENT)) {
                        String str = (String) value;
                        if (str.matches("\\[.*\\]")) {
                            String sub = str.substring(1, str.length() - 1);
                            String[] spl = sub.split(", ");
                            Set<String> returns = new HashSet<>();
                            for (String t : spl) {
                                returns.add(t.replace(COMMA_REPLACEMENT, ", "));
                            }
                            value = returns;
                        }
                    }
                } else if (type.equalsIgnoreCase(TYPE_BOOLEAN)) {
                    value = cursor.getString(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_INT)) {
                    value = cursor.getInt(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_LONG)) {
                    value = cursor.getLong(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_FLOAT)) {
                    value = cursor.getFloat(valueIndex);
                } else if (type.equalsIgnoreCase(TYPE_STRING_SET)) {
                    value = cursor.getString(valueIndex);
                }
                resultMap.put(key, value);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return resultMap;
    }

}