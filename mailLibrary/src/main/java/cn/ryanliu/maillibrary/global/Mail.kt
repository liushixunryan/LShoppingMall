package cn.ryanliu.maillibrary.global

import android.content.Context
import cn.ryanliu.maillibrary.util.storage.MemoryStore

object Mail {
    val configurator: Configurator
        get() = Configurator.instance
    fun init(context : Context) : Configurator{
        MemoryStore.instance
            .addData(
                GlobalKeys.APPLICATION_CONTEXT,
                context.applicationContext)
        return Configurator.instance
    }
    fun <T> getConfiguration(key:String) : T{
        return configurator.getConfiguration(key)
    }
    fun <T> getConfiguration(key:GlobalKeys) : T{
        return configurator.getConfiguration(key.name)
    }
}