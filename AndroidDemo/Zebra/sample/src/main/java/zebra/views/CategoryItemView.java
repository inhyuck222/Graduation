package zebra.views;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import example.zxing.R;
import zebra.beans.CategoryItem;

/**
 * Created by multimedia on 2016-05-27.
 */
public class CategoryItemView extends FrameLayout{
    ImageView productImg;
    TextView productName;

    public CategoryItemView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.navi_header, this);

        productImg = (ImageView)findViewById(R.id.profileImage);
        productName = (TextView)findViewById(R.id.name);
    }

    public void setViewItem(CategoryItem item){
        productImg.setImageResource(R.drawable.zebra_icon_icon);
        productName.setText("야미야미");

    }
}
