package ru.todo.present.mvi

sealed class Response<T> {

    class Success(val item: T) : Response<T>()
    object TimeOut : Response()
    class Error() : Response()
}