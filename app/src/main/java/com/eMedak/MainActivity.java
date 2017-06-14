package com.eMedak;

import android.Manifest;
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
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

public class MainActivity extends AppCompatActivity {
    String ForwarTo;
  //  public static final String UPLOAD_URL = "http://192.254.233.173/~rajeshch/PHP/crmcloud/uploadimg.php";
 // e-sangareddy first one inserturl service
   public static final String UPLOAD_URL = "http://209.105.239.132/SangareddyG/wsGrievanceServices.aspx";
  // public static final String UPLOAD_URL = "http://45.35.13.110/nzmg/wsGrievanceServices.aspx";
    //public static final String UPLOAD_KEY = "image";
    public static final String UPLOAD_KEY = "GrievancePhotoPath1";
    String myrdo;
    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;
    private Button buttonView;
    int myid;
    private ImageView imageView;

    private Bitmap bitmap,bitmap2,bitmap3;
    String selectofficer;
    private Uri filePath;
    List<String> categories,c1,off,cn,cntwo,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c34,c35,c27,comp;
    ArrayList<collectornumbers> cc,cctwo,ccad145,ccad172,ccad173,ccad183,ccad184,ccad185,
            ccad186,ccad187,ccad188,ccad189,ccad588,ccad146,ccad589,ccad590,ccad591,ccad592,
            ccad593,ccad594,ccad595,ccad147,ccad148,ccad166,ccad167,ccad168,ccad169,ccad170;
    ArrayList<mandal> fc1,fc2,fc3,fc4,fc5,fc6,fc7,fc8,fc9,fc10,fc11,fc12,fc13,fc14,fc15,fc16,fc17,fc35,fc18,fc19,fc20,fc21,fc22,fc23,fc24,fc25;
    Spinner mandal,village,complaintto;
    ArrayAdapter<String> dataAdapter2,ad145,ad172,ad173,ad183,ad184,ad185,ad186,ad187,ad188,ad189,ad588,ad146,ad589,ad590,ad591,ad592,
            ad593,ad594,ad595,ad147,ad148,ad166,ad167,ad168,ad169,ad170;
    List<String> cad145,cad172,cad173,cad183,cad184,cad185,cad186,cad187,cad188,cad189,cad588,cad146,cad589,cad590,cad591,cad592,
            cad593,cad594,cad595,cad147,cad148,cad166,cad167,cad168,cad169,cad170;
    ArrayAdapter<String> dataAdapter3,dataAdapter4;
    ArrayAdapter<String> adc1,adc2,adc3,adc4,adc5,adc6,adc7,adc8,adc9,adc10,adc11,adc12,adc13,adc14,adc15,adc16,adc17,adc18,adc19
            ,adc20,adc21,adc22,adc23,adc24,adc34,adc35;
    int mandalcode , villagecode;
    String mandalname,villagename;
    TextView choosefile,file1choose,myimage2,choosefilethree;
    String selectmyimages,selectmyimage1="image",selectmyimage2="image2",selectmyimage3="image3";
    ImageView iv_back;
    Button btn_submit;
    EditText et_name,et_mobile,et_aadhar,et_address,et_email,et_description;
    private static int RESULT_LOAD_IMG = 1;
    String mymandal,myvillage,mycomplaintto;
    Bitmap scaledBitmap = null,scaledBitmap2 = null,scaledBitmap3 = null;
    public ImageView myimage,myimagesettwo,myimagethree;
    String imgDecodableString;
    Bitmap  thumbnail;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    public String myimageonefilename=null,myimagetwofilename=null,myimagethreefilename=null;
    Dialog dialog;
    TextView btn_clearall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageupload);
        selectmyimages="novalue";
        btn_clearall = (TextView)findViewById(R.id.btn_clearall);
        btn_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new MainActivity.fssservice().execute();
               Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        choosefile = (TextView)findViewById(R.id.choosefile);
        clearPreferences();
        // file1choose = (TextView)findViewById(R.id.file1choose);
        btn_submit = (Button)findViewById(R.id.btn_submit);
        myimage = (ImageView)findViewById(R.id.myimage);
        myimagesettwo = (ImageView)findViewById(R.id.myimagesettwo);
        et_name = (EditText)findViewById(R.id.et_name);
        et_mobile = (EditText)findViewById(R.id.et_mobile);
        et_aadhar = (EditText)findViewById(R.id.et_aadhar);
        et_address = (EditText)findViewById(R.id.et_address);
        et_email = (EditText)findViewById(R.id.et_email);
        et_description = (EditText)findViewById(R.id.et_description);
        choosefilethree = (TextView)findViewById(R.id.choosefilethree);
        myimage2 = (TextView)findViewById(R.id.myimage2);
        iv_back  =(ImageView)findViewById(R.id.iv_back);
        myimagethree =(ImageView)findViewById(R.id.myimagethree);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,SelectList.class);
                startActivity(i);
                finish();
            }
        });

        cn= new ArrayList<String>();
        cn.add( "--Select--");
        cn.add("Asst. SugarCane Commissioner, Sangareddy");
        cn.add( "AC Endowment");
        cn.add("AD FISHARIES");
        cn.add("AD Handlooms & Textiles");
        cn.add("AD HORTICULTURE");
        cn.add("AD MINES");
        cn.add( "AD TSGLI");
        cn.add( "BC DEVELOPMENT OFFICER");
        cn.add("Chief Planning Office");
        cn.add("CEO ZP");
     //   cn.add("CEO ZP");
        cn.add("Collector");
        cn.add("Commissioner-Sangareddy");
        cn.add("Commissioner-Sadashivpet");
        cn.add("Commissioner-Zaheerabad");
        cn.add("Commissioner-Andole");

        cn.add("DD AE");
        cn.add("DEO/SSA");
        cn.add("DFO");
        cn.add("Dist. Agriculture Officer");
        cn.add("District Audit Office");


       // cn.add("District Collector");
        cn.add("District Coordinate Office (new collectornumbers( Arogya Sree))");
        cn.add("District Cooperative Officer");
        cn.add("District Civil Supply Officer");
        cn.add("District Employment Exchange");
        cn.add("District Fisheries Officer");
        cn.add("Dist. Horticulure & Secriculure");
        cn.add("DIST IRRIGATION OFFICER");
        cn.add("District Marketing Officer");
        cn.add("DIST R&B OFFICER");
        cn.add("Dist. Prohibition & Excise Officer");
        cn.add("District Registration and Stamps");
        cn.add("District SC Development Office");
        cn.add("District Survey & Land Records Office");
        cn.add("District Intermediate Education Officer");
        cn.add("Dist. IDC Sangareddy");
        cn.add( "District Tribal welfare Development Officer");
        cn.add("Dist. Veternary & Animal Husbandry Officer");
        cn.add("DM,Housing");
        cn.add("DPRO");
        cn.add("DM & HO");
        cn.add("DPO");
       // cn.add("DPO");
        cn.add("DRO");
        cn.add("DRDO");
        cn.add("DSO");
        cn.add("Dy. Commissioner Labour");
        cn.add("Dy. Director District Treasury Office");
        cn.add("Asst.Pay & Accounts Office(new collectornumbers(Works&Projects))");
        cn.add("Dist. Minority Welfare Development Officer");
        cn.add("DEO");
      //  cn.add("DRDO");
        cn.add("Dy. Director Ground Water");
        cn.add("Dy. Commissioner Transport");
        cn.add("EE (Mission Bhagiratha)");
        cn.add("Executive Engineer(R&B) National Highways Hyderabad");
        cn.add("EXCISE");
        cn.add("EE PR Sangareddy");
        cn.add("EE Pollution Sangareddy");
        cn.add("EE Pollution RC Pur");
        cn.add("EE TS MIDC");
        cn.add("FIRE");
        cn.add("GM DIC");
        cn.add("Inspector Legal Metrology");
        cn.add("JC");
        cn.add("JD AGRICULTURE");
        cn.add("Lead District Manager(LDM)");
        cn.add("Manager Civil Corporation Corporation");
        cn.add("PD ICDS/ DISABLE  WELFARE");
        cn.add("PD MEPMA");
        cn.add("SE TDWSP Water Grid");
        cn.add("Regional manegar/Road Transport Corporaton dept");
        cn.add("Regional Sainik Welfare Office");
        cn.add("SE (Transco)");
        cn.add("Secretary Zilla Grandalaya Samsta");
        cn.add("STDA");
        cn.add("Telanagana Vaidhya Vidhana Parishad");
        cn.add("UNICEF (new collectornumbers(Parishkaram Cell))");
        cn.add("YOUTH AND SPORTS");
        cn.add("WELFARE DEPARTMENTS");


        cad145= new ArrayList<String>();
        cad146= new ArrayList<String>();
        cad147= new ArrayList<String>();
        cad148= new ArrayList<String>();
        cad166= new ArrayList<String>();
        cad167= new ArrayList<String>();
        cad168= new ArrayList<String>();
        cad169= new ArrayList<String>();
        cad170= new ArrayList<String>();
        cad172= new ArrayList<String>();
        cad173= new ArrayList<String>();
        cad183= new ArrayList<String>();
        cad184= new ArrayList<String>();
        cad185= new ArrayList<String>();
        cad186= new ArrayList<String>();
        cad187= new ArrayList<String>();
        cad188= new ArrayList<String>();
        cad189= new ArrayList<String>();
        cad588= new ArrayList<String>();
        cad589= new ArrayList<String>();
        cad590= new ArrayList<String>();
        cad591= new ArrayList<String>();
        cad592= new ArrayList<String>();
        cad593= new ArrayList<String>();
        cad594= new ArrayList<String>();
        cad595= new ArrayList<String>();

        cad589.add("--Select--");
        cad589.add("AMEENPUR  MPDO");
        cad589.add("AMEENPUR  MRO");
        cad173.add("--Select--");
        cad173.add("ANDOLE  MPDO");
        cad173.add("ANDOLE  MRO");
        cad590.add("--Select--");
        cad590.add("GUMMADIDALA  MPDO");
        cad590.add("GUMMADIDALA  MRO");
        cad183.add("--Select--");
        cad183.add("HATNOORA  MPDO");
        cad183.add("HATNOORA MRO");
        cad170.add("--Select--");
        cad170.add("JHARASANGAM  MPDO");
        cad170.add("JHARASANGAM MRO");
        cad187.add("--Select--");
        cad187.add("JINNARAM MPDO");
        cad187.add("JINNARAM MRO");
        cad148.add("--Select--");
        cad148.add("KALHER  MPDO");
        cad148.add("KALHER MRO");
        cad588.add("--Select--");
        cad588.add("KANDHI  MPDO");
        cad588.add("KANDHI MRO");
        cad145.add("--Select--");
        cad145.add("KANGTI  MPDO");
        cad145.add("KANGTI MRO");
        cad169.add("--Select--");
        cad169.add("KOHIR,MPDO");
        cad169.add("KOHIR MRO");
        cad185.add("--Select--");
        cad185.add("KONDAPUR  MPDO");
        cad185.add("KONDAPUR MRO");
        cad146.add("--Select--");
        cad146.add("MANOOR  MPDO");
        cad146.add("MANOOR  MRO");
        cad593.add("--Select--");
        cad593.add("MUGUDAMPALLY  MPDO");
        cad593.add("MUGUDAMPALLY MRO");
        cad592.add("--Select--");
        cad592.add("MUNIPALLY  MPDO");
        cad592.add("MUNIPALLY  MRO");
        cad595.add("--Select--");
        cad595.add("NAGILGIDDA MPDO");
        cad595.add("NAGILGIDDA MRO");
        cad147.add("--Select--");
        cad147.add("NARAYANAKHED MPDO");
        cad147.add("NARAYANAKHED MRO");
        cad147.add(" NARAYANKHED RDO");
        cad167.add("--Select--");
        cad167.add("NYALKAL MPDO");
        cad167.add("NYALKAL MRO");
        cad188.add("--Select--");
        cad188.add("PATANCHERU MPDO");
        cad188.add("PATANCHERU MRO");
        cad172.add("--Select--");
        cad172.add("PULKAL MPDO");
        cad172.add("PULKAL MRO");
        cad166.add("--Select--");
        cad166.add("RAIKODE MPDO");
        cad166.add("RAIKODE MRO");
        cad189.add("--Select--");
        cad189.add("RAMACHANDRAPURAM MPDO");
        cad189.add("RAMACHANDRAPURAM MRO");
        cad184.add("SADASHIVAPET MPDO");
        cad184.add("SADASHIVAPET MRO");
        cad186.add("--Select--");
        cad186.add("SANGAREDDY MPDO");
        cad186.add("SANGAREDDY MRO");
        cad186.add("SANGAREDDY RDO");
        cad594.add("--Select--");
        cad594.add("SIRGAPUR MPDO");
        cad594.add("SIRGAPUR MRO");
        cad591.add("--Select--");
        cad591.add("VATPALLY MPDO");
        cad591.add("VATPALLY MRO");
        cad168.add("--Select--");
        cad168.add("ZAHEERABAD MPDO");
        cad168.add("ZAHEERABAD MRO");



        cc = new ArrayList<collectornumbers>();
        cc.add(new collectornumbers(1, "1", "1"));
        cc.add(new collectornumbers(12570, "Asst, cane commissioner, Sangareddy", "9246761959"));
        cc.add(new collectornumbers(12558, "AC Endowment", "9246761959"));
        cc.add(new collectornumbers(12525, "AD FISHARIES", "9246761959"));
        cc.add(new collectornumbers(12557, "AD Handlooms & Textiles", "9246761959"));
        cc.add(new collectornumbers(12505, "AD HORTICULTURE", "9246761959"));
        cc.add(new collectornumbers(12523, "AD MINES", "9246761959"));
        cc.add(new collectornumbers(12568, "AD TSGLI", "9246761959"));
        cc.add(new collectornumbers(12520, "BC DEVELOPMENT OFFICER", "9246761959"));
        cc.add(new collectornumbers(12543, "Chief Planning Office", "9246761959"));
        cc.add(new collectornumbers(12513, "CEO ZP", "9246761959"));
      //  cc.add(new collectornumbers(12563, "CEO ZP", "9246761959"));
        cc.add(new collectornumbers(12398, "Collector", "9246761959"));

        cc.add(new collectornumbers(12628, "Commissioner-Sangareddy", "0000000000"));
        cc.add(new collectornumbers(12629, "Commissioner-Sadashivpet", "0000000000"));
        cc.add(new collectornumbers(12630, "Commissioner-Zaheerabad", "0000000000"));
        cc.add(new collectornumbers(12631, "Commissioner-Andole", "0000000000"));
        cc.add(new collectornumbers(12512, "DD AE", "9246761959"));
        cc.add(new collectornumbers(12506, "DEO/SSA", "9246761959"));
        cc.add(new collectornumbers(12519, "DFO", "9246761959"));
        cc.add(new collectornumbers(12531, "Dist. Agriculture Officer", "9246761959"));
        cc.add(new collectornumbers(12545, "District Audit Office", "9246761959"));
       // cc.add(new collectornumbers(12526, "District Collector", "9246761959"));
        cc.add(new collectornumbers(12536, "District Coordinate Office (new collectornumbers( Arogya Sree))", "9246761959"));
        cc.add(new collectornumbers(12530, "District Cooperative Officer", "9246761959"));
        cc.add(new collectornumbers(12540, "District Civil Supply Officer", "9246761959"));
        cc.add(new collectornumbers(12538, "District Employment Exchange", "9246761959"));
        cc.add(new collectornumbers(12532, "District Fisheries Officer", "9246761959"));
        cc.add(new collectornumbers(12529, "Dist. Horticulure & Secriculure", "9246761959"));
        cc.add(new collectornumbers(12521, "DIST IRRIGATION OFFICER", "9246761959"));
        cc.add(new collectornumbers(12556, "District Marketing Officer", "9246761959"));
        cc.add(new collectornumbers(12522, "DIST R&B OFFICER", "9246761959"));
        cc.add(new collectornumbers(12554, "Dist. Prohibition & Excise Officer", "9246761959"));
        cc.add(new collectornumbers(12528, "District Registration and Stamps", "9246761959"));
        cc.add(new collectornumbers(12534, "District SC Development Office", "9246761959"));
        cc.add(new collectornumbers(12527, "District Survey & Land Records Office", "9246761959"));
        cc.add(new collectornumbers(12559, "District Intermediate Education Officer", "9246761959"));
        cc.add(new collectornumbers(12567, "Dist. IDC Sangareddy", "9246761959"));
        cc.add(new collectornumbers(12542, "District Tribal welfare Development Officer", "9246761959"));
        cc.add(new collectornumbers(12533, "Dist. Veternary & Animal Husbandry Officer", "9246761959"));
        cc.add(new collectornumbers(12571, "DM,Housing", "9246761959"));
        cc.add(new collectornumbers(12544, "DPRO", "9246761959"));
        cc.add(new collectornumbers(12535, "DM & HO", "9246761959"));
        cc.add(new collectornumbers(12508, "DPO", "9246761959"));
      //  cc.add(new collectornumbers(12514, "DPO", "9246761959"));
        cc.add(new collectornumbers(12401, "DRO", "9246761959"));
        cc.add(new collectornumbers(12504, "DRDO", "9246761959"));
        cc.add(new collectornumbers(12524, "DSO", "9246761959"));
        cc.add(new collectornumbers(12539, "Dy. Commissioner Labour", "9246761959"));
        cc.add(new collectornumbers(12546, "Dy. Director District Treasury Office", "9246761959"));
        cc.add(new collectornumbers(12547, "Asst.Pay & Accounts Office(new collectornumbers(Works&Projects))", "9246761959"));
        cc.add(new collectornumbers(12548, "Dist. Minority Welfare Development Officer", "9246761959"));
        cc.add(new collectornumbers(12549, "DEO", "9246761959"));
      //  cc.add(new collectornumbers(12551, "DRDO", "9246761959"));
        cc.add(new collectornumbers(12553, "Dy. Director Ground Water", "9246761959"));
        cc.add(new collectornumbers(12569, "Dy. Commissioner Transport", "9246761959"));
        cc.add(new collectornumbers(12633, "EE (Mission Bhagiratha)", "0000000000"));
        cc.add(new collectornumbers(12634, "Executive Engineer(R&B) National Highways Hyderabad", "0000000000"));
        cc.add(new collectornumbers(12518, "EXCISE", "9246761959"));
        cc.add(new collectornumbers(12550, "EE PR Sangareddy", "9246761959"));
        cc.add(new collectornumbers(12565, "EE Pollution Sangareddy", "9246761959"));
        cc.add(new collectornumbers(12566, "EE Pollution RC Pur", "9246761959"));
        cc.add(new collectornumbers(12537, "EE TS MIDC", "9246761959"));
        cc.add(new collectornumbers(12517, "FIRE", "9246761959"));
        cc.add(new collectornumbers(12516, "GM DIC", "9246761959"));
        cc.add(new collectornumbers(12555, "Inspector Legal Metrology", "9246761959"));
        cc.add(new collectornumbers(12400, "JC", "9246761959"));
        cc.add(new collectornumbers(12515, "JD AGRICULTURE", "9246761959"));;
        cc.add(new collectornumbers(12635, "Lead District Manager(LDM)", "0010101010"));;
        cc.add(new collectornumbers(12541, "Manager Civil Corporation Corporation", "9246761959"));
        cc.add(new collectornumbers(12507, "PD ICDS/ DISABLE  WELFARE", "9246761959"));
        cc.add(new collectornumbers(12562, "PD MEPMA", "9246761959"));
        cc.add(new collectornumbers(12552, "SE TDWSP Water Grid", "9246761959"));
        cc.add(new collectornumbers(12572, "Regional manegar/Road Transport Corporaton dept", "9246761959"));
        cc.add(new collectornumbers(12560, "Regional Sainik Welfare Office", "9246761959"));
        cc.add(new collectornumbers(12632, "SE (Transco)", "0000000000"));
        cc.add(new collectornumbers(12564, "Secretary Zilla Grandalaya Samsta", "9246761959"));
        cc.add(new collectornumbers(12510, "STDA", "9246761959"));
        cc.add(new collectornumbers(12636, "Telanagana Vaidhya Vidhana Parishad", "0000000000"));
        cc.add(new collectornumbers(12561, "UNICEF (new collectornumbers(Parishkaram Cell))", "9246761959"));
        cc.add(new collectornumbers(12511, "YOUTH AND SPORTS", "9246761959"));
        cc.add(new collectornumbers(12509, "WELFARE DEPARTMENTS","9246761959"));


        ccad145 = new ArrayList<collectornumbers>();
        ccad172 = new ArrayList<collectornumbers>();
        ccad173 = new ArrayList<collectornumbers>();
        ccad183 = new ArrayList<collectornumbers>();
        ccad184 = new ArrayList<collectornumbers>();
        ccad185 = new ArrayList<collectornumbers>();
        ccad186 = new ArrayList<collectornumbers>();
        ccad187 = new ArrayList<collectornumbers>();
        ccad188 = new ArrayList<collectornumbers>();
        ccad189 = new ArrayList<collectornumbers>();
        ccad588 = new ArrayList<collectornumbers>();
        ccad146 = new ArrayList<collectornumbers>();
        ccad589 = new ArrayList<collectornumbers>();
        ccad590 = new ArrayList<collectornumbers>();
        ccad591 = new ArrayList<collectornumbers>();
        ccad592 = new ArrayList<collectornumbers>();
        ccad593 = new ArrayList<collectornumbers>();
        ccad594 = new ArrayList<collectornumbers>();
        ccad595 = new ArrayList<collectornumbers>();
        ccad147 = new ArrayList<collectornumbers>();
        ccad148 = new ArrayList<collectornumbers>();
        ccad166 = new ArrayList<collectornumbers>();
        ccad167 = new ArrayList<collectornumbers>();
        ccad168 = new ArrayList<collectornumbers>();
        ccad169 = new ArrayList<collectornumbers>();
        ccad170 = new ArrayList<collectornumbers>();
        cctwo = new ArrayList<collectornumbers>();
      //  cctwo.add(new collectornumbers(1, "1", "1"));
        ccad186.add(new collectornumbers(12598, "SANGAREDDY MPDO", "9246761959"));
        ccad186.add(new collectornumbers(12598, "SANGAREDDY MPDO", "9246761959"));
        ccad186.add(new collectornumbers(12624, "SANGAREDDY MRO", "9246761959"));
        ccad186.add(new collectornumbers(12573, "SANGAREDDY RDO", "9246761959"));

        ccad145.add(new collectornumbers(12584, "KANGTI  MPDO", "9246761959"));
        ccad145.add(new collectornumbers(12584, "KANGTI  MPDO", "9246761959"));
        ccad145.add(new collectornumbers(12610, "KANGTI MRO", "9246761959"));

        ccad172.add(new collectornumbers(12594, "PULKAL MPDO", "9246761959"));
        ccad172.add(new collectornumbers(12594, "PULKAL MPDO", "9246761959"));
        ccad172.add(new collectornumbers(12620, "PULKAL MRO", "9246761959"));

        ccad173.add(new collectornumbers(12577, "ANDOLE  MPDO", "9246761959"));
        ccad173.add(new collectornumbers(12577, "ANDOLE  MPDO", "9246761959"));
        ccad173.add(new collectornumbers(12603, "ANDOLE  MRO", "9246761959"));

        ccad183.add(new collectornumbers(12579, "HATNOORA  MPDO", "9246761959"));
        ccad183.add(new collectornumbers(12579, "HATNOORA  MPDO", "9246761959"));
        ccad183.add(new collectornumbers(12605, "HATNOORA MRO", "9246761959"));

        ccad184.add(new collectornumbers(12597, "SADASHIVAPET MPDO", "9246761959"));
        ccad184.add(new collectornumbers(12597, "SADASHIVAPET MPDO", "9246761959"));
        ccad184.add(new collectornumbers(12623, "SADASHIVAPET MRO", "9246761959"));

        ccad185.add(new collectornumbers(12586, "KONDAPUR  MPDO", "9246761959"));
        ccad185.add(new collectornumbers(12586, "KONDAPUR  MPDO", "9246761959"));
        ccad185.add(new collectornumbers(12612, "KONDAPUR MRO", "9246761959"));

        ccad187.add(new collectornumbers(12581, "JINNARAM MPDO", "9246761959"));
        ccad187.add(new collectornumbers(12581, "JINNARAM MPDO", "9246761959"));
        ccad187.add(new collectornumbers(12607, "JINNARAM MRO", "9246761959"));

        ccad188.add(new collectornumbers(12593, "PATANCHERU MPDO", "9246761959"));
        ccad188.add(new collectornumbers(12593, "PATANCHERU MPDO", "9246761959"));
        ccad188.add(new collectornumbers(12619, "PATANCHERU MRO", "9246761959"));

        ccad189.add(new collectornumbers(12596, "RAMACHANDRAPURAM MPDO", "9246761959"));
        ccad189.add(new collectornumbers(12596, "RAMACHANDRAPURAM MPDO", "9246761959"));
        ccad189.add(new collectornumbers(12622, "RAMACHANDRAPURAM MRO", "9246761959"));

        ccad588.add(new collectornumbers(12583, "KANDHI  MPDO", "9246761959"));
        ccad588.add(new collectornumbers(12583, "KANDHI  MPDO", "9246761959"));
        ccad588.add(new collectornumbers(12609, "KANDHI MRO", "9246761959"));

        ccad146.add(new collectornumbers(12587, "MANOOR  MPDO", "9246761959"));
        ccad146.add(new collectornumbers(12587, "MANOOR  MPDO", "9246761959"));
        ccad146.add(new collectornumbers(12613, "MANOOR  MRO", "9246761959"));

        ccad589.add(new collectornumbers(12576, "AMEENPUR  MPDO", "9246761959"));
        ccad589.add(new collectornumbers(12576, "AMEENPUR  MPDO", "9246761959"));
        ccad589.add(new collectornumbers(12602, "AMEENPUR  MRO", "9246761959"));

        ccad590.add(new collectornumbers(12578, "GUMMADIDALA  MPDO", "9246761959"));
        ccad590.add(new collectornumbers(12578, "GUMMADIDALA  MPDO", "9246761959"));
        ccad590.add(new collectornumbers(12604, "GUMMADIDALA  MRO", "9246761959"));

        ccad591.add(new collectornumbers(12600, "VATPALLY MPDO", "9246761959"));
        ccad591.add(new collectornumbers(12600, "VATPALLY MPDO", "9246761959"));
        ccad591.add(new collectornumbers(12626, "VATPALLY MRO", "9246761959"));

        ccad592.add(new collectornumbers(12589, "MUNIPALLY  MPDO", "9246761959"));
        ccad592.add(new collectornumbers(12589, "MUNIPALLY  MPDO", "9246761959"));
        ccad592.add(new collectornumbers(12615, "MUNIPALLY  MRO", "9246761959"));

        ccad593.add(new collectornumbers(12588, "MUGUDAMPALLY  MPDO", "9246761959"));
        ccad593.add(new collectornumbers(12588, "MUGUDAMPALLY  MPDO", "9246761959"));
        ccad593.add(new collectornumbers(12614, "MUGUDAMPALLY MRO", "9246761959"));

        ccad594.add(new collectornumbers(12599, "SIRGAPUR MPDO", "9246761959"));
        ccad594.add(new collectornumbers(12599, "SIRGAPUR MPDO", "9246761959"));
        ccad594.add(new collectornumbers(12625, "SIRGAPUR MRO", "9246761959"));

        ccad595.add(new collectornumbers(12590, "NAGILGIDDA MPDO", "9246761959"));
        ccad595.add(new collectornumbers(12590, "NAGILGIDDA MPDO", "9246761959"));
        ccad595.add(new collectornumbers(12616, "NAGILGIDDA MRO", "9246761959"));

        ccad147.add(new collectornumbers(12591, "NARAYANAKHED MPDO", "9246761959"));
        ccad147.add(new collectornumbers(12591, "NARAYANAKHED MPDO", "9246761959"));
        ccad147.add(new collectornumbers(12617, "NARAYANAKHED MRO", "9246761959"));
        ccad147.add(new collectornumbers(12575, "NARAYANKHED RDO", "9246761959"));

        ccad148.add(new collectornumbers(12582, "KALHER  MPDO", "9246761959"));
        ccad148.add(new collectornumbers(12582, "KALHER  MPDO", "9246761959"));
        ccad148.add(new collectornumbers(12608, "KALHER MRO", "9246761959"));

        ccad166.add(new collectornumbers(12595, "RAIKODE MPDO", "9246761959"));
        ccad166.add(new collectornumbers(12595, "RAIKODE MPDO", "9246761959"));
        ccad166.add(new collectornumbers(12621, "RAIKODE MRO", "9246761959"));

        ccad167.add(new collectornumbers(12592, "NYALKAL MPDO", "9246761959"));
        ccad167.add(new collectornumbers(12592, "NYALKAL MPDO", "9246761959"));
        ccad167.add(new collectornumbers(12618, "NYALKAL MRO", "9246761959"));

        ccad168.add(new collectornumbers(12601, "ZAHEERABAD MPDO", "9246761959"));
        ccad168.add(new collectornumbers(12601, "ZAHEERABAD MPDO", "9246761959"));
        ccad168.add(new collectornumbers(12627, "ZAHEERABAD MRO", "9246761959"));
        ccad168.add(new collectornumbers(12574, "ZAHEERABAD RDO", "9246761959"));

        ccad169.add(new collectornumbers(12585, "KOHIR,MPDO", "9246761959"));
        ccad169.add(new collectornumbers(12585, "KOHIR,MPDO", "9246761959"));
        ccad169.add(new collectornumbers(12611, "KOHIR MRO", "9246761959"));

        ccad170.add(new collectornumbers(12580, "JHARASANGAM  MPDO", "9246761959"));
        ccad170.add(new collectornumbers(12580, "JHARASANGAM  MPDO", "9246761959"));
        ccad170.add(new collectornumbers(12606, "JHARASANGAM MRO", "9246761959"));



        fc1= new ArrayList<mandal>();
        fc1.add(new mandal(1," null",0));
        fc1.add(new mandal(145,"Babulgaon",1000));
        fc1.add(new mandal(145,"Borgi",1003));
        fc1.add(new mandal(145,"Bheemra",1013));
        fc1.add(new mandal(145,"Chowkanpalle",1002));
        fc1.add(new mandal(145,"Chapta [B]",1004));
        fc1.add(new mandal(145,"Chapta [Khurd]",1011));
        fc1.add(new mandal(145,"Degulawadi",1001));
        fc1.add(new mandal(145,"Damaragiddi [Panchamahal]",1021));
        fc1.add(new mandal(145,"Bhanswada",1020));
        fc1.add(new mandal(145,"Enkemori",1025));
        fc1.add(new mandal(145,"Gajulpahad",1018));
        fc1.add(new mandal(145,"Jamgikhurd",1005));
        fc1.add(new mandal(145,"Jamgiburg",1006));
        fc1.add(new mandal(145,"Kangti",1012));
        fc1.add(new mandal(145,"Murkunjal",1010));
        fc1.add(new mandal(145,"Nagoor [K]",1014));
        fc1.add(new mandal(145,"Nagoor [B]",1015));
       // fc1.add(new mandal(145,"Potpalle",1022));
        fc1.add(new mandal(145,"Ramathirath",1023));
        fc1.add(new mandal(145,"Rasole",1019));
        fc1.add(new mandal(145,"Sidhangarga",1024));
        fc1.add(new mandal(145,"Sukalteerth",1017));
        fc1.add(new mandal(145,"Tadkal",1009));
        fc1.add(new mandal(145,"Turkwadgaon",1016));
        fc1.add(new mandal(145,"Valmoor",1008));
        fc1.add(new mandal(145,"Yampalle",1007));

        fc2= new ArrayList<mandal>();
        fc2.add(new mandal(1," null",0));
        fc2.add(new mandal(146,"Athimail",1043));
        fc2.add(new mandal(146,"Badalgaon",1042));
        fc2.add(new mandal(146,"Bellapur",1044));
        fc2.add(new mandal(146,"Borancha",1050));
        fc2.add(new mandal(146,"Dosapalle",1037));
        fc2.add(new mandal(146,"Davvur",1035));
        fc2.add(new mandal(146,"Dudhagonda",1047));
        fc2.add(new mandal(146,"Danvar",1051));
        fc2.add(new mandal(146,"Errakipalle",1026));
        fc2.add(new mandal(146,"Enekpalle",1027));
        fc2.add(new mandal(146,"Gatlingampalle",1046));
        fc2.add(new mandal(146,"Gondagaon",1031));
        fc2.add(new mandal(146,"Keswar",1033));
        fc2.add(new mandal(146,"Mavinelli",1032));
        fc2.add(new mandal(146,"Mugdumpur",1049));
        fc2.add(new mandal(146,"Maikode",1045));
        fc2.add(new mandal(146,"Manoor",1036));
        fc2.add(new mandal(146,"Nadigadda Hukrana",1040));
        fc2.add(new mandal(146,"Pulkurthy",1041));
      //  fc2.add(new mandal(146,"Raipalle",1052));
        fc2.add(new mandal(146,"Shari Damargidda",1029));
        fc2.add(new mandal(146,"Shelgera",1034));
        fc2.add(new mandal(146,"Yarriboguda",1030));
        fc2.add(new mandal(146,"Thornal",1039));
        fc2.add(new mandal(146,"Tumnoor",1048));
        fc2.add(new mandal(146,"Utpalle",1028));
        fc2.add(new mandal(146,"Usirkapalle",1053));
        fc2.add(new mandal(146,"Yelgoi",1054));
       // fc2.add(new mandal(146,"Yelgoi",1038));

        fc3= new ArrayList<mandal>();
        fc3.add(new mandal(1," null",0));
        fc3.add(new mandal(147,"Abenda",1068));
        fc3.add(new mandal(147,"Anthwar",1074));
      //  fc3.add(new mandal(147,"Anthwar",1081));
      //  fc3.add(new mandal(147,"Anthwar",1080));
        fc3.add(new mandal(147,"Bhanapur",1075));
        fc3.add(new mandal(147,"Chandkhanpally",1055));
        fc3.add(new mandal(147,"Chaptakhadeem",1059));
        fc3.add(new mandal(147,"Chandkhanpalle",1066));
        fc3.add(new mandal(147,"Gadthihokrana",1069));
        fc3.add(new mandal(147,"Gadthi Hokrana",1056));
        fc3.add(new mandal(147,"Hangarga [K]",1058));
        fc3.add(new mandal(147,"Hangarga [B]",1067));
        fc3.add(new mandal(147,"Hanmanthraopet",1072));

        fc3.add(new mandal(147,"Juzalpur",1076));
        fc3.add(new mandal(147,"Kamjipoor",1061));
        fc3.add(new mandal(147,"Mansoorpur",1070));

        fc3.add(new mandal(147,"Madhavar",1073));
        fc3.add(new mandal(147,"Namalimet",1064));
        fc3.add(new mandal(147,"Narasapur",1065));
        fc3.add(new mandal(147,"Narayankhed",1071));
     //   fc3.add(new mandal(147,"Narasapur",1082));
        fc3.add(new mandal(147,"Paidpally",1057));
        fc3.add(new mandal(147,"Panchagaon",1077));
        fc3.add(new mandal(147,"Ryakal",1060));
        fc3.add(new mandal(147,"Ryalamadugu",1063));
        fc3.add(new mandal(147,"Rudrar",1079));
        fc3.add(new mandal(147,"Sanjivanrao Pet",1062));
        fc3.add(new mandal(147,"Sathagaon",1078));




        fc4= new ArrayList<mandal>();
        fc4.add(new mandal(1," null",0));
        fc4.add(new mandal(148,"Bachepalle",1090));
        fc4.add(new mandal(148,"Fathepoor",1083));
        fc4.add(new mandal(148,"Gosaipalle",1094));
        fc4.add(new mandal(148,"Kalher",1085));
        fc4.add(new mandal(148,"Krishnapoor",1092));
        fc4.add(new mandal(148,"Khanapur (Kadeem)",1093));
        fc4.add(new mandal(148,"Khanapoor (B)",1088));
        fc4.add(new mandal(148,"Masan Palle",1086));
        fc4.add(new mandal(148,"Mahadev Palle",1087));
        fc4.add(new mandal(148,"Mardi",1084));
        fc4.add(new mandal(148,"Mungepalle",1096));
        fc4.add(new mandal(148,"Malharpoor",1097));
        fc4.add(new mandal(148,"Nagdhar",1095));
        fc4.add(new mandal(148,"Ramreddi Pet",1089));
        fc4.add(new mandal(148,"Raparthy",1091));


        fc5= new ArrayList<mandal>();
        fc5.add(new mandal(1," null",0));
        fc5.add(new mandal(166,"Auranganagar (Patti Hasnabad)",1127));
        fc5.add(new mandal(166,"Chimnapur (DP)",1100));
        fc5.add(new mandal(166,"Chimnapur",1116));
        fc5.add(new mandal(166,"Hudavandapur @ Narayanpalle",1126));
        fc5.add(new mandal(166,"Hulgera",1114));
        fc5.add(new mandal(166,"Itkepally",1098));
        fc5.add(new mandal(166,"Itkepalle",1108));
        fc5.add(new mandal(166,"Indoor",1120));
       // fc5.add(new mandal(166,"Indoor",1128));
        fc5.add(new mandal(166,"Jamga (Khurd)",1117));
        fc5.add(new mandal(166,"Jamalpur (DP)",1099));
        fc5.add(new mandal(166,"Jamgi (Khurd)",1101));
        fc5.add(new mandal(166,"Khanjamalpur",1107));
        fc5.add(new mandal(166,"Karchal",1122));
        fc5.add(new mandal(166,"Kushnoor",1113));
        //fc5.add(new mandal(166,"Matoor",1129));
        //fc5.add(new mandal(166,"Matoor",1130));
       // fc5.add(new mandal(166,"Matoor",1131));
       // fc5.add(new mandal(166,"Mustafapur",1105));
        fc5.add(new mandal(166,"Matoor",1106));
        fc5.add(new mandal(166,"Mahbathpur",1111));
        fc5.add(new mandal(166,"Moratga",1119));
        fc5.add(new mandal(166,"Mamidipally",1103));
        fc5.add(new mandal(166,"Mustafapur",1121));
        fc5.add(new mandal(166,"Nallampally",1104));
        fc5.add(new mandal(166,"Nallampalle",1123));
        fc5.add(new mandal(166,"Nagwar",1112));
        fc5.add(new mandal(166,"Peapalpally",1102));
        fc5.add(new mandal(166,"Pampad",1109));
        fc5.add(new mandal(166,"Peapalpalle",1118));
        fc5.add(new mandal(166,"Raipalle [Patti Karchal]",1125));
        fc5.add(new mandal(166,"Sirur",1110));
        fc5.add(new mandal(166,"Shamshuddinpur",1115));
        fc5.add(new mandal(166,"Yousufpur",1124));

        fc6= new ArrayList<mandal>();
        fc6.add(new mandal(1," null",0));
        fc6.add(new mandal(167,"Ameerabad",1139));
        fc6.add(new mandal(167,"Atnur",1153));
        fc6.add(new mandal(167,"Basanthpur",1162));
        fc6.add(new mandal(167,"Chingepally",1132));
        fc6.add(new mandal(167,"Chalki",1142));
        fc6.add(new mandal(167,"Chingepalle",1143));
        fc6.add(new mandal(167,"Cheekurthi",1138));
        fc6.add(new mandal(167,"Dappur",1146));
        fc6.add(new mandal(167,"Gunjetti",1134));
        fc6.add(new mandal(167,"Gangwar",1169));
        fc6.add(new mandal(167,"Ganjoti",1150));
      //fc6.add(new mandal(167,"Gangwar",1164));
        fc6.add(new mandal(167,"Husselli",1149));
        fc6.add(new mandal(167,"Hadnur",1158));
        fc6.add(new mandal(167,"Kakijanwada",1140));
        fc6.add(new mandal(167,"Kalbemal",1161));
        fc6.add(new mandal(167,"Khaleelpur[M]",1166));
        fc6.add(new mandal(167,"Murthujapur",1141));
        fc6.add(new mandal(167,"Mamidgi",1160));
        fc6.add(new mandal(167,"Mungi",1157));
        fc6.add(new mandal(167,"Metalkunta",1163));
        fc6.add(new mandal(167,"Mirzapur[B]{",1165));
        fc6.add(new mandal(167,"Mariampur",1144));
        fc6.add(new mandal(167,"Mirjapur[N]",1154));
        fc6.add(new mandal(167,"Malgi",1145));
        fc6.add(new mandal(167,"Malkanpahad",1168));
        fc6.add(new mandal(167,"Naimatabad",1136));
        fc6.add(new mandal(167,"Nyalkal",1137));
     // fc6.add(new mandal(167,"Nyalkal",1152));
        fc6.add(new mandal(167,"Namtabad",1159));
        fc6.add(new mandal(167,"Raghavapur",1580));
        fc6.add(new mandal(167,"Ramtheerth",1151));
        fc6.add(new mandal(167,"Rejinthal",1167));
        fc6.add(new mandal(167,"Shamshallapur",1148));
        fc6.add(new mandal(167,"Shamshullapur",1133));
        fc6.add(new mandal(167,"Tatpally",1135));
        fc6.add(new mandal(167,"Tatpalle",1155));
        fc6.add(new mandal(167,"Tekur",1156));
        fc6.add(new mandal(167,"Waddi",1147));

        fc7= new ArrayList<mandal>();
        fc7.add(new mandal(1," null",0));
        fc7.add(new mandal(168,"Anegunta",1204));
        fc7.add(new mandal(168,"Algole",1186));
        fc7.add(new mandal(168,"Buchnelli",1181));
        fc7.add(new mandal(168,"Burdipahad",1182));
        fc7.add(new mandal(168,"Chinna Hyderabad",1174));
        fc7.add(new mandal(168,"Chiragpally",1175));
        fc7.add(new mandal(168,"Chiragpalle",1179));
        fc7.add(new mandal(168,"Didgi",1185));
        fc7.add(new mandal(168,"Govindpur",1177));
        fc7.add(new mandal(168,"Godegarpalle (Patti Dhanasiri)",1191));
        fc7.add(new mandal(168,"Gudpalle",1193));
        fc7.add(new mandal(168,"Hothi [B]",1197));
        fc7.add(new mandal(168,"Hyderabad",1198));
        fc7.add(new mandal(168,"Hothi [K]",1199));
        fc7.add(new mandal(168,"Huggelli",1202));
        fc7.add(new mandal(168,"Ippepalle",1195));
        fc7.add(new mandal(168,"Kothur [B]",1184));
        fc7.add(new mandal(168,"Kasimpur",1190));
        fc7.add(new mandal(168,"Khan Jamalapur",1192));
        fc7.add(new mandal(168,"Malkapur [Jadi]",1206));
        fc7.add(new mandal(168,"Malchelma",1207));
        fc7.add(new mandal(168,"Mogdampalle",1194));
        fc7.add(new mandal(168,"Pasthapur",1172));
        fc7.add(new mandal(168,"Pastapur",1187));
        fc7.add(new mandal(168,"Raipalle [ P.D.]",1196));
        fc7.add(new mandal(168,"Rachannapet",1208));
        fc7.add(new mandal(168,"Ranjole",1200));
        fc7.add(new mandal(168,"Raipalle",1201));
        fc7.add(new mandal(168,"Raipally patti Digwal",1170));
        fc7.add(new mandal(168,"Sathwar",1176));
        fc7.add(new mandal(168,"Shekapur",1178));
        fc7.add(new mandal(168,"Satwar",1180));
        fc7.add(new mandal(168,"Shaikapur",1203));
        fc7.add(new mandal(168,"Sarjaraopet",1205));
        fc7.add(new mandal(168,"Tumkunta",1183));
        fc7.add(new mandal(168,"Tamadpalle",1188));
        fc7.add(new mandal(168,"Thammadpalli",1173));
        fc7.add(new mandal(168,"Zahirabad (M )",1171));
        fc7.add(new mandal(168,"Zahirabad (Rural)",1189));


        fc8= new ArrayList<mandal>();

        fc8.add(new mandal(1," null",0));
        fc8.add(new mandal(169,"Bilalpur",1227));
        fc8.add(new mandal(169,"Badampet",1225));
        fc8.add(new mandal(169,"Chinthalghat",1213));
        fc8.add(new mandal(169,"Digwal",1211));
        fc8.add(new mandal(169,"Godgar Palle",1224));
        fc8.add(new mandal(169,"Gurjuwada",1218));
        fc8.add(new mandal(169,"Kavelli",1212));
        fc8.add(new mandal(169,"Kothur Patti Digwal",1214));
        fc8.add(new mandal(169,"Kothur Pattikohir",1228));
        fc8.add(new mandal(169,"Kohir",1216));
        fc8.add(new mandal(169,"Madri",1210));
        fc8.add(new mandal(169,"Maniyarpalle",1226));
        fc8.add(new mandal(169,"Machreddipalle",1220));
        fc8.add(new mandal(169,"Nagireddy Palle",1230));
        fc8.add(new mandal(169,"Pothireddypally",1209));
        fc8.add(new mandal(169,"Pothireddypalle",1215));
        fc8.add(new mandal(169,"Paidigummal",1229));
        fc8.add(new mandal(169,"Picharagad",1219));
        fc8.add(new mandal(169,"Parsapalle",1222));
        fc8.add(new mandal(169,"Rajnelli",1217));
        fc8.add(new mandal(169,"Sajjapur",1221));
        fc8.add(new mandal(169,"Siddapur Pattikohir",1223));


        fc9= new ArrayList<mandal>();
        fc9.add(new mandal(1," null",0));
        fc9.add(new mandal(170,"Boppanpally",1236));
        fc9.add(new mandal(170,"Bidekanna",1265));
        fc9.add(new mandal(170,"Bopanpalle",1256));
        fc9.add(new mandal(170,"Chilkepalle",1267));
        //fc9.add(new mandal(170,"Chilapally",1234));
        fc9.add(new mandal(170,"Chilkepally",1245));
        fc9.add(new mandal(170,"Chilemamidi",1261));
        fc9.add(new mandal(170,"Chilepalle",1252));
        fc9.add(new mandal(170,"Devarampally",1240));
        fc9.add(new mandal(170,"Edakulapally",1239));
        fc9.add(new mandal(170,"Edulapally",1243));
        fc9.add(new mandal(170,"Edakulapalle",1260));
        fc9.add(new mandal(170,"Guntamarpally",1237));
        fc9.add(new mandal(170,"Giniyarpally",1244));
        fc9.add(new mandal(170,"Giniyarpalle",1266));
        fc9.add(new mandal(170,"Guntamarpalle",1257));
        fc9.add(new mandal(170,"Jeerlapally",1238));
        fc9.add(new mandal(170,"Jharasangam",1255));
        fc9.add(new mandal(170,"Jeerlapalle",1258));
        fc9.add(new mandal(170,"Junegaon",1259));
        fc9.add(new mandal(170,"Kappad",1248));
        fc9.add(new mandal(170,"Kamalpalle",1249));
        fc9.add(new mandal(170,"Kamalpally",1233));
        fc9.add(new mandal(170,"Krishnapur",1264));
        fc9.add(new mandal(170,"Kuppanagar",1254));
        fc9.add(new mandal(170,"Kakkerwada",1251));
        fc9.add(new mandal(170,"Medapally",1241));
        fc9.add(new mandal(170,"Machnoor",1263));
        fc9.add(new mandal(170,"Potpally",1235));
        fc9.add(new mandal(170,"Potpalle",1253));
        fc9.add(new mandal(170,"Pyarawaram",1262));
        fc9.add(new mandal(170,"Rampur (DP)",1242));
        fc9.add(new mandal(170,"Sangam [Khurd]",1250));
        fc9.add(new mandal(170,"Tummanpalle",1247));
        fc9.add(new mandal(170,"Tummanpally",1232));
        fc9.add(new mandal(170,"Vanampally",1231));
        fc9.add(new mandal(170,"Vanampalle",1246));

        fc10= new ArrayList<mandal>();
        fc10.add(new mandal(1," null",0));
        fc10.add(new mandal(172,"Bommareddygudem",1294));
        fc10.add(new mandal(172,"Chakriyal",1290));
        fc10.add(new mandal(172,"Choutkur",1281));
        fc10.add(new mandal(172,"Esojipet",1283));
        fc10.add(new mandal(172,"Gangojipet",1289));
        fc10.add(new mandal(172,"Gangulur",1284));
        fc10.add(new mandal(172,"Hunnapur (DP)",1268));
        fc10.add(new mandal(172,"Hunnapur",1271));
        fc10.add(new mandal(172,"Kodur",1282));
        fc10.add(new mandal(172,"Lakshmisagar",1279));
        fc10.add(new mandal(172,"Muddaipet",1275));
       // fc10.add(new mandal(172,"Muddaipet",1293));
        fc10.add(new mandal(172,"Mudimanik",1277));
        fc10.add(new mandal(172,"Posanipalle",1280));
        fc10.add(new mandal(172,"Pulkal",1270));
       // fc10.add(new mandal(172,"Pulkal",1276));
        fc10.add(new mandal(172,"Peddareddipet",1272));
        fc10.add(new mandal(172,"Sureddi Itkyal",1278));
        fc10.add(new mandal(172,"Seripeddareddipet (DP)",1269));
        fc10.add(new mandal(172,"Seripeddareddipet",1273));
        fc10.add(new mandal(172,"Singoor",1274));
        fc10.add(new mandal(172,"Seriramreddiguda",1285));
        fc10.add(new mandal(172,"Sarafpalle",1286));
        fc10.add(new mandal(172,"Sivampet",1291));
        fc10.add(new mandal(172,"Taddanpalle",1288));
        fc10.add(new mandal(172,"Vendikole",1292));
        fc10.add(new mandal(172,"Venkatakistapur @ Angadipet",1287));

        fc11= new ArrayList<mandal>();
        fc11.add(new mandal(1," null",0));
        fc11.add(new mandal(173,"Almaipet",1325));
        fc11.add(new mandal(173,"Aksanpalle",1317));
        fc11.add(new mandal(173,"Andole",1323));
        fc11.add(new mandal(173,"Aksanpally",1305));
        fc11.add(new mandal(173,"Brahmanpally",1304));
        fc11.add(new mandal(173,"chintakunta",1583));
        fc11.add(new mandal(173,"Dakoor",1321));
        fc11.add(new mandal(173,"Danampalle",1315));
        fc11.add(new mandal(173,"Danampally",1302));
        fc11.add(new mandal(173,"Kansanpalle",1311));
        fc11.add(new mandal(173,"Kansanpally",1298));
        fc11.add(new mandal(173,"Kichanapally",1300));
        fc11.add(new mandal(173,"Kondareddipally",1308));
        fc11.add(new mandal(173,"Kichanapalle",1313));
        fc11.add(new mandal(173,"Kodekal",1319));
        fc11.add(new mandal(173,"Mansanpally",1301));
        fc11.add(new mandal(173,"Masanpally",1306));
        fc11.add(new mandal(173,"Neradigunta",1303));
        fc11.add(new mandal(173,"Nadlapur",1320));
        fc11.add(new mandal(173,"Neerdigunta",1316));
       // fc11.add(new mandal(173,"Pothireddypally",1307));
       // fc11.add(new mandal(173,"Rollapahad",1295));
        fc11.add(new mandal(173,"Ramsanpally",1299));
        fc11.add(new mandal(173,"Ramsanpalle",1312));
        fc11.add(new mandal(173,"Rollapahad",1314));
        fc11.add(new mandal(173,"Sangupet",1322));
        fc11.add(new mandal(173,"Saibanpet",1324));
        fc11.add(new mandal(173,"Serimallareddipally",1297));
        fc11.add(new mandal(173,"Serimallareddipalle",1310));
        fc11.add(new mandal(173,"Talema",1296));
        fc11.add(new mandal(173,"Telelma",1309));
        fc11.add(new mandal(173,"Tadamanoor",1318));

        fc12= new ArrayList<mandal>();
        fc12.add(new mandal(1," null",0));
        fc12.add(new mandal(183,"Akwanchaguda",1363));
        fc12.add(new mandal(183,"Borpatla",1354));
        fc12.add(new mandal(183,"Chintalcheru",1359));
        fc12.add(new mandal(183,"Cheekumaddur",1339));
        fc12.add(new mandal(183,"Devalpalle",1348));
       // fc12.add(new mandal(183,"Doultabad",1349));
        fc12.add(new mandal(183,"Doultabad",1334));
      //  fc12.add(new mandal(183,"Doultabad",1335));
      //  fc12.add(new mandal(183,"Doultabad",1336));
        fc12.add(new mandal(183,"Devalpally",1330));
        fc12.add(new mandal(183,"Govindarajupally",1327));
        fc12.add(new mandal(183,"Govindarajupalle",1343));
        fc12.add(new mandal(183,"Gundla Machnur",1333));
        fc12.add(new mandal(183,"Gundla Machanur",1356));
        fc12.add(new mandal(183,"Hathnoora",1346));
        fc12.add(new mandal(183,"Kasala",1329));
        fc12.add(new mandal(183,"Kasal",1347));
        fc12.add(new mandal(183,"Kodapak",1361));
        fc12.add(new mandal(183,"Koniyal",1340));
        fc12.add(new mandal(183,"Madhura",1342));
        fc12.add(new mandal(183,"Mangapur",1350));
        fc12.add(new mandal(183,"Nasthipur",1331));
        fc12.add(new mandal(183,"Naguldevpalle",1344));
        fc12.add(new mandal(183,"Naguldevpally",1328));
        fc12.add(new mandal(183,"Nastipur",1351));
        fc12.add(new mandal(183,"Palpanoor",1332));
        fc12.add(new mandal(183,"Panyal",1341));
        fc12.add(new mandal(183,"Palapnoor",1355));
        fc12.add(new mandal(183,"Reddikhanapur",1353));
        fc12.add(new mandal(183,"Royyapalle",1362));
        fc12.add(new mandal(183,"Seri Sirpura",1337));
        fc12.add(new mandal(183,"Seri Sirpuram (DP)",1326));
        fc12.add(new mandal(183,"Sirpuram",1338));
        fc12.add(new mandal(183,"Sadullanagar",1358));
        fc12.add(new mandal(183,"Sikandarpur",1345));
        fc12.add(new mandal(183,"Taherkhanpet",1352));
        fc12.add(new mandal(183,"Turkal Khanapur",1357));
        fc12.add(new mandal(183,"Yellammaguda",1360));



        fc13= new ArrayList<mandal>();
        fc13.add(new mandal(1," null",0));
        fc13.add(new mandal(184,"Ankenpally",1368));
        fc13.add(new mandal(184,"Aroor",1369));
        fc13.add(new mandal(184,"Ankanpalle",1380));
        fc13.add(new mandal(184,"Arur",1381));
        fc13.add(new mandal(184,"Babilgaon",1377));
        fc13.add(new mandal(184,"Etigaddasangam",1373));
        fc13.add(new mandal(184,"Enkepally",1367));
        fc13.add(new mandal(184,"Enkepalle",1378));
        fc13.add(new mandal(184,"Ishratabad",1379));
        fc13.add(new mandal(184,"Kolkur",1375));
        fc13.add(new mandal(184,"Kambalpalle",1384));
        fc13.add(new mandal(184,"Maddikunta",1582));
        fc13.add(new mandal(184,"Melgirpet",1370));
        fc13.add(new mandal(184,"Malapahad",1372));
        fc13.add(new mandal(184,"Machireddipalle",1376));
        fc13.add(new mandal(184,"Machireddipally",1365));
        fc13.add(new mandal(184,"Milgirpet",1382));
        fc13.add(new mandal(184,"Nagulpally",1366));
        fc13.add(new mandal(184,"Nandikandi",1388));
        fc13.add(new mandal(184,"Pottipally",1364));
        fc13.add(new mandal(184,"Pottipalle",1374));
        fc13.add(new mandal(184,"Regenthal",1386));
        fc13.add(new mandal(184,"Sadashivpet",1581));
        fc13.add(new mandal(184,"Siddapur [Rural]",1387));
        fc13.add(new mandal(184,"Thangadpally",1371));
        fc13.add(new mandal(184,"Thangadpalle",1383));
        fc13.add(new mandal(184,"Veltur",1389));
        fc13.add(new mandal(184,"Yawapur",1385));




        fc14= new ArrayList<mandal>();
        fc14.add(new mandal(1," null",0));
        fc14.add(new mandal(185,"Aliabad",1391));
      //  fc14.add(new mandal(185,"Aliabad",1403));
        fc14.add(new mandal(185,"Chi.Konapur",1399));
        fc14.add(new mandal(185,"Gunthapalle",1398));
        fc14.add(new mandal(185,"Gadimalkapuram",1402));
        fc14.add(new mandal(185,"Goplaram (Kurd)",1394));
        fc14.add(new mandal(185,"Garakurthi",1390));
        fc14.add(new mandal(185,"Haridaspur",1396));
        fc14.add(new mandal(185,"Kutubshapet",1393));
        fc14.add(new mandal(185,"Mansanipalle",1400));
        fc14.add(new mandal(185,"Munidevunipalle",1401));
        fc14.add(new mandal(185,"Machepalle",1395));
        fc14.add(new mandal(185,"Terpole",1397));
        fc14.add(new mandal(185,"Togurpalle",1392));

        fc15= new ArrayList<mandal>();
        fc15.add(new mandal(1," null",0));
        fc15.add(new mandal(186,"Angadipet",1424));
        fc15.add(new mandal(186,"Chintalpally",1405));
       // fc15.add(new mandal(186,"Chimnapur",1426));
        fc15.add(new mandal(186,"Chintalpalle",1411));
        fc15.add(new mandal(186,"Edthanur",1417));
        fc15.add(new mandal(186,"Fasalwadi",1415));
        fc15.add(new mandal(186,"Irigipalle",1410));
        fc15.add(new mandal(186,"Ismailkhanpet",1416));
        fc15.add(new mandal(186,"Irigipally",1404));
        fc15.add(new mandal(186,"Kalwakunta",1409));
        fc15.add(new mandal(186,"Kalabgoor",1412));
        fc15.add(new mandal(186,"Kothlapur",1420));
       // fc15.add(new mandal(186,"Kothlapur",1422));
       // fc15.add(new mandal(186,"Kothlapur",1423));
        fc15.add(new mandal(186,"Kulabgoor",1414));
        fc15.add(new mandal(186,"Mohd.Shapur",1418));
        fc15.add(new mandal(186,"Pothreddipalle",1419));
        fc15.add(new mandal(186,"Pothreddipally",1408));
        fc15.add(new mandal(186,"Sangareddy (M )",1407));
        fc15.add(new mandal(186,"Tadlapally",1406));
        fc15.add(new mandal(186,"Tadlapalle",1413));
        fc15.add(new mandal(186,"Utharpalle",1421));
        fc15.add(new mandal(186,"Yeddumailaram",1425));



        fc16= new ArrayList<mandal>();
        fc16.add(new mandal(1," null",0));
        fc16.add(new mandal(187,"Amdoor",1439));
        fc16.add(new mandal(187,"Bonthapalle",1434));
        fc16.add(new mandal(187,"Chetlapotharam",1446));
        fc16.add(new mandal(187,"Gaddapotharam",1431));
        fc16.add(new mandal(187,"Gotla",1437));
        fc16.add(new mandal(187,"Gaddipotharam",1447));
        fc16.add(new mandal(187,"Kankunta",1433));
        fc16.add(new mandal(187,"Kodakanchi",1441));
        fc16.add(new mandal(187,"Khazipally",1429));
        fc16.add(new mandal(187,"Kistaipally",1430));
        fc16.add(new mandal(187,"Khazipalle",1444));
        fc16.add(new mandal(187,"Kistaipalle",1445));
        fc16.add(new mandal(187,"Mangampet",1448));
       // fc16.add(new mandal(187,"Mangampet",1436));
        fc16.add(new mandal(187,"Nallapalle",1432));
        fc16.add(new mandal(187,"Nalthur",1443));
        fc16.add(new mandal(187,"Ootla",1449));
        fc16.add(new mandal(187,"Palem  (DP)",1427));
        fc16.add(new mandal(187,"Puttaguda",1442));
        fc16.add(new mandal(187,"Solakpalle",1438));
        fc16.add(new mandal(187,"Solakpally",1428));
        fc16.add(new mandal(187,"Sivanagar",1440));
        fc16.add(new mandal(187,"Vailal",1435));


        fc17= new ArrayList<mandal>();
        fc17.add(new mandal(1," null",0));
        fc17.add(new mandal(188,"Bachuguda",1460));
        fc17.add(new mandal(188,"Bhanur",1463));
        fc17.add(new mandal(188,"Bandal Guda",1470));
        fc17.add(new mandal(188,"Biramguda",1471));
        fc17.add(new mandal(188,"Chinnakanjerla",1453));
      /// fc17.add(new mandal(188,"Inole",1468));
       // fc17.add(new mandal(188,"Inole",1469));
        fc17.add(new mandal(188,"Isnapur",1472));
        fc17.add(new mandal(188,"Indresham",1458));
        fc17.add(new mandal(188,"Inole",1459));
        fc17.add(new mandal(188,"Krishtareddipet",1456));
      //  fc17.add(new mandal(188,"Kyasaram",1462));
        fc17.add(new mandal(188,"Kardanur",1465));
        fc17.add(new mandal(188,"Kyasaram",1451));
        fc17.add(new mandal(188,"Lakdaram",1452));
        fc17.add(new mandal(188,"Muthangi",1466));
        fc17.add(new mandal(188,"Patancheru",1450));
        fc17.add(new mandal(188,"Peddakanjerla",1454));
        fc17.add(new mandal(188,"Pashamylaram",1461));
        fc17.add(new mandal(188,"Patighanpur",1464));
        fc17.add(new mandal(188,"Rameshwar Banda",1457));
        fc17.add(new mandal(188,"Rendlagadda",1467));
        fc17.add(new mandal(188,"Wadakpalle",1455));

        fc18= new ArrayList<mandal>();
        fc18.add(new mandal(1," null",0));
        fc18.add(new mandal(189,"Edulnagulapally",1475));
        fc18.add(new mandal(189,"Edulanagulapalle",1478));
        fc18.add(new mandal(189,"Kachireddipally",1474));
        fc18.add(new mandal(189,"Kollur",1579));
        fc18.add(new mandal(189,"Manmole (DP)",1476));
        fc18.add(new mandal(189,"Osman Nagar",1479));
        fc18.add(new mandal(189,"Tellapur",1480));
        fc18.add(new mandal(189,"Velmala",1473));
        fc18.add(new mandal(189,"Velmula",1477));



        fc19= new ArrayList<mandal>();
        fc19.add(new mandal(1," null",0));
        fc19.add(new mandal(588,"Arutla",1486));
       // fc19.add(new mandal(588,"Arutla",1496));
        fc19.add(new mandal(588,"Chidruppa",1487));
        fc19.add(new mandal(588,"Cheriyal",1495));
        fc19.add(new mandal(588,"Erdanoor",1481));
        fc19.add(new mandal(588,"Eddumailaram",1485));
        fc19.add(new mandal(588,"Indrakaran",1494));
        fc19.add(new mandal(588,"Julkal",1493));
        fc19.add(new mandal(588,"Kashipur",1483));
        fc19.add(new mandal(588,"Koulampet",1489));
        fc19.add(new mandal(588,"Kandi",1488));
        fc19.add(new mandal(588,"Kalvemula",1491));
       //  fc19.add(new mandal(588,"Mamidipally",1482));
        fc19.add(new mandal(588,"Makthaalloor",1490));
        fc19.add(new mandal(588,"Topgonda",1492));
        fc19.add(new mandal(588,"Utharpally",1484));


        fc20= new ArrayList<mandal>();
        fc20.add(new mandal(1," null",0));
        fc20.add(new mandal(589,"Kishtareddipet",1498));
        fc20.add(new mandal(589,"Patelguda",1500));
        //fc20.add(new mandal(589,"Patelguda",1501));
        //fc20.add(new mandal(589,"Patelguda",1502));
        fc20.add(new mandal(589,"Renlagadda  (DP)",1499));
        fc20.add(new mandal(589,"Wadakpally",1497));

        fc21= new ArrayList<mandal>();
        fc21.add(new mandal(1," null",0));
        fc21.add(new mandal(590,"Bonthapally",1505));
        fc21.add(new mandal(590,"Domadugu",1508));
        fc21.add(new mandal(590,"Gummadidala",1507));
        fc21.add(new mandal(590,"Kanukunta",1504));
        fc21.add(new mandal(590,"Nallavally",1503));
        fc21.add(new mandal(590,"Pyaranagar",1506));

        fc22= new ArrayList<mandal>();
        fc22.add(new mandal(1," null",0));
        fc22.add(new mandal(591,"Buddaipally",1512));
        fc22.add(new mandal(591,"Bhoothkur",1514));
        fc22.add(new mandal(591,"Bijilipur",1526));
      //  fc22.add(new mandal(591,"Bijilipur",1521));
        fc22.add(new mandal(591,"Devnoor",1513));
        fc22.add(new mandal(591,"Dudiyal",1524));
        fc22.add(new mandal(591,"Gorrekal",1518));
        fc22.add(new mandal(591,"Gowtapoor",1519));
        fc22.add(new mandal(591,"Khadirabad",1516));
        fc22.add(new mandal(591,"Marvelly",1522));
        fc22.add(new mandal(591,"Medikunda",1523));
        fc22.add(new mandal(591,"Nirjipala",1525));
        fc22.add(new mandal(591,"Nagulapally",1511));
        fc22.add(new mandal(591,"Pothulaboguda",1517));
        fc22.add(new mandal(591,"Shahednagar @ Ghatpally",1510));
        fc22.add(new mandal(591,"Palvatla",1520));
        fc22.add(new mandal(591,"Usrikpally",1515));
        fc22.add(new mandal(591,"Vatpally",1509));


        fc23= new ArrayList<mandal>();
        fc23.add(new mandal(1," null",0));
        fc23.add(new mandal(592,"Bhusareddipally",1537));
        fc23.add(new mandal(592,"Beloor",1528));
        fc23.add(new mandal(592,"Bodapally",1534));
        fc23.add(new mandal(592,"Chinna Loni",1539));
        fc23.add(new mandal(592,"Chinnachelmeda",1541));
        fc23.add(new mandal(592,"Chilapally",1527));
        fc23.add(new mandal(592,"Garlapally",1531));
        fc23.add(new mandal(592,"Hydlapoor",1540));
        fc23.add(new mandal(592,"Kallapally",1529));
        fc23.add(new mandal(592,"Makthakyasaram",1530));
        fc23.add(new mandal(592,"Mallikarjunpally",1536));
       // fc23.add(new mandal(592,"Mansanpally",1533));
        fc23.add(new mandal(592,"Pedda Loni",1538));
        fc23.add(new mandal(592,"Pedda Chelmeda",1542));
        fc23.add(new mandal(592,"Thakkallapally",1535));
        fc23.add(new mandal(592,"Tatipally",1532));



        fc24= new ArrayList<mandal>();
        fc24.add(new mandal(1," null",0));
        fc24.add(new mandal(593,"Auranganagar",1553));
        fc24.add(new mandal(593,"Asadgunj",1551));
        fc24.add(new mandal(593,"Dhanasiri",1552));
        fc24.add(new mandal(593,"Gopanpally",1544));
        fc24.add(new mandal(593,"Godgarpally (Patti Dhanasiri)",1545));
        fc24.add(new mandal(593,"Gudpally",1547));
        fc24.add(new mandal(593,"Gousabad",1554));
        fc24.add(new mandal(593,"Ippepally",1548));
        fc24.add(new mandal(593,"KhanJamalapur",1546));
        fc24.add(new mandal(593,"Mogudampally",1543));
        fc24.add(new mandal(593,"Madgi",1550));
        fc24.add(new mandal(593,"Raipally [ Patti Dhanasiri ]",1549));


        fc25= new ArrayList<mandal>();
        fc25.add(new mandal(1," null",0));
        fc25.add(new mandal(594,"Bokkasgaon",1563));
        fc25.add(new mandal(594,"Chimal Pahad",1560));
        fc25.add(new mandal(594,"Goudgaon [K]",1559));
        fc25.add(new mandal(594,"Gardegaon",1556));
        fc25.add(new mandal(594,"Kadpal",1567));
        fc25.add(new mandal(594,"Mubarakpoor",1562));
        fc25.add(new mandal(594,"Pochapoor",1564));
        fc25.add(new mandal(594,"Sultanabad",1555));
       // fc25.add(new mandal(594,"Sultanabad",1565));
        fc25.add(new mandal(594,"Sirgapoor",1566));
        fc25.add(new mandal(594,"Ujjalampahad",1561));
        fc25.add(new mandal(594,"Wasar",1557));
        fc25.add(new mandal(594,"Wangdhal",1558));


        fc35=new ArrayList<mandal>();
        fc35.add(new mandal(1," null",0));
        fc35.add(new mandal(595,"Audathpur",1569));
        fc35.add(new mandal(595,"Goudgaon (Janwada)",1568));
        fc35.add(new mandal(595,"Karsgutti",1571));
        fc35.add(new mandal(595,"Kharamungi",1574));
        fc35.add(new mandal(595,"Morgi",1575));
        fc35.add(new mandal(595,"Mukthapur",1577));
       // fc35.add(new mandal(595,"Mukthapur",1578));
        fc35.add(new mandal(595,"Nagalgidda",1572));
        fc35.add(new mandal(595,"Pusalpahad",1576));
        fc35.add(new mandal(595,"Shikarkhana",1573));
        fc35.add(new mandal(595,"Yesgi",1570));


        mandal = (Spinner)findViewById(R.id.mandal);
        village = (Spinner)findViewById(R.id.village);
        complaintto = (Spinner)findViewById(R.id.complaintto);
        comp = new ArrayList<String>();
        c1 = new ArrayList<String>();
        c2 = new ArrayList<String>();
        c3 = new ArrayList<String>();
        c4 = new ArrayList<String>();
        c5 = new ArrayList<String>();
        c6 = new ArrayList<String>();
        c7 = new ArrayList<String>();
        c8 = new ArrayList<String>();
        c9 = new ArrayList<String>();
        c10 = new ArrayList<String>();
        c11 = new ArrayList<String>();
        c12 = new ArrayList<String>();
        c13 = new ArrayList<String>();
        c14 = new ArrayList<String>();
        c15 = new ArrayList<String>();
        c16 = new ArrayList<String>();
        c17 = new ArrayList<String>();
        c18 = new ArrayList<String>();
        c19 = new ArrayList<String>();
        c20= new ArrayList<String>();
        c21 = new ArrayList<String>();
        c22 = new ArrayList<String>();
        c23= new ArrayList<String>();
        c24= new ArrayList<String>();
        c34= new ArrayList<String>();
        c35= new ArrayList<String>();
        categories = new ArrayList<String>();

        categories.add("--Select--");
        categories.add("Ameenpur");
        categories.add("Andole");
        categories.add("Gummadidala");
        categories.add("Hathnoora");
        categories.add("Jharasangam");
        categories.add("Jinnaram");
        categories.add("Kalher");
        categories.add("Kandi");
        categories.add("Kangti");
        categories.add("Kohir");
        categories.add("Kondapur");
        categories.add("Manoor");
        categories.add("Mogudampally");
        categories.add("Munipally");
        categories.add("Nagalgidda");
        categories.add("Narayankhed");
        categories.add("Nyalkal");
        categories.add("Patancheru");
        categories.add("Pulkal");
        categories.add("Raikode");
        categories.add("Ramachandrapuram");
        categories.add("Sadasivpet");
        categories.add("Sangareddy");
        categories.add("Sirgapoor");
        categories.add("Vatpally");
        categories.add("Zahirabad");

        // Creating adapter for spinner
        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mandal.setAdapter(dataAdapter);
        c1.add("--select--");
        c1.add("Babulgaon");
        c1.add("Borgi");
        c1.add("Bheemra");
        c1.add("Chowkanpalle");
        c1.add("Chapta [B]");
        c1.add("Chapta [Khurd]");
        c1.add("Degulawadi");
        c1.add("Damaragiddi [Panchamahal]");
        c1.add("Bhanswada");
        c1.add("Enkemori");
        c1.add("Gajulpahad");
        c1.add("Jamgikhurd");
        c1.add("Jamgiburg");
        c1.add("Kangti");
        c1.add("Murkunjal");
        c1.add("Nagoor [K]");
        c1.add("Nagoor [B]");
       // c1.add("Potpalle");
        c1.add("Ramathirath");
        c1.add("Rasole");
        c1.add("Sidhangarga");
        c1.add("Sukalteerth");
        c1.add("Tadkal");
        c1.add("Turkwadgaon");
        c1.add("Valmoor");
        c1.add("Yampalle");

        c2.add("--select--");
        c2.add("Athimail");
        c2.add("Badalgaon");
        c2.add("Bellapur");
        c2.add("Borancha");
        c2.add("Dosapalle");
        c2.add("Davvur");
        c2.add("Dudhagonda");
        c2.add("Danvar");
        c2.add("Errakipalle");
        c2.add("Enekpalle");
        c2.add("Gatlingampalle");
        c2.add("Gondagaon");
        c2.add("Keswar");
        c2.add("Mavinelli");
        c2.add("Mugdumpur");
        c2.add("Maikode");
        c2.add("Manoor");
        c2.add("Nadigadda Hukrana");
        c2.add("Pulkurthy");
       // c2.add("Raipalle");
        c2.add("Shari Damargidda");
        c2.add("Shelgera");
        c2.add("Yarriboguda");
        c2.add("Thornal");
        c2.add("Tumnoor");
        c2.add("Utpalle");
        c2.add("Usirkapalle");
        c2.add("Yelgoi");
       // c2.add("Yelgoi");

        c3.add("--select--");
        c3.add("Abenda");
        c3.add("Anthwar");
      //  c3.add("Anthwar");
       // c3.add("Anthwar");
        c3.add("Bhanapur");
        c3.add("Chandkhanpally");
        c3.add("Chaptakhadeem");
        c3.add("Chandkhanpalle");
        c3.add("Gadthihokrana");
        c3.add("Gadthi Hokrana");
        c3.add("Hangarga [K]");
        c3.add("Hangarga [B]");
        c3.add("Hanmanthraopet");

        c3.add("Juzalpur");
        c3.add("Kamjipoor");
        c3.add("Mansoorpur");

        c3.add("Madhavar");
        c3.add("Namalimet");
        c3.add("Narasapur");
        c3.add("Narayankhed");
       // c3.add("Narasapur");
        c3.add("Paidpally");
        c3.add("Panchagaon");
        c3.add("Ryakal");
        c3.add("Ryalamadugu");
        c3.add("Rudrar");
        c3.add("Sanjivanrao Pet");
        c3.add("Sathagaon");


        c4.add("--select--");
        c4.add("Bachepalle");
        c4.add("Fathepoor");
        c4.add("Gosaipalle");
        c4.add("Kalher");
        c4.add("Krishnapoor");
        c4.add("Khanapur (Kadeem)");
        c4.add("Khanapoor (B)");
        c4.add("Masan Palle");
        c4.add("Mahadev Palle");
        c4.add("Mardi");
        c4.add("Mungepalle");
        c4.add("Malharpoor");
        c4.add("Nagdhar");
        c4.add("Ramreddi Pet");
        c4.add("Raparthy");

        c5.add("--select--");
        c5.add("Auranganagar (Patti Hasnabad)");
        c5.add("Chimnapur (DP)");
        c5.add("Chimnapur");
        c5.add("Hudavandapur @ Narayanpalle");
        c5.add("Hulgera");
        c5.add("Itkepally");
        c5.add("Itkepalle");
        c5.add("Indoor");
       // c5.add("Indoor");
        c5.add("Jamga (Khurd)");
        c5.add("Jamalpur (DP)");
        c5.add("Jamgi (Khurd)");
        c5.add("Khanjamalpur");
        c5.add("Karchal");
        c5.add("Kushnoor");
       // c5.add("Matoor");
      //  c5.add("Matoor");
       // c5.add("Matoor");
       // c5.add("Mustafapur");
        c5.add("Matoor");
        c5.add("Mahbathpur");
        c5.add("Moratga");
        c5.add("Mamidipally");
        c5.add("Mustafapur");
        c5.add("Nallampally");
        c5.add("Nallampalle");
        c5.add("Nagwar");
        c5.add("Peapalpally");
        c5.add("Pampad");
        c5.add("Peapalpalle");
        c5.add("Raipalle [Patti Karchal]");
        c5.add("Sirur");
        c5.add("Shamshuddinpur");
        c5.add("Yousufpur");

        c6.add("--select--");
        c6.add("Ameerabad");
        c6.add("Atnur");
        c6.add("Basanthpur");
        c6.add("Chingepally");
        c6.add("Chalki");
        c6.add("Chingepalle");
        c6.add("Cheekurthi");
        c6.add("Dappur");
        c6.add("Gunjetti");
        c6.add("Gangwar");
        c6.add("Ganjoti");
      //  c6.add("Gangwar");
        c6.add("Husselli");
        c6.add("Hadnur");
        c6.add("Kakijanwada");
        c6.add("Kalbemal");
        c6.add("Khaleelpur[M]");
        c6.add("Murthujapur");
        c6.add("Mamidgi");
        c6.add("Mungi");
        c6.add("Metalkunta");
        c6.add("Mirzapur[B]{");
        c6.add("Mariampur");
        c6.add("Mirjapur[N]");
        c6.add("Malgi");
        c6.add("Malkanpahad");
        c6.add("Naimatabad");
        c6.add("Nyalkal");
        //c6.add("Nyalkal");
        c6.add("Namtabad");
        c6.add("Raghavapur");
        c6.add("Ramtheerth");
        c6.add("Rejinthal");
        c6.add("Shamshallapur");
        c6.add("Shamshullapur");
        c6.add("Tatpally");
        c6.add("Tatpalle");
        c6.add("Tekur");
        c6.add("Waddi");


        c7.add("--select--");
        c7.add("Anegunta");
        c7.add("Algole");
        c7.add("Buchnelli");
        c7.add("Burdipahad");
        c7.add("Chinna Hyderabad");
        c7.add("Chiragpally");
        c7.add("Chiragpalle");
        c7.add("Didgi");
        c7.add("Govindpur");
        c7.add("Godegarpalle (Patti Dhanasiri)");
        c7.add("Gudpalle");
        c7.add("Hothi [B]");
        c7.add("Hyderabad");
        c7.add("Hothi [K]");
        c7.add("Huggelli");
        c7.add("Ippepalle");
        c7.add("Kothur [B]");
        c7.add("Kasimpur");
        c7.add("Khan Jamalapur");
        c7.add("Malkapur [Jadi]");
        c7.add("Malchelma");
        c7.add("Mogdampalle");
        c7.add("Pasthapur");
        c7.add("Pastapur");
        c7.add("Raipalle [ P.D.]");
        c7.add("Rachannapet");
        c7.add("Ranjole");
        c7.add("Raipalle");
        c7.add("Raipally patti Digwal");
        c7.add("Sathwar");
        c7.add("Shekapur");
        c7.add("Satwar");
        c7.add("Shaikapur");
        c7.add("Sarjaraopet");
        c7.add("Tumkunta");
        c7.add("Tamadpalle");
        c7.add("Thammadpalli");
        c7.add("Zahirabad (M )");
        c7.add("Zahirabad (Rural)");

        c8.add("--select--");
        c8.add("Bilalpur");
        c8.add("Badampet");
        c8.add("Chinthalghat");
        c8.add("Digwal");
        c8.add("Godgar Palle");
        c8.add("Gurjuwada");
        c8.add("Kavelli");
        c8.add("Kothur Patti Digwal");
        c8.add("Kothur Pattikohir");
        c8.add("Kohir");
        c8.add("Madri");
        c8.add("Maniyarpalle");
        c8.add("Machreddipalle");
        c8.add("Nagireddy Palle");
        c8.add("Pothireddypally");
        c8.add("Pothireddypalle");
        c8.add("Paidigummal");
        c8.add("Picharagad");
        c8.add("Parsapalle");
        c8.add("Rajnelli");
        c8.add("Sajjapur");
        c8.add("Siddapur Pattikohir");

        c9.add("--select--");
        c9.add("Boppanpally");
        c9.add("Bidekanna");
        c9.add("Bopanpalle");
        c9.add("Chilkepalle");
        //c9.add("Chilapally");
        c9.add("Chilkepally");
        c9.add("Chilemamidi");
        c9.add("Chilepalle");
        c9.add("Devarampally");
        c9.add("Edakulapally");
        c9.add("Edulapally");
        c9.add("Edakulapalle");
        c9.add("Guntamarpally");
        c9.add("Giniyarpally");
        c9.add("Giniyarpalle");
        c9.add("Guntamarpalle");
        c9.add("Jeerlapally");
        c9.add("Jharasangam");
        c9.add("Jeerlapalle");
        c9.add("Junegaon");
        c9.add("Kappad");
        c9.add("Kamalpalle");
        c9.add("Kamalpally");
        c9.add("Krishnapur");
        c9.add("Kuppanagar");
        c9.add("Kakkerwada");
        c9.add("Medapally");
        c9.add("Machnoor");
        c9.add("Potpally");
        c9.add("Potpalle");
        c9.add("Pyarawaram");
        c9.add("Rampur (DP)");
        c9.add("Sangam [Khurd]");
        c9.add("Tummanpalle");
        c9.add("Tummanpally");
        c9.add("Vanampally");
        c9.add("Vanampalle");


        c10.add("--select--");
        c10.add("Bommareddygudem");
        c10.add("Chakriyal");
        c10.add("Choutkur");
        c10.add("Esojipet");
        c10.add("Gangojipet");
        c10.add("Gangulur");
        c10.add("Hunnapur (DP)");
        c10.add("Hunnapur");
        c10.add("Kodur");
        c10.add("Lakshmisagar");
        c10.add("Muddaipet");
        //c10.add("Muddaipet");
        c10.add("Mudimanik");
        c10.add("Posanipalle");
        c10.add("Pulkal");
       // c10.add("Pulkal");
        c10.add("Peddareddipet");
        c10.add("Sureddi Itkyal");
        c10.add("Seripeddareddipet (DP)");
        c10.add("Seripeddareddipet");
        c10.add("Singoor");
        c10.add("Seriramreddiguda");
        c10.add("Sarafpalle");
        c10.add("Sivampet");
        c10.add("Taddanpalle");
        c10.add("Vendikole");
        c10.add("Venkatakistapur @ Angadipet");

        c11.add("--select--");
        c11.add("Almaipet");
        c11.add("Aksanpalle");
        c11.add("Andole");
        c11.add("Aksanpally");
        c11.add("Brahmanpally");
        c11.add("chintakunta");
        c11.add("Dakoor");
        c11.add("Danampalle");
        c11.add("Danampally");
        c11.add("Kansanpalle");
        c11.add("Kansanpally");
        c11.add("Kichanapally");
        c11.add("Kondareddipally");
        c11.add("Kichanapalle");
        c11.add("Kodekal");
        c11.add("Mansanpally");
        c11.add("Masanpally");
        c11.add("Neradigunta");
        c11.add("Nadlapur");
        c11.add("Neerdigunta");
      //  c11.add("Pothireddypally");
       // c11.add("Rollapahad");
        c11.add("Ramsanpally");
        c11.add("Ramsanpalle");
        c11.add("Rollapahad");
        c11.add("Sangupet");
        c11.add("Saibanpet");
        c11.add("Serimallareddipally");
        c11.add("Serimallareddipalle");
        c11.add("Talema");
        c11.add("Telelma");
        c11.add("Tadamanoor");

        c12.add("--select--");
        c12.add("Akwanchaguda");
        c12.add("Borpatla");
        c12.add("Chintalcheru");
        c12.add("Cheekumaddur");
        c12.add("Devalpalle");
        //c12.add("Doultabad");
        c12.add("Doultabad");
        //c12.add("Doultabad");
       // c12.add("Doultabad");
        c12.add("Devalpally");
        c12.add("Govindarajupally");
        c12.add("Govindarajupalle");
        c12.add("Gundla Machnur");
        c12.add("Gundla Machanur");
        c12.add("Hathnoora");
        c12.add("Kasala");
        c12.add("Kasal");
        c12.add("Kodapak");
        c12.add("Koniyal");
        c12.add("Madhura");
        c12.add("Mangapur");
        c12.add("Nasthipur");
        c12.add("Naguldevpalle");
        c12.add("Naguldevpally");
        c12.add("Nastipur");
        c12.add("Palpanoor");
        c12.add("Panyal");
        c12.add("Palapnoor");
        c12.add("Reddikhanapur");
        c12.add("Royyapalle");
        c12.add("Seri Sirpura");
        c12.add("Seri Sirpuram (DP)");
        c12.add("Sirpuram");
        c12.add("Sadullanagar");
        c12.add("Sikandarpur");
        c12.add("Taherkhanpet");
        c12.add("Turkal Khanapur");
        c12.add("Yellammaguda");



        c13.add("--select--");
        c13.add("Ankenpally");
        c13.add("Aroor");
        c13.add("Ankanpalle");
        c13.add("Arur");
        c13.add("Babilgaon");
        c13.add("Etigaddasangam");
        c13.add("Enkepally");
        c13.add("Enkepalle");
        c13.add("Ishratabad");
        c13.add("Kolkur");
        c13.add("Kambalpalle");
        c13.add("Maddikunta");
        c13.add("Melgirpet");
        c13.add("Malapahad");
        c13.add("Machireddipalle");
        c13.add("Machireddipally");
        c13.add("Milgirpet");
        c13.add("Nagulpally");
        c13.add("Nandikandi");
        c13.add("Pottipally");
        c13.add("Pottipalle");
        c13.add("Regenthal");
        c13.add("Sadashivpet");
        c13.add("Siddapur [Rural]");
        c13.add("Thangadpally");
        c13.add("Thangadpalle");
        c13.add("Veltur");
        c13.add("Yawapur");




        c14.add("--select--");
        c14.add("Aliabad");
       // c14.add("Aliabad");
        c14.add("Chi.Konapur");
        c14.add("Gunthapalle");
        c14.add("Gadimalkapuram");
        c14.add("Goplaram (Kurd)");
        c14.add("Garakurthi");
        c14.add("Haridaspur");
        c14.add("Kutubshapet");
        c14.add("Mansanipalle");
        c14.add("Munidevunipalle");
        c14.add("Machepalle");
        c14.add("Terpole");
        c14.add("Togurpalle");


        c15.add("--select--");
        c15.add("Angadipet");
        c15.add("Chintalpally");
       // c15.add("Chimnapur");
        c15.add("Chintalpalle");
        c15.add("Edthanur");
        c15.add("Fasalwadi");
        c15.add("Irigipalle");
        c15.add("Ismailkhanpet");
        c15.add("Irigipally");
        c15.add("Kalwakunta");
        c15.add("Kalabgoor");
        c15.add("Kothlapur");
       // c15.add("Kothlapur");
       // c15.add("Kothlapur");
        c15.add("Kulabgoor");
        c15.add("Mohd.Shapur");
        c15.add("Pothreddipalle");
        c15.add("Pothreddipally");
        c15.add("Sangareddy (M )");
        c15.add("Tadlapally");
        c15.add("Tadlapalle");
        c15.add("Utharpalle");
        c15.add("Yeddumailaram");



        c16.add("--select--");
        c16.add("Amdoor");
        c16.add("Bonthapalle");
        c16.add("Chetlapotharam");
        c16.add("Gaddapotharam");
        c16.add("Gotla");
        c16.add("Gaddipotharam");
        c16.add("Kankunta");
        c16.add("Kodakanchi");
        c16.add("Khazipally");
        c16.add("Kistaipally");
        c16.add("Khazipalle");
        c16.add("Kistaipalle");
        c16.add("Mangampet");
       // c16.add("Mangampet");
        c16.add("Nallapalle");
        c16.add("Nalthur");
        c16.add("Ootla");
        c16.add("Palem  (DP)");
        c16.add("Puttaguda");
        c16.add("Solakpalle");
        c16.add("Solakpally");
        c16.add("Sivanagar");
        c16.add("Vailal");


        c17.add("--select--");
        c17.add("Bachuguda");
        c17.add("Bhanur");
        c17.add("Bandal Guda");
        c17.add("Biramguda");
        c17.add("Chinnakanjerla");
     //   c17.add("Inole");
      //  c17.add("Inole");
        c17.add("Isnapur");
        c17.add("Indresham");
        c17.add("Inole");
        c17.add("Krishtareddipet");
       // c17.add("Kyasaram");
        c17.add("Kardanur");
        c17.add("Kyasaram");
        c17.add("Lakdaram");
        c17.add("Muthangi");
        c17.add("Patancheru");
        c17.add("Peddakanjerla");
        c17.add("Pashamylaram");
        c17.add("Patighanpur");
        c17.add("Rameshwar Banda");
        c17.add("Rendlagadda");
        c17.add("Wadakpalle");


        c18.add("--select--");
        c18.add("Edulanagulapalle");
        c18.add("Edulnagulapally");
        c18.add("Kachireddipally");
        c18.add("Kollur");
        c18.add("Manmole (DP)");
        c18.add("Osman Nagar");
       // c18.add("Ramachandrapuram");
        c18.add("Tellapur");
        c18.add("Velmala");
        c18.add("Velmula");

        c19.add("--select--");
        c19.add("Arutla");
      //  c19.add("Arutla");
        c19.add("Chidruppa");
        c19.add("Cheriyal");
        c19.add("Erdanoor");
        c19.add("Eddumailaram");
        c19.add("Indrakaran");
        c19.add("Julkal");
        c19.add("Kashipur");
        c19.add("Koulampet");
        c19.add("Kandi");
        c19.add("Kalvemula");
       // c19.add("Mamidipally");
        c19.add("Makthaalloor");
        c19.add("Topgonda");
        c19.add("Utharpally");


        c20.add("--select--");
        c20.add("Kishtareddipet");
        c20.add("Patelguda");
       // c20.add("Patelguda");
       // c20.add("Patelguda");
        c20.add("Renlagadda  (DP)");
        c20.add("Wadakpally");


        c21.add("--select--");
        c21.add("Bonthapally");
        c21.add("Domadugu");
        c21.add("Gummadidala");
        c21.add("Kanukunta");
        c21.add("Nallavally");
        c21.add("Pyaranagar");

        c22.add("--select--");
        c22.add("Buddaipally");
        c22.add("Bhoothkur");
        c22.add("Bijilipur");
      //  c22.add("Bijilipur");
        c22.add("Devnoor");
        c22.add("Dudiyal");
        c22.add("Gorrekal");
        c22.add("Gowtapoor");
        c22.add("Khadirabad");
        c22.add("Marvelly");
        c22.add("Medikunda");
        c22.add("Nirjipala");
        c22.add("Nagulapally");
        c22.add("Pothulaboguda");
        c22.add("Shahednagar @ Ghatpally");
        c22.add("Palvatla");
        c22.add("Usrikpally");
        c22.add("Vatpally");


        c23.add("--select--");
        c23.add("Bhusareddipally");
        c23.add("Beloor");
        c23.add("Bodapally");
        c23.add("Chinna Loni");
        c23.add("Chinnachelmeda");
        c23.add("Chilapally");
        c23.add("Garlapally");
        c23.add("Hydlapoor");
        c23.add("Kallapally");
        c23.add("Makthakyasaram");
        c23.add("Mallikarjunpally");
       // c23.add("Mansanpally");
        c23.add("Pedda Loni");
        c23.add("Pedda Chelmeda");
        c23.add("Thakkallapally");
        c23.add("Tatipally");



        c24.add("--select--");
        c24.add("Auranganagar");
        c24.add("Asadgunj");
        c24.add("Dhanasiri");
        c24.add("Gopanpally");
        c24.add("Godgarpally (Patti Dhanasiri)");
        c24.add("Gudpally");
        c24.add("Gousabad");
        c24.add("Ippepally");
        c24.add("KhanJamalapur");
        c24.add("Mogudampally");
        c24.add("Madgi");
        c24.add("Raipally [ Patti Dhanasiri ]");

        c34.add("--select--");
        c34.add("Bokkasgaon");
        c34.add("Chimal Pahad");
        c34.add("Goudgaon [K]");
        c34.add("Gardegaon");
        c34.add("Kadpal");
        c34.add("Mubarakpoor");
        c34.add("Pochapoor");
        c34.add("Sultanabad");
       // c34.add("Sultanabad");
        c34.add("Sirgapoor");
        c34.add("Ujjalampahad");
        c34.add("Wasar");
        c34.add("Wangdhal");

        c35.add("--select--");
        c35.add("Audathpur");
        c35.add("Goudgaon (Janwada)");
        c35.add("Karsgutti");
        c35.add("Kharamungi");
        c35.add("Morgi");
        c35.add("Mukthapur");
        //c35.add("Mukthapur");
        c35.add("Nagalgidda");
        c35.add("Pusalpahad");
        c35.add("Shikarkhana");
        c35.add("Yesgi");

        adc1= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c1);
        adc1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c2);
        adc2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc3= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c3);
        adc3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc4= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c4);
        adc4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc5= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c5);
        adc5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc6= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c6);
        adc6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc7= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c7);
        adc7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc8= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c8);
        adc8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc9= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c9);
        adc9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc10= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c10);
        adc10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc11= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c11);
        adc11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc12= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c12);
        adc12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc13= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c13);
        adc13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc14= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c14);
        adc14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc15= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c15);
        adc15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc16= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c16);
        adc16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc17= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c17);
        adc17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc18= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c18);
        adc18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc19= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c19);
        adc19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc20= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c20);
        adc20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc21= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c21);
        adc21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc22= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c22);
        adc22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc23= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,c23);
        adc23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adc24= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,c24);
        adc24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc34= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,c34);
        adc34.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adc35= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,c35);
        adc35.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad145= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad145);
        ad145.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad172= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad172);
        ad172.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad173= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad173);
        ad173.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad183= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad183);
        ad183.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad184= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad184);
        ad184.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad185= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad185);
        ad185.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad186= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad186);
        ad186.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad187= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad187);
        ad187.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad188= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad188);
        ad188.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad189= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad189);
        ad189.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


         ad588= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad588);
        ad588.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad146= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad146);
        ad146.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad589= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad589);
        ad589.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad590= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad590);
        ad590.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad591= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad591);
        ad591.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad592= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad592);
        ad592.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ad593= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad593);
        ad593.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad594= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad594);
        ad594.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad595= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad595);
        ad595.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad147= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad147);
        ad147.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad148= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad148);
        ad148.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad166= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad166);
        ad166.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad167= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad167);
        ad167.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad168= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad168);
        ad168.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad169= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad169);
        ad169.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ad170= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cad170);
        ad170.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        mandal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             //   Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                mymandal=adapterView.getItemAtPosition(i).toString();
                mandalname=adapterView.getItemAtPosition(i).toString();
                switch (adapterView.getItemAtPosition(i).toString()){

                    case "Ameenpur":
                        village.setAdapter(adc20);
                        break;
                    case "Andole":
                        village.setAdapter(adc11);
                        break;
                    case "Gummadidala":
                        village.setAdapter(adc21);
                        break;
                    case "Hathnoora":
                        village.setAdapter(adc12);
                        break;
                    case "Jharasangam":
                        village.setAdapter(adc9);
                        break;
                    case "Jinnaram":
                        village.setAdapter(adc16);
                        break;
                    case "Kalher":
                        village.setAdapter(adc4);
                        break;
                    case "Kandi":
                        village.setAdapter(adc19);
                        break;
                    case "Kangti":
                        village.setAdapter(adc1);
                        break;
                    case "Kohir":
                        village.setAdapter(adc8);
                        break;
                    case "Kondapur":
                        village.setAdapter(adc14);
                        break;
                    case "Manoor":
                        village.setAdapter(adc2);
                        break;
                    case "Mogudampally":
                        village.setAdapter(adc24);
                        break;

                    case "Munipally":
                        village.setAdapter(adc23);
                        break;
                    case "Nagalgidda":
                        village.setAdapter(adc35);
                        break;
                    case "Narayankhed":
                        village.setAdapter(adc3);
                        break;
                    case "Nyalkal":
                        village.setAdapter(adc6);
                        break;
                    case "Patancheru":
                        village.setAdapter(adc17);
                        break;
                    case "Pulkal":
                        village.setAdapter(adc10);
                        break;
                    case "Raikode":
                        village.setAdapter(adc5);
                        break;
                    case "Ramachandrapuram":
                        village.setAdapter(adc18);
                        break;
                    case "Sadasivpet":
                        village.setAdapter(adc13);
                        break;
                    case "Sangareddy":
                        village.setAdapter(adc15);
                        break;
                    case "Sirgapoor":
                        village.setAdapter(adc34);
                        break;
                    case "Vatpally":
                        village.setAdapter(adc22);
                        break;
                    case "Zahirabad":
                        village.setAdapter(adc7);
                        break;

                }
              /*  if (adapterView.getItemAtPosition(i).toString().equals("Ameenpur")){
                    village.setAdapter(adc1);
                }else {
                    village.setAdapter(dataAdapter);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
              //  Toast.makeText(getBaseContext(),adapterView.toString(),Toast.LENGTH_SHORT).show();
                mymandal="";
            }
        });

        village.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String x , y;
                switch (mymandal){
                    case "Ameenpur":
                        mandalcode = fc20.get(i).getMandalid();
                        villagecode = fc20.get(i).getVillageid();
                        villagename = fc20.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                        break;
                    case "Andole":
                        mandalcode = fc11.get(i).getMandalid();
                        villagecode = fc11.get(i).getVillageid();
                        villagename = fc11.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();
                     /*   String x1 , y1;
                        x1 = String.valueOf(villagecode);y1= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x1+ "  "+y1,Toast.LENGTH_SHORT).show();*/
                        break;
                    case "Gummadidala":
                        mandalcode = fc21.get(i).getMandalid();
                        villagecode = fc21.get(i).getVillageid();
                        villagename = fc21.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        break;
                    case "Hathnoora":
                        mandalcode = fc12.get(i).getMandalid();
                        villagecode = fc12.get(i).getVillageid();
                        villagename = fc12.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /* x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Jharasangam":
                        mandalcode = fc9.get(i).getMandalid();
                        villagecode = fc9.get(i).getVillageid();
                        villagename = fc9.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                      /*  x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Jinnaram":
                        mandalcode = fc16.get(i).getMandalid();
                        villagecode = fc16.get(i).getVillageid();
                        villagename = fc16.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;

                    case "Kalher":
                        mandalcode = fc4.get(i).getMandalid();
                        villagecode = fc4.get(i).getVillageid();
                        villagename = fc4.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                        /*x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                       // myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Kandi":
                        mandalcode = fc19.get(i).getMandalid();
                        villagecode = fc19.get(i).getVillageid();
                        villagename = fc19.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Kangti":
                        mandalcode = fc1.get(i).getMandalid();
                        villagecode = fc1.get(i).getVillageid();
                        villagename = fc1.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                      /*  x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                      //  myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Kohir":
                        mandalcode = fc8.get(i).getMandalid();
                        villagecode = fc8.get(i).getVillageid();
                        villagename = fc8.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                    /*    x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                      //  myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Kondapur":
                        mandalcode = fc14.get(i).getMandalid();
                        villagecode = fc14.get(i).getVillageid();
                        villagename = fc14.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                    /*    x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                      //  myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Manoor":
                        mandalcode = fc2.get(i).getMandalid();
                        villagecode = fc2.get(i).getVillageid();
                        villagename = fc2.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                      /*  x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Mogudampally":
                        mandalcode = fc24.get(i).getMandalid();
                        villagecode = fc24.get(i).getVillageid();
                        villagename = fc24.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                 /*       x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                      //  myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Munipally":
                        mandalcode = fc23.get(i).getMandalid();
                        villagecode = fc23.get(i).getVillageid();
                        villagename = fc23.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                   /*     x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        break;
                    case "Nagalgidda":
                        mandalcode = fc35.get(i).getMandalid();
                        villagecode = fc35.get(i).getVillageid();
                        villagename = fc35.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                   /*     x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();*/
                     //   myvillage = adapterView.getItemAtPosition(i).toString();
                        //  Toast.makeText(getBaseContext(),fc15.get(i).getVillagename().toString(),Toast.LENGTH_SHORT).show();
                        break;

                    case "Narayankhed":
                        mandalcode = fc3.get(i).getMandalid();
                        villagecode = fc3.get(i).getVillageid();
                        villagename = fc3.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                    /*    x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        break;
                    case "Nyalkal":
                        mandalcode = fc6.get(i).getMandalid();
                        villagecode = fc6.get(i).getVillageid();
                        villagename = fc6.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                   /*     x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        break;

                    case "Patancheru":
                        mandalcode = fc17.get(i).getMandalid();
                        villagecode = fc17.get(i).getVillageid();
                        villagename = fc17.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //  village.setAdapter(adc17);
                        break;
                    case "Pulkal":
                        mandalcode = fc10.get(i).getMandalid();
                        villagecode = fc10.get(i).getVillageid();
                        villagename = fc10.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                    /*    x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        // village.setAdapter(adc10);
                        break;
                    case "Raikode":
                        mandalcode = fc5.get(i).getMandalid();
                        villagecode = fc5.get(i).getVillageid();
                        villagename = fc5.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                   /*     x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //  village.setAdapter(adc5);
                        break;
                    case "Ramachandrapuram":
                        mandalcode = fc18.get(i).getMandalid();
                        villagecode = fc18.get(i).getVillageid();
                        villagename = fc18.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                  /*      x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //   village.setAdapter(adc18);
                        break;
                    case "Sadasivpet":
                        mandalcode = fc13.get(i).getMandalid();
                        villagecode = fc13.get(i).getVillageid();
                        villagename = fc13.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                  /*      x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //  village.setAdapter(adc13);
                        break;
                    case "Sangareddy":
                        mandalcode = fc15.get(i).getMandalid();
                        villagecode = fc15.get(i).getVillageid();
                        villagename = fc15.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        // village.setAdapter(adc15);
                        break;
                    case "Sirgapoor":
                        mandalcode = fc25.get(i).getMandalid();
                        villagecode = fc25.get(i).getVillageid();
                        villagename = fc25.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                     /*   String xx1 , yy1;
                        xx1 = String.valueOf(villagecode);yy1= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),xx1+ "  "+yy1,Toast.LENGTH_SHORT).show();*/
                        break;
                    case "Vatpally":
                        mandalcode = fc22.get(i).getMandalid();
                        villagecode = fc22.get(i).getVillageid();
                        villagename = fc22.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

              /*          x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //  village.setAdapter(adc22);
                        break;
                    case "Zahirabad":
                        mandalcode = fc7.get(i).getMandalid();
                        villagecode = fc7.get(i).getVillageid();
                        villagename = fc7.get(i).getVillagename();
                        myvillage = adapterView.getItemAtPosition(i).toString();

                   /*     x = String.valueOf(villagecode);y= String.valueOf(mandalcode);
                        Toast.makeText(getBaseContext(),x+ " "+y,Toast.LENGTH_SHORT).show();
                        myvillage = adapterView.getItemAtPosition(i).toString();*/
                        //  village.setAdapter(adc7);
                        break;
                /*    categories.add("Patancheru");
                    categories.add("Pulkal");
                    categories.add("Raikode");
                    categories.add("Ramachandrapuram");
                    categories.add("Sadasivpet");
                    categories.add("Sangareddy");
                    categories.add("Sirgapoor");
                    categories.add("Vatpally");
                    categories.add("Zahirabad");*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myvillage = "";
            }
        });
        comp.add("Collector");
        comp.add("JC");
        comp.add("DRO");
        comp.add("DRDO");
        comp.add("AD HORTICULTURE");

        off= new ArrayList<String>();
        off.add("--Select--");
        off.add("District Level Officers");
        off.add("Mandal Level Officers");

        dataAdapter2= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, off);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        complaintto.setAdapter(dataAdapter2);

        dataAdapter3= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cn);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataAdapter4= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cntwo);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        complaintto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mycomplaintto =adapterView.getItemAtPosition(i).toString();
                myid = i;
               //  Toast.makeText(getBaseContext(),mycomplaintto.toString(),Toast.LENGTH_SHORT).show();

                if (mycomplaintto.equals("District Level Officers")){
                    selectofficer="District";
                    complaintto.setAdapter(dataAdapter3);

                    //   selectmyofficer=adapterView.getItemAtPosition(i).toString();
                    //  validations.MyAlertBox(MainActivity.this,selectmyofficer);
                }else if (mycomplaintto.equals("Mandal Level Officers")){
                    selectofficer="Mandal";
                 //   complaintto.setAdapter(dataAdapter4);
                    switch (mymandal){
                        case "Ameenpur":
                            myrdo="ad589";
                            complaintto.setAdapter(ad589);
                            break;
                        case "Andole":
                            myrdo="ad173";
                            complaintto.setAdapter(ad173);
                            break;
                        case "Gummadidala":
                            myrdo="ad590";
                            complaintto.setAdapter(ad590);
                            break;
                        case "Hathnoora":
                            myrdo="ad183";
                            complaintto.setAdapter(ad183);
                            break;
                        case "Jharasangam":
                            myrdo="ad170";
                            complaintto.setAdapter(ad170);
                            break;
                        case "Jinnaram":
                            myrdo="ad187";
                            complaintto.setAdapter(ad187);
                             break;
                         case "Kalher":
                             myrdo="ad148";
                             complaintto.setAdapter(ad148);
                            break;
                        case "Kandi":
                            myrdo="ad588";
                            complaintto.setAdapter(ad588);
                            break;
                        case "Kangti":
                            myrdo="ad145";
                            complaintto.setAdapter(ad145);

                            break;
                        case "Kohir":
                            myrdo="ad169";
                            complaintto.setAdapter(ad169);

                            break;
                        case "Kondapur":
                            myrdo="ad185";
                            complaintto.setAdapter(ad185);

                            break;
                        case "Manoor":
                            myrdo="ad146";
                            complaintto.setAdapter(ad146);

                            break;
                        case "Mogudampally":
                            myrdo="ad593";
                            complaintto.setAdapter(ad593);
                            break;
                        case "Munipally":
                            myrdo="ad592";
                            complaintto.setAdapter(ad592);
                            break;
                        case "Nagalgidda":
                            myrdo="ad595";
                            complaintto.setAdapter(ad595);
                            break;
                        case "Narayankhed":
                            myrdo="ad147";
                            complaintto.setAdapter(ad147);
                            break;
                        case "Nyalkal":
                            myrdo="ad167";
                            complaintto.setAdapter(ad167);
                            break;
                        case "Patancheru":
                            myrdo="ad188";
                            complaintto.setAdapter(ad188);
                            break;
                        case "Pulkal":
                            myrdo="ad172";
                            complaintto.setAdapter(ad172);
                            break;
                        case "Raikode":
                            myrdo="ad166";
                            complaintto.setAdapter(ad166);
                            break;
                        case "Ramachandrapuram":
                            myrdo="ad189";
                            complaintto.setAdapter(ad189);
                            break;
                        case "Sadasivpet":
                            myrdo="ad184";
                            complaintto.setAdapter(ad184);
                            break;
                        case "Sangareddy":
                            myrdo="ad186";
                            complaintto.setAdapter(ad186);
                            break;

                        case "Sirgapoor":
                            myrdo="ad594";
                            complaintto.setAdapter(ad594);
                            break;
                        case "Vatpally":
                            myrdo="ad591";
                            complaintto.setAdapter(ad591);
                            break;
                        case "Zahirabad":
                            myrdo="ad168";
                            complaintto.setAdapter(ad168);
                               break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mycomplaintto="";
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }
        choosefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showFileChooser();
                //  file1choose.setText("one file selected");
                //selectImage();
                selectmyimages = "selectmyimage1";
                selectImage();
            }
        });

        myimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectmyimages = "selectmyimage2";
                selectImage();
            }
        });
        choosefilethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectmyimages = "selectmyimage3";
                selectImage();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  new MainActivity.JSONParse("srinivasu@gmail.com").execute();
                if(mymandal.equals("--Select--")){
                    validations.MyAlertBox(MainActivity.this,"select Mandal ");
                }else if (myvillage.equals("--select--")){
                    validations.MyAlertBox(MainActivity.this,"select village ");
                }
                else if (et_name.getText().toString().length()==0){
                    validations.MyAlertBox(MainActivity.this,"Enter name");
                }else if(et_mobile.getText().length()==0 || et_mobile.getText().length()<10){
                    validations.MyAlertBox(MainActivity.this,"Enter mobile no should be 10 digits");

                }else if(et_aadhar.getText().length()>0 &&  et_aadhar.getText().length() <12){
                    validations.MyAlertBox(MainActivity.this,"Enter aadhar no should be 12 digits");
                }
                else if (et_address.getText().length()==0){
                    validations.MyAlertBox(MainActivity.this,"Enter address");

                }
                else if (mycomplaintto.equals("--Select--")){
                    validations.MyAlertBox(MainActivity.this,"Please select grievence officer");
                }
                else if(et_description.getText().length() == 0){
                    validations.MyAlertBox(MainActivity.this,"Enter description");

                }/* else if (myimage.getDrawable() == null){
                    validations.MyAlertBox(MainActivity.this,"Please upload photo");
                }*/
                else {
                    if (et_email.getText().length()==0){
                        dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.loading);

                        uploadImage(mandalcode,villagecode,et_name.getText().toString()
                                ,et_mobile.getText().toString(),et_aadhar.getText().toString(),et_address.getText().toString()
                                ,et_email.getText().toString(),mycomplaintto,et_description.getText().toString());
                      /*  new MainActivity.JSONParsemyimagethree(mandalcode,mandalcode,et_name.getText().toString()
                                ,et_mobile.getText().toString(),et_aadhar.getText().toString(),et_address.getText().toString()
                                ,et_email.getText().toString(),mycomplaintto,et_description.getText().toString()).execute();*/

                    }else {
                        uploadImage(mandalcode,villagecode,et_name.getText().toString()
                                ,et_mobile.getText().toString(),et_aadhar.getText().toString(),et_address.getText().toString()
                                ,et_email.getText().toString(),mycomplaintto,et_description.getText().toString());
                     /*   new MainActivity.JSONParsemyimagethree(mandalcode,mandalcode,et_name.getText().toString()
                                ,et_mobile.getText().toString(),et_aadhar.getText().toString(),et_address.getText().toString()
                                ,et_email.getText().toString(),mycomplaintto,et_description.getText().toString()).execute();*/

                    }
                }
                //new MainActivity.JSONParse("srinivasu@gmail.com").execute();
            }
        });
    }

  /*  private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        Log.d("myimage",encodedImage.toString());
        return encodedImage;
    }

    private void uploadImage(final int mymandal, final int myvillage, final String et_name, final String et_mobile, final String et_aadhar,
                             final String et_address, final String et_email, final String mycomplaintto, final String et_description){

        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  loading.dismiss();
                String myid;
                String x = String.valueOf(s.length());
                Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_LONG).show();

/*
                try {
                    JSONObject obj = new JSONObject(s.toString());
                    JSONArray jsonMainArr = obj.getJSONArray("result");
                    for (int i = 0;i<jsonMainArr.length();i++){
                        JSONObject ss = jsonMainArr.getJSONObject(i);
                        if (ss.getString("CreateEvent").toString().equals("success")){
                            List<String> toEmailList = Arrays.asList(et_email.toString().split("\\s*,\\s*"));
                            new SendMailTask(MainActivity.this).execute("sangareddygrievance@gmail.com",
                                    "sangareddygrievance123", toEmailList,
                                    "e-Sangareddy Grievance Monitoring System ", "<!DOCTYPE html>\n" +
                                            "<html>\n" +
                                            "<body style=\"color:#000000\">\n" +
                                            " \n" +
                                            "<p ><b>Dear  Applicant  ,</b></p>\n" +
                                            " \n" +
                                            "<p>Your Grievance was successfully registered vide Registration No : </p>\n" +ss.getString("createevent_id").toString()+
                                            "<p> It will be forwarded to concerned Officer for redressal. You can know the status of your grievance by clicking on Know Your Grievance Status option. </p>\n" +
                                            "<p> Regards </p><p>Grievance Cell</p><p>Sangareddy District.</p>\n" +
                                            " \n" +
                                            "</body>\n" +
                                            "</html>\n"
                            );
                            Intent aa = new Intent(MainActivity.this,GenerateNumber.class);
                            aa.putExtra("number",ss.getString("createevent_id").toString());
                            startActivity(aa);
                            finish();
                        }
                    }
                 } catch (JSONException e) {
                    e.printStackTrace();
                }
*/

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
                if (selectofficer.equals("District")){
                    ForwarTo = String.valueOf(cc.get(myid).getId());
                    //Toast.makeText(getBaseContext(),x,Toast.LENGTH_SHORT).show();
                }else if (selectofficer.equals("Mandal")){
                    //ForwarTo = String.valueOf(cctwo.get(myid).getId());
                    switch (myrdo){
                        case "ad589":
                            ForwarTo = String.valueOf(ccad589.get(myid).getId());
                            break;
                        case "ad173":
                            ForwarTo = String.valueOf(ccad173.get(myid).getId());
                             break;
                        case "ad590":
                            ForwarTo = String.valueOf(ccad590.get(myid).getId());
                             break;
                        case "ad183":
                             ForwarTo = String.valueOf(ccad183.get(myid).getId());
                             break;
                        case "ad170":
                            ForwarTo = String.valueOf(ccad170.get(myid).getId());
                             break;
                        case "ad187":
                            ForwarTo = String.valueOf(ccad187.get(myid).getId());
                              break;
                        case "ad148":
                            ForwarTo = String.valueOf(ccad148.get(myid).getId());
                             break;
                        case "ad588":
                            ForwarTo = String.valueOf(ccad588.get(myid).getId());
                            break;
                        case "ad145":
                            ForwarTo = String.valueOf(ccad145.get(myid).getId());
                             break;
                        case "ad169":
                            ForwarTo = String.valueOf(ccad169.get(myid).getId());
                            break;
                        case "ad185":
                            ForwarTo = String.valueOf(ccad185.get(myid).getId());
                            break;
                        case "ad146":
                            ForwarTo = String.valueOf(ccad146.get(myid).getId());
                            break;
                        case "ad593":
                            ForwarTo = String.valueOf(ccad593.get(myid).getId());
                            break;
                        case "ad592":
                            ForwarTo = String.valueOf(ccad592.get(myid).getId());
                            break;
                        case "ad595":
                            ForwarTo = String.valueOf(ccad595.get(myid).getId());
                            break;
                        case "ad147":
                            ForwarTo = String.valueOf(ccad147.get(myid).getId());
                            break;
                        case "ad167":
                            ForwarTo = String.valueOf(ccad167.get(myid).getId());
                            break;
                        case "ad188":
                            ForwarTo = String.valueOf(ccad188.get(myid).getId());
                            break;
                        case "ad172":
                            ForwarTo = String.valueOf(ccad172.get(myid).getId());
                            break;
                        case "ad166":
                            ForwarTo = String.valueOf(ccad166.get(myid).getId());
                            break;
                        case "ad189":
                            ForwarTo = String.valueOf(ccad189.get(myid).getId());
                            break;
                        case "ad184":
                            ForwarTo = String.valueOf(ccad184.get(myid).getId());
                            break;
                        case "ad186":
                            ForwarTo = String.valueOf(ccad186.get(myid).getId());
                            break;
                        case "ad594":
                            ForwarTo = String.valueOf(ccad594.get(myid).getId());
                            break;
                        case "ad591":
                            ForwarTo = String.valueOf(ccad591.get(myid).getId());
                            break;
                        case "ad168":
                            ForwarTo = String.valueOf(ccad168.get(myid).getId());
                            break;
                    }
                }

                String filename = selectmyimages;
                HashMap<String,String> data = new HashMap<>();

               // data.put(UPLOAD_KEY, uploadImage);
                data.put("ForwarTo",ForwarTo);
                data.put("ApplicationName",et_name);
                data.put("intmandalId", String.valueOf(mymandal));
                data.put("intVillageid", String.valueOf(myvillage));
                data.put("Address",et_address.toString());
                data.put("mobileNo",et_mobile.toString());
                data.put("Email",et_email.toString());
                data.put("Aadhar_No",et_aadhar.toString());
                data.put("GrievanceDescription",et_description.toString());

                data.put("Created_by","12");
                data.put("GrievancePhotoFile1","sdfasdf");
              /*  uploadImage1 = "data:image/gif;base64,R0lGODlhPQBEAPeoAJosM//AwO/AwHVYZ/z595kzAP/s7P+goOXMv8+fhw/v739/f+8PD98fH/8mJl+fn/9ZWb8/PzWlwv///6wWGbImAPgTEMImIN9gUFCEm/gDALULDN8PAD6atYdCTX9gUNKlj8wZAKUsAOzZz+UMAOsJAP/Z2ccMDA8PD/95eX5NWvsJCOVNQPtfX/8zM8+QePLl38MGBr8JCP+zs9myn/8GBqwpAP/GxgwJCPny78lzYLgjAJ8vAP9fX/+MjMUcAN8zM/9wcM8ZGcATEL+QePdZWf/29uc/P9cmJu9MTDImIN+/r7+/vz8/P8VNQGNugV8AAF9fX8swMNgTAFlDOICAgPNSUnNWSMQ5MBAQEJE3QPIGAM9AQMqGcG9vb6MhJsEdGM8vLx8fH98AANIWAMuQeL8fABkTEPPQ0OM5OSYdGFl5jo+Pj/+pqcsTE78wMFNGQLYmID4dGPvd3UBAQJmTkP+8vH9QUK+vr8ZWSHpzcJMmILdwcLOGcHRQUHxwcK9PT9DQ0O/v70w5MLypoG8wKOuwsP/g4P/Q0IcwKEswKMl8aJ9fX2xjdOtGRs/Pz+Dg4GImIP8gIH0sKEAwKKmTiKZ8aB/f39Wsl+LFt8dgUE9PT5x5aHBwcP+AgP+WltdgYMyZfyywz78AAAAAAAD///8AAP9mZv///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAKgALAAAAAA9AEQAAAj/AFEJHEiwoMGDCBMqXMiwocAbBww4nEhxoYkUpzJGrMixogkfGUNqlNixJEIDB0SqHGmyJSojM1bKZOmyop0gM3Oe2liTISKMOoPy7GnwY9CjIYcSRYm0aVKSLmE6nfq05QycVLPuhDrxBlCtYJUqNAq2bNWEBj6ZXRuyxZyDRtqwnXvkhACDV+euTeJm1Ki7A73qNWtFiF+/gA95Gly2CJLDhwEHMOUAAuOpLYDEgBxZ4GRTlC1fDnpkM+fOqD6DDj1aZpITp0dtGCDhr+fVuCu3zlg49ijaokTZTo27uG7Gjn2P+hI8+PDPERoUB318bWbfAJ5sUNFcuGRTYUqV/3ogfXp1rWlMc6awJjiAAd2fm4ogXjz56aypOoIde4OE5u/F9x199dlXnnGiHZWEYbGpsAEA3QXYnHwEFliKAgswgJ8LPeiUXGwedCAKABACCN+EA1pYIIYaFlcDhytd51sGAJbo3onOpajiihlO92KHGaUXGwWjUBChjSPiWJuOO/LYIm4v1tXfE6J4gCSJEZ7YgRYUNrkji9P55sF/ogxw5ZkSqIDaZBV6aSGYq/lGZplndkckZ98xoICbTcIJGQAZcNmdmUc210hs35nCyJ58fgmIKX5RQGOZowxaZwYA+JaoKQwswGijBV4C6SiTUmpphMspJx9unX4KaimjDv9aaXOEBteBqmuuxgEHoLX6Kqx+yXqqBANsgCtit4FWQAEkrNbpq7HSOmtwag5w57GrmlJBASEU18ADjUYb3ADTinIttsgSB1oJFfA63bduimuqKB1keqwUhoCSK374wbujvOSu4QG6UvxBRydcpKsav++Ca6G8A6Pr1x2kVMyHwsVxUALDq/krnrhPSOzXG1lUTIoffqGR7Goi2MAxbv6O2kEG56I7CSlRsEFKFVyovDJoIRTg7sugNRDGqCJzJgcKE0ywc0ELm6KBCCJo8DIPFeCWNGcyqNFE06ToAfV0HBRgxsvLThHn1oddQMrXj5DyAQgjEHSAJMWZwS3HPxT/QMbabI/iBCliMLEJKX2EEkomBAUCxRi42VDADxyTYDVogV+wSChqmKxEKCDAYFDFj4OmwbY7bDGdBhtrnTQYOigeChUmc1K3QTnAUfEgGFgAWt88hKA6aCRIXhxnQ1yg3BCayK44EWdkUQcBByEQChFXfCB776aQsG0BIlQgQgE8qO26X1h8cEUep8ngRBnOy74E9QgRgEAC8SvOfQkh7FDBDmS43PmGoIiKUUEGkMEC/PJHgxw0xH74yx/3XnaYRJgMB8obxQW6kL9QYEJ0FIFgByfIL7/IQAlvQwEpnAC7DtLNJCKUoO/w45c44GwCXiAFB/OXAATQryUxdN4LfFiwgjCNYg+kYMIEFkCKDs6PKAIJouyGWMS1FSKJOMRB/BoIxYJIUXFUxNwoIkEKPAgCBZSQHQ1A2EWDfDEUVLyADj5AChSIQW6gu10bE/JG2VnCZGfo4R4d0sdQoBAHhPjhIB94v/wRoRKQWGRHgrhGSQJxCS+0pCZbEhAAOw==";
                data.put("title","fasdfasdf");
                data.put("imageUrl",uploadImage1);
                data.put("userId","asdfas");*/

                System.out.print(data.toString());
               // String result = rh.sendPostRequest(UPLOAD_URL,data);
                String result = rh.sendPostRequest("http://104.236.126.68:5000/api/post/addPost",data);
                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

  /*  @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            //showFileChooser();
            selectImage();
        }

        if(v == buttonUpload){
            uploadImage();
        }

        if(v == buttonView){
            viewImage();
        }
    }*/

    private void viewImage() {
        startActivity(new Intent(this, ImageListView.class));
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                   /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);*/
                    userChoosenTask ="Take Photo";
                    cameraIntent();
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    /*Intent intent = new   Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
*/                  userChoosenTask ="Choose from Library";
                    galleryIntent();
                }
                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, REQUEST_CAMERA);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE){
                onSelectFromGalleryResult(data);
            }
            else if (requestCode == REQUEST_CAMERA){
                onCaptureImageResult(data);
            }
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

        Uri tempUri = getImageUri(MainActivity.this, thumbnail);

        // CALL THIS METHOD TO GET THE ACTUAL PATH
        File finalFile = new File(getRealPathFromURI(tempUri));
        bitmap =(Bitmap) data.getExtras().get("data");

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
        switch (selectmyimages){
            case "selectmyimage1":
               // uploadImage();
                // new MainActivity.JSONParsedoitfast(scaledBitmap,"one").execute();
                myimage.setMaxWidth(150);
                myimage.setMaxHeight(150);
                myimage.setImageBitmap(bitmap);
                scaledBitmap=bitmap;
                break;
            case "selectmyimage2":
              //  uploadImage();
                //   new MainActivity.JSONParsedoitfast(scaledBitmap2,"two").execute();
                myimagesettwo.setMaxWidth(150);
                myimagesettwo.setMaxHeight(150);
                myimagesettwo.setImageBitmap(bitmap);
                scaledBitmap2=bitmap;
                break;
            case "selectmyimage3":
             //   uploadImage();
                //  new MainActivity.JSONParsedoitfast(scaledBitmap3,"three").execute();
                myimagethree.setMaxWidth(150);
                myimagethree.setMaxHeight(150);
                myimagethree.setImageBitmap(bitmap);
                scaledBitmap3=bitmap;
                break;
        }

    }

    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
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
                switch (selectmyimages){
                    case "selectmyimage1":
                    //    uploadImage();
                        myimage.setMaxWidth(150);
                        myimage.setMaxHeight(150);
                       // new MainActivity.JSONParsedoitfast(scaledBitmap,"one").execute();
                        scaledBitmap=bitmap;
                        myimage.setImageBitmap(bitmap);
                        break;
                    case "selectmyimage2":
                    //    uploadImage();
                        myimagesettwo.setMaxWidth(150);
                        myimagesettwo.setMaxHeight(150);
                     //   new MainActivity.JSONParsedoitfast(scaledBitmap2,"two").execute();
                        scaledBitmap2=bitmap;
                        myimagesettwo.setImageBitmap(bitmap);
                        break;
                    case "selectmyimage3":
                    //    uploadImage();
                        myimagethree.setMaxWidth(150);
                        myimagethree.setMaxHeight(150);
                      //  new MainActivity.JSONParsedoitfast(scaledBitmap3,"three").execute();
                        scaledBitmap3=bitmap;
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

    private class JSONParsemyimagethree extends AsyncTask<String, String, JSONObject> {
        String email,password;
        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String mymandal,  myvillage,  et_name, et_mobile,  et_aadhar,
                et_address,  et_email,  mycomplaintto,  et_description;


        public JSONParsemyimagethree(int mymandal, int myvillage, String et_name,String et_mobile, String et_aadhar,
                                     String et_address, String et_email, String mycomplaintto, String et_description) {
            this.mymandal= String.valueOf(mymandal);
            this.myvillage= String.valueOf(myvillage);
            this.et_name=et_name; this.et_mobile=et_mobile;  this.et_aadhar=et_aadhar;
            this.et_address=et_address;  this.et_email=et_email;
            this.mycomplaintto=mycomplaintto;  this.et_description=et_description;

        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();
            Bitmap bitmap1 =scaledBitmap;
            Bitmap bitmap2 =scaledBitmap2;
            Bitmap bitmap3 =scaledBitmap3;
          /*  File f1 = new File(String.valueOf(scaledBitmap));
            File f2 = new File(String.valueOf(scaledBitmap2));
            File f3 = new File(String.valueOf(scaledBitmap3));*/

            String m = String.valueOf(mandalcode);
            String v = String.valueOf(villagecode);
            nameValuePairs.add(new BasicNameValuePair("mandal",m));
            nameValuePairs.add(new BasicNameValuePair("village",v));
            nameValuePairs.add(new BasicNameValuePair("name",et_name));
            nameValuePairs.add(new BasicNameValuePair("mobile",et_mobile));
            nameValuePairs.add(new BasicNameValuePair("aadhar",et_aadhar));
            nameValuePairs.add(new BasicNameValuePair("address",et_address));
            nameValuePairs.add(new BasicNameValuePair("email",et_email));
            nameValuePairs.add(new BasicNameValuePair("complainto",mycomplaintto));
            nameValuePairs.add(new BasicNameValuePair("description",et_description));

            nameValuePairs.add(new BasicNameValuePair("p_image",selectmyimage1));
            nameValuePairs.add(new BasicNameValuePair("myimage",selectmyimage2));
            nameValuePairs.add(new BasicNameValuePair("myded",selectmyimage3));
            nameValuePairs.add(new BasicNameValuePair("mandalname",mandalname));
            nameValuePairs.add(new BasicNameValuePair("villagename",villagename));
         /*   if (bitmap1!=null){
                nameValuePairs.add(new BasicNameValuePair("p_image",getStringImage(bitmap1)));
            }else {
                nameValuePairs.add(new BasicNameValuePair("p_image","alskdfj"));
            }
            if (bitmap2!=null){
                nameValuePairs.add(new BasicNameValuePair("myimage",getStringImage(bitmap2)));
            }else{
                nameValuePairs.add(new BasicNameValuePair("myimage","alskdfjl"));
            }
            if (bitmap3!=null){
                nameValuePairs.add(new BasicNameValuePair("myded",getStringImage(bitmap3)));
            }else{
                nameValuePairs.add(new BasicNameValuePair("myded","slkdjf"));
            }

            for (int i=0;i<nameValuePairs.size();i++){
                Log.d("value"+i,nameValuePairs.get(i).toString());
            }*/
            json = JSONParser.makeServiceCall("http://192.254.233.173/~rajeshch/PHP/govt/index/userpicvaluestwo",1,nameValuePairs);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                JSONObject obj = new JSONObject(json.toString());
                if(obj.getString("status").equals("success")){
                  //  dialog.show();
                   /* List<String> toEmailList = Arrays.asList(et_email.toString().split("\\s*,\\s*"));
                    new SendMailTask(MainActivity.this).execute("sangareddygrievance@gmail.com",
                            "sangareddygrievance123", toEmailList,
                            "DialYourCollector", "<html><body><p>Dear \n"+"Your Grievance was successfully registered vide Registration No "+obj.getString("users")+
                                    "It will be forwarded to concerned Officer for redressal. You can know the status of your grievance by clicking on Know Your Grievance Status option."
                    +"\n\nRegards\nGrievance Cell\nSangareddy District</p></body></html>");*/

                    List<String> toEmailList = Arrays.asList(et_email.toString().split("\\s*,\\s*"));
                    new SendMailTask(MainActivity.this).execute("sangareddygrievance@gmail.com",
                            "sangareddygrievance123", toEmailList,
                            "DialYourCollector", "<!DOCTYPE html>\n" +
                                    "<html>\n" +
                                    "<body style=\"color:#af7ac5\">\n" +
                                    " \n" +
                                    "<p ><b>Dear            ,</b></p>\n" +
                                    " \n" +
                                    "<p>Your Grievance was successfully registered vide Registration No : </p>\n" +obj.getString("users")+
                                    "<p> It will be forwarded to concerned Officer for redressal. You can know the status of your grievance by clicking on Know Your Grievance Status option. </p>\n" +
                                    "<p> Regards </p><p>Grievance Cell</p><p>Sangareddy District.</p>\n" +
                                    " \n" +
                                    "</body>\n" +
                                    "</html>\n"
                    );



                    Intent i = new Intent(MainActivity.this,GenerateNumber.class);
                    i.putExtra("number", obj.getString("users"));
                    startActivity(i);
                    finish();
                  //  validations.MyAlertBox(MainActivity.this,json.toString());
                }
            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
            }
        }
    }

    private class fssservice extends AsyncTask<String, String, JSONObject> {
        String email,password;
        private ProgressDialog pDialog;
        private ArrayList<NameValuePair> nameValuePairs;
        private JSONObject json;
        String mymandal,  myvillage,  et_name, et_mobile,  et_aadhar,
                et_address,  et_email,  mycomplaintto,  et_description;
        fssservice(){}

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... arg0) {
            nameValuePairs = new ArrayList<NameValuePair>();

         /*   nameValuePairs.add(new BasicNameValuePair("ForwarTo","23"));
            nameValuePairs.add(new BasicNameValuePair("ApplicationName","son"));
            nameValuePairs.add(new BasicNameValuePair("intmandalId","12"));
            nameValuePairs.add(new BasicNameValuePair("intVillageid","12"));
            nameValuePairs.add(new BasicNameValuePair("Address","asdfsdf"));
            nameValuePairs.add(new BasicNameValuePair("mobileNo","sdfasdd"));
            nameValuePairs.add(new BasicNameValuePair("Email","dsdff"));
            nameValuePairs.add(new BasicNameValuePair("Aadhar_No","234423423"));
            nameValuePairs.add(new BasicNameValuePair("GrievanceDescription","sdfasdf"));

            nameValuePairs.add(new BasicNameValuePair("Created_by","12"));
          *//*  nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile1","sdfasdf"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath1","sdfasdf"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile2","asdfas"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath2","asdfs"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath3","asdfsd"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile3","asdfsdf"));*//*

            json = JSONParser.makeServiceCall("http://209.105.239.132/SangareddyG/wsGrievanceServices.aspx",2,nameValuePairs);*/

            nameValuePairs.add(new BasicNameValuePair("ForwarTo","23"));
            nameValuePairs.add(new BasicNameValuePair("ApplicationName","son"));
            nameValuePairs.add(new BasicNameValuePair("intmandalId","12"));
            nameValuePairs.add(new BasicNameValuePair("intVillageid","12"));
            nameValuePairs.add(new BasicNameValuePair("Address","asdfsdf"));
            nameValuePairs.add(new BasicNameValuePair("mobileNo","sdfasdd"));
            nameValuePairs.add(new BasicNameValuePair("Email","dsdff"));
            nameValuePairs.add(new BasicNameValuePair("Aadhar_No","234423423"));
            nameValuePairs.add(new BasicNameValuePair("GrievanceDescription","sdfasdf"));

            nameValuePairs.add(new BasicNameValuePair("Created_by","12"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile1","sdfasdf"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath1","sdfasdf"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile2","asdfas"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath2","asdfs"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoPath3","asdfsd"));
            nameValuePairs.add(new BasicNameValuePair("GrievancePhotoFile3","asdfsdf"));

            json = JSONParser.makeServiceCall("http://209.105.239.132/SangareddyG/wsGrievanceServices.aspx",2,nameValuePairs);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                JSONObject obj = new JSONObject(json.toString());

            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
            }
        }
    }


}
