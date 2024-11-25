package ru.sandbox.minitales.common.utils

interface Mapper<F, T> {
    fun map(from: F): T
}