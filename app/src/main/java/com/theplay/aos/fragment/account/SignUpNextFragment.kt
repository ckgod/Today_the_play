package com.theplay.aos.fragment.account

import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSignUpNextBinding
import com.theplay.aos.fragment.account.SignUpNextFragmentDirections

class SignUpNextFragment() : BaseKotlinFragment<FragmentSignUpNextBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up_next

    private var checkTos = false
    private var checkFirstNick = true
    private var checkSecondNick = false
    private var checkThirdNick = false

    override fun initStartView() {
        binding.btnBack.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.llTos.setOnClickListener {
            if(!checkTos) {
                binding.tvTos.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
                binding.ivTos.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_tos_true))
            }
            else {
                binding.tvTos.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                binding.ivTos.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_tos_false))
            }
            checkTos = !checkTos
        }
        binding.llFirstNick.setOnClickListener {
            checkFirstNick = true; checkSecondNick = false; checkThirdNick = false
            binding.ivFirstArrow.visibility = View.VISIBLE
            binding.tvFirstNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainForText))
            binding.ivSecondArrow.visibility = View.INVISIBLE
            binding.tvSecondNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            binding.ivThirdArrow.visibility = View.INVISIBLE
            binding.tvThirdNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        }
        binding.llSecondNick.setOnClickListener {
            checkFirstNick = false; checkSecondNick = true; checkThirdNick = false
            binding.ivFirstArrow.visibility = View.INVISIBLE
            binding.tvFirstNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            binding.ivSecondArrow.visibility = View.VISIBLE
            binding.tvSecondNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainForText))
            binding.ivThirdArrow.visibility = View.INVISIBLE
            binding.tvThirdNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
        }
        binding.llThirdNick.setOnClickListener {
            binding.llThirdNick.visibility = View.INVISIBLE
            binding.llThirdNick.isClickable = false
            binding.etThirdNick.visibility = View.VISIBLE
            binding.etThirdNick.requestFocus()
            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding.etThirdNick, InputMethodManager.SHOW_FORCED)
        }
        binding.etThirdNick.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if(binding.etThirdNick.text.isNotEmpty()) {
                        hideKeyboard(binding.etThirdNick)
                        checkFirstNick = false; checkSecondNick = false; checkThirdNick = true

                        binding.tvThirdNick.text = binding.etThirdNick.text.toString()
                        binding.llThirdNick.visibility = View.VISIBLE
                        binding.llThirdNick.isClickable = true
                        binding.etThirdNick.visibility = View.INVISIBLE
                        binding.etThirdNick.clearFocus()

                        binding.tvFirstNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
                        binding.ivFirstArrow.visibility = View.INVISIBLE
                        binding.tvSecondNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
                        binding.ivSecondArrow.visibility = View.INVISIBLE
                        binding.tvThirdNick.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainForText))
                        binding.ivThirdArrow.visibility = View.VISIBLE
                    }
                    else {
                        hideKeyboard(binding.etThirdNick)
                        binding.etThirdNick.clearFocus()
                    }
                    true
                }
                else -> false
            }
        }
        binding.btnComplete.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                if(!checkTos) {
                    showCustomToast("이용약관을 동의해주세요")
                    return
                }
                if(checkFirstNick) {

                }
                else if(checkSecondNick) {

                }
                else if(checkThirdNick) {

                }

                findNavController().navigate(SignUpNextFragmentDirections.actionSignUpNextFragmentToMainFragment())
            }
        })
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        binding.etThirdNick.clearFocus()
    }


    companion object {
        const val TAG = "SignUpNextFragment"
    }
}