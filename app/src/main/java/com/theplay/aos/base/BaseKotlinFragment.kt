package com.theplay.aos.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.theplay.aos.MainActivity
import com.theplay.aos.R
import com.theplay.aos.customview.DelayedProgressDialog
import com.theplay.aos.customview.LottieDialog
import com.theplay.aos.utils.ViewUtils

abstract class BaseKotlinFragment<T : ViewDataBinding> : Fragment() {
    var mProgressCircle: DelayedProgressDialog? = null
    var mLottieLoading: LottieDialog? = null
    var progress : com.theplay.aos.customview.ProgressDialog? = null

//    lateinit var binding: T
    var databinding: T? = null
    lateinit var binding : T

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     * ex) R.layout.activity_sbs_main
     */
    abstract val layoutResourceId: Int
    open val showBottomNav : Boolean = true

    /**
     * 레이아웃을 띄운 직후 호출.
     * 뷰나 액티비티의 속성 등을 초기화.
     * ex) 리사이클러뷰, 툴바, 드로어뷰..
     */
    abstract fun initStartView()

    /**
     * 두번째로 호출.
     * 데이터 바인딩 및 rxjava 설정.
     * ex) rxjava observe, databinding observe..
     */
    abstract fun initDataBinding()

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
    abstract fun initAfterBinding()

    /**
     * 초기설정 이외, onResume 됬을때의 View Update 를 위한 함수
     * Observing 형식의 데이터만 아닌 경우에만 해당
     */
    abstract fun reLoadUI()



    private var isSetBackButtonValid = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!(::binding.isInitialized)) {
            binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            initStartView()
            initDataBinding()
            initAfterBinding()
        }
        (requireActivity() as? MainActivity)?.setBottomNavVisible(showBottomNav)
        reLoadUI()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        binding = databinding!!
//        databinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun hideKeyboard(view: View) {
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @Suppress("DEPRECATION")
    fun setStatusBar(window:Window, view : View, context: Context) {
        // status bar 투명하게 하기sSSSSfghjk
        view.setPadding(0, ViewUtils.getStatusBarHeight(context),0,0)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
    }

    abstract inner class OnSingleClickListener : View.OnClickListener {
        //마지막으로 클릭한 시간
        private var mLastClickTime: Long = 0
        //중복클릭시간차이
        private val MIN_CLICK_INTERVAL: Long = 1000

        abstract fun onSingleClick(v: View)

        override fun onClick(v: View) {
            //현재 클릭한 시간
            val currentClickTime = SystemClock.uptimeMillis()
            //이전에 클릭한 시간과 현재시간의 차이
            val elapsedTime = currentClickTime - mLastClickTime
            //마지막클릭시간 업데이트
            mLastClickTime = currentClickTime

            //내가 정한 중복클릭시간 차이를 안넘었으면 클릭이벤트 발생못하게 return
            if (elapsedTime <= MIN_CLICK_INTERVAL)
                return
            //중복클릭시간 아니면 이벤트 발생
            onSingleClick(v)
        }
    }

    fun interceptBackPressed() : Boolean {
        findNavController().popBackStack()
        return true
    }

    fun backStep() {
        findNavController().popBackStack()
    }

    fun showCustomToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    fun showNetworkError() {
        Toast.makeText(context, getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }

    fun showLottie() {
        if(mLottieLoading == null) {
            mLottieLoading = LottieDialog()
        }
        mLottieLoading?.show(requireActivity().supportFragmentManager, "base")
    }

    fun hideLottie() {
        mLottieLoading?.cancel()
    }

    fun showProgress() {
        if(progress == null) {
            progress = com.theplay.aos.customview.ProgressDialog(requireContext())
        }
        progress?.show()
    }

    fun hideProgress() {
        progress?.dismiss()
    }


}