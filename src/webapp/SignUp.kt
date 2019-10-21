package webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

const val SIGN_UP = "signup"

@Location(SIGN_UP)
class SignUp

fun Route.signUp() {
    get<SignUp> {
        call.respondText("Sign up page")
    }
}