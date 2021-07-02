package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.FollowListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserPageFollowViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _followerListResponse : MutableLiveData<FollowListResponse> = MutableLiveData()
    val followerListResponse get() = _followerListResponse

    fun getFollower(userId : Int) {
        CompositeDisposable().add(
            remoteRepository.getUserFollowers(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        followerListResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        followerListResponse.postValue(null)
                    })
        )
    }
//
//    private var _followingListResponse : MutableLiveData<FollowListResponse> = MutableLiveData()
//    val followingListResponse get() = _followingListResponse
//
//    fun getFollowing() {
//        CompositeDisposable().add(
//            remoteRepository.getFollowing()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { response ->
//                        followingListResponse.postValue(response)
//                    }, { throwable ->
//                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
//                        followingListResponse.postValue(null)
//                    })
//        )
//    }

    companion object{
        const val TAG = "UserPageFollowViewModel"
    }
}