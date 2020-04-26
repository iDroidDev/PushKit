package com.huawei.pushkit.factory

import android.app.Activity
import android.util.Log
import com.huawei.agconnect.config.AGConnectServicesConfig
import com.huawei.hms.aaid.HmsInstanceId
import com.huawei.hms.common.ApiException
import com.huawei.hms.push.HmsMessaging
import com.huawei.pushkit.constant.Constant

class HuaweiPushKitImpl(private val activity: Activity):BasePushKit() {


    override fun getAAID(getAAIDResult: (aaid: String) -> Unit) {
        super.getAAID(getAAIDResult)

        val idResult = HmsInstanceId.getInstance(activity).aaid
        idResult.addOnSuccessListener { aaidResult ->
            getAAIDResult(aaidResult.id)
        }.addOnFailureListener {
            getAAIDResult("")
        }
    }

    override fun deleteAAID(getDeleteAAIDResult: (result: Boolean) -> Unit) {
        super.deleteAAID(getDeleteAAIDResult)

        object : Thread() {
            override fun run() {
                try {
                    HmsInstanceId.getInstance(activity).deleteAAID()
                    getDeleteAAIDResult(true)
                } catch (e: Exception) {
                    getDeleteAAIDResult(false)
                }
            }
        }.start()
    }

    override fun getToken(getTokenResult: (aaid: String) -> Unit) {
        super.getToken(getTokenResult)

        object : Thread() {
            override fun run() {
                try {

                    val appId =
                        AGConnectServicesConfig.fromContext(activity)
                            .getString("client/app_id")
                    val pushtoken = HmsInstanceId.getInstance(activity).getToken(appId, Constant.HCM)
                    System.out.println("push "+pushtoken)
                    getTokenResult(pushtoken)
                } catch (e: java.lang.Exception) {
                    getTokenResult("")
                }
            }
        }.start()
    }

    override fun deleteToken(getDeleteTokenResult: (result: Boolean) -> Unit) {
        super.deleteToken(getDeleteTokenResult)

        object : Thread() {
            override fun run() {
                try {
                    HmsInstanceId.getInstance(activity)
                        .deleteToken(Constant.appid, Constant.HCM)
                    getDeleteTokenResult(true)
                } catch (e: ApiException) {
                    getDeleteTokenResult(false)
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun subscribe(message: String, getSubscribeResult: (result: Boolean) -> Unit) {
        super.subscribe(message, getSubscribeResult)

        try {
            HmsMessaging.getInstance(activity).subscribe(message).addOnCompleteListener {
                    task ->
                if (task.isSuccessful)
                {
                    getSubscribeResult(true)
                }
                else{

                    Log.e("TAG", "unsubscribe failed: ret=" + task.getException().message)
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
            HmsMessaging.getInstance(activity).unsubscribe(message).addOnCompleteListener { task ->

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

    override fun setAutoInitEnabled(setAutoInitEnabled: Boolean) {
        super.setAutoInitEnabled(setAutoInitEnabled)

        try {
            HmsMessaging.getInstance(activity).isAutoInitEnabled = setAutoInitEnabled
        } catch (e:java.lang.Exception){
            setAutoInitEnabled(false)
            e.printStackTrace()
        }
    }

    override fun isAutoInitEnabled(isAutoInitEnabledResult: (result: Boolean) -> Unit){
        super.isAutoInitEnabled(isAutoInitEnabledResult)

        try {
            isAutoInitEnabledResult(HmsMessaging.getInstance(activity).isAutoInitEnabled)
        } catch (e:java.lang.Exception){
            isAutoInitEnabledResult(false)
            e.printStackTrace()
        }
    }


    override fun getId(getIdResult: (id: String) -> Unit) {
        super.getId(getIdResult)

        try {
            getIdResult(HmsInstanceId.getInstance(activity).id)
        }catch (e:java.lang.Exception){
            getIdResult("")
            e.printStackTrace()
        }
    }

    override fun getCreationTime(getCreationTimeResult: (result: Long) -> Unit) {
        super.getCreationTime(getCreationTimeResult)

        try {
            getCreationTimeResult(HmsInstanceId.getInstance(activity).creationTime)
        }
            catch (e:java.lang.Exception){
                e.printStackTrace()
                getCreationTimeResult(0)
            }
    }
}



