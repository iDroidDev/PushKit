package com.huawei.pushkitdemoX

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.huawei.pushkit.manager.HuaweiGooglePushManager
import com.huawei.pushkit.constant.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var isEnabled:Boolean=false
    lateinit var manager: HuaweiGooglePushManager
    //lateinit var kitBroadcastReceiver: KitBroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)


        manager = HuaweiGooglePushManager(this)


        manager.setAutoInitEnabled(true)
        createReceiver()
        setListener()



    }

    fun setListener() {
        btn_get_aaid.setOnClickListener(this)
        btn_delete_aaid.setOnClickListener(this)
        btn_get_token.setOnClickListener(this)
        btn_delete_token.setOnClickListener(this)
        btn_subs.setOnClickListener(this)
        btn_unsubs.setOnClickListener(this)
        btn_get_creation_time.setOnClickListener(this)
        btn_get_id.setOnClickListener(this)
        btn_is_auto_init_enabled.setOnClickListener(this)
        btn_set_auto_init_enabled.setOnClickListener(this)
    }

    fun createReceiver() {
        //kitBroadcastReceiver = KitBroadcastReceiver()
        intentFilter = IntentFilter()
        intentFilter.addAction("push_tag")
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_get_aaid -> manager.getAAID { System.out.println("btn_get_aaid $it")
                showToast("btn_get_aaid $it")
            }
            R.id.btn_delete_aaid -> manager.deleteAAID {System.out.println("btn_delete_aaid $it")
                showToast("btn_delete_aaid $it")}
            R.id.btn_get_token -> manager.getToken {System.out.println("btn_get_token $it")
                showToast("btn_get_token $it")}
            R.id.btn_delete_token -> manager.deleteToken {System.out.println("btn_delete_token $it")
                showToast("btn_delete_token $it")}
            R.id.btn_subs -> manager.subscribe(Constant.TOPIC_MESSAGE) {System.out.println("btn_subs $it")
                showToast("btn_subs $it")}
            R.id.btn_unsubs -> manager.unSubscribe(Constant.TOPIC_MESSAGE) {System.out.println("btn_unsubs $it")
                showToast("btn_unsubs $it")}
            R.id.btn_is_auto_init_enabled -> manager.isAutoInitEnabled {System.out.println("btn_is_auto_init_enabled $it")
                showToast("btn_is_auto_init_enabled $it")}
            R.id.btn_set_auto_init_enabled -> {
                showToast("btn_set_auto_init_enabled")
                manager.setAutoInitEnabled(isEnabled)
                isEnabled = !isEnabled }
            R.id.btn_get_id -> manager.getId { showToast("btn_get_id $it")
                showToast("btn_get_id $it")}
            R.id.btn_get_creation_time -> manager.getCreationTime { System.out.println("getId $it")
                showToast("btn_get_creation_time $it")}
        }
    }

    fun showToast(msg: String){
        runOnUiThread {
            Toast.makeText(this,msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        //registerReceiver(kitBroadcastReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        //unregisterReceiver(kitBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        //unregisterReceiver(kitBroadcastReceiver)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        //registerReceiver(kitBroadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        //unregisterReceiver(kitBroadcastReceiver)
    }

    companion object {
        var token: String? = null
    }

}
