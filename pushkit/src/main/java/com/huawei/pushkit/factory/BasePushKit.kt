package com.huawei.pushkit.factory

import android.util.Log

open class BasePushKit:PushKit {

    private val TAG = "BaseKit"

    override fun getAAID(getAAIDResult: (aaid: String) -> Unit) {
       Log.d(TAG,"getAAID")
    }

    override fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit) {
       Log.d(TAG,"deleteAAID")
    }

    override fun getToken(getTokenResult: (aaid: String) -> Unit) {
        Log.d(TAG,"getToken")
    }

    override fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit) {
        Log.d(TAG,"deleteToken")
    }

    override fun subscribe(message: String, getSubscribeResult: (result: Boolean) -> Unit) {
        Log.d(TAG,"subscribe")
    }

    override fun unSubscribe(message:String,getUnSubscribeResult: (result: Boolean) -> Unit) {
        Log.d(TAG, "unSubscribe")
    }

    override fun isAutoInitEnabled(isAutoInitEnabledResult: (result: Boolean) -> Unit) {
        Log.d(TAG, "isAutoInitEnabled")
    }

    override fun sendMessagge(remoteMessage: String, getSendMessaggeResult: (result: Boolean) -> Unit) {
        Log.d(TAG,"sendMessagge")
    }

    override fun setAutoInitEnabled(setAutoInitEnabled: Boolean) {
        Log.d(TAG,"setAutoInitEnabled")
    }

    override fun getId(getIdResult: (id: String) -> Unit) {
        Log.d(TAG,"getId")
    }

    override fun getCreationTime(getCreationTimeResult: (result: Long) -> Unit) {
        Log.d(TAG,"getCreationTime")
    }

}



