package itp361.nyp;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {


    String[] result;
    Context context;
    int[] imageId;
    String[] values = new String[]{"KPDetails", "shirtstall", "Activity3"};
    private static LayoutInflater inflater = null;
    List<String> IdList = new ArrayList<>();
    String HttpUrl = "http://192.168.1.2/retrieveall.php";
String getId;
    public CustomAdapter(Context entrep, String[] osNameList, int[] osImages) {

        // TODO Auto-generated constructor stub
        result = osNameList;
        context = entrep;
        imageId = osImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView os_text;
        ImageView os_img;
String valuees;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.gridview, null);
        holder.os_text = (TextView) rowView.findViewById(R.id.os_texts);
        holder.os_img = (ImageView) rowView.findViewById(R.id.os_images);


        rowView.setPadding(8, 8, 8, 8);
        holder.os_text.setText(result[position]);
        holder.os_img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                String val = values[position]; // arg2 is the index of item


                Class ourClass = null;
                try {
                    ourClass = Class.forName("itp361.nyp." + val);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                new GetHttpResponse(context).execute();

                Intent intent = new Intent(context, ourClass);
                Toast.makeText(context, "You Clicked " + getId, Toast.LENGTH_SHORT).show();
                context.startActivity(intent);


            }
        });

        return rowView;
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        String JSonResult;

        List<Stall> StallList;

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
            // Passing HTTP URL to HttpServicesClass Class.
            HttpServicesClass httpServicesClass = new HttpServicesClass(HttpUrl);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            Stall stall;

                            StallList = new ArrayList<>();

                            for(int i=0; i<jsonArray.length(); i++)
                            {
                                stall = new Stall();

                                jsonObject = jsonArray.getJSONObject(i);

                                // Adding Stall Id TO IdList Array.
                                IdList.add(jsonObject.getString("BusinessID").toString());

                                //Adding Stall Name.
                                stall.StallName = jsonObject.getString("BusinessName").toString();

                                StallList.add(stall);

                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
//            progressBar.setVisibility(View.GONE);
//
//            StallListView.setVisibility(View.VISIBLE);
//
//            ListAdapterClass adapter = new ListAdapterClass(StallList, context);
//
//            StallListView.setAdapter(adapter);

        }
    }

}

