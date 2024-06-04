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
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterFragment extends Fragment implements View.OnClickListener {

    // declare the variables
    Button readUserData, save_button;
    EditText userNameInput, userLoginInput, userPasswordInput;
    TextView fileContent;

    private final String filename = "SaveUserData.txt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        readUserData = view.findViewById(R.id.read_button);
        save_button = view.findViewById(R.id.register_button);
        userNameInput = view.findViewById(R.id.userName);
        userLoginInput = view.findViewById(R.id.userLogin);
        userPasswordInput = view.findViewById(R.id.userPassword);
        fileContent = view.findViewById(R.id.content);

        readUserData.setOnClickListener(this);
        save_button.setOnClickListener(this);

        return view;
    }

    public void printMessage(String m) {
        Toast.makeText(getActivity(), m, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        Button b = (Button) view;

        // get the button text: in our case either read or write depending on the button pressed.
        String b_text = b.getText().toString();

        switch (b_text.toLowerCase()) {
            case "write": {
                writeData();
                break;
            }
            case "read": {
                readData();
                break;
            }
        }
    }

    private void writeData() {
        try {
            FileOutputStream fos = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            String nameUserData = userNameInput.getText().toString();
            String loginUserData = userLoginInput.getText().toString();
            String passwordUserData = userPasswordInput.getText().toString();

            // Adding a delimiter between name, login, and password data
            String data = nameUserData + "\n" + loginUserData + "\n" + passwordUserData;

            fos.write(data.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userNameInput.setText("");
        userLoginInput.setText("");
        userPasswordInput.setText("");
        printMessage("Writing to file " + filename + " completed...");
    }

    private void readData() {
        try {
            FileInputStream fin = getActivity().openFileInput(filename);
            int a;
            StringBuilder temp = new StringBuilder();
            while ((a = fin.read()) != -1) {
                temp.append((char) a);
            }

            fin.close();

            // setting text from the file
            if (temp.length() > 0) {
                String[] userData = temp.toString().split("\n");
                if (userData.length >= 3) {
                    fileContent.setText("Name: " + userData[0] + "\nLogin: " + userData[1] + "\nPassword: " + userData[2]);
                } else {
                    fileContent.setText("Incomplete data in file.");
                }
            } else {
                fileContent.setText("No data found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        printMessage("Reading from file " + filename + " completed...");
    }
}
