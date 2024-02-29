package hardik.CCSITianSportal.StudentRegistration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hardik.CCSITianSportal.HomePages.StudentHomepage.StudentFragments.StudentHome;
import hardik.CCSITianSportal.R;

public class StudentRegistration extends AppCompatActivity {
    String Enrollment, Student_Name, Email, Semester, Section, Phone_No, Gender, Program, Program_Code, Game1, Game2, Game3, HouseName, HouseIncharge;
    TextInputEditText edt_enrollment, edt_studentName, edt_email, edt_phoneNumber;
    RadioButton radioButton_male, radioButton_female;
    RadioGroup radioGroup_Gender;
    RequestQueue requestQueue;
    SharedPreferences sharedPreferences;
    Button btn_next;
    String[] Semesters = {"1", "2", "3", "4", "5", "6", "7", "8"};
    String[] Sections = {"NONE", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String[] Programs = {
            "BCA",
            "B.Sc (Animation)",
            "B.Sc-HONS(CS)",
            "B.Tech-CS",
            "B.Tech-CS (Lateral)",
            "B.Tech (ADCA-IBM)",
            "B.Tech (CTIS-IN)",
            "B.Tech (DS-IN)",
            "B.Tech (AI/ML/DL)",
            "B.Tech (AI/ML/DL) (Lateral)",
            "BCA-CTIS",
            "BCA-MAWT",
            "B.Tech (DS-TCS)",
            "M.Tech-CS",
            "MCA"
    };
    String[] ProgramCode = {
            "BCA-001",
            "BSC002",
            "BSC001",
            "BTECH001",
            "BTECH002",
            "BTECH020",
            "BTECH003",
            "BTECH025",
            "BTECH022",
            "BTECH026",
            "BCA002",
            "BCA004",
            "BTECH027",
            "BCA001",
            "MCA004"
    };
    String[] Games_1 = {
            "Cricket",
            "Football",
            "Volleyball",
            "Basketball",
            "Kabaddi",
            "Batminton",
            "Tug Of War",
            "Table Tennis",
            "100 mt",
            "Shotput",
            "Long Jump",
            "Discus Throw",
            "Javellin Throw",
            "Carrom",
            "Chess"
    };
    String[] Games_2_3 = {
            "NONE",
            "Cricket",
            "Football",
            "Volleyball",
            "Basketball",
            "Kabaddi",
            "Batminton",
            "Tug Of War",
            "Table Tennis",
            "100 mt",
            "Shotput",
            "Long Jump",
            "Discus Throw",
            "Javellin Throw",
            "Carrom",
            "Chess"
    };

//    // TODO: 03-12-2023 Replace the value of Incharge(value) with Incharge Names
//    HashMap<String, String> hashMap = new HashMap<String, String>()
//    {{
//        put("Emerald Eagles", "A");
//        put("Ruby Rhinos", "B");
//        put("Sapphire Sharks", "C");
//        put("Topaz Tigers", "D");
//        put("Dark Knights", "E");
//        put("Die Heart", "F");
//        put("The Warriors", "G");
//        put("Green Gladiators", "H");
//        put("The Hunters", "I");
//        put("Hawk", "J");
//        put("Falcon", "K");
//    }};

    String[] Houses = {
            "Emerald Eagles",
            "Ruby Rhinos",
            "Sapphire Sharks",
            "Topaz Tigers",
            "Dark Knights",
            "Die Heart",
            "The Warriors",
            "Green Gladiators",
            "The Hunters",
            "Hawk",
            "Falcon"
    };
    String[] HousesIncharge = {
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K"
    };
    Spinner spinnerSemester, spinnerSection, spinnerProgram, spinnerGame1, spinnerGame2, spinnerGame3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        // EditTexts (TextInputEditText)...
        edt_enrollment = findViewById(R.id.edt_enrollment);
        edt_studentName = findViewById(R.id.edt_studentName);
        edt_phoneNumber = findViewById(R.id.edt_phoneNumber);
        edt_email = findViewById(R.id.edt_email);

        //  Radio Groups and Buttons...
        radioGroup_Gender = findViewById(R.id.radioGroup_Gender);
        radioButton_male = findViewById(R.id.radioButton_male);
        radioButton_female = findViewById(R.id.radioButton_female);

        // Spinners...
        spinnerSemester = findViewById(R.id.spinner_semester);
        spinnerSection = findViewById(R.id.spinner_section);
        spinnerProgram = findViewById(R.id.spinner_program);
        spinnerGame1 = findViewById(R.id.spinner_game1);
        spinnerGame2 = findViewById(R.id.spinner_game2);
        spinnerGame3 = findViewById(R.id.spinner_game3);

        // Button...
        btn_next = findViewById(R.id.btn_next);

        // setting all spinners...
        setSpinners();

        // TODO: 21-12-2023 NOTE : I have relocate this piece of code inside function goToStudentHome()
        /*// getting values of every fields from views...
        Enrollment = edt_enrollment.getText().toString().trim();
        Student_Name = edt_studentName.getText().toString().trim();
        Email = edt_email.getText().toString().trim();
        Semester = spinnerSemester.getSelectedItem().toString().trim();
        Section = spinnerSection.getSelectedItem().toString();
        Phone_No = edt_phoneNumber.getText().toString().trim();
        if (radioButton_male.isChecked()) {
            Gender = "Male";
        }
        if (radioButton_female.isChecked()) {
            Gender = "Female";
        }
        Program = spinnerProgram.getSelectedItem().toString().trim();
        Program_Code = getProgram_Code(Program);
        Game1 = spinnerGame1.getSelectedItem().toString().trim();
        Game2 = spinnerGame2.getSelectedItem().toString().trim();
        Game3 = spinnerGame3.getSelectedItem().toString().trim();
        HouseName = getHouseName(Semester, Program);
        HouseIncharge = getHouseIncharge(HouseName);*/

    }  /* onCreate() Ends */

    private void setSpinners() {
        ArrayAdapter arrayAdapterSemester = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Semesters);
        arrayAdapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(arrayAdapterSemester);

        ArrayAdapter arrayAdapterSection = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Sections);
        arrayAdapterSection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSection.setAdapter(arrayAdapterSection);

        ArrayAdapter arrayAdapterProgram = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Programs);
        arrayAdapterProgram.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgram.setAdapter(arrayAdapterProgram);

        ArrayAdapter arrayAdapterGame1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Games_1);
        arrayAdapterGame1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGame1.setAdapter(arrayAdapterGame1);

        ArrayAdapter arrayAdapterGame2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Games_2_3);
        arrayAdapterGame2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGame2.setSelection(0);
        spinnerGame2.setAdapter(arrayAdapterGame2);

        ArrayAdapter arrayAdapterGame3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Games_2_3);
        arrayAdapterGame3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGame3.setSelection(0);
        spinnerGame3.setAdapter(arrayAdapterGame3);
    }

    public void goToStudentHome(View view) {

        // getting values of every fields from views...
        Enrollment = edt_enrollment.getText().toString().trim();
        Student_Name = edt_studentName.getText().toString().trim();
        Email = edt_email.getText().toString().trim();
        Semester = spinnerSemester.getSelectedItem().toString().trim();
        Section = spinnerSection.getSelectedItem().toString();
        Phone_No = edt_phoneNumber.getText().toString().trim();
        if (radioButton_male.isChecked()) {
            Gender = "Male";
        }
        if (radioButton_female.isChecked()) {
            Gender = "Female";
        }
        Program = spinnerProgram.getSelectedItem().toString().trim();
        Program_Code = getProgram_Code(Program);
        Game1 = spinnerGame1.getSelectedItem().toString().trim();
        Game2 = spinnerGame2.getSelectedItem().toString().trim();
        Game3 = spinnerGame3.getSelectedItem().toString().trim();
        HouseName = getHouseName(Semester, Program);
        HouseIncharge = getHouseIncharge(HouseName);

        if ( !(Enrollment.isEmpty()) && !(Student_Name.isEmpty()) && !(Email.isEmpty()) && !(Semester.isEmpty()) && !(Section.isEmpty()) && !(Phone_No.isEmpty()) && !(Gender.isEmpty()) && !(Program.isEmpty()) && !(Program_Code.isEmpty()) && !(Game1.isEmpty()) && !(Game2.isEmpty()) && !(Game3.isEmpty()) && !(HouseName.isEmpty()) && !(HouseIncharge.isEmpty()) ) {
            // sending data to database...
            sendRegistrationData(Enrollment, Student_Name, Email, Semester, Section, Phone_No, Gender, Program, Program_Code, Game1, Game2, Game3, HouseName, HouseIncharge);

            // saving data in shared preferences named as "RegistrationDataPreference"...
            updateSharedPreferences(Enrollment, Student_Name, Email, Semester, Section, Phone_No, Gender, Program, Program_Code, Game1, Game2, Game3, HouseName, HouseIncharge);

            // sending user to next activity (studentHome) using intent...
            updateUI();

        }else {
            Toast.makeText(this, "All fields are Mandatory !", Toast.LENGTH_SHORT).show();
        }
    }     // runs on click of "next" button
    public void sendRegistrationData(String Enrollment, String Student_Name, String Email, String Semester, String Section, String Phone_No, String Gender, String Program, String Program_Code, String Game1, String Game2, String Game3, String HouseName, String HouseIncharge){

        // Location of file "insertStudentRegistrationData.php" in present in htdocs folder of xampp...
        String url="http://192.168.29.81:80/CCSITianSPORTAL_PHP/insertStudentRegistrationData.php";

        // TODO: 10-12-2023 Change to GET if not works...
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(StudentRegistration.this, "Registration Successful 👍", Toast.LENGTH_LONG).show();
                Log.d("RegistrationDataSentSuccess...", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentRegistration.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                Log.d("RegistrationDataSentError...",error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String>data=new HashMap<>();             // registration data from user...
                data.put("Enroll",Enrollment);
                data.put("Student_Name", Student_Name);
                data.put("Email", Email);
                data.put("Semester", Semester);
                data.put("Section", Section);
                data.put("Phone_No", Phone_No);
                data.put("Gender", Gender);
                data.put("Program", Program);
                data.put("Program_Code", Program_Code);
                data.put("Game1", Game1);
                data.put("Game2", Game2);
                data.put("Game3", Game3);
                data.put("HouseName", HouseName);
                data.put("HouseIncharge", HouseIncharge);
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);

    }
    private void updateSharedPreferences(String Enrollment, String Student_Name, String Email, String Semester, String Section, String Phone_No, String Gender, String Program, String Program_Code, String Game1, String Game2, String Game3, String HouseName, String HouseIncharge) {
        // creating and inserting data into shared preferences (RegistrationDataPreference)...
        sharedPreferences = getSharedPreferences("RegistrationDataPreference",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Enrollment", Enrollment);
        editor.putString("Student_Name", Student_Name);
        editor.putString("Email", Email);
        editor.putString("Semester", Semester);
        editor.putString("Section", Section);
        editor.putString("Phone_No", Phone_No);
        editor.putString("Gender", Gender);
        editor.putString("Program", Program);
        editor.putString("Program_Code", Program_Code);
        editor.putString("Game1", Game1);
        editor.putString("Game2", Game2);
        editor.putString("Game3", Game3);
        editor.putString("HouseName", HouseName);
        editor.putString("HouseIncharge", HouseIncharge);
        editor.putBoolean("RegisteredSuccessCardVisibility", true);
        editor.apply();
    }
    private void updateUI() {
        Intent iStudentHome = new Intent(StudentRegistration.this, StudentHome.class);
        iStudentHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(iStudentHome);        // redirecting user to registration page...
    }
    public String getProgram_Code(String Program) {
        if(Objects.equals(Program, Programs[0])){
            Program_Code = ProgramCode[0];
        } else if (Objects.equals(Program, Programs[1])) {
            Program_Code = ProgramCode[1];
        } else if (Objects.equals(Program, Programs[2])) {
            Program_Code = ProgramCode[2];
        } else if (Objects.equals(Program, Programs[3])) {
            Program_Code = ProgramCode[3];
        } else if (Objects.equals(Program, Programs[4])) {
            Program_Code = ProgramCode[4];
        } else if (Objects.equals(Program, Programs[5])) {
            Program_Code = ProgramCode[5];
        } else if (Objects.equals(Program, Programs[6])) {
            Program_Code = ProgramCode[6];
        } else if (Objects.equals(Program, Programs[7])) {
            Program_Code = ProgramCode[7];
        } else if (Objects.equals(Program, Programs[8])) {
            Program_Code = ProgramCode[8];
        } else if (Objects.equals(Program, Programs[9])) {
            Program_Code = ProgramCode[9];
        } else if (Objects.equals(Program, Programs[10])) {
            Program_Code = ProgramCode[10];
        } else if (Objects.equals(Program, Programs[11])) {
            Program_Code = ProgramCode[11];
        } else if (Objects.equals(Program, Programs[12])) {
            Program_Code = ProgramCode[12];
        } else if (Objects.equals(Program, Programs[13])) {
            Program_Code = ProgramCode[13];
        } else if (Objects.equals(Program, Programs[14])) {
            Program_Code = ProgramCode[14];
        }
        return Program_Code;
    }

    // TODO: 10-12-2023 Complete this function...
    public String getHouseName(String Semester, String Program){
        if(false){
            // write all conditions to set House Name here...
        }else{
            HouseName = "Yet to be assigned";
        }
        return HouseName;
    }
    public String getHouseIncharge(String HouseName){
        if(Objects.equals(HouseName, Houses[0])){
            HouseIncharge = HousesIncharge[0];
        }else if(Objects.equals(HouseName, Houses[1])){
            HouseIncharge = HousesIncharge[1];
        }else if(Objects.equals(HouseName, Houses[2])){
            HouseIncharge = HousesIncharge[2];
        }else if(Objects.equals(HouseName, Houses[3])){
            HouseIncharge = HousesIncharge[3];
        }else if(Objects.equals(HouseName, Houses[4])){
            HouseIncharge = HousesIncharge[4];
        }else if(Objects.equals(HouseName, Houses[5])){
            HouseIncharge = HousesIncharge[5];
        }else if(Objects.equals(HouseName, Houses[6])){
            HouseIncharge = HousesIncharge[6];
        }else if(Objects.equals(HouseName, Houses[7])){
            HouseIncharge = HousesIncharge[7];
        }else if(Objects.equals(HouseName, Houses[8])){
            HouseIncharge = HousesIncharge[8];
        }else if(Objects.equals(HouseName, Houses[9])){
            HouseIncharge = HousesIncharge[9];
        }else if(Objects.equals(HouseName, Houses[10])){
            HouseIncharge = HousesIncharge[10];
        }else{
            HouseIncharge = "Yet to be assigned";
        }
        return HouseIncharge;
    }
}