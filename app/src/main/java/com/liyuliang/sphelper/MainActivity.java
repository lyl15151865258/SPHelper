package com.liyuliang.sphelper;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.liyuliang.sphelper.contentprovider.SPHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 存储到默认xml文件（文件名为SPHelperImpl类中的DEFAULT_NAME）
        SPHelper.save("aaa", 1);
        SPHelper.save("bbb", "22");
        SPHelper.save("ccc", 100f);
        SPHelper.save("ddd", false);

        // 存储到指定名称的xml
        SPHelper.save("myConfig", "aaa", 1);
        SPHelper.save("myConfig", "bbb", "22");
        SPHelper.save("myConfig", "ccc", 100f);
        SPHelper.save("myConfig", "ddd", false);

        Log.d("MainActivity", "默认文件aaa：" + SPHelper.getInt("aaa", 0));
        Log.d("MainActivity", "默认文件bbb：" + SPHelper.getString("bbb", ""));
        Log.d("MainActivity", "默认文件ccc：" + SPHelper.getFloat("ccc", 12f));
        Log.d("MainActivity", "默认文件ddd：" + SPHelper.getBoolean("ddd", true));

        Log.d("MainActivity", "自定义文件cat：" + SPHelper.getInt("myConfig", "aaa", 0));
        Log.d("MainActivity", "自定义文件dog：" + SPHelper.getString("myConfig", "bbb", ""));
        Log.d("MainActivity", "自定义文件pig：" + SPHelper.getFloat("myConfig", "ccc", 12f));
        Log.d("MainActivity", "自定义文件tiger：" + SPHelper.getBoolean("myConfig", "ddd", true));

        // 移除默认xml文件的key-value
        SPHelper.remove("bbb");
        // 移除指定xml文件的key-value
        SPHelper.remove("myConfig", "bbb");

        Log.d("MainActivity", "移除默认文件bbb：" + SPHelper.getString("bbb", null));
        Log.d("MainActivity", "移除自定义文件bbb：" + SPHelper.getString("myConfig","bbb", null));

        // 清除默认xml所有key-value
        SPHelper.clear();
        // 清除指定xml所有key-value
        SPHelper.clear("myConfig");

        Log.d("MainActivity", "清除默认文件：" + SPHelper.getFloat("ccc", 12f));
        Log.d("MainActivity", "清除自定义文件：" + SPHelper.getFloat("myConfig", "ccc", 12f));


    }
}