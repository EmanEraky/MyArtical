package com.eman.articalsapp.domain.usecases

import com.eman.articalsapp.data.repo.ApiHelper
import javax.inject.Inject

open class getMainArticalUseCase @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getArtical(apikey: String) =
        apiHelper.getArtical(apikey)


}