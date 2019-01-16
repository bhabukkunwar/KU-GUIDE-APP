package com.example.hppower.kuguideapp;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonForMap;
    ExtraFunction checknet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checknet  = new ExtraFunction(this,this);

        buttonForMap = findViewById(R.id.mapButton);
        buttonForMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checknet.checkInternet("activity_mainPage")) {

                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(i);
                }

            }
        });

    }
}

