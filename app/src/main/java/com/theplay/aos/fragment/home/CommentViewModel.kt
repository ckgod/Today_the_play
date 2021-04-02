package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class CommentViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _commentResponse : MutableLiveData<CommentResponse> = MutableLiveData()
    val commentResponse get() = _commentResponse

    fun getComment(postId: Int) {
        CompositeDisposable().add(
            remoteRepository.getComment(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        commentResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")

                        if (throwable is HttpException) {
                            if(throwable.code() == 404) {
                                commentResponse.postValue(CommentResponse(404, listOf(),"댓글이 없습니다.",true))
                            }
                            else {
                                commentResponse.postValue(null)
                            }
                        }
                        else {
                            commentResponse.postValue(null)
                        }
                    })
        )
    }

    private var _addCommentResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val addCommentResponse get() = _addCommentResponse

    fun addPostComment(postId: Int, addCommentRequest: AddCommentRequest) {
        CompositeDisposable().add(
            remoteRepository.postComment(postId, addCommentRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        addCommentResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        addCommentResponse.postValue(null)
                    })
        )
    }


    companion object{
        const val TAG = "CommentViewModel"
    }
}