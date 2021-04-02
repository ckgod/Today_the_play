package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MainBoardResponse
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

    companion object{
        const val TAG = "MyPageBoardViewModel"
    }
}