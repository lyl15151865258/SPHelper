package com.liyuliang.sphelper.contentprovider;

import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_NAME;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_TYPE;
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.CURSOR_COLUMN_VALUE;
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
import static com.liyuliang.sphelper.contentprovider.ConstantUtil.VALUE;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * 利用ContentProvider封装的SharedPreferences跨进程数据共享
 * Created at 2018/12/14 20:27
 *
 * @author LiYuliang
 * @version 1.0
 */

public class SPContentProvider extends ContentProvider {

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String[] path = uri.getPath().split(SEPARATOR);
        String spName = path[1];
        String type = path[2];
        if (type.equals(TYPE_GET_ALL)) {
            Map<String, ?> all = SPHelperImpl.getAll(getContext(), spName);
            if (all != null) {
                MatrixCursor cursor = new MatrixCursor(new String[]{CURSOR_COLUMN_NAME, CURSOR_COLUMN_TYPE, CURSOR_COLUMN_VALUE});
                Set<String> keySet = all.keySet();
                for (String key : keySet) {
                    Object[] rows = new Object[3];
                    rows[0] = key;
                    rows[2] = all.get(key);
                    if (rows[2] instanceof Boolean) {
                        rows[1] = TYPE_BOOLEAN;
                    } else if (rows[2] instanceof String) {
                        rows[1] = TYPE_STRING;
                    } else if (rows[2] instanceof Integer) {
                        rows[1] = TYPE_INT;
                    } else if (rows[2] instanceof Long) {
                        rows[1] = TYPE_LONG;
                    } else if (rows[2] instanceof Float) {
                        rows[1] = TYPE_FLOAT;
                    }
                    cursor.addRow(rows);
                }
                return cursor;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        // 用这个来取数值
        String[] path = uri.getPath().split(SEPARATOR);
        String spName = path[1];
        String type = path[2];
        String key = path[3];
        if (type.equals(TYPE_CONTAIN)) {
            return SPHelperImpl.contains(getContext(), spName, key) + "";
        }
        return SPHelperImpl.get(getContext(), spName, key, type);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String[] path = uri.getPath().split(SEPARATOR);
        String spName = path[1];
        String key = path[3];
        Object obj = values.get(VALUE);
        if (obj != null) {
            boolean result = SPHelperImpl.save(getContext(), spName, key, obj);
            if (result) {
                return uri;
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String[] path = uri.getPath().split(SEPARATOR);
        String spName = path[1];
        String type = path[2];
        if (type.equals(TYPE_CLEAN)) {
            boolean result = SPHelperImpl.clear(getContext(), spName);
            if (result) {
                return 1;
            } else {
                return 0;
            }
        } else if (type.equals(TYPE_DELETE)) {
            String key = path[3];
            if (SPHelperImpl.contains(getContext(), spName, key)) {
                boolean result = SPHelperImpl.remove(getContext(), spName, key);
                if (result) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return insert(uri, values) == null ? 0 : 1;
    }
}