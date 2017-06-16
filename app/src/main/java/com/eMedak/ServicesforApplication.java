package com.eMedak;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ServicesforApplication extends AppCompatActivity {
    ArrayAdapter<String> adc1, adc2;
    ArrayList<mandalweb> mandalweb;
    ArrayList<villageweb> villageweb;
    ArrayList<String> mandals, villages;
    Bitmap bitmap;
    List<String> categories, c1, off, cn, cntwo;
    Spinner mandal, village, complaintto;
    ArrayAdapter<String> dataAdapter2;
    ArrayAdapter<String> dataAdapter3, dataAdapter4;
    String mandalname;

    ImageView iv_back;
    Button btn_submit;
    EditText et_name, et_mobile, et_aadhar, et_address, et_email, et_description, et_surveryno, et_input_extent;
    private static int RESULT_LOAD_IMG = 1;
    String mymandal, myvillage, mymandalid, myvillageid;

    TextView btn_clearall;
    CheckBox original, Khasra, Protected, Ceiling, Pahanies, Past, Latest, Register, Soil;
    String orig, Khas, Prot, Ceil, Paha, Pas, Late, Regir, Soi, checkme, selectmandal, selectvillage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationform);
        checkme = "";
        btn_clearall = (TextView) findViewById(R.id.btn_clearall);
        btn_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new MainActivity.fssservice().execute();
                Intent i = new Intent(ServicesforApplication.this, ServicesforApplication.class);
                startActivity(i);
                finish();
            }
        });

        clearPreferences();
        // file1choose = (TextView)findViewById(R.id.file1choose);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        et_name = (EditText) findViewById(R.id.et_name);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_aadhar = (EditText) findViewById(R.id.et_aadhar);
        et_email = (EditText) findViewById(R.id.et_email);
        et_surveryno = (EditText) findViewById(R.id.et_surveryno);
        et_input_extent = (EditText) findViewById(R.id.et_input_extent);

        original = (CheckBox) findViewById(R.id.original);
        Khasra = (CheckBox) findViewById(R.id.Khasra);
        Protected = (CheckBox) findViewById(R.id.Protected);
        Ceiling = (CheckBox) findViewById(R.id.Ceiling);
        Pahanies = (CheckBox) findViewById(R.id.Pahanies);
        Past = (CheckBox) findViewById(R.id.Past);
        Latest = (CheckBox) findViewById(R.id.Latest);
        Register = (CheckBox) findViewById(R.id.Register);
        Soil = (CheckBox) findViewById(R.id.Soil);

        c1 = new ArrayList<String>();

        original.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    orig = "1000";
                    checkme = "checkme";
                } else {
                    checkme = "";
                    orig = "1";
                }
            }
        });
        Khasra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Khas = "1001";
                } else {
                    checkme = "";
                    Khas = "1";
                }
            }
        });
        Protected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Prot = "1002";
                } else {
                    checkme = "";
                    Prot = "1";
                }
            }
        });

        Ceiling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Ceil = "1003";
                } else {
                    checkme = "";
                    Ceil = "1";
                }
            }
        });
        Pahanies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Paha = "1004";
                } else {
                    checkme = "";
                    Paha = "1";
                }
            }
        });
        Past.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Pas = "1005";
                } else {
                    checkme = "";
                    Pas = "1";
                }
            }
        });
        Latest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Late = "1006";
                } else {
                    checkme = "";
                    Late = "1";
                }
            }
        });
        Register.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Regir = "1007";
                } else {
                    checkme = "";
                    Regir = "1";
                }
            }
        });
        Soil.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkme = "checkme";
                    Soi = "1008";
                } else {
                    checkme = "";
                    Soi = "1";
                }
            }
        });

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ServicesforApplication.this, SelectList.class);
                startActivity(i);
                finish();
            }
        });


        mandal = (Spinner) findViewById(R.id.mandal);
        village = (Spinner) findViewById(R.id.village);

        mandalweb = new ArrayList<mandalweb>();
        villageweb = new ArrayList<villageweb>();
        mandals = new ArrayList<String>();
        villages = new ArrayList<String>();
        //  mandal.setAdapter(adc1);
        getmandals();


        mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectmandal = adapterView.getItemAtPosition(i).toString();
                mymandalid = String.valueOf(mandalweb.get(i).getMandalid());
                mandalname = mandalweb.get(i).getMandalname().toString();
                villageweb.clear();

                new ServicesforApplication.JSONParse(mandalweb.get(i).getMandalid()).execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //  Toast.makeText(getBaseContext(),adapterView.toString(),Toast.LENGTH_SHORT).show();
                mymandal = "";
            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                myvillageid = String.valueOf(villageweb.get(i).getVillageid());
                myvillage = villageweb.get(i).getVillagename().toString();

                selectvillage = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myvillageid = "";
            }
        });


        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, off);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cn);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cntwo);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().length() == 0) {
                    showalert("Please Enter the Applicant Name");
                } else if (et_aadhar.getText().toString().length() < 12) {
                    showalert("Please Enter Valid Aadhar Number ");
                } else if (et_mobile.getText().toString().length() == 0) {
                    showalert("Please Enter the Phone Number");
                } else if (selectmandal.equals("--Select--")) {
                    showalert("Please select the Mandal ");
                } else if (selectvillage.equals("--Select--")) {
                    showalert("Please select the Village ");
                } else if (et_surveryno.getText().toString().length() == 0) {
                    showalert("Please Enter Survey Number");
                } else if (checkme.length() == 0) {
                    showalert("Please check the options");
                } else {
                    if (et_email.getText().toString().length() > 0) {
                        if (!validations.email(et_email.getText().toString())) {
                            showalert("Please Enter  Valid Email Address");
                        } else {
                            new ServicesforApplication.SubmitServicesforApp(et_name.getText().toString(), et_mobile.getText().toString(),
                                    et_aadhar.getText().toString(), et_email.getText().toString(),
                                    et_surveryno.getText().toString(), et_input_extent.getText().toString()).execute();
                        }
                    } else {
                        new ServicesforApplication.SubmitServicesforApp(et_name.getText().toString(), et_mobile.getText().toString(),
                                et_aadhar.getText().toString(), et_email.getText().toString(),
                                et_surveryno.getText().toString(), et_input_extent.getText().toString()).execute();
                    }
                }
            }
        });
    }

    public void showalert(String title) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ServicesforApplication.this);
        builder1.setMessage(title);
        builder1.setCancelable(true);
        builder1.setIcon(R.drawable.biglogo);
        builder1.setPositiveButton(
                "Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void getmandals() {

        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // loading = ProgressDialog.show(WebserviceMain.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                mandalweb.clear();
                mandals.clear();
                mandalweb.add(new mandalweb(3, "--Select--"));
                // Toast.makeText(getApplicationContext(), ss.toString(), Toast.LENGTH_LONG).show();
                mandals.add("--Select--");

                try {
                    JSONObject obj = new JSONObject(s.toString());
                    JSONArray jsonMainArr = obj.getJSONArray("Mandals");
                    for (int i = 0; i < jsonMainArr.length(); i++) {
                        JSONObject ss = jsonMainArr.getJSONObject(i);
                        mandalweb.add(new mandalweb(ss.getInt("intRId"), ss.getString("MandalName")));
                        // Toast.makeText(getApplicationContext(), ss.toString(), Toast.LENGTH_LONG).show();
                        mandals.add(ss.getString("MandalName"));
                    }
                    adc1 = new ArrayAdapter<String>(ServicesforApplication.this, android.R.layout.simple_spinner_item, mandals);
                    adc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mandal.setAdapter(adc1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                HashMap<String, String> data = new HashMap<>();
                String result = rh.sendPostRequest("http://209.105.242.128/mdkesms/wsgetmandaldata.ASPX", data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }


    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String id;

        public JSONParse(int id) {
            this.id = String.valueOf(id);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("Mandalid", id));
            json = JSONParser.makeServiceCall("http://209.105.242.128/mdkesms/wsgetvillagedata.aspx", 1, nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            villageweb.clear();
            villages.clear();
            villageweb.add(new villageweb("--Select--", "--Select--"));
            villages.add("--Select--");
            try {

                JSONObject obj = new JSONObject(json.toString());
                JSONArray jsonMainArr = obj.getJSONArray("Villages");
                for (int i = 0; i < jsonMainArr.length(); i++) {
                    JSONObject ss = jsonMainArr.getJSONObject(i);
                    villageweb.add(new villageweb(ss.getString("intVillageId"), ss.getString("VillageName")));
                    //Toast.makeText(getApplicationContext(), ss.toString(), Toast.LENGTH_LONG).show();
                    villages.add(ss.getString("VillageName"));
                }
                adc2 = new ArrayAdapter<String>(ServicesforApplication.this, android.R.layout.simple_spinner_item, villages);
                adc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                village.setAdapter(adc2);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    private class SubmitServicesforApp extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String id;
        String applicantname, mobile, aadhar, email, surveyno, extent;

        public SubmitServicesforApp(String applicantname, String mobile, String aadhar, String email, String surveyno, String extent) {
            this.applicantname = applicantname;
            this.mobile = mobile;
            this.aadhar = aadhar;
            this.email = email;
            this.surveyno = surveyno;
            this.extent = extent;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {

            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("ApplicantName", applicantname));
            nameValuePairs.add(new BasicNameValuePair("AadharNo", aadhar));
            nameValuePairs.add(new BasicNameValuePair("MobileNo", mobile));
            nameValuePairs.add(new BasicNameValuePair("Email", email));
            nameValuePairs.add(new BasicNameValuePair("IntMandalid", mymandalid));
            nameValuePairs.add(new BasicNameValuePair("intVillageid", myvillageid));
            nameValuePairs.add(new BasicNameValuePair("SurveyNumber", surveyno));
            nameValuePairs.add(new BasicNameValuePair("Extent", extent));
            nameValuePairs.add(new BasicNameValuePair("Created_by", "10000"));
            nameValuePairs.add(new BasicNameValuePair("Service1", orig));
            nameValuePairs.add(new BasicNameValuePair("Service2", Khas));
            nameValuePairs.add(new BasicNameValuePair("Service3", Prot));
            nameValuePairs.add(new BasicNameValuePair("Service4", Ceil));
            nameValuePairs.add(new BasicNameValuePair("Service5", Paha));
            nameValuePairs.add(new BasicNameValuePair("Service6", Pas));
            nameValuePairs.add(new BasicNameValuePair("Service7", Late));
            nameValuePairs.add(new BasicNameValuePair("Service8", Regir));
            nameValuePairs.add(new BasicNameValuePair("Service9", Soi));

            json = JSONParser.makeServiceCall("http://209.105.242.128/mdkesms/wsRequestServices.aspx", 1, nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                JSONObject jsonObject = new JSONObject(json.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject result = jsonArray.getJSONObject(i);

                    if (result.getString("CreateEvent").toString().equals("success")) {
                        Toast.makeText(getBaseContext(), result.getString("CreateEvent").toString(), Toast.LENGTH_LONG).show();
                        List<String> toEmailList = Arrays.asList(email.toString().split("\\s*,\\s*"));
                        new SendMailTask(ServicesforApplication.this).execute("medakeservices@gmail.com",
                                "medakeservices123", toEmailList,
                                "e-Sangareddy Grievance Monitoring System ", "<!DOCTYPE html>\n" +
                                        "<html>\n" +
                                        "<body style=\"color:#000000\">\n" +
                                        " \n" +
                                        "<p ><b>Dear  Applicant  ,</b></p>\n" +
                                        " \n" +
                                        "<p>Your e-Service Request was successfully registered vide Registration No  : </p>" + result.getString("createevent_id").toString() +
                                        "<p> t will be forwarded to concerned Officer for process. You can know the status of your e-Service Request by clicking on Know Your  e-Service Request Status option. </p>\n" +
                                        "<p> Regards </p><p>\ne-Service Request Cell\n</p><p>Medak District.</p>\n" +
                                        " \n" +
                                        "</body>\n" +
                                        "</html>\n"
                        );
                        Intent dd = new Intent(ServicesforApplication.this, GenerateNumberApplication.class);
                        dd.putExtra("number", result.getString("createevent_id").toString());
                        startActivity(dd);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(), "Please Try Again", Toast.LENGTH_LONG).show();
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private void clearPreferences() {
        try {
            //hello
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear YOUR_APP_PACKAGE_GOES HERE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
