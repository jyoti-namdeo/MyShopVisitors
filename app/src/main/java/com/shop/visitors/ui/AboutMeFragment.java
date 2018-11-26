package com.shop.visitors.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shop.visitors.R;
import com.shop.visitors.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class AboutMeFragment extends DialogFragment {
    private TextView aboutMeEditText;

    public AboutMeFragment() {
        // Empty constructor is required for DialogFragment
    }

    public static AboutMeFragment newInstance(String title) {
        AboutMeFragment frag = new AboutMeFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_me_fragment, container);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aboutMeEditText = view.findViewById(R.id.about_me_editText);
        aboutMeEditText.setText(FileUtil.readAsset(getActivity(), "about_me.txt"));
    }
}
