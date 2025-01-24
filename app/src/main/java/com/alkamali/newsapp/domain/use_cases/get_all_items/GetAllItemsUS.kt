package com.alkamali.newsapp.domain.use_cases.get_all_items

import com.alkamali.newsapp.data.repository.Repository


class GetAllItemsUS(
    private val repository: Repository
) {
    operator fun invoke() = repository.getAllItem()
}
