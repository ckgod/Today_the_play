package com.theplay.aos.api

import com.theplay.aos.api.model.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody

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

    fun getMyPageTopInfo() : Observable<MyPageTopResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getMyPageTopInfo()
    }

    fun getHotRecipe(pageNumber : Int, pageSize : Int) : Observable<HotRecipeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getHotRecipe(pageNumber, pageSize)
    }

    fun getMainBoard(pageNumber : Int, pageSize : Int) : Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getMainBoard(pageNumber, pageSize)
    }

    fun getSettingProfile() : Observable<SettingProfileResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getSettingProfile()
    }

    fun putChangeNickName(changeNickRequest: ChangeNickRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).putChangeNickName(changeNickRequest)
    }

    fun putChangePw(changePwRequest: ChangePwRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).putChangePw(changePwRequest)
    }

    fun getPrivacyStatus() : Observable<PrivacyStatusResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getPrivacyYn()
    }

    fun postAddPost(request : HashMap<String, RequestBody>, files : List<MultipartBody.Part>) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postAddPost(request, files)
    }

    fun postLike(postId : Int) : Observable<PostLikeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postLike(postId)
    }

    fun getComment(postId: Int) : Observable<CommentResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getComment(postId)
    }

    fun postComment(postId: Int, addCommentRequest: AddCommentRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postAddComment(postId, addCommentRequest)
    }

    fun getLikedPost(pageNumber: Int,pageSize: Int) : Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getLikedPost(pageNumber, pageSize)
    }

    fun getMyPost(pageNumber: Int,pageSize: Int) : Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getMyPost(pageNumber, pageSize)
    }

    fun postSaveRecipe(alcoholTagId : Int) : Observable<RecipeSaveResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postSaveRecipe(alcoholTagId)
    }

    fun getFollower() : Observable<FollowListResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getFollower()
    }

    fun getFollowing() : Observable<FollowListResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getFollowing()
    }

    fun getFollowPeed(pageNumber: Int, pageSize: Int) :Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getFollowPeed(pageNumber, pageSize)
    }

}