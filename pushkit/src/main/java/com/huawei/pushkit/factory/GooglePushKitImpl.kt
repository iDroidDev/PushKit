package com.huawei.pushkit.factory

import android.app.Activity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import java.lang.Exception

class GooglePushKitImpl(activity: Activity):BasePushKit() {

    val TAG = "GooglePushKitImpl"

    override fun getAAID(getAAIDResult: (aaid: String) -> Unit) {
        super.getAAID(getAAIDResult)

        try {
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { getAAIDResult(it.id)
                Log.e(TAG,"id:${it.id}")
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit) {
        super.deleteAAID(getDeleteAAIDResult)
        Thread(Runnable {
            try {
                Log.e(TAG,"start")
                FirebaseInstanceId.getInstance().deleteInstanceId()
                getDeleteAAIDResult(true)
                Log.e(TAG,"stop")
            }
            catch (e:Exception){
                getDeleteAAIDResult(false)
                e.printStackTrace()
            }
        }).start()
    }

    override fun getToken(getTokenResult: (aaid: String) -> Unit) {
        super.getToken(getTokenResult)
        try {
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                val newToken = it.token
                getTokenResult(newToken)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit) {
        super.deleteToken(getDeleteTokenResult)
        Thread(Runnable {
            try {
                Log.e(TAG,"startToken")
                FirebaseInstanceId.getInstance().deleteInstanceId()
                Log.e(TAG,"stopToken")
                getDeleteTokenResult(true)
            }
            catch (e:Exception){
                getDeleteTokenResult(false)
                e.printStackTrace()
            }
        }).start()
    }

    override fun subscribe(message: String, getSubscribeResult: (result: Boolean) -> Unit) {
        super.subscribe(message, getSubscribeResult)
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(message).addOnCompleteListener {
                    task ->
                if (task.isSuccessful)
                {
                    getSubscribeResult(true)
                }
                else{
                    getSubscribeResult(false)
                }
            }
        }
        catch (e:java.lang.Exception){
            getSubscribeResult(false)
        }
    }

    override fun unSubscribe(message: String, getUnSubscribeResult: (result: Boolean) -> Unit) {
        super.unSubscribe(message, getUnSubscribeResult)
        try {

            FirebaseMessaging.getInstance().unsubscribeFromTopic(message).addOnCompleteListener { task ->

                if (task.isSuccessful){
                    getUnSubscribeResult(true)
                }
                else{
                    getUnSubscribeResult(false)
                }
            }
        }
        catch (e:java.lang.Exception){
            e.printStackTrace()
            getUnSubscribeResult(false)
        }
    }

    override fun isAutoInitEnabled(isAutoInitEnabledResult: (result: Boolean) -> Unit){
        super.isAutoInitEnabled(isAutoInitEnabledResult)
        try {
            isAutoInitEnabledResult(FirebaseInstanceId.getInstance().instanceId.isSuccessful)
        }catch (e:java.lang.Exception){
            isAutoInitEnabledResult(false)
            e.printStackTrace()
        }
    }

    override fun setAutoInitEnabled(setAutoInitEnabled: Boolean) {
        super.setAutoInitEnabled(setAutoInitEnabled)

        try {
            FirebaseMessaging.getInstance().isAutoInitEnabled = setAutoInitEnabled
        }
        catch (e:java.lang.Exception){
            e.printStackTrace()
        }
    }

    override fun getId(getIdResult: (id: String) -> Unit) {
        super.getId(getIdResult)
        try {
            getIdResult(FirebaseInstanceId.getInstance().id)
        }catch (e:java.lang.Exception){
            getIdResult("")
            e.printStackTrace()
        }
    }

    override fun getCreationTime(getCreationTimeResult: (result: Long) -> Unit) {
        super.getCreationTime(getCreationTimeResult)
        try {
            getCreationTimeResult(FirebaseInstanceId.getInstance().creationTime)
        }
        catch (e:java.lang.Exception){
            e.printStackTrace()
            getCreationTimeResult(0)
        }
    }
}




