package webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

const val SIGN_IN = "signin"

@Location(SIGN_IN)
class SignIn

fun Route.signin() {
    get<SignIn> {
        call.respondText("Sign in")
    }
}