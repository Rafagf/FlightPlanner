package webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

const val ABOUT = "about"

@Location(ABOUT)
class About

fun Route.about() {
    get<About> {
        call.respondText("About page")
    }
}