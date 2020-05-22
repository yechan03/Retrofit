package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView TV;
    private Retrofit retrofit;

    private final static String BASE_URL ="https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        GithubInterface githubInterface = retrofit.create(GithubInterface.class);

        Call<List<Contributor>> call = githubInterface.contributor("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            // 성공시
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                List<Contributor> contributors = response.body();
                // 받아온 리스트를 순회하면서
                for (Contributor contributor : contributors) {
                    // 텍스트 뷰에 login 정보를 붙임
                    TV.append(contributor.login);
                }
            }

            @Override
            // 실패시
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "정보받아오기 실패", Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
    public void init(){
        TV = findViewById(R.id.tv);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
