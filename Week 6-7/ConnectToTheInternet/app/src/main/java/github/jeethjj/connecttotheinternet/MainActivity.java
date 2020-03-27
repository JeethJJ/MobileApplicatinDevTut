package github.jeethjj.connecttotheinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //https://www.googleapis.com/books/v1/volumes?q=pride+prejudice&maxRe sults=5&printType=books
    final String DEBUG_TAG = "** MAIN_ACTIVITY **"; // Base URL for the Books API.
    final String BOOK_BASE_URL =
            "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for the search string
    final String QUERY_PARAM = "q";
// Parameter to limit search results.

    final String MAX_RESULTS = "maxResults"; // Parameter to filter by print type
    final String PRINT_TYPE = "printType";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
// Build up the query URI, limiting results to 5 printed
        Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon() .appendQueryParameter(QUERY_PARAM, "pride+prejudice") .appendQueryParameter(MAX_RESULTS, "5") .appendQueryParameter(PRINT_TYPE, "books")
                .build();
        String myurl = builtURI.toString(); mTextView = findViewById(R.id.tv); mTextView.setText("Empty");
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            new SimpleAsyncTask(mTextView).execute(myurl); }

        private String downloadUrl(String myurl) {
            InputStream inputStream = null;
// Only display the first 50000 characters of the retrieved // web page content.
            int len = 500000;
            HttpURLConnection conn = null; try {
                URL url = new URL(myurl); conn =
                        (HttpURLConnection) url.openConnection(); conn.setReadTimeout(10000 /* milliseconds */); conn.setConnectTimeout(15000 /* milliseconds */); // Start the query
                conn.connect();
                int response = conn.getResponseCode(); Log.d(DEBUG_TAG, "The response is: " + response); inputStream = conn.getInputStream();
// Convert the InputStream into a string String contentAsString =
                convertInputToString(inputStream, len); return contentAsString;
                // Close the InputStream and connection
            } catch (IOException ex) {
                ex.printStackTrace(); } finally {
                conn.disconnect();
                if (inputStream != null) {
                    try { inputStream.close();
                    } catch (IOException ex2) {
                        ex2.printStackTrace(); }}}
                    return ""; }
// Reads an InputStream and converts it to a String.
                public String convertInputToString(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new
                        InputStreamReader(stream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line + "\n"); }
                    if (builder.length() == 0) { return null;
                    }
                    String resultString = builder.toString();
                    return resultString;
                }
                public class SimpleAsyncTask extends AsyncTask<String, Void, String> {
                    private WeakReference<TextView> mTextView;
                    SimpleAsyncTask(TextView tv) { mTextView = new WeakReference<>(tv);
                    }
                    @Override
                    protected String doInBackground(String... strings) {
                        String jsonFullString = downloadUrl(strings[0]);
                        try {
                            JSONObject data = new JSONObject(jsonFullString); JSONArray all_items = data.getJSONArray("items"); // extract all items titles
                            String titles = "";
                            for (int i=0; i < all_items.length(); i++) {
                                JSONObject book = all_items.getJSONObject(i);
                                JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                                titles += i + ": " + volumeInfo.getString("title") + "\n";
                            }
                            return titles;
                        } catch (JSONException jex) { jex.printStackTrace();
                        }
                        return ""; }
                    protected void onPostExecute(String result) { mTextView.get().setText(result);
                    } }


        }