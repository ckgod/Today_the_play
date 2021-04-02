package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class MyPageGoodViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _getLikedResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val getLikedResponse get() = _getLikedResponse

    fun getLikedPost(pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getLikedPost(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getLikedResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getLikedResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "MyPageGoodViewModel"
    }
}