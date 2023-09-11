package com.neotica.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterListResponse (
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterResponse>
)

data class Info(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)