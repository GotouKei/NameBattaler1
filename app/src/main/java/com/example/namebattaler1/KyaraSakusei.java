package com.example.namebattaler1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KyaraSakusei extends AppCompatActivity implements      //キャラを作成するためのクラス
    RadioGroup.OnCheckedChangeListener{

    private EditText mEditText1Name;    //入力された名前を格納する

    private TextView Name1Kome1;    //米印を入れる変数

    private Button touroku;     //登録ボタン

    private RadioGroup kyaraSentaku;    //キャラのジョブを決めるラジオボタン

    private Intent intent;

    private String strName;
    private int intHp;      //HP
    private int intMp;      //MP
    private int intStr;     //STR
    private int intDef;     //DEF
    private int intAgi;     //AGI
    private int intLuck;    //LUCK
    private int intParty_Number;     //PARTYNUMBER
    private String strCreate_At;    //CREATE_AT

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyara_sakusei);

        findViews();

        init();

        kyaraSentaku.setOnCheckedChangeListener(this);

        touroku.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(intent != null) {
                    InputMethodManager inputMethodManager =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                    saveList();

                    intent.putExtra("KEY_NAME", strName);
                    intent.putExtra("KEY_HP", intHp);
                    intent.putExtra("KEY_MP", intMp);
                    intent.putExtra("KEY_STR", intStr);
                    intent.putExtra("KEY_DEF", intDef);
                    intent.putExtra("KEY_AGI", intAgi);
                    intent.putExtra("KEY_LUCK", intLuck);
                    intent.putExtra("KEY_PARTY_NUMBER", intParty_Number);
                    intent.putExtra("KEY_CREATE_AT", strCreate_At);

                    startActivity(intent);
                }

                else{
                    //Toast.makeText(KyaraSakusei.this, "ジョブを選んでください");
                }
            }
        });
    }


    private void findViews(){
        mEditText1Name = findViewById(R.id.editText1Name);

        Name1Kome1 = findViewById(R.id.text1Kome1);

        touroku = findViewById(R.id.button1Regist);

        kyaraSentaku = findViewById(R.id.radioGroup1);

    }

    private void init(){
        mEditText1Name.setText("");

        Name1Kome1.setText("");

        mEditText1Name.requestFocus();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        switch(checkedId){
            case R.id.radioButton4_senshi:
                intent = new Intent(KyaraSakusei.this, KyaraKanryo.class);
                intHp =
                break;
            case R.id.radioButton4_mahotsukai:
                intent = new Intent(KyaraSakusei.this, KyaraKanryo.class);
                break;
            case R.id.radioButton4_soryo:
                intent = new Intent(KyaraSakusei.this, KyaraKanryo.class);
                break;
        }
    }

    private void saveList(){
        strName = mEditText1Name.getText().toString();

        if(strName.equals("")) {
            Name1Kome1.setText("※");

            Toast.makeText(KyaraSakusei.this, "※の箇所を入力してください", Toast.LENGTH_SHORT).show();
        } else{
            Name1Kome1.setText("");
            DBAdapter dbAdapter = new DBAdapter(this);

            dbAdapter.openDB();

            dbAdapter.saveDB(strName, intHp, intMp, intStr, intDef, intAgi, intLuck, intParty_Number, strCreate_At);
            }
    }
}
