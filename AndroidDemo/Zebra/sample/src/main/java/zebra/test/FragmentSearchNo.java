package zebra.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.zxing.R;

/**
 * Created by multimedia on 2016-05-26.
 */
public class FragmentSearchNo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_search_no_fragment, container, false);
    }
}