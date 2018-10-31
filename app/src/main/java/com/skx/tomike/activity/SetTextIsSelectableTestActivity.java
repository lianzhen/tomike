package com.skx.tomike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * setTextIsSelectable()方法测试类
 * <p>
 * vivo x7 ：长按无法复制，双击可实现可选复制
 * 三星note 3：xml中 android:textIsSelectable="true"   java中setTextIsSelectable(false); 这个时候如果跳出当前页面会报错：StackOverflowError，其他情况的设置一切都是ok的
 */
public class SetTextIsSelectableTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text_is_selectable_test);


        TextView tv_textCopyTest_content = (TextView) findViewById(R.id.textCopyTest_content);
        tv_textCopyTest_content.setText("啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊" +
                "啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊啊啊啊啊啊啊啊 啊啊");

        tv_textCopyTest_content.setTextIsSelectable(false);
    }

    public void gotoOther(View view) {
        Intent intent = new Intent(this, MatrixImageActivity.class);
        startActivity(intent);
    }
}
