package com.theplay.aos.api

import com.theplay.aos.api.model.*
import io.reactivex.Observable

class RemoteRepository{

    fun postLogin(loginRequest: LoginRequest): Observable<LoginResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postLogin(params = loginRequest)
    }

    fun postSignUp(signUpRequest: SignUpRequest): Observable<SignUpResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postSignUp(params = signUpRequest)
    }

    fun putNickName(nickNameRequest: NickNameRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).putNickName(params = nickNameRequest)
    }

    fun getNotice() : Observable<NoticeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getNotice()
    }

    fun getNoticeDetail(noticeId : Int) :Observable<NoticeDetailResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getNoticeDetail(noticeId)
    }

    fun getRandomNick() : Observable<RandomNickNameResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getRandomNick()
    }


}