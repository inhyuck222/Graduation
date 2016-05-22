package zebra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import example.zxing.R;
import zebra.adapters.NaviAdapter;
import zebra.beans.NaviItem;
import zebra.dialog.ReviewDialog;
import zebra.json.Review;
import zebra.manager.BarcodeManager;
import zebra.network.NetworkManager;
import zebra.views.NaviHeaderView;
import zebra.beans.ReviewItem;
import zebra.adapters.ReviewAdapter;

/**
 * Created by multimedia on 2016-05-01.
 */
public class ReviewActivity extends AppCompatActivity {
    ListView reviewList;
    ReviewAdapter mAdapter;
    View reviewHeader;

    String barcode;

    //for toolbar
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    NaviAdapter naviAdapter;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barcode = BarcodeManager.getInstance().getBarcode();
        network();

        setContentView(R.layout.activity_review);

        reviewList = (ListView) findViewById(R.id.reviewList);
        mAdapter = new ReviewAdapter();
        reviewList.setAdapter(mAdapter);

        reviewHeader = getLayoutInflater().inflate(R.layout.review_header, null, false);

        reviewList.addHeaderView(reviewHeader);

        //Toolbar 설정
        setToolbar();

        reviewHeader.findViewById(R.id.dialogButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ReviewDialog dialogFragment = new ReviewDialog();
                dialogFragment.show(fm, "dialog");
            }
        });
    }

    public void network(){
        NetworkManager.getInstance().review(this, barcode, new NetworkManager.OnResultResponseListener<Review>() {
            @Override
            public void onSuccess(Review result) {
                if(result.productInfo==null){
                    //Toast.makeText(ReviewActivity.this, "없어", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ReviewActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                    //상품 없는 Activity로 이동
                }
                else{
                    for (int i = 0; i < 10; i++) {
                        ReviewItem item = new ReviewItem(R.drawable.icon, result.reviews.get(i).id , 3, result.reviews.get(i).reviewText);
                        mAdapter.add(item);
                    }
                }
                Toast.makeText(ReviewActivity.this, "성공", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(int code, String responseString) {
                Toast.makeText(ReviewActivity.this, "실패", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mDrawerList = (ListView) findViewById(R.id.naviList);
        naviAdapter = new NaviAdapter();
        mDrawerList.setAdapter(naviAdapter);

        NaviHeaderView naviHeader = new NaviHeaderView(ReviewActivity.this);
        mDrawerList.addHeaderView(naviHeader);

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                NaviItem item = new NaviItem(R.drawable.ic_perm_identity_black_48dp, "프로필");
                naviAdapter.add(item);
            }
            if (i == 1) {
                NaviItem item = new NaviItem(R.drawable.ic_library_books_black_48dp, "나의 리뷰");
                naviAdapter.add(item);
            }
            if (i == 2) {
                NaviItem item = new NaviItem(R.drawable.ic_redeem_black_48dp, "선물함");
                naviAdapter.add(item);
            }
            if (i == 3) {
                NaviItem item = new NaviItem(R.drawable.logout, "로그아웃");
                naviAdapter.add(item);
            }
        }

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int editedPosition = position + 1;
                Toast.makeText(ReviewActivity.this, "You selected item " + editedPosition, Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View v) {
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
