package com.mihneacristian.report_it.presentation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.mapbox.mapboxsdk.Mapbox;
import com.mihneacristian.report_it.R;
import com.mihneacristian.report_it.presentation.fragments.IssuesFragment;
import com.mihneacristian.report_it.presentation.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private IssuesFragment issuesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottomNavigation);
        chipNavigationBar.setItemSelected(R.id.map, true);

        mapFragment = new MapFragment();
        issuesFragment = new IssuesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, mapFragment)
                .commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i) {
                    case R.id.map: {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frameLayout, mapFragment)
                                .commit();
                        break;
                    }
                    case R.id.issues: {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frameLayout, issuesFragment)
                                .commitNowAllowingStateLoss();
                        break;
                    }
                    case R.id.aboutUs: {
                        Toast.makeText(getApplicationContext(), "About us", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.contactUs: {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "popamihneacristian@gmail.com" });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Issues and Suggestions");
                        intent.putExtra(Intent.EXTRA_TEXT,"\n" +
                                "Manufacturer: "
                                + Build.MANUFACTURER.toUpperCase()
                                + " \nModel: "
                                + Build.MODEL.toUpperCase());
                        startActivity(Intent.createChooser(intent, ""));
                        break;
                    }
                    default:
                        break;
                }
            }
        });
    }
}