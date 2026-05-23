package com.memowave.todotracker.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ThemeViewModel : ViewModel() {
    private val _theme = MutableStateFlow(ThemeType.LIGHT)
    val theme: StateFlow<ThemeType> = _theme

    fun setTheme(themeType: ThemeType) {
        _theme.value = themeType
    }
}