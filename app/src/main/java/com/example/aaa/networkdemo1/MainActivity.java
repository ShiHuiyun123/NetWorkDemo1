package com.example.aaa.networkdemo1;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {
    private TextView txtv;
    private RequestQueue requestQueue;
    private static final String TAG = "name";
    private ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtv = ((TextView) findViewById(R.id.txtv));

        requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest("", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                txtv.setText(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                txtv.setText(volleyError.getMessage());
            }
        });
        request.setTag(TAG);
        imgv = ((ImageView) findViewById(R.id.imgv));
        //参数一：下载图片的URL 参数二：Listener<Bitmap>接口回调 参数三:最大宽度 参数四：最大高度
        //参数五：像素模式（一个像素上所能展示的颜色  默认ARGB-8888）RGB-565没有透明度 //参数六：报错的接口
        ImageRequest imageRequest = new ImageRequest("https://www.baidu.com/img/bd_logo1.png", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imgv.setImageBitmap(response);
            }
        }, 100, 100, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        requestQueue.add(imageRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null)

        {
            requestQueue.cancelAll(TAG);
        }
    }
}
