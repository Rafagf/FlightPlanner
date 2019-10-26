package webapp

import com.rafag.flightplanner.model.UserSession
import com.rafag.flightplanner.repositories.user.UserRepository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

const val HOME = "/"

@Location(HOME)
class Home

fun Route.home(userRepository: UserRepository) {
    get<Home> {
        val user = call.sessions.get<UserSession>()?.let { userRepository.getUser(it.userId) }
        call.respond(FreeMarkerContent("home.ftl", mapOf("user" to user)))
    }
}