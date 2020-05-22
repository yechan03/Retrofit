package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubInterface {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributor(
            @Path("owner") String owner,
            @Path("repo") String repo
    );


}
