import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
// import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun Application.getGrades() {
        val counts: MutableMap<String, Int> = mutableMapOf()
        install(ContentNegotiation) {
            gson {
            }
        }
        routing {
            get("/{operation}") {
                try {
                    val operation = call.parameters["operation"]!!
                    //val first = call.parameters["first"]!!.toInt()
                    //val second = call.parameters["second"]!!.toInt()
                    val result = when(operation) {
                        "MPs" -> "30% of grade"
                        "Quizzes" -> "24% of grade"
                        "Exams" -> "6% of grade"
                        "Homework" -> "20% of grade"
                        "Lecture Participation" -> "5% of grade"
                        "Lab Participation" -> "5% of grade"
                        "Extra Credit" -> "5% of grade"
                        else -> throw Exception("$operation is not supported")
                    }
                    call.respond(result)
                } catch (e: Exception) {
                    call.respond(io.ktor.http.HttpStatusCode.BadRequest)
                }
            }
        }
}

fun main() {
    embeddedServer(Netty, port = 8000, module = Application::getGrades).start(wait = true)
}
