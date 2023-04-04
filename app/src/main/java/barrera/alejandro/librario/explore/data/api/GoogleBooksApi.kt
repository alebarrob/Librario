package barrera.alejandro.librario.explore.data.api

import barrera.alejandro.librario.explore.data.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") key: String
    ): SearchDto

    companion object {
        const val BASE_URL = "https://www.googleapis.com/"
    }
}

