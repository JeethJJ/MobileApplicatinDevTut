package github.jeethjj.sqlite;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{
    private WeakReference<TextView> mTextView;
    private String st;

    SimpleAsyncTask(TextView tv, String st) {
        mTextView = new WeakReference<>(tv);
        this.st=st;

    }

    @SuppressLint("WrongThread")
    @Override
    protected String doInBackground(Void... voids) {
        String text="";
        for(int i=0; i<st.length();i++){
            text=text+st.charAt(i);
            set(text);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

            }
        }
        return text;
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

    protected void set(String ss){
        mTextView.get().setText(ss);
    }

}
