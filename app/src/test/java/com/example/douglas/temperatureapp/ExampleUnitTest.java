package com.example.douglas.temperatureapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void jniWorks() throws Exception {
        JNITempConverter jtc = new JNITempConverter();
        float[] testCelcValues = {100.0f, 0.0f};
        float[] desiredResults = {212.0f, 32.0f};
        float[] results = jtc.convertCToF(testCelcValues);
        for (int i = 0; i < results.length; i++) {
            assertEquals(results[i], desiredResults[i], 0.1);
        }

    }

    @Test
    public void testTempGeneration() throws Exception {
        TemperatureGenerator tempGen = new TemperatureGenerator();
        assertEquals(tempGen.getTemperaturesC().size(), 5);
    }
}