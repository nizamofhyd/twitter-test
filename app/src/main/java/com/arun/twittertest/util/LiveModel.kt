package com.arun.twittertest.util

data class LiveModel<T>(val model: T?, val status: Status, val errorMessage: String?)

enum class Status {
    SUCCESS,
    ERROR
}