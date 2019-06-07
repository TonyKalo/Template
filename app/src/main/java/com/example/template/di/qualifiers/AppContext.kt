package com.example.template.di.qualifiers

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppContext