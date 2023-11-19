package com.example.ktor.presentation.uiComponents


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ktor.R
import com.example.ktor.network.model.RawGitRepository

@Composable
fun OwnerCard(gitRepo: RawGitRepository){
    Card(shape = RoundedCornerShape(40.dp),  elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.End

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(gitRepo.owner?.avatarUrl)
                    .crossfade(true)
                    .build(),
                    placeholder = painterResource(R.drawable.asphalt_gray_svgrepo_com),
                    contentDescription = gitRepo.owner?.login,)
                Spacer(modifier = Modifier.padding(20.dp) )
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(
                        text = stringResource(id = R.string.ownerInfoText),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)

                    Text(
                        text = stringResource(R.string.ownerName) + " " +
                                gitRepo.owner?.login.toString(),
                        fontSize = 16.sp)

                    Text(text = stringResource(R.string.userId) + " " +
                            gitRepo.owner?.id.toString(),
                        fontSize = 16.sp)

                    Text(
                        text = stringResource(R.string.userType) + " " +
                                gitRepo.owner?.type.toString(),
                        fontSize = 16.sp)

                    Text(
                        text = stringResource(R.string.userUrl) + " " +
                                gitRepo.owner?.htmlUrl,
                        fontSize = 16.sp)
                }
            }
        }
    }
}
