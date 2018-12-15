package com.example.exitpoll;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.DatabaseHelper.COL_SCORE;
import static com.example.exitpoll.DatabaseHelper.COL_ID;
import static com.example.exitpoll.DatabaseHelper.COL_NUMBER;
import static com.example.exitpoll.DatabaseHelper.COL_IMAGE;
import static com.example.exitpoll.DatabaseHelper.TABLE_NAME;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getName();

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Item_Poll> mPollItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(Main2Activity.this);
        mDb = mHelper.getWritableDatabase();

        loadPollData();
        setupListView();

        Button clearpollButton = findViewById(R.id.clear_button);
        clearpollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv0 = new ContentValues();
                cv0.put(COL_NUMBER, "no");
                cv0.put(COL_SCORE,"0");
                cv0.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv0,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );

                ContentValues cv1 = new ContentValues();
                cv1.put(COL_NUMBER, "1");
                cv1.put(COL_SCORE,"0");
                cv1.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv1,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );

                ContentValues cv2 = new ContentValues();
                cv2.put(COL_NUMBER, "2");
                cv2.put(COL_SCORE,"0");
                cv2.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv2,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );

                ContentValues cv3 = new ContentValues();
                cv3.put(COL_NUMBER, "3");
                cv3.put(COL_SCORE,"0");
                cv3.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv3,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );

                loadPollData();
                setupListView();
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPollData();
        setupListView();
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

    private void setupListView() {
        PollListAdapter adapter = new PollListAdapter(
                Main2Activity.this,
                R.layout.item_poll,
                mPollItemList
        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);

    }
}
