package ru.haliksar.flowApp.features.user.signin.presentation.di.injector

import ru.haliksar.flowApp.features.user.signin.presentation.di.SignInModules
import ru.haliksar.flowapp.libraries.core.presentation.base.DynamicInjector

class SignInDynamicInjector : DynamicInjector(SignInModules)