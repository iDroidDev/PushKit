package com.huawei.pushkit.factory

interface PushKit {
    fun getAAID(getAAIDResult: (aaid: String) -> Unit)
    fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit)
    fun getToken(getTokenResult: (aaid: String) -> Unit)
    fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit)
    fun subscribe(message:String,getSubscribeResult: (result: Boolean) -> Unit)
    fun unSubscribe(message:String,getUnSubscribeResult: (result: Boolean) -> Unit)
    fun isAutoInitEnabled(isAutoInitEnabledResult: (result: Boolean) -> Unit)
    fun setAutoInitEnabled(setAutoInitEnabled:Boolean)
    fun getId(getIdResult: (id: String) -> Unit)
    fun getCreationTime(getCreationTimeResult: (result: Long) -> Unit)
    fun sendMessagge(remoteMessage: String, getSendMessaggeResult: (result: Boolean) -> Unit)
}