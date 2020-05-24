package com.example.namebattaler1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class KyaraIchiran extends AppCompatActivity {

    private DBAdapter dbAdapter;
    private MyBaseAdapter myBaseAdapter;
    private List<MyListItem> items;
    private ListView mListView02;
    private MyListItem myListItem;
    private MyListItem sakujo;

    private String[] columns = null;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kyara_ichiran);

        dbAdapter = new DBAdapter(this);

        items = new ArrayList<>();

        myBaseAdapter = new MyBaseAdapter(this, items);

        mListView02 = findViewById(R.id.listView2);

        loadMyList();

        mListView02.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                Intent intent = new Intent(getApplicationContext(), KyaraShosai.class);

                MyListItem selectedText = items.get(position);
                String name = selectedText.getName();

                intent.putExtra("Text", name);

                startActivity(intent);
            }
        });


        mListView02.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id){
                AlertDialog.Builder builder = new AlertDialog.Builder(KyaraIchiran.this);
                builder.setTitle("削除");
                builder.setMessage("削除しますか");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {
                        sakujo = items.get(position);
                        int listId = sakujo.getId();

                        dbAdapter.openDB();
                        dbAdapter.selectDelete(String.valueOf(listId));
                        dbAdapter.closeDB();
                        loadMyList();
                    }
                });

                builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
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
                        c.getInt(0),
                        c.getString(1));

                items.add(myListItem);
            } while(c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();
        mListView02.setAdapter(myBaseAdapter);
        myBaseAdapter.notifyDataSetChanged();
    }


    public class MyBaseAdapter extends BaseAdapter {

        private Context context;
        private List<MyListItem> items;

        private class ViewHolder{
            TextView text11Name;
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

                TextView text11Name = view.findViewById(R.id.text11Name);

                holder = new ViewHolder();
                holder.text11Name = text11Name;
                view.setTag(holder);

            } else{
                holder = (ViewHolder) view.getTag();
            }

            holder.text11Name.setText(myListItem.getName());

            return view;
        }
    }


}
