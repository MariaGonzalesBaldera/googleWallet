package com.example.myapplication

import com.google.gson.annotations.SerializedName

class AccessToken(
    @SerializedName("access_token") val accessToken: String?,
    @SerializedName("expires_in") val expiresIn: Int?
) {
    @SerializedName("refresh_expires_in")
    val refreshExpiresIn: Int? = null

    @SerializedName("refresh_token")
    val refreshToken: String? = null

    @SerializedName("token_type")
    val tokenType: String? = null

    @SerializedName("id_token")
    val idToken: String? = null

    @SerializedName("not_before_policy")
    val notBeforePolicy: String? = null

    @SerializedName("session_state")
    val sessionState: String? = null

    @SerializedName("scope")
    val scope: String? = null
}