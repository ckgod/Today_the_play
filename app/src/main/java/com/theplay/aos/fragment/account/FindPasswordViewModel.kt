package com.theplay.aos.fragment.account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.DefaultResponse
import com.theplay.aos.api.model.FindPasswordRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FindPasswordViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _findPasswordResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val findPasswordResponse get() = _findPasswordResponse

    fun postFindPassword(findPasswordRequest: FindPasswordRequest) {
        CompositeDisposable().add(
            remoteRepository.postFindPassword(findPasswordRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        findPasswordResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage : ${throwable.localizedMessage}")
                        findPasswordResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "FindPasswordViewModel"
    }
}