package cn.ryanliu.maillibrary.global

import android.os.Handler
import cn.ryanliu.maillibrary.util.storage.MemoryStore
import java.lang.RuntimeException
import java.util.concurrent.Delayed

class Configurator private constructor() {
    /**
     * 线程安全的单例模式
     * 深入学习可以仿照JAVA写法
     */
    private object Holder {
        //代表单例
        internal val INSTANCE = Configurator()
    }

    companion object {
        //这里获取到全局的存储容器
        private val mStore: MemoryStore = MemoryStore.instance

        //Handler需要反复使用,不妨提前创建
        private val mHandler = Handler()

        internal val instance: Configurator
            get() = Holder.INSTANCE
    }

    init {
        //加一个标签,判断是否配置完成,现在还没开始配置
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, false)
        mStore.addData(GlobalKeys.HANDLER, mHandler)
    }

    /**
     * 访问服务器的api的设置
     */
    fun withApiHost(host: String): Configurator {
        mStore.addData(GlobalKeys.API_HOST, host)
        return this
    }

    fun withLoaderDelayed(delayed: Long) : Configurator{
        mStore.addData(GlobalKeys.LOADER_DELAYED,delayed)
        return this
    }

    /**
     * 结束配置
     */
    fun configure() {
        mStore.addData(GlobalKeys.IS_CONFIGURE_READY, true)
        //下面可以做一些回收动作
    }

    private fun checkConfiguration() {
        var isReady = mStore.getData<Boolean>(GlobalKeys.IS_CONFIGURE_READY)
        if (!isReady) {
            throw RuntimeException("config is not ready")
        }
    }

    fun <T> getConfiguration(key: String): T {
        checkConfiguration()
        return mStore.getData(key)
    }

    fun <T> getConfiguration(key: Enum<*>): T {
        checkConfiguration()
        return getConfiguration(key.name)
    }
}