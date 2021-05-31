package com.theplay.aos.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseBottomSheetFragment
import com.theplay.aos.databinding.BottomSheetPostMenuBinding
import com.theplay.aos.iadapter.PostMenuAdapter
import com.theplay.aos.iadapter.PostMenuInterface
import com.theplay.aos.item.PostMenuItem

interface MenuBottomSheetListener {
    fun clickMenu(type : Int)
}

class BottomSheetMainPost() : BaseBottomSheetFragment<BottomSheetPostMenuBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.bottom_sheet_post_menu

    var listener: MenuBottomSheetListener? = null
    var menuItemList : MutableList<PostMenuItem> = mutableListOf()

    override fun onResume() {
        super.onResume()
//        dialog?.window?.setWindowAnimations(-1)
    }

    override fun initStartView() {
        binding.btnClose.setOnClickListener {
            dismiss()
        }
        menuItemList.add(PostMenuItem(getString(R.string.menu_save_recipe),1))
        menuItemList.add(PostMenuItem(getString(R.string.menu_follow),2))
        menuItemList.add(PostMenuItem(getString(R.string.menu_share),3))
        menuItemList.add(PostMenuItem(getString(R.string.menu_report),4))

        binding.rv.adapter = PostMenuAdapter(requireActivity(),requireContext(),menuItemList).apply {
            setMenuInterface(object : PostMenuInterface{
                override fun clickMenu(type: Int) {
                    listenerFun(type)
                }
            })
        }
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    override fun reLoadUI() {
    }

    fun listenerFun(type : Int) {
        this.listener?.clickMenu(type)
    }

    fun setMenuBottomSheetInterface(menuBottomSheetListener: MenuBottomSheetListener) {
        this.listener = menuBottomSheetListener
    }


    companion object {
        const val TAG = "BottomSheetMainPost"
    }
}