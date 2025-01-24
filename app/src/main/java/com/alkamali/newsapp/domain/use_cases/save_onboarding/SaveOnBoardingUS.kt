package com.alkamali.newsapp.domain.use_cases.save_onboarding

import com.alkamali.newsapp.data.repository.Repository

class SaveOnBoardingUS(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}