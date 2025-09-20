package dev.andrescoder.gamingapp.presentation.screens.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.andrescoder.gamingapp.R
import dev.andrescoder.gamingapp.presentation.components.DefaultTextField
import dev.andrescoder.gamingapp.presentation.components.DialogCapturePicture
import dev.andrescoder.gamingapp.presentation.screens.new_post.NewPostViewModel
import dev.andrescoder.gamingapp.presentation.ui.theme.GamingAppTheme
import dev.andrescoder.gamingapp.presentation.ui.theme.Red500


@Composable
fun NewPostContent(viewModel: NewPostViewModel = hiltViewModel()) {

    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()
    val dialogState = remember { mutableStateOf(false) }

    DialogCapturePicture(
        status = dialogState,
        takePhoto = viewModel::takePhoto,
        pickImage = viewModel::pickImage
    )

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Red500),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (viewModel.state.image != "") {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0x1FFFFFFF))
                        ) {
                            AsyncImage(
                                model = viewModel.state.image,
                                contentDescription = "Selected image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        dialogState.value = true
                                    }
                            )
                        }
                    } else {
                        Image(
                            modifier = Modifier
                                .height(70.dp)
                                .padding(top = 20.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = "Add Image"
                        )
                        Text(
                            modifier = Modifier.clickable {
                                dialogState.value = true
                            },
                            text = "Selecciona una imagen",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            DefaultTextField(
                modifier = Modifier.padding(top = 15.dp, start = 20.dp, end = 20.dp),
                value = state.name,
                onValueChange = { viewModel.onNameInputChange(it) },
                label = "Nombre del juego",
                icon = Icons.Default.Face,
                contentDescription = "Introduce nombre del juego",
                errorMsg = "",
                validateField = { }
            )

            DefaultTextField(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                value = state.description,
                onValueChange = { viewModel.onDescriptionInputChange(it) },
                label = "Descripcion",
                icon = Icons.AutoMirrored.Filled.List,
                contentDescription = "Introduce descripcion del juego",
                errorMsg = "",
                validateField = { }
            )

            Text(
                text = "CATEGORIAS",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            viewModel.radioOptions.forEach { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = option.category == state.category,
                            onClick = { viewModel.onCategorySelected(option.category) },
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RadioButton(
                        selected = option.category == state.category,
                        onClick = { viewModel.onCategorySelected(option.category) },
                    )
                    Row() {
                        Text(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(12.dp),
                            text = option.category
                        )
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .padding(8.dp),
                            painter = painterResource(id = option.image),
                            contentDescription = option.category
                        )
                    }
                }
            }

        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultNewPostContent() {
    GamingAppTheme(
        darkTheme = true
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewPostContent()
        }
    }
}