package com.eMedak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GenerateNumber extends AppCompatActivity {
    TextView tv_complaintnumber;
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_number);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        tv_complaintnumber = (TextView)findViewById(R.id.tv_complaintnumber);
        getIntent().getStringExtra("number");
        tv_complaintnumber.setText("Your Grievance Reference Id : "+getIntent().getStringExtra("number"));
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GenerateNumber.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
