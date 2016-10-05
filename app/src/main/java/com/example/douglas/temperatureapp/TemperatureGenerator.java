package com.example.douglas.temperatureapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by douglas on 10/4/16.
 */

public class TemperatureGenerator {


    public TemperatureGenerator(){

    }

    public List<Double> getTemperatures() {

        Random randomGenerator = new Random();
        ArrayList<Double> temperatures = new ArrayList<Double>(5);
        for (int i = 0; i < 5; i++) {
            temperatures.add(randomGenerator.nextDouble() * 30);
        }
        return temperatures;
    }
}
