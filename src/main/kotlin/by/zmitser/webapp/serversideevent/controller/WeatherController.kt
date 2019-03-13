package by.zmitser.webapp.serversideevent.controller

import org.openweathermap.api.WeatherClient
import org.openweathermap.api.query.QueryBuilderPicker
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.openweathermap.api.query.forecast.daily.ByCityName
import org.openweathermap.api.query.UnitFormat.METRIC
import java.util.concurrent.Executors.newCachedThreadPool
import org.openweathermap.api.query.Language.ENGLISH


@RestController
@RequestMapping("/weather")
class WeatherController(val weatherClient: WeatherClient) {

    private val nonBlockingService = newCachedThreadPool()

    @GetMapping
    fun emitWeather(): SseEmitter {
        val emitter =  SseEmitter()
        val build: ByCityName = QueryBuilderPicker.pick()
                .forecast()
                .daily()
                .byCityName("Minsk")
                .unitFormat(METRIC)
                .language(ENGLISH)
                .build()
        nonBlockingService.execute {
            emitter.send(weatherClient.getWeatherData(build))
            emitter.complete()
        }
        return emitter
    }

}