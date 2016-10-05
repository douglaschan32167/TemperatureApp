LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := jniTempConverter
LOCAL_SRC_FILES := jniTempConverter.c
include $(BUILD_SHARED_LIBRARY)