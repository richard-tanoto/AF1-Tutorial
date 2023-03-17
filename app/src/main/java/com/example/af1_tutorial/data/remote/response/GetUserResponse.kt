package com.example.af1_tutorial.data.remote.response

import com.example.af1_tutorial.data.model.User
import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @field:SerializedName("per_page")
    val perPage: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("data")
    val data: List<User>,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    /*@field:SerializedName("support")
    val support: Support*/
)
