package barrera.alejandro.librario.explore.data.dto

import com.squareup.moshi.Json

data class VolumeInfo(
    @field:Json(name = "title")
    val title: String?,

    @field:Json(name = "authors")
    val authors: List<String>?,

    @field:Json(name = "description")
    val description: String?
)

