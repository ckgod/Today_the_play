package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.DefaultResponse
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.PostLikeResponse
import com.theplay.aos.fragment.home.HomeViewModel
import com.theplay.aos.fragment.home.MainBoardViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyPageBoardViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _getMyPostResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val getMyPostResponse get() = _getMyPostResponse

    fun getMyPost(pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getMyPost(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getMyPostResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getMyPostResponse.postValue(null)
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

    private var _deletePostResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val deletePostResponse get() = _deletePostResponse

    fun deletePost(postId: Int) {
        CompositeDisposable().add(
            remoteRepository.deletePost(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        deletePostResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG, "throwable.localizedMessage${throwable.localizedMessage}")
                        deletePostResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "MyPageBoardViewModel"
    }
}