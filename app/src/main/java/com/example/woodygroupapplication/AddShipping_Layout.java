package com.example.woodygroupapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.utils.AddUtils;
import com.example.utils.NameUtils;

public class AddShipping_Layout extends AppCompatActivity {

    ImageView btnBackAddShip;
    Button btnSave;
    SwitchCompat switchCompat;
    EditText editText, editText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shipping_layout);

        editText=findViewById(R.id.editText);
        editText4=findViewById(R.id.editText4);

        btnSave=findViewById(R.id.btnSave);
        btnBackAddShip = findViewById(R.id.btnBackAddShip);
        switchCompat = findViewById(R.id.swichcompat);

        btnBackAddShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddShipping_Layout.this, Checkout_Layout.class));
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity2();
            }

            private void nextActivity2() {
                String strName= editText.getText().toString().trim();
                String strAddress= editText4.getText().toString().trim();

                NameUtils nameUtils= new NameUtils(strName);
                AddUtils addUtils = new AddUtils(strAddress);

                Intent intent1=new Intent(AddShipping_Layout.this,Checkout_Layout.class);

                Bundle bundle1= new Bundle();
                bundle1.putSerializable("object_name", nameUtils);

                Bundle bundle2= new Bundle();
                bundle2.putSerializable("object_address", addUtils);

                intent1.putExtras(bundle1);
                intent1.putExtras(bundle2);
                startActivity(intent1);
            }
        });
    }
}