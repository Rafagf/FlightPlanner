package webapp

import com.rafag.flightplanner.MIN_PASSWORD_LENGTH
import com.rafag.flightplanner.MIN_USER_ID_LENGTH
import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.model.domain.User
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

const val SIGN_UP = "signup"


@Location(SIGN_UP)
data class SignUp(
    val userId: String = "",
    val displayName: String = "",
    val email: String = "",
    val error: String = ""
)

fun Route.signUp(userRepository: UserRepository, hashFunction: (String) -> String) {
    post<SignUp> {
        val user = call.sessions.get<UserSession>()?.let {
            userRepository.getUser(it.userId)
        }

        if (user != null) {
            return@post call.redirect(Flights())
        }

        val signUpParameters = call.receive<Parameters>()
        val userId = signUpParameters["userId"] ?: return@post call.redirect(it)
        val password = signUpParameters["password"] ?: return@post call.redirect(it)
        val displayName = signUpParameters["displayName"] ?: return@post call.redirect(it)
        val email = signUpParameters["email"] ?: return@post call.redirect(it)

        val signUpError = SignUp(userId, password, displayName, email)

        when {
            userId.length < MIN_USER_ID_LENGTH -> call.redirect(signUpError.copy(error = "UserId should be at least $MIN_USER_ID_LENGTH long"))
            password.length < MIN_PASSWORD_LENGTH -> call.redirect(signUpError.copy(error = "Password should be at least $MIN_PASSWORD_LENGTH long"))
            !userNameValid(displayName) -> call.redirect(signUpError.copy(error = "Username should consist of digits, letters, dots or underscores"))
            userRepository.getUser(userId) != null -> call.redirect(signUpError.copy("Username $userId is already registered"))
            userRepository.userByEmail(email) != null -> call.redirect(signUpError.copy("Email $email is already registered"))

            else -> {
                val hash = hashFunction(password)
                val newUser = User(userId, email, displayName, hash)

                try {
                    userRepository.createUser(newUser)
                    call.sessions.set(UserSession(userId))
                    call.redirect(Flights())
                } catch (e: Throwable) {
                    call.redirect(signUpError.copy(error = "Failed to register"))
                }
            }
        }
    }

    get<SignUp> {
        val user = call.sessions.get<UserSession>()?.let { session -> userRepository.getUser(session.userId) }
        if (user != null) {
            call.redirect(Home())
        } else {
            call.respond(FreeMarkerContent("signup.ftl", mapOf("error" to it.error)))
        }
    }
}