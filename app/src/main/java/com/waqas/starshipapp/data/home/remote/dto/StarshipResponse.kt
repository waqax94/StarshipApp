package com.waqas.starshipapp.data.home.remote.dto

import com.google.gson.annotations.SerializedName

data class StarshipResponse(
    @SerializedName("MGLT") val MGLT: String,
    @SerializedName("cargo_capacity") val cargo_capacity: String,
    @SerializedName("consumables") val consumables: String,
    @SerializedName("cost_in_credits") val cost_in_credits: String,
    @SerializedName("created") val created: String,
    @SerializedName("crew") val crew: String,
    @SerializedName("edited") val edited: String,
    @SerializedName("films") val films: List<String>,
    @SerializedName("hyperdrive_rating") val hyperdrive_rating: String,
    @SerializedName("length") val length: String,
    @SerializedName("manufacturer") val manufacturer: String,
    @SerializedName("max_atmosphering_speed") val max_atmosphering_speed: String,
    @SerializedName("model") val model: String,
    @SerializedName("name") val name: String,
    @SerializedName("passengers") val passengers: String,
    @SerializedName("pilots") val pilots: List<String>,
    @SerializedName("starship_class") val starship_class: String,
    @SerializedName("url") val url: String,
)