package hardik.CCSITianSportal.HomePages.FacultyHomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import hardik.CCSITianSportal.R;

public class StudentDetails extends AppCompatActivity {
    String Enrollment, Student_Name, Email, Semester, Section, Phone_No, Gender, Program, Program_Code, Game1, Game2, Game3, HouseName, HouseIncharge;
    TextView txt_studentName, txt_email, txt_phoneNo, txt_programCode, txt_houseName, txt_game1, txt_game2, txt_game3;
    ImageView img_game1, img_game2, img_game3;
    CardView card_Game1, card_Game2, card_Game3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // getting index of the selected row of the student...
        int idx = getIntent().getIntExtra("student_idx", 0);

        //TODO: fetch particular student all details using student idx...
        getStudentRegistrationData(idx);

        //TODO: set all details into layout...
        txt_studentName = (TextView) findViewById(R.id.txt_studentName);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_phoneNo = (TextView) findViewById(R.id.txt_phoneNo);
        txt_programCode = (TextView) findViewById(R.id.txt_programCode);
        txt_houseName = (TextView) findViewById(R.id.txt_houseName);
        txt_game1 = (TextView) findViewById(R.id.txt_game1);
        txt_game2 = (TextView) findViewById(R.id.txt_game2);
        txt_game3 = (TextView) findViewById(R.id.txt_game3);
        img_game1 = (ImageView) findViewById(R.id.img_game1);
        img_game2 = (ImageView) findViewById(R.id.img_game2);
        img_game3 = (ImageView) findViewById(R.id.img_game3);
        card_Game1 = (CardView) findViewById(R.id.card_game1);
        card_Game2 = (CardView) findViewById(R.id.card_game2);
        card_Game3 = (CardView) findViewById(R.id.card_game3);

        // setting values  on the fragment studentHome page...
        txt_studentName.setText(Student_Name);
        txt_email.setText(Email);
        txt_phoneNo.setText(Phone_No);
        txt_programCode.setText(Program_Code);
        txt_houseName.setText(HouseName);

        txt_game1.setText(Game1);
        img_game1.setImageResource(getGameImages(Game1));

        // setting visibility to GONE if selected NONE (only for game 2 and 3)...
        if (Game2.equalsIgnoreCase("NONE")) {
            card_Game2.setVisibility(View.GONE);
        } else {
            txt_game2.setText(Game2);
            img_game2.setImageResource(getGameImages(Game2));
        }
        if (Game3.equalsIgnoreCase("NONE")) {
            card_Game3.setVisibility(View.GONE);
        } else {
            txt_game3.setText(Game3);
            img_game3.setImageResource(getGameImages(Game3));
        }
    }

    private void getStudentRegistrationData(int idx) {
        // getting all students enrollment from registration Table...
        String url="http://192.168.29.81:80/CCSITianSPORTAL_PHP/getStudentRegistrationData.php";
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // TODO: 14-12-2023 if not working then change to POST method...
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("StudentRegistrationData", "Data fetched successfully");
                try {
                    // TODO: 19-12-2023 make the variable and this function to static as directed in the comment (if not working)...
/*
                    // "static void main" must be defined in a public class.
                    public class Main {
                        static int a, b; // add static keyword
                        static String str; // add static keyword
                        public static void main(String[] args) {
                            setData();
                            System.out.println(a);
                            System.out.println(b);
                            System.out.println(str);
                        }
                        static void setData(){
                            a = 10;
                            b = 20;
                            str = "Hello Hardik";
                        }
                    }
*/
                    // all data of a particular student whose index is passed from the previous activity through intent...
                    Enrollment = response.getJSONObject(idx).getString("Enrollment_No");
                    Student_Name = response.getJSONObject(idx).getString("Student_Name");
                    Email = response.getJSONObject(idx).getString("Email");
                    Semester = response.getJSONObject(idx).getString("Semester");
                    Section = response.getJSONObject(idx).getString("Section");
                    Phone_No = response.getJSONObject(idx).getString("Phone_No");
                    Gender = response.getJSONObject(idx).getString("Gender");
                    Program = response.getJSONObject(idx).getString("Program");
                    Program_Code = response.getJSONObject(idx).getString("Program_Code");
                    Game1 = response.getJSONObject(idx).getString("Game1");
                    Game2 = response.getJSONObject(idx).getString("Game2");
                    Game3 = response.getJSONObject(idx).getString("Game3");
                    HouseName = response.getJSONObject(idx).getString("HouseName");
                    HouseIncharge = response.getJSONObject(idx).getString("HouseIncharge");

                } catch (JSONException e) {
                    Log.d("ErrorInFileStudentDetail", "JSONException Catch block...");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorInFileStudentDetail",error.toString());
                Toast.makeText(StudentDetails.this, "error from file StudentDetail in volley errorListener()...", Toast.LENGTH_SHORT).show();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);
    }

    // TODO: 17-12-2023 Set all sports images from internet (all should be in same size and check by using in card before cropping)...
    public int getGameImages(String Game){
        if( Game.equalsIgnoreCase("Cricket") ){
            return R.drawable.img_cricket;
        } else if ( Game.equalsIgnoreCase("Football") ) {
            return R.drawable.img_football;
        }else if ( Game.equalsIgnoreCase("Volleyball") ) {
            return R.drawable.img_volleyball;
        }else if ( Game.equalsIgnoreCase("Basketball") ) {
            return R.drawable.img_basketball;
        }else if ( Game.equalsIgnoreCase("Kabaddi") ) {
            return R.drawable.img_kabaddi;
        }else if ( Game.equalsIgnoreCase("Badminton") ) {
            return R.drawable.img_batminton;
        }else if ( Game.equalsIgnoreCase("Tug Of War") ) {
            return R.drawable.img_tug_of_war;
        }else if ( Game.equalsIgnoreCase("Table Tennis") ) {
            return R.drawable.img_table_tennis;
        }else if ( Game.equalsIgnoreCase("100 mt") ) {
            return R.drawable.img_100mt;
        }else if ( Game.equalsIgnoreCase("Shotput") ) {
            return R.drawable.img_shotput;
        }else if ( Game.equalsIgnoreCase("Long Jump") ) {
            return R.drawable.img_long_jump;
        }else if ( Game.equalsIgnoreCase("Discus Throw") ) {
            return R.drawable.img_discus_throw;
        }else if ( Game.equalsIgnoreCase("Javellin Throw") ) {
            return R.drawable.img_javelin_throw;
        }else if ( Game.equalsIgnoreCase("Carrom") ) {
            return R.drawable.img_carrom;
        }else if ( Game.equalsIgnoreCase("Chess") ) {
            return R.drawable.img_chess;
        }else {
            return 0;
        }
    }
}