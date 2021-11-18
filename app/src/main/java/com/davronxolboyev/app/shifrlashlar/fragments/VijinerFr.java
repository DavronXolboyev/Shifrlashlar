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

public class VijinerFr extends Fragment {

    EditText text;
    EditText key;
    MaterialButton convert;
    TextView result;

    static char[] origin = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static char[] origin2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vijiner, container, false);
        text = view.findViewById(R.id.textVijiner);
        key = view.findViewById(R.id.keyVijiner);
        convert = view.findViewById(R.id.convertVijiner);
        result = view.findViewById(R.id.resultVijiner);

        convert.setOnClickListener(v -> {
            result.setText("");
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            key.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Qiymatlarni kiriting!", Toast.LENGTH_SHORT).show();
            } else {
                int sizeT = text.getText().length();
                int sizeK = key.getText().length();
                if (sizeK > sizeT) {
                    Toast.makeText(getContext(), "Kalit matndan uzun bo'lmasin!", Toast.LENGTH_SHORT).show();
                } else {
                    Vijiner();
                }
            }

        });
        return view;
    }

    private void Vijiner() {
        String matn = text.getText().toString();
        String kalit = key.getText().toString();
        if (MainActivity.ID == 1) {
            result.setText(shif(matn, kalit));
        } else {
            result.setText(def(matn, kalit));
        }

    }

    public static String shif(String text, String key) {
        if (text.length() < key.length()) {
            return "Kalit matndan uzun bo'lmasin";
        }
        StringBuilder newText = new StringBuilder();
        char[] keys = key.toCharArray();
        char[] c = text.toCharArray();
        int[] a = new int[keys.length];
        int index;
        for (int i = 0; i < a.length; i++) {
            if ((int) keys[i] >= 97 && (int) keys[i] <= 122) {
                keys[i] = Character.toLowerCase(keys[i]);
                a[i] = 97 - (int) keys[i];
            } else {
                keys[i] = Character.toUpperCase(keys[i]);
                a[i] = 65 - (int) keys[i];
            }
        }

        for (int i = 0; i < c.length; i++) {
            index = i % keys.length;
            if ((int) c[i] >= 97 && (int) c[i] <= 122)
                newText.append(origin2[((int) c[i] - a[index] - 97) % 26]);
            else
                newText.append(origin[((int) c[i] - a[index] - 65) % 26]);
        }

        return newText.toString();
    }

    public static String def(String text, String key) {
        if (text.length() < key.length()) {
            return "Kalit matndan uzun bo'lmasin";
        }
        StringBuilder newText = new StringBuilder();
        char[] keys = key.toCharArray();
        char[] c = text.toCharArray();
        int[] a = new int[keys.length];
        int index, count;
        for (int i = 0; i < a.length; i++) {
            if ((int) keys[i] >= 97 && (int) keys[i] <= 122) {
                keys[i] = Character.toLowerCase(keys[i]);
                a[i] = 97 - (int) keys[i];
            } else {
                keys[i] = Character.toUpperCase(keys[i]);
                a[i] = 65 - (int) keys[i];
            }
        }

        for (int i = 0; i < c.length; i++) {
            index = i % keys.length;
            if ((int) c[i] >= 97 && (int) c[i] <= 122) {
                count = (int) c[i] - 97;
                if (count > Math.abs(a[index]))
                    newText.append(origin2[(count + a[index]) % 26]);
                else
                    newText.append(origin2[(26 + count + a[index]) % 26]);

            } else {
                count = (int) c[i] - 65;
                if (count > Math.abs(a[index]))
                    newText.append(origin[(count + a[index]) % 26]);
                else
                    newText.append(origin[(26 + count + a[index]) % 26]);
            }
        }

        return newText.toString();

    }
}