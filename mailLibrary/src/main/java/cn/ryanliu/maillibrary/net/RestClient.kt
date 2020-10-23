package cn.ryanliu.maillibrary.net

import cn.ryanliu.maillibrary.net.callback.*
import retrofit2.Call
import retrofit2.Callback
import java.util.*

/**
 * 在所有依赖mail-Library的app中对外暴露直接使用的客户端
 */
class RestClient internal constructor(
    private var url: String? = null,
    private var params : WeakHashMap<String,Any>? = null,
    private var request: IRequest? = null,
    private var success: ISuccess? = null,
    private var failure: IFailure? = null,
    private var error: IError? = null,
    private var complete: IComplete? = null
) {
    companion object {
        fun builder(): RestClientBuilder {
            return RestClientBuilder()
        }
    }

    private fun request(method: HttpMethod){
        val service =  RestCreator.restService
        var call : Call<String>?
        request?.onRequestStart()

        call = when(method){
            HttpMethod.GET -> service.get(url,params)
            HttpMethod.POST -> service.post(url,params)
            HttpMethod.PUT -> service.put(url,params)
            HttpMethod.DELETE -> service.delete(url,params)
            //以下先不实现
            HttpMethod.UPLOAD -> TODO()
            HttpMethod.DOEMLOAD -> TODO()
        }
        call.enqueue(requestCallback)
    }
    private val requestCallback : Callback<String>
    get() = RequestCallbacks(request,success,failure,error,complete)

    fun get() {
        request(HttpMethod.GET)
    }

    fun post() {
        request(HttpMethod.POST)
    }

    fun put() {
        request(HttpMethod.PUT)
    }

    fun delete() {
        request(HttpMethod.DELETE)
    }
}