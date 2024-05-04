interface Subject {
    fun register(temperatureObserver: TemperatureObserver)
    fun remove(temperatureObserver: TemperatureObserver)
    fun notifyObservers()
}

interface TemperatureObserver {
    fun update(
        temperature: Float,
        humidity: Float,
        pressure: Float
    )
}

class WeatherStation : Subject {
    private val observers = mutableListOf<TemperatureObserver>()
    private var temperature: Float = 0.0f
    private var humidity: Float = 0.0f
    private var pressure: Float = 0.0f

    fun setWeatherData(
        temperature: Float,
        humidity: Float,
        pressure: Float
    ) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure

        notifyObservers()
    }

    override fun register(temperatureObserver: TemperatureObserver) {
        observers.add(temperatureObserver)
    }

    override fun remove(temperatureObserver: TemperatureObserver) {
        observers.remove(temperatureObserver)
    }

    override fun notifyObservers() {
        for (listener in observers) {
            listener.update(temperature, humidity, pressure)
        }
    }
}

class DisplayDevice(private val name: String) : TemperatureObserver {
    override fun update(temperature: Float, humidity: Float, pressure: Float) {
        println("$name Display: Temperature = $temperature, Humidity = $humidity, Pressure = $pressure")
    }
}

fun main() {
    val weatherStation = WeatherStation()

    val displayDevice1 = DisplayDevice("Display Device 1")
    val displayDevice2 = DisplayDevice("Display Device 2")

    weatherStation.register(displayDevice1)
    weatherStation.register(displayDevice2)

    // Simulate weather data changes
    weatherStation.setWeatherData(25.5f, 60.0f, 1013.25f)
}