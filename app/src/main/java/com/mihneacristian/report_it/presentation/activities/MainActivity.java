package com.mihneacristian.report_it.presentation.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.mapbox.mapboxsdk.Mapbox;
import com.mihneacristian.report_it.R;
import com.mihneacristian.report_it.presentation.fragments.AboutUsFragment;
import com.mihneacristian.report_it.presentation.fragments.IssuesFragment;
import com.mihneacristian.report_it.presentation.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    private MapFragment mapFragment;
    private IssuesFragment issuesFragment;
    private AboutUsFragment aboutUsFragment;
    public static final int NOTIFICATION_LAUNCH_CODE = 485;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.bottomNavigation);
        chipNavigationBar.setItemSelected(R.id.map, true);

        mapFragment = new MapFragment();
        issuesFragment = new IssuesFragment();
        aboutUsFragment = new AboutUsFragment();

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
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frameLayout, aboutUsFragment)
                                .commitNowAllowingStateLoss();
                        break;
                    }
                    case R.id.contactUs: {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.author_email)});
                        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject));
                        intent.putExtra(Intent.EXTRA_TEXT, "\n" +
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