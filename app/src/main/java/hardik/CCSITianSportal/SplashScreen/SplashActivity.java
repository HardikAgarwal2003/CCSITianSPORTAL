package hardik.CCSITianSportal.SplashScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyHomepage;
import hardik.CCSITianSportal.HomePages.StudentHomepage.StudentHomePage;
import hardik.CCSITianSportal.LoginSignup.UserType;
import hardik.CCSITianSportal.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // to directly redirect user to StudentHomePage if already logged in...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("AuthDataPreference", MODE_PRIVATE);
                boolean checkIfAlreadyLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                String userType = sharedPreferences.getString("UserType", "Student");
                if (checkIfAlreadyLoggedIn) {
                    if (userType.equalsIgnoreCase("Student")) {
                        Intent iStudentHomePage = new Intent(SplashActivity.this, StudentHomePage.class);
                        startActivity(iStudentHomePage);
                        finish();                           // to make the back action closes the whole application and not redirecting to the splash screen when clucking back button
                    } else {
                        Intent iFacultyHomePage = new Intent(SplashActivity.this, FacultyHomepage.class);
                        startActivity(iFacultyHomePage);
                        finish();
                    }
                } else {
                    Intent iSelectUserType = new Intent(SplashActivity.this, UserType.class);
                    startActivity(iSelectUserType);
                    finish();
                }
            }
        }, 2500);
    }
}