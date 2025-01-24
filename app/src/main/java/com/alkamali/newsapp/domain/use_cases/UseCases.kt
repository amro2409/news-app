package com.alkamali.newsapp.domain.use_cases

import com.alkamali.newsapp.domain.use_cases.get_all_items.GetAllItemsUS
import com.alkamali.newsapp.domain.use_cases.read_onboarding.ReadOnBoardingUS
import com.alkamali.newsapp.domain.use_cases.save_onboarding.SaveOnBoardingUS

data class UseCases (
    val getAllItemsUS: GetAllItemsUS,
    val saveOnBoardingUS: SaveOnBoardingUS,
    val readOnBoardingUS: ReadOnBoardingUS
)
