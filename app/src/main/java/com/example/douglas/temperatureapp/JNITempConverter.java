package com.example.douglas.temperatureapp;

import java.util.Arrays;

/**
 * Created by douglas on 10/4/16.
 */

public class JNITempConverter {
    static {
        System.loadLibrary("jniTempConverter");
    }

    public native float[] convertCToF(float[] cDegrees);

    public static void main(String args[]) {
        JNITempConverter jtc = new JNITempConverter();
        float[] ctemps = {0.1f, 0.2f};
        System.out.println("In Java, the converted values are " + Arrays.toString(jtc.convertCToF(ctemps)));
    }
}
