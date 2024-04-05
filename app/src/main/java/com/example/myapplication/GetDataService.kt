package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface GetDataService {
    @FormUrlEncoded
    @POST("/realms/master/protocol/openid-connect/token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("client_secret") clientSecret: String,
        @Field("scope") scope: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<AccessToken>
}
interface GetDataServiceGoogle {
    @FormUrlEncoded
    @POST("/realms/master/protocol/openid-connect/token")
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("grant_type") grantType: String,
        @Field("client_secret") clientSecret: String,
        @Field("scope") scope: String,
    ): Call<AccessToken>
}