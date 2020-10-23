package cn.ryanliu.kotlintest

import android.app.Application
import cn.ryanliu.maillibrary.global.Mail

class MailExampleApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Mail.init(this)
            .withApiHost("http://mock.fulingjie.com/mock/api/")
            .configure()
    }
}