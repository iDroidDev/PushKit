package idroid.android.locationkit.utils

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class CheckServiceAvaiable {
    companion object {
        fun getAvailableService(context: Context): DistributeType {
            return when {
                com.huawei.hms.api.ConnectionResult.SUCCESS == HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(
                    context
                ) -> DistributeType.HUAWEI_SERVICES
                ConnectionResult.SUCCESS == GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                    context
                ) -> DistributeType.GOOGLE_SERVICES
                else -> DistributeType.HUAWEI_SERVICES
            }
        }
    }
}

