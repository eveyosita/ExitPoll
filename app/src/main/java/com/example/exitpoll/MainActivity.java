package com.example.exitpoll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.DatabaseHelper.COL_SCORE;
import static com.example.exitpoll.DatabaseHelper.COL_ID;
import static com.example.exitpoll.DatabaseHelper.COL_NUMBER;
import static com.example.exitpoll.DatabaseHelper.COL_IMAGE;
import static com.example.exitpoll.DatabaseHelper.TABLE_NAME;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Item_Poll> mPollItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button number1 = findViewById(R.id.number1_button);
        Button number2 = findViewById(R.id.number2_button);
        Button number3 = findViewById(R.id.number3_button);
        Button voteno = findViewById(R.id.no_button);

        loadPollData();

        voteno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPollData();
                Item_Poll item = mPollItemList.get(0);
                String sText = item.score.toString();
                int score = Integer.valueOf(sText) + 1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "no");
                cv.put(COL_SCORE, newscore);
                cv.put(COL_IMAGE, "vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPollData();


            }
        });

        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPollData();
                Item_Poll item = mPollItemList.get(1);
                String sText = item.score.toString();
                int score = Integer.valueOf(sText) + 1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "1");
                cv.put(COL_SCORE, newscore);
                cv.put(COL_IMAGE, "one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPollData();
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPollData();
                Item_Poll item = mPollItemList.get(2);
                String sText = item.score.toString();
                int score = Integer.valueOf(sText) + 1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "2");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPollData();
            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPollData();
                Item_Poll item = mPollItemList.get(3);
                String sText = item.score.toString();
                int score = Integer.valueOf(sText) + 1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NUMBER, "3");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPollData();
            }
        });


        Button nextbutton = findViewById(R.id.next_button);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
    }
        private void loadPollData() {
            Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

            mPollItemList = new ArrayList<>();
            while (c.moveToNext()) {
                long id = c.getLong(c.getColumnIndex(COL_ID));
                String score = c.getString(c.getColumnIndex(COL_SCORE));
                String number = c.getString(c.getColumnIndex(COL_NUMBER));
                String image = c.getString(c.getColumnIndex(COL_IMAGE));

                Item_Poll item = new Item_Poll(id, number, score, image);
                mPollItemList.add(item);
            }
            c.close();
        }

}
