package com.theplay.aos.fragment.userpage

import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentUserPageBoardPersonalBinding
import com.theplay.aos.iadapter.MyPagePersonalAdapter
import com.theplay.aos.iadapter.MyPagePersonalInterface

class UserPageBoardPersonalFragment() : BaseKotlinFragment<FragmentUserPageBoardPersonalBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_board_personal

    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        ApplicationClass.tmpPostList?.let {
            itemList = mutableListOf()
            for(i in it) {
                itemList.add(i)
            }
            binding.rv.adapter = MyPagePersonalAdapter(this, requireActivity(), requireContext(), itemList).apply {
                setInterface(object : MyPagePersonalInterface {
                    override fun clickLike(postId: Int) {

                    }

                    override fun clickComment(postId: Int, nickName: String) {
                        requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(UserPeedFragmentDirections.actionUserPeedFragmentToCommentFragment(postId,nickName))
                    }
                })
            }
        }
    }

    companion object {
        const val TAG = "UserPageBoardPersonalFragment"
    }
}