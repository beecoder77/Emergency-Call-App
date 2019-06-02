package com.beecoder.dicodingsubmission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Ecall> ecallList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle("Emergency List");
        recyclerView = findViewById(R.id.recyclev);
        Log.i("autolog", "onCreate");

        getData();
    }

    private void getData() {
        Log.i("autolog", "getData");
        try {
            String url = "http://api.gantengbanget.me/";
            Log.i("autolog", "http://api.gantengbanget.me/");

            Retrofit retrofit= null;
            Log.i("autolog", "retrofit");

            if (retrofit == null){
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.i("autolog", "build();");
            }

            APIService service = retrofit.create(APIService.class);
            Log.i("autolog", " APIService service = retrofit.create(APIService.class);");

            Call<List<Ecall>> call = service.getEcallData();
            Log.i("autolog", "Call<ArrayList<Ecall>> call = service.getEcallData();");

            call.enqueue(new Callback<List<Ecall>>() {
                @Override
                public void onResponse(Call<List<Ecall>> call, Response<List<Ecall>> response) {
                    ecallList = response.body();
                    Log.i("autolog", "ecallList = response.body();");

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    Log.i("autolog", "recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));");

                    EcallAdapter ecallAdapter = new EcallAdapter(MainActivity.this, ecallList);
                    Log.i("autolog", "EcallAdapter ecallAdapter = new EcallAdapter(MainActivity.this, ecallList);");

                    ecallAdapter.setListEcall(ecallList);
                    Log.i("autolog", "ecallAdapter.setListEcall(ecallList);");

                    recyclerView.setAdapter(ecallAdapter);
                    Log.i("autolog", "recyclerView.setAdapter(ecallAdapter);");
                }

                @Override
                public void onFailure(Call<List<Ecall>> call, Throwable t) {
                    Log.i("autolog", t.getMessage());
                }
            });
        }catch (Exception e) {Log.i("autolog", "Exception");}
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
