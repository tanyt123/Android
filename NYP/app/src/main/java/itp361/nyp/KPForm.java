package itp361.nyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.R.attr.duration;
import static android.R.attr.numberPickerStyle;
import static android.R.attr.type;
import static itp361.nyp.R.id.numberPicker;

import static itp361.nyp.R.id.tvInvisibleErrors;


public class KPForm extends AppCompatActivity {
   TextView name,adminNo;
    Button button;
    NumberPicker noPicker;
    Spinner s1, s2;
EditText reasons;
  String text,texts;
    Button btnClosePopup,btnyes;
    String pickedValue, reason;
    private boolean isSpinnerTouched = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kpform);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name = (TextView) findViewById(R.id.Name);
        adminNo = (TextView) findViewById(R.id.AdminNo);
        reasons = (EditText) findViewById(R.id.reasons);

        s1 = (Spinner) findViewById(R.id.typespin);

        s2 = (Spinner) findViewById(R.id.reasonspin);


        noPicker = (NumberPicker) findViewById(numberPicker);
        noPicker.setMinValue(15);
        noPicker.setMaxValue(99);
        s1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        s2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerTouched = true;
                return false;
            }
        });
        s1.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){


                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                if (!isSpinnerTouched) return;
                String sp1 = String.valueOf(s1.getSelectedItem());
                if (sp1.contentEquals("Withdraw")) {
                    s2.setEnabled(true);

                    List<String> list = new ArrayList<String>();
                    list.add("Select");
                    list.add("Graduating");
                    list.add("See no potential on return");
                    list.add("Others");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter);
                } else if (sp1.contentEquals("Deposit")) {

                    s2.setEnabled(false);


                } else {


                    ((TextView) s1.getSelectedView()).setError("");


                    s2.setEnabled(false);
                    List<String> list = new ArrayList<String>();
                    list.add("Select");
                    ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter2.notifyDataSetChanged();
                    s2.setAdapter(dataAdapter2);

                }

            }

                public void onNothingSelected (AdapterView < ? > arg0){
            // TODO Auto-generated method stub

        }
    });
                s2.setOnItemSelectedListener(new OnItemSelectedListener() {
                                                 public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){

                                                     ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                                                     String sp2 = String.valueOf(s2.getSelectedItem());
                                                     if (sp2.contentEquals("Others")){
                                                         reasons.setVisibility(view.VISIBLE);

                                                     }
                                                     else if(sp2.contentEquals("Select")){
                                                         ((TextView) s2.getSelectedView()).setError("");
                                                     }

                                                 }

                                                 public void onNothingSelected (AdapterView < ? > arg0){
                                                     // TODO Auto-generated method stub

                                                 }
                                             });



//        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//
//                if (name.getText().toString().length() <= 0) {
//                    name.setError("Accept Alphabets Only.");
//                } else if (!name.getText().toString().matches("[a-zA-Z ]+")) {
//                    name.setError("Accept Alphabets Only.");
//                }
//            }
//        });
//        AdminNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//
//                if (AdminNo.getText().toString().length() != 7) {
//                    AdminNo.setError("Enter Admin No.");
//                } else if (!AdminNo.getText().toString().substring(0, AdminNo.length() - 2).matches("[0-9]+")) {
//                    AdminNo.setError("Enter Admin No.");
//                } else if (!AdminNo.getText().toString().substring(AdminNo.length() - 1).matches("[a-zA-Z ]+")) {
//                    AdminNo.setError("Enter Admin No.");
//                }
//            }
//        });


        button = (Button) findViewById(R.id.btncfm);

        // Capture button clicks
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class

// TODO Auto-generated method stub
                    initiatePopupWindow();
                }


//


        });


    }
    private PopupWindow pwindo;

    private void initiatePopupWindow() {
        try {
// We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) KPForm.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.screen_popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 500, 500, true);
            pwindo.showAtLocation(layout, Gravity.  CENTER, 0, 0);

            btnClosePopup = (Button) layout.findViewById(R.id.bNoClear);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);
            btnyes = (Button) layout.findViewById(R.id.bYesClear);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnClickListener cancel_button_click_listener = new OnClickListener() {
        public void onClick(View v) {
            pwindo.dismiss();

        }
    };
    public void CfmClick(View v) {
//
//            Intent myIntent = new Intent(KPForm.this,
//                    entrep.class);
            text = s1.getSelectedItem().toString();
            texts = s2.getSelectedItem().toString();
             pickedValue = String.valueOf(noPicker.getValue());


////            myIntent.putExtra("Type",text);
////            myIntent.putExtra("Reason",texts);
////            myIntent.putExtra("Amount",pickedValue);
////            myIntent.putExtra("OtherReason",reasons.toString());
//            startActivity(myIntent);
            String method = "addamount";
        BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method,text,texts,pickedValue,reasons.getText().toString());
        Toast.makeText(this, "This is my Toast message!"+ text+","+texts+","+pickedValue+","+reasons.getText().toString(),
                Toast.LENGTH_LONG).show();
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }




    }




