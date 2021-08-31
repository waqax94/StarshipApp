package com.waqas.starshipapp.domain.home.entity

data class StarshipEntity(
    val MGLT: String,
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdriveRating: String,
    val length: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starshipClass: String,
    val url: String,
    var isFavourite: Boolean = false
)