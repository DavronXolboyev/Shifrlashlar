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

import java.util.Arrays;

public class RsaFr extends Fragment {


    static char[] origin = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static char[] origin2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    EditText P;
    EditText Q;
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
        View view = inflater.inflate(R.layout.fragment_rsa, container, false);
        P = view.findViewById(R.id.tubP);
        Q = view.findViewById(R.id.tubQ);
        text = view.findViewById(R.id.textRSA);
        convert = view.findViewById(R.id.convertRSA);
        result = view.findViewById(R.id.resultRSA);

        convert.setOnClickListener(v -> {
            result.setText("");
            P.onEditorAction(EditorInfo.IME_ACTION_DONE);
            Q.onEditorAction(EditorInfo.IME_ACTION_DONE);
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (P.getText().toString().isEmpty()
                    || Q.getText().toString().isEmpty()
                        || text.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Qiymatlar kiritilmagan!", Toast.LENGTH_SHORT).show();
            }else {

                int p = Integer.parseInt(P.getText().toString());
                int q = Integer.parseInt(Q.getText().toString());
                if (MainActivity.ID == 1) {
                    if (chekTub(p) && chekTub(q)) {
                        if (p+q>7){
                            RSA();
                        }else {
                            Toast.makeText(getContext(), "p yoki q 5 dan katta bo`lsin", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getContext(), "Tub son kiriting!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    RSA();
                }
            }
        });
        return view;
    }

    public void RSA() {
        String matn = text.getText().toString();
        int a = Integer.parseInt(P.getText().toString());
        int b = Integer.parseInt(Q.getText().toString());
        if (MainActivity.ID == 1) {
            result.setText(Arrays.toString(shif(matn, a, b)));
        } else {
            result.setText(def(matn, a, b));
        }

    }

    public static boolean chekTub(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int EKUB(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        }
        while (a != b) {
            if (a > b)
                a -= b;
            else
                b -= a;
        }

        return a;
    }

    public static int findId(char c) {
        if ((int) c >= 65 && (int) c <= 90) {
            for (int i = 0; i < origin.length; i++) {
                if (origin[i] == c)
                    return i;
            }
        } else if ((int) c >= 97 && (int) c <= 122) {
            for (int i = 0; i < origin.length; i++) {
                if (origin2[i] == c)
                    return i;
            }
        }

        return -1;
    }

    public static int[] shif(String text, int p, int q) {
        int n = p * q, f = (p - 1) * (q - 1), e = 2, d = 1;
        int[] shif = new int[text.length() + 1];
        char[] c = text.toCharArray();

        while (EKUB(f, e) != 1) {
            e++;
        }

        while ((e * d) % f != 1) {
            d++;
        }

        for (int i = 0; i < c.length; i++) {
            int index = findId(c[i]);
            shif[i] = (int) (Math.pow(index + 1, e) % n);
        }
        shif[shif.length - 1] = d;

        return shif;

    }

    public static String def(String text, int d, int n) {
        String[] def = text.split(",");
        StringBuilder newText = new StringBuilder();

        for (String s : def) {
            int index = Integer.parseInt(s);
            index = (int) ((Math.pow(index, d)) % n);
            newText.append(origin2[index - 1]);
        }

        return newText.toString();

    }

}