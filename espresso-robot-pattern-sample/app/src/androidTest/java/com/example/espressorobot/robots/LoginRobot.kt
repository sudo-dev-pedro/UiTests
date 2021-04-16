package com.example.espressorobot.robots

import com.example.espressorobot.R

fun loginAction(
        func: LoginRobot.() -> Unit
) = LoginRobot().apply {
    func()
}

class LoginRobot : BaseTestRobot() {
    fun onClickLoginButton() = clickButton(R.id.btnLogin)

    fun matchError(error: String) = matchText(
            textView(android.R.id.message),
            error
    )

    fun inputEmail() = fillEditText(R.id.etEmail, "mail@example.com")
    fun inputPassword() = fillEditText(R.id.etPassword, "pass")

}