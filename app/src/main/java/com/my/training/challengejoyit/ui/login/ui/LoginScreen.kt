package com.my.training.challengejoyit.ui.login.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.my.training.challengejoyit.R
import kotlinx.coroutines.launch


/*
En esta parte llamamos a nuesta pantalla de Login
*/
@Composable
fun LoginScreen(viewModel: LoginViewModel){
    Box(
        Modifier
            .fillMaxSize()
    ) {
        Login( viewModel = viewModel)
    }
}

/*
En esta parte es para poner el background image del fondo de la pantalla
*/
@Composable
fun BackgroundImage(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
        ,
        contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.logo2), // Reemplaza con tu imagen de fondo
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxSize()

            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    ),
                    startY = 0f,
                    endY = 1150f
                )
            )
    )
}

/*
En esta parte completamos toda la estructura del screen Login.
Además se está haciendo responsivo para pantallas de celulares que son menores y mayores a 700dp
*/
@Composable
fun Login(
    viewModel: LoginViewModel,
) {
    val configuration = LocalConfiguration.current

    val screenHeightDp = configuration.screenHeightDp

    val email :String by viewModel.email.observeAsState(initial = "")
    val password :String by viewModel.password.observeAsState(initial = "")
    val validateEmail: Boolean by viewModel.validateEmail.observeAsState(initial = false)
    val isLoadingEmailUser: Boolean by viewModel.isLoadingEmailUser.observeAsState(initial = false)
    val courutinesScope = rememberCoroutineScope()


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
                    .size(33.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = "Hi!",
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp))
                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
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
                        EmailField(email, {viewModel.onLoginChanged(it, password)})
                        Spacer(modifier = Modifier.padding(8.dp))
                        ContinueButton(heigt = 48.dp, texto = "Continue", validate = validateEmail,
                            onLoginSelected = {
                                courutinesScope.launch {
                                    viewModel.consultApiEmail(email)
                                }
                                })
                        Spacer(modifier = Modifier.padding(6.dp))
                        if(isLoadingEmailUser){
                            Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                                CircularProgressIndicator()
                            }
                        }
                        Text(
                            text = "or",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(
                            text = "Continue with Facebook",
                            imageResource = R.drawable.facebook,
                            padding = 10.dp,
                            heigt = 50.dp,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(text = "Continue with Google",
                            imageResource = R.drawable.google,
                            padding = 10.dp,
                            heigt = 50.dp,
                            onClick = {})
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(text = "Continue with Apple",
                            imageResource = R.drawable.apple,
                            padding = 10.dp,
                            heigt = 50.dp,
                            onClick = {})
                        Spacer(modifier = Modifier.padding(12.dp))
                        SignUpText(fontsize = 17)
                        Spacer(modifier = Modifier.padding(6.dp))
                        forgotPassword(fontsize = 17)
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
                    .size(25.dp),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Text(
                    text = "Hi!",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp))
                Box(modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 55.dp)
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
                        EmailField(email, {viewModel.onLoginChanged(it, password)})
                        Spacer(modifier = Modifier.padding(8.dp))
                        ContinueButton(40.dp, texto = "Continue", validate = validateEmail , onLoginSelected = {
                            courutinesScope.launch {
                                viewModel.consultApiEmail(email)
                            }
                        })
                        Spacer(modifier = Modifier.padding(6.dp))
                        if(isLoadingEmailUser){
                            Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.Center){
                                CircularProgressIndicator()
                            }
                        }
                        Text(
                            text = "or",
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(
                            text = "Continue with Facebook",
                            imageResource = R.drawable.facebook,
                            padding = 10.dp,
                            heigt = 38.dp,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(text = "Continue with Google",
                            imageResource = R.drawable.google,
                            padding = 10.dp,
                            heigt = 38.dp,
                            onClick = {})
                        Spacer(modifier = Modifier.padding(6.dp))
                        SocialButton(text = "Continue with Apple",
                            imageResource = R.drawable.apple,
                            padding = 9.dp,
                            heigt = 38.dp,
                            onClick = {})
                        Spacer(modifier = Modifier.padding(12.dp))
                        SignUpText(fontsize = 14)
                        Spacer(modifier = Modifier.padding(6.dp))
                        forgotPassword(fontsize = 14)
                    }
                }
            }
        }
    }
}




