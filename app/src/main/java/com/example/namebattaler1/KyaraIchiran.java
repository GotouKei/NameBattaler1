package com.example.namebattaler1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class KyaraIchiran extends AppCompatActivity {

    private DBAdapter dbAdapter;    //データベースを操作するインスタンス
    private MyBaseAdapter myBaseAdapter;    //自身が持っているリストを変換
    private List<MyListItem> items;     //登録されたデータ
    private ListView onlyListView;   //一個の登録されたデータ
    private MyListItem myListItem;  //登録されたデータの箱の箱

    private String[] columns = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyara_ichiran);

        dbAdapter = new DBAdapter(this);    //データベースに自身の情報を渡す

        items = new ArrayList<>();  //DBに登録されたリスト

        myBaseAdapter = new MyBaseAdapter(this, items);     //

        onlyListView = findViewById(R.id.listView);

        loadMyList();

        onlyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent = new Intent(getApplicationContext(), KyaraShosai.class);

                loadMyList();

                MyListItem selectedText = items.get(position);
                String strName = selectedText.getName();
                String strJob = Integer.toString(selectedText.getJob());
                String strHp = Integer.toString(selectedText.getHp());
                String strMp = Integer.toString(selectedText.getMp());
                String strStr = Integer.toString(selectedText.getStr());
                String strDef = Integer.toString(selectedText.getDef());
                String strAgi = Integer.toString(selectedText.getAgi());
                String strLuck = Integer.toString(selectedText.getLuck());
                String strCreate_At = Integer.toString(selectedText.getCreate_At());

                intent.putExtra("KEY_NAME", strName);
                intent.putExtra("KEY_JOB", strJob);
                intent.putExtra("KEY_HP", strHp);
                intent.putExtra("KEY_MP", strMp);
                intent.putExtra("KEY_STR", strStr);
                intent.putExtra("KEY_DEF", strDef);
                intent.putExtra("KEY_AGI", strAgi);
                intent.putExtra("KEY_LUCK", strLuck);
                intent.putExtra("KEY_CREATE_AT", strCreate_At);

                startActivity(intent);
            }
        });


    }

    public void kyara_sakusei1(View view){
        Intent intent = new Intent(this, KyaraSakusei.class);
        startActivity(intent);
    }

    public void modoru1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void loadMyList(){
        items.clear();

        dbAdapter.openDB();

        Cursor c = dbAdapter.getDB(columns);

        if(c.moveToFirst()){
            do{
                myListItem = new MyListItem(
                        c.getString(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getInt(3),
                        c.getInt(4),
                        c.getInt(5),
                        c.getInt(6),
                        c.getInt(7),
                        c.getInt(8));

                items.add(myListItem);
            } while(c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();
        onlyListView.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged();
    }

    public class MyBaseAdapter extends BaseAdapter {

        private Context context;
        private List<MyListItem> items;

        private class ViewHolder{
            TextView text12Name;
            TextView text12Job;
            TextView text12Hp;
            TextView text12Mp;
            TextView text12Str;
            TextView text12Def;
            TextView text12Agi;
        }

        MyBaseAdapter(Context context, List<MyListItem> items){
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount(){
            return items.size();
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;

            myListItem = items.get(position);

            if(view == null){
                LayoutInflater inflater =
                        (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert inflater != null;
                view = inflater.inflate(R.layout.row_sheet_listview, parent, false);

                TextView text12Name = view.findViewById(R.id.text12Name);
                TextView text12Job = view.findViewById(R.id.text12Job);
                TextView text12Hp = view.findViewById(R.id.text12Hp);
                TextView text12Mp = view.findViewById(R.id.text12Mp);
                TextView text12Str = view.findViewById(R.id.text12Str);
                TextView text12Def = view.findViewById(R.id.text12Def);
                TextView text12Agi = view.findViewById(R.id.text12Agi);

                holder = new ViewHolder();
                holder.text12Name = text12Name;
                holder.text12Job = text12Job;
                holder.text12Hp = text12Hp;
                holder.text12Mp = text12Mp;
                holder.text12Str = text12Str;
                holder.text12Def = text12Def;
                holder.text12Agi = text12Agi;
                view.setTag(holder);

            } else{
                holder = (ViewHolder) view.getTag();
            }

            String strJob = Integer.toString(myListItem.getJob());
            String strHp = Integer.toString(myListItem.getHp());
            String strMp = Integer.toString(myListItem.getMp());
            String strStr = Integer.toString(myListItem.getStr());
            String strDef = Integer.toString(myListItem.getDef());
            String strAgi = Integer.toString(myListItem.getAgi());

            holder.text12Name.setText(myListItem.getName());
            holder.text12Job.setText(strJob);
            holder.text12Hp.setText(strHp);
            holder.text12Mp.setText(strMp);
            holder.text12Str.setText(strStr);
            holder.text12Def.setText(strDef);
            holder.text12Agi.setText(strAgi);

            return view;
        }
    }
}