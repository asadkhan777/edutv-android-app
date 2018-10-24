# edutv-android-app
A lightweight video player for viewing informational media content provided by educational institutions in rural areas #furlenco #video #streaming


# Problem Statement : Android Task
An NGO is helping village kids by giving them Android phones so they can watch educational videos. The NGO wants an Android application on which these kids can watch videos. However, the village kids only have internet access in the NGO center.

The NGO wants an Android application that saves the video while a kid is watching it. This means that the kid will not need to re-download that video to watch it again.

Challenge:
[x] A video should be saved in the internal storage while user is streaming it.
[x] You can use this link to access the video: http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
[x] Make sure there is a single request going to the server for streaming and saving on the Android phone.

Bonus Point:
[x] Hating AsyncTasks!

# Proposed Solution : EduTv Video streaming app
A light-weight android application build using various cutting edge technologies including rxjava2, kotlin, retrofit, exoplayer, etc.

This player downloads the file in parallel while streaming the video inside the app.
Once a downloaded file is available, the app won't need to fetch that file from the net again (provided the app's data isn't cleared).

We achieved this using the *repository pattern* where both network & local data sources were queried and the fastest one would be fetched. If cache is not available, network would be hit and data would be cached in that network request itself.

Screenshots for the app are as below:

<p align="center">
  <img src="https://github.com/asadkhan777/edutv-android-app/blob/develop/screenshots/screenshot_1_com.asadkhan.furlenco.edutv.png" width="200" title="Screenshot 1">
  
  <img src="https://github.com/asadkhan777/edutv-android-app/blob/develop/screenshots/screenshot_2_com.asadkhan.furlenco.edutv.png" width="200" alt="Screenshot 2">

<img src="https://github.com/asadkhan777/edutv-android-app/blob/develop/screenshots/screenshot_3_com.asadkhan.furlenco.edutv.png" width="550" alt="Screenshot 3">
  
</p>
