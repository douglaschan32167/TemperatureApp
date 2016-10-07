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
    private TextView ambientTempView, degreesTypeView;
    private TextView mondayDegrees, tuesdayDegrees, wednesdayDegrees, thursdayDegrees, fridayDegrees;

    private float ambientTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        this.ambientTempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        this.degreeType = "C";

        setContentView(R.layout.activity_main);
        this.tempGen = new TemperatureGenerator();
        this.toggleDegreesButton = (Button)findViewById(R.id.degreesToggleButton);
        this.degreesTypeView = (TextView) findViewById(R.id.degreesType);
        this.mondayDegrees = (TextView) findViewById(R.id.mondayDegrees);
        this.tuesdayDegrees = (TextView) findViewById(R.id.tuesdayDegrees);
        this.wednesdayDegrees = (TextView) findViewById(R.id.wednesdayDegrees);
        this.thursdayDegrees = (TextView) findViewById(R.id.thursdayDegrees);
        this.fridayDegrees = (TextView) findViewById(R.id.fridayDegrees);

        this.ambientTempView = (TextView)findViewById(R.id.ambientTemperatureView);
        showTemperatures();

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
        if (this.degreeType.equals("C")) {
            temps = this.tempGen.getTemperaturesC();
            this.toggleDegreesButton.setText("F -> C");
        } else {
            temps = this.tempGen.getTemperaturesF();
            this.toggleDegreesButton.setText("C -> F");
        }


        //usually weather apps round to either whole numbers or 1 decimal
        this.mondayDegrees.setText(String.format("%6.1f", temps.get(0)));
        this.tuesdayDegrees.setText(String.format("%6.1f", temps.get(1)));
        this.wednesdayDegrees.setText(String.format("%6.1f", temps.get(2)));
        this.thursdayDegrees.setText(String.format("%6.1f", temps.get(3)));
        this.fridayDegrees.setText(String.format("%6.1f", temps.get(4)));
        this.degreesTypeView.setText((char) 0x00B0  + this.degreeType);

        String ambientTempInfo = String.format("%3.1f", getAmbientTemp()) + (char) 0x00B0  + this.degreeType;
        this.ambientTempView.setText(ambientTempInfo);
    }

    /**
     * A function consolidating the degree type checks when determining the ambient temperature.
     * @return The ambient temperature in whichever degree type is selected.
     */
    public Float getAmbientTemp() {
        if (this.degreeType.equals("C")) {
            return this.ambientTemp;
        } else {
            return this.tempGen.convertTemperatureCToF(this.ambientTemp);
        }
    }

    /**
     * Change the degrees used in the temperatures and refresh the values shown.
     * @param view
     */
    public void toggleTemperatureTypes(View view) {
        if (this.degreeType.equals("C")) {
            this.degreeType = "F";
        } else {
            this.degreeType = "C";
        }
        showTemperatures();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        this.ambientTemp = event.values[0];

        String ambientTempInfo = String.format("%3.1f", getAmbientTemp()) + (char) 0x00B0  + this.degreeType;
        this.ambientTempView.setText(ambientTempInfo);

    }
}
