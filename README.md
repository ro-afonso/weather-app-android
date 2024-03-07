# Android Weather App
Android app with daily and weekly weather-related information.

The Figure below shows the daily forecast based on the current location (on the left), the weekly forecast based on the current location (in the middle), and the weekly forecast for either a specific city and country or a set of coordinates in the world:

![Weather App Screenshots](https://github.com/ro-afonso/weather-app-android/assets/93609933/b2c1bfad-2cc6-438a-959f-f21f55df052f)

The [Weatherbit.io Weather API](https://www.weatherbit.io/) was used to obtain the weather information seen above through JSON messages.

After requesting the user's permission to access the device's location and once connected to the internet, the URL for the API is set according to its purpose (daily or weekly forecast, city, country, etc). The respective JSON message is then obtained and all the desired information is assigned to the UI.

For daily and weekly forecasts of the current location, the latitude and longitude values are set with the coordinates obtained from the device's location, as well as the number of days desired and the API key.

For weekly forecasts of other locations, the weather information is obtained by setting either the longitude and latitude coordinates or the city's name and respective country code. For example, if the user sets the longitude and latitude values, the city name and country code are automatically set according to the JSON message, and vice-versa.

# How to run

To successfully run the app on your Android device, follow these steps:
1) Create an account on [Weatherbit.io](https://www.weatherbit.io/) and generate your own API key for free
2) Install [Android Studio](https://developer.android.com/studio) on your computer
3) Activate developer mode on your Android device and connect it to your computer through a USB cable
4) Update the 'API_KEY' variable in the 'MainActivity2' and 'MainActivity3' files with your API key
5) Run the app to install it on your device and disconnect the cable once finished
6) Turn on Wi-Fi/Mobile Data and GPS on your device and open the app to check weather forecasts
