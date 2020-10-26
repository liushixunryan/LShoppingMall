package cn.ryanliu.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import cn.ryanliu.maillibrary.net.RestClient
import cn.ryanliu.maillibrary.net.callback.IFailure
import cn.ryanliu.maillibrary.net.callback.ISuccess
import cn.ryanliu.maillibrary.ui.loader.LoaderStyles

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RestClient
            .builder()
            .url("index.php")
            .loader(this@MainActivity,LoaderStyles.BallBeatIndicator)
            .success(object : ISuccess {
                override fun onSuccess(response: String) {
                    Log.i("it520", response)
                }

            })
            .failure(object : IFailure{
                override fun onFailure(t: Throwable) {
                    Log.i("it520", "onFailure: " + t)
                }

            })
            .builder()
            .get()
    }

}
