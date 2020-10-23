package cn.ryanliu.maillibrary.net.callback

import okhttp3.Response

/**
 * 请求成功
 */
interface ISuccess {
    fun onSuccess(response: String)
}