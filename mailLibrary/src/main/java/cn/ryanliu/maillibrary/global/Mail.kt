package cn.ryanliu.maillibrary.global

import android.app.Application
import android.content.Context
import cn.ryanliu.maillibrary.util.storage.MemoryStore
import com.blankj.utilcode.util.Utils

object Mail {
    val configurator: Configurator
        get() = Configurator.instance
    fun init(context : Context) : Configurator{
        MemoryStore.instance
            .addData(
                GlobalKeys.APPLICATION_CONTEXT,
                context.applicationContext)
        //初始化工具类
        Utils.init(context as Application?)
        return Configurator.instance
    }
    fun <T> getConfiguration(key:String) : T{
        return configurator.getConfiguration(key)
    }
    fun <T> getConfiguration(key:GlobalKeys) : T{
        return configurator.getConfiguration(key.name)
    }
}