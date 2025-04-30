package com.klivvrtask.presentaion.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.klivvrtask.R

@Composable
fun CountryFlag(countryCode: String) {
    val context = LocalContext.current
    val resourceId = remember(countryCode) {
        val name = countryCode.lowercase()
        context.resources.getIdentifier(name, "drawable", context.packageName)
    }

    if (resourceId != 0) {
        Image(
            painter = painterResource(id = resourceId),
            contentDescription = "$countryCode flag",
            modifier = Modifier.size(24.dp)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.not_found),
            contentDescription = "$countryCode flag",
            modifier = Modifier.size(24.dp)
        )
    }
}