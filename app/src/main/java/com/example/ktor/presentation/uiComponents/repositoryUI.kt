package com.example.ktor.presentation.uiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ktor.R
import com.example.ktor.network.model.RawGitRepository


@Composable
fun ReposInfo(gitRepo: RawGitRepository){

Column(modifier = Modifier.fillMaxWidth().background(Color.Gray).padding(10.dp),horizontalAlignment = Alignment.CenterHorizontally) {
    Card( elevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp),
        shape = RoundedCornerShape(40.dp)
    )
    {
        Text(text = stringResource(id = R.string.reposInfoText),

        modifier = Modifier.padding(10.dp,10.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold) }
}

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        CardRepoItem(stringResource(R.string.repositoryName),
            gitRepo.name.toString())

        CardRepoItem(stringResource(R.string.repositoryId),
            gitRepo.id.toString())

        CardRepoItem(stringResource(R.string.repositoryUrl),
            gitRepo.htmlUrl.toString())

        CardRepoItem(stringResource(R.string.repositoryLanguage),
            gitRepo.language.toString())

        CardRepoItem(stringResource(R.string.repositoryForkCount),
            gitRepo.forksCount.toString())

        CardRepoItem(stringResource(R.string.repositoryCreated_at),
            gitRepo.createdAt.toString())

        CardRepoItem(stringResource(R.string.repositoryUpdated_at),
            gitRepo.updatedAt.toString())

        CardRepoItem(
            stringResource(R.string.repositoryPushed_at),
            gitRepo.pushedAt.toString())

        CardRepoItem(stringResource(R.string.repositorySize),
            gitRepo.size.toString())

        CardRepoItem(stringResource(R.string.repositoryWatchers_count),
            gitRepo.watchersCount.toString())

        CardRepoItem(stringResource(R.string.repositoryStargazers_count),
            gitRepo.stargazersCount.toString())

    }

}

@Composable
fun CardRepoItem(name:String, text:String){
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "$name: $text")
        }
    }
}
