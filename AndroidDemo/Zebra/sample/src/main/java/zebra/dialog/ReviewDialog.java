package zebra.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import example.zxing.R;

/**
 * Created by multimedia on 2016-05-20.
 */
public class ReviewDialog extends Dialog {
    EditText reviewEditText;
    Button dialogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.review_dialog);
        dialogSet();
        //setSty

        reviewEditText = (EditText)findViewById(R.id.reviewEditText);
        dialogButton = (Button)findViewById(R.id.dialogButton);
    }

    public ReviewDialog(Context context) {
        super(context);
    }

    public void dialogSet(){
        this.setTitle("안녕");
    }
}
