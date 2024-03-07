//https://www.weatherbit.io/api/weather-current
//https://www.weatherbit.io/api/meta
//https://www.weatherbit.io/api/swaggerui/weather-api-v2#!/1632day324732daily32Forecast/get_forecast_daily_lat_lat_lon_lon
package com.example.weatherapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var WEATHER_URL = ""
    var API_KEY = "YOUR API KEY" //Update with your API key
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        obtainLocation()

        val buttonGetWeather = findViewById<Button>(R.id.btGetWeather)
        buttonGetWeather.setOnClickListener{
            Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()

            obtainLocation()
        }

        val buttonWeeklyForecast = findViewById<Button>(R.id.btWeeklyForecast)
        buttonWeeklyForecast.setOnClickListener{
            Toast.makeText(this, "Changing to weekly forecast", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val buttonSetNewLocation = findViewById<Button>(R.id.btSetNewLocationDaily)
        buttonSetNewLocation.setOnClickListener{
            Toast.makeText(this, "Setting new location", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    //ask location permission
    private fun obtainLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(this, permissions,0)
            return
        } else {
            // get the last location
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                // get the latitude and longitude  and create the http URL
                WEATHER_URL =
                    "https://api.weatherbit.io/v2.0/current?" +
                            "lat=" + location?.latitude +
                            "&lon=" + location?.longitude +
                            "&key=" + API_KEY
                // this function will, fetch data from URL
                getWeatherInformation()
            }
        }
    }

    //callback for request authorization
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permission_granted = true
        for (i in 0 until permissions.size) {
            val grantResult = grantResults[i]
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                permission_granted = false
            }
        }
        if( permission_granted) {
            getWeatherInformation()
        }
    }

    fun getWeatherInformation() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response  from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, WEATHER_URL,
            Response.Listener<String> { response ->
                showWeatherInformation(response)
            },
            // In case of any error
            Response.ErrorListener {
                Toast.makeText(getApplicationContext(), "Could not get weather information", Toast.LENGTH_SHORT).show();
            })
        queue.add(stringReq)
    }

    fun showWeatherInformation(jsonWeather: String) {
        val obj = JSONObject(jsonWeather)
        val arr = obj.getJSONArray("data")  // weather info is in the array called data
        val objData = arr.getJSONObject(0)  // get position 0 of the array

        findViewById<TextView>(R.id.info_temperature).text = objData.getString("temp") + "ºC"
        findViewById<TextView>(R.id.textViewLatValue).text = objData.getString("lat")
        findViewById<TextView>(R.id.textViewLonValue).text = objData.getString("lon")
        findViewById<TextView>(R.id.textViewCityName).text = objData.getString("city_name") +" ("+objData.getString("country_code") + ")"
        findViewById<TextView>(R.id.textViewDateEdit).text = objData.getString("datetime").substring(0,10)
        findViewById<TextView>(R.id.info_pressure).text = objData.getString("pres") + " mb"
        findViewById<TextView>(R.id.info_humidity).text = objData.getString("rh") + "%"
        findViewById<TextView>(R.id.info_precipitation_rate).text = objData.getString("precip") + " mm/hr"
        findViewById<TextView>(R.id.info_UV).text = objData.getString("uv")

        val objWeather = objData.getJSONObject("weather") //weather description is inside a sub-object in the objData

        findViewById<TextView>(R.id.info_description).text = objWeather.getString("description")

        val imageIconCode = objWeather.getString("icon")
        val drawableResourceId =
            this.resources.getIdentifier(imageIconCode,"drawable",this.packageName)
        findViewById<ImageView>(R.id.info_image).setImageResource(drawableResourceId)
    }

}