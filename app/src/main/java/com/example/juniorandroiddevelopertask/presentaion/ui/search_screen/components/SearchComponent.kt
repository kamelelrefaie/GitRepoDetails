package com.example.juniorandroiddevelopertask.presentaion.ui.search_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Scanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInputComponent(
    searchValue: String,
    onSearchValueChange: (searchValue: String) -> Unit,
    onQrClick: () -> Unit
) {
    OutlinedTextField(
        value = searchValue, onValueChange = {
            onSearchValueChange(it)
        }, singleLine = true,
        placeholder = { Text(text = "Search", fontFamily = FontFamily.Serif) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.QrCodeScanner,
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onQrClick()
                    },
                contentDescription = "Filter Icon"

            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, start = 15.dp, end = 15.dp)
            .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.LightGray,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.LightGray
        ), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Preview
@Composable
fun PreviewSearchInputComponent() {

    SearchInputComponent(searchValue = "", onSearchValueChange = {}, onQrClick = {})
}