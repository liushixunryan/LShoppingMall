package cn.ryanliu.maillibrary.fragment.bottom

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.ryanliu.maillibrary.fragment.MailFragment

abstract class BottomItemFragment : MailFragment() {

    private var mTouchTime: Long = 0

    companion object {
        private const val WAIT_TIME = 2000L
    }

    override fun onBackPressedSupport(): Boolean {
        if (System.currentTimeMillis() - mTouchTime < WAIT_TIME) {
            _mActivity.finish()
        } else {
            mTouchTime = System.currentTimeMillis()
            Toast.makeText(_mActivity, "双击退出", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}