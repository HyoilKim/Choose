package com.example.choose.ui.myPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.choose.R;

public class CardRegister extends AppCompatActivity {
    public static final int SUCCES_REGISTER = 10;
    EditText cardNumber1, cardNumber2, cardNumber3, cardNumber4;
    EditText validMM, validYY;
    EditText cardName, cvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardregister);
        initView();

        Intent intent = getIntent();
        String cardNumber = intent.getStringExtra("cardNumber");
        String validPeriod = intent.getStringExtra("validPeriod");

        Log.d("PRINT RESULT", "++++++++++++" + cardNumber);
        Log.d("PRINT RESULT", "++++++++++++" + validPeriod);

        if (cardNumber.equals("")) {
            cardNumber1.setText("");
            cardNumber2.setText("");
            cardNumber3.setText("");
            cardNumber4.setText("");
        } else {
            String tmp1[] = cardNumber.split(" ");
            cardNumber1.setText(tmp1[0]);
            cardNumber2.setText(tmp1[1]);
            cardNumber3.setText(tmp1[2]);
            cardNumber4.setText(tmp1[3]);
        }
//        String tmp1[] = cardNumber.split(" ");
//        cardNumber1.setText(tmp1[0]);
//        cardNumber2.setText(tmp1[1]);
//        cardNumber3.setText(tmp1[2]);
//        cardNumber4.setText(tmp1[3]);

        if (validPeriod.equals("")) {
            validMM.setText("");
            validYY.setText("");
        } else {
            String[] tmp2 = validPeriod.split("/");
            validMM.setText(tmp2[0]);
            validYY.setText(tmp2[1]);
        }

        cvc.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("~~","~~");
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    String name = cardName.getText().toString();
                    Log.d("register name", name);
                    Intent intent = new Intent();
//                    intent1.putExtra("cardName", name);
                    intent.putExtra("cardName", name);
                    setResult(SUCCES_REGISTER, intent);
                    finish();
                    return false;
                }
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.d("%%","%%");
                    return true;
                }
                return false;
            }
         });
    }

    public void initView() {
        cardName = findViewById(R.id.cardName);
        cardName.requestFocus();
        cardNumber1 = findViewById(R.id.cardNum1);
        cardNumber2 = findViewById(R.id.cardNum2);
        cardNumber3 = findViewById(R.id.cardNum3);
        cardNumber4 = findViewById(R.id.cardNum4);
        validMM = findViewById(R.id.validMM);
        validYY = findViewById(R.id.validYY);
        cvc = findViewById(R.id.cvc);
    }

}

