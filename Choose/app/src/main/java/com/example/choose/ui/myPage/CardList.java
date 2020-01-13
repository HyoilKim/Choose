package com.example.choose.ui.myPage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.choose.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.InputStream;
import java.util.ArrayList;

public class CardList extends AppCompatActivity {
    public static final int SUCCES_REGISTER = 10;
    Button mButton;
    ListView listView;
    String cardNumber;
    String validPeriod;
    String parsingText = "";
    ArrayAdapter<String> adapter;
    FirebaseVisionImage cardImage;
    ArrayList<String> cardList;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardlist);
        mButton = findViewById(R.id.my_button);
        listView = findViewById(R.id.listView);

        cardList = new ArrayList<>();
        cardList.add("신한 체크 카드");
        cardList.add("국민 체크 카드");
        cardList.add("국민 신용 카드");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cardList);
        listView.setAdapter(adapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("PRINTㅡㅡㅡㅡㅡㅡ", resultCode+"");
        if (resultCode == RESULT_OK) {
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                cardImage = FirebaseVisionImage.fromBitmap(img);
                recognizeText(cardImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == SUCCES_REGISTER) {
//            String name = intent.getStringExtra("name").toString();
            String name = data.getStringExtra("cardName");
            cardList.add(name);
            adapter.notifyDataSetChanged();
            Log.d("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ",name+"");
        }
    }

    public void recognizeText(FirebaseVisionImage image) {
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();

        detector.processImage(image)
            .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    // Task completed successfully
                    // [START_EXCLUDE]
                    // [START get_text]
                    for (FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                        Rect boundingBox = block.getBoundingBox();
                        Point[] cornerPoints = block.getCornerPoints();
                        String text = block.getText();
                        if (text.contains("/")) validPeriod = text;
                        if (text.length() >= 19) cardNumber = text;
                        parsingText += text+"\n";
                    }
                    Log.d("@@", cardNumber);
                    Log.d("@@", validPeriod);

                    Intent intent = new Intent(getApplicationContext(), CardRegister.class);
                    intent.putExtra("cardNumber", cardNumber);
                    intent.putExtra("validPeriod", validPeriod);
                    startActivityForResult(intent, SUCCES_REGISTER);
                }
            })
            .addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("PRINT", "-----------------------------------Failure------------------");
                }
            });

    }
}