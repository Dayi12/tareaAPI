package com.example.tareaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tareaapi.interfaz.revistasAPI;
import com.example.tareaapi.models.revistas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText Codigo;
    TextView Resultado;
    Button btnBuscar;
    private String URL="https://revistas.uteq.edu.ec/ws/";
    private String Ruta=" https://revistas.uteq.edu.ec/ws/issues.php";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Codigo=findViewById(R.id.txtCodigo);
        Resultado=findViewById(R.id.txtResultado);
        requestQueue= Volley.newRequestQueue(this);

    }
    public void setBtnBuscarVolley (View view){
        jsonArrayRequest(Codigo.getText().toString(),Ruta);

    }
    public void setBtnBuscarRetrofil (View view){
        buscarRevista(Codigo.getText().toString());
    }

    private void buscarRevista(String j_id){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        revistasAPI revistasAPI = retrofit.create(revistasAPI.class);
        Call<List<revistas>> call = revistasAPI.buscar(j_id);
        call.enqueue(new Callback<List<revistas>>(){
            @Override
            public void onResponse(Call<List<revistas>> call, Response<List<revistas>> response) {
                try {
                    if(response.isSuccessful()){
                        List<revistas> r= response.body();
                        String c="";
                        for (int i=0;i<r.size();i++){
                            revistas p=r.get(i);
                            c+= p.toString();
                        }
                        Resultado.setText(c);
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<revistas>> call, Throwable t) {

            }
        });
    }
    private void jsonArrayRequest(String j_id, String Ruta){
        Ruta+="?j_id="+j_id;
        JsonArrayRequest json=new JsonArrayRequest(Request.Method.GET,
                Ruta, null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size=response.length();
                        for(int i=0;i<size;i++){
                            try {
                                JSONObject objeto = new JSONObject(response.get(i).toString());
                                String issue_id=objeto.getString("issue_id");
                                Resultado.append("issue_id:" + issue_id );
                                String volume=objeto.getString("volume");
                                Resultado.append("\nvolume:" + volume );
                                String number=objeto.getString("number");
                                Resultado.append("\nnumber:" + issue_id );
                                String year=objeto.getString("year");
                                Resultado.append("\nyear:" + issue_id );
                                String date_published=objeto.getString("date_published");
                                Resultado.append("\ndate_published:" + issue_id );
                                String title=objeto.getString("title");
                                Resultado.append("\ntitle:" + title );
                                String doi=objeto.getString("doi");
                                Resultado.append("\ndoi:" + doi );
                                String cover=objeto.getString("cover");
                                Resultado.append("\ncover:" + cover);

                            }catch (JSONException e){
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(json);

    }
}
