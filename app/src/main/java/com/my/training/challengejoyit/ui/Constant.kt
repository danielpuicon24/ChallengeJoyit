package com.my.training.challengejoyit.ui

import android.content.Context
import android.content.res.Configuration

fun getScreenSize(context: Context): Pair<Int, Int> {
    val configuration = context.resources.configuration
    val screenWidthDp = configuration.screenWidthDp
    val screenHeightDp = configuration.screenHeightDp

    return Pair(screenWidthDp, screenHeightDp)
}
