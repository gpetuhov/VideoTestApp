package com.gpetuhov.android.videotestapp.data.source.remote.models

import com.google.gson.annotations.SerializedName

class Results(
    @SerializedName("src")
    var videoUrl1: String?,

    @SerializedName("single")
    var videoUrl2: String?,

    @SerializedName("split_v")
    var videoUrl3: String?,

    @SerializedName("split_h")
    var videoUrl4: String?
)
