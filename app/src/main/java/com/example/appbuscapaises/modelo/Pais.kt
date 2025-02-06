package com.example.appbuscapaises.modelo

//Clase 1
data class Pais(
    val name: Nombre,
    val capital: List<String>?,
    val flags: Bandera,
    val population: Int,
    val region: String,
    val area: Double,
    val subregion: String
)

//Clase 2
data class Nombre(
    val common: String
)
//Clase 3
data class Bandera(
    val png: String
)