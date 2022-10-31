package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.juniorandroiddevelopertask.presentaion.theme.Grey
import com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen.SaveListScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipMaker(onClick: () -> Unit,isLike:Boolean,label:String,imageVector: ImageVector) {
    AssistChip(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(
                text = label,
                color = if (isLike) Color.White else Color.Black
            )
        },
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                tint = if (isLike) Color.White else Color.Black,
                contentDescription = "description",
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        },
        colors = AssistChipDefaults.assistChipColors(containerColor = if (isLike) Color.Black else Grey)
    )
}