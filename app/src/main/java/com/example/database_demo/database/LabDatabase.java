package com.example.database_demo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.database_demo.entity.Person;
import com.example.database_demo.dao.PersonDao;

@Database(entities = {Person.class}, version = 1)
public abstract class LabDatabase extends RoomDatabase{

    public abstract PersonDao personDao();
}
