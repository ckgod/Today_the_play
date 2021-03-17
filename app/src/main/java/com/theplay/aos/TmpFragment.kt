package com.theplay.aos

import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentTmpBinding

class TmpFragment() : BaseKotlinFragment<FragmentTmpBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_tmp

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "TmpFragment"
    }
}