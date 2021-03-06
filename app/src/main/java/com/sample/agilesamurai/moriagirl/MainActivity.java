package com.sample.agilesamurai.moriagirl;

import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.agilesamurai.moriagirl.views.SoundMeterActivity;

import java.util.ArrayList;
import java.util.List;

import jp.live2d.Live2D;
import jp.live2d.utils.android.FileManager;

public class MainActivity extends AppCompatActivity {
    static List<String> name = new ArrayList();
    Button syokaiButton;
    Button byebyeButton;
    MemberManager memberManager;
    Speaking speaking;
    Greeting greeting;
    SelfIntroduction selfIntroduction;
    Byebye byebye;
    final int num_of_topic = 3;
    //何人自己紹介したのかカウント
    int selfintroduction_count = 0;
    GLSurfaceView glView;
    SampleGLSurfaceView girlView;

    public enum State {
        DisplayInputMessage,
        InputName,
        SelfIntroduction,
        TopicPut,
        ByeBye;
    }


    static State state = State.DisplayInputMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syokaiButton = (Button) findViewById(R.id.syokai);
        byebyeButton = (Button) findViewById(R.id.byebye);
        speaking = new Speaking(this);
        memberManager = new MemberManager(this);
        name = memberManager.getNames();
        greeting = new Greeting(this);
        byebye = new Byebye(this);
        greeting.randomGreeting();

        FileManager.init(getApplicationContext());

        //for Live2d
        Live2D.init();
        //assets/haru/motionsの中から動きを選択
        //TODO 動きを変更するにはどうすれば良いのだろうか
        girlView = new SampleGLSurfaceView(this, "haru/motions/haru_m_05.mtn");
        glView = (GLSurfaceView) findViewById(R.id.surfaceView);
        glView.setRenderer(girlView.renderer);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        setState(State.DisplayInputMessage);
        speaking.shutDown();
    }

    public void displayInputMessage(){
        displayMessage(getString(R.string.inputName));
        setState(State.InputName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューの要素を追加して取得
        MenuItem actionItem = menu.add("Action.json Button Return title");

        // アイコンを設定
        actionItem.setIcon(android.R.drawable.ic_menu_revert);

        // SHOW_AS_ACTION_ALWAYS:常に表示
        actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("タイトル画面へ戻りますか？");
        alertDialogBuilder.setIcon(R.drawable.ribbonkuma);

        alertDialogBuilder.setPositiveButton("戻る", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                finish();
            }

        });

        alertDialogBuilder.setNegativeButton(R.string.cancel ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }


        });

        alertDialogBuilder.create().show();

        //finish();
        return true;
    }

    // TODO: いつタップされても入力できちゃうから直す
    public void inputName() {
        boolean apply;
        apply = memberManager.inputName();
        System.out.println(apply);
    }

    public void selfIntroduction(View view){
        setState(State.SelfIntroduction);
        selfIntroduction = new SelfIntroduction(name,selfintroduction_count,this);
        selfIntroduction.introduction();
        selfintroduction_count++;
        syokaiButton.setVisibility(View.INVISIBLE);
    }

    public void topicPut(){
    }

    public void byebye() {
        byebyeButton.setVisibility(View.VISIBLE);
        byebye.randomByeBye();
    }


    public void onClick(View view){
        switch(state) {
            case DisplayInputMessage:
                displayInputMessage();
                break;
            case InputName:
                inputName();
                break;
            case SelfIntroduction:
                selfIntroduction(view);
                break;
            case TopicPut:
                topicPut();
                break;
            case ByeBye:
                byebye();
                break;
        }
    }

    public void byebyeClick(View view){
        finish();
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.speakText);
        priceTextView.setText(message);
        speech(message);
    }

    private void speech(String string){
        speaking.speak(string);
    }

    static public void setState(State s){
        state = s;
    }
}

