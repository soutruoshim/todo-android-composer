package com.srhdp.to_docompose.data.models


import androidx.compose.ui.graphics.Color
import com.srhdp.to_docompose.ui.theme.HighPriorityColor
import com.srhdp.to_docompose.ui.theme.LowPriorityColor
import com.srhdp.to_docompose.ui.theme.MedPriorityColor
import com.srhdp.to_docompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MedPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}