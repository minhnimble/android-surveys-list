package co.nimblehq.usecase.session

import co.nimblehq.data.authenticator.TokenRefresher
import co.nimblehq.data.error.SessionError.RefreshTokenError
import co.nimblehq.data.lib.schedulers.RxSchedulerProvider
import co.nimblehq.data.model.AuthData
import co.nimblehq.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class RefreshTokenIfNeededSingleUseCase @Inject constructor(
    rxSchedulerProvider: RxSchedulerProvider,
    private val tokenRefresher: TokenRefresher
) : SingleUseCase<AuthData, AuthData>(
    rxSchedulerProvider.io(),
    rxSchedulerProvider.main(),
    ::RefreshTokenError
) {

    override fun create(input: AuthData): Single<AuthData> {
        return if (input.isExpired) {
            tokenRefresher.refreshToken(
                input.refreshToken
            )
        } else {
            Single.just(input)
        }
    }
}
