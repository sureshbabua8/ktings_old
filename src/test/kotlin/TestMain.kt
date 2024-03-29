import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

class TestMain : StringSpec({
    "should retrieve root path properly" {
        withTestApplication(Application::adder) {
            handleRequest(HttpMethod.Get, "/").apply {
                response.status() shouldBe HttpStatusCode.OK
                response.status() shouldBe "Hello, world!"
            }
        }
    }
})
