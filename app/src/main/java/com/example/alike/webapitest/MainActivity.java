package com.example.alike.webapitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//public class Product
//{
//    public string Id { get; set; }
//    public string Name { get; set; }
//    public decimal Price { get; set; }
//    public string Category { get; set; }
//}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button) ;
        Button btnpost = (Button) findViewById(R.id.Postbutton) ;

        btnpost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String url = "http://webapplicationazurewebap1.azurewebsites.net/api/values";
                Toast.makeText(getApplicationContext(),"POST clicked", Toast.LENGTH_LONG).show();


                final Map<String,String> params = new HashMap<String,String>();
                params.put("firstname", "Ali");
                params.put("lastname", "Karimli");
                params.put("birthday", "02.02.1994");
                params.put("sextype", "M");
                params.put("email", "ali@kerimli.com");
                params.put("password", "12421");
                params.put("registrationtype", "A");

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),response.toString()+" OnPost", Toast.LENGTH_LONG).show();


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(),error.toString()+" OnPostError", Toast.LENGTH_LONG).show();

                    }
                });
                Volley.newRequestQueue(getApplicationContext()).add(jsonObjectRequest);

            }

        });



        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String url = "http://webapplicationazurewebap1.azurewebsites.net/api/category";

                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Toast.makeText(getApplicationContext(), response.toString()+"success", Toast.LENGTH_LONG).show();
                        //TODO: handle success
                        try {
                            setElements(response);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        //TODO: handle failure
                        Toast.makeText(getApplicationContext(),error.toString()+" get", Toast.LENGTH_LONG).show();
                    }
                });

                Volley.newRequestQueue(getApplicationContext()).add(jsonRequest);

                //Toast.makeText(getApplicationContext(), jsonRequest.toString(), Toast.LENGTH_LONG).show();
            }
        });



        // new JsonObjectRequest(Request.Method.GET, url + String.valueOf(id), null, new Response.Listener<JSONObject>()
        //String url = "https://www.youraddress.com/";
/*
        Map<String, String> params = new HashMap();
        params.put("first_param" ,"1");
        params.put("second_param", "2");
        JSONObject parameters = new JSONObject(params);
*/


    }






    private void setElements(JSONObject response) throws JSONException {
         String str=null;
         str="name:"+response.getString("name")+" id:"+response.getString("id");
         //str=str+response.getString("id");
        final EditText simpleEditText = (EditText) findViewById(R.id.editText);
        simpleEditText.setText(str);

    }
}
