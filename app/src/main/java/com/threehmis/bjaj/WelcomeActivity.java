package com.threehmis.bjaj;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.threehmis.bjaj.api.Const;
import com.threehmis.bjaj.module.logins.LoginActivity;
import com.threehmis.bjaj.utils.SPUtils;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView textbottom = (TextView) findViewById(R.id.textbottom);
        textbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFirst = (boolean) SPUtils.get(WelcomeActivity.this,Const.ISFIRST,true);
         /*       if (isFirst) {
                    startActivity(new Intent(WelcomeActivity.this, ChangeAddressActivity.class));
                } else {*/
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
             /*   }*/

                finish();


            }
        });
    }


}
