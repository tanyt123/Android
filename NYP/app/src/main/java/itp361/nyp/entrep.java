package itp361.nyp;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * Created by User on 2/6/2017.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class entrep extends Fragment implements OnItemClickListener {
    View myView;
    ImageButton myImageButton;
    ImageButton myImageButton1;
    ImageButton myImageButton2;

    String[] values = new String[]{"KPDetails", "shirtstall", "Activity3"};

    private ListView listView;

    public static final String GET_IMAGE_URL = "http://192.168.1.2/GetAllImages.php";

    public GetAlImages getAlImages;

    public static final String BITMAP_ID = "BITMAP_ID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.entrep, container, false);

        getActivity().setTitle("             Start a business");


        GridView gridview;


        gridview =(GridView)myView.findViewById(R.id.gridview);
        gridview.setAdapter(new CustomAdapter(getActivity(),osNameList,osImages));
//        getURLs();
        return myView;
    }

//    private void getImages(){
//        class GetImages extends AsyncTask<Void,Void,Void> {
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(),"Downloading images...","Please wait...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(Void v) {
//                super.onPostExecute(v);
//                loading.dismiss();
//                //Toast.makeText(ImageListView.this,"Success",Toast.LENGTH_LONG).show();
//                CustomAdapter customList = new CustomAdapter(getActivity(),osNameList,osImages);
//                listView.setAdapter(customList);
//            }
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                try {
//                    getAlImages.getAllImages();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }
//        GetImages getImages = new GetImages();
//        getImages.execute();
//    }
//
//    private void getURLs() {
//        class GetURLs extends AsyncTask<String,Void,String>{
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(getActivity(),"Loading...","Please Wait...",true,true);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                getAlImages = new GetAlImages(s);
//                getImages();
//            }
//
//            @Override
//            protected String doInBackground(String... strings) {
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(strings[0]);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//
//                    return sb.toString().trim();
//
//                }catch(Exception e){
//                    return null;
//                }
//            }
//        }
//        GetURLs gu = new GetURLs();
//        gu.execute(GET_IMAGE_URL);
//    }

    public static String[] osNameList = {
            "Kachang Puteh",
            "Shirt",
            "Hotdog Bun",

    };
    public static int[] osImages = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    //       ;


////        GridView gridview = (GridView) myView.findViewById(R.id.gridview);
////        gridview.setAdapter(new ImageAdapter(getActivity()));
////
//        gridview.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                String val = values[position]; // arg2 is the index of item
//                Class ourClass  = null;
//                try {
//                    ourClass = Class.forName("itp361.nyp."+val);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent(getActivity(),ourClass);
//                startActivity(intent);
//            }
//        });
////        myImageButton = (ImageButton) myView.findViewById(R.id.img1);
////        myImageButton1 = (ImageButton) myView.findViewById(R.id.img2);
////        myImageButton2 = (ImageButton) myView.findViewById(R.id.img3);
////
////
////        View.OnClickListener listener = new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                switch (v.getId()) {
////                    case R.id.img1:
////                        //Start activity 1 here, for example
////                        Intent intentLoadNewActivity = new Intent(getActivity(), KPDetails.class);
////                        startActivity(intentLoadNewActivity);
////                        break;
//////                    case R.id.img2:
//////                        //Start activity 2 here
//////                        Intent intentLoadNewActivity1 = new Intent(entrep.this, ShirtBooth.class);
//////                        startActivity(intentLoadNewActivity1);
//////                        break;
//////                    case R.id.img3:
//////                        //Start activity 3 here
//////                        Intent intentLoadNewActivity2 = new Intent(entrep.this, HotDogBun.class);
//////                        startActivity(intentLoadNewActivity2);
//////                        break;
////
////                }
////
////            }
////        };
////
////        myImageButton.setOnClickListener(listener);
////        myImageButton1.setOnClickListener(listener);
////        myImageButton2.setOnClickListener(listener);
//       return myView;
//
//
//    }
//}

}