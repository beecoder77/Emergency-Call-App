package com.beecoder.dicodingsubmission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
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

    TextView judul, textdetail;
    ImageView img;
    Button button_nomor;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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

                String nomor = getIntent().getStringExtra(EXTRA_NOMOR);
                int phoneNumber = Integer.parseInt(nomor);

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                context.startActivity(callIntent);
            }
        });
    }
}
