package com.example.template.di.scopes

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity