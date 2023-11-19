package com.example.ktor.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ktor.R
import com.example.ktor.data.Resource
import com.example.ktor.network.model.RawGitRepository
import com.example.ktor.presentation.uiComponents.OwnerCard
import com.example.ktor.presentation.uiComponents.ReposInfo
import com.example.ktor.presentation.viewModel.MainViewModel
import com.example.ktor.ui.theme.KtorTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            KtorTheme {
                val repository = viewModel.gitRepository.collectAsState()
                repository.value?.let {
                    when (it) {
                        is Resource.Failure -> {
                            ErrorScreen(it.exception)
                        }
                        Resource.Loading -> {
                            LoadingImage()
                        }
                        is Resource.Success -> {
                            if(checkApiIsExists(it.result)){
                                ErrorScreen(exception = Exception(stringResource(R.string.repoNotFound)))
                            }
                            else{
                                Column(modifier = Modifier.background(Color.Gray)) {
                                    OwnerCard(it.result)
                                    ReposInfo(it.result)
                                }
                            }

                        }
                    }

                }
            }

        }
    }
    private fun checkApiIsExists(gitRepo:RawGitRepository):Boolean{
        return gitRepo.id == null
        
    }
}

@Composable
fun LoadingImage(){
    Surface(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(R.drawable.github_svgrepo_com__1_)
        Image(painter = image, contentDescription = null)
    }
}

@Composable
fun ErrorScreen(exception: Exception){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = stringResource(R.string.errorText),
                color = colorResource(R.color.red),
                fontWeight = FontWeight.Bold)
            Text(exception.message.toString())
    }
}




