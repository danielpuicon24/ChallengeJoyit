package com.my.training.challengejoyit.ui.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.training.challengejoyit.R
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(viewModel: LoginViewModel){


    Box(
        Modifier
            .fillMaxSize()
    ) {
        SignUp( viewModel = viewModel) }

}

@Composable
fun SignUp(
    viewModel: LoginViewModel
) {
    val configuration = LocalConfiguration.current

    val screenHeightDp = configuration.screenHeightDp

    val passwordVisible = rememberSaveable { mutableStateOf(false) }

    val email: String by viewModel.email.observeAsState(initial = "")
    val password: String by viewModel.password.observeAsState(initial = "")
    val name: String by viewModel.name.observeAsState(initial = "")
    val validateRegister: Boolean by viewModel.validateRegister.observeAsState(initial = false)
    val isLoadingRegister: Boolean by viewModel.isLoadingRegister.observeAsState(initial = false)

    var courutineScope = rememberCoroutineScope()

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
                    text = "Sign up",
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
                        Text(
                            text = "Looks like you don't have an account. Let's create a new account for ",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.CenterStart),
                            fontSize = 16.sp
                        )
                        Text(
                            text = email,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        NameField(name = name, {viewModel.signUpChanged(email, it, password)})
                        Spacer(modifier = Modifier.padding(8.dp))
                        PasswordField(password = password, {viewModel.signUpChanged(email,name, it)},isVisible = passwordVisible)
                        Spacer(modifier = Modifier.padding(12.dp))
                        Text(
                            text = "By selecting Agree and continue below,",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 0.dp, end = 16.dp),
                            fontSize = 16.sp
                        )
                        Row {
                            Text(text = "I agrre to ",
                                color = Color.White,
                                textAlign = TextAlign.Start,
                                fontSize = 16.sp,)
                            Text(text = "Terms of Service AND privacy Policy ",
                                color = Color(0xFF03c38e),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start)
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        ContinueButton(heigt = 48.dp, texto = "Agree and Continue", validate = validateRegister,
                            onLoginSelected = {
                                courutineScope.launch {
                                    viewModel.onSignUp(email, name, password)
                                }
                            })
                        if(isLoadingRegister){
                            Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                                CircularProgressIndicator()
                            }
                        }
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
                    text = "Sign up",
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
                        Text(
                            text = "Looks like you don't have an account. Let's create a new account for ",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp
                        )
                        Text(
                            text = email,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                            ,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                        Spacer(modifier = Modifier.padding(12.dp))
                        NameField(name = name, {viewModel.signUpChanged(email, it, password)})
                        Spacer(modifier = Modifier.padding(8.dp))
                        PasswordField(password = password, {viewModel.signUpChanged(email, name, it)}, isVisible = passwordVisible)
                        Spacer(modifier = Modifier.padding(12.dp))
                        Text(
                            text = "By selecting Agree and continue below,",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            fontSize = 14.sp
                        )
                        Row {
                            Text(text = "I agree to ",
                                color = Color.White,
                                fontSize = 14.sp)
                            Text(text = "Terms of Service and Privacy Policy",
                                color = Color(0xFF03c38e),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.padding(12.dp))
                        ContinueButton(heigt = 40.dp, texto = "Agree and Continue", validate = validateRegister,
                            onLoginSelected = {courutineScope.launch {
                                viewModel.onSignUp(email, name, password)
                            }})
                        if(isLoadingRegister){
                            Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                                CircularProgressIndicator()
                            }
                        }
                    }
                }
            }
        }
    }
}