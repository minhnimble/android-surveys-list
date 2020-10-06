package co.nimblehq.data.api.request.helper

import co.nimblehq.data.lib.common.OAUTH_GRANT_TYPE_PASSWORD
import co.nimblehq.data.api.common.secrets.Secrets
import co.nimblehq.data.api.request.LoginByPasswordWithEmailRequest

object RequestHelper {

    fun loginWithEmailRequest(
        email: String,
        password: String
    ): LoginByPasswordWithEmailRequest {
        return LoginByPasswordWithEmailRequest(
            OAUTH_GRANT_TYPE_PASSWORD,
            email,
            password,
            Secrets.clientId,
            Secrets.clientSecret
        )
    }
}