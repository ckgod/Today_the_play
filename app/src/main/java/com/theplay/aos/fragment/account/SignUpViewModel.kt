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

class SignUpViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _randomNickResponse : MutableLiveData<RandomNickNameResponse> = MutableLiveData()
    val randomNickResponse get() = _randomNickResponse

    fun getRandomNick() {
        CompositeDisposable().add(
            remoteRepository.getRandomNick()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        randomNickResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        randomNickResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "SignUpViewModel"
    }
}