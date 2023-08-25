package com.my.training.challengejoyit.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.training.challengejoyit.ui.login.data.model.DataLogin
import com.my.training.challengejoyit.ui.login.data.model.ResponseSuccessSearchUser
import com.my.training.challengejoyit.ui.login.data.model.UserData
import com.my.training.challengejoyit.ui.login.data.model.UsuarioSuccesSearch
import com.my.training.challengejoyit.ui.login.domain.GetChallengeUseCase
import com.my.training.challengejoyit.ui.login.navigation.AppNavigator
import com.my.training.challengejoyit.ui.login.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getChallengeUseCase: GetChallengeUseCase,
    private val appNavigator: AppNavigator
) : ViewModel(){

    val navigationChannel = appNavigator.navigationChannel

     val datosUsuario = MutableLiveData<UserData>()


    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name


    private val _validateEmail = MutableLiveData<Boolean>()
    val validateEmail : LiveData<Boolean> = _validateEmail

    private val _validateRegister = MutableLiveData<Boolean>()
    val validateRegister : LiveData<Boolean> = _validateRegister

    private val _validateLogin = MutableLiveData<Boolean>()
    val validateLogin : LiveData<Boolean> = _validateLogin

    private val _isLoadingEmailUser = MutableLiveData<Boolean>()
    val isLoadingEmailUser : LiveData<Boolean> = _isLoadingEmailUser

    private val _validateEmailUser = MutableLiveData<Boolean>()
    val validateEmailUser : LiveData<Boolean> = _validateEmailUser

    private val _isLoadingLogin = MutableLiveData<Boolean>()
    val isLoadingLogin : LiveData<Boolean> = _isLoadingLogin

    private val _isLoadingRegister = MutableLiveData<Boolean>()
    val isLoadingRegister : LiveData<Boolean> = _isLoadingRegister


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _validateEmail.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _validateLogin.value = email.isNotEmpty() && isValidPassword(password)
    }

    fun signUpChanged(email: String, name: String, password: String){
        _email.value = email
        _password.value = password
        _name.value = name
        _validateRegister.value = email.isNotEmpty() && name.isNotEmpty() && isValidPassword(password)
    }

    suspend fun onSigIn(email: String, password: String){
        _isLoadingLogin.value = true
        delay(2000)
        var data = DataLogin(
            email = email,
            password = password
        )
        var respuesta = getChallengeUseCase.postLogin(data)
        var token = respuesta.get("token")
        if(token != null){
            println(token)
            var responseSearch = getSearchUser(4)

            val user = responseSearch.data
            datosUsuario.postValue(user)
            onNavigateToDatosUsuarioScreen();
        }
        delay(2000)
        _isLoadingLogin.value = false
    }

    suspend fun onSignUp(email: String, name: String, password: String){
        _isLoadingRegister.value = true
        delay(2000)
        var data = DataLogin(
            email = "eve.holt@reqres.in",
            password = password
        )
        var respuesta = getChallengeUseCase.postRegister(data)
        var id = respuesta.get("id")
        if(id != null){
            val doubleValue: Double = id as Double
            var responseSearch = getSearchUser(doubleValue.toDouble().toInt())
            val user = responseSearch.data
            datosUsuario.postValue(user)
            onNavigateToDatosUsuarioScreen();
        }
        _isLoadingRegister.value = false
    }

    suspend fun getSearchUser(idUser : Int) : ResponseSuccessSearchUser {
        var response = getChallengeUseCase.searchUser(idUser)
        return response

    }
    private fun isValidPassword(password: String): Boolean = password.length > 5

    fun isValidEmail(email: String) {
        _email.value = email
        _validateEmail.value = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    suspend fun consultApiEmail(email: String) {
        _isLoadingEmailUser.value = true
        if(email.isNotEmpty()){
            delay(2000)
            if(email == "eve.holt@reqres.in"){
                onNavigateToSignInScreen()
            }else{
                _password.value = ""
                onNavigateToSignUpScreen()
            }
        }
        _isLoadingEmailUser.value = false
    }

    fun onNavigateToSignInScreen() {
        appNavigator.tryNavigateTo(Destination.SigninScreen())
    }

    fun onNavigateToSignUpScreen() {
        appNavigator.tryNavigateTo(Destination.SignupScreen())
    }

    fun onNavigateToLoginScreen() {
        appNavigator.tryNavigateTo(Destination.LoginScreen())
    }

    fun onNavigateToDatosUsuarioScreen() {
        appNavigator.tryNavigateTo(Destination.DetailsScreen())
    }
}