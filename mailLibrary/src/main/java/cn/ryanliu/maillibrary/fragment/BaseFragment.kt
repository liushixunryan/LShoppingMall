package cn.ryanliu.maillibrary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import me.yokeyword.fragmentation.SupportFragment
import java.lang.NullPointerException

abstract class BaseFragment : SupportFragment() {

    private lateinit var mRootView: View

    //传Any是因为可以传递一个Layout的int值也可以传View的具体布局
    abstract fun setLayous(): Any

    abstract fun onBindView(saveInstanceState: Bundle?, rootView: View)

    fun <T : View> findView(@IdRes viewId: Int): T {
        return mRootView.findViewById(viewId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = when {
            setLayous() is Int -> inflater.inflate(setLayous() as Int, container, false)
            setLayous() is View -> setLayous() as View
            else -> throw ClassCastException("type of setLayout() must be int or view")
        }
        mRootView = rootView
        onBindView(savedInstanceState, rootView)
        return rootView
    }
}