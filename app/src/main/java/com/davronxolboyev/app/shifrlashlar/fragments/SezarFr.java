package com.davronxolboyev.app.shifrlashlar.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.davronxolboyev.app.shifrlashlar.MainActivity;
import com.davronxolboyev.app.shifrlashlar.R;
import com.google.android.material.button.MaterialButton;

public class SezarFr extends Fragment {

    EditText text;
    EditText key;
    TextView result;
    MaterialButton convert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sezar, container, false);
        text = view.findViewById(R.id.textSezar);
        key = view.findViewById(R.id.keySezar);
        result = view.findViewById(R.id.resultSezar);
        convert = view.findViewById(R.id.convertSezar);
        convert.setOnClickListener(v -> {
            result.setText("");
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            key.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Qiymatlarni kiriting!", Toast.LENGTH_SHORT).show();
            } else {
                int a = Integer.parseInt(key.getText().toString());
                if (a < 26) {
                    Sezar();
                } else {
                    Toast.makeText(getContext(), "Kalit 26 dan katta!", Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    private void Sezar() {
        //aniqlash
        // Qiymatlarni olish
        String matn = text.getText().toString();
        int kalit = Integer.parseInt(key.getText().toString());
        if (MainActivity.ID == 1) {
            result.setText(shifrSezer(kalit, matn));
        } else {
            result.setText(deshifrSezer(kalit, matn));
        }
    }

    public String shifrSezer(int key, String text) {
        char[] c = text.toCharArray();
        StringBuilder newText = new StringBuilder();

        for (char value : c) {
            int index = (int) value + key;
            if (((int) value <= 90 && index > 90) || ((int) value >= 97 && index > 122)) {
                index -= 26;
            }
            newText.append((char) index);
        }
        return newText.toString();
    }

    public String deshifrSezer(int key, String text) {
        char[] c = text.toCharArray();
        StringBuilder newText = new StringBuilder();

        for (char value : c) {
            int index = (int) value - key;
            if (((int) value <= 90 && index < 65) || ((int) value >= 97 && index < 97)) {
                index += 26;
            }
            newText.append((char) index);
        }
        return newText.toString();
    }
}