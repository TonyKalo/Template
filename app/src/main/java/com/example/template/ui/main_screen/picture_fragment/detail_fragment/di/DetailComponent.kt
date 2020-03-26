package com.example.template.ui.main_screen.picture_fragment.detail_fragment.di

import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import com.example.template.ui.main_screen.picture_fragment.detail_fragment.DetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class, DetailBindModule::class])
interface DetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailComponent
    }

    fun inject(fragment: DetailFragment)
}
