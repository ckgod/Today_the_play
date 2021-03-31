package com.theplay.aos.fragment.setting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class SettingViewModel() : ViewModel() {
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

    private val _putChangeNickNameResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val putChangeNickNameResponse get() = _putChangeNickNameResponse

    fun putNickName(changeNickRequest: ChangeNickRequest) {
        CompositeDisposable().add(
            remoteRepository.putChangeNickName(changeNickRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        putChangeNickNameResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        putChangeNickNameResponse.postValue(null)
                    })
        )
    }

    private val _putChangePwResponse : MutableLiveData<DefaultResponse> = MutableLiveData()
    val putChangePwResponse get() = _putChangePwResponse

    fun putChangePw(changePwRequest: ChangePwRequest) {
        CompositeDisposable().add(
            remoteRepository.putChangePw(changePwRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        putChangePwResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        if (throwable is HttpException) {
                            if(throwable.code() == 409) {
                                putChangePwResponse.postValue(DefaultResponse(-1007,"기존 비밀번호가 일치하지 않습니다.",false))
                            }
                        }
                        else {
                            putChangePwResponse.postValue(null)
                        }
                    })
        )
    }

    private val _getPrivacyStatusResponse : MutableLiveData<PrivacyStatusResponse> = MutableLiveData()
    val getPrivacyStatusResponse get() = _getPrivacyStatusResponse

    fun getPrivacy() {
        CompositeDisposable().add(
            remoteRepository.getPrivacyStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getPrivacyStatusResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getPrivacyStatusResponse.postValue(null)
                    })
        )
    }

    private val _noticeResponse : MutableLiveData<NoticeResponse> = MutableLiveData()
    val noticeResponse get() = _noticeResponse

    fun getNotice() {
        CompositeDisposable().add(
            remoteRepository.getNotice()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        noticeResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        noticeResponse.postValue(null)
                    })
        )
    }

    private val _noticeDetailResponse : MutableLiveData<NoticeDetailResponse> = MutableLiveData()
    val noticeDetailResponse get() = _noticeDetailResponse

    fun getNoticeDetail(noticeId : Int) {
        CompositeDisposable().add(
            remoteRepository.getNoticeDetail(noticeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        noticeDetailResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        noticeDetailResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "SettingViewModel"
    }
}