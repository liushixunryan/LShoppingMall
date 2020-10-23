package cn.ryanliu.maillibrary.net

import androidx.annotation.RestrictTo
import cn.ryanliu.maillibrary.global.GlobalKeys
import cn.ryanliu.maillibrary.global.Mail
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 创建Retrofit的各个实例
 */
object RestCreator {
    /**
     * 构建我们的okhttp
     */
    private object OkhttpHolder {
        private const val TIME_OUT = 60
        private val BUILDER = OkHttpClient.Builder()
        internal val OK_HTTP_CLIENT = BUILDER
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()
    }

    private object RetrofitHolder {
        //从全局的配置中取出baseUrl
        private val BASE_URL =
            Mail.getConfiguration<String>(GlobalKeys.API_HOST)
        internal val RETORFIT_CLIENT = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkhttpHolder.OK_HTTP_CLIENT)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    private object RestServiceHolder {
        internal val REST_SERVICE = RetrofitHolder
            .RETORFIT_CLIENT
            .create(RestService::class.java)
    }

    val restService: RestService
        get() = RestServiceHolder.REST_SERVICE
}