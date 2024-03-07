//https://www.weatherbit.io/api/weather-current
//https://www.weatherbit.io/api/meta
//https://www.weatherbit.io/api/swaggerui/weather-api-v2#!/1632day324732daily32Forecast/get_forecast_daily_lat_lat_lon_lon
//
package com.example.weatherapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
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

class MainActivity3 : AppCompatActivity() {
    var WEATHER_URL = ""
    var API_KEY = "YOUR API KEY" //Update with your API key
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var DAY = 0
    var UPDATE_DAYS = true
    var newLat: Double? = null
    var newLon: Double? = null
    var newCity = ""
    var newCountryCode = ""
    lateinit var lastButtonPressed : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_location_forecast)

        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        obtainLocation()
        findViewById<Button>(R.id.btWeeklyOne2).setBackgroundColor(Color.parseColor("#2D52EF40"))

        lastButtonPressed = findViewById<Button>(R.id.btWeeklyOne2)

        val buttonGetWeatherDayOne = findViewById<Button>(R.id.btWeeklyOne2)
        buttonGetWeatherDayOne.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 0
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyOne2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyOne2)
            obtainLocation()
        }

        val buttonGetWeatherDayTwo = findViewById<Button>(R.id.btWeeklyTwo2)
        buttonGetWeatherDayTwo.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 1
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyTwo2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyTwo2)
            obtainLocation()
        }

        val buttonGetWeatherDayThree = findViewById<Button>(R.id.btWeeklyThree2)
        buttonGetWeatherDayThree.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 2
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyThree2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyThree2)
            obtainLocation()
        }

        val buttonGetWeatherDayFour = findViewById<Button>(R.id.btWeeklyFour2)
        buttonGetWeatherDayFour.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 3
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyFour2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyFour2)
            obtainLocation()
        }

        val buttonGetWeatherDayFive = findViewById<Button>(R.id.btWeeklyFive2)
        buttonGetWeatherDayFive.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 4
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyFive2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyFive2)
            obtainLocation()
        }

        val buttonGetWeatherDaySix = findViewById<Button>(R.id.btWeeklySix2)
        buttonGetWeatherDaySix.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 5
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklySix2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklySix2)
            obtainLocation()
        }

        val buttonGetWeatherDaySeven = findViewById<Button>(R.id.btWeeklySeven2)
        buttonGetWeatherDaySeven.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 6
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklySeven2).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklySeven2)
            obtainLocation()
        }

        val buttonBackToCurrentLocation = findViewById<Button>(R.id.btBackToCurrentLocation)
        buttonBackToCurrentLocation.setOnClickListener{
            Toast.makeText(this, "Changing to current day forecast", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSetLatAndLon = findViewById<Button>(R.id.btSetLatAndLon)
        buttonSetLatAndLon.setOnClickListener{
            Toast.makeText(this, "Lat and Lon values set", Toast.LENGTH_SHORT).show()
            //newLat = findViewById<EditText>(R.id.editTextLat).text
            findViewById<EditText>(R.id.editTextCityName).text.clear()
            findViewById<EditText>(R.id.editTextCountryCode).text.clear()
            newCity = ""
            newCountryCode = ""
            try {
                newLat = findViewById<TextView>(R.id.editTextLat).text.toString().toDouble()
                newLon = findViewById<TextView>(R.id.editTextLon).text.toString().toDouble()
                findViewById<TextView>(R.id.textViewHelpLocationForecast).visibility = View.INVISIBLE
                obtainLocation()
            }
            catch(e : Exception){
                Toast.makeText(this, "Invalid Latitude or Longitude values", Toast.LENGTH_SHORT).show()
            }
        }

        val buttonSetCityAndCountry = findViewById<Button>(R.id.btSetCityAndCountry)
        buttonSetCityAndCountry.setOnClickListener{
            Toast.makeText(this, "City name and Country code set", Toast.LENGTH_SHORT).show()
            newCity = findViewById<EditText>(R.id.editTextCityName).text.toString()
            newCountryCode = findViewById<EditText>(R.id.editTextCountryCode).text.toString()
            newLat = null
            newLon = null
            findViewById<EditText>(R.id.editTextLat).text.clear()
            findViewById<EditText>(R.id.editTextLon).text.clear()
            newCity = findViewById<TextView>(R.id.editTextCityName).text.toString()
            newCountryCode = findViewById<TextView>(R.id.editTextCountryCode).text.toString()
            findViewById<TextView>(R.id.textViewHelpLocationForecast).visibility = View.INVISIBLE
            obtainLocation()
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
                if(UPDATE_DAYS){
                    WEATHER_URL =
                        "https://api.weatherbit.io/v2.0/forecast/daily?" +
                                "lat=" + location?.latitude +
                                "&lon=" + location?.longitude +
                                "&key=" + API_KEY +
                                "&days=8"
                    getWeatherInformation()
                }
                else if(newLat != null && newLon != null){
                    WEATHER_URL =
                        "https://api.weatherbit.io/v2.0/forecast/daily?" +
                                "lat=" + newLat +
                                "&lon=" + newLon +
                                "&key=" + API_KEY +
                                "&days=8"
                    getWeatherInformation()
                }
                else if(newCity.isNotEmpty() && newCountryCode.isNotEmpty()){
                    WEATHER_URL =
                        "https://api.weatherbit.io/v2.0/forecast/daily?" +
                                "city=" + newCity +
                                "&country=" + newCountryCode +
                                "&key=" + API_KEY +
                                "&days=8"
                    getWeatherInformation()
                }
                else{
                    Toast.makeText(this, "Location information empty", Toast.LENGTH_SHORT).show()
                }
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
            { response ->
                showWeatherInformation(response)
            },
            // In case of any error
            {
                Toast.makeText(getApplicationContext(), "Could not get weather information", Toast.LENGTH_SHORT).show();
            })
        queue.add(stringReq)
    }

    fun showWeatherInformation(jsonWeather: String) {
        if(jsonWeather.isNotEmpty()) {
            val obj = JSONObject(jsonWeather)
            if(obj.has("error")){
                Toast.makeText(getApplicationContext(),  obj.getString("error"), Toast.LENGTH_SHORT).show();
                return
            }
            val arr =
                obj.getJSONArray("data")  // this time, each day's info is in the array called data
            if (UPDATE_DAYS) {
                findViewById<Button>(R.id.btWeeklyOne2).text =
                    arr.getJSONObject(0).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklyTwo2).text =
                    arr.getJSONObject(1).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklyThree2).text =
                    arr.getJSONObject(2).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklyFour2).text =
                    arr.getJSONObject(3).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklyFive2).text =
                    arr.getJSONObject(4).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklySix2).text =
                    arr.getJSONObject(5).getString("datetime").substring(5, 10)
                findViewById<Button>(R.id.btWeeklySeven2).text =
                    arr.getJSONObject(6).getString("datetime").substring(5, 10)
                findViewById<TextView>(R.id.editTextLat).text = obj.getString("lat")
                findViewById<TextView>(R.id.editTextLon).text = obj.getString("lon")
                findViewById<TextView>(R.id.editTextCityName).text = obj.getString("city_name")
                findViewById<TextView>(R.id.editTextCountryCode).text =
                    obj.getString("country_code")
                newLat = findViewById<TextView>(R.id.editTextLat).text.toString().toDouble()
                newLon = findViewById<TextView>(R.id.editTextLon).text.toString().toDouble()
                newCity = findViewById<TextView>(R.id.editTextCityName).text.toString()
                newCountryCode = findViewById<TextView>(R.id.editTextCountryCode).text.toString()
                UPDATE_DAYS = false
            }
            findViewById<TextView>(R.id.editTextLat).text = obj.getString("lat")
            findViewById<TextView>(R.id.editTextLon).text = obj.getString("lon")
            findViewById<TextView>(R.id.editTextCityName).text = obj.getString("city_name")
            findViewById<TextView>(R.id.editTextCountryCode).text = obj.getString("country_code")
            val objData = arr.getJSONObject(DAY)  // get position 0 of the array
            findViewById<TextView>(R.id.info_temperature).text = objData.getString("temp") + "ºC"
            findViewById<TextView>(R.id.info_pressure).text = objData.getString("pres") + " mb"
            findViewById<TextView>(R.id.info_humidity).text = objData.getString("rh") + "%"
            findViewById<TextView>(R.id.info_precipitation_probability).text =
                objData.getString("pop") + "%"
            findViewById<TextView>(R.id.info_UV).text = objData.getString("uv")

            val objWeather =
                objData.getJSONObject("weather") //weather description is inside a sub-object in the objData

            findViewById<TextView>(R.id.info_description).text = objWeather.getString("description")

            val imageIconCode = objWeather.getString("icon")
            val drawableResourceId =
                this.resources.getIdentifier(imageIconCode, "drawable", this.packageName)
            findViewById<ImageView>(R.id.info_image).setImageResource(drawableResourceId)
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid Location", Toast.LENGTH_SHORT).show();
        }
    }
}