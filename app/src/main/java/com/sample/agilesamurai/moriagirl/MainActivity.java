package com.sample.agilesamurai.moriagirl;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static List<String> name = new ArrayList();
    Button syokaiButton;
    MemberManager memberManager;
    Speeching speeching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        syokaiButton = (Button)findViewById(R.id.syokai);
        speeching = new Speeching(this);

        memberManager = new MemberManager(this);
        name = memberManager.getNames();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        speeching.shutDown();
    }

    public void displayInputMessage(View view){
        displayMessage(getString(R.string.inputName));
    }

    // TODO: いつタップされても入力できちゃうから直す
    public void inputName(View view){
        memberManager.inputName();
    }

    public void selfIntroduction(View view){
        SelfIntroduction selfIntroduction = new SelfIntroduction(name, this);
        selfIntroduction.introduction();
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.mikuText);
        priceTextView.setText(message);
        speech(message);
    }

    private void speech(String string){
        speeching.speechText(string);
    }
}
