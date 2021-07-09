package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemMyPageFollowBinding
import com.theplay.aos.databinding.ItemMyPageGoodBinding
import com.theplay.aos.fragment.mypage.MyPeedFragmentDirections
import com.theplay.aos.fragment.userpage.UserPeedFragmentDirections
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.MyPageFollowItem
import com.theplay.aos.item.MyPageGoodItem
import com.theplay.aos.utils.ViewUtils

interface MyPageFollowAdapterInterface {
    fun clickedLeft(userId : Int, userName : String)
    fun clickedRight(userId : Int, userName : String)
}

class MyPageFollowAdapter(private val FragmentType : Int, private val activity : Activity, private val context: Context, private val items: MutableList<MyPageFollowItem>) : RecyclerView.Adapter<MyPageFollowAdapter.MyPageFollowVH>() {
    private var listener : MyPageFollowAdapterInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFollowVH {
        val itemBinding = ItemMyPageFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageFollowVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPageFollowVH, position: Int) {
        val item: MyPageFollowItem = items[position]
        holder.bind(item)
    }

    inner class MyPageFollowVH(var binding: ItemMyPageFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyPageFollowItem) {
            binding.tvName.text = item.name
            if (FragmentType == FRAG_RIGHT) {
                binding.btnDelete.text = context.getString(R.string.my_page_follow_cancel)
            }
            if (FragmentType == 1 || FragmentType == 2) { // Fragment 가 MyPage 일때만
                itemView.setOnClickListener {
                    // plan 클릭시 유저 페이지로 이동
                    activity.findNavController(R.id.main_nav_host_fragment).navigate(MyPeedFragmentDirections.actionMyPeedFragmentToUserPeedActivity(item.userId))
                }
                binding.btnDelete.setOnClickListener {
                    Log.d(TAG, "clicked delete")
                    listener?.clickedLeft(item.userId, item.name)
                }
                binding.btnBan.setOnClickListener{
                    Log.d(TAG, "clicked ban")
                    listener?.clickedRight(item.userId, item.name)
                }
            }
            else { // UserPage 일때는 버튼 작동 x
                binding.ctlButtonContainer.visibility = View.GONE
                itemView.setOnClickListener {
                    activity.findNavController(R.id.main_nav_host_fragment).navigate(UserPeedFragmentDirections.actionUserPeedFragmentToUserPeedActivity(item.userId))
                }
            }
        }
    }

    fun setListener(myPageFollowAdapterInterface: MyPageFollowAdapterInterface) {
        this.listener = myPageFollowAdapterInterface
    }

    companion object{
        const val TAG = "MyPageFollowAdapter"
        const val FRAG_LEFT = 1
        const val FRAG_RIGHT = 2
    }
}
