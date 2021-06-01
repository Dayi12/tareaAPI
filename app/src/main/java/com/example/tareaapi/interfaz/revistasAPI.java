package com.example.tareaapi.interfaz;

import com.example.tareaapi.models.revistas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface revistasAPI {
    @GET("issues.php")
    public Call<List<revistas>> buscar(@Query("j_id") String j_id);

}
