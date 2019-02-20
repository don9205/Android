package com.example.cjgreen.a17dbhandling;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;

    TextView textView;

    Button button;
    Button button2;
    Button button3;
    Button button4;



    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText.getText().toString();
                createTable(tableName);

            }
        });

        button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();
                String tableName = editText.getText().toString().trim();
                int age = Integer.parseInt(ageStr);



                insertData(tableName, name, age, mobile);
            }
        });

        button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString().trim();
                selectData(tableName);
            }
        });

    }//end of onCreate()

    public void createDatabase(String databaseName){
        //데이터베이스를 생성하거나 열어주는 기능

        println("데이터베이스 생성중...");

        database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
        if(database != null) {
            textView.append("데이터베이스가 오픈되었습니다." + "\n");
        }

    }

    public  void  selectData(String tableName) {
        if(database != null) {
            String sql = "select name, age, mobile from " + tableName;
            Cursor cursor = database.rawQuery(sql, null);

            for(int i=0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);
                println("#" + i + " : " + name + " " + age + " " + mobile);
            }
            cursor.close();

        }else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }


    public  void insertData(String tableName, String name, int age, String mobile) {
        if(database != null) {
            String sql = "insert into" + tableName + "values (?, ?, ?)";
            Object[] params = {name, age, mobile};
            //+ "values ( " + name + ", " + age + "," + mobile + ")";
            database.execSQL(sql);
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }

    }


    public void createTable(String tableName){
        //데이터베이스를 생성하거나 열어주는 기능

        println("테이블 생성중...");
        if(database != null) {

        String sql = "create table " + tableName + "(_id integer PRIMARY KEY AUTOINCREMENT, name text, age integer, mobile text)";
        database.execSQL(sql);
        println("테이블 생성되었습니다.");

        }else {
            println("데이터베이스를 먼저 오픈해주세요.");
        }

    }



    public void  println(String data) {
        textView.append(data + "\n");
    }

}//end of MainActivity
