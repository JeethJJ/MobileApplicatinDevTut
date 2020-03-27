package github.jeethjj.asyncloaderwithprogressbar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask; import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar; import android.widget.TextView;
public class MainActivity extends AppCompatActivity { ProgressBar pb;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.pb);
        tv = findViewById(R.id.tv);
    }


    public void buttonOClickHandler(View view) {
        tv.setText("Time consuming task in progress!");
        pb.setProgress(0);
        pb.setVisibility(View.VISIBLE);
        new MyAsyncTask().execute("Message sent to background thread");
    }


    private class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            for (int i=1; i<=5; i++) {
                try {
                Thread.sleep(2000);
                    publishProgress(i);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            }
            return "Task completed";
        }
        /* Runs on the UI thread receiving the value sent by
publishProgress()
           run in the background thread */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]*20);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pb.setVisibility(View.INVISIBLE);
            tv.setText(s);
        }
    } }