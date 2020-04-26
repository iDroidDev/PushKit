package com.huawei.pushkit.factory

import android.app.Activity
import idroid.android.locationkit.utils.DistributeType

class PushKitFactory {

    companion object{
            fun getPushKitFactory(activity : Activity, type: DistributeType):PushKit{
                return if(type== DistributeType.HUAWEI_SERVICES){
                    HuaweiPushKitImpl(activity)

            }else{
                    GooglePushKitImpl(activity)
            }
        }
    }
}