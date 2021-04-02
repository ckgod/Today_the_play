package com.theplay.aos.customview

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass.Companion.colorToCodeHashMap
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.R
import com.theplay.aos.api.model.AddPostRequest
import com.theplay.aos.databinding.CustomDialogAddDrinkBinding
import com.theplay.aos.databinding.CustomDialogAddMaterialBinding
import com.theplay.aos.iadapter.AddIconAdapter
import com.theplay.aos.item.AddIconItem
import com.theplay.aos.item.DrinkItem
import com.theplay.aos.item.RecipeMaterialItem
import com.theplay.aos.utils.ViewUtils
import kotlin.math.sqrt

interface AddMaterialListener {
    fun onComplete(item : AddPostRequest.Ingredient)
}

class AddMaterialDialog(
    var mContext: Context,
    var mActivity: Activity
) : AppCompatDialog(mContext) {
    private lateinit var binding: CustomDialogAddMaterialBinding
    private lateinit var listener: AddMaterialListener
    var iconList : MutableList<AddIconItem> = mutableListOf()
    var curIcon = R.drawable.ic_drinks_soju_main
    var curColor = R.color.mainColor
    var curUnit = "개"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogAddMaterialBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.unitPicker.maxValue = 1; binding.unitPicker.minValue = 0
        val pickerUnit  = arrayOf("개", "%")
        binding.unitPicker.displayedValues = pickerUnit
        binding.unitPicker.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        binding.unitPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            if(newVal == 1) {
                curUnit = "%"
            }
            else {
                curUnit = "개"
            }
            Log.d(TAG, "curUnit $curUnit")
        }
        binding.etName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etName.text.isEmpty()) { binding.tvNameHint.visibility = View.INVISIBLE }
                else {binding.tvNameHint.visibility = View.VISIBLE }
            }
        })
        binding.btnComplete.setOnClickListener {
            if (binding.etName.text.isEmpty()) {
                Toast.makeText(mContext, "재료 이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.etNumber.text.isEmpty() || binding.etNumber.text.toString().toInt() <= 0) {
                Toast.makeText(mContext, "재료 수량을 제대로 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val quantity = "${binding.etNumber.text.toString()}$curUnit"
            val iconName = iconHashMap.filterValues { it == curIcon }.keys.elementAt(0)
            val item = AddPostRequest.Ingredient(colorToCodeHashMap[curColor]!!, iconName ,binding.etName.text.toString(), quantity)
            Log.d(TAG, "ingredient add : ${item.toString()}")
            listener.onComplete(item)
        }
        binding.rvIcon.layoutManager = SliderLayoutManager(binding.rvIcon, mContext).apply {
            callback = object : SliderLayoutManager.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {
                    // 아이템 선택
                    curIcon = iconList[layoutPosition].icon
                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(binding.rvIcon)
        iconList.add(AddIconItem(R.drawable.drinks_cock_main))
        iconList.add(AddIconItem(R.drawable.drinks_wine_main))
        iconList.add(AddIconItem(R.drawable.drinks_vod_main))
        iconList.add(AddIconItem(R.drawable.drinks_shot_main))
        iconList.add(AddIconItem(R.drawable.ic_drinks_soju_main))
        iconList.add(AddIconItem(R.drawable.drinks_sake_main))
        iconList.add(AddIconItem(R.drawable.drinks_can_main))
        iconList.add(AddIconItem(R.drawable.drinks_wine_2_main))
        iconList.add(AddIconItem(R.drawable.drinks_beer_main))
        iconList.add(AddIconItem(R.drawable.drinks_lemon_m))
        iconList.add(AddIconItem(R.drawable.drinks_leaf_m))
        iconList.add(AddIconItem(R.drawable.drinks_cherry_m))
        binding.rvIcon.adapter = AddIconAdapter(mActivity, mContext, iconList)
//        binding.rvIcon.scrollToPosition(4)
        binding.rvIcon.smoothScrollToPosition(4)
    }

    fun setDialogListener(addMaterialListener: AddMaterialListener){
        this.listener = addMaterialListener
    }

    class SliderLayoutManager(var recyclerView: RecyclerView, context: Context?) : LinearLayoutManager(context) {
        var callback: OnItemSelectedListener? = null
        override fun onScrollStateChanged(state: Int) {
            super.onScrollStateChanged(state)
            // When scroll stops we notify on the selected item
            if (state.equals(RecyclerView.SCROLL_STATE_IDLE)) {
                // Find the closest child to the recyclerView center --> this is the selected item.
                val recyclerViewCenterX = getRecyclerViewCenterX()
                var minDistance = recyclerView.height
                var position = -1
                for (i in 0 until recyclerView.childCount) {
                    val child = recyclerView.getChildAt(i)
                    val childCenterX = getDecoratedBottom(child) + (getDecoratedTop(child) - getDecoratedBottom(child)) / 2
                    var childDistanceFromCenter = Math.abs(childCenterX - recyclerViewCenterX)
                    if (childDistanceFromCenter < minDistance) {
                        minDistance = childDistanceFromCenter
                        position = recyclerView.getChildLayoutPosition(child)
                    }
                }
                // Notify on the selected item
                callback?.onItemSelected(position)
            }
        }

        private fun getRecyclerViewCenterX() : Int {
            return (recyclerView.top - recyclerView.bottom)/2 + recyclerView.bottom
        }

        override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
            super.onLayoutChildren(recycler, state)
            scaleDownView()
        }

        override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
            if (orientation == LinearLayoutManager.VERTICAL) {
                val scrolled = super.scrollVerticallyBy(dy, recycler, state)
                scaleDownView()
                return scrolled
            } else {
                return 0
            }
        }

        private fun scaleDownView() {
            val mid = height / 2.0f
            for (i in 0 until childCount) {
                // Calculating the distance of the child from the center
                val child = getChildAt(i)
                val childMid = (getDecoratedTop(child!!) + getDecoratedBottom(child)) / 2.0f
                val distanceFromCenter = Math.abs(mid - childMid)

                // The scaling formula
                val scale = 1-sqrt((distanceFromCenter/height).toDouble()).toFloat()*0.66f

                // Set scale to view
                child.scaleX = scale
                child.scaleY = scale
            }
        }

        interface OnItemSelectedListener {
            fun onItemSelected(layoutPosition: Int)
        }
    }

    companion object{
        const val TAG = "AddMaterialDialog"
    }

}