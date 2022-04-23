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

//        SPHelper.save("bbb", "22");
//        SPHelper.save("myConfig", "bbb", "22");

        Log.d("MainActivity", "默认文件bbb：" + SPHelper.getString("bbb", ""));
        Log.d("MainActivity", "自定义文件dog：" + SPHelper.getString("myConfig", "bbb", ""));
    }
}