package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.api.model.CommentResponse
import com.theplay.aos.databinding.ItemCommentOfCommentBinding


class CommentOfCommentAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<CommentResponse.SecondComment>) : RecyclerView.Adapter<CommentOfCommentAdapter.CommentOfCommentVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentOfCommentVH {
        val itemBinding = ItemCommentOfCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentOfCommentVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentOfCommentVH, position: Int) {
        val item: CommentResponse.SecondComment = items[position]
        holder.bind(item)
    }

    inner class CommentOfCommentVH(var binding: ItemCommentOfCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CommentResponse.SecondComment) {
            binding.tvNickName.text = item.nickname
            binding.tvContent.text = item.content
            binding.tvLikeCnt.text = "좋아요 ${item.commentLikeCount}개"
            if(item.commentLikeYn == "Y") {
                binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
            }
            else {
                binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
            }
        }
    }

    companion object{
        const val TAG = "CommentOfCommentAdapter"
    }
}
