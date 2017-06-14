package com.eMedak;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SelectList extends AppCompatActivity implements View.OnClickListener{
    Button btn_submit, btn_submit2, Applicationstatus, Applicationkwnstatus;
    private AnimatorSet mSetLeftIn;
  RelativeLayout select_rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_list);
        select_rl = (RelativeLayout)findViewById(R.id.select_rl);

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit2 = (Button) findViewById(R.id.knowstatus);
        Applicationstatus = (Button) findViewById(R.id.Applicationstatus);
        Applicationkwnstatus = (Button) findViewById(R.id.Applicationkwnstatus);

        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        // Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        btn_submit.startAnimation(slideUp);

        Animation slideUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        // Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        btn_submit2.startAnimation(slideUp2);

        Animation slideUp3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        // Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Applicationstatus.startAnimation(slideUp3);

        Animation slideUp4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        // Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        Applicationkwnstatus.startAnimation(slideUp4);

        btn_submit.setOnClickListener(SelectList.this);
        btn_submit2.setOnClickListener(SelectList.this);
        Applicationstatus.setOnClickListener(SelectList.this);
        Applicationkwnstatus.setOnClickListener(SelectList.this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                Intent i = new Intent(SelectList.this, MainActivityWeb.class);
                startActivity(i);
                break;
            case R.id.knowstatus:
                Intent dd = new Intent(SelectList.this, Verification.class);
                startActivity(dd);
                break;
            case R.id.Applicationstatus:
                Intent aa= new Intent(SelectList.this, ServicesforApplication.class);
                startActivity(aa);
                break;

            case R.id.Applicationkwnstatus:
                Intent zz= new Intent(SelectList.this, VerificationApplication.class);
                startActivity(zz);
                break;
        }
    }
}
