package hardik.CCSITianSportal.HomePages.StudentHomepage.StudentFragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import hardik.CCSITianSportal.R;

public class StudentHome extends Fragment {
    CardView card_Game1, card_Game2, card_Game3, cardRegistered;
    // TODO: 14-12-2023 Change strings to static if values cannot be retrieved from method getAllValues() means values are not updated in strings...
    static String Enrollment, Student_Name, Email, Semester, Section, Phone_No, Gender, Program, Program_Code, Game1, Game2, Game3, HouseName, HouseIncharge;
    static boolean RegisteredSuccessCardVisibility;
    TextView txt_hiUser, txt_studentName, txt_email, txt_phoneNo, txt_programCode, txt_houseName, txt_game1, txt_game2, txt_game3;
    ImageView img_game1, img_game2, img_game3;
    Button btn_registeredSuccess;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 21-12-2023 NOTE : I commented these two lines and moved all the commented portions to onCreateView method...
//        Context con = requireActivity().getApplicationContext();
//        View view = getView();

        /*if (view != null) {
            txt_hiUser = (TextView) view.findViewById(R.id.txt_hiUser);
            txt_studentName = (TextView) view.findViewById(R.id.txt_studentName);
            txt_email = (TextView) view.findViewById(R.id.txt_email);
            txt_phoneNo = (TextView) view.findViewById(R.id.txt_phoneNo);
            txt_programCode = (TextView) view.findViewById(R.id.txt_programCode);
            txt_houseName = (TextView) view.findViewById(R.id.txt_houseName);
            txt_game1 = (TextView) view.findViewById(R.id.txt_game1);
            txt_game2 = (TextView) view.findViewById(R.id.txt_game2);
            txt_game3 = (TextView) view.findViewById(R.id.txt_game3);
            img_game1 = (ImageView) view.findViewById(R.id.img_game1);
            img_game2 = (ImageView) view.findViewById(R.id.img_game2);
            img_game3 = (ImageView) view.findViewById(R.id.img_game3);
            card_Game1 = (CardView) view.findViewById(R.id.card_game1);
            card_Game2 = (CardView) view.findViewById(R.id.card_game2);
            card_Game3 = (CardView) view.findViewById(R.id.card_game3);
            cardRegistered = (CardView) view.findViewById(R.id.card_registeredSuccessfully);
            btn_registeredSuccess = (Button) view.findViewById(R.id.btn_registeredSuccess);

            // getting shared preferences values (from RegistrationDataPreference)...
            getAllPreferencesValues(con);

            // setting values  on the fragment studentHome page
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

            // checking whether to show RegisteredSuccessCardVisibility or not...
            if (RegisteredSuccessCardVisibility) {
                cardRegistered.setVisibility(View.GONE);
            } else {
                cardRegistered.setVisibility(View.VISIBLE);
            }
        } else {
            Log.d("View is NULL", "Cannot find any view in \"StudentHome.java\"");
        }

        btn_registeredSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCardVisibility(v);
            }
        });*/
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TODO: 21-12-2023 NOTE : I added these two lines extra...
        return inflater.inflate(R.layout.fragment_studenthome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context con = requireActivity().getApplicationContext();


        if (view != null) {
            txt_hiUser = (TextView) view.findViewById(R.id.txt_hiUser);
            txt_studentName = (TextView) view.findViewById(R.id.txt_studentName);
            txt_email = (TextView) view.findViewById(R.id.txt_email);
            txt_phoneNo = (TextView) view.findViewById(R.id.txt_phoneNo);
            txt_programCode = (TextView) view.findViewById(R.id.txt_programCode);
            txt_houseName = (TextView) view.findViewById(R.id.txt_houseName);
            txt_game1 = (TextView) view.findViewById(R.id.txt_game1);
            txt_game2 = (TextView) view.findViewById(R.id.txt_game2);
            txt_game3 = (TextView) view.findViewById(R.id.txt_game3);
            img_game1 = (ImageView) view.findViewById(R.id.img_game1);
            img_game2 = (ImageView) view.findViewById(R.id.img_game2);
            img_game3 = (ImageView) view.findViewById(R.id.img_game3);
            card_Game1 = (CardView) view.findViewById(R.id.card_game1);
            card_Game2 = (CardView) view.findViewById(R.id.card_game2);
            card_Game3 = (CardView) view.findViewById(R.id.card_game3);
            cardRegistered = (CardView) view.findViewById(R.id.card_registeredSuccessfully);
            btn_registeredSuccess = (Button) view.findViewById(R.id.btn_registeredSuccess);

            // getting shared preferences values (from RegistrationDataPreference)...
            // TODO: 22-12-2023 Note :- Value is not fetching resolve it...
            getAllPreferencesValues(con);

            // setting values  on the fragment studentHome page
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

            // checking whether to show RegisteredSuccessCardVisibility or not...
            if (RegisteredSuccessCardVisibility) {
                cardRegistered.setVisibility(View.GONE);
            } else {
                cardRegistered.setVisibility(View.VISIBLE);
            }
        } else {
            Log.d("View is NULL", "Cannot find any view in \"StudentHome.java\"");
        }

        btn_registeredSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCardVisibility(v);
            }
        });

    }

    private static void getAllPreferencesValues(Context con) {
        SharedPreferences sharedPreferences = con.getSharedPreferences("RegistrationDataPreference", MODE_PRIVATE);

        Enrollment = sharedPreferences.getString("Enrollment", "Your Enrollment");
        Student_Name = sharedPreferences.getString("Student_Name", "Your Name");
        Email = sharedPreferences.getString("Email", "Your Email");
        Semester = sharedPreferences.getString("Semester", "Semester");
        Section = sharedPreferences.getString("Section", "Your Section");
        Phone_No = sharedPreferences.getString("Phone_No", "Your Phone");
        Gender = sharedPreferences.getString("Gender", "Your Gender");
        Program = sharedPreferences.getString("Program", "Your Program");
        Program_Code = sharedPreferences.getString("Program_Code", "Program Code");
        Game1 = sharedPreferences.getString("Game1", "Game 1");
        Game2 = sharedPreferences.getString("Game2", "Game 2");
        Game3 = sharedPreferences.getString("Game3", "Game 3");
        HouseName = sharedPreferences.getString("HouseName", "Your House");
        HouseIncharge = sharedPreferences.getString("HouseIncharge", "Your HouseIncharge");
        RegisteredSuccessCardVisibility = sharedPreferences.getBoolean("RegisteredSuccessCardVisibility", true);
    }
    public void removeCardVisibility(View view) {
        CardView cardRegistered = view.findViewById(R.id.card_registeredSuccessfully);
        cardRegistered.setVisibility(View.GONE);
        SharedPreferences sharedPreferences = requireActivity().getApplicationContext().getSharedPreferences("RegistrationDataPreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("RegisteredSuccessCardVisibility", false);
        editor.apply();
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