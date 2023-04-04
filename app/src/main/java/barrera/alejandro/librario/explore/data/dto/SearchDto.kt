package barrera.alejandro.librario.explore.data.dto

import com.squareup.moshi.Json

data class SearchDto(
    @field:Json(name = "items")
    val items: List<Item>
)