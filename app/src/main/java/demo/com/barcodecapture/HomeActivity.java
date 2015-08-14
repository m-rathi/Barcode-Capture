package demo.com.barcodecapture;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity implements View.OnClickListener {
private Button scanButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton=(Button)findViewById(R.id.button);

        scanButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
