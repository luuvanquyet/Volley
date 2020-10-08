package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Json_Array_Request();
    }
    private void StringRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://khoapham.vn";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"loi",Toast.LENGTH_SHORT).show();
                        Log.d("AAA","loi"+error);
                    }
                });
        requestQueue.add(stringRequest);
    }
    private void JsonobjectRequest(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void Json_Array_Request(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url ="https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jsonArray = new JSONArray(response.toString());
                    for(int i = 0; i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String khoahoc = jsonObject.getString("khoahoc");
                        String hocphi = jsonObject.getString("hocphi");
                        Toast.makeText(MainActivity.this, khoahoc +" _ " +hocphi, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"loi",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}