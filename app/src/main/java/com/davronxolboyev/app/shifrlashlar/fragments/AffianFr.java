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

public class AffianFr extends Fragment {

    static char[] origin = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static char[] origin2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    EditText sonA;
    EditText sonB;
    EditText text;
    MaterialButton convert;
    TextView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_affian, container, false);

        sonA = view.findViewById(R.id.toqSonAf);
        sonB = view.findViewById(R.id.sonB_Af);
        text = view.findViewById(R.id.textAffian);
        convert = view.findViewById(R.id.convertAffian);
        result = view.findViewById(R.id.resultAf);

        convert.setOnClickListener(v -> {
            result.setText("");
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            sonA.onEditorAction(EditorInfo.IME_ACTION_DONE);
            sonB.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (sonB.getText().toString().isEmpty()
                        || sonA.getText().toString().isEmpty()
                            || text.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Qiymatlar kiritilmagan!", Toast.LENGTH_SHORT).show();
            }
            else {
                int a = Integer.parseInt(sonA.getText().toString());
                if (a % 2 == 0) {
                    Toast.makeText(getContext(), "A son toq son emas", Toast.LENGTH_SHORT).show();
                }else {
                    Affian();
                }
            }
        });
        return view;
    }

    public void Affian() {
        int a = Integer.parseInt(sonA.getText().toString());
        int b = Integer.parseInt(sonB.getText().toString());
        String matn = text.getText().toString();
        if (MainActivity.ID == 1) {
            result.setText(shif(matn, a, b));
        } else {
            result.setText(def(matn, a, b));
        }
    }

    public static String shif(String text, int a, int b) {
        char[] c = text.toCharArray();
        int index;
        StringBuilder newText = new StringBuilder();
        for (char value : c) {
            if ((int) value <= 90 && (int) value >= 65) {
                for (int j = 0; j < origin.length; j++) {
                    if (origin[j] == value) {
                        index = a * j + b;
                        if (index < 0)
                            newText.append((char) ((index % 26 + 26) + 65));
                        else
                            newText.append((char) (index % 26 + 65));
                        break;
                    }
                }
            } else if ((int) value <= 122 && (int) value >= 97) {
                for (int j = 0; j < origin2.length; j++) {
                    if (origin2[j] == value) {
                        index = a * j + b;
                        if (index < 0)
                            newText.append(origin2[index % 26 + 26]);
                        else
                            newText.append((char) (index % 26 + 97));
                        break;
                    }
                }
            }
        }

        return newText.toString();
    }

    public static String def(String text, int a, int b) {
        StringBuilder newText = new StringBuilder();
        char[] c = text.toCharArray();
        int A = 1, index;
        while ((A * a) % 26 != 1) {
            A++;
        }

        for (char value : c) {
            if ((int) value <= 90 && (int) value >= 65) {
                for (int j = 0; j < origin.length; j++) {
                    if (origin[j] == value) {
                        index = A * (j - b);
                        if (index >= 0)
                            newText.append(origin[index % 26]);
                        else
                            newText.append(origin[index % 26 + 26]);
                    }
                }

            } else if ((int) value <= 122 && (int) value >= 97) {
                for (int j = 0; j < origin2.length; j++) {
                    if (origin2[j] == value) {
                        index = A * (j - b);
                        if (index >= 0)
                            newText.append(origin2[index % 26]);
                        else
                            newText.append(origin2[index % 26 + 26]);
                    }
                }
            }
        }
        return newText.toString();
    }

}