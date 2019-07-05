package com.example.template.utils

import io.reactivex.Scheduler



interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler

}