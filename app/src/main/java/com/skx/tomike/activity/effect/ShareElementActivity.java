package com.skx.tomike.activity.effect;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * 共享元素1
 */
public class ShareElementActivity extends SkxBaseActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_share_element);
        imageView = (ImageView) findViewById(R.id.imageView);

        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShareElementActivity.this, ShareElement2Activity.class);
                    // create the transition animation - the images in the layouts
                    // of both activities are defined with android:transitionName="robot"
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ShareElementActivity.this, imageView, "shareElement");
                        ShareElementActivity.this.startActivity(intent, options.toBundle());
                    } else {
                        ShareElementActivity.this.startActivity(intent);
                    }
                }
            });
        }
    }
}
