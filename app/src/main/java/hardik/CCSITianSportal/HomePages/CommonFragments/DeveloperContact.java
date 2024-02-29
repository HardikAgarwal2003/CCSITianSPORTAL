package hardik.CCSITianSportal.HomePages.CommonFragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hardik.CCSITianSportal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeveloperContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeveloperContact extends Fragment {

    public DeveloperContact() {

    }

    public static DeveloperContact newInstance(String param1, String param2) {
        DeveloperContact fragment = new DeveloperContact();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_developer_contact, container, false);
    }
}