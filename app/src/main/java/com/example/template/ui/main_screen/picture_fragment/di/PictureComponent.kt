package com.example.template.ui.main_screen.picture_fragment.di

import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [PictureModule::class, PictureBindModule::class])
interface PictureComponent : AndroidInjector<PictureFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<PictureFragment>
}
