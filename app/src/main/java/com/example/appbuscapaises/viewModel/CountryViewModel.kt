package com.example.appbuscapaises.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appbuscapaises.modelo.Pais
import com.example.appbuscapaises.network.ServicioApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    // Varible para almacenar la lista de países
    private val _countries = MutableStateFlow<List<Pais>>(emptyList())
    val countries: StateFlow<List<Pais>> = _countries
    // Variable para controlar la visibilidad del indicador de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    // Función para buscar un país por su nombre
    fun searchCountry(name: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = ServicioApi.api.getCountryByName(name)
                _countries.value = result
                _isLoading.value = false
            } catch (e: Exception) {
                _countries.value = emptyList()
            }
        }
    }
    // Función para obtener un país por su nombre
    fun getCountryByName(name: String): Pais? {
        return _countries.value.find { it.name.common == name }
    }
    // Función para obtener el país más poblado
    fun getMostPopulatedCountry(): Pais? {
        return _countries.value.maxByOrNull { it.population }
    }
    // Función para obtener el país con mayor superficie
    fun getLargestCountry(): Pais? {
        return _countries.value.maxByOrNull { it.area }
    }
    // Función para obtener el país menos poblado
    fun getLeastPopulatedCountry(): Pais? {
        return _countries.value
            .filter { it.population > 0 }
            .minByOrNull { it.population }
    }
    // Función para obtener el país más pequeño
    fun getSmallestCountry(): Pais? {
        return _countries.value
            .filter { it.area != null && it.area > 0 && it.area.isFinite() }
            .minByOrNull { it.area }
    }
    // Función para obtener el número total de países
    fun getTotalCountries(): Int {
        return _countries.value.size
    }
    // Función para cargar todos los países
    fun loadAllCountries() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = ServicioApi.api.getAllCountries()
                _countries.value = result
                _isLoading.value = false
            } catch (e: Exception) {
                _countries.value = emptyList()
            }
        }
    }

}
