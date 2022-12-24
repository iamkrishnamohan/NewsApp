package `in`.newtel.newsapp.repository

import `in`.newtel.newsapp.api.ApiService
import javax.inject.Inject


class ApiRepository @Inject constructor(private val apiServices: ApiService) {
    //suspend fun getPopularMoviesList(page: Int) = apiServices.getPopularMoviesList(page)

    suspend fun getBreakingNews(countryCode: String, page: Int) =
        apiServices.getBreakingNews(countryCode, page)

    suspend fun searchForNews(query:String,page: Int)=apiServices.searchForNews(query,page)
    /*suspend fun getMovieDetails(id: Int) =
        apiServices.getMovieDetails(id)*/
}