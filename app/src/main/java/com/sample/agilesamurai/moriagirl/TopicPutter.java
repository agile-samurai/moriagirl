package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import java.util.Random;
import java.util.List;

/**
 * Created by ito on 2016/08/26.
 */
public class TopicPutter {
    String text;
    Activity activity;
    Talking talking;
    TextRead grouptopic;
    TextRead personaltopic;
    int count = 0;

    String[] textList = {"僕の趣味は寝ることだよ．君たちの趣味を教えてよ", "昨日あった出来事の中で一番印象に残ってるものを教えてよ．"};
    int listSize = textList.length;

    TopicPutter(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        grouptopic = new TextRead(context_input, "GroupTopic.txt");
        personaltopic = new TextRead(context_input, "PersonalTopic.txt");
    }

    public String textContents(){
        return text;
    }


    /*
    //とりあえず１行目のお題表示
    public void textPut(){
        text = inputfile.simpleTextRead(1);
        talking.talk(text);
    }
    */

    //10行の中でランダムで１行表示
    public int randomTextPut(List<String> name){
        count++;
        Random rnd = new Random();
        int ran = rnd.nextInt(2);
        if(ran == 0) {
            text = grouptopic.randomTextRead();
        }else{
            text = personaltopic.randomTextRead();
            ran = rnd.nextInt(name.size());
            text = name.get(ran) + "さん" + text;
        }
        talking.talk(text);
        return count;
    }

    public int forDemoTextPut(){
        count++;
        text = textList[count];
        talking.talk(text);
        if(count == listSize){
            count = -1;
        }
        return count;
    }

}
