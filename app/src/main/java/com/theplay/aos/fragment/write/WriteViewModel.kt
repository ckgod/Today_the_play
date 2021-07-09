package com.theplay.aos.fragment.write

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.*
import com.theplay.aos.fragment.home.HomeViewModel
import com.theplay.aos.fragment.mypage.MyPageBoardViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class WriteViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _settingProfileResponse : MutableLiveData<SettingProfileResponse> = MutableLiveData()
    val settingProfileResponse get() =_settingProfileResponse

    fun getSettingProfile() {
        CompositeDisposable().add(
            remoteRepository.getSettingProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        settingProfileResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        settingProfileResponse.postValue(null)
                    })
        )
    }

    private var _addPostResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val addPostResponse get() = _addPostResponse

    fun postAddPost(request : HashMap<String, RequestBody>, files : List<MultipartBody.Part>) {
        CompositeDisposable().add(
            remoteRepository.postAddPost(request,files)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        addPostResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        addPostResponse.postValue(null)
                    })
        )
    }

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
                        Log.d(MyPageBoardViewModel.TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getMyPostResponse.postValue(null)
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
                        Log.d(HomeViewModel.TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        mainBoardResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "WriteViewModel"
    }
}