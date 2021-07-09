package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.DefaultResponse
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse
import com.theplay.aos.api.model.UserPageTopResponse
import com.theplay.aos.fragment.home.FollowViewModel
import com.theplay.aos.fragment.home.MainBoardViewModel
import com.theplay.aos.fragment.mypage.MyPageFollowViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class UserPageViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _userPageTopResponse : MutableLiveData<UserPageTopResponse> = MutableLiveData()
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

    private var _postFollowingResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val postFollowingResponse get() = _postFollowingResponse

    fun postFollow(userId: Int) {
        CompositeDisposable().add(
            remoteRepository.postFollow(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        postFollowingResponse.postValue(response)
                    }, { throwable ->
                        if(throwable is HttpException) {
                            when(throwable.code()) {
                                409 -> { // 이미 팔로우 되어있을때
                                    postFollowingResponse.postValue(DefaultResponse(409,"이미 팔로우 중인 사용자입니다.", true))
                                }
                                else -> {
                                    postFollowingResponse.postValue(null)
                                }
                            }
                        }
                        else {
                            Log.d(TAG, "throwable.localizedMessage${throwable.localizedMessage}")
                            postFollowingResponse.postValue(null)
                        }
                    })
        )
    }

    private var _cancelFollowingResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val cancelFollowingResponse get() = _cancelFollowingResponse

    fun cancelFollowing(userId :Int) {
        CompositeDisposable().add(
            remoteRepository.cancelFollowing(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        cancelFollowingResponse.postValue(response)
                    }, { throwable ->
                        Log.d(MyPageFollowViewModel.TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        cancelFollowingResponse.postValue(null)
                    })
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

    companion object{
        const val TAG = "UserPageViewModel"
    }
}