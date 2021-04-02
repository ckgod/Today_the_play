package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse
import com.theplay.aos.api.model.PostLikeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _myPageTopResponse : MutableLiveData<MyPageTopResponse> = MutableLiveData()
    val myPageTopResponse get() = _myPageTopResponse

    fun getMyPageTopInfo() {
        CompositeDisposable().add(
            remoteRepository.getMyPageTopInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        myPageTopResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        myPageTopResponse.postValue(null)
                    })
        )
    }

    private var _mainBoardResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val mainBoardResponse get() = _mainBoardResponse

    fun getMainBoard(pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getMainBoard(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        mainBoardResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        mainBoardResponse.postValue(null)
                    })
        )
    }

    private var _postLikeResponse : MutableLiveData<PostLikeResponse> = MutableLiveData()
    val postLikeResponse get() = _postLikeResponse

    fun postLike(postId : Int) {
        CompositeDisposable().add(
            remoteRepository.postLike(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        postLikeResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        postLikeResponse.postValue(null)
                    })
        )
    }

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
        const val TAG = "HomeViewModel"
    }
}