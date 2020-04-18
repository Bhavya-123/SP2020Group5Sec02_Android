# SP2020Group5Sec02_Android
## APP Name : Jobs Made Easy
## Group Members
- Bhavya Deepthi Gorrepati
- Venkat Prudhvi Dommaraju
- Kamal DonthiReddy
- Dheeraj Edupuganti
### Work Division
The app can be segregated into three parts like Signups and Logins, Staff operations,Student Operations.
- Signups & Logins(Prudhvi)
- Staff Operations(Bhavya)
- Student Operations(Kamal & Dheeraj)

### Test Credentials
Works with any credentials. Once can create credentials for login by signing up.
**Sample Credentials**
- Student Login: Username: s123@nwmissouri.edu, Password-hello123
- Staff Login: EmpId-134286, Password-enterpassword

### Supported Devices(Tested by us)
- Pixel 2 XL API 26(Name)  Android 8.0(Target)
- Pixel XL API 26(Name)  Android 8.0(Target)
- Nexus 6P API 26(Name) Android 8.0(Target)

### APK File
apk file is in the following path
[APK file](https://github.com/Bhavya-123/SP2020Group5Sec02_Android/blob/master/SP2020Group5/jobsmadeeasy.apk)

### Sequence Information

**Flow Chart**
 ![Flow Chart](https://github.com/Bhavya-123/SP2020Group5Sec02_Android/blob/master/Flow%20Chart.png)

   1. On Lunch of the app, Home screen appears.
   2. Home Screen has the below following
      - Two sign up buttons for Staff and student individually
      - Two logins individually for Staff and Student
   3. On Click of Respective Sign up button user will be navigated to Sign up page to enter the details. After successful validation of       the entered details, Registeration will be successful and data will be stored in database and will be rediredcted to Respective         Login Screen.
   4. In the login screen, the entered username and password will be checked against database and if it found user will be navigated to       either Staff Home Activity or student Activity.
   5. If Logged in through staff credentials, will land into Staff Home where the Staff Home Activity is a navigation drawer with five         menu items called Home,Post a Job, View Jobs, Profile and Help
      - Add a Job: This is a functionality where University staff can add Jobs if all validations are successful and will be stored in           the database.
      - View Jobs: All jobs stored in the database will be displayed.
      - Profile: Logged in details will be displayed which include Name,EmpId,password and a mode to change the password. On click of           change in password text view password can be changed and updated in firebase databse
      - Help: If Staff has some issues,it can be raised through this functionality which will be stored in the database.
   6. If logged in through student credentials ,will land into the student home activity where the Student Home Activity is a navigation       drawer with six menu items called Home, My Jobs, Search, Major, Profile and Help.
      - My jobs: This functionality displays the jobs applied by the student from the database.
      - Major: This displays the jobs posted by the staff based on the Major selected from the database
      - Profile: This functionality displays the logged in student credentials like name, email id and password with a provision to            change password and update in the database.
      - Help: This helps students to raise queries if any and those will be persisted in the database.
   7. If user clicks on Log out button from the overflow menu options user(Staff/Student) will be navigated to Home Screen
      
   

