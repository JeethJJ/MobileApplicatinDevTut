package github.jeethjj.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class listElements extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_elements);

        TableLayout tableLayout = new TableLayout(this);

        TableRow tableRow = new TableRow(this);
        TextView tv = new TextView(this);

        tv.setText("ID            ");
        tableRow.addView(tv);

        tv = new TextView(this);
        tv.setText("Name              ");
        tableRow.addView(tv);

        tv = new TextView(this);
        tv.setText("Address          ");
        tableRow.addView(tv);

        tv = new TextView(this);
        tv.setText("Age           ");
        tableRow.addView(tv);

        tv = new TextView(this);
        tv.setText("Position        ");
        tableRow.addView(tv);

        tableLayout.addView(tableRow);


        db = new DatabaseHelper(this);
        Cursor data = db.getData();

        while(data.moveToNext()){
            tableRow = new TableRow(this);

            tv = new TextView(this);
            tv.setText(data.getString(0));
            tableRow.addView(tv);

            tv = new TextView(this);
            tv.setText(data.getString(1));
            tableRow.addView(tv);

            tv = new TextView(this);
            tv.setText(data.getString(2));
            tableRow.addView(tv);

            tv = new TextView(this);
            tv.setText(data.getString(3));
            tableRow.addView(tv);

            tv = new TextView(this);
            tv.setText(data.getString(4));
            tableRow.addView(tv);

            tableLayout.addView(tableRow);
        }

        setContentView(tableLayout);
    }
}
