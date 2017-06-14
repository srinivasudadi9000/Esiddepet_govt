package com.eMedak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class VerificationApplication extends AppCompatActivity {
    ImageView iv_back;
    EditText input_description;
    Button btn_submit, btn_clearall;
    private int keyDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificationapp);

        input_description = (EditText) findViewById(R.id.input_description);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_clearall = (Button) findViewById(R.id.btn_clearall);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerificationApplication.this, SelectList.class);
                startActivity(i);
                finish();
            }
        });
        btn_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_description.setText("");
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_description.getText().toString().length() == 0) {
                    validations.MyAlertBox(VerificationApplication.this, "Enter Service Request Number ");
                } else {
                    Intent i = new Intent(VerificationApplication.this, ShowComplaintDetailsApplication.class);
                    i.putExtra("id", input_description.getText().toString());
                    startActivity(i);
                }

            }
        });

    }
}
