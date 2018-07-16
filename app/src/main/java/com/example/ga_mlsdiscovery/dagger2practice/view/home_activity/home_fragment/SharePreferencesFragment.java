package com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ga_mlsdiscovery.dagger2practice.R;
import com.example.ga_mlsdiscovery.dagger2practice.application.Dagger2Application;
import com.example.ga_mlsdiscovery.dagger2practice.view.home_activity.home_fragment.di_shared_prefs_fragment.MyPrefs;

import javax.inject.Inject;

/*
1. create shared preferences from getSharedPreferences("file", context.MODE_PRIVATE)
with dagger, shared preferences are created in the module
2. view.setText(preferences.getString("username", "default value")
with dagger, a separate sharedpreferences class will call its method
3. create sharedPreferences method to add data to file
:   public void saveEditTextToSharedPrefs(View v){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", editText.getText().toString());
        editor.apply();
    }
with dagger, a putData method is called from your shared preferences class
4. Call method when needed to refer to file
// With Dagger a separate dependency can be made to create shared preferences and the module will
 */
public class SharePreferencesFragment extends Fragment implements View.OnClickListener{
    @Inject
    MyPrefs myPrefs;
    private TextView textView;
    private EditText editText;
    private Button button;
    private SharedPreferences preferences;

    public SharePreferencesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_share_preferences_fragment, container, false);
        //dagger
        Dagger2Application.getApp().getPrefsFragmentComponent().inject(this);

        //create views
        editText = v.findViewById(R.id.et_shared_prefs);
        textView = v.findViewById(R.id.tv_shared_prefs);
        button = v.findViewById(R.id.bt_shared_prefs);
        button.setOnClickListener(this);

        //check sharedPreferences
        /*
        Shared Prefs are already in every application no new instance is created
        To get sharedPreferences we must get context. Activity is an extension of context
         */
        textView.setText(myPrefs.getPreferences("username", "default value"));
        return v;
    }

    public void saveEditTextToSharedPrefs(){
        String value = editText.getText().toString();
        myPrefs.putPreferences("username", value);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.bt_shared_prefs :
                saveEditTextToSharedPrefs();
                break;
        }
    }
}
