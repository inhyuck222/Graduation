package com.example.multimedia.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Pager tab define
    private static final String TAB_TAG = "currentTab";
    private static final String TAB_ID_FLAG = "tab_flag";
    private static final String TAB_ID_FILEMNG = "tab_filemng";
    private static final String TAB_ID_SETTINGS = "tab_settings";

    // Intent Extra define
    public static final String EXTRA_KEY_WHOS_PAGE = "whosPage";
    public static final String EXTRA_VALUE_MYPAGE = "myPage";

    // Tab Pager
    TabHost tabHost;
    ViewPager pager;
    TabsAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        pager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new TabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_accessibility_black_24dp, null)), TabFragment1.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("",ResourcesCompat.getDrawable(getResources(), R.drawable.ic_accessibility_black_24dp, null)), TabFragment2.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab3").setIndicator("", ResourcesCompat.getDrawable(getResources(), R.drawable.ic_accessibility_black_24dp, null)), TabFragment3.class, null);
        setTabColor(tabHost);

        mAdapter.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setTabColor(tabHost);
                if (tabId.equals("tab1")) {

                } else if (tabId.equals("tab2")) {

                } else {

                }
            }
        });

    }

    public void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent)); // selected
    }


    public void pushSignUpFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.pager, new TabFragment4()).addToBackStack(null).commit();
    }


    // Back Pressed 처리 in Fragment
    public interface OnBackPressedListener {
        public void onBackPressed();
    }
    OnBackPressedListener mOnBackPressedListener;

    public void setOnBackPressedListener (OnBackPressedListener listener) {
        mOnBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if (mOnBackPressedListener != null) {
            mOnBackPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

}