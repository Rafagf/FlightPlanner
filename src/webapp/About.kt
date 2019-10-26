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

const val ABOUT = "about"

@Location(ABOUT)
class About

fun Route.about(userRepository: UserRepository) {
    get<About> {
        val user = call.sessions.get<UserSession>()?.let { userRepository.getUser(it.userId) }
        call.respond(FreeMarkerContent("about.ftl", mapOf("user" to user)))
    }
}