package com.beecoder.dicodingsubmission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Detail extends AppCompatActivity {

    public static final String EXTRA_DETAIL = "extra_detail";
    public static final String EXTRA_NOMOR = "extra_nomor";
    public static final String EXTRA_LINK = "extra_link";
    public static final String EXTRA_NAME = "extra_name";

    private static final int REQUEST_CALL = 1;

    TextView judul, textdetail;
    ImageView img;
    Button button_nomor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setActionBarTitle("Detail");
        judul = findViewById(R.id.detail_judul);
        textdetail = findViewById(R.id.detail_detail);
        img = findViewById(R.id.detail_img);
        button_nomor = findViewById(R.id.detail_nomor);


        hajar();

    }

    private void hajar() {

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String detail = getIntent().getStringExtra(EXTRA_DETAIL);
        String link = getIntent().getStringExtra(EXTRA_LINK);

        Glide.with(Detail.this)
                .load(link)
                .apply(new RequestOptions().override(150,200))
                .into(img);

        judul.setText(name);
        textdetail.setText(detail);

        button_nomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nomor = (int) getIntent().getIntExtra(String.valueOf(EXTRA_NOMOR),0);
                String phoneNumber = Integer.toString(nomor);

                if(ContextCompat.checkSelfPermission(Detail.this,Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Detail.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                }else {
                    String dial = "tel:" + phoneNumber;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                hajar();
            }else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
