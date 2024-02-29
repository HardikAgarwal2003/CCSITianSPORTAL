package hardik.CCSITianSportal.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import hardik.CCSITianSportal.HomePages.StudentHomepage.StudentHomePage;
import hardik.CCSITianSportal.R;

public class StudentLogin extends AppCompatActivity {
    int user_idx;
    public static boolean existing;
    ArrayList<String> listEnrollment = new ArrayList<String>();
    ArrayList<String> listPassword = new ArrayList<String>();
    String[] studentRegistrationData = new String[14]; // for storing student's Registration data...
    TextInputEditText edt_enrollment, edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        edt_enrollment = findViewById(R.id.edt_enrollment);
        edt_password = findViewById(R.id.edt_password);
    }

    public void goToSignupActivity(View view) {
        updateUI("iSignup");
    }
    public void goToStudentHomepage(View view) {

        if( !(edt_enrollment.getText().toString().isEmpty()) && !(edt_password.getText().toString().isEmpty()) ){
            String enrollment = edt_enrollment.getText().toString().trim();
            String password = edt_password.getText().toString().trim();

            // check if the user is already registered (existing user) ...
            checkUserCredentials(enrollment, password);
            Log.d("existing?", existing +"");
            if(existing){
                // setting registration data to shared preferences...
                getAllRegistrationData(enrollment);

                // sending user to StudentHomePage Activity...
                updateUI("iStudentHomePage");
            }else{
                Toast.makeText(StudentLogin.this, "User Not Found ! Check credentials or signup please...", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(StudentLogin.this, "Both fields are mandatory...", Toast.LENGTH_SHORT).show();
        }


    }
    public void checkUserCredentials(String enrollment, String password){
        String url="http://192.168.29.81:80/CCSITianSPORTAL_PHP/getAuthData.php";
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // TODO: 14-12-2023 if not working then change to POST method...
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("AuthorisedData", "Data fetched successfully");
                try {
                    // storing all auth data into arraylists...
                    for (int i = 0; i < response.length(); i++){
                        listEnrollment.add(response.getJSONObject(i).getString("Enrollment_No"));
                        listPassword.add(response.getJSONObject(i).getString("Password"));
                        Log.d("AuthData", response.getJSONObject(i).getString("Enrollment_No"));
                        Log.d("AuthData", response.getJSONObject(i).getString("Password"));
                    }
                    // checking user existence...
                    if(listEnrollment.contains(enrollment)){
                        int idx_enrollment = listEnrollment.indexOf(enrollment);
                        Log.d("idx_enrollment :- ", idx_enrollment+"");
                        Log.d("idx_enrollment :- ", listPassword.get(idx_enrollment)+"");
                        if(Objects.equals(listPassword.get(idx_enrollment), password)){
                            // user is existing 👍...
                            userExistence(true);

                            // creating and inserting data into shared preferences(AuthDataPreference)...
                            SharedPreferences sharedPreferencesAuth = getSharedPreferences("AuthDataPreference", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferencesAuth.edit();
                            editor.putString("Enrollment", enrollment);
                            editor.putString("Password", password);
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("UserType", "Student");
                            editor.apply();
                        }
                    }
                } catch (JSONException e) {
                    Log.d("ErrorInFileStudentLogin", "JSONException Catch block...");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorInStudentLogin",error.toString());
                Toast.makeText(StudentLogin.this, "error from file Student Login in volley errorListener()...", Toast.LENGTH_SHORT).show();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
        Log.d("existing?", existing +"");
    }

    public void userExistence(boolean existing1){
        existing = existing1;
    }
    private void getAllRegistrationData(String enrollment) {
        // getting all students enrollment from registration Table...

        String url="http://192.168.29.81:80/CCSITianSPORTAL_PHP/getStudentRegistrationData.php";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        // TODO: 14-12-2023 if not working then change to POST method...
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RegistrationData", "Data fetched successfully");
                try {
                    // storing all students Enrollment_No from student_registration table into arraylist listEnrollment...
                    for (int i = 0; i < response.length(); i++){
                        listEnrollment.add(response.getJSONObject(i).getString("Enrollment_No"));
                        Log.d("RegistrationData_Enrollment", response.getJSONObject(i).getString("Enrollment_No"));
                    }

                    // getting index of a particular student in student_registration table...
                    user_idx = listEnrollment.indexOf(enrollment);
                    Log.d("user_idx :- ", user_idx+"");

                    // creating and inserting data into shared preferences...
                    updateSharedPreferences(user_idx, response);


                } catch (JSONException e) {
                    Log.d("ErrorInFileStudentLogin", "JSONException Catch block...");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorInStudentLogin",error.getMessage());
                Toast.makeText(StudentLogin.this, "error from file Student Login in volley errorListener()...", Toast.LENGTH_SHORT).show();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }
    private void updateSharedPreferences(int user_idx, JSONArray response){
        try {
            studentRegistrationData[0] = response.getJSONObject(user_idx).getString("Enrollment_No");
            studentRegistrationData[1] = response.getJSONObject(user_idx).getString("Student_Name");
            studentRegistrationData[2] = response.getJSONObject(user_idx).getString("Email");
            studentRegistrationData[3] = response.getJSONObject(user_idx).getString("Semester");
            studentRegistrationData[4] = response.getJSONObject(user_idx).getString("Section");
            studentRegistrationData[5] = response.getJSONObject(user_idx).getString("Phone_No");
            studentRegistrationData[6] = response.getJSONObject(user_idx).getString("Gender");
            studentRegistrationData[7] = response.getJSONObject(user_idx).getString("Program");
            studentRegistrationData[8] = response.getJSONObject(user_idx).getString("Program_Code");
            studentRegistrationData[9] = response.getJSONObject(user_idx).getString("Game1");
            studentRegistrationData[10] = response.getJSONObject(user_idx).getString("Game2");
            studentRegistrationData[11] = response.getJSONObject(user_idx).getString("Game3");
            studentRegistrationData[12] = response.getJSONObject(user_idx).getString("HouseName");
            studentRegistrationData[13] = response.getJSONObject(user_idx).getString("HouseIncharge");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < 14; i++){
            Log.d("studentRegistrationData[" + i + "]", studentRegistrationData[i]+"");
        }

        // creating and inserting data into shared preferences(RegistrationDataPreference)...
        SharedPreferences sharedPreferencesRegistration = getSharedPreferences("RegistrationDataPreference",MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferencesRegistration.edit();
        editor1.putString("Enrollment", studentRegistrationData[0]);
        editor1.putString("Student_Name", studentRegistrationData[1]);
        editor1.putString("Email", studentRegistrationData[2]);
        editor1.putString("Semester", studentRegistrationData[3]);
        editor1.putString("Section", studentRegistrationData[4]);
        editor1.putString("Phone_No", studentRegistrationData[5]);
        editor1.putString("Gender", studentRegistrationData[6]);
        editor1.putString("Program", studentRegistrationData[7]);
        editor1.putString("Program_Code", studentRegistrationData[8]);
        editor1.putString("Game1", studentRegistrationData[9]);
        editor1.putString("Game2", studentRegistrationData[10]);
        editor1.putString("Game3", studentRegistrationData[11]);
        editor1.putString("HouseName", studentRegistrationData[12]);
        editor1.putString("HouseIncharge", studentRegistrationData[13]);
        editor1.putBoolean("RegisteredSuccessCardVisibility", true);
        editor1.apply();

    }
    private void updateUI(String activity) {
        if(activity.equals("iSignup")){
            Intent iSignup = new Intent(StudentLogin.this, StudentSignup.class);
            startActivity(iSignup);
        }
        if(activity.equals("iStudentHomePage")){
            Intent iStudentHomePage = new Intent(StudentLogin.this, StudentHomePage.class);
            startActivity(iStudentHomePage);
            finish();
        }
    }

}
