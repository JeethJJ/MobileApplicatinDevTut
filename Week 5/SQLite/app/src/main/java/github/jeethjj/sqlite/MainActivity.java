package github.jeethjj.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText name;
    EditText address;
    EditText age;
    EditText position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name=findViewById(R.id.editText5);
                address=findViewById(R.id.editText2);
                age=findViewById(R.id.editText3);
                position=findViewById(R.id.editText4);
                db.addData(String.valueOf(name.getText()),String.valueOf(address.getText()),Integer.parseInt(String.valueOf(age.getText())),String.valueOf(position.getText()));
                Intent intent = new Intent(MainActivity.this, listElements.class);
                startActivity(intent);
            }
        });
    }

}
