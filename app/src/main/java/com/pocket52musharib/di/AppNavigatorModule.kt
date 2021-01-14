package com.pocket52musharib.di

import com.pocket52musharib.ui.AppNavigator
import com.pocket52musharib.ui.AppNavigatorInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Musharib Ali on 13/1/21.
 * I.S.T Pvt. Ltd
 * musharib.ali@innobles.com
 */
@InstallIn(ActivityComponent::class)
@Module
abstract class AppNavigatorModule {
    @Binds
    abstract fun bindNavigator(appNavigator: AppNavigator): AppNavigatorInterface
}