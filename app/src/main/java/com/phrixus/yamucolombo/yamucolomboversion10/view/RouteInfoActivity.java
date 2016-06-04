package com.phrixus.yamucolombo.yamucolomboversion10.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phrixus.yamucolombo.yamucolomboversion10.R;

public class RouteInfoActivity extends Activity {
    TextView route_no_txt,route_info_txt;
    ImageButton mapbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);
        route_info_txt = (TextView) findViewById(R.id.routeinfo_from_totxt);
        route_no_txt = (TextView) findViewById(R.id.routeinfo_routenotxt);
        route_no_txt.setText(getIntent().getStringExtra("route_no"));
        route_info_txt.setText(getIntent().getStringExtra("routeinfo"));
        mapbtn = (ImageButton) findViewById(R.id.routeinfo_mapbtn);
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RouteInfoActivity.this,RouteMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
