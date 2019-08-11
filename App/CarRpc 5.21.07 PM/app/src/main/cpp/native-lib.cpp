#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_carrpc_nickd_car_1rpc_CarMovements_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
