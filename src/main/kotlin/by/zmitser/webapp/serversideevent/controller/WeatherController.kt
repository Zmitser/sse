package by.zmitser.webapp.serversideevent.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*
import java.util.concurrent.Executors.newCachedThreadPool


@RestController
@RequestMapping("/weather")
class WeatherController {

    private val nonBlockingService = newCachedThreadPool()

    @GetMapping
    fun emitWeather(): SseEmitter {
        val emitter =  SseEmitter()
        nonBlockingService.execute {
            emitter.send("/sse" + " @ " + Date())
            emitter.complete()
        }
        return emitter
    }

}