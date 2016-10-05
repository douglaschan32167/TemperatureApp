//
// Created by douglas on 10/5/16.
//

#include <jni.h>
#include <stdio.h>
#include "com_example_douglas_temperatureapp_JNITempConverter.h"

JNIEXPORT jfloatArray JNICALL Java_com_example_douglas_temperatureapp_JNITempConverter_convertCToF
  (JNIEnv *env, jobject thisobj, jfloatArray inJNIArray) {
    jfloat *inCArray = (*env)->GetFloatArrayElements(env, inJNIArray, NULL);
    if (NULL == inCArray) return NULL;
    jsize length = (*env)->GetArrayLength(env, inJNIArray);

    int i;
    float fahrValue;
    jfloat outCArray[length];
    for(i = 0; i < length; i++) {
      fahrValue = inCArray[i] / 5 * 9 + 32;
      outCArray[i] = fahrValue;
    }
    (*env)->ReleaseFloatArrayElements(env, inJNIArray, inCArray, 0); // release resources



    // Step 3: Convert the C's Native jdouble[] to JNI jdoublearray, and return
    jfloatArray outJNIArray = (*env)->NewFloatArray(env, length);  // allocate
    if (NULL == outJNIArray) return NULL;
    (*env)->SetFloatArrayRegion(env, outJNIArray, 0 , length, outCArray);  // copy
    return outJNIArray;

  }