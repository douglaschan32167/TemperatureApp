package com.example.douglas.temperatureapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor ambientTempSensor;
    private TextView testView, ambientTempView;

    private float ambientTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        ambientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);

        setContentView(R.layout.activity_main);
        TemperatureGenerator tempGen = new TemperatureGenerator();
        testView = (TextView)findViewById(R.id.testTextView);
        if (ambientTempSensor == null) {
            testView.setText("There is no ambient temp sensor");
        } else {
            testView.setText("The temps are " + tempGen.getTemperaturesC().toString() + "which in Fahr is " + tempGen.getTemperaturesF().toString());
        }
        ambientTempView = (TextView)findViewById(R.id.ambientTemperatureView);

    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, ambientTempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        ambientTemp = event.values[0];
        String ambientTempInfo = "The ambient temp is " + String.valueOf(ambientTemp);
        ambientTempView.setText(ambientTempInfo);

    }
}
