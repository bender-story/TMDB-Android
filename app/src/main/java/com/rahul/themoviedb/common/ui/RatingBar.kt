package com.rahul.themoviedb.common.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rahul.themoviedb.common.utils.formatToOneDecimalPlaces
import com.rahul.themoviedb.ui.theme.OrangeDeep
import com.rahul.themoviedb.ui.theme.OrangeLight

@Composable
fun RatingBar(rating: Double, votes: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rating.formatToOneDecimalPlaces(),
                color = OrangeDeep,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                for (i in 1..10) {
                    if (i <= rating) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = OrangeLight
                        )
                    } else {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
        Text(text = "(Votes: $votes)", color = OrangeLight)
    }
}