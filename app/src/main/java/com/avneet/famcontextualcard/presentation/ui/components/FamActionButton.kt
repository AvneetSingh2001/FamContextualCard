package com.avneet.famcontextualcard.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.avneet.famcontextualcard.R
import com.avneet.famcontextualcard.ui.theme.backGroundColor

@Composable
fun FamActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconId: Int,
    text: String,
) {
    FilledTonalButton(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = backGroundColor
        ),
        contentPadding = PaddingValues(10.dp),
        onClick = { onClick() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = text, textAlign = TextAlign.Center)
        }
    }
}


@Preview
@Composable
fun FamActionButtonPreview() {
    FamActionButton(onClick = { }, iconId = R.drawable.ic_bell, text = "remind later")
}