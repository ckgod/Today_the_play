package com.theplay.aos.fragment.recipe

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.HotRecipeActivity
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentHotRecipeBinding
import com.theplay.aos.iadapter.HotRecipeAdapter
import com.theplay.aos.iadapter.HotRecipeAdapterListener

class HotRecipeFragment() : BaseKotlinFragment<FragmentHotRecipeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_hot_recipe

    lateinit var tagName : String
    private val viewModel by lazy { HotRecipeViewModel() }

    override fun initStartView() {
        val act = requireActivity()
        when(act) {
            is HotRecipeActivity -> {
                tagName = act.returnArgs().tagName
            }
        }
        binding.tvTag.text = "#$tagName"
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
            requireActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
    }

    override fun initDataBinding() {
        viewModel.hotRecipeDetailResponse.observe(this@HotRecipeFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    ApplicationClass.hotRecipeListTmp = it.data.content
                    binding.rv.adapter = HotRecipeAdapter(requireActivity(),requireContext(),it.data.content).apply {
                        setInterface(object : HotRecipeAdapterListener{
                            override fun DoubleTap(postId: Int) {
                                showCustomToast("double Tap")
                            }

                            override fun clickedLike(postId: Int) {
                                showCustomToast("lick click")
                            }
                        })
                    }
                }
                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {
        viewModel.getHotRecipeDetail(tagName, 0,20)
    }

    override fun reLoadUI() {
    }

    fun removeActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }

    companion object {
        const val TAG = "HotRecipeFragment"
    }
}