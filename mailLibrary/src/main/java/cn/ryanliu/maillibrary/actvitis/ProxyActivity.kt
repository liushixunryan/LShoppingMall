package cn.ryanliu.maillibrary.actvitis

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import cn.ryanliu.maillibrary.R
import cn.ryanliu.maillibrary.fragment.MailFragment
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportActivityDelegate
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator


abstract class ProxyActivity :
    SupportActivity(), ISupportActivity {

    private lateinit var mDelegate: SupportActivityDelegate
    abstract fun setRootFragment(): MailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate = SupportActivityDelegate(this)
        mDelegate.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = R.id.fragment_container
        setContentView(container)
        if (savedInstanceState == null) {
            mDelegate.loadRootFragment(R.id.fragment_container, setRootFragment())
        }
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
        //内存回收
        System.gc()
        System.runFinalization()
    }

    override fun getSupportDelegate(): SupportActivityDelegate {
        return mDelegate
    }

    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        //动画实现
        return mDelegate.fragmentAnimator
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        //设置fragment动画
        mDelegate.fragmentAnimator = DefaultHorizontalAnimator()
    }


    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

}