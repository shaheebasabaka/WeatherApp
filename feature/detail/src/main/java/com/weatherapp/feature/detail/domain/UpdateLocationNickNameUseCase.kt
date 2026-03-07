package com.weatherapp.feature.detail.domain

import javax.inject.Inject

class UpdateLocationNicknameUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(id: Int, nickname: String) {
        repository.updateNickname(id, nickname)
    }
}
