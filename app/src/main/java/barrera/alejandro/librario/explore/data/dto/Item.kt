package barrera.alejandro.librario.explore.data.dto

import com.squareup.moshi.Json

data class Item(
    @field:Json(name = "volumeInfo")
    val volumeInfo: VolumeInfo
)
