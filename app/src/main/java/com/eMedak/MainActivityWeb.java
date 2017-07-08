package com.eMedak;

import android.Manifest;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eMedak.adapters.mandalweb;
import com.eMedak.adapters.officers;
import com.eMedak.adapters.villageweb;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivityWeb extends AppCompatActivity implements View.OnClickListener {
    private Bitmap bitmap;
    public static final String UPLOAD_URL = "http://209.105.242.128/MDKG/wsGrievanceServices.aspx";
    TextView choosefile, file1choose, myimage2, choosefilethree;
    String selectmyimages, selectmyimage1 = "image", selectmyimage2 = "image2", selectmyimage3 = "image3";
    ImageView iv_back;
    Button btn_submit;
    EditText et_name, et_mobile, et_aadhar, et_address, et_email, et_description;
    String mymandal, myvillage, mycomplaintto;
    Bitmap scaledBitmap = null, scaledBitmap2 = null, scaledBitmap3 = null;
    public ImageView myimage, myimagesettwo, myimagethree;
    Spinner mandal, village, complaintto, officerslevel;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Dialog dialog;
    TextView btn_clearall;
    String userChoosenTask;
    ArrayAdapter<String> adc1, adc2, officersadapter, officersselectadpt;
    ArrayList<com.eMedak.adapters.mandalweb> mandalweb;
    ArrayList<com.eMedak.adapters.villageweb> villageweb;
    ArrayList<com.eMedak.adapters.officers> officersweb;
    ArrayList<String> mandals, villages, officers, officersselect;
    String mymandalid, myvillageid, myofficers_cmp, selectmandal, selectvillage;
    int id_comp;
    RelativeLayout myrr_grv;
    private AnimatorSet mSetLeftIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_web);
        myrr_grv = (RelativeLayout) findViewById(R.id.myrr_grv);

       /* Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);
        // Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        myrr_grv.startAnimation(slideUp);*/

        selectmyimages = "novalue";
        btn_clearall = (TextView) findViewById(R.id.btn_clearall);
        btn_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new MainActivity.fssservice().execute();
                Intent i = new Intent(MainActivityWeb.this, MainActivityWeb.class);
                startActivity(i);
                finish();
            }
        });

        choosefile = (TextView) findViewById(R.id.choosefile);
        clearPreferences();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        // file1choose = (TextView)findViewById(R.id.file1choose);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        myimage = (ImageView) findViewById(R.id.myimage);
        myimagesettwo = (ImageView) findViewById(R.id.myimagesettwo);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_aadhar = (EditText) findViewById(R.id.et_aadhar);
        et_address = (EditText) findViewById(R.id.et_address);
        et_email = (EditText) findViewById(R.id.et_email);
        et_description = (EditText) findViewById(R.id.et_description);
        choosefilethree = (TextView) findViewById(R.id.choosefilethree);
        myimage2 = (TextView) findViewById(R.id.myimage2);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        myimagethree = (ImageView) findViewById(R.id.myimagethree);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivityWeb.this, SelectList.class);
                startActivity(i);
                finish();
            }
        });

        mandal = (Spinner) findViewById(R.id.mandal);
        village = (Spinner) findViewById(R.id.village);
        complaintto = (Spinner) findViewById(R.id.complaintto);
        officerslevel = (Spinner) findViewById(R.id.officerslevel);

        mandalweb = new ArrayList<mandalweb>();
        villageweb = new ArrayList<villageweb>();
        officersweb = new ArrayList<officers>();
        mandals = new ArrayList<String>();
        villages = new ArrayList<String>();
        officers = new ArrayList<String>();
        officersselect = new ArrayList<String>();
        //  mandal.setAdapter(adc1);
        officersselect.add("--Select--");
        officersselect.add("District Level Officers");
        officersselect.add("Mandal Level Officers");

        officersselectadpt = new ArrayAdapter<String>(MainActivityWeb.this, android.R.layout.simple_spinner_item, officersselect);
        officersselectadpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        officerslevel.setAdapter(officersselectadpt);


        getmandals();
        choosefile.setOnClickListener(MainActivityWeb.this);
        myimage2.setOnClickListener(MainActivityWeb.this);
        choosefilethree.setOnClickListener(MainActivityWeb.this);
        btn_submit.setOnClickListener(MainActivityWeb.this);

        mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String position = String.valueOf(i);
                // Toast.makeText(getBaseContext(), position, Toast.LENGTH_LONG).show();
                selectmandal = adapterView.getItemAtPosition(i).toString();
                mymandalid = String.valueOf(mandalweb.get(i).getMandalid());
                mymandal = String.valueOf(mandalweb.get(i).getMandalname());
                //  mandalname = mandalweb.get(i).getMandalname().toString();
                villageweb.clear();

                //   complaintto.setAdapter(officersselectadpt);
                if (i == 0) {

                } else {
                    new MainActivityWeb.getVillage(mandalweb.get(i).getMandalid()).execute();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //  Toast.makeText(getBaseContext(),adapterView.toString(),Toast.LENGTH_SHORT).show();
                mymandalid = "";
            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectvillage = adapterView.getItemAtPosition(i).toString();
                myvillageid = String.valueOf(villageweb.get(i).getVillageid());
                myvillage = String.valueOf(villageweb.get(i).getVillagename());
                Toast.makeText(getBaseContext(), myvillageid, Toast.LENGTH_LONG).show();
                // myvillage = villageweb.get(i).getVillagename().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myvillageid = "";
            }
        });

        complaintto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mycomplaintto = parent.getItemAtPosition(position).toString();
                //  Toast.makeText(getBaseContext(), mycomplaintto, Toast.LENGTH_LONG).show();
                id_comp = position;

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        officerslevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myofficers_cmp = parent.getItemAtPosition(position).toString();
                //  Toast.makeText(getBaseContext(), mycomplaintto, Toast.LENGTH_LONG).show();

                if (myofficers_cmp.equals("District Level Officers")) {

                    new MainActivityWeb.getOfficers("1", mymandalid).execute();
                } else if (myofficers_cmp.equals("Mandal Level Officers")) {
                    new MainActivityWeb.getOfficers("2", mymandalid).execute();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                    adc1 = new ArrayAdapter<String>(MainActivityWeb.this, android.R.layout.simple_spinner_item, mandals);
                    adc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mandal.setAdapter(adc1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                HashMap<String, String> data = new HashMap<>();
                String result = rh.sendPostRequest("http://209.105.242.128/MDKG/wsgetmandaldata.ASPX", data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choosefile:
                selectmyimages = "selectmyimage1";
                selectImage();
                break;
            case R.id.myimage2:
                selectmyimages = "selectmyimage2";
                selectImage();
                break;
            case R.id.choosefilethree:
                selectmyimages = "selectmyimage3";
                selectImage();
                break;
            case R.id.btn_submit:

                if (selectmandal.equals("--Select--")) {
                    validations.MyAlertBox(MainActivityWeb.this, "select Mandal ");
                } else if (selectvillage.equals("--Select--")) {
                    validations.MyAlertBox(MainActivityWeb.this, "select village ");
                } else if (et_name.getText().toString().length() == 0) {
                    validations.MyAlertBox(MainActivityWeb.this, "Enter name");
                } else if (et_mobile.getText().length() == 0 || et_mobile.getText().length() < 10) {
                    validations.MyAlertBox(MainActivityWeb.this, "Enter mobile no should be 10 digits");

                } else if (et_aadhar.getText().length() == 0 || et_aadhar.getText().length() < 12) {
                    validations.MyAlertBox(MainActivityWeb.this, "Enter aadhar no should be 12 digits");
                } else if (et_address.getText().length() == 0) {
                    validations.MyAlertBox(MainActivityWeb.this, "Enter address");

                } else if (myofficers_cmp.equals("--Select--")) {
                    validations.MyAlertBox(MainActivityWeb.this, "Please select Officer Level");
                } else if (mycomplaintto.equals("--Select--")) {
                    validations.MyAlertBox(MainActivityWeb.this, "Please select grievence officer");
                } else if (et_description.getText().length() == 0) {
                    validations.MyAlertBox(MainActivityWeb.this, "Enter description");

                } else if (myimage.getDrawable() == null) {
                    validations.MyAlertBox(MainActivityWeb.this, "Please upload photo");
                } else {
                    if (et_email.getText().toString().length() == 0) {
                        dialog = new Dialog(MainActivityWeb.this, android.R.style.Theme_Translucent);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.loading);


                        uploadImage(mymandalid, myvillageid, et_name.getText().toString()
                                , et_mobile.getText().toString(), et_aadhar.getText().toString(), et_address.getText().toString()
                                , et_email.getText().toString(), officersweb.get(id_comp).getOfficerid(), et_description.getText().toString());


                    } else {
                        if (validations.email(et_email.getText().toString())) {


                            uploadImage(mymandalid, myvillageid, et_name.getText().toString()
                                    , et_mobile.getText().toString(), et_aadhar.getText().toString(), et_address.getText().toString()
                                    , et_email.getText().toString(), officersweb.get(id_comp).getOfficerid(), et_description.getText().toString());
                        } else {
                            validations.MyAlertBox(MainActivityWeb.this, "Enter Valid Email");
                        }

                    }
                }
                //  uploadImage(mymandalid, myvillageid, et_name.getText().toString()
                //         , et_mobile.getText().toString(), et_aadhar.getText().toString(), et_address.getText().toString()
                //         , et_email.getText().toString(), officersweb.get(id_comp).getOfficerid(), et_description.getText().toString());
              /* String xx = officersweb.get(id_comp).getOfficername();
               Toast.makeText(getBaseContext(),"officername : "+xx.toString()+"  villageid  "+myvillageid.toString()+"  mandal  "+
               mymandalid+"  vname  "+myvillage+" mname  "+mymandal
                       +"  id_comp  "+officersweb.get(id_comp).getOfficername()+"  ofid "+officersweb.get(id_comp).getOfficerid(),Toast.LENGTH_LONG).show();
*/
                break;
        }
    }




    private class getOfficers extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String Addressto, Mandalid;

        public getOfficers(String Addressto, String Mandalid) {
            this.Mandalid = Mandalid;
            this.Addressto = Addressto;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("Addressto", Addressto));
            nameValuePairs.add(new BasicNameValuePair("Mandalid", Mandalid));
            json = JSONParser.makeServiceCall("http://209.105.242.128/MDKG/wsgetOfficersdata.aspx", 1, nameValuePairs);
            // json = JSONParser.makeServiceCall("http://209.105.242.128/mdkesms/wsgetofficersdata.aspx", 1, nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            // Toast.makeText(getBaseContext(), "service called", Toast.LENGTH_LONG).show();
            officersweb.clear();
            officers.clear();
            officersweb.add(new officers("--Select--", "--Select--"));
            officers.add("--Select--");
            try {
                JSONObject obj = new JSONObject(json.toString());
                JSONArray jsonMainArr = obj.getJSONArray("users");
                for (int i = 0; i < jsonMainArr.length(); i++) {
                    JSONObject ss = jsonMainArr.getJSONObject(i);
                    officersweb.add(new officers(ss.getString("intDeptid"), ss.getString("DeptSubOfficerName")));
                    //Toast.makeText(getApplicationContext(), ss.toString(), Toast.LENGTH_LONG).show();
                    officers.add(ss.getString("DeptSubOfficerName"));
                }
                officersadapter = new ArrayAdapter<String>(MainActivityWeb.this, android.R.layout.simple_spinner_item, officers);
                officersadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                complaintto.setAdapter(officersadapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private class getVillage extends AsyncTask<String, String, JSONObject> {

        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String id;

        public getVillage(int id) {
            this.id = String.valueOf(id);
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("Mandalid", id));
            json = JSONParser.makeServiceCall("http://209.105.242.128/MDKG/wsgetvillagedata.aspx", 1, nameValuePairs);

            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            JSONObject c;
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
                adc2 = new ArrayAdapter<String>(MainActivityWeb.this, android.R.layout.simple_spinner_item, villages);
                adc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                village.setAdapter(adc2);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d("myimage", encodedImage.toString());
        return encodedImage;
    }
    private void uploadImage(final String mymandal, final String myvillage, final String et_name, final String et_mobile, final String et_aadhar,
                             final String et_address, final String et_email, final String mycomplaintto, final String et_description){

        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivityWeb.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);
                loading.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(json.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject result = jsonArray.getJSONObject(i);

                        if (result.getString("CreateEvent").toString().equals("success")) {
                            Toast.makeText(getBaseContext(), result.getString("CreateEvent").toString(), Toast.LENGTH_LONG).show();
                            List<String> toEmailList = Arrays.asList(et_email.toString().split("\\s*,\\s*"));
                            new SendMailTask(MainActivityWeb.this).execute("medakeservices@gmail.com",
                                    "medakeservices123", toEmailList,
                                    "e-Medak Grievance Monitoring System ", "<!DOCTYPE html>\n" +
                                            "<html>\n" +
                                            "<body style=\"color:#000000\">\n" +
                                            " \n" +
                                            "<p ><b>Dear  Applicant  ,</b></p>\n" +
                                            " \n" +
                                            "<p>Your Grievance was successfully registered vide Registration No : </p>\n" + result.getString("createevent_id").toString() +
                                            "<p> It will be forwarded to concerned Officer for redressal. You can know the status of your grievance by clicking on Know Your Grievance Status option. </p>\n" +
                                            "<p> Regards </p><p>Grievance Cell</p><p>Medak District.</p>\n" +
                                            " \n" +
                                            "</body>\n" +
                                            "</html>\n"
                            );
                            Intent dd = new Intent(MainActivityWeb.this, GenerateNumberApplication.class);
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

            @Override
            protected String doInBackground(Bitmap... params) {
                // Bitmap bitmap1 = params[0];
                String uploadImage1,uploadImage2,uploadImage3;

                if (scaledBitmap ==null){
                    uploadImage1 = "nullimage";
                }else {
                    uploadImage1 = getStringImage(scaledBitmap);
                }
                if (scaledBitmap2==null){
                    uploadImage2 = "nullimage";
                }else {
                    uploadImage2 = getStringImage(scaledBitmap2);
                }
                if (scaledBitmap3==null){
                    uploadImage3 = "nullimage";
                }else {
                    uploadImage3 = getStringImage(scaledBitmap3);
                }

                String filename = selectmyimages;
                HashMap<String,String> data = new HashMap<>();

                // data.put(UPLOAD_KEY, uploadImage);
                data.put("ForwarTo", mycomplaintto);
                data.put("ApplicationName",et_name);
                data.put("intmandalId", mymandal);
                data.put("intVillageid", myvillage);
                data.put("Address",et_address.toString());
                data.put("mobileNo",et_mobile.toString());
                data.put("Email",et_email.toString());
                data.put("Aadhar_No",et_aadhar.toString());
                data.put("GrievanceDescription",et_description.toString());

                data.put("Created_by","12");
                data.put("GrievancePhotoFile1","sdfasdf");
                data.put("GrievancePhotoPath1",uploadImage1);
                data.put("GrievancePhotoFile2","asdfas");
                data.put("GrievancePhotoPath2",uploadImage2);
                data.put("GrievancePhotoFile3","asdfsdf");
                data.put("GrievancePhotoPath3",uploadImage3);

                System.out.print(data.toString());
                String result = rh.sendPostRequest(UPLOAD_URL,data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }




    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityWeb.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (options[item].equals("Choose from Gallery")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void cameraIntent() {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        Uri tempUri = getImageUri(MainActivityWeb.this, thumbnail);

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        File finalFile = new File(getRealPathFromURI(tempUri));
        bitmap = (Bitmap) data.getExtras().get("data");
         // compressImage(finalFile.getAbsolutePath().toString());
        // ivImage.setImageBitmap(scaledBitmap);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (selectmyimages) {
            case "selectmyimage1":
                // uploadImage();
                // new MainActivity.JSONParsedoitfast(scaledBitmap,"one").execute();
                myimage.setMaxWidth(150);
                myimage.setMaxHeight(150);
                myimage.setImageBitmap(bitmap);
                scaledBitmap = bitmap;
                break;
            case "selectmyimage2":
                //  uploadImage();
                //   new MainActivity.JSONParsedoitfast(scaledBitmap2,"two").execute();
                myimagesettwo.setMaxWidth(150);
                myimagesettwo.setMaxHeight(150);
                myimagesettwo.setImageBitmap(bitmap);
                scaledBitmap2 = bitmap;
                break;
            case "selectmyimage3":
                //   uploadImage();
                //  new MainActivity.JSONParsedoitfast(scaledBitmap3,"three").execute();
                myimagethree.setMaxWidth(150);
                myimagethree.setMaxHeight(150);
                myimagethree.setImageBitmap(bitmap);
                scaledBitmap3 = bitmap;
                break;
        }

    }


    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

                Uri tempUri = getImageUri(getApplicationContext(), bitmap);

                File finalFile = new File(getRealPathFromURI(tempUri));
                //  compressImage(finalFile.getAbsolutePath().toString());

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");

                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                switch (selectmyimages) {
                    case "selectmyimage1":
                        //    uploadImage();
                        myimage.setMaxWidth(150);
                        myimage.setMaxHeight(150);
                        // new MainActivity.JSONParsedoitfast(scaledBitmap,"one").execute();
                        scaledBitmap = bitmap;
                        myimage.setImageBitmap(bitmap);
                        break;
                    case "selectmyimage2":
                        //    uploadImage();
                        myimagesettwo.setMaxWidth(150);
                        myimagesettwo.setMaxHeight(150);
                        //   new MainActivity.JSONParsedoitfast(scaledBitmap2,"two").execute();
                        scaledBitmap2 = bitmap;
                        myimagesettwo.setImageBitmap(bitmap);
                        break;
                    case "selectmyimage3":
                        //    uploadImage();
                        myimagethree.setMaxWidth(150);
                        myimagethree.setMaxHeight(150);
                        //  new MainActivity.JSONParsedoitfast(scaledBitmap3,"three").execute();
                        scaledBitmap3 = bitmap;
                        myimagethree.setImageBitmap(bitmap);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void clearPreferences() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear YOUR_APP_PACKAGE_GOES HERE");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
