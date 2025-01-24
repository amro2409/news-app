package com.alkamali.newsapp.domain.model



interface Result
data class  Success<T>(val data: T?): Result
data class  Error (val error: String?): Result
data class  Loading (val isLoading: String): Result



