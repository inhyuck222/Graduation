package example.zxing;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by songmho on 2015-07-12.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    List<ReviewObject> items;
    int item_layout;
    public ReviewAdapter(Context context, List<ReviewObject> items, int item_layout) {
        this.context=context;
        this.items=items;
        this.item_layout=item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,null);
        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReviewObject item=items.get(position);
        Drawable drawable=context.getResources().getDrawable(item.getImage());
        holder.image.setBackground(drawable);
        holder.title.setText(item.getTitle());
//        item.setRating();
//        holder.ratingBar.setRating(item.rating.getRating());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        RatingBar ratingBar;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.image);
            title=(TextView)itemView.findViewById(R.id.title);
            ratingBar=(RatingBar)itemView.findViewById(R.id.ratingBar1);
            cardview=(CardView)itemView.findViewById(R.id.cardview);
        }
    }
}
