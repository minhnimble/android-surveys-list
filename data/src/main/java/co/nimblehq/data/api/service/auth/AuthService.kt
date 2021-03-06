package co.nimblehq.data.api.service.auth

import co.nimblehq.data.api.request.OAuthRequest
import co.nimblehq.data.api.response.auth.OAuthResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Providing APIs to communicate with Nimble Surveys for OAuth services
 */
interface AuthService {

    @POST("/api/v1/oauth/token")
    fun loginByPasswordWithEmail(
        @Body request: OAuthRequest.LoginByPasswordWithEmail
    ): Single<OAuthResponse>

    @POST("/api/v1/oauth/revoke")
    fun logout(
        @Body request: OAuthRequest.Logout
    ): Completable

    @POST("/api/v1/oauth/token")
    fun refreshToken(
        @Body request: OAuthRequest.RefreshToken
    ): Single<OAuthResponse>
}
