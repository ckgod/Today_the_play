package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemMyPageFollowBinding
import com.theplay.aos.databinding.ItemMyPageGoodBinding
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
        private var visible_button : Boolean = false
        fun bind(item: MyPageFollowItem) {
            binding.tvName.text = item.name
            if (FragmentType == FRAG_RIGHT) {
                binding.btnDelete.text = context.getString(R.string.my_page_follow_cancel)
            }
            itemView.setOnClickListener {
                if(visible_button) binding.ctlButtonContainer.visibility = View.GONE
                else binding.ctlButtonContainer.visibility = View.VISIBLE
                visible_button = !visible_button
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
