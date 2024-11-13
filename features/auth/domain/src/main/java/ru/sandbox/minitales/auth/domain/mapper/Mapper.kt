package ru.sandbox.minitales.auth.domain.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}