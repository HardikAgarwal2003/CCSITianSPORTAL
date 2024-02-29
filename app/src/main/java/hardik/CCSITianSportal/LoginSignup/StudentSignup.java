package hardik.CCSITianSportal.LoginSignup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import hardik.CCSITianSportal.R;
import hardik.CCSITianSportal.StudentRegistration.StudentRegistration;

public class StudentSignup extends AppCompatActivity {
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    TextInputEditText edt_Enrollment, edt_Password, edt_ConfirmPassword;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        requestQueue = Volley.newRequestQueue(this);
        edt_Enrollment = findViewById(R.id.edt_enrollment);
        edt_Password = findViewById(R.id.edt_password);
        edt_ConfirmPassword = findViewById(R.id.edt_ConfirmPassword);
        btn_next = findViewById(R.id.btn_next);
    }

    public void goToStudentRegistration(View view) { // runs on click of "next" button

        String enrollment = edt_Enrollment.getText().toString().trim();
        String password = edt_Password.getText().toString().trim();
        String confirmPassword = edt_ConfirmPassword.getText().toString().trim();

        if (!(enrollment.isEmpty()) && !(password.isEmpty()) && !(confirmPassword.isEmpty())) {
            if (password.equals(confirmPassword)) {

                // sending data to database...
                sendAuthData(enrollment, password);

                // saving data in shared preferences named as "AuthDataPreference"...
                updateSharedPreferences(enrollment, password);

                // sending user to next activity (StudentRegistration) using intent...
                updateUI();

            } else {
                Toast.makeText(this, "Confirm password doesn't match !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "All fields are Mandatory !", Toast.LENGTH_SHORT).show();
        }
    }
    public void sendAuthData(String enrollment, String password) {

        // Location of file "insertAuthData.php" in present in htdocs folder of xampp...
        String url = "http://192.168.29.81:80/CCSITianSPORTAL_PHP/insertAuthData.php";

        // TODO: 10-12-2023 Change to GET if not works...
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(StudentSignup.this, "Signed up Successfully 👍", Toast.LENGTH_LONG).show();
                Log.d("AuthDataSentSuccess...", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentSignup.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                Log.d("AuthDataSentError...", error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> data = new HashMap<>();             // auth data from user...
                data.put("Enroll", enrollment);
                data.put("Pass", password);
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
    private void updateSharedPreferences(String enrollment, String password) {

        // creating and inserting data into shared preferences(AuthDataPreference)...
        sharedPreferences = getSharedPreferences("AuthDataPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Enrollment", enrollment);
        editor.putString("Password", password);
        editor.putBoolean("isLoggedIn", true);
        editor.putString("UserType", "Student");
        editor.apply();
    }
    private void updateUI() {
        Intent iStudentRegistration = new Intent(StudentSignup.this, StudentRegistration.class);
        iStudentRegistration.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iStudentRegistration);        // redirecting user to registration page...
    }
}