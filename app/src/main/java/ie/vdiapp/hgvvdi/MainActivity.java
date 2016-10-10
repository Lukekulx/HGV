package ie.vdiapp.hgvvdi;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.EntityIterator;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button mSend;
    private Button mClear;

    //Vehicle Details
    private EditText mvehicleReg;
    private EditText mmileage;
    private EditText mdriverName;

    private String vehicleReg;
    private String mileage;
    private String driverName;

    //In-Cab Checks
    private CheckBox mincab1;
    private CheckBox mincab2;
    private CheckBox mincab3;
    private CheckBox mincab4;
    private CheckBox mincab5;
    private CheckBox mincab6;

    private String incab1;
    private String incab2;
    private String incab3;
    private String incab4;
    private String incab5;
    private String incab6;


    //External Vehicle Checks
    private CheckBox mext1;
    private CheckBox mext2;
    private CheckBox mext3;
    private CheckBox mext4;
    private CheckBox mext5;
    private CheckBox mext6;
    private CheckBox mext7;
    private CheckBox mext8;
    private CheckBox mext9;
    private CheckBox mext10;
    private CheckBox mext11;
    private CheckBox mext12;

    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String ext11;
    private String ext12;


    //Prior to Leaving Depot
    private CheckBox mprior1;
    private CheckBox mprior2;

    private String prior1;
    private String prior2;

    //On-the-Road
    private CheckBox monroad1;
    private CheckBox monroad2;

    private String onroad1;
    private String onroad2;

    //Defect Details
    private EditText mdefectDetails;

    private String defectDetails;


    //Other Stuff
    private boolean connectivity;
    private static String subject;
    private static String emailData;
    private String date;
    private String time;

    public ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast launchT = Toast.makeText(MainActivity.this, "Please Remember this is not a tick box exercise", Toast.LENGTH_LONG);
        launchT.setGravity(Gravity.CENTER, 0, 0);
        launchT.show();

        setVariables();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    void setVariables() {

        emailData = "";

        mvehicleReg = (EditText) findViewById(R.id.vehicleReg);
        mmileage = (EditText)findViewById(R.id.mileage);
        mdriverName = (EditText)findViewById(R.id.driver);




        mincab1 = (CheckBox)findViewById(R.id.incab1);
        mincab2 = (CheckBox)findViewById(R.id.incab2);
        mincab3 = (CheckBox)findViewById(R.id.incab3);
        mincab4 = (CheckBox)findViewById(R.id.incab4);
        mincab5 = (CheckBox)findViewById(R.id.incab5);
        mincab6 = (CheckBox)findViewById(R.id.incab6);

        mext1 = (CheckBox)findViewById(R.id.ext1);
        mext2 = (CheckBox)findViewById(R.id.ext2);
        mext3 = (CheckBox)findViewById(R.id.ext3);
        mext4 = (CheckBox)findViewById(R.id.ext4);
        mext5 = (CheckBox)findViewById(R.id.ext5);
        mext6 = (CheckBox)findViewById(R.id.ext6);
        mext7 = (CheckBox)findViewById(R.id.ext7);
        mext8 = (CheckBox)findViewById(R.id.ext8);
        mext9 = (CheckBox)findViewById(R.id.ext9);
        mext10 = (CheckBox)findViewById(R.id.ext10);
        mext11 = (CheckBox)findViewById(R.id.ext11);
        mext12 = (CheckBox)findViewById(R.id.ext12);

        mprior1 = (CheckBox)findViewById(R.id.prior1);
        mprior2 = (CheckBox)findViewById(R.id.prior2);

        monroad1 = (CheckBox)findViewById(R.id.onroad1);
        monroad2 = (CheckBox)findViewById(R.id.onroad2);

        mdefectDetails = (EditText)findViewById(R.id.defectdetails);


        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Send();
            }
        });

        mClear = (Button) findViewById(R.id.clear);
        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                areYouSure();
            }
        });

    }

    void Send() {
        date = getTodaysDate();
        time = getCurrentTime();
        vehicleReg = mvehicleReg.getText().toString();
        mileage = mmileage.getText().toString();
        driverName = mdriverName.getText().toString();
        defectDetails = mdefectDetails.getText().toString();

        emailData += "<table border='2' cellpadding='6'>" ;

        emailData += "<tr><th>Vehicle Registration Number : </th><td>" + vehicleReg + "</td></tr>\n";
        emailData += "<tr><th>Mileage : </th><td>" + mileage + "</td></tr>\n";
        emailData += "<tr><th>Date : </th><td>" + date + "</td></tr>\n";
        emailData += "<tr><th>Time : </th><td>" + time + "</td></tr>\n";
        emailData += "<tr><th>Driver : </th><td>" + driverName + "</td></tr>\n";

        emailData += "</table><br>";

        emailData += "<table border='2' cellpadding='6'>" ;
        emailData += "<tr><th colspan='2'><h3><font color='#bc1f00'>In-Cab Checks </font></h3></th></tr>";



        emailData += "<tr><td>" + getString(R.string.incab1)+ "</td><td>" + changeBool(mincab1) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.incab2)+ "</td><td>" + changeBool(mincab2) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.incab3)+ "</td><td>" + changeBool(mincab3) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.incab4)+ "</td><td>" + changeBool(mincab4) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.incab5)+ "</td><td>" + changeBool(mincab5) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.incab6)+ "</td><td>" + changeBool(mincab6) +"</td></tr>";




        emailData += "<tr><th colspan='2'><h3><font color='#bc1f00'>External Vehicle Checks </font></h3></th></tr>";

        emailData += "<tr><td>" + getString(R.string.ext1)+"</td><td>" + changeBool(mext1) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext2)+"</td><td>" + changeBool(mext2) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext3)+"</td><td>" + changeBool(mext3) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext4)+"</td><td>" + changeBool(mext4) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext5)+"</td><td>" + changeBool(mext5) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext6)+"</td><td>" + changeBool(mext6) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext7)+"</td><td>" + changeBool(mext7) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext8)+"</td><td>" + changeBool(mext8) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext9)+"</td><td>" + changeBool(mext9) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext10)+"</td><td>" + changeBool(mext10) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext11)+"</td><td>" + changeBool(mext11) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.ext12)+"</td><td>" + changeBool(mext12) +"</td></tr>";



        emailData += "<tr><th colspan='2'><h3><font color='#bc1f00'>Prior to Leaving Depot </font></h3></th></tr>";
        emailData += "<tr><td>" + getString(R.string.prior1)+"</td><td>" + changeBool(mprior1) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.prior2)+"</td><td>" + changeBool(mprior2) +"</td></tr>";


        emailData += "<tr><th colspan='2'><h3><font color='#bc1f00'>On The Road </font></h3></th></tr>";
        emailData += "<tr><td>" + getString(R.string.onroad1)+"</td><td>" + changeBool(monroad1) +"</td></tr>";
        emailData += "<tr><td>" + getString(R.string.onroad2)+"</td><td>" + changeBool(monroad2) +"</td></tr>";



        emailData += "<tr><th colspan='2'><h3><font color='#bc1f00'>Defect Details </font></h3></th></tr>";
        emailData += "<tr><td colspan='2'>"+defectDetails+"</td></tr>";

        emailData += "</table><br>";
        //Check Internet Connection
        if(isNetworkAvailable())
        {
            //Send Email
            SendEmailAsyncTask sendEmail = new SendEmailAsyncTask();
            sendEmail.setMain(this);
            sendEmail.execute();
        }


    }

    void areYouSure()
    {

    }

    public void Exit(String title, String message)
    {
        //Put up the Yes/No message box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle(title)//"Clear All")
                .setMessage(message)//"Are you sure?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Yes button clicked, do something
                        android.os.Process.killProcess(android.os.Process.myPid());

                    }
                })
                .setNegativeButton("No", null)						//Do nothing on no
                .show();
    }

    private String getTodaysDate() {

        final Calendar c = Calendar.getInstance();

        return(new StringBuilder()
                .append(c.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(c.get(Calendar.MONTH) + 1).append("/")
                .append(c.get(Calendar.YEAR)).append(" ")).toString();
    }

    private String getCurrentTime() {

        final Calendar c = Calendar.getInstance();

        int tidyMinute = c.get(Calendar.MINUTE);
        String mins = ""+tidyMinute;

        if(tidyMinute < 10)
        {
            mins = "0"+tidyMinute;
        }

        return(new StringBuilder()
                .append(c.get(Calendar.HOUR_OF_DAY)).append(":")
                .append(mins).toString());
        //.append(c.get(Calendar.MINUTE)).append("")).toString();
    }

    public static String getEmailData() {
        return emailData;
    }
    public static String getSubject(){
        return subject;
    }

    public String changeBool(CheckBox chkBx)
    {
        String str = "";

        if(chkBx.isChecked())
        {
            str = Character.toString ((char) 10004);
        }
        else
        {
            str = Character.toString ((char) 10008);
            str = "<font color='#bc1f00'>"+str+"</font>";
        }

        return str;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {



    MainActivity mMain;

    GMailSender sender = new GMailSender("murrayambulanceapp@gmail.com", "checklist2014");


    public SendEmailAsyncTask() {
        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "SendEmailAsyncTask()");
        String[] toArr = { "murrayambulanceapp@gmail.com"};

    }

    public void setMain(MainActivity newMain)
    {
        mMain = newMain;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... params) {
        if (BuildConfig.DEBUG) Log.v(SendEmailAsyncTask.class.getName(), "doInBackground()");
        try {

                sender.sendMail(MainActivity.getSubject(),
                        MainActivity.getEmailData(), // email_data,
                        "murrayambulanceapp@gmail.com",
                        "lukemurray89@gmail.com"//"munnellydean@gmail.com,murrayambulanceapp@gmail.com,info@murrayambulance.ie"
                );
                mMain.pDialog.setMessage("Email is Sent");


            return true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("SendMail", e.getMessage(), e);

            return false;
        }
    }

    @Override   //TESTING
    protected void onPostExecute(Boolean result)
    {
        super.onPostExecute(result);

        // mMain.pDialog.dismiss();
        mMain.Exit("VDI Sent", "Would you like to exit the app?");

    }



}