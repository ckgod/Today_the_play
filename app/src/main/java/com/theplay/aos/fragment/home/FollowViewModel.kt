package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse
import com.theplay.aos.api.model.PostLikeResponse
import com.theplay.aos.api.model.RecipeSaveResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class FollowViewModel() : ViewModel() {
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

    private var _followingPostResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val followingPostResponse get() = _followingPostResponse

    fun getFollowingPostResponse(pageNumber: Int, pageSize: Int) {
        CompositeDisposable().add(
            remoteRepository.getFollowPeed(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        followingPostResponse.postValue(response)
                    }, { throwable ->
                        Log.d(HomeViewModel.TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        followingPostResponse.postValue(null)
                    }
                )
        )
    }

    private var _followPeedResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val followPeedResponse get() = _followPeedResponse

    fun getFollowPeed(pageNumber :Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getFollowPeed(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        followPeedResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        followPeedResponse.postValue(null)
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

    private var _postSaveRecipeResponse : MutableLiveData<RecipeSaveResponse> = MutableLiveData()
    val postSaveRecipeResponse get() = _postSaveRecipeResponse

    fun postSaveRecipe(tagId : Int) {
        CompositeDisposable().add(
            remoteRepository.postSaveRecipe(tagId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        postSaveRecipeResponse.postValue(response)
                    }, { throwable ->
                        if(throwable is HttpException) {
                            when(throwable.code()) {
                            }
                        }
                        else {
                            Log.d(TAG, "throwable.localizedMessage${throwable.localizedMessage}")
                            postSaveRecipeResponse.postValue(null)
                        }
                    })
        )
    }


    companion object{
        const val TAG = "FollowViewModel"
    }
}