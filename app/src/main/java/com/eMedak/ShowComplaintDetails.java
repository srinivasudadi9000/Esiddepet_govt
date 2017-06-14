package com.eMedak;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowComplaintDetails extends AppCompatActivity {
    ImageView iv_back,tv_image,myimagetwo,myimagethree;
    TextView tv_mandal_id,tv_village_id,tv_mobileno,tv_aadhar,tv_email,tv_complaitnto_answer,
            tv_complaintdes_answer,tv_status_answer,tv_name,tv_address;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_complaint_details);
        iv_back = (ImageView)findViewById(R.id.iv_back);
        tv_image = (ImageView)findViewById(R.id.tv_image);
        myimagetwo = (ImageView)findViewById(R.id.myimage2);
        myimagethree = (ImageView)findViewById(R.id.myimage);

        tv_mandal_id = (TextView)findViewById(R.id.tv_mandal_id);
        tv_village_id = (TextView)findViewById(R.id.tv_village_id);
        tv_mobileno = (TextView)findViewById(R.id.tv_mobileno);
        tv_aadhar = (TextView)findViewById(R.id.tv_aadhar);
        tv_email = (TextView)findViewById(R.id.tv_email);
        tv_complaitnto_answer = (TextView)findViewById(R.id.tv_complaitnto_answer);
        tv_complaintdes_answer = (TextView)findViewById(R.id.tv_complaintdes_answer);
        tv_status_answer = (TextView)findViewById(R.id.tv_status_answer);
        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_address = (TextView)findViewById(R.id.tv_address);

        dialog = new Dialog(ShowComplaintDetails.this,android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loading);
        dialog.show();
       // new ShowComplaintDetails.sendsms().execute();
        String x = getIntent().getStringExtra("id").toString();
        //  Toast.makeText(getBaseContext(),x.toString(),Toast.LENGTH_SHORT).show();
         new ShowComplaintDetails.JSONParse(getIntent().getStringExtra("id").toString()).execute();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ShowComplaintDetails.this,Verification.class);
                startActivity(it);
                finish();
            }
        });

    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String id;

        public   JSONParse(String id) {
            this.id= id;
        }



        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("intGrievanceid",id));


            json = JSONParser.makeServiceCall("http://209.105.242.128/MDKG/wsgetgrievancedata.aspx", 1,nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            JSONObject c;
            try {
                JSONObject obj = new JSONObject(json.toString());
                JSONArray peoples = obj.getJSONArray("users");
                for (int l = 0; l < peoples.length(); l++) {
                    JSONObject cc = peoples.getJSONObject(l);

                    tv_mandal_id.setText(cc.getString("mandal_name"));
                    tv_village_id.setText(cc.getString("village_name"));
                    tv_aadhar.setText(cc.getString("Aadhar_No"));
                    tv_email.setText(cc.getString("Email"));
                    tv_name.setText(cc.getString("ApplicationName"));
                    tv_mobileno.setText(cc.getString("mobileNo"));
                    tv_complaitnto_answer.setText(cc.getString("officer_name"));
                    tv_complaintdes_answer.setText(cc.getString("GrievanceDescription"));
                  //
                    tv_address.setText(cc.getString("Address"));
                   // String asd = "http://192.254.233.173/~rajeshch/PHP/crmcloud/upload/"+cc.getString("GrievancePhotoPath1")+".jpeg";
                    String asd = "http://"+cc.getString("GrievancePhotoPath1");
                    tv_image.setMaxWidth(150);
                    tv_image.setMaxHeight(150);
                    Picasso.with(ShowComplaintDetails.this).load(asd).into(tv_image);
                   // String ddd = "http://192.254.233.173/~rajeshch/PHP/crmcloud/upload/"+cc.getString("GrievancePhotoPath2")+".jpeg";
                    String ddd ="http://"+cc.getString("GrievancePhotoPath2");
                    myimagetwo.setMaxWidth(150);
                    myimagetwo.setMaxHeight(150);
                    Picasso.with(ShowComplaintDetails.this).load(ddd).into(myimagetwo);
                  //  String aaa = "http://192.254.233.173/~rajeshch/PHP/crmcloud/upload/"+cc.getString("GrievancePhotoPath3")+".jpeg";
                    String aaa ="http://"+cc.getString("GrievancePhotoPath3");
                    myimagethree.setMaxWidth(150);
                    myimagethree.setMaxHeight(150);
                    Picasso.with(ShowComplaintDetails.this).load(aaa).into(myimagethree);
                    tv_status_answer.setText(cc.getString("Status"));
                    dialog.dismiss();
                }

            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
            }


        }
    }
    private class sendsms extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String id;

        public  sendsms() {

        }

        //http://login.redsms.in/API/SendMessage.ashx?user=VizagJC&password=jc123&phone=8885270193&text=testmessage&type=p

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("user","VizagJC"));
            nameValuePairs.add(new BasicNameValuePair("password","jc123"));
            nameValuePairs.add(new BasicNameValuePair("phone","8885270193"));
            nameValuePairs.add(new BasicNameValuePair("text","VizagJC"));
            nameValuePairs.add(new BasicNameValuePair("type","1231"));


            json = JSONParser.makeServiceCall("http://login.redsms.in/API/SendMessage.ashx", 1,nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {


        }
    }

}
