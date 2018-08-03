package com.udacity.shahd.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static DatabaseHelper db;

    public static ArrayList<Container> containersList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        createContainer();
        containersList.addAll(db.getAllContainers());
        Toast.makeText(getApplicationContext(), "addAll: " + containersList.get(0).getContainerName(), Toast.LENGTH_SHORT).show();

    }
    public void map(View view) {
        Intent intent = new Intent(this, ContainersRegions.class);
        startActivity(intent);

    }

    public void trash(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map) {
            Intent intent = new Intent();
            intent.setClass(this, activity_maps.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    public static void createContainer() {
        // inserting note in db and getting
        // newly inserted note id
        Log.d("MainActivity", "createContainer");
        long id1 = db.insertContainer( "39.895504","21.416422", "mina",  "c-m-1",80);
        long id2 = db.insertContainer( "39.901738","21.413705", "mina",  "c-m-2",40);
        long id3 = db.insertContainer( "39.891728","21.412058", "mina",  "c-m-3",10);
        long id4 = db.insertContainer( "21.4149474","39.8997368", "arafat",  "c-a-1",50);
        long id5 = db.insertContainer( "21.4149474","39.8997368", "muzdalifah",  "c-z-1",10);
//        containers.add(c1);
//        containers.add(c2);
//        containers.add(c3);

        // get the newly inserted note from db
//        Container  = db.getNote(id);
//
//        if (n != null) {
//            // adding new note to array list at 0 position
//            notesList.add(0, n);
//
//            // refreshing the list
//            mAdapter.notifyDataSetChanged();
//
//            toggleEmptyNotes();
//        }

    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateContainer(int id) {
        Container container = containersList.get(id);
        // updating note text
        container.setStatus(0);

        // updating note in db
        db.updateContainer(container);


    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
//    private void deleteNote(int position) {
//        // deleting the note from db
//        db.deleteNote(notesList.get(position));
//
//        // removing the note from the list
//        notesList.remove(position);
//        mAdapter.notifyItemRemoved(position);
//
//        toggleEmptyNotes();
//    }

    /**
     * Opens dialog with Edit - Delete options
     * Edit - 0
     * Delete - 0
     */
}
