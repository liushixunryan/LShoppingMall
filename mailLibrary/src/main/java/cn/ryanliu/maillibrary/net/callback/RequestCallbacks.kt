package cn.ryanliu.maillibrary.net.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestCallbacks(
    private var request: IRequest? = null,
    private var success: ISuccess? = null,
    private var failure: IFailure? = null,
    private var error: IError? = null,
    private var complete: IComplete? = null
) : Callback<String> {
    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                if (success != null) {
                    if (response.body() != null) {
                        success?.onSuccess(response.body()!!)
                    }
                }
            }
        } else {
            if (error != null) {
                error?.onError(response.code(), response.message())
            }
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        if (failure != null){
            failure?.onFailure(t)
            request?.onRequestEnd()
        }
    }
}