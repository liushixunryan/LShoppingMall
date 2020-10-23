package cn.ryanliu.maillibrary.net.callback

/**
 * 请求结束,不管是否成功
 */
interface IComplete {
    fun onSuccess()
}