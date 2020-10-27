package cn.ryanliu.maillibrary.actvitis

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import cn.ryanliu.maillibrary.fragment.MailFragment
import me.yokeyword.fragmentation.Fragmentation
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivityDelegate

abstract class ProxyActivity :
    AppCompatActivity(),ISupportActivity{
    private lateinit var mDelegate : SupportActivityDelegate
    abstract fun setRootFragment() : MailFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate = SupportActivityDelegate(this)
        mDelegate.onCreate(savedInstanceState)
    }
    private fun initContainer(savedInstanceState: Bundle?){
        val container = FrameLayout(this)

    }
}