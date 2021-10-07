package com.example.board;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class write_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writepage);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        Button backbtn = (Button) findViewById(R.id.write_back_btn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerintent = new Intent(write_page.this, homepage.class);
                write_page.this.startActivity(registerintent);
            }
        });
        Button writesubmitbtn = (Button) findViewById(R.id.write_submit_btn);
        writesubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    write_method();
            }
        });
    }



    public void write_method() {
        EditText write_title_edit = (EditText) findViewById(R.id.write_title_edit);
        EditText write_content_edit = (EditText) findViewById(R.id.write_content_edit);

        final String writetitle = write_title_edit.getText().toString();
        final String writecontent = write_content_edit.getText().toString();

        String url = "http://192.168.1.100/joinphp/write.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);

                        AlertDialog.Builder builder = new AlertDialog.Builder(write_page.this);
                        builder.setTitle("글쓰기 성공");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.out.println("버튼클릭");
                            }
                        });
                        builder.setNegativeButton("나가기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent registerintent = new Intent(write_page.this, homepage.class);
                                write_page.this.startActivity(registerintent);
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error + "통신실패");
                    }
                }
        ) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("writetitle_php", writetitle);
                params.put("writecontent_php", writecontent);
                params.put("writer", first_page.json1);
                return params;
            }
        };

        staticmethod.requestQueue.add(request);


    }






}