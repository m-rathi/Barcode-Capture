package demo.com.barcodecapture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class HomeActivity extends Activity implements View.OnClickListener,Serializable {
    private Button scanButton, sendButton,addButton;
    String[] location, location1,location2;
    String barcode;
    int index;
    String mSelectedItem, mSelectedItem1,mSelectedItem2;
    ArrayList<String> barcodes;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<String> barcodes = outState.getStringArrayList("barcodes");
        if(barcodes == null) {
            barcodes = new ArrayList<String>();
        }
        TextView tv = (TextView) findViewById(R.id.barcode);
        barcodes.add(tv.getText().toString());
        outState.putStringArrayList("barcodes", barcodes);
        super.onSaveInstanceState(outState);
    }



    //ListView lv = (ListView) findViewById(R.id.listView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = (Button) findViewById(R.id.button);
        scanButton.setOnClickListener(this);

        sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(this);

        addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();

        String value2 = bundle != null ? bundle.getString("BARCODE") : "12345";
        // ArrayList<String> barcodes = null;

        ArrayList<String> barcodes = null;

        if (!(savedInstanceState != null && savedInstanceState.getStringArrayList("barcodes") == null)) {
            barcodes = new ArrayList<String>();
            barcodes.add(value2);
        } else {
            barcodes = savedInstanceState.getStringArrayList("barcodes");
        }
        String bacodeList = "";
        for (String barcode : barcodes) {
            bacodeList += barcode;
            ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, barcodes);

            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter2);
        }}
//        TextView tv = (TextView) findViewById(R.id.barcode);
//        tv.setText(bacodeList);

    protected void addItemList() {
        TextView tv = (TextView) findViewById(R.id.barcode);
        if (isInputValid(tv)) {
            barcodes.add(0, tv.getText().toString());
            tv.setText("");

            adapter2.notifyDataSetChanged();

        }

    }

    protected boolean isInputValid(TextView tv) {
        // TODO Auto-generatd method stub
        if (tv.getText().toString().trim().length() < 1) {
            tv.setError("Please Enter Item");
            return false;
        } else {
            return true;
        }

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
addItemList();
                // String barcode=tv.getText().toString();

                break;

            case R.id.send:
                // do your code
                // sendSMS("5556", location [index]);
                //OR you can also send sms using below code.
                sendSMS();
                break;

            case R.id.add:
                //addTolist();
                // do your code
                break;

            default:
                break;
        }

    }

//    public void onClick(View v) {
//            sendSMS("5556", location [index]);
//            //OR you can also send sms using below code.
//            sendSMS("5556", mSelectedItem);
//        }

    //?sends an SMS message to another device?
//        private void sendSMS(String phoneNumber, String message)
//        {
//            SmsManager sms = SmsManager.getDefault();
//            sms.sendTextMessage(phoneNumber, null, message, null, null);
//        }
//    }
//

//    protected void addTolist(){
//
//
//        String barcodeArray []={barcode,barcode};
//        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, barcodeArray);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter2);
//
//
//
//
//    }
    protected void sendSMS() {
        Log.i("Send SMS", "");
        //TextView tv = (TextView) findViewById(R.id.barcode);

        //String barcode = tv.getText().toString();


        TextView tv = (TextView) findViewById(R.id.barcode);

        String barcode = tv.getText().toString();

//        String barcodeArray []={barcode,barcode,"barcode"};
//        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, barcodeArray);
//
//        ListView listView = (ListView) findViewById(R.id.listView);
//        listView.setAdapter(adapter2);
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String(""));
        if (barcode != null) {
            smsIntent.putExtra("sms_body","User:"+"Mayuri"+" "+"Customer:"+"Company"+""+"Order"+location2[index]+"Process:" + location[index] + " " + "Status:" + location1[index] + " " + "Serial No:" + barcode);
        }

        else{
           // Toast toast = Toast.makeText(getApplicationContext(), "scan data is not available ", Toast.LENGTH_LONG);


        }
        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(HomeActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}


//    @Override
//    public void onClick(View view) {
//        Intent i=new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(i);
//    }

