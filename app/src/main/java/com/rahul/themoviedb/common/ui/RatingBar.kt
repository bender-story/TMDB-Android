package com.rahul.themoviedb.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rahul.themoviedb.ui.theme.OrangeLight

@Composable
fun RatingBar(rating: Int, votes:Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 1..5) {
            if (i <= rating) {
                Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(24.dp), tint = OrangeLight)
            } else {
                Icon(Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.Gray)
            }
        }
        Text(text = "($votes)", color = OrangeLight)
    }
}