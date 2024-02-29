package hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyFragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Objects;

import hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyFragments.RecyclerViewFiles.NoOfEntries;
import hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyFragments.RecyclerViewFiles.RegistrationAdapter;
import hardik.CCSITianSportal.R;

public class FacultyHome extends Fragment {

    ArrayList<String> listEnrollment = new ArrayList<String>();
    ArrayList<String> listStudentName = new ArrayList<String>();
    RequestQueue requestQueue;
    RecyclerView recyclerView;
    int numberOfEntries;
    public FacultyHome() {
    }

    public static FacultyHome newInstance() {
        return new FacultyHome();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Context con = requireActivity().getApplicationContext();
        View view = getView();
        recyclerView = Objects.requireNonNull(view).findViewById(R.id.recycler_students);

        getAllStudentData(con);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_facultyhome, container, false);
    }
    private void getAllStudentData(Context con) {
        // getting all students enrollment from registration Table...
        String url="http://192.168.29.81:80/CCSITianSPORTAL_PHP/getStudentRegistrationData.php";
        requestQueue= Volley.newRequestQueue(con);

        // TODO: 14-12-2023 if not working then change to POST method...
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null,  new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RegistrationData", "Data fetched successfully");
                try {
                    numberOfEntries = response.length();
                    Log.d(">>>>>", numberOfEntries+"");
                    NoOfEntries size = new NoOfEntries();
                    size.setNoOfEntries(numberOfEntries);

                    // storing all students Enrollment_No from student_registration table into arraylist listEnrollment and listStudentName...
                    for (int i = 0; i < response.length(); i++){
                        listEnrollment.add(response.getJSONObject(i).getString("Enrollment_No"));
                        listStudentName.add(response.getJSONObject(i).getString("Student_Name"));
                        Log.d("RegistrationData_Enrollment", response.getJSONObject(i).getString("Enrollment_No"));
                        Log.d("RegistrationData_StudentName", response.getJSONObject(i).getString("Student_Name"));
                    }

                    // setting data to recycler view rows
                    RegistrationAdapter authorisedStudentAdapter = new RegistrationAdapter(con, listEnrollment, listStudentName, numberOfEntries);
                    recyclerView.setLayoutManager(new LinearLayoutManager(con));
                    recyclerView.setAdapter(authorisedStudentAdapter);

                    // TODO: 16-12-2023 see this in last and can be used in onclick of a recycler view row...
                    /*// getting index of a particular student in student_registration table...
                    user_idx = listEnrollment.indexOf(enrollment);*/

                } catch (JSONException e) {
                    Log.d("ErrorInFileFacultyHome", "JSONException Catch block...");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorInStudentLogin",error.toString());
                Toast.makeText(con, "error from file Student Login in volley errorListener()...", Toast.LENGTH_SHORT).show();
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);

    }
}