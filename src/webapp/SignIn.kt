package webapp

import com.rafag.flightplanner.repositories.user.UserRepository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

const val SIGN_IN = "signin"

@Location(SIGN_IN)
class SignIn

fun Route.signIn(userRepository: UserRepository, userHashFunction: (String) -> String) {
    get<SignIn> {
        call.respond(FreeMarkerContent("signin.ftl", null))
    }
}