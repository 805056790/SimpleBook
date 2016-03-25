package graduation.hnust.simplebook.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import graduation.hnust.simplebook.R;

/**
 * The main view of app
 *
 * @Author : panxin
 * @Date : 10:39 PM 3/24/16
 * @Email : panxin109@gmail.com
 */
public class FragmentMain extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
