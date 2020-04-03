package com.skx.tomike.tanklaboratory.animation.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.TreeView;

/**
 * 贝塞尔动画事例
 */
public class BezierAnimatorActivity extends AppCompatActivity {

    private TreeView mTreeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_animatior);

        mTreeView = findViewById(R.id.bezierAnimator_tree_view);

    }
}
