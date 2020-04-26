
PushKit SDK

This SDK includes Huawei PushKit and Google FCM enhancements. Recently, with the release of Huawei App Gallery, many applications are performing location integration.
Our main goal now aims to solve your pushkit related problems. You will have completed both integrations using only our libraries.

Installing

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
    
Add the dependency to your project build.gradle file:
        implementation 'com.github.iDroidDev:PushKit:1.1'
        
Usage

Configure PushKit in your Main Class

    lateinit var manager: HuaweiGooglePushManager
    manager = HuaweiGooglePushManager(this)
        override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_get_aaid -> manager.getAAID { System.out.println("btn_get_aaid $it") }
            R.id.btn_delete_aaid -> manager.deleteAAID {System.out.println("btn_delete_aaid $it") }
            R.id.btn_get_token -> manager.getToken {System.out.println("btn_get_token $it") }
            R.id.btn_delete_token -> manager.deleteToken {System.out.println("btn_delete_token $it") }
            R.id.btn_subs -> manager.subscribe(Constant.TOPIC_MESSAGE) {System.out.println("btn_subs $it") }
            R.id.btn_unsubs -> manager.unSubscribe(Constant.TOPIC_MESSAGE) {System.out.println("btn_unsubs $it") }
            R.id.btn_is_auto_init_enabled -> manager.isAutoInitEnabled {System.out.println("btn_is_auto_init_enabled $it") }
            R.id.btn_set_auto_init_enabled -> {
                showToast("btn_set_auto_init_enabled")
                manager.setAutoInitEnabled(isEnabled)
                isEnabled = !isEnabled }
            R.id.btn_get_id -> manager.getId { showToast("btn_get_id $it") }
            R.id.btn_get_creation_time -> manager.getCreationTime { System.out.println("getId $it") }
        }
    }   
    
Our PushKit SDK link: https://github.com/iDroidDev/PushKit.git

P.S: The project doesn't work in android phones with lower than 8.1. version.

Authors

•	Işılsu Çitim - Mobile Developer

•	Yasin Emre Türker - Mobile Developer

Don't worry

You can always contact us.

•	Işılsu Çitim Email : isilsu.citim@huawei.com

•	Yasin Emre Türker Email :  yasin.emre.turker@huawei.com

