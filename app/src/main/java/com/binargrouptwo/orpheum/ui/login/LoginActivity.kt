package com.binargrouptwo.orpheum.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.binargrouptwo.orpheum.constants.Constants
import com.binargrouptwo.orpheum.databinding.ActivityLoginBinding
import com.binargrouptwo.orpheum.ui.home.MainActivity
import com.binargrouptwo.orpheum.ui.home.online.SpotifyActivityTesting
import com.binargrouptwo.orpheum.model.networking.auth.APIToken
import com.binargrouptwo.orpheum.utils.Preferences
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var preferences: Preferences

    var id = ""
    var displayName = ""
    var email = ""
    var avatar = ""
    var accessToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPreferences()
        setUpPreferences()
        button()
    }

    private fun button(){
        binding.btnLogin.setOnClickListener {
//            login()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun login(){
        var request = getAuth(AuthorizationResponse.Type.TOKEN)
        AuthorizationClient.openLoginActivity(
            this,
            Constants.AUTH_TOKEN_REQUEST_CODE,request
        )
    }

    private fun getAuth(type: AuthorizationResponse.Type): AuthorizationRequest {
        return AuthorizationRequest.Builder(APIToken.CLIENT_ID,type, Constants.REDIRECT_URI)
            .setShowDialog(false)
            .setScopes(arrayOf("user-read-email"))
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Constants.AUTH_TOKEN_REQUEST_CODE == requestCode){
            val response = AuthorizationClient.getResponse(resultCode,data)
            val accessToken: String?= response.accessToken
            fetchSpotifyUserProfile(accessToken)
        }
    }

    private fun fetchSpotifyUserProfile(token: String?) {
        Log.d("Status: ", "Please Wait...")
        if (token == null) {
            Log.i("Status: ", "Something went wrong - No Access Token found")
            return
        }

        val getUserProfileURL = "https://api.spotify.com/v1/me"

        GlobalScope.launch(Dispatchers.Default) {
            val url = URL(getUserProfileURL)
            val httpsURLConnection = withContext(Dispatchers.IO) {url.openConnection() as HttpsURLConnection }
            httpsURLConnection.requestMethod = "GET"
            httpsURLConnection.setRequestProperty("Authorization", "Bearer $token")
            httpsURLConnection.doInput = true
            httpsURLConnection.doOutput = false
            val response = httpsURLConnection.inputStream.bufferedReader()
                .use { it.readText() }  // defaults to UTF-8
            withContext(Dispatchers.Main) {
                val jsonObject = JSONObject(response)

                // Spotify Id
                val spotifyId = jsonObject.getString("id")
                Log.d("Spotify Id :", spotifyId)
                id = spotifyId

                // Spotify Display Name
                val spotifyDisplayName = jsonObject.getString("display_name")
                Log.d("Spotify Display Name :", spotifyDisplayName)
                displayName = spotifyDisplayName

                // Spotify Email
                val spotifyEmail = jsonObject.getString("email")
                Log.d("Spotify Email :", spotifyEmail)
                email = spotifyEmail


                val spotifyAvatarArray = jsonObject.getJSONArray("images")
                //Check if user has Avatar
                val spotifyAvatarURL: String
                if (spotifyAvatarArray.length() > 0) {
                    spotifyAvatarURL = spotifyAvatarArray.getJSONObject(0).getString("url")
                    Log.d("Spotify Avatar : ", spotifyAvatarURL)
                    avatar = spotifyAvatarURL
                }

                Log.d("Spotify AccessToken :", token)
                accessToken = token

                openDetailsActivity()
            }
        }
    }

    private fun openDetailsActivity() {
        val myIntent = Intent(this@LoginActivity, SpotifyActivityTesting::class.java)
        myIntent.putExtra("spotify_id", id)
        myIntent.putExtra("spotify_display_name", displayName)
        myIntent.putExtra("spotify_email", email)
        myIntent.putExtra("spotify_avatar", avatar)
        myIntent.putExtra("spotify_access_token", accessToken)
        startActivity(myIntent)
    }

    private fun setPreferences() {
        preferences = Preferences(this)
        preferences.setValues("id", "login")
    }

    private fun setUpPreferences() {
        preferences = Preferences(this)
        if (preferences.getValues("login").equals("home")) {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}