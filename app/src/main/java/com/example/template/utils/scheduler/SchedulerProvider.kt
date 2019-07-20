package com.example.template.utils.scheduler

import io.reactivex.Scheduler



interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler

}