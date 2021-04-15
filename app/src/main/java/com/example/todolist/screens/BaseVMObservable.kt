package com.example.todolist.screens

// O - Observer
open class BaseVMObservable<O> {

    protected val observers: MutableList<O> = mutableListOf()

    fun subscribe(observer: O) {
        observers.add(observer)
    }

    fun unsubscribe(observer: O) {
        observers.remove(observer)
    }

}