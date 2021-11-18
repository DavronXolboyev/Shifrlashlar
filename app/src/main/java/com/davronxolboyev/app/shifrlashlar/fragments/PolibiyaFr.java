package com.davronxolboyev.app.shifrlashlar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.davronxolboyev.app.shifrlashlar.MainActivity;
import com.davronxolboyev.app.shifrlashlar.R;
import com.google.android.material.button.MaterialButton;

public class PolibiyaFr extends Fragment {

    static int k;
    EditText key;
    EditText text;
    MaterialButton convert;
    RadioButton usul1;
    RadioButton usul2;
    RadioButton usul3;
    TextView result;

    static char[][] c = new char[5][5];
    static char[] origin = {'A', 'B', 'C', 'D', 'E','F', 'G', 'H', 'I', 'J','K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y','Z'};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_polibiya, container, false);
        key = view.findViewById(R.id.keyP);
        text = view.findViewById(R.id.textPolibiya);
        convert = view.findViewById(R.id.convertPolibiya);
        result = view.findViewById(R.id.resultPolibiya);
        usul1 = view.findViewById(R.id.usul1);
        usul2 = view.findViewById(R.id.usul2);
        usul3 = view.findViewById(R.id.usul3);

        // Usulni aniqlash
        findVal();
        convert.setOnClickListener(v -> {
            result.setText("");
            text.onEditorAction(EditorInfo.IME_ACTION_DONE);
            key.onEditorAction(EditorInfo.IME_ACTION_DONE);
            findVal();
            if (key.getText().toString().isEmpty() || text.getText().toString().isEmpty()){
                Toast.makeText(getContext(),"Qiymatlar kiritilmagan!",Toast.LENGTH_SHORT).show();
            }else {
                Polibiya();
            }
        });

        return view;
    }

    private void findVal(){
        if (usul1.isChecked()){
            k = 1;
            usul2.setChecked(false);
            usul3.setChecked(false);
        }
        if (usul2.isChecked()){
            k = 2;
            usul1.setChecked(false);
            usul3.setChecked(false);
        }
        if (usul3.isChecked()){
            k = 3;
            usul2.setChecked(false);
            usul1.setChecked(false);
        }
    }

    private void Polibiya() {
        // kalitga sozlash
        changeArray(key.getText().toString().toUpperCase());

        String matn = text.getText().toString().toUpperCase();

        switch (k){
            case 1 : {
                char[] array;
                if (MainActivity.ID == 1){
                    array = matn.toCharArray();
                    for (char item:array){
                        result.append(""+shif1(item));
                    }
                }else {
                    array = matn.toCharArray();
                    for (char item:array){
                        result.append(""+def1(item));
                    }
                }

                break;
            }
            case 2 : {
                if (MainActivity.ID == 1){
                    result.setText(indexToString(getIndexs(matn)));
                }else {
                    result.setText(indexToString1(getIndex1(matn)));
                }
                break;
            }
            case 3 : {
                if (MainActivity.ID == 1){
                    result.setText(indexToString(changeIndex(getIndexs(matn))));
                }else {
                    result.setText(indexToString1(changeIndex1(getIndex1(matn))));
                }
                break;
            }
        }
    }


    //1-usul
    public static char shif1(char x) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c[i][j] == x)
                    if (i != 4)
                        return c[i + 1][j];
                    else
                        return c[0][i];
            }
        }

        return x;
    }

    public static char def1(char x) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c[i][j] == x)
                    if (i != 0)
                        return c[i - 1][j];
                    else
                        return c[4][i];
            }
        }

        return x;
    }


    //  2-usul
    public static int[] getIndexs(String s) {
        char[] text = s.toCharArray();
        int[] a = new int[s.length() * 2];
        boolean chek;

        for (int k = 0; k < text.length; k++) {
            chek = false;
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 5; i++) {
                    if (text[k] == c[i][j]) {
                        chek = true;
                        a[k] = j;
                        a[k + text.length] = i;
                        break;
                    }
                }
                if (chek)
                    break;
            }
        }
        return a;
    }

    public static String indexToString(int[] a) {
        StringBuilder text = new StringBuilder();
        for (int k = 0; k < a.length; k += 2) {
            text.append(c[a[k + 1]][a[k]]);

        }
        return text.toString();

    }

    public static int[] getIndex1(String s) {
        char[] text = s.toCharArray();
        int[] a = new int[s.length() * 2];
        boolean chek;

        for (int k = 0; k < text.length; k++) {
            chek = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (text[k] == c[i][j]) {
                        chek = true;
                        a[k * 2] = j;
                        a[2 * k + 1] = i;
                        break;
                    }
                }
                if (chek)
                    break;
            }
        }
        return a;
    }

    public static String indexToString1(int[] a) {
        StringBuilder text = new StringBuilder();
        int x = a.length / 2;
        for (int i = 0; i < x; i++) {
            text.append(c[a[i + x]][a[i]]);
        }

        return text.toString();
    }


    // 3-usul
    public static int[] changeIndex(int[] a) {
        int[] c = new int[a.length];
        if (c.length - 1 >= 0) System.arraycopy(a, 1, c, 0, c.length - 1);
        c[c.length - 1] = a[0];
        return c;
    }

    public static int[] changeIndex1(int[] a) {
        int[] c = new int[a.length];
        c[0] = a[a.length - 1];
        System.arraycopy(a, 0, c, 1, c.length - 1);

        return c;
    }

    public static void changeArray(String kalit) {
        char key=kalit.charAt(0);
        int x=0;
        for (int i=0;i<5;i++)
        {
            for (int j = 0; j <5 ; j++) {
                c[i][j]=origin[x++];
                if (origin[x-1]==key)
                    x++;
            }
        }
    }


}