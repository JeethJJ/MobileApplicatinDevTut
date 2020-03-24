package github.jeethjj.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        //db.addData("JJ","79",21,"CEO");
        Cursor data = db.getData();
        ArrayList<String> listData = new ArrayList<>();
        int i = 0;
        while(data.moveToNext()){
            listData.add(data.getString(1));
            Log.d("NUMMMMMMMMM", String.valueOf(i));
            Log.d("DATAAAAAAAA",data.getString(1));
            i++;
        }
    }
}
