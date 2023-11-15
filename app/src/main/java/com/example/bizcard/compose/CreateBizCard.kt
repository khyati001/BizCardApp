package com.example.bizcard.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.bizcard.R

@Composable
fun CreateBizCard() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(dimensionResource(R.dimen.padding_fifteen)),
            shape = RoundedCornerShape(corner = CornerSize(dimensionResource(R.dimen.padding_fifteen))),
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.padding_four))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile()
                Divider(
                    thickness = dimensionResource(R.dimen.padding_three),
                    color = MaterialTheme.colorScheme.onSecondary
                )
                CreateInfo()
                Button(modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_ten)),
                    onClick = {
                        buttonClickedState.value = !buttonClickedState.value
                    }) {
                    Text(
                        text = stringResource(R.string.portfolio),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                if (buttonClickedState.value) {
                    Content()
                } else {
                    Box {}
                }
            }
        }
    }
}

@Composable
private fun CreateInfo() {
    Text(
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_five)),
        text = stringResource(R.string.user_name),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary
    )
    Text(
        text = stringResource(R.string.android_compose_programmer),
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_three)),
        style = MaterialTheme.typography.bodyLarge,
        color = Color.DarkGray
    )
    Text(
        text = stringResource(R.string.twitter_handle),
        modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_three)),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.DarkGray
    )
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile_image),
        contentDescription = stringResource(R.string.profile_image),
        modifier = modifier
            .size(dimensionResource(R.dimen.size_hundred_and_fifty))
            .clip(CircleShape)
            .padding(dimensionResource(R.dimen.padding_ten)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(dimensionResource(R.dimen.padding_ten))
    ) {
        Surface(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_three))
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(CornerSize(dimensionResource(R.dimen.padding_six))),
            border = BorderStroke(
                width = dimensionResource(R.dimen.padding_two),
                color = Color.LightGray
            ),
            color = MaterialTheme.colorScheme.surface
        ) {
            Portfolio(
                data = stringArrayResource(id = R.array.Projects).toList()
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_five))
    ) {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_eight))
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.padding_four))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    CreateImageProfile(modifier = Modifier.size(dimensionResource(R.dimen.size_hundred)))
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_seven))
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_two)),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.project_description),
                            modifier = Modifier.padding(
                                start = dimensionResource(R.dimen.padding_two),
                                top = dimensionResource(R.dimen.padding_two)
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BizCardPreview() {
    CreateBizCard()
}