package hardik.CCSITianSportal.HomePages.CommonFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hardik.CCSITianSportal.R;

public class RateAndFeedback extends Fragment {



    public RateAndFeedback() {
        // Required empty public constructor
    }

    public static RateAndFeedback newInstance(String param1, String param2) {
        RateAndFeedback fragment = new RateAndFeedback();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rate_and_feedback, container, false);
    }
}