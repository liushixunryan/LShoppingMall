package cn.ryanliu.kotlintest

import cn.ryanliu.kotlintest.fragments.TestFragment
import cn.ryanliu.maillibrary.actvitis.ProxyActivity
import cn.ryanliu.maillibrary.fragment.MailFragment

class MainActivity : ProxyActivity() {

    override fun setRootFragment(): MailFragment {
        return TestFragment()
    }

}
