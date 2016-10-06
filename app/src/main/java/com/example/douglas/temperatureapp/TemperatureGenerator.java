package com.example.douglas.temperatureapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by douglas on 10/4/16.
 */

public class TemperatureGenerator {
    private ArrayList<Float> temperaturesF;
    private ArrayList<Float> temperaturesC;
    private JNITempConverter tempConverter;


    public TemperatureGenerator(){
        this.tempConverter = new JNITempConverter();
        randomizeTemperatures();
    }

    /**
     *A function to re-randomize the temperatures. To simulate the fact that weather forecasts can change.
     */
    public void randomizeTemperatures() {
        this.temperaturesF = new ArrayList<Float>(5);
        this.temperaturesC = new ArrayList<Float>(5);
        Random randomGenerator = new Random();
        float CTempArray[] = new float[5];
        for (int i = 0; i < 5; i++) {
            Float nextFloat = randomGenerator.nextFloat()*30;
            this.temperaturesC.add(nextFloat);
            CTempArray[i] = nextFloat.floatValue();
        }

        float[] convertedTemps = this.tempConverter.convertCToF(CTempArray);
        for (float nextFloat : convertedTemps) {
            this.temperaturesF.add(Float.valueOf(nextFloat));
        }

    }

    /**
     *Accessor function for celsius temperatures.
     */
    public List<Float> getTemperaturesC() {
        return this.temperaturesC;
    }

    /**
     *Accessor function for fahrenheit temperatures.
     */
    public List<Float> getTemperaturesF() {
        return this.temperaturesF;
    }
}
