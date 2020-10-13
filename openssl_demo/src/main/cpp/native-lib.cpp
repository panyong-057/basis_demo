#include <jni.h>
#include <string>

#include "openssl/md5.h"
#include <unistd.h>
#include <android/log.h>

#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,"JNI",__VA_ARGS__)
using namespace std;

void md5_test1(void);

void md5_test2(void);


int md5_test(void *idata, size_t len, unsigned char *md5);

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_jin_ende_1test_MainActivity_stringFromJNI(
        JNIEnv *env, jobject instance, jobject jobject_,
        jstring str_) {

//    const char *str = env->GetStringUTFChars(str_, 0);
//    unsigned char OutBuffer[200];
//
//
//
//    int outLen = 0;
//
//    do_encrypt(str, 6,OutBuffer,&outLen);
//
//    print_hex(OutBuffer, outLen);

   // md5_test1();
     md5_test2();

//    const char *input_data = "My name is hancq";
//    unsigned char md5[16];
//    int i;
//    MD5_CTX ctx;
//    memset(&ctx, 0, sizeof(ctx));
//    MD5_Init(&ctx);
//    MD5_Update(&ctx, input_data, strlen(input_data));
//    MD5_Final(md5, &ctx);
//    LOGE("md5_test1:%d",strlen(input_data));
//    for (i = 0; i < 16; i++) {
//        LOGE("%02x", md5[i]);
//    }



    return env->NewStringUTF(OpenSSL_version(OPENSSL_VERSION));
}

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_jin_ende_1test_MainActivity_stringFromJNI1(
        JNIEnv *env, jobject instance, jobject jobject_,
        jstring str_) {
    const char *str = env->GetStringUTFChars(str_, 0);

    md5_test1();
    LOGE("md5_test1:%s", str.c_str());

    return env->NewStringUTF(OpenSSL_version(OPENSSL_VERSION));
}


int md5_test(const void *idata, size_t len, unsigned char *md5) {
    MD5_CTX ctx;

    if (idata == NULL || len <= 0 || md5 == NULL) {
        LOGE("Input param invalid!\n");
        return -1;
    }

    memset(&ctx, 0, sizeof(ctx));
    MD5_Init(&ctx);
    MD5_Update(&ctx, idata, len);
    MD5_Final(md5, &ctx);

    return 0;
}


void md5_test11(const char *data, unsigned char md51[]) {
    const char *input_data = "My name is hancq";
    unsigned char md5[16];
    int i;
    md5_test(input_data, 16, md5);
    // char md51[16]= "My name is";

    for (i = 0; i < 16; i++) {
        LOGE("%02x", md5[i]);
    }
    unsigned char a[6] = {'G', 'o', 'o', 'd',};
    string str = "";
    str = (char *) a;
    LOGE("md5_test1:%s", str.c_str());
}

void md5_test1(void) {
    const char *input_data = "My name is hancq";
    unsigned char md5[16];
    int i;

    md5_test(input_data, 16, md5);
    // char md51[16]= "My name is";


    for (i = 0; i < 16; i++) {
        LOGE("%02x", md5[i]);
    }
    unsigned char a[6] = {'G', 'o', 'o', 'd',};
    string str = "";
    str = (char *) a;
    LOGE("md5_test1:%s", str.c_str());
}

void md5_test2(void) {
    const char *input_data = "My name is hancq";
    unsigned char md5[16];
    int i;

    //MD5(input_data, 16, md5);
    LOGE("md5_test2:");
    for (i = 0; i < 16; i++) {
        LOGE("%02x", md5[i]);
    }
    LOGE("\n");
}
