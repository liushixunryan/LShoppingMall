package cn.ryanliu.maillibrary.net.callback

/**
 * 请求错误
 */
interface IError {
    fun onError(code :Int ,msg :String)
}