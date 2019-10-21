package webapp

import com.rafag.flightplanner.webapp.redirect
import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.routing.Route

const val SIGN_OUT = "singout"

@Location(SIGN_OUT)
class SignOut

fun Route.signOut() {
    get<SignOut> {
        call.redirect(SignIn())
    }
}