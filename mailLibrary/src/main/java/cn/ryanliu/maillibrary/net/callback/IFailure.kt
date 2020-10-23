package cn.ryanliu.maillibrary.net.callback

/**
 * 请求失败
 */
interface IFailure {
    fun onFailure(t:Throwable)
}