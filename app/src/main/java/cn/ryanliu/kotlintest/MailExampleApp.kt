package cn.ryanliu.kotlintest

import android.app.Application
import cn.ryanliu.maillibrary.global.Mail

class MailExampleApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Mail.init(this)
                //假装网络有两秒延迟,方便观察loading
            .withLoaderDelayed(2000)
            .withApiHost("http://mock.fulingjie.com/mock/api/")
            .configure()
    }
}