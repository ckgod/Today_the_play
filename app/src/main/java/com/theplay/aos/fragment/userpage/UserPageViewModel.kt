package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserPageViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _userPageTopResponse : MutableLiveData<MyPageTopResponse> = MutableLiveData()
    val userPageTopResponse get() = _userPageTopResponse

    fun getUserTopInfo(userId : Int) {
        CompositeDisposable().add(
            remoteRepository.getUserTopInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        userPageTopResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        userPageTopResponse.postValue(null)
                    })
        )
    }

    private var _userPageLikesResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val userPageLikesResponse get() = _userPageLikesResponse

    fun getUserLikes(userId: Int, pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getUserLikes(userId,pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        userPageLikesResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        userPageLikesResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "UserPageViewModel"
    }
}