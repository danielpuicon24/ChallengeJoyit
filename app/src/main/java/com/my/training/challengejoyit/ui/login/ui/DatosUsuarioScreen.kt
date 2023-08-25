package com.my.training.challengejoyit.ui.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.my.training.challengejoyit.R
import kotlinx.coroutines.launch

@Composable
fun DatosUsuariosScreen(viewModel: LoginViewModel){
    Box(
        Modifier
            .fillMaxSize()
    ) {
        DatosUsuario( viewModel = viewModel)
    }

}

@Composable
fun DatosUsuario(
    viewModel: LoginViewModel
) {
    val configuration = LocalConfiguration.current

    val screenHeightDp = configuration.screenHeightDp

    val datosUsuarioState = viewModel.datosUsuario.observeAsState(initial = null)
    var emailUsuario = datosUsuarioState.value?.email
    var nombreUsuario = "${datosUsuarioState.value?.first_name} ${datosUsuarioState.value?.last_name}"
    var avatar = datosUsuarioState.value?.avatar


    BackgroundImage()
    if(screenHeightDp > 600){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clickable { viewModel.onNavigateToLoginScreen() }
                    .size(33.dp),

                )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = "Datos del Usuario",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp))
                Box(modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 70.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(6))
                    .background(
                        brush = Brush.radialGradient(
                            listOf(Color(0xFF2c2c2c), Color(0x992c2c2c)),
                            radius = 500f
                        )
                    ),){
                    Column(
                        modifier = Modifier
                            .background(Color.Transparent) // Establece un fondo transparente para el contenido
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row{

                            Image(
                                painter = rememberAsyncImagePainter(avatar),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)                       // clip to the circle shape
                                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                            )
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = nombreUsuario, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                if (emailUsuario != null) {
                                    Text(text = emailUsuario, color = Color.White, textAlign = TextAlign.Start)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(12.dp))

                    }
                }
            }
        }
    }else{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .clickable { viewModel.onNavigateToLoginScreen() }
                    .size(25.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = "Datos del Usuario",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp))
                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 55.dp,)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .clip(RoundedCornerShape(6))
                    .background(
                        brush = Brush.radialGradient(
                            listOf(Color(0xFF2c2c2c), Color(0x992c2c2c)),
                            radius = 500f
                        )
                    ),){
                    Column(
                        modifier = Modifier
                            .background(Color.Transparent) // Establece un fondo transparente para el contenido
                            .padding(16.dp)
                            .align(Alignment.TopStart)
                    ) {
                        Spacer(modifier = Modifier.padding(8.dp))
                        Row{
                            Image(
                                painter = rememberAsyncImagePainter(avatar),
                                contentDescription = "avatar",
                                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)                       // clip to the circle shape
                                    .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                            )
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(text = nombreUsuario, color = Color.White, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
                                if (emailUsuario != null) {
                                    Text(text = emailUsuario, color = Color.White, textAlign = TextAlign.Start)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun ImageFromUrl(imageUrl: String) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Image(
        painter = painter,
        contentDescription = null, // Agrega una descripción si es necesario
        modifier = Modifier.fillMaxSize() // Modifica el modifier según tus necesidades
    )
}
