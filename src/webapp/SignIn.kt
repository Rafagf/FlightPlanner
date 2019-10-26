package webapp

import com.rafag.flightplanner.MIN_PASSWORD_LENGTH
import com.rafag.flightplanner.MIN_USER_ID_LENGTH
import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.repositories.user.UserRepository
import com.rafag.flightplanner.userNameValid
import com.rafag.flightplanner.webapp.flights.Flights
import com.rafag.flightplanner.webapp.redirect
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set

const val SIGN_IN = "signin"

@Location(SIGN_IN)
data class SignIn(val userId: String = "", val error: String = "")

fun Route.signIn(userRepository: UserRepository, hashFunction: (String) -> String) {

    post<SignIn> {
        val signInParameters = call.receive<Parameters>()
        val userId = signInParameters["userId"] ?: return@post call.redirect(it)
        val password = signInParameters["password"] ?: return@post call.redirect(it)

        val signInError = SignIn(userId)

        val signin = when {
            userId.length < MIN_USER_ID_LENGTH -> null
            password.length < MIN_PASSWORD_LENGTH -> null
            !userNameValid(userId) -> null
            else -> userRepository.getUserWithHash(userId, hashFunction(password))
        }

        if (signin == null) {
            call.redirect(signInError.copy(error = "Invalid username or password"))
        } else {
            call.sessions.set(UserSession(signin.userId))
            call.redirect(Flights())
        }
    }

    get<SignIn> {
        val user = call.sessions.get<UserSession>()?.let { userRepository.getUser(it.userId) }

        if (user != null) {
            call.redirect(Home())
        } else {
            call.respond(FreeMarkerContent("signin.ftl", mapOf("userId" to it.userId, "error" to it.error), ""))
        }
    }
}