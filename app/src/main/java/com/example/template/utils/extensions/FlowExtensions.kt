@file:Suppress("unused")
package com.example.template.utils.extensions

import com.example.template.core.data.Result
import com.example.template.core.data.Result.Loading
import com.example.template.core.data.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

fun <T> Flow<Result<T>>.asResultFlow(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<Result<T>> {
    return this
        .catch { emit(Error(it as Exception)) }
        .flowOn(dispatcher)
        .onStart { emit(Loading(true)) }
        .onCompletion { emit(Loading(false)) }
}
