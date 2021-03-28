package com.theplay.aos.api

import com.theplay.aos.api.model.*
import io.reactivex.Observable
import retrofit2.http.*

class UserApi {
    interface UserApiImpl {
        @POST("v1/sign-in")
        fun postLogin(@Body params: LoginRequest): Observable<LoginResponse>

        @POST("v1/sign-up")
        fun postSignUp(@Body params : SignUpRequest) : Observable<SignUpResponse>

        @PUT("v1/user/nickname")
        fun putNickName(@Body params : NickNameRequest) : Observable<DefaultResponse>

        @GET("v1/user/notice")
        fun getNotice() : Observable<NoticeResponse>

        @GET("v1/user/notice/{noticeId}")
        fun getNoticeDetail(@Path("noticeId") noticeId : Int):Observable<NoticeDetailResponse>

        @GET("/v1/sign-up")
        fun getRandomNick():Observable<RandomNickNameResponse>

//        //15
//        @GET("diaries")
//        fun getDiaries(
//                @Query("page") page : Int,
//                @Query("status") status : Int
//        ) : Observable<DiariesResponse>
//
//        // 15-4
//        @GET("/notice/{diaryID}")
//        fun getNotice(@Path("diaryID") diaryID : Int):Observable<GetNoticeResponse>
//
//        // 19
//        @GET("/diaries/{diaryID}/posts")
//        fun getDiaryList(
//                @Path("diaryID") diaryID: Int,
//                @Query("page") page : Int
//                //@Query("post") post : Int
//        ) :Observable<DiaryListResponse>
//
//        // 28
//        @POST("posts/{postID}/like")
//        fun postLike(
//                @Path("postID") postID: Int,
//                @Query("type") type: Int,
//                @Body params:LikeRequest
//        ) : Observable<DefaultResponse>


    }
}