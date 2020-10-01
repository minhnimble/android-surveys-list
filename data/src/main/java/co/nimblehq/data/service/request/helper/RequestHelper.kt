package co.nimblehq.data.service.request.helper

import co.nimblehq.data.lib.common.OAUTH_GRANT_TYPE_PASSWORD
import co.nimblehq.data.service.common.secrets.Secrets
import co.nimblehq.data.service.request.AuthRequest

object RequestHelper {

    fun loginWithEmailRequest(
        email: String,
        password: String
    ): AuthRequest.LoginByPasswordWithEmail {
        return AuthRequest.LoginByPasswordWithEmail(
            OAUTH_GRANT_TYPE_PASSWORD,
            email,
            password,
            Secrets.clientId,
            Secrets.clientSecret
        )
    }
}
