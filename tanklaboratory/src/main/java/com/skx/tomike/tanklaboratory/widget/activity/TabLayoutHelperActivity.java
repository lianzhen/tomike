package com.skx.tomike.tanklaboratory.widget.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.fragment.TabLayoutContentFragment;
import com.skx.tomike.tanklaboratory.widget.view.TabLayoutHelper;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.utils.SkxDrawableUtil;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutHelperActivity extends SkxBaseActivity<BaseViewModel> {

    private TabLayout mTabLayout;
    private ViewPager vpContent;
    private TextView txtContent;
    private GridLayout selectorGridLayout;

    private final List<String> tabList = new ArrayList<>();

    @Override
    protected void initParams() {
        tabList.add("天猫");
        tabList.add("京东");
        tabList.add("拼多多");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tablayout_helper;
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.tabLayoutHelper_tab);
        vpContent = findViewById(R.id.tabLayoutHelper_content);
        txtContent = findViewById(R.id.tabLayoutHelper_textContent);
        selectorGridLayout = findViewById(R.id.tabLayoutHelper_selector);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      系统提供的使用方式
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));
//        mTabLayout.addTab(mTabLayout.newTab().setText("啊呀呦"));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));

//        View v = LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null);
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(v));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));
//        mTabLayout.addTab(mTabLayout.newTab().setText("啊呀呦"));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(v));
//        mTabLayout.addTab(mTabLayout.newTab().setCustomView(R.layout.layout_tab_item));

//        mTabLayout.getTabAt(4).select();
//        Log.e("删除前 选中的tab位置", "" + mTabLayout.getSelectedTabPosition());
//        mTabLayout.removeTabAt(mTabLayout.getTabCount() - 1);
//        mTabLayout.removeTabAt(mTabLayout.getTabCount() - 1);
//        Log.e("删除后 选中的tab位置", "" + mTabLayout.getSelectedTabPosition());

        initGridLayout();
//        new TabLayoutHelper(mTabLayout).setSimpleAdapter(tabList)
//                .setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.skx_ff4081))
//                .setSelectedTabIndicatorHeight(10)
//                .setOnItemClickListener(new TabLayoutHelper.OnTabCustomViewClick() {
//                    @Override
//                    public void onItemClick(TabLayout.Tab tab, int position, View view) {
//                        contentText.setText(tabList.get(position));
//                    }
//                });

        // 自定义view 加载
//        new TabLayoutHelper<String>(mTabLayout).
//                setCustomAdapter(LayoutInflater.from(this).inflate(R.layout.layout_tab_item, null), new TabLayoutHelper.TabLayoutAdapter<String>(tabList) {
//                    @Override
//                    public void bindData(int position, View view, String charSequence) {
//                        ImageView imgv_tabIcon = (ImageView) view.findViewById(R.id.tab_icon);
//                        TextView tv_tabTitle = (TextView) view.findViewById(R.id.tab_title);
//                        tv_tabTitle.setText(charSequence);
//                    }
//                })
//                .setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.skx_ff4081))
//                .setSelectedTabIndicatorHeight(10)
//                .setOnItemClickListener(new TabLayoutHelper.OnTabCustomViewClick() {
//                    @Override
//                    public void onItemClick(TabLayout.Tab tab, int position, View view) {
//                        contentText.setText(tabList.get(position));
//                    }
//                });

        List<Fragment> fragments = new ArrayList<>();
        TabLayoutContentFragment fragment1 = TabLayoutContentFragment.newInstance(tabList.get(0));
        TabLayoutContentFragment fragment2 = TabLayoutContentFragment.newInstance(tabList.get(1));
        TabLayoutContentFragment fragment3 = TabLayoutContentFragment.newInstance(tabList.get(2));
        TabLayoutContentFragment fragment4 = TabLayoutContentFragment.newInstance(tabList.get(3));
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        vpContent.setAdapter(adapter);

        // 布局文件xml 加载
        new TabLayoutHelper<String>(mTabLayout)
                .initTabIndicator(ContextCompat.getColor(this, R.color.skx_ff4081), 5)
                .setCustomAdapter(R.layout.layout_tab_item, new TabLayoutHelper.TabLayoutAdapter<String>(tabList) {
                    @Override
                    public void bindData(int position, View view, String charSequence) {
                        TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                        tv_tabTitle.setText(charSequence);
                    }
                })
                .setOnCustomItemSelectorListener(new TabLayoutHelper.OnCustomItemSelectorListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab, int position, View view) {
                        TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                        tv_tabTitle.setTextColor(Color.parseColor("#ff4081"));
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab, int position, View view) {
                        TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                        tv_tabTitle.setTextColor(Color.parseColor("#323232"));
                    }
                })
                .setOnItemClickListener(new TabLayoutHelper.OnTabCustomViewClick() {
                    @Override
                    public void onItemClick(TabLayout.Tab tab, int position, View view) {
                        txtContent.setText(tabList.get(position));
                    }
                })
                .initSelectedCustomTab(4)
                .customTabSetupWithViewPager(vpContent);

//        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                vpContent.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        /*
        1.mTabLayout 添加view会报错：Only TabItem instances can be added to TabLayout.
          mTabLayout.addView(inflate);
        */
    }

    public static class FragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

    }

    private void initGridLayout() {
        for (int i = 0, j = tabList.size(); i < j; i++) {
            String name = tabList.get(i);

            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.width = 300;
            lp.height = 200;
//            lp.columnSpec = 10;
//            lp.rowSpec
            lp.topMargin = 10;
            lp.rightMargin = 20;

            TextView textView = new TextView(this);
            textView.setText(name);
            textView.setGravity(Gravity.CENTER);
            ViewCompat.setBackground(textView, (new SkxDrawableUtil()).getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                    .setStroke(ContextCompat.getColor(this, R.color.skx_2c3e50), 3)
                    .setCornerRadius(4)
                    .create());

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mTabLayout.setScrollPosition(2, 0.5f, true);
                }
            });

            selectorGridLayout.addView(textView, lp);
        }
    }
}
