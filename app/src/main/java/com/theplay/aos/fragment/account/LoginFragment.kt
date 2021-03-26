package com.theplay.aos.fragment.account

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentLoginBinding
import com.theplay.aos.fragment.account.LoginFragmentDirections

class LoginFragment() : BaseKotlinFragment<FragmentLoginBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    // viewModel
    private val viewModel by lazy { LoginFragmentViewModel() }

    override fun initStartView() {
        binding.btnLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
            }
        })
    }

    override fun initDataBinding() {
//        viewModel.loginResponse.observe(this@LoginFragment, Observer {
//            if (it == null) {
//                hideCircleProgress()
//                showNetworkError()
//            } else {
//                Log.d(TAG, it.message + it.code)
//                if (it.code == 100) {
//                    Log.d(TAG, it.result)
//                    val preferences: SharedPreferences = requireContext().getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE)
//                    val editor = preferences.edit()
//                    editor.putString(X_ACCESS_TOKEN, it.result)
//                    editor.apply()
//                    viewModel.getUserInfo()
//                } else {
//                    hideCircleProgress()
//                    var dialog = CustomDialogOneButton(context,getString(R.string.login_invalid_id),getString(R.string.confirm))
//                    dialog.show()
//                }
//            }
//        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "LoginFragment"
    }
}