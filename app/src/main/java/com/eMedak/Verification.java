package com.eMedak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Verification extends AppCompatActivity {
    ImageView iv_back;
    EditText input_description;
    Button btn_submit,btn_clearall;
    private int keyDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        input_description = (EditText)findViewById(R.id.input_description);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        btn_clearall = (Button)findViewById(R.id.btn_clearall);

        iv_back  =(ImageView)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Verification.this,SelectList.class);
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
                if (input_description.getText().toString().length()==0){
                    validations.MyAlertBox(Verification.this,"Enter Grievance ID ");
                }else{
                    Intent i = new Intent(Verification.this,ShowComplaintDetails.class);
                    i.putExtra("id",input_description.getText().toString());
                    startActivity(i);
                }
              /*  List<String> toEmailList = Arrays.asList("srinivasdadi9000@gmail.com"
                        .split("\\s*,\\s*"));
                new SendMailTask(Verfication.this).execute("android.chakri@gmail.com",
                        "gemsandroid", toEmailList, "myandroid team", "looking good");*/
            }
        });


/*
        input_description.addTextChangedListener(new TextWatcher() {

            private String a;

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                boolean flag = true;
                String eachBlock[] = input_description.getText().toString()
                        .split("-");
                for (int i = 0; i < eachBlock.length; i++) {
                    if (eachBlock[i].length() > 4) {
                        Log.v("11111111111111111111", "cc" + flag
                                + eachBlock[i].length());
                    }
                }
                if (flag) {
                    input_description.setOnKeyListener(new View.OnKeyListener() {

                        public boolean onKey(View v, int keyCode,
                                             KeyEvent event) {

                            if (keyCode == KeyEvent.KEYCODE_DEL)
                                keyDel = 1;
                            return false;
                        }
                    });

                    if (keyDel == 0) {
                        if (((input_description.getText().length() + 1) % 4) == 0) {
                            if (input_description.getText().toString()
                                    .split("-").length <= 2) {
                                input_description.setText(input_description
                                        .getText() + "-");
                                input_description
                                        .setSelection(input_description
                                                .getText().length());
                            }
                        }
                        a = input_description.getText().toString();
                    } else {
                        a = input_description.getText().toString();
                        keyDel = 0;
                    }

                } else {
                    input_description.setText(a);
                }

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void afterTextChanged(Editable s) {

            }

        });
*/

    }
}
