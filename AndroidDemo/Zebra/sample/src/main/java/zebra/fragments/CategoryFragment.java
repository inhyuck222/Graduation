package zebra.fragments;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import example.zxing.R;
import zebra.adapters.CategoryAdapter;
import zebra.beans.CategoryItem;

/**
 * Created by multimedia on 2016-05-27.
 */
public class CategoryFragment extends Fragment {
    GridView gridView;
    CategoryAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        setGridView(v);


        return v;
    }

    private void setGridView(View v){
        gridView = (GridView) v.findViewById(R.id.gridView);
        mAdapter = new CategoryAdapter();
        gridView.setAdapter(mAdapter);

        for (int i = 0; i < 10; i++) {
            CategoryItem item = new CategoryItem("","야미야미");
            mAdapter.add(item);
        }
    }
}
