package zebra.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import example.zxing.R;
import zebra.activity.MyPageActivity;
import zebra.adapters.MyReviewAdapter;
import zebra.adapters.ReviewAdapter;
import zebra.beans.MyReviewItem;
import zebra.data.ReviewData;
import zebra.json.MyReview;
import zebra.json.Review;
import zebra.manager.MemberManager;
import zebra.manager.NetworkManager;
import zebra.views.ReviewHeaderView;

/**
 * Created by multimedia on 2016-05-29.
 */
public class MyReviewFragment extends Fragment {

    ListView reviewList;
    MyReviewAdapter mAdapter;
    MyReview result;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_review, container, false);

        result = MemberManager.getInstance().getReviews();

        setListView(view);

        return view;
    }

    private void setListView(View view){
        reviewList = (ListView) view.findViewById(R.id.reviewList);
        mAdapter = new MyReviewAdapter();

        reviewList.setAdapter(mAdapter);

        for (int i = 0; i < MemberManager.getInstance().getReviews().reviews.size(); i++) {
            MyReviewItem item = new MyReviewItem(result.reviews.get(i).productUrl,
                    result.reviews.get(i).id ,
                    result.reviews.get(i).starPoint,
                    result.reviews.get(i).reviewText);
            mAdapter.add(item);
        }
    }
}
