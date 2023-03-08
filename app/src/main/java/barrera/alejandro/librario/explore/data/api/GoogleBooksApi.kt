package barrera.alejandro.librario.explore.data.api

import barrera.alejandro.librario.explore.data.dto.Search
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {
    @GET("books/v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") key: String
    ): Response<Search>

    companion object {
        const val BASE_URL = "https://www.googleapis.com/"
    }
}

