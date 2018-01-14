package itp361.nyp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.CollationElementIterator;

import static android.R.attr.type;
import static itp361.nyp.R.id.Reason;
import static itp361.nyp.R.id.Type;

public class CfmPurchase extends AppCompatActivity {
    String  Type=getIntent().getExtras().getString("Type");
    String  Reason=getIntent().getExtras().getString("Reason");
    String  Amount=getIntent().getExtras().getString("Amount");
    String  Others=getIntent().getExtras().getString("OtherReason");
    TextView name, adminNo, Types, Reasons, amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cfm_purchase);
           name=(TextView)findViewById(R.id.Name);
        adminNo=(TextView)findViewById(R.id.AdminNo);
        Types=(TextView)findViewById(R.id.typespin);
        Reasons=(TextView)findViewById(R.id.reasonspin);
        amount=(TextView)findViewById(R.id.numberPicker);
        Types.setText(Type);
        amount.setText(Amount);
        if (Reason=="Others"){
            Reasons.setText(Others);
        }
        else {
            Reasons.setText(Reason);
        }
    }
}
