package itp361.nyp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.R.attr.data;
import static android.R.attr.id;
import static itp361.nyp.R.id.progressBar;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
public class KPDetails extends AppCompatActivity {

TextView Date, Stall;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    ProgressBar progressBar;
    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;
    String Stallholder;
    String TempItem;
    String HttpURL="http://192.168.1.2/retrieveone.php";

    private Button buttonGetImage;
    private ImageView imageView;

    private RequestHandler requestHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpdetails);




        requestHandler = new RequestHandler();


        TempItem = getIntent().getStringExtra("ListViewValue");
        HttpWebCall(TempItem);
         Date =(TextView) findViewById(R.id.date);
        Stall = (TextView) findViewById(R.id.stall);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button mondayEdit = (Button) findViewById(R.id.button);

 mondayEdit.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(KPDetails.this,
                        KPForm.class);
                startActivity(myIntent);
                finish();
            }
            });

        }
//    private void getImage() {
//
//        class GetImage extends AsyncTask<String,Void,Bitmap>{
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(KPDetails.this, "Uploading...", null,true,true);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap b) {
//                super.onPostExecute(b);
//                loading.dismiss();
//                imageView.setImageBitmap(b);
//            }
//
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                String id = params[0];
//                String add = "http://simplifiedcoding.16mb.com/ImageUpload/getImage.php?id="+id;
//                URL url = null;
//                Bitmap image = null;
//                try {
//                    url = new URL(add);
//                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return image;
//            }
//        }
//
//        GetImage gi = new GetImage();
//        gi.execute(id);
//    }


//    public void onClick(View v) {
//        getImage();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();
        if(id==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(KPDetails.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(KPDetails.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("BusinessID",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, HttpURL);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            // Storing Student Name, Phone Number, Class into Variables.
                           Stallholder = jsonObject.getString("BusinessName").toString() ;


                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            Stall.setText(Stallholder);

        }
    }

//
//    public void OnCfm(View view) {
//        String businessName = UsernameEt.getText().toString();
//        String AmountTotal = PasswordEt.getText().toString();
//        String type = "login";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, businessName, AmountTotal);
//    }

}
