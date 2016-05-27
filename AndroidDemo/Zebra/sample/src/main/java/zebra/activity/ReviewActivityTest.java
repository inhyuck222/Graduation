package zebra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import example.zxing.R;
import zebra.adapters.NaviAdapter;
import zebra.adapters.ReviewTabsAdapter;
import zebra.beans.NaviItem;
import zebra.beans.ReviewItem;
import zebra.fragments.ReviewFragment;
import zebra.fragments.ReviewRegisterFragment;
import zebra.json.Review;
import zebra.manager.NetworkManager;
import zebra.manager.ReviewManager;
import zebra.manager.ScanManager;
import zebra.views.NaviHeaderView;

/**
 * Created by multimedia on 2016-05-27.
 */
public class ReviewActivityTest extends AppCompatActivity {
    String barcode,productUrl;
    Review result;

    //for toolbar
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    NaviAdapter naviAdapter;
    ActionBarDrawerToggle mDrawerToggle;

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
    ReviewTabsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_test);

        setToolbar();

        barcode = ScanManager.getInstance().getBarcode();
        productUrl = ScanManager.getInstance().getProductUrl();
        result = (Review)getIntent().getParcelableExtra("Result");
        ReviewManager.getInstance().setReview(result);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(android.R.id.tabcontent, new ReviewFragment()).commit();
        }

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        pager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new ReviewTabsAdapter(this, getSupportFragmentManager(), tabHost, pager);

        mAdapter.addTab(tabHost.newTabSpec("tab1").setIndicator("", ResourcesCompat.getDrawable(getResources(), R.drawable.zebra_icon_logo, null)), ReviewFragment.class, null);
        mAdapter.addTab(tabHost.newTabSpec("tab2").setIndicator("",ResourcesCompat.getDrawable(getResources(), R.drawable.zebra_icon_logo, null)), ReviewRegisterFragment.class, null);
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

    public void pushReviewFragment() {
        getFragmentManager().beginTransaction().replace(android.R.id.tabcontent, new ReviewFragment()).addToBackStack(null).commit();
    }

    public void pushReviewRegisterFragment() {
        getFragmentManager().beginTransaction().replace(android.R.id.tabcontent, new ReviewRegisterFragment()).addToBackStack(null).commit();
    }

    public void popFragment() {
        getFragmentManager().popBackStack();
    }

    void setToolbar(){
        //Toolbar 설정
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        mDrawerList = (ListView)findViewById(R.id.naviList);
        naviAdapter = new NaviAdapter();
        mDrawerList.setAdapter(naviAdapter);

        NaviHeaderView header = new NaviHeaderView(ReviewActivityTest.this);
        mDrawerList.addHeaderView(header);

        //navbar 아이템들, 지워야됨
        for (int i=0; i<4; i++) {
            if(i == 0){NaviItem item = new NaviItem(R.drawable.ic_perm_identity_black_48dp, "프로필");naviAdapter.add(item);}
            if(i == 1){NaviItem item = new NaviItem(R.drawable.ic_library_books_black_48dp, "나의 리뷰");naviAdapter.add(item);}
            if(i == 2){NaviItem item = new NaviItem(R.drawable.ic_redeem_black_48dp, "선물함");naviAdapter.add(item);}
            if(i == 3){NaviItem item = new NaviItem(R.drawable.logout, "로그아웃");naviAdapter.add(item);}
        }

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int editedPosition = position+1;
                Toast.makeText(ReviewActivityTest.this, "You selected item " + editedPosition, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    public void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(ContextCompat.getColor(ReviewActivityTest.this, R.color.primary)); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(ContextCompat.getColor(ReviewActivityTest.this, R.color.accent_pressed)); // selected
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home: {
                if (mDrawerLayout.isDrawerOpen(mDrawerList)){
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("ReviewActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("ReviewActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                //ScanManager에 barcode를 set
                ScanManager.getInstance().setBarcode(result.getContents());
                barcode = ScanManager.getInstance().getBarcode();
                network();
            }
        } else {
            Log.d("ReviewActivity", "Cancelled scan");
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void network(){
        NetworkManager.getInstance().review(this, barcode, new NetworkManager.OnResultResponseListener<Review>() {
            @Override
            public void onSuccess(Review result) {
                if(result.productInfo==null){
                    Toast.makeText(ReviewActivityTest.this, "등록 된 상품이 없습니다.", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ReviewActivityTest.this,ProductRegisterActivity.class);
                    startActivity(i);
                    finish();
                }else if(result == null){
                    Toast.makeText(ReviewActivityTest.this, "등록 대기중", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    //ReviewManager에 result를 set
                    ScanManager.getInstance().setProductUrl(result.productInfo.productUrl);
                    ReviewManager.getInstance().setReview(result);
                    popFragment();
                    pushReviewFragment();
                }
                Toast.makeText(ReviewActivityTest.this, "성공", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(int code, String responseString) {
                Toast.makeText(ReviewActivityTest.this, "실패 "+code, Toast.LENGTH_LONG).show();
            }
        });
    }
}