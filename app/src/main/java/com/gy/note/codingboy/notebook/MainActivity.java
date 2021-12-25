package com.gy.note.codingboy.notebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.gy.note.R;

public class MainActivity extends AppCompatActivity
{

    private Button addbtn;
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private Cursor cursor;
    private Button btn_user_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn=(Button)findViewById(R.id.add);
        lv=(ListView)findViewById(R.id.list);

        btn_user_info = findViewById(R.id.btn_user_info);
        notesDB=new NotesDB(this);
        dbReader=notesDB.getReadableDatabase();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddContent.class);
                startActivity(intent);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent=new Intent(MainActivity.this,SelectAct.class);
                intent.putExtra(NotesDB.ID,cursor.getInt(cursor.getColumnIndex(NotesDB.ID)));
                intent.putExtra(NotesDB.CONTENT,cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
                startActivity(intent);
            }
        });

        btn_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void selectDB()
    {
       cursor=dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
//        Cursor cursor = dbReader.rawQuery("select * from " + NotesDB.TABLE_NAME,new String[]{});
        adapter=new MyAdapter(this,cursor);
        lv.setAdapter(adapter);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        selectDB();
    }
}
