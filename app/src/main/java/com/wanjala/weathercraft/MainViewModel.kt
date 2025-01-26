package com.wanjala.weathercraft

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wanjala.weathercraft.data.models.City
import com.wanjala.weathercraft.data.models.CurrentWeatherUIModel
import com.wanjala.weathercraft.data.models.DailyForecast
import com.wanjala.weathercraft.data.models.DailyForecastUIModel
import com.wanjala.weathercraft.data.models.ForecastUIModel
import com.wanjala.weathercraft.data.repositories.MainRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CitySearchUiState {
    data object Idle : CitySearchUiState()
    data object Loading : CitySearchUiState()
    data object Saving : CitySearchUiState()
    data object FetchingWeather : CitySearchUiState()
    data class Success(val message: String) : CitySearchUiState()
    data class Error(val message: String) : CitySearchUiState()
}

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepositoryImpl) : ViewModel() {

    // Weather state
    private val _weatherUIState = MutableStateFlow<CitySearchUiState>(CitySearchUiState.Idle)
    val weatherUiState: StateFlow<CitySearchUiState> = _weatherUIState

    // Forecast state
    private val _forecastUIState = MutableStateFlow<CitySearchUiState>(CitySearchUiState.Idle)
    val forecastUiState: StateFlow<CitySearchUiState> = _forecastUIState

    // Search state
    private val _searchUIState = MutableStateFlow<CitySearchUiState>(CitySearchUiState.Idle)
    val searchUiState: StateFlow<CitySearchUiState> = _searchUIState

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity

    private val _currentWeather = MutableStateFlow<CurrentWeatherUIModel?>(null)
    val currentWeather: StateFlow<CurrentWeatherUIModel?> = _currentWeather

    private val _forecastData = MutableStateFlow<List<DailyForecastUIModel>?>(null)
    val forecastData: StateFlow<List<DailyForecastUIModel>?> = _forecastData

    var searchQuery by mutableStateOf("")
        private set

    var suggestions by mutableStateOf<List<City>>(emptyList())
        private set

    init {
        viewModelScope.launch {
            try {
                repository.getSelectedCity().collect { city ->
                    _selectedCity.value = city
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading selected city: ${e.localizedMessage}")
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (query.isBlank()) {
            resetSearchState()
            return
        }

        searchQuery = query
        _searchUIState.value = CitySearchUiState.Loading

        viewModelScope.launch {
            try {
                val result = repository.getCitySuggestions(query)
                suggestions = result
                _searchUIState.value = if (result.isEmpty()) {
                    CitySearchUiState.Error("No results found for \"$query\".")
                } else {
                    CitySearchUiState.Idle
                }
            } catch (e: Exception) {
                _searchUIState.value = CitySearchUiState.Error(e.localizedMessage ?: "An unexpected error occurred.")
                suggestions = emptyList()
            }
        }
    }

    fun triggerWeatherFetch(city: City) {
        _weatherUIState.value = CitySearchUiState.FetchingWeather
        viewModelScope.launch {
            try {
                val weather = repository.getCurrentWeather(
                    lat = city.coordinates.latitude,
                    lon = city.coordinates.longitude
                )
                _currentWeather.value = weather
                _weatherUIState.value = CitySearchUiState.Idle
            } catch (e: Exception) {
                _weatherUIState.value = CitySearchUiState.Error("Failed to fetch weather data: ${e.localizedMessage}")
            }
        }
    }

    fun triggerForecastFetch(city: City) {
        _forecastUIState.value = CitySearchUiState.FetchingWeather
        viewModelScope.launch {
            try {
                val forecast = repository.getWeatherForecast(
                    lat = city.coordinates.latitude,
                    lon = city.coordinates.longitude
                )
                _forecastData.value = forecast
                _forecastUIState.value = CitySearchUiState.Idle
            } catch (e: Exception) {
                _forecastUIState.value = CitySearchUiState.Error("Failed to fetch forecast data: ${e.localizedMessage}")
            }
        }
    }

    fun saveSelectedCity(city: City, onNavigateToHome: () -> Unit) {
        _searchUIState.value = CitySearchUiState.Saving
        viewModelScope.launch {
            try {
                // Attempt to fetch weather and forecast for the new city
                val weatherJob = async { repository.getCurrentWeather(city.coordinates.latitude, city.coordinates.longitude) }
                val forecastJob = async { repository.getWeatherForecast(city.coordinates.latitude, city.coordinates.longitude) }

                // Await both fetches
                val weather = weatherJob.await()
                val forecast = forecastJob.await()

                // Update UI with fetched data
                _currentWeather.value = weather
                _forecastData.value = forecast

                // Save the city if all fetches succeed
                repository.saveSelectedCity(city)
                _selectedCity.value = city
                _searchUIState.value = CitySearchUiState.Success("City changed successfully!")

                // Navigate to home
                onNavigateToHome()
            } catch (e: Exception) {
                _searchUIState.value = CitySearchUiState.Error("Failed to change city: ${e.localizedMessage}")
            }
        }
    }


    private fun resetSearchState() {
        searchQuery = ""
        suggestions = emptyList()
        _searchUIState.value = CitySearchUiState.Idle
    }
}
