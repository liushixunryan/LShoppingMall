package cn.ryanliu.maillibrary.fragment.bottom

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.LinearLayoutCompat
import cn.ryanliu.maillibrary.R
import cn.ryanliu.maillibrary.fragment.MailFragment

abstract class BaseBottomFragment : MailFragment() {
    private val mTabBean = ArrayList<BottomTabBean>()
    val mItemFragments = ArrayList<BottomItemFragment>()
    private val mItems = LinkedHashMap<BottomTabBean, BottomItemFragment>()
    private var mCurrentFragment = 0

    //设置首页一打开展示哪个平级的Fragment
    private var mIndexFragment = 0
    private var mClickedColor = Color.RED

    private lateinit var mBottomBar: LinearLayoutCompat

    abstract fun setItems(builder: ItemBuilder): LinkedHashMap<BottomTabBean, BottomItemFragment>

    override fun setLayout(): Any {
        return R.layout.fragment_bottom
    }

    //设置首页一打开展示哪个平级的Fragment
    abstract fun setIndexFragment(): Int

    @ColorInt
    abstract fun setClickedColor(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mIndexFragment = setIndexFragment()
        if (setClickedColor() != 0) {
            mClickedColor = setClickedColor()
        }
        val builder = ItemBuilder.builder()
        val items = setItems(builder)

        mItems.putAll(items)
        for ((key, value) in mItems){
            mTabBean.add(key)
            mItemFragments.add(value)
        }
    }

    override fun onBindView(saveInstanceState: Bundle?, rootView: View) {
        mBottomBar = findView(R.id.bottom_bar)
        val size = mItems.size
        //kotlin用法,意思是从0~size
        for (i in 0 until size){
            LayoutInflater.from(context)
                .inflate(R.layout.bottom_item_icon_text,mBottomBar)
            val item =mBottomBar.getChildAt(0)
        }
    }
}