package zebra.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.zxing.R;
import zebra.activity.ReviewActivityTest;
import zebra.json.Review;
import zebra.manager.MemberManager;
import zebra.manager.NetworkManager;
import zebra.manager.ReviewManager;
import zebra.manager.ScanManager;

/**
 * Created by multimedia on 2016-05-27.
 */
public class ReviewRegisterFragment  extends Fragment {
    EditText reviewEditText;
    Button registerButton, cancelButton;
    AppCompatRatingBar ratingBar;
    String id, barcode, productUrl, memberUrl, reviewText;
    double starPoint;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review,container,false);
        reviewEditText = (EditText)view.findViewById(R.id.reviewEditText);

        registerButton = (Button)view.findViewById(R.id.registerButton);
        cancelButton = (Button)view.findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( ((ReviewActivityTest)getContext()), "취소", Toast.LENGTH_LONG).show();
                ((ReviewActivityTest) getActivity()).popFragment();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewGettedOnclick = v;
                id = MemberManager.getInstance().getId();
                barcode = ScanManager.getInstance().getBarcode();
                productUrl = ScanManager.getInstance().getProductUrl();
                memberUrl = MemberManager.getInstance().getMemberUrl();
                reviewText = reviewEditText.getText().toString();
                starPoint = (double) ratingBar.getRating();
                if (reviewText.equals("") || starPoint == 0)
                    Toast.makeText(v.getContext(), "리뷰와 별점을 입력해주세요", Toast.LENGTH_LONG).show();
                else {
                    NetworkManager.getInstance().reviewRegister(v.getContext(), id, reviewText, barcode, starPoint, productUrl, memberUrl, new NetworkManager.OnResultResponseListener<Review>() {
                        @Override
                        public void onSuccess(Review result) {
                            ((ReviewActivityTest) getActivity()).pushReviewFragment();
                        }
                        @Override
                        public void onFail(int code, String responseString) {
                            Toast.makeText(viewGettedOnclick.getContext(), "야미실패", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        return view;
    }
}