/*
En esta parte completamos el Input del Email
*/
@Composable
fun EmailField(email:String, onTextFieldChanged:(String) -> Unit){
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) Color(0xFF03c38e) else Color(0xFFE5F8F3)
    val backgroundColor = if (isFocused) Color.White else Color(0xFFE5F8F3)

    TextField(
        value = email,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .onFocusChanged { isFocused = it.isFocused },
        label = { Text(text = "Email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.Gray,
        ),
    )
}

/*
En esta parte completamos el Input del Password
*/
@Composable
fun PasswordField(password: String, onTextFieldChanged: (String) -> Unit, isVisible: MutableState<Boolean>) {
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) Color(0xFF03c38e) else Color(0xFFE5F8F3)
    val backgroundColor = if (isFocused) Color.White else Color(0xFFE5F8F3)
    val visualTransformation = if(isVisible.value) VisualTransformation.None else PasswordVisualTransformation()

    TextField(value = password, onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .onFocusChanged { isFocused = it.isFocused },
        label = { Text(text = "Password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.Gray,
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            val image = if (isVisible.value)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (isVisible.value) "Hide password" else "Show password"

            IconButton(onClick = {isVisible.value = !isVisible.value}){
                Icon(imageVector  = image, description)
            }
        },
    )
}

/*
En esta parte completamos el Input Name
*/
@Composable
fun NameField( name : String, onTextFieldChanged: (String) -> Unit){
    var isFocused by remember { mutableStateOf(false) }

    val borderColor = if (isFocused) Color(0xFF03c38e) else Color(0xFFE5F8F3)
    val backgroundColor = if (isFocused) Color.White else Color(0xFFE5F8F3)

    TextField(
        value = name,
        onValueChange = { onTextFieldChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .onFocusChanged { isFocused = it.isFocused },
        label = { Text(text = "Name") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = backgroundColor,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLabelColor = Color.Gray,
        ),
    )
}

/*
En esta parte completamos el Boton que nos ayudará en todas las pantallas
*/
@Composable
fun ContinueButton(heigt: Dp, texto : String, validate: Boolean, onLoginSelected : () -> Unit){
    Button(onClick = { onLoginSelected() }, modifier = Modifier
        .fillMaxWidth()
        .height(heigt),
        colors = ButtonDefaults.buttonColors( backgroundColor = Color(0xFF03c38e)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = texto,
            color = Color.White,
            fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

/*
En esta parte completamos el Boton de Logueo con Redes Sociales
*/
@Composable
fun SocialButton(text: String, imageResource: Int, padding : Dp, heigt: Dp, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(heigt)
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFE5F8F3))
        .padding(padding),
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


/*
En esta parte completamos la indicación si es que no tiene una cuenta, entonces debe registrarse
*/
@Composable
fun SignUpText(fontsize: Int) {
    Row {
        Text(
            text = "Don't have an account?",
            color = Color.White,
            fontSize = fontsize.sp // Usar fontSize en lugar de fontsize
        )
        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        Text(
            modifier = Modifier,
            text = "Sign up",
            color = Color(0xFF03c38e),
            fontWeight = FontWeight.Bold,
            fontSize = fontsize.sp // Usar fontSize en lugar de fontsize
        )
    }
}

@Composable
fun forgotPassword(fontsize: Int){
    Text(text = "Forgot your password?",
        color = Color(0xFF03c38e),
        fontWeight = FontWeight.Bold,
        fontSize = fontsize.sp)
}

