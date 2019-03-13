package by.zmitser.webapp.serversideevent

import org.openweathermap.api.UrlConnectionDataWeatherClient
import org.openweathermap.api.WeatherClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ServerSideEventApplication{
    @Bean
    fun openWeatherMapService(): WeatherClient = UrlConnectionDataWeatherClient("1d82742ee19d794f79341fab6dac7b50")


}


fun main(args: Array<String>) {
    SpringApplication.run(ServerSideEventApplication::class.java, *args)
}