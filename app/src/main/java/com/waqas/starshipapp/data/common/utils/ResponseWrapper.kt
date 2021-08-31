package com.waqas.starshipapp.data.common.utils

import com.google.gson.annotations.SerializedName

data class WrappedListResponse <T> (
        @SerializedName("count") var count: Int,
        @SerializedName("next") var next: String? = null,
        @SerializedName("previous") var previous: String? = null,
        @SerializedName("results") var results: List<T>? = null
)