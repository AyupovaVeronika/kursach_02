package com.example.kursach_02;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.FileInputStream;
import java.io.IOException;

public class VhodFragment extends Fragment implements View.OnClickListener {

    // declare the variables
    Button loginButton;
    EditText userLoginInput, userPasswordInput;
    TextView loginStatus;

    private final String filename = "SaveUserData.txt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vhod, container, false);

        loginButton = view.findViewById(R.id.login_button);
        userLoginInput = view.findViewById(R.id.userLogin);
        userPasswordInput = view.findViewById(R.id.userPassword);
        loginStatus = view.findViewById(R.id.login_status);

        loginButton.setOnClickListener(this);

        return view;
    }

    public void printMessage(String m) {
        Toast.makeText(getActivity(), m, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        String loginUserData = userLoginInput.getText().toString();
        String passwordUserData = userPasswordInput.getText().toString();

        if (checkLoginData(loginUserData, passwordUserData)) {
            loginStatus.setText("Login successful. Welcome, " + loginUserData + "!");
            printMessage("Login successful");
        } else {
            loginStatus.setText("Login failed. Incorrect login or password.");
            printMessage("Login failed");
        }
    }

    private boolean checkLoginData(String login, String password) {
        try {
            FileInputStream fin = getActivity().openFileInput(filename);
            int a;
            StringBuilder temp = new StringBuilder();
            while ((a = fin.read()) != -1) {
                temp.append((char) a);
            }

            fin.close();

            if (temp.length() > 0) {
                String[] userData = temp.toString().split("\n");
                for (int i = 1; i < userData.length; i += 3) {
                    if (userData[i].equals(login) && userData[i + 1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
