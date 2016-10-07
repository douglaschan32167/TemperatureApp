package com.example.douglas.temperatureapp;

import java.util.Arrays;

/**
 * Created by douglas on 10/4/16.
 */

public class JNITempConverter {
    static {
        System.loadLibrary("jniTempConverter");
    }

    /**
     * Pass the float array into the C code in order to convert to F. Works for arrays of any size.
     * @param cDegrees An array of celsius temperature values in floats.
     * @return An array of fahrenheit temperature values in floats.
     */
    public native float[] convertCToF(float[] cDegrees);

    public static void main(String args[]) {
        JNITempConverter jtc = new JNITempConverter();
        float[] ctemps = {0.1f, 0.2f};
        System.out.println("In Java, the converted values are " + Arrays.toString(jtc.convertCToF(ctemps)));
    }
}
