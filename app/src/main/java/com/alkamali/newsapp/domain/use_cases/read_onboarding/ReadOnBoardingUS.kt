package com.alkamali.newsapp.domain.use_cases.read_onboarding

import com.alkamali.newsapp.data.repository.Repository

class ReadOnBoardingUS(
    private val repository: Repository
) {
   operator fun invoke() = repository.readOnBoardingState()
}