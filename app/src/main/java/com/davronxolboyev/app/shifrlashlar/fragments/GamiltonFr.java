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

import java.util.Random;

public class GamiltonFr extends Fragment {

    EditText text;
    EditText key;
    MaterialButton convert;
    MaterialButton clear;
    MaterialButton getKey;
    TextView result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gamilton, container, false);

        text = view.findViewById(R.id.textGamilton);
        key = view.findViewById(R.id.keyGamilton);
        convert = view.findViewById(R.id.convertGamilton);
        clear = view.findViewById(R.id.clearGamilton);
        getKey = view.findViewById(R.id.getKeyG);
        result = view.findViewById(R.id.resultGamilton);

        getKey.setOnClickListener(v -> {
            if (text.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Matn kiritilmagan!", Toast.LENGTH_SHORT).show();
            } else {
                key.setEnabled(true);
                String hint;
                int c = text.getText().length() / 8;
                if (c * 8 != text.getText().length()) {
                    c++;
                }
                hint = "" + c + " ta usul vergul bilan yoziladi!\nYo`llar 1 va 2";
                Toast.makeText(getContext(), hint, Toast.LENGTH_LONG).show();
            }
        });

        convert.setOnClickListener(v -> {
            result.setText("");
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            key.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (text.getText().toString().isEmpty() || key.getText().toString().isEmpty()) {
                key.setEnabled(false);
                Toast.makeText(getContext(), "Qiymatlar kiritilmagan!", Toast.LENGTH_SHORT).show();
            } else {
                Gamiltonn();
                clear.setEnabled(true);
            }
        });

        clear.setOnClickListener(v -> {
            text.setText("");
            key.setText("");
            result.setText("");
            key.setEnabled(false);
        });


        return view;
    }

    private void Gamiltonn() {
        String matn = text.getText().toString();
        String kalit = key.getText().toString();
        if (MainActivity.ID == 1) {
            shifrGamilton(matn, kalit);
        } else {
            deshifrGamilton(matn, kalit);
        }
    }

    private void deshifrGamilton(String matn, String kalit) {
        int l = matn.length() / 8;
        String[] detext2;
        String[] dkeys = kalit.split(",");
        detext2 = new String[l];
        int dindex = 0;
        for (int i = 0; i < l; i++) {
            int dk = Integer.parseInt(dkeys[i]);
            detext2[i] = matn.substring(dindex, dindex + 8);
            dindex += 8;
            result.append(deshifr(detext2[i], dk));
        }
    }

    public static String deshifr(String text, int key) {
        String[] s = new String[8];
        if (key == 1) {
            s[2] = String.valueOf(text.charAt(0));
            s[3] = String.valueOf(text.charAt(1));
            s[1] = String.valueOf(text.charAt(2));
            s[0] = String.valueOf(text.charAt(3));
            s[4] = String.valueOf(text.charAt(4));
            s[5] = String.valueOf(text.charAt(5));
            s[7] = String.valueOf(text.charAt(6));
            s[6] = String.valueOf(text.charAt(7));
        } else {
            s[3] = String.valueOf(text.charAt(0));
            s[7] = String.valueOf(text.charAt(1));
            s[6] = String.valueOf(text.charAt(2));
            s[2] = String.valueOf(text.charAt(3));
            s[0] = String.valueOf(text.charAt(4));
            s[1] = String.valueOf(text.charAt(5));
            s[5] = String.valueOf(text.charAt(6));
            s[4] = String.valueOf(text.charAt(7));
        }

        StringBuilder orgText = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("-")) s[i] = " ";
            if (!(s[i].charAt(0) > 34 && s[i].charAt(0) < 48))
                orgText.append(s[i]);
        }

        return orgText.toString();
    }

    private void shifrGamilton(String matn, String kalit) {
        StringBuilder text = new StringBuilder(matn);
        int c = text.length() / 8;
        String[] text2;
        if (c * 8 == text.length()) {

            System.out.print(c + " ta yo`lni [1 yoki 2] vergul (,) orqali kiriting : ");
            String[] keys = kalit.split(",");
            text2 = new String[c];
            int index = 0;
            for (int i = 0; i < c; i++) {
                int k = Integer.parseInt(keys[i]);
                text2[i] = text.substring(index, index + 8);
                index += 8;
                System.out.print(shifr(text2[i], k));
            }
        } else {
            String[] ch = {"#", ")", "%", "$", "/", "+", "*", "("};
            int k = (c + 1) * 8 - text.length();
            for (int i = 0; i < k; i++) {
                Random random = new Random();
                text.append(ch[random.nextInt(8)]);
            }
            c++;
            //System.out.print(c + " ta yo`lni [1 yoki 2] vergul (,) orqali kiriting : ");
            String[] keys2 = kalit.split(",");
            text2 = new String[c];
            int index = 0;
            for (int i = 0; i < c; i++) {
                int k2 = Integer.parseInt(keys2[i]);
                text2[i] = text.substring(index, index + 8);
                index += 8;
                //System.out.println(text2[i]);
                //System.out.print(shifr(text2[i], k2));
                result.append(shifr(text2[i], k2));
            }
        }
    }

    public static String shifr(String text, int key) {
        String[] s = new String[8];
        for (int i = 0; i < text.length(); i++) {
            char a = text.charAt(i);
            if (a == ' ') s[i] = "-";
            else s[i] = String.valueOf(text.charAt(i));
        }
        text = "";
        if (key == 1) {
            text += s[2] + s[3] + s[1] + s[0] + s[4] + s[5] + s[7] + s[6];
        } else {
            text += s[3] + s[7] + s[6] + s[2] + s[0] + s[1] + s[5] + s[4];
        }
        return text;
    }
}