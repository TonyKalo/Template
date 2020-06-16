package com.example.template.ui.main_screen.picture_fragment.detail_fragment.di

import com.example.template.ui.main_screen.picture_fragment.detail_fragment.DetailFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [DetailModule::class, DetailBindModule::class])
interface DetailComponent : AndroidInjector<DetailFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<DetailFragment>
}
