package hardik.CCSITianSportal.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.View;

import hardik.CCSITianSportal.R;

public class UserType extends AppCompatActivity {
    CardView card_Faculty, card_Student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);
        card_Faculty = findViewById(R.id.card_Faculty);
        card_Student = findViewById(R.id.card_Student);

        card_Faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iFacultyLogin = new Intent(UserType.this, FacultyLogin.class);
                startActivity(iFacultyLogin);
            }
        });
        card_Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iStudentLogin = new Intent(UserType.this, StudentLogin.class);
                startActivity(iStudentLogin);
            }
        });
    }
}