package com.sample.agilesamurai.moriagirl;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by kusamura on 2016/12/04.
 */
public class ActionController {
    String text;
    String speak;
    Activity activity;
    Talking talking;
    ActionReader actionReader;
    int count = 0;
    List<Action> pTopics = new ArrayList<>();
    List<Action> gTopics = new ArrayList<>();
    List<Action> reactions = new ArrayList<>();
    List<List<Integer>> reactionLevel = new ArrayList<>();

    ActionController(Activity context_input) {
        text = "";
        activity = context_input;
        talking = new Talking(activity);
        actionReader = new ActionReader(activity);
        getActions();
    }

    public int moveAction(List<String> name, Action.ActionType actionType,int feverLevel){
        if(actionType == )
        count++;
        text = action.text;
        speak = action.speak;
        Random rnd = new Random();
        int ran = rnd.nextInt(name.size());
        if(actionType == Action.ActionType.Personal) {
            text = text.replaceFirst("NAME", name.get(ran));
            speak = speak.replaceFirst("NAME", name.get(ran));
        }
        talking.talk(text, speak);
        return count;
    }

    public void moveAction(int feverLevel,int num) {
        if(num > reactionLevel.get(0).size())
    }

    public void getActions() {
        for(int i=0;i<actionReader.getPersonalTopicLength();i++) {
            pTopics.add(actionReader.readAction(Action.ActionType.Personal),i);
        }
        for(int i=0;i<actionReader.getGroupTopicLength();i++) {
            gTopics.add(actionReader.readAction(Action.ActionType.Group),i);
        }
        for(int i=0;i<actionReader.getReactionTopicLength();i++) {
            reactions.add(actionReader.readAction(Action.ActionType.Reaction),i);
            int minFever = reactions.get(i).minFever;
            int maxFever = reactions.get(i).maxFever;

            if(minFever <= 0) {
                reactionLevel.get(0).add(i);
            }
            if(minFever <= 1 && maxFever >=1) {
                reactionLevel.get(1).add(i);
            }
            if(minFever <= 2 && maxFever >=2) {
                reactionLevel.get(2).add(i);
            }
            if(minFever <= 3 && maxFever >=3){
                reactionLevel.get(3).add(i);
            }
            if(maxFever >=4){
                reactionLevel.get(4).add(i);
            }

            }
        }
    }
}
