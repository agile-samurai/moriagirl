package com.sample.agilesamurai.moriagirl;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by motoki on 2016/08/29.
 */
public class MemberManager{
    private List names = new ArrayList();
    private Activity activity;

    public MemberManager(Activity activity){
        this.activity = activity;
    }

    /**
     * 名前を入力するウィンドウを表示し、名前を入力させる
     */
    public void inputName(){
        AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(R.string.name);
        alertDialogBuilder.setIcon(R.drawable.asd);
        final EditText input = new EditText(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialogBuilder.setView(input);
        alertDialogBuilder.setPositiveButton(R.string.signIn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String currentName = input.getText().toString();
                names.add(currentName);

                Button syokaiButton = (Button)activity.findViewById(R.id.syokai);
                syokaiButton.setVisibility(View.VISIBLE);

                // TODO: Toastよりは吹き出しに何か表示してしゃべってくれた方がいい
                Toast.makeText(activity, currentName + "登録しました～",
                        Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton(R.string.cancel ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.create();
        alertDialogBuilder.show();
    }

    /**
     * 名前を取得する
     * @return 名前List
     */
    public List getNames(){
        return names;
    }

}