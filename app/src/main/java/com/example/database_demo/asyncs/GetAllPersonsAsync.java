package com.example.database_demo.asyncs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;


import com.example.database_demo.PersonsActivity;
import com.example.database_demo.database.LabDatabase;
import com.example.database_demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

public class GetAllPersonsAsync extends AsyncTask<Void,Void, List<Person>> {

    private Context context;
    private LabDatabase labDatabase;

    public GetAllPersonsAsync(Context context, LabDatabase labDatabase) {
        this.context = context;
        this.labDatabase = labDatabase;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This will normally run on a background thread. But to better
     * support testing frameworks, it is recommended that this also tolerates
     * direct execution on the foreground thread, as part of the {@link #execute} call.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param voids The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected List<Person> doInBackground(Void... voids) {
        ArrayList<String> personNames = new ArrayList<>();
        List<Person> persons = labDatabase.personDao().getAllPersons();
        for(Person p: persons) {
            personNames.add(p.getName());
        }

        Intent i = new Intent(context, PersonsActivity.class);
        i.putExtra("Persons", personNames);
        context.startActivity(i);
        return null;
    }
}
