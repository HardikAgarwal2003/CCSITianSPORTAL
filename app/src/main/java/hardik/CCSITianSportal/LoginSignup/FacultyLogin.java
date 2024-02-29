package hardik.CCSITianSportal.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyHomepage;
import hardik.CCSITianSportal.R;

public class FacultyLogin extends AppCompatActivity {
    final String faculty_email = "hardik140103@gmail.com";
    final String faculty_password = "password_140103";
    TextInputEditText edt_facultyEmail, edt_facultyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edt_facultyEmail = findViewById(R.id.edt_facultyEmail);
        edt_facultyPassword = findViewById(R.id.edt_facultyPassword);

        setContentView(R.layout.activity_faculty_login);
    }
    public void goToFacultyHomepage(View view) {
        String email = edt_facultyEmail.getText().toString().trim();
        String password = edt_facultyPassword.getText().toString().trim();

        // verifying email and password...
        if( !(email.isEmpty()) && !(password.isEmpty()) ){
            if( email.equals(faculty_email) && password.equals(faculty_password)){

                // creating and inserting data into shared preferences(AuthDataPreference)...
                updateSharedPreferences();

                // sending user to FacultyHomepage...
                updateUI();

            }else{
                Toast.makeText(FacultyLogin.this, "Email or Password are incorrect !", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(FacultyLogin.this, "Both Fields are mandatory !", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("AuthDataPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("UserType", "Faculty");
        editor.apply();
    }
    private void updateUI() {
        Intent iFacultyHome = new Intent(FacultyLogin.this, FacultyHomepage.class);
        startActivity(iFacultyHome);
        finish();
    }
}