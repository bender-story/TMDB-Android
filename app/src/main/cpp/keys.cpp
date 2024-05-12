#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_rahul_themoviedb_Constants_getApiKey(JNIEnv* env, jobject /* this */) {
    return env->NewStringUTF("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MWIxOWZmZDhhN2FjMzYwY2NlMjZmNGYwNzFlNzJlZSIsInN1YiI6IjVjZTBlM2ZkYzNhMzY4NDg1YjFlMDVhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._8zCyHhuR_b2L-xbfET_HKrEoy-_m1TqR39CdOByLxw");
}