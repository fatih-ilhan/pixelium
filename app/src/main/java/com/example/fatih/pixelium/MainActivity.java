package com.example.fatih.pixelium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Tick(View v) {

        if(v.getId()==R.id.button1) {
            Intent intent = new Intent(getApplicationContext(),DrawingScreen.class);
            startActivity(intent);
        }

        else if(v.getId()==R.id.button2) {
            Intent intent = new Intent(getApplicationContext(),PrevWorksScreen.class);
            startActivity(intent);
        }

        else if(v.getId()==R.id.button3) {
            Intent intent = new Intent(getApplicationContext(),AlarmScreen.class);
            startActivity(intent);
        }

        else if(v.getId()==R.id.button4) {
            Intent intent = new Intent(getApplicationContext(),ChildLockScreen.class);
            startActivity(intent);
        }
    }
}
