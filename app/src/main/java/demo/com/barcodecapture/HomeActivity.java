package demo.com.barcodecapture;

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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {
    private Button scanButton, sendButton,addButton;
    String[] location, location1,location2;
    String barcode;
    int index;
    String mSelectedItem, mSelectedItem1,mSelectedItem2;


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


       TextView tv = (TextView) findViewById(R.id.barcode);
        tv.setText(getIntent().getStringExtra("mytext"));


        location = getResources().getStringArray(R.array.spinner3);
        Spinner s1 = (Spinner) findViewById(R.id.process);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_spinner_item, location);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index = arg0.getSelectedItemPosition();
                //OR you can also store selected item using below line.
                mSelectedItem = arg0.getSelectedItem().toString();
                // Toast.makeText(getBaseContext(), "You have selected " + location[index], Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        location1 = getResources().getStringArray(R.array.spinner2);
        Spinner s2 = (Spinner) findViewById(R.id.status);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.my_spinner_item, location1);
        s2.setAdapter(adapter3);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index = arg0.getSelectedItemPosition();
                //OR you can also store selected item using below line.
                mSelectedItem1 = arg0.getSelectedItem().toString();
                // Toast.makeText(getBaseContext(), "You have selected " + location1[index], Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        location2 = getResources().getStringArray(R.array.spinner1);
        Spinner s3 = (Spinner) findViewById(R.id.order);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.my_spinner_item, location2);
        s3.setAdapter(adapter4);
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                index = arg0.getSelectedItemPosition();
                //OR you can also store selected item using below line.
                mSelectedItem2 = arg0.getSelectedItem().toString();
                // Toast.makeText(getBaseContext(), "You have selected " + location1[index], Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //String barcode=getIntent().getStringExtra("mytext");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                // String barcode=tv.getText().toString();

                break;

            case R.id.send:
                // do your code
                // sendSMS("5556", location [index]);
                //OR you can also send sms using below code.
                sendSMS();
                break;

            case R.id.add:
                addTolist();
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

    protected void addTolist(){

        String barcodeArray []={barcode,barcode,"barcode"};
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, barcodeArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter2);
    }
    protected void sendSMS() {
        Log.i("Send SMS", "");
        //TextView tv = (TextView) findViewById(R.id.barcode);

        //String barcode = tv.getText().toString();


        TextView tv = (TextView) findViewById(R.id.barcode);

        String barcode = tv.getText().toString();

        String barcodeArray []={barcode,barcode,"barcode"};
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, barcodeArray);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter2);
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

