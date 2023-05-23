package com.srhdp.to_docompose.ui.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.srhdp.to_docompose.R

@Composable
fun EmptyContent(){
    Column(modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Icon(
             modifier = Modifier.size(120.dp),
             painter = painterResource(id = R.drawable.tag_faces), contentDescription = "Sad Face")
         Text(
             text = "No Task Found",

         )
    }
}

@Composable
@Preview
private fun EmptyContentReview(){
    EmptyContent()
}