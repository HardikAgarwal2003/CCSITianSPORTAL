package hardik.CCSITianSportal.HomePages.FacultyHomepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

import hardik.CCSITianSportal.HomePages.CommonFragments.DeveloperContact;
import hardik.CCSITianSportal.HomePages.CommonFragments.RateAndFeedback;
import hardik.CCSITianSportal.HomePages.FacultyHomepage.FacultyFragments.FacultyHome;
import hardik.CCSITianSportal.HomePages.CommonFragments.Settings;
import hardik.CCSITianSportal.R;

public class FacultyHomepage extends AppCompatActivity {
    SNavigationDrawer sNavigationDrawer;
    Class fragmentClass;
    public static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_homepage);

//********************************* Code for Navigation Drawer Starts ******************************************//
        sNavigationDrawer = findViewById(R.id.navigationDrawerFaculty);

        //Creating a list of menu Items

        List<MenuItem> menuItems = new ArrayList<>();

        //Use the MenuItem given by this library and not the default one.
        //First parameter is the title of the menu item and then the second parameter is the image which will be the background of the menu item.

        // the sequence of these declarations are reflected in the application
        menuItems.add(new MenuItem("FacultyHome", R.drawable.icon_home));
        menuItems.add(new MenuItem("Settings", R.drawable.icon_settings));
        menuItems.add(new MenuItem("Developer Contact", R.drawable.icon_developer_contact));
        menuItems.add(new MenuItem("Rate and Settings", R.drawable.icon_rate_and_feedback));

        //then add them to navigation drawer
        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass = FacultyHome.class;     // to set the default page (fragment)
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayoutFaculty, fragment).commit();
        }

        //Listener to handle the menu item click. It returns the position of the menu item clicked. Based on that you can switch between the fragments.
        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position " + position);

                switch (position) {
                    case 0: {
                        fragmentClass = FacultyHome.class;
                        break;
                    }
                    case 1: {
                        fragmentClass = Settings.class;
                        break;
                    }
                    case 2: {
                        fragmentClass = DeveloperContact.class;
                        break;
                    }
                    case 3: {
                        fragmentClass = RateAndFeedback.class;
                        break;
                    }
                }

                //Listener for drawer events such as opening and closing.
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening() {

                    }

                    @Override
                    public void onDrawerClosing() {
                        System.out.println("Drawer closed");
                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayoutFaculty, fragment).commit();
                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State " + newState);
                    }
                });
            }
        });
//******************************* Code for Navigation Drawer Finished *************************************//
    }
}