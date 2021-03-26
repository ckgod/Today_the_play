package com.theplay.aos.fragment.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginFragmentViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _loginResponse: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginResponse get() = _loginResponse


    fun postLogin(loginRequest : LoginRequest) {
        CompositeDisposable().add(
            remoteRepository.postLogin(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { response ->
                    loginResponse.postValue(response)
                }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        loginResponse.postValue(null)
            })
        )
    }

    companion object{
        const val TAG = "LoginFragmentViewModel"
    }
}