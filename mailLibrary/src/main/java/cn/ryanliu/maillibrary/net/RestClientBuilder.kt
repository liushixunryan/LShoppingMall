package cn.ryanliu.maillibrary.net

import cn.ryanliu.maillibrary.net.callback.*
import java.util.*

/**
 * 构建RestClient并初始化参数和回调
 */
class RestClientBuilder(
    private var url: String? = null,
    private var request: IRequest? = null,
    private var success: ISuccess? = null,
    private var failure: IFailure? = null,
    private var error: IError? = null,
    private var complete: IComplete? = null
) {
    private val mParams = WeakHashMap<String, Any>()
    fun url(url: String): RestClientBuilder {
        this.url = url
        return this
    }

    fun params(key: String, value: Any): RestClientBuilder {
        mParams[key] = value
        return this
    }

    fun params(params: WeakHashMap<String, Any>): RestClientBuilder {
        mParams.putAll(params)
        return this
    }

    fun onRequest(iRequest: IRequest): RestClientBuilder {
        this.request = iRequest
        return this
    }

    fun success(ISuccess: ISuccess): RestClientBuilder {
        this.success = ISuccess
        return this
    }

    fun failure(IFailure: IFailure): RestClientBuilder {
        this.failure = IFailure
        return this
    }

    fun complete(IComplete: IComplete): RestClientBuilder {
        this.complete = IComplete
        return this
    }

    fun error(IError: IError): RestClientBuilder {
        this.error = IError
        return this
    }

    fun builder(): RestClient {
        return RestClient(url,mParams, request, success, failure, error, complete)
    }

}