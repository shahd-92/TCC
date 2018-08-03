package com.udacity.shahd.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textView= (TextView) findViewById(R.id.result);
        Intent intent = this.getIntent();
        if (intent != null) {
            String barcode = getIntent().getStringExtra("code");
            if(barcode!=null)
            textView.setText(barcode);
        }
    }


    public void sterilization(View view) {
        Toast.makeText(this, "sterilization order sent to the trash", Toast.LENGTH_LONG).show();

    }

}
