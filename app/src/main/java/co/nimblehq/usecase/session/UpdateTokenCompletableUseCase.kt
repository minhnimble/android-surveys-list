package co.nimblehq.usecase.session

import co.nimblehq.data.error.Ignored
import co.nimblehq.data.lib.schedulers.RxSchedulerProvider
import co.nimblehq.data.api.response.OAuthResponse
import co.nimblehq.data.model.AuthData
import co.nimblehq.data.storage.SecureStorage
import co.nimblehq.usecase.base.CompletableUseCase
import io.reactivex.Completable
import javax.inject.Inject

class UpdateTokenCompletableUseCase @Inject constructor(
    rxSchedulerProvider: RxSchedulerProvider,
    private val secureStorage: SecureStorage
) : CompletableUseCase<UpdateTokenCompletableUseCase.Input>(
    rxSchedulerProvider.io(),
    rxSchedulerProvider.main(),
    ::Ignored
) {

    data class Input(
        val response: AuthData
    )

    override fun create(input: Input): Completable {
        return Completable.fromAction {
            secureStorage.apply {
                userAccessToken = input.response.accessToken
                userAccessTokenCreatedAt = input.response.createdAt
                userAccessTokenExpiresIn = input.response.expiresIn
                userRefreshToken = input.response.refreshToken
                userTokenType = input.response.tokenType
            }
        }
    }
}