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
                        Log.d(TAG,"throwable.localizedMessage : ${throwable.localizedMessage}")
                        //loginResponse.postValue(null)
                        if (throwable is HttpException) {
                            if(throwable.code() == 400) {
                                loginResponse.postValue(LoginResponse(-1005,"","비밀번호를 다시 입력해주세요.",false))
                            }
                            else if(throwable.code() == 404) {
                                loginResponse.postValue(LoginResponse(-1000,"","존재하지 않는 회원입니다.",false))
                            }
                            else {
                                loginResponse.postValue(null)
                            }
                            Log.d(TAG, "code is ${throwable.code()}")
                            Log.d(TAG, "message is ${throwable.response().toString()}")
                        }
                        else {
                            loginResponse.postValue(null)
                        }
            })
        )
    }

    companion object{
        const val TAG = "LoginFragmentViewModel"
    }
}