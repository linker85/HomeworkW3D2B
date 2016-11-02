package com.example.com.starwarsretrofit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.com.starwarsretrofit.models.Example;
import com.example.com.starwarsretrofit.models.MyInterface;
import com.example.com.starwarsretrofit.models.RelatedTopic;
import com.example.com.starwarsretrofit.models.Student;
import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG_";
    private static String BASE_URL = "https://api.duckduckgo.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doMagic(View view) {
        if (checkInternet()) {
            Retrofit retrofit = new Retrofit.Builder().
                    baseUrl(BASE_URL).
                    addConverterFactory(JacksonConverterFactory.create()).
                    build();

            MyInterface myService = retrofit.create(MyInterface.class);

            Call<Example> call = myService.retrieveCharactersByQuery("superman characters");

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    Example resultApi = response.body();
                    List<RelatedTopic> relate = resultApi.getRelatedTopics();
                    for (RelatedTopic r : relate) {
                        Log.d(TAG, r.toString());
                    }
                    //load all
                    List<RelatedTopic> contacts = SugarRecord.listAll(RelatedTopic.class);

                    Log.d(TAG, "listAll: " + contacts.toString());
                    //delete all
                    SugarRecord.deleteAll(RelatedTopic.class);
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {

                }
            });
        }
    }

    public void doMagicWithHTTPNoConverter(View view) {
        // should be a singleton
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.mocky.io/v2/57a01bec0f0000c10d0f650f")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                // ... check for failure using `isSuccessful` before proceeding

                // Read data on the worker thread
                final String responseData = response.body().string();

                // Run view-related code back on the main thread
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.d(TAG, "run: " + responseData);
                            JSON json = JSON.fromStringToJSON(responseData);
                            if (json.object != null) {
                                // If the String is a JSON array
                                if (json.isJsonArray) {
                                    JSONArray jsonArray = (JSONArray) json.object;
                                    Log.d(TAG, "run: " + jsonArray);
                                    // loop array
                                    for (int i = 0 ; i < jsonArray.length(); i++) {
                                        JSONObject obj = jsonArray.getJSONObject(i);
                                        String A = obj.getString("name");
                                        String B = obj.getString("age");
                                        String C = obj.getString("grade");
                                        System.out.println("Name: " + A + ", age: " + B + ", grade: " + C);
                                        Student s = new Student(A, B, C);
                                        s.save();
                                    }
                                }
                                // If it's a JSON object
                                else {
                                    JSONObject jsonObject = (JSONObject) json.object;
                                    Log.d(TAG, "run: " + jsonObject);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(getApplicationContext(), "checkInternet: " + "Connected to WIFI", Toast.LENGTH_LONG).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(getApplicationContext(), "checkInternet: " + "Connected to Mobile data", Toast.LENGTH_LONG).show();
            }
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "checkInternet: " + "Not connected", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
