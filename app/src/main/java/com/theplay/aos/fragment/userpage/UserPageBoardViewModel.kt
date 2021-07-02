package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MainBoardResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserPageBoardViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _getUserPostsResponse : MutableLiveData<MainBoardResponse> = MutableLiveData()
    val getUserPostsResponse get() = _getUserPostsResponse

    fun getUserPosts(userId : Int, pageNumber: Int, pageSize: Int) {
        CompositeDisposable().add(
            remoteRepository.getUserPosts(userId, pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getUserPostsResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getUserPostsResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "UserPageBoardViewModel"
    }
}