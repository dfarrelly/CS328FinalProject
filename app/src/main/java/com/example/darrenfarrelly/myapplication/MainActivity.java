package com.example.darrenfarrelly.myapplication;

//import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
            public SensorManager sensorManager;
            public Sensor accelerometer;
            boolean appRunning = false;// use to see if we are currently displaying values. changes when onCreate is called


    @Override
    protected void onCreate(Bundle savedInstanceState) {
      //  appRunning = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    public void startStop(View view)
    {
        if(appRunning == false)//if the accelerometer is off this changes boolean to true(on)
        {
        appRunning = true;
        sensorManager.registerListener(this, accelerometer, SENSOR_DELAY_NORMAL);
        Toast.makeText(this, R.string.ApplicationResumed , Toast.LENGTH_SHORT).show();
        Button button = (Button) view;
        button.setText(R.string.button_stop);//go back to string.xml!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
        else//if the accelerometer is on this changes boolean to false(off)
        {

            sensorManager.unregisterListener(this,accelerometer);
            Toast.makeText(this,R.string.ApplicationOff,Toast.LENGTH_SHORT).show();
            Button button = (Button) view;
            button.setText(R.string.button_start);
            appRunning = false;
        }
    }

    public void onPause()
    {

        super.onPause();
        sensorManager.unregisterListener(this,accelerometer);
    }
    public void onResume() {
        super.onResume();
        if(appRunning == true) {//shouldnt this be true

            sensorManager.registerListener(this, accelerometer, SENSOR_DELAY_NORMAL);
            //come back and have this not start before button is press. rn it will resume on startup
        }
        }


    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            View text = findViewById(R.id.accel_x);
            TextView Vtext = (TextView) text;

            View text2 = findViewById(R.id.accel_y);
            TextView V2text = (TextView) text2;

            View text3 = findViewById(R.id.accel_z);
            TextView V3text = (TextView) text3;


            Vtext.setText(String.valueOf(event.values[0]));
            V2text.setText(String.valueOf(event.values[1]));
            V3text.setText(String.valueOf(event.values[2]));
            appRunning = true;
        }

      //  Button button =(Button)findViewById(R.id.accel_x);
        //button.setText(event.values[0]);
   // event.values[0];
    }
}
