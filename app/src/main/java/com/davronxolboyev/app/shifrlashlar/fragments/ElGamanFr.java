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

public class ElGamanFr extends Fragment {

    static char[] origin = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public EditText g;
    EditText p;
    EditText a;
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
        View view = inflater.inflate(R.layout.fragment_el_gaman, container, false);

        g = view.findViewById(R.id.tubG);
        p = view.findViewById(R.id.tubP);
        a = view.findViewById(R.id.sonA);
        text = view.findViewById(R.id.textElGaman);
        convert = view.findViewById(R.id.convertElGaman);
        result = view.findViewById(R.id.resultElGaman);

        convert.setOnClickListener(v -> {
            g.onEditorAction(EditorInfo.IME_ACTION_DONE);
            p.onEditorAction(EditorInfo.IME_ACTION_DONE);
            a.onEditorAction(EditorInfo.IME_ACTION_DONE);
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            if (g.getText().toString().isEmpty()
                    || p.getText().toString().isEmpty()
                        || a.getText().toString().isEmpty()
                            || text.getText().toString().isEmpty()){
                Toast.makeText(getContext(),"Qiymatlar kiritilmagan!",Toast.LENGTH_SHORT).show();
            }else {
                int G = Integer.parseInt(g.getText().toString());
                int P = Integer.parseInt(p.getText().toString());
                int A = Integer.parseInt(a.getText().toString());
                String matn = text.getText().toString();
                if (chekTub(G) && chekTub(P)){
                    result.setText("");
                    ElGaman(G,P,A,matn.toUpperCase());
                }else {
                    Toast.makeText(getContext(),"g va p TUB son bo`lishi kerak!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void ElGaman(int g, int p, int a, String matn) {

        if (MainActivity.ID == 1){
            result.setText(shif(g,p,a,matn));
        }else {
            String[] dmatn = matn.split(",");
            result.setText(deshifr(g,p,a,dmatn));
        }

    }

    public static int EKUB(int k, int p) {
        p -= 1;

        while (k != p) {

            if (k > p)
                k -= p;
            else
                p -= k;

        }

        return k;
    }

    public static String shif(int g, int p, int a, String text2) {
        long y;
        int k = 2;
        int[] C = new int[text2.length()];
        while (EKUB(k, p) != 1) {
            k++;
        }
        int r = (int) (Math.pow(g, k)) % p;

        y = (long) (Math.pow(g, a)) % p;

        for (int i = 0; i < text2.length(); i++) {
            for (int j = 0; j < origin.length; j++) {
                if (text2.charAt(i) == origin[j]) {
                    C[i] = (int) (j * (Math.pow(y, k)) % p);
                    break;
                }
            }
        }
        return Arrays.toString(C) + "\t r = " + r;
    }

    private static boolean chekTub(int n) {
        if (n <= 2)
            return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    private static String deshifr(int r, int p, int a, String[] desh) {

        StringBuilder builder = new StringBuilder();
        for (String s : desh) {
            int c = Integer.parseInt(s);
            int x = 1;
            for (int i = 0; i < (p - 1 - a); i++) {
                x = (x * r) % p;
            }
            x = (x * c) % p;

            builder.append(origin[x]);
        }
        return builder.toString();
    }

}