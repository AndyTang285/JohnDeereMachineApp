package com.example.marxteamproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.AdvancedMarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.Manifest;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    FloatingActionButton currentLocationBtn;
    public static int LOCATION_REQUEST_CODE = 100;

    private Marker currentLocationMarker;

    // creating a variable
    // for search view.
    SearchView searchView;
    LinearLayout pinInfoPg;

    EditText pinNameText;

    //initializing system to find current location
    FusedLocationProviderClient fusedLocationProviderClient;

    Handler handler;

    long refreshTime = 2000; //1000 = 1sec
    Runnable runnable;
    //initializing SupportMapFragment to get the map screen
    SupportMapFragment mapFragment;

    //tractor coordinates
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef = db.collection("tractors").document("default");
    CollectionReference home = docRef.collection("home");
    CollectionReference work = docRef.collection("work");

    CollectionReference pins = db.collection("pins");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        // initializing our search view.
        searchView = findViewById(R.id.map_search);

        //find current location button from the layout
        currentLocationBtn = findViewById(R.id.current_location);


        // Obtain the SupportMapFragment and get notified
        // when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if(mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        fusedLocationProviderClient = (FusedLocationProviderClient) LocationServices.getFusedLocationProviderClient(this);

        handler();

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(MapActivity.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address = addressList.get(0);

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    // on below line we are adding marker to that position.
                    map.addMarker(new MarkerOptions().position(latLng).title(location));

                    // below line is to animate camera to that position.
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    handler.removeCallbacks(runnable);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        work.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot document : queryDocumentSnapshots) {
                    String tractorID = document.getId();
                    /*String tractorName = document.getString("tractorName");
                    double tractorLatitude = document.getDouble("tractorLatitude");
                    double tractorLongitude = document.getDouble("tractorLongitude");
                    Log.i("XDXD", tractorID + "Latitude: " + tractorLatitude + " " + "Longitude: " + tractorLongitude);
                    LatLng tractorLocation = new LatLng(tractorLatitude, tractorLongitude);
                    map.addMarker(new MarkerOptions().position(tractorLocation).title(tractorName));*/
                }
            }
        });

        pins.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                    String pinID = document.getId();
                    String pinName = document.getString("name");
                    double pinLatitude = document.getDouble("latitude");
                    double pinLongitude = document.getDouble("longitude");
                    LatLng pinLocation = new LatLng(pinLatitude, pinLongitude);
                    map.addMarker(new MarkerOptions().position(pinLocation).title(pinName).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                }
            }
        });
    }

    private void handler() {
        handler = new Handler();

        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(runnable,refreshTime);
                checkLocationPermission();
            }
        }, refreshTime );
    }


    private void checkLocationPermission() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getUserLocation();

        } else {
            requestForPermissions();
        }
    }

    private void requestForPermissions() {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Accepted", Toast.LENGTH_SHORT).show();
            } else  {
                Toast.makeText(this, "Permission Rejected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    double lat = location.getLatitude();
                    double lng = location.getLongitude();

                    LatLng userLocation = new LatLng(lat, lng);

                    if (currentLocationMarker != null) {
                        currentLocationMarker.remove();
                    }

                    currentLocationMarker = map.addMarker(new MarkerOptions().position(userLocation).title("Me").icon(BitmapDescriptorFactory.fromResource(R.drawable.vishu)));

                    map.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
                    map.animateCamera(CameraUpdateFactory.zoomTo(15));

                    Log.i("XOXO", "" + lat + " " + lng);

                }
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        //click and hold for a few seconds, then create a pin will show up
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                handler.removeCallbacks(runnable);
                showPinPopup(findViewById(R.id.map_fragment), latLng);
            }
        });


        //when clicked, finds current location again
        currentLocationBtn.setOnClickListener((v -> handler()));


        //when map dragged, stop always taking user back to current  location
        map.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                handler.removeCallbacks(runnable);
            }
        });
    }

    //Pin popup
    private void showPinPopup(View view, LatLng latLng) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View pinPopup = inflater.inflate(R.layout.create_pin_screen, null);
        PopupWindow popupWindow = new PopupWindow(pinPopup, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);


        //when user clicks cancel button, the popup closes and no pin is created
        pinPopup.findViewById(R.id.cancel_pin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        //when user clicks create pin, a pin gets created, and name gets stored.
        pinPopup.findViewById(R.id.create_pin_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               pinNameText = pinPopup.findViewById(R.id.pin_name_edit_text);
                String pinName = pinNameText.getText().toString();
                MarkerOptions marker = new MarkerOptions().position(latLng).title(pinName).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
                map.addMarker(marker);
                // Store the pin in Firestore
                storePinInFirestore(latLng, pinName);
                popupWindow.dismiss();
            }
        });
    }

    private void storePinInFirestore(LatLng latLng, String pinName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference pinsCollection = db.collection("pins");

        Map<String, Object> pinData = new HashMap<>();
        pinData.put("latitude", latLng.latitude);
        pinData.put("longitude", latLng.longitude);
        pinData.put("name", pinName);

        pinsCollection.add(pinData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("Firestore", "Pin stored successfully: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Firestore", "Error storing pin: " + e.getMessage());
            }
        });
    }



}
