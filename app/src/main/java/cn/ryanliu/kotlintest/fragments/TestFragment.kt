package cn.ryanliu.kotlintest.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import cn.ryanliu.kotlintest.R
import cn.ryanliu.maillibrary.fragment.MailFragment

class TestFragment : MailFragment() {

    override fun setLayout(): Any {
        return R.layout.fragment_test
    }

    override fun onBindView(saveInstanceState: Bundle?, rootView: View) {
        Toast.makeText(context, "初始化完成", Toast.LENGTH_SHORT).show()
    }
}