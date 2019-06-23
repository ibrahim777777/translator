package com.example.translator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject> {
    TextView show;
    EditText enter;
    Spinner from,to;
    ProgressBar dd;
    Button translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show=findViewById(R.id.translatetext);
        enter=findViewById(R.id.texttt);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        dd=findViewById(R.id.progressBar);
        translate=findViewById(R.id.translate);
        translate.setBackgroundColor(Color.LTGRAY);
    }

    public void translate(View view) {
        translate.setBackgroundColor(Color.GREEN);
        show.setText(null);
        dd.setVisibility(View.VISIBLE);

        RequestQueue queue = Volley.newRequestQueue(this);
        if (from.getSelectedItem().equals("from")){
            Toast.makeText(this, "choose language", Toast.LENGTH_SHORT).show();
            return;
        }
   if (to.getSelectedItem().equals("to")){
            Toast.makeText(this, "choose language", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://api.mymemory.translated.net/get?q="+enter.getText()+"&langpair="+from.getSelectedItem()+"|"+to.getSelectedItem();
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,null,this,this);

        queue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {
        translate.setBackgroundColor(Color.LTGRAY);


        try {

       show.setText(response.getJSONObject("responseData").getString("translatedText")+"\n" +response.getJSONArray("matches").getJSONObject(0).getString("translation")+"\n"+response.getJSONArray("matches").getJSONObject(1).getString("translation")+"\n");
            dd.setVisibility(View.INVISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

