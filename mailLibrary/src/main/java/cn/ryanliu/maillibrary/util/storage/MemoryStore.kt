package cn.ryanliu.maillibrary.util.storage

import cn.ryanliu.maillibrary.global.GlobalKeys

class MemoryStore private constructor() {
    /**
     * 线程安全的单例模式
     * 深入学习可以仿照JAVA写法
     */
    private object Holder {
        //代表单例
        internal val INSTANCE = MemoryStore()
    }

    companion object {
        val instance: MemoryStore
            get() = Holder.INSTANCE
    }

    private val mDataMap = HashMap<String, Any>()
    fun <T> getData(key: String): T {
        //告诉这个转换是没有经过转换的
        @Suppress("UNCHECKED_CAST")
        return mDataMap[key] as T
    }

    fun addData(key: String, value: Any): MemoryStore {
        mDataMap[key] = value
        return this
    }

    fun <T> getData(key: Enum<*>): T {
        return getData(key.name)
    }

    fun addData(key: Enum<*>, value: Any): MemoryStore {
        addData(key.name, value)
        return this
    }
}
