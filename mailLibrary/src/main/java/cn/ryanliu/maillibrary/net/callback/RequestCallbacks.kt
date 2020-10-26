package cn.ryanliu.maillibrary.net.callback

import android.os.Handler
import cn.ryanliu.maillibrary.global.GlobalKeys
import cn.ryanliu.maillibrary.global.Mail
import cn.ryanliu.maillibrary.ui.loader.LoaderStyles
import cn.ryanliu.maillibrary.ui.loader.MailLoader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestCallbacks(
    private val request: IRequest?,
    private val success: ISuccess?,
    private val failure: IFailure?,
    private val error: IError?,
    private val complete: IComplete?,
    private val loaderStyle: LoaderStyles?
) : Callback<String> {
    override fun onResponse(call: Call<String>, response: Response<String>) {
        if (response.isSuccessful) {
            if (call.isExecuted) {
                if (success != null) {
                    if (response.body() != null) {
                        success.onSuccess(response.body()!!)
                    }
                }
            }
        } else {
            if (error != null) {
                error?.onError(response.code(), response.message())
            }
        }
        onRequestFinsh()
    }

    private fun onRequestFinsh() {
        val delayed = Mail.getConfiguration<Long>(GlobalKeys.LOADER_DELAYED)
        if (loaderStyle != null) {
            HANDLER.postDelayed({ MailLoader.stopLoading() }, delayed)
        }
    }

    override fun onFailure(call: Call<String>, t: Throwable) {
        if (failure != null) {
            failure?.onFailure(t)
            request?.onRequestEnd()
        }
    }

    companion object {
        private val HANDLER = Mail.getConfiguration<Handler>(GlobalKeys.HANDLER)
    }
}

