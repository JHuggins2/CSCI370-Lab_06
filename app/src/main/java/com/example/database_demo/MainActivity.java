package com.example.database_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.database_demo.asyncs.GetAllPersonsAsync;
import com.example.database_demo.dao.PersonDao;
import com.example.database_demo.database.LabDatabase;
import com.example.database_demo.entity.Person;

public class MainActivity extends AppCompatActivity {

    private Button buttonViewList;
    private Button buttonAddPerson;
    private EditText editTextName;
    LabDatabase labDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddPerson = findViewById(R.id.buttonAddName);
        buttonViewList = findViewById(R.id.buttonViewList);
        editTextName = findViewById(R.id.editTextPersonName);

        labDatabase = Room.databaseBuilder(this, LabDatabase.class, "person_DataBase").build();

        GetAllPersonsAsync getAllPersonsAsync = new GetAllPersonsAsync(this, labDatabase);

        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Person person = new Person();
                        person.setName(name);
                        labDatabase.personDao().insertPerson(person);
                        System.out.println("-------Added Person------------");
                    }
                }) .start();
            }
        });

        buttonViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllPersonsAsync.execute();
            }
        });
    }
}