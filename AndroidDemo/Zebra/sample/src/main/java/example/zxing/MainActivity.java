package example.zxing;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();


                Intent intent = new Intent();
                ComponentName name = new ComponentName("example.zxing","example.zxing.ReviewActivity");
                intent.setComponent(name);

                startActivity(intent);
            }
        } else {
            Log.d("MainActivity", "Cancelled scan");
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void scanClicked(View v){
        new IntentIntegrator(this).setCaptureActivity(ToolbarCaptureActivity.class).initiateScan();
    }

    public void reviewClicked(View v){
        Intent intent = new Intent();
        ComponentName name = new ComponentName("example.zxing", "example.zxing.ReviewActivity");

        intent.setComponent(name);

        startActivity(intent);
    }
}
