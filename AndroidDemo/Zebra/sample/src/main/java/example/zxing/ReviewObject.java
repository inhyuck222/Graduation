package example.zxing;

import android.widget.RatingBar;

/**
 * Created by multimedia on 2016-05-12.
 */
public class ReviewObject {
    int image;
    String title;
    RatingBar rating;

    int getImage() { return this.image; }
    String getTitle(){
        return this.title;
    }

    ReviewObject(int image, String title){
        this.image=image;
        this.title=title;
        this.rating = null;
    }
}
