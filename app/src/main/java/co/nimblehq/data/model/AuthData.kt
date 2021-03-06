package co.nimblehq.data.model

import co.nimblehq.data.api.response.auth.OAuthResponse

data class AuthData(
    val accessToken: String = "",
    val createdAt: Long = 0,
    val expiresIn: Long = 0,
    val refreshToken: String = "",
    val tokenType: String = ""
) {
    val isValid: Boolean
        get() = accessToken.isNotEmpty() && tokenType.isNotEmpty() && refreshToken.isNotEmpty()

    val isExpired: Boolean
        get() = System.currentTimeMillis() / 1000 >= createdAt + expiresIn
}

fun OAuthResponse.toAuthData() = with(data.attributes) {
    AuthData(
        accessToken,
        createdAt,
        expiresIn,
        refreshToken,
        tokenType
    )
}
