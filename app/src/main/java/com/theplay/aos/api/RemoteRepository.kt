package com.theplay.aos.api

import com.theplay.aos.api.model.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

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

    fun postFollow(userId : Int) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postFollow(userId)
    }

    fun cancelFollowing(userId : Int) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).cancelFollowing(userId)
    }

    fun deleteFollower(userId: Int) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).deleteFollower(userId)
    }

    fun banFollower(userId: Int) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).banFollower(userId)
    }

    fun getMyRecipes(pageNumber: Int, pageSize: Int) : Observable<MyRecipeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getMyRecipes(pageNumber, pageSize)
    }

    fun getHotRecipeDetail(tagName : String, pageNumber : Int, pageSize : Int) : Observable<HotRecipeDetailResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getHotRecipesDetail(tagName, pageNumber, pageSize)
    }

    fun getUserTopInfo(userId : Int) : Observable<UserPageTopResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getUserTopInfo(userId)
    }

    fun getUserPosts(userId : Int, pageNumber: Int, pageSize: Int) : Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getUserPosts(userId, pageNumber, pageSize)
    }

    fun getUserLikes(userId : Int, pageNumber: Int, pageSize: Int) : Observable<MainBoardResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getUserLikes(userId, pageNumber, pageSize)
    }

    fun getUserFollowers(userId: Int) : Observable<FollowListResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getUserFollowers(userId)
    }

    fun getUserMyRecipes(userId : Int, pageNumber: Int, pageSize: Int) : Observable<MyRecipeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).getUserMyRecipe(userId,pageNumber, pageSize)
    }

    fun postReport(reportRequest: ReportRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postReportPost(reportRequest)
    }

    fun postFindPassword(findPasswordRequest: FindPasswordRequest) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postFindPassword(findPasswordRequest)
    }

    fun deletePost(postId: Int) : Observable<DefaultResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).deletePost(postId)
    }

    fun postLikeComment(commentId : Int) : Observable<CommentLikeResponse> {
        return RetrofitCreator.create(
            UserApi.UserApiImpl::class.java
        ).postCommentLike(commentId)
    }
}