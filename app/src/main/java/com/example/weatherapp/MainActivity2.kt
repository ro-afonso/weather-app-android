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
import kotlin.math.abs

class MainActivity2 : AppCompatActivity() {
    var WEATHER_URL = ""
    var API_KEY = "YOUR API KEY" //Update with your API key
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var DAY = 0
    var UPDATE_DAYS = true
    lateinit var lastButtonPressed : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weekly_forecast)

        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        obtainLocation()
        findViewById<Button>(R.id.btWeeklyOne).setBackgroundColor(Color.parseColor("#2D52EF40"))

        lastButtonPressed = findViewById<Button>(R.id.btWeeklyOne)

        val buttonGetWeatherDayOne = findViewById<Button>(R.id.btWeeklyOne)
        buttonGetWeatherDayOne.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 0
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyOne).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyOne)
            obtainLocation()
        }

        val buttonGetWeatherDayTwo = findViewById<Button>(R.id.btWeeklyTwo)
        buttonGetWeatherDayTwo.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 1
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyTwo).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyTwo)
            obtainLocation()
        }

        val buttonGetWeatherDayThree = findViewById<Button>(R.id.btWeeklyThree)
        buttonGetWeatherDayThree.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 2
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyThree).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyThree)
            obtainLocation()
        }

        val buttonGetWeatherDayFour = findViewById<Button>(R.id.btWeeklyFour)
        buttonGetWeatherDayFour.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 3
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyFour).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyFour)
            obtainLocation()
        }

        val buttonGetWeatherDayFive = findViewById<Button>(R.id.btWeeklyFive)
        buttonGetWeatherDayFive.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 4
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklyFive).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklyFive)
            obtainLocation()
        }

        val buttonGetWeatherDaySix = findViewById<Button>(R.id.btWeeklySix)
        buttonGetWeatherDaySix.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 5
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklySix).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklySix)
            obtainLocation()
        }

        val buttonGetWeatherDaySeven = findViewById<Button>(R.id.btWeeklySeven)
        buttonGetWeatherDaySeven.setOnClickListener{
            //Toast.makeText(this, "Getting weather data", Toast.LENGTH_SHORT).show()
            DAY = 6
            lastButtonPressed.setBackgroundColor(Color.parseColor("#2DF44336"))
            findViewById<Button>(R.id.btWeeklySeven).setBackgroundColor(Color.parseColor("#2D52EF40"))
            lastButtonPressed = findViewById<Button>(R.id.btWeeklySeven)
            obtainLocation()
        }

        val buttonDailyForecast = findViewById<Button>(R.id.btDailyForecast)
        buttonDailyForecast.setOnClickListener{
            Toast.makeText(this, "Changing to current day forecast", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val buttonSetNewLocation = findViewById<Button>(R.id.btSetNewLocation)
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
                    "https://api.weatherbit.io/v2.0/forecast/daily?" +
                            "lat=" + location?.latitude +
                            "&lon=" + location?.longitude +
                            "&key=" + API_KEY +
                            "&days=8"
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
        val arr = obj.getJSONArray("data")  // this time, each day's info is in the array called data

        if(UPDATE_DAYS){
            findViewById<Button>(R.id.btWeeklyOne).text = arr.getJSONObject(0).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklyTwo).text = arr.getJSONObject(1).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklyThree).text = arr.getJSONObject(2).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklyFour).text = arr.getJSONObject(3).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklyFive).text = arr.getJSONObject(4).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklySix).text = arr.getJSONObject(5).getString("datetime").substring(5,10)
            findViewById<Button>(R.id.btWeeklySeven).text = arr.getJSONObject(6).getString("datetime").substring(5,10)
            findViewById<TextView>(R.id.textViewLatValue).text = obj.getString("lat")
            findViewById<TextView>(R.id.textViewLonValue).text = obj.getString("lon")
            findViewById<TextView>(R.id.textViewCityName).text = obj.getString("city_name") +" ("+obj.getString("country_code") + ")"
            findViewById<TextView>(R.id.textViewDateEdit2).text = arr.getJSONObject(0).getString("datetime")
            UPDATE_DAYS = false
            DAY = 0
        }
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
}