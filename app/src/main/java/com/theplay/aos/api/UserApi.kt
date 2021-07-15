package com.theplay.aos.api

import com.theplay.aos.api.model.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

class UserApi {
    interface UserApiImpl {
        @POST("/v1/sign-in")
        fun postLogin(@Body params: LoginRequest): Observable<LoginResponse>

        @POST("/v1/sign-up")
        fun postSignUp(@Body params : SignUpRequest) : Observable<SignUpResponse>

        @PUT("/v1/user/nickname")
        fun putNickName(@Body params : NickNameRequest) : Observable<DefaultResponse>

        @GET("/v1/user/notice")
        fun getNotice() : Observable<NoticeResponse>

        @GET("/v1/user/notice/{noticeId}")
        fun getNoticeDetail(@Path("noticeId") noticeId : Int):Observable<NoticeDetailResponse>

        @GET("/v1/random-nickname")
        fun getRandomNick():Observable<RandomNickNameResponse>

        @GET("/v1/user/main-info")
        fun getMyPageTopInfo() : Observable<MyPageTopResponse>

        @GET("/v1/popular-recipes") // 인기 레시피 조회
        fun getHotRecipe(@Query("pageNumber") pageNumber : Int, @Query("pageSize") pageSize : Int) : Observable<HotRecipeResponse>

        @GET("/v1/main-posts")
        fun getMainBoard(@Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        @GET("/v1/user/setting")
        fun getSettingProfile() :Observable<SettingProfileResponse>

        @PUT("/v1/user/nickname")
        fun putChangeNickName(@Body params : ChangeNickRequest) : Observable<DefaultResponse>

        @PUT("/v1/user/password")
        fun putChangePw(@Body params : ChangePwRequest) : Observable<DefaultResponse>

        @GET("/v1/user/show-yn")
        fun getPrivacyYn() : Observable<PrivacyStatusResponse>

        @Multipart
        @POST("/v1/post")
        fun postAddPost(@PartMap request : HashMap<String, RequestBody>, @Part files : List<MultipartBody.Part>) : Observable<DefaultResponse>

        @POST("/v1/post/{postId}/like")
        fun postLike(@Path("postId") postId : Int) : Observable<PostLikeResponse>

        @GET("/v1/post/{postId}/comments")
        fun getComment(@Path("postId") postId: Int) : Observable<CommentResponse>

        @POST("/v1/post/{postId}/comment")
        fun postAddComment(@Path("postId") postId: Int, @Body params : AddCommentRequest) : Observable<DefaultResponse>

        @GET("/v1/user/posts/like")
        fun getLikedPost(@Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        @GET("/v1/user/posts")
        fun getMyPost(@Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        @POST("/v1/recipe/{alcoholTagId}")
        fun postSaveRecipe(@Path("alcoholTagId") alcoholTagId : Int) : Observable<RecipeSaveResponse>

        @GET("/v1/user/followers")
        fun getFollower() : Observable<FollowListResponse>

        @GET("/v1/user/followings")
        fun getFollowing() : Observable<FollowListResponse>

        @GET("/v1/following-posts")
        fun getFollowPeed(@Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        // 팔로우하기
        @POST("/v1/user/following/{userId}")
        fun postFollow(@Path("userId") userID : Int) : Observable<DefaultResponse>

        // 팔로잉취소
        @DELETE("/v1/user/followings/{userId}")
        fun cancelFollowing(@Path("userId") userID: Int) : Observable<DefaultResponse>

        // 팔로워삭제
        @DELETE("/v1/user/followers/{userId}")
        fun deleteFollower(@Path("userId") userID: Int) : Observable<DefaultResponse>

        // 팔로워차단
        @POST("/v1/user/followers/block/{userId}")
        fun banFollower(@Path("userId") userID : Int) : Observable<DefaultResponse>

        // 나의 레시피 조회
        @GET("/v1/user/recipes")
        fun getMyRecipes(@Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MyRecipeResponse>

        // 인기레시피 태그별 조회
        @GET("/v1/popular-recipes/{tagName}")
        fun getHotRecipesDetail(@Path("tagName") tagName : String, @Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize : Int) : Observable<HotRecipeDetailResponse>

        // 선택유저 상단정보 조회
        @GET("/v1/user/{userId}/main-info")
        fun getUserTopInfo(@Path("userId") userId : Int) : Observable<UserPageTopResponse>

        // 선택유저 게시글 조회
        @GET("/v1/user/{userId}/posts")
        fun getUserPosts(@Path("userId") userId : Int, @Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        // 선택유저 좋아요 게시글 조회
        @GET("/v1/user/{userId}/posts/like")
        fun getUserLikes(@Path("userId") userID: Int, @Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MainBoardResponse>

        // 선택유저 팔로워리스트 조회
        @GET("/v1/user/{userId}/followers")
        fun getUserFollowers(@Path("userId") userID: Int) : Observable<FollowListResponse>

        // 선택유저 레시피저장한거 조회
        @GET("/v1/user/{userId}/recipes")
        fun getUserMyRecipe(@Path("userId") userID: Int, @Query("pageNumber") pageNumber: Int, @Query("pageSize") pageSize: Int) : Observable<MyRecipeResponse>

        // 신고하기
        @POST("/v1/post/report")
        fun postReportPost(@Body params : ReportRequest) : Observable<DefaultResponse>

        // 비밀번호 찾기
        @POST("/v1/user/password")
        fun postFindPassword(@Body params : FindPasswordRequest) : Observable<DefaultResponse>

        // 게시글 삭제
        @DELETE("/v1/post/{postId}")
        fun deletePost(@Path("postId") postId: Int) : Observable<DefaultResponse>

        // 댓글 좋아요
        @POST("/v1/comment/{postCommentId}/like")
        fun postCommentLike(@Path("postCommentId") commentId : Int) : Observable<CommentLikeResponse>

        // 게시글 수정
        @PUT("/v1/post/{postId}")
        fun putModifyPost(@Path("postId") postId: Int, @Body params: ModifyPostRequest) : Observable<DefaultResponse>


    }
}