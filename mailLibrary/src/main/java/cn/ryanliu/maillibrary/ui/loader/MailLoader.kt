package cn.ryanliu.maillibrary.ui.loader

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AppCompatDialog
import cn.ryanliu.maillibrary.R
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.Utils
import com.wang.avi.AVLoadingIndicatorView
import com.wang.avi.Indicator
import com.wang.avi.indicators.BallClipRotateMultipleIndicator

object MailLoader {
    private val LOADER_SIZE_SCALE = 8
    private val LOADER_OFFSET_SCALE = 10

    //Dialog容器,用于管理Dialog
    private val LOADERS = ArrayList<AppCompatDialog>()

    //默认的loader
    private val DEFAYLT_LOADER = BallClipRotateMultipleIndicator()

    private fun createDialog(
        context: Context?,
        avLoadingIndicatorView: AVLoadingIndicatorView
    ): AppCompatDialog {
        val dialog = AppCompatDialog(context, R.style.dialog)
        //获取屏幕的宽高
        val deviceWidth = ScreenUtils.getScreenWidth();
        val deviceHeight = ScreenUtils.getScreenHeight();
        val dialogWindow = dialog.window;
        dialog.setContentView(avLoadingIndicatorView)

        if (dialogWindow != null) {
            val lp = dialogWindow.attributes
            lp.width = deviceWidth / LOADER_SIZE_SCALE
            lp.height = deviceHeight / LOADER_SIZE_SCALE
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SCALE
            lp.gravity = Gravity.CENTER
        }

        LOADERS.add(dialog)
        return dialog
    }

    fun showLoading(context: Context?, type: Enum<LoaderStyles>) {
        showLoading(context, type.name)
    }

    fun showLoading(context: Context?, type: String) {
        val avLoadingIndicatorView = AVLoadingIndicatorView(context)
        avLoadingIndicatorView.setIndicator(type)
        createDialog(context, avLoadingIndicatorView).show()
    }

    @JvmOverloads
    fun showLoading(context: Context?, indicator: Indicator = DEFAYLT_LOADER) {
        val avLoadingIndicatorView = AVLoadingIndicatorView(context)
        avLoadingIndicatorView.setIndicator(indicator)
        createDialog(context, avLoadingIndicatorView).show()
    }

    fun stopLoading() {
        for (dialog in LOADERS) {
            if (dialog.isShowing) {
                dialog.cancel()
            }
        }
        LOADERS.clear()
    }
}