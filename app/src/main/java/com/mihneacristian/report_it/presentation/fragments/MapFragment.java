package com.mihneacristian.report_it.presentation.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.transition.SlideDistanceProvider;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.UiSettings;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mihneacristian.report_it.R;
import com.mihneacristian.report_it.data.dto.IssuesDTO;
import com.mihneacristian.report_it.data.remote.ApplicationAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class MapFragment extends Fragment {

    private MapView mapView;
    private Icon customIcon;
    final ApplicationAPI applicationAPI = ApplicationAPI.createAPI();
    Call<List<IssuesDTO>> call = applicationAPI.getIssues();
    FloatingActionButton fab;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        UiSettings uiSettings = mapboxMap.getUiSettings();
                        uiSettings.areAllGesturesEnabled();
                    }
                });

                call.clone().enqueue(new Callback<List<IssuesDTO>>() {
                    @Override
                    public void onResponse(Call<List<IssuesDTO>> call, Response<List<IssuesDTO>> response) {
                        for (IssuesDTO dto : response.body()) {

                            switch (dto.getIssueType()) {
                                case "Pothole": {
                                    Bitmap bitmap = BitmapUtils.getBitmapFromDrawable(
                                            ContextCompat.getDrawable(
                                                    getContext().getApplicationContext(),
                                                    R.drawable.ic_pothole
                                            )
                                    );

                                    customIcon = IconFactory.getInstance(requireContext().getApplicationContext()).fromBitmap(bitmap);
                                    break;
                                }
                                case "Litter": {
                                    Bitmap bitmap = BitmapUtils.getBitmapFromDrawable(
                                            ContextCompat.getDrawable(
                                                    getContext().getApplicationContext(),
                                                    R.drawable.ic_pin
                                            )
                                    );
                                    customIcon = IconFactory.getInstance(requireContext().getApplicationContext()).fromBitmap(bitmap);
                                    break;
                                }
                                case "Graffiti": {
                                    Bitmap bitmap = BitmapUtils.getBitmapFromDrawable(
                                            ContextCompat.getDrawable(
                                                    getContext().getApplicationContext(),
                                                    R.drawable.mapbox_marker_icon_default
                                            )
                                    );
                                    customIcon = IconFactory.getInstance(requireContext().getApplicationContext()).fromBitmap(bitmap);
                                    break;
                                }
                                default:
                                    break;
                            }

                            mapboxMap.addMarker(
                                    new MarkerOptions()
                                            .position(new LatLng(dto.getLat(), dto.getLng()))
                                            .title(dto.getIssueTitle())
                                            .snippet(dto.getIssueType() + "\n" + dto.getIssueSeverity())
                                            .icon(customIcon)
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<List<IssuesDTO>> call, Throwable t) {
                        //TODO
                    }
                });

                fab = view.findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast toast = Toast.makeText(getApplicationContext(), R.string.place_location, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                        mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                            @Override
                            public boolean onMapClick(@NonNull LatLng point) {

                                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                                        MapFragment.this.getContext(),
                                        R.style.BottomSheetDialog
                                );

                                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                                        .inflate(R.layout.layout_bottom_sheet,
                                                (LinearLayout) view.findViewById(R.id.bottomSheetContainer));

                                bottomSheetDialog.setContentView(bottomSheetView);
                                bottomSheetDialog.show();

                                mapboxMap.removeOnMapClickListener(this);
                                return true;
                            }
                        });
                    }
                });

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onDestroy();
    }
}
