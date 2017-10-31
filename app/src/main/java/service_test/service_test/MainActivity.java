package service_test.service_test;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;
import io.sentry.event.BreadcrumbBuilder;

public class MainActivity extends  AppCompatActivity {

    final String LOG_TAG = "myLogs";
    EditText digitData;
    EditText nameData;
    EditText stringParcelable;
    EditText integerParcelable;
    EditText stringSerializable;
    EditText integerSerializable;
    EditText stringGSON;

    //menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    //menu item callbacks
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return true;
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        //sentry initialisation+
        Context ctx = this.getApplicationContext();
        // Use the Sentry DSN (client key) from the Project Settings page on Sentry
        String sentryDsn = "https://3f0e061a18dc499cad98aa1788fb7485:735dbf8a2d354911af678d339118c6a5@sentry.io/236985";

        // Initialize the Sentry client
        Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));

        // Record a breadcrumb that will be sent with the next event(s)
        Sentry.record(
                new BreadcrumbBuilder().setMessage("User made an action").build()
        );
        //sentry initialisation-

        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }

    public void onClickStart(View v) {
        Intent sub = new Intent(this, SubActivity.class);
        digitData = (EditText)findViewById(R.id.dataDigit);
        nameData  = (EditText)findViewById(R.id.dataName);
        stringParcelable = (EditText)findViewById(R.id.dataStringParcelable);
        integerParcelable = (EditText)findViewById(R.id.dataIntegerParcelable);
        stringSerializable = (EditText)findViewById(R.id.dataStringSerializable);
        integerSerializable = (EditText)findViewById(R.id.dataIntegerSerializable);
        stringGSON = (EditText)findViewById(R.id.dataStringGSON);

        sub.putExtra("Name", nameData.getText().toString());
        try {
            sub.putExtra("Digit", Integer.parseInt(digitData.getText().toString()));
        } catch( NumberFormatException nfe){
            Toast.makeText(getApplicationContext(), "Could not parse digit into Integer",
                    Toast.LENGTH_LONG).show();
        }

        //Parcelable
        sub.putExtra(DataParcelable.class.getCanonicalName(),
                new DataParcelable(stringParcelable.getText().toString(),
                        Integer.parseInt(integerParcelable.getText().toString())));

        //Serializable
        sub.putExtra(DataSerializable.class.getCanonicalName(), new DataSerializable(Integer.parseInt(integerSerializable.getText().toString()),
                stringSerializable.getText().toString()));

        //GSON
        DataGSON dGson = new DataGSON(stringGSON.getText().toString());
        Gson gson = new Gson();
        String jsonString = gson.toJson(dGson);
        sub.putExtra(DataGSON.class.getCanonicalName(), jsonString);

        startActivity(sub);
    }

}
