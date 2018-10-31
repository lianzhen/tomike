package com.skx.tomike.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.util.TagImageSpan;

public class SpannableStringBuilderActivity extends SkxBaseActivity {

    TextView textView;
    TextView textView_topMark;
    String contentStr = "房客已退房，如果入住期间对您的财产造成损失需要赔偿，请立即与房客协商解决，若双方不能达成一致，之后可申请小猪介入。\n{23:48}之内可以提交索赔要求，请及时操作。";
    String contentStr1 = "$199.99HDK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string_builder);

        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        textView = (TextView) findViewById(R.id.textView_value);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView_topMark = (TextView) findViewById(R.id.textView_topMark);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        setStateView(textView, contentStr);

        setMarkView();
    }

    private void setMarkView() {
        SpannableString ss = new SpannableString(contentStr1);
        //设置上下标
//        ss.setSpan(new SubscriptSpan(), 7, contentStr1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
        ss.setSpan(new SuperscriptSpan(),  7, contentStr1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标
        textView_topMark.setText(ss);
    }

    private void setStateView(TextView textView, String contentStr) {
        int tagStart = contentStr.indexOf("{");
        int tagEnd = contentStr.indexOf("}");
        String contentStr0 = contentStr.substring(0, tagStart);
        SpannableString contentStr0Style = new SpannableString(contentStr0);
        contentStr0Style.setSpan(new BackgroundColorSpan(Color.parseColor("#ffffff")), 0, contentStr0.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentStr0Style.setSpan(new ForegroundColorSpan(Color.parseColor("#323232")), 0, contentStr0.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(contentStr0Style);

        for (int i = tagStart; i <= tagEnd; i++) {
            char c = contentStr.charAt(i);
            String charStr = String.format(" %s ", c);
            SpannableString style = new SpannableString(charStr);
            style.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, charStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
            if (" : ".equals(charStr)) {
                textView.append(charStr);
                continue;
            } else if (" { ".equals(charStr) || " } ".equals(charStr)) {
                continue;
            } else {
                TagImageSpan span = new TagImageSpan(0, charStr.length());
                style.setSpan(span, 0, charStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.append(style);
        }

        String contentStr2 = contentStr.substring(tagEnd + 1, contentStr.length());
        SpannableString contentStr2Style = new SpannableString(contentStr2);
        contentStr2Style.setSpan(new BackgroundColorSpan(Color.parseColor("#ffffff")), 0, contentStr2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentStr2Style.setSpan(new ForegroundColorSpan(Color.parseColor("#323232")), 0, contentStr2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.append(contentStr2Style);
    }
}
