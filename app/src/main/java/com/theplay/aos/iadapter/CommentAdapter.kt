package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.api.model.AddPostRequest
import com.theplay.aos.api.model.CommentResponse
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.ItemCommentBinding
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.databinding.ItemRecipeStairBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.WriteRecipeStepItem
import com.theplay.aos.utils.ViewUtils
import kotlin.math.exp

interface CommentAdapterListener{
    fun clickAddComment(commentId : Int)
    fun clickLike(commentId: Int)
}

class CommentAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<CommentResponse.Comment>) : RecyclerView.Adapter<CommentAdapter.CommentVH>() {
    lateinit var listener : CommentAdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentVH {
        val itemBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentVH, position: Int) {
        val item: CommentResponse.Comment = items[position]
        holder.bind(item)
    }

    fun setAdapterListener(commentAdapterListener: CommentAdapterListener) {
        this.listener = commentAdapterListener
    }

    inner class CommentVH(var binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        private var expanded = false
        fun bind(item: CommentResponse.Comment) {
            binding.tvNickName.text = item.nickname
            binding.tvContent.text = item.content
            binding.tvLikeCnt.text = "좋아요 ${item.commentLikeCount}개"
            if(item.commentLikeYn == "Y") {
                binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
            }
            else {
                binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
            }

            binding.ivLike.setOnClickListener {
                if(item.commentLikeYn == "N") {
                    binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
                    item.commentLikeYn = "Y"
                    item.commentLikeCount++
                    binding.tvLikeCnt.text = "좋아요 ${item.commentLikeCount}개"
                    listener.clickLike(item.postCommentId)
                }
                else {
                    binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
                    item.commentLikeYn = "N"
                    item.commentLikeCount--
                    binding.tvLikeCnt.text = "좋아요 ${item.commentLikeCount}개"
                    listener.clickLike(item.postCommentId)
                }
            }

            binding.tvAddRes.setOnClickListener {
                listener.clickAddComment(item.postCommentId)
            }

            if(item.secondComments.isEmpty()) {
                binding.lineAdd.visibility = View.GONE
                binding.tvShowRes.visibility = View.GONE
            }
            else { // note 대댓글 있을때 답글 보기 활성화
                val commentOfCommentList : MutableList<CommentResponse.SecondComment> = item.secondComments
                binding.rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                binding.rv.adapter = CommentOfCommentAdapter(activity,context,commentOfCommentList)
                binding.lineAdd.visibility = View.VISIBLE
                binding.tvShowRes.visibility = View.VISIBLE
                binding.tvShowRes.setOnClickListener {
                    if(expanded) {
                        binding.tvShowRes.text = "답글 보기"
                        binding.exvComment.collapse()
                    }
                    else {
                        binding.tvShowRes.text = "답글 숨기기"
                        binding.exvComment.expand()
                    }
                    expanded = !expanded
                }
            }
        }
    }

    companion object{
        const val TAG = "CommentAdapter"
    }
}
