package com.example.douglas.temperatureapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Button toggleDegreesButton;
    private SensorManager sensorManager;
    private Sensor ambientTempSensor;
    private String degreeType;
    private TemperatureGenerator tempGen;
    private TextView ambientTempView, testView, degreesTypeView;
    private TextView mondayDegrees, tuesdayDegrees, wednesdayDegrees, thursdayDegrees, fridayDegrees;

    private float ambientTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        this.ambientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        this.degreeType = "Celsius";

        setContentView(R.layout.activity_main);
        this.tempGen = new TemperatureGenerator();
        this.toggleDegreesButton = (Button)findViewById(R.id.degreesToggleButton);
        this.testView = (TextView)findViewById(R.id.testTextView);
        this.degreesTypeView = (TextView) findViewById(R.id.degreesType);
        this.mondayDegrees = (TextView) findViewById(R.id.mondayDegrees);
        this.tuesdayDegrees = (TextView) findViewById(R.id.tuesdayDegrees);
        this.wednesdayDegrees = (TextView) findViewById(R.id.wednesdayDegrees);
        this.thursdayDegrees = (TextView) findViewById(R.id.thursdayDegrees);
        this.fridayDegrees = (TextView) findViewById(R.id.fridayDegrees);


        if (ambientTempSensor == null) {
            this.testView.setText("There is no ambient temp sensor");
        } else {
            this.testView.setText("The temps are " + this.tempGen.getTemperaturesC().toString() + "which in Fahr is " + this.tempGen.getTemperaturesF().toString());
        }
        showTemperatures();


        this.ambientTempView = (TextView)findViewById(R.id.ambientTemperatureView);

    }

    protected void onResume() {
        super.onResume();
        this.sensorManager.registerListener(this, this.ambientTempSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        this.sensorManager.unregisterListener(this);
    }

    private void showTemperatures(){
        List<Float> temps;
        if (this.degreeType.equals("Celsius")) {
            temps = this.tempGen.getTemperaturesC();
            this.toggleDegreesButton.setText("Show in Fahrenheit");
        } else {
            temps = this.tempGen.getTemperaturesF();
            this.toggleDegreesButton.setText("Show in Celsius");
        }


        this.mondayDegrees.setText(String.valueOf(temps.get(0)));
        this.tuesdayDegrees.setText(String.valueOf(temps.get(1)));
        this.wednesdayDegrees.setText(String.valueOf(temps.get(2)));
        this.thursdayDegrees.setText(String.valueOf(temps.get(3)));
        this.fridayDegrees.setText(String.valueOf(temps.get(4)));
        this.degreesTypeView.setText("Degrees " + this.degreeType);
    }

    public void toggleTemperatureTypes(View view) {
        if (this.degreeType.equals("Celsius")) {
            this.degreeType = "Fahrenheit";
        } else {
            this.degreeType = "Celsius";
        }
        showTemperatures();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        ambientTemp = event.values[0];
        String ambientTempInfo = "The ambient temp is " + String.valueOf(ambientTemp);
        ambientTempView.setText(ambientTempInfo);

    }
}
