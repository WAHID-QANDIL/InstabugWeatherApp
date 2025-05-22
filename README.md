![img.png](images/img.png)

# **NOTE: THIS APP DID NOT PASS THE CHALLENGE**
[Android Internship Task 2025-1.pdf](https://github.com/user-attachments/files/20369903/Android.Internship.Task.2025-1.pdf)


# Application Behaviour
## First launch:
The app request a location permission to get the current device `lat`,`lon` information
If it not granted for some reason, the application did **NOT** run and request to open settings and enable location permission for the app
If it granted the app make an API call to https://www.visualcrossing.com/ to get weather, then parse json and extract 5 forecast days to cache them into database
The API-calls are optimized by restrict the number of calls every 1H, so, if the time cache out did NOT expired, no new API calls even the user make pull-refresh, and get cached data instead.
**This optimization needed `if we run a large-scale app to make sure our backend din not fail because` of the number of requests, and also, the weather here won't change in an hour.**

# APK-LINK 
**https://drive.google.com/file/d/1J2CqjUUKybZMcqZrQXQACFy378KQopca/view?usp=drive_link**

# App Techs,Tools
<ol>
<li>Clean Architecture**</li>
<li>Material3</li>
<li>Compose</li>
<li>Kotlin</li>
<li>SQLite Database (""I'm NOT using Room because it considered to be 3rd party lib")</li>
<li>SharedPreferences(""I can go with DataStore for more optimization and read/write speed")</li>
<li>Manual DI (""I'm NOT using Dagger/Dagger-Hilt because all of then considered to be 3rd party libs")</li>
<li>Single Activity</li>
<li>ViewModels</li>
<li>CallBacks("Not AsyncTask because it's deprecated"") </li>
<li>Threading (""No Coroutines usage because it considered to be 3rd party lib")</li>
<li>LiveData</li>
<li>HttpUrlConnection</li>
<li>Compose Navigation</li>
<li>Junit</li>
</ol>

# App Screen Shots


<table>
     

  
  <tr>
    <td><img src="images/Screenshot%202025-05-10%20042119.png" alt="Image 1" width="400"/></td>
     <td><img src="images/Screenshot%202025-05-10%20050911.png" alt="Image 2"/></td>
   
   
  </tr>

   <tr>
    <td><img src="images/Screenshot%202025-05-10%20042220.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot%202025-05-10%20042232.png" alt="Image 2" width="400"/></td>
  </tr>


   <tr>
    <td><img src="images/Screenshot%202025-05-10%20042328.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot 2025-05-10 042441.png" alt="Image 2" width="400"/></td>
  </tr>



   <tr>
    <td><img src="images/Screenshot%202025-05-10%20042402.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot%202025-05-10%20125417.png" alt="Image 2" width="400"/></td>
  </tr>



   <tr>
    <td><img src="images/Screenshot%202025-05-10%20042441.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot%202025-05-10%20042516.png" alt="Image 2" width="400"/></td>
  </tr>

   <tr>
    <td><img src="images/Screenshot%202025-05-10%20042539.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot%202025-05-10%20044217.png" alt="Image 2" width="400"/></td>
  </tr>



   <tr>
    <td><img src="images/Screenshot%202025-05-10%20053519.png" alt="Image 1" width="400"/></td>
    <td><img src="images/Screenshot%202025-05-10%20042206.png" alt="Image 2" width="400"/></td>
  </tr>

  
</table>

Considerations:
# **This crash has FIXED**
The app suppose the GPS provider is enabled, if it doesn't, the app will throw an exception and crash
