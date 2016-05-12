package example.zxing;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by multimedia on 2016-05-01.
 */
public class ReviewActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<ReviewObject> items;
    ReviewObject[] item;
    RatingBar[] rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        initRecyclerView();

        setList();

        recyclerView.setAdapter(new ReviewAdapter(getApplicationContext(), items, R.layout.activity_main));
    }

    void initRecyclerView(){
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    void setList(){
        items=new ArrayList<>();
        item=new ReviewObject[10];
        for(int i=0;i<10;i++){
            //rating[i] = (RatingBar) findViewById(R.id.ratingBar1);
            item[i]=new ReviewObject(R.drawable.icon,"#"+i);
            items.add(item[i]);
        }
//      for(int i=0;i<10;i++)item[i]=new Recycler_item(R.drawable.a,"#"+i, rating[i]);
//      for(int i=0;i<10;i++) items.add(item[i]);
    }

    public void buttonClicked(View v){
        Intent intent = new Intent();
        ComponentName name = new ComponentName("example.zxing", "example.zxing.MainActivity");

        intent.setComponent(name);

        startActivity(intent);
        finish();
    }
}
