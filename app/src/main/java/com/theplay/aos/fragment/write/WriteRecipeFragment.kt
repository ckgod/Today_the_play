package com.theplay.aos.fragment.write

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.api.model.AddPostRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.AddMaterialDialog
import com.theplay.aos.customview.AddMaterialListener
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.databinding.FragmentWriteRecipeBinding
import com.theplay.aos.iadapter.MaterialAdapter
import com.theplay.aos.iadapter.RecipeStepAdapter
import com.theplay.aos.item.DrinkItem
import com.theplay.aos.item.WriteRecipeStepItem
import com.theplay.aos.utils.DrinkUtil

class WriteRecipeFragment() : BaseKotlinFragment<FragmentWriteRecipeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_write_recipe

    private val safeArgs : WriteRecipeFragmentArgs by navArgs()

    var stepList : MutableList<AddPostRequest.Step> = mutableListOf()
    var materialList : MutableList<AddPostRequest.Ingredient> = mutableListOf()
    var curStep = 0
    lateinit var ctlStepList : MutableList<ConstraintLayout>
    lateinit var etStepList : MutableList<EditText>

    override fun initStartView() {
        ctlStepList = mutableListOf(binding.ctlStep1,binding.ctlStep2,binding.ctlStep3,binding.ctlStep4,binding.ctlStep5)
        etStepList = mutableListOf(binding.etContent1,binding.etContent2,binding.etContent3,binding.etContent4,binding.etContent5)

        Log.d(TAG, findNavController().toString())
        binding.ivIcon.background = ContextCompat.getDrawable(requireContext(), safeArgs.icon)
        binding.tvName.text = safeArgs.name
        binding.ivIcon.backgroundTintList = ContextCompat.getColorStateList(requireContext(), safeArgs.colorType)
        binding.tvName.setTextColor(ContextCompat.getColor(requireContext(), safeArgs.colorType))
        binding.rvRecipes.layoutManager =LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRecipes.adapter = MaterialAdapter(requireActivity(),requireContext(),materialList)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnPlusRecipe.setOnClickListener {
            val dialog = AddMaterialDialog(requireContext(), requireActivity()).apply {
                setDialogListener(object : AddMaterialListener{
                    override fun onComplete(item: AddPostRequest.Ingredient) {
                        materialList.add(item)
                        binding.rvRecipes.adapter?.notifyDataSetChanged()
                        dismiss()
                    }
                })
            }
            dialog.show()
        }
        binding.btnPlusStair.setOnClickListener {
            if(curStep == 4) {
                showCustomToast("최대 5단계까지 가능합니다.")
                return@setOnClickListener
            }
            if (etStepList[curStep].text.isEmpty()) {
                showCustomToast("${curStep+1}단계를 먼저 적어주세요.")
                return@setOnClickListener
            }
            etStepList[curStep].isClickable = false
            etStepList[curStep].clearFocus()
            etStepList[curStep].isEnabled = false
            stepList.add(AddPostRequest.Step(etStepList[curStep].text.toString(),curStep + 1))

            curStep++
            ctlStepList[curStep].visibility = View.VISIBLE
//            stepList.add(AddPostRequest.Step("",curStep))
        }
        binding.btnComplete.setOnClickListener {
            if(materialList.isEmpty()) {
                showCustomToast("재료를 하나 이상 추가해주세요")
                return@setOnClickListener
            }
            DrinkUtil.materialList = materialList
            DrinkUtil.DrinkName = safeArgs.name

            if(etStepList[curStep].text.isNotEmpty()) {
                stepList.add(AddPostRequest.Step(etStepList[curStep].text.toString(),curStep+1))
            }
            DrinkUtil.stepList = stepList
            findNavController().popBackStack()
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {

    }

    companion object {
        const val TAG = "WriteRecipeFragment"
    }
}