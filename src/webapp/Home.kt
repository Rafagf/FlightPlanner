package webapp

import io.ktor.application.call
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Route

const val HOME = "home"

@Location(HOME)
class Home

fun Route.home() {
    get<Home> {
        call.respondText("Home page")
    }
}