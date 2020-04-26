package com.huawei.pushkit.manager

import android.app.Activity
import com.huawei.pushkit.factory.PushKit
import com.huawei.pushkit.factory.PushKitFactory
import idroid.android.locationkit.utils.CheckServiceAvaiable

class HuaweiGooglePushManager(val activity: Activity) {

    private var pushkit: PushKit? = null

    init {
        pushkit = PushKitFactory.getPushKitFactory(
            activity,CheckServiceAvaiable.getAvailableService(activity))
    }
    fun getAAID(getAAIDResult: (aaid: String) -> Unit) {
        pushkit?.getAAID(getAAIDResult)
    }
    fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit) {
        pushkit?.deleteAAID(getDeleteAAIDResult)
    }
    fun getToken(getTokenResult: (aaid: String) -> Unit) {
        pushkit?.getToken(getTokenResult)
    }
    fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit) {
        pushkit?.deleteToken(getDeleteTokenResult)
    }
    fun setAutoInitEnabled(setAutoInitEnabled: Boolean) {
        pushkit?.setAutoInitEnabled(setAutoInitEnabled)
    }
    fun subscribe(message:String,getSubscribeResult: (result: Boolean) -> Unit) {
        pushkit?.subscribe(message,getSubscribeResult)
    }
    fun unSubscribe(message:String,getUnSubscribeResult: (result: Boolean) -> Unit) {
        pushkit?.unSubscribe(message,getUnSubscribeResult)
    }
    fun isAutoInitEnabled(isAutoInitEnabledResult: (result: Boolean) -> Unit) {
        pushkit?.isAutoInitEnabled(isAutoInitEnabledResult)
    }
    fun getId(getIdResult: (id: String) -> Unit) {
        pushkit?.getId(getIdResult)
    }
    fun getCreationTime(getCreationTimeResult: (result: Long) -> Unit) {
        pushkit?.getCreationTime(getCreationTimeResult)
    }
    fun sendMessagge(remoteMessage: String, getSendMessaggeResult: (result: Boolean) -> Unit)  {
        pushkit?.sendMessagge(remoteMessage,getSendMessaggeResult)
    }
}