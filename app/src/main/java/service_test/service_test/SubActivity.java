package service_test.service_test;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {
    final String LOG_TAG = "subLogs";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        TextView viewName  = (TextView)findViewById(R.id.textViewName);
        TextView viewDigit = (TextView)findViewById(R.id.textViewDigit);
        TextView viewParseString = (TextView)findViewById(R.id.textViewStringParcelable);
        TextView viewParseInteger = (TextView)findViewById(R.id.textViewIntegerParcelable);
        TextView viewSerializableString = (TextView)findViewById(R.id.textViewStringSerializable);
        TextView viewSerializableInteger = (TextView)findViewById(R.id.textViewIntegerSerializable);
        TextView viewGSON = (TextView)findViewById(R.id.textViewGSON);

        Bundle intentData = getIntent().getExtras();
        if(intentData==null){
            return;
        }
        else{
            String myname = intentData.getString("Name");
            int digit     = intentData.getInt("Digit");
            if (myname!=null){
                viewName.setText("Name: " +myname);
            }
            if (digit!=0){
                viewDigit.setText("Digit: " +String.valueOf(digit));
            }
        }
        //Parcelable
        DataParcelable parseData = getIntent().getParcelableExtra(DataParcelable.class.getCanonicalName());
        viewParseString.setText("String parcelable is: " + parseData.s);
        viewParseInteger.setText("Integer parcelable is: " + parseData.i);

        //Serializable
        DataSerializable serializableData = (DataSerializable) getIntent().getSerializableExtra(DataSerializable.class.getCanonicalName());
        viewSerializableString.setText("String serializable is: "+serializableData.getName());
        viewSerializableInteger.setText("Integer serializable is: "+serializableData.getId());

        //GSON
        String GSONdata = getIntent().getExtras().getString(DataGSON.class.getCanonicalName());
        Gson gson = new Gson();
        DataGSON objGSON = gson.fromJson(GSONdata, DataGSON.class);
        viewGSON.setText("GSON string is: " + objGSON.CustomerName);


    }


}
