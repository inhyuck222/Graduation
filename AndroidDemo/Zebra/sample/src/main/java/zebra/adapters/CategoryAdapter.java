package zebra.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import zebra.beans.CategoryItem;
import zebra.views.CategoryItemView;

/**
 * Created by multimedia on 2016-05-27.
 */
public class CategoryAdapter extends BaseAdapter {

    private List<CategoryItem> items = new ArrayList<CategoryItem>();

    public void add(CategoryItem item) {
        items.add(item);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoryItemView view = null;

        if (convertView == null) {
            view = new CategoryItemView(parent.getContext());
        } else {
            view = (CategoryItemView) convertView;
        }
        view.setViewItem(items.get(position));

        return view;
    }
}
