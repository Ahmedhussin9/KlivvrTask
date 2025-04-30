package com.klivvrtask.presentaion.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TimelineHeader(
    letter: String,
    showTopLine: Boolean,
    showBottomLine: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Canvas(modifier = Modifier
            .width(50.dp)
            .fillMaxHeight()
            .align(Alignment.CenterStart)
        ) {
            val centerX = size.width / 2
            val circleY = size.height / 2

            if (showTopLine) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(centerX, 0f),
                    end = Offset(centerX, circleY - 20),
                    strokeWidth = 4f
                )
            }

            drawCircle(
                color = Color.White,
                radius = 40f,
                center = Offset(centerX, circleY),
                style = Stroke(width = 4f)
            )

            if (showBottomLine) {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(centerX, circleY + 20),
                    end = Offset(centerX, size.height),
                    strokeWidth = 4f
                )
            }
        }

        Text(
            text = letter,
            modifier = Modifier
                .offset(x = 20.dp)
                .align(Alignment.CenterStart),
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TimelineHeaderPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        TimelineHeader(letter = "A", showTopLine = false, showBottomLine = true)
        TimelineHeader(letter = "B", showTopLine = true, showBottomLine = true)
        TimelineHeader(letter = "Z", showTopLine = true, showBottomLine = false)
    }
}