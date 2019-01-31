package com.example.hppower.kuguideapp;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker, mBlock9Marker,mBlock14Marker,mBlock8Marker,mBlock7Marker,mBlock6Marker,mCanteen, mLibrary,mKUCAFE,mKUGH,mKUstaff,mNIB,mOffice,mNewGH,mKUINTH,mKUTTCG,mKUTTCB,mKUsocial,mKUCenter,mWarehouse,mFootball,mBasketball,mMess,mMulti,mSwim,mworkshop,mTTL,mTTL2,mBlock10,mBlock11,mBlock12,mCV,mAdmin,mFountain,mKUGate,mKUBH;
    LocationRequest mLocationRequest;
    GoogleMap mMap;
    SupportMapFragment mapFragment;
    ZoomControls zoom;
    Button satView;
    AutoCompleteTextView destination;
    String destinationEntered, blockName, infoValue;
    int photosLayout;
    double markerLat, markerLng;
    List<LatLng> markerPoints = Arrays.asList(new LatLng[2]);

    LatLng MarkerPosition;
    LatLng latLngBlock9 = new LatLng(27.619990, 85.539023);
    LatLng latLngBlock14 = new LatLng(27.617456, 85.538921);
    LatLng latLngBlock8 = new LatLng(27.619665, 85.539357);
    LatLng latLngBlock7 = new LatLng(27.619276, 85.539482);
    LatLng latLngBlock6 = new LatLng(27.618907, 85.539325);
    LatLng Canteen = new LatLng(27.619251, 85.538875);
    LatLng Library = new LatLng(27.618903, 85.538622);
    LatLng Cafe = new LatLng(27.618451, 85.538325);
    LatLng KUGH = new LatLng(27.618102, 85.539277);
    LatLng KUStaff = new LatLng(27.617663, 85.539411);
    LatLng NIB = new LatLng(27.617199, 85.539229);
    LatLng OfficeExam = new LatLng(27.617518, 85.539015);
    LatLng NewGH = new LatLng(27.617644, 85.538082);
    LatLng KUINTH = new LatLng(27.617644, 85.537914);
    LatLng KUTTCG = new LatLng(27.617431, 85.537483);
    LatLng KUTTCB = new LatLng(27.617398, 85.537207);
    LatLng KUsocial = new LatLng(27.617741, 85.536324);
    LatLng KUFit = new LatLng(27.617420, 85.535770);
    LatLng Warehouse = new LatLng(27.617655, 85.534948);
    LatLng FootBall = new LatLng(27.618778, 85.536984);
    LatLng BasketBall = new LatLng(27.618255, 85.536431);
    LatLng Mess = new LatLng(27.617988, 85.537863);
    LatLng Multi = new LatLng(27.619399, 85.537245);
    LatLng Swim = new LatLng(27.619366, 85.536726);
    LatLng workshop = new LatLng(27.619695, 85.536814);
    LatLng TTL = new LatLng(27.619695, 85.536814);
    LatLng TTL2 = new LatLng(27.620083, 85.537815);
    LatLng Block10 = new LatLng(27.619706, 85.538107);
    LatLng Block11 = new LatLng(27.619279, 85.538035);
    LatLng Block12 = new LatLng(27.618894, 85.538060);
    LatLng CV = new LatLng(27.619248, 85.538847);
    LatLng Admin = new LatLng(27.619492, 85.538627);
    LatLng Fountain = new LatLng(27.618616, 85.538596);
    LatLng KUGate = new LatLng(27.620656, 85.538378);
    LatLng KUBH = new LatLng(27.620656, 85.538378);



    String KEY_GOOGLE_API = "AIzaSyBzHFV0jP-cnHGmBDT99JJzv4_VH7a4WC0";

    Polyline polyline;
    Boolean line = false;
    Double latitude,longitude;
    View rootView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        rootView = getWindow().getDecorView().findViewById(android.R.id.content);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        zoom = findViewById(R.id.zcZoom);

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("apkflow", "zoomout clicked");

                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });

        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("apkflow", "zoomin clicked");

                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        satView = findViewById(R.id.btSatellite);
        satView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("apkflow", "view clicked");

                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    satView.setText("NORMAL VIEW");
                } else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    satView.setText("SATELLITE VIEW");
                }
            }
        });


        //location's autocomplete text
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(this, R.layout.autocompletelist, getResources().getStringArray(R.array.Location));
        destination = (AutoCompleteTextView) findViewById(R.id.placeAutoComplete);
        destination.setAdapter(arrayAdapter2);
        destination.setThreshold(2);

        destination.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                destinationEntered = (String) parent.getItemAtPosition(position);
                destination.setText(destinationEntered);
                destination.setSelection(destination.getText().length());

                hideKeyboardFrom(getApplicationContext(),rootView);
                showBox();

            }
        });
    }

    private void showBox() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.custom_alertbox, null);

        Button getDir = (Button) promptView.findViewById(R.id.GetDirection);
        Button showPhotos = (Button) promptView.findViewById(R.id.showPhotos);
        TextView main_title = (TextView) promptView.findViewById(R.id.title);
        TextView block = (TextView) promptView.findViewById(R.id.depart);
        TextView blockValue = (TextView) promptView.findViewById(R.id.departValue);

        TextView info = (TextView) promptView.findViewById(R.id.info);


        blockName();

        getDir.setText(" Get Direction ");
        showPhotos.setText(" Show Photo ");
        main_title.setText(destinationEntered);
        block.setText("Info: ");
        blockValue.setText(blockName);
        info.setText(infoValue);



        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
        builder.setCancelable(true);


        final AlertDialog alert = builder.create();

        getDir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Log.e("apkflow", "alat:" + markerLat + "blng:" + markerLng);

                markerPoints.set(1, MarkerPosition);

                LatLng origin = (LatLng) markerPoints.get(0);
                LatLng dest = (LatLng) markerPoints.get(1);

                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(url);

              //  marker.showInfoWindow();

                alert.dismiss();

            }
        });

        showPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            Intent i = new Intent(MapsActivity.this,blockPhotos.class);
            i.putExtra("blockId",destinationEntered);
            startActivity(i);


            }
        });


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alert.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = 1000;
        lp.gravity = Gravity.CENTER;

        alert.getWindow().setAttributes(lp);
        alert.setView(promptView);
        alert.show();
    }

    private void blockName() {

        switch(destinationEntered){
            case ("Block 9"):
                blockName = "DoCSE";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619990, 85.539023);
                break;

            case ("Block 8"):
                blockName = "DoME";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619665, 85.539357);
                break;
            case ("Block 7"):
                blockName = "DoME";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619276, 85.539482);
                break;
            case ("Block 6"):
                blockName = "DoME";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618907, 85.539325);
                break;
            case ("Block 14"):
                blockName = "DoME";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617456, 85.538921);
                break;
            case ("Canteen"):
                blockName = "DoME";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619251, 85.538875);
                break;
            case ("Library"):
                blockName = "KU Library";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618903, 85.538622);
                break;
            case ("Cafe"):
                blockName = "Cafe of KU";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618451, 85.538325);
                break;
            case ("KU Girls Hostel"):
                blockName = "KU Girls Hostel";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618102, 85.539277);
                break;
            case ("KU Staff Quarter"):
                blockName = "Staff Quarter";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617663, 85.539411);
                break;
            case ("Nepal Investment Bank"):
                blockName = "Staff Quarter";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617199, 85.539229);
                break;
            case ("OfficeExam"):
                blockName = "Office of Examination Controller ";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617518, 85.539015);
                break;
            case ("KU INT Hostel"):
                blockName = "KU International Hostel";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617644, 85.537914);
                break;
            case ("KU TTC Girls Hostel"):
                blockName = "KU TTC Girls Hostel";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617431, 85.537483);
                break;
            case ("KU TTC Boys Hostel"):
                blockName = "KU TTC Boys Hostel";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617398, 85.537207);
                break;
            case ("KU Social Hall"):
                blockName = "KU Social Hall";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617741, 85.536324);
                break;
            case ("KUFit"):
                blockName = "KU Fitness Centre";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617420, 85.535770);
                break;
            case ("Warehouse"):
                blockName = "Warehouse";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617655, 85.534948);
                break;
            case ("FootBall Ground"):
                blockName = "KU Football Ground";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618778, 85.536984);
                break;
            case ("BasketBall Court"):
                blockName = "KU Basketball Court";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618255, 85.536431);
                break;
            case ("KU Mess"):
                blockName = "KU Mess";
                infoValue = ".";
                MarkerPosition = new LatLng(27.617988, 85.537863);
                break;
            case ("Multi Purpose Hall"):
                blockName = "New Multipurpose Hall";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619399, 85.537245);
                break;
            case ("Swimming Pool"):
                blockName = "Swimming Pool of KU";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619366, 85.536726);
                break;
            case ("workshop"):
                blockName = "Workshop";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619695, 85.536814);
                break;
            case ("TTL"):
                blockName = "TTL";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619695, 85.536814);
                break;
            case ("TTL2"):
                blockName = "TTL2";
                infoValue = ".";
                MarkerPosition = new LatLng(27.620083, 85.537815);
                break;
            case ("Block10"):
                blockName = "10";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619706, 85.538107);
                break;
            case ("Block11"):
                blockName = "11";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619279, 85.538035);
                break;

            case ("Block12"):
                blockName = "Depart of Pharmacy";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618894, 85.538060);
                break;
            case ("CV Raman Auditorium"):
                blockName = "CV Raman Auditorium";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619248, 85.538847);
                break;
            case ("Administration Block"):
                blockName = "KU Administration Block";
                infoValue = ".";
                MarkerPosition = new LatLng(27.619492, 85.538627);
                break;
            case ("Fountain"):
                blockName = "Library Fountain";
                infoValue = ".";
                MarkerPosition = new LatLng(27.618616, 85.538596);
                break;
            case ("KU Gate"):
                blockName = "KU Entrance Gate";
                infoValue = ".";
                MarkerPosition = new LatLng(27.620656, 85.538378);
                break;
























        }




    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.e("apkflow", "marker clicked");

                markerLat = marker.getPosition().latitude;
                markerLng = marker.getPosition().longitude;

                Log.e("apkflow", "alat:" + markerLat + "blng:" + markerLng);

                MarkerPosition = new LatLng(markerLat, markerLng);
                markerPoints.set(1, MarkerPosition);

                LatLng origin = (LatLng) markerPoints.get(0);
                LatLng dest = (LatLng) markerPoints.get(1);

                String url = getDirectionsUrl(origin, dest);

                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(url);

                marker.showInfoWindow();


                return true;
            }
        });


    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.e("apkflow", "onconnected started");

        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(1000);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }


    @Override
    public void onLocationChanged(final Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }


        //Place current location marker
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

        Log.e("apkflow","latitude:"+latitude+"longitude:"+longitude);

        markerPoints.set(0,latLng);
//Showing Current Location Marker on Map

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if (null != locations && null != providerList && providerList.size() > 0) {




            Geocoder geocoder = new Geocoder(getApplicationContext(),
                    Locale.getDefault());
            try {
                List<Address> listAddresses = geocoder.getFromLocation(latitude,
                        longitude, 1);
                if (null != listAddresses && listAddresses.size() > 0) {
                    String state = listAddresses.get(0).getAdminArea();
                    String country = listAddresses.get(0).getCountryName();
                    String subLocality = listAddresses.get(0).getSubLocality();
                    markerOptions.title("" + latLng + "," + subLocality + "," + state
                            + "," + country);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        }

        mBlock9Marker = mMap.addMarker(new MarkerOptions().position(latLngBlock9).title("Block 9").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock14Marker = mMap.addMarker(new MarkerOptions().position(latLngBlock14).title("Block 14").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock8Marker = mMap.addMarker(new MarkerOptions().position(latLngBlock8).title("Block 8").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock7Marker = mMap.addMarker(new MarkerOptions().position(latLngBlock7).title("Block 7").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock6Marker = mMap.addMarker(new MarkerOptions().position(latLngBlock6).title("Block 6").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mCanteen = mMap.addMarker(new MarkerOptions().position(Canteen).title("Canteen").icon(BitmapDescriptorFactory.fromResource(R.drawable.canteeen )));
        mLibrary = mMap.addMarker(new MarkerOptions().position(Library).title("Library").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mKUCAFE = mMap.addMarker(new MarkerOptions().position(Cafe).title("KU Cafe").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe)));
        mKUGH = mMap.addMarker(new MarkerOptions().position(KUGH).title("KU Girls Hostelb").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));
        mKUstaff = mMap.addMarker(new MarkerOptions().position(KUStaff).title("KU Staff Quarter").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mNIB = mMap.addMarker(new MarkerOptions().position(NIB).title("Nepal Investment Bank").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mOffice = mMap.addMarker(new MarkerOptions().position(OfficeExam).title("Office of the Controller of Examination").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mNewGH = mMap.addMarker(new MarkerOptions().position(NewGH).title("KU New Girls Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));
        mKUINTH = mMap.addMarker(new MarkerOptions().position(KUINTH).title("KU International Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));
        mKUTTCG = mMap.addMarker(new MarkerOptions().position(KUTTCG).title("KU TTC Girls Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));
        mKUTTCB = mMap.addMarker(new MarkerOptions().position(KUTTCB).title("KU TTC Boys Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));
        mKUsocial = mMap.addMarker(new MarkerOptions().position(KUsocial).title("KU Social Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mKUCenter = mMap.addMarker(new MarkerOptions().position(KUFit).title("KU Fitness Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mWarehouse = mMap.addMarker(new MarkerOptions().position(Warehouse).title("Warehouse").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mFootball = mMap.addMarker(new MarkerOptions().position(FootBall).title("Football Ground").icon(BitmapDescriptorFactory.fromResource(R.drawable.football)));
        mBasketball = mMap.addMarker(new MarkerOptions().position(BasketBall).title("Basketball Court").icon(BitmapDescriptorFactory.fromResource(R.drawable.basketball)));
        mMess = mMap.addMarker(new MarkerOptions().position(Mess).title("KU Mess").icon(BitmapDescriptorFactory.fromResource(R.drawable.canteeen)));
        mMulti = mMap.addMarker(new MarkerOptions().position(Multi).title("KU MultiPurpose Hall").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mSwim = mMap.addMarker(new MarkerOptions().position(Swim).title("Swimming Pool").icon(BitmapDescriptorFactory.fromResource(R.drawable.swimming)));
        mworkshop = mMap.addMarker(new MarkerOptions().position(workshop).title("Workshop").icon(BitmapDescriptorFactory.fromResource(R.drawable.workshop)));
        mTTL = mMap.addMarker(new MarkerOptions().position(TTL).title("TTL Metal and Carpentry Workshop").icon(BitmapDescriptorFactory.fromResource(R.drawable.workshop)));
        mTTL2 = mMap.addMarker(new MarkerOptions().position(TTL2).title("TTL Metal and Carpentry Workshop").icon(BitmapDescriptorFactory.fromResource(R.drawable.workshop)));
        mBlock10 = mMap.addMarker(new MarkerOptions().position(Block10).title("Block 10").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock11 = mMap.addMarker(new MarkerOptions().position(Block11).title("Block 11").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mBlock12 = mMap.addMarker(new MarkerOptions().position(Block12).title("Block 12").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mCV = mMap.addMarker(new MarkerOptions().position(CV).title("CV Raman Auditorium").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mAdmin = mMap.addMarker(new MarkerOptions().position(Admin).title("Administrator Block").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mFountain = mMap.addMarker(new MarkerOptions().position(Fountain).title("Fountain").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mKUGate = mMap.addMarker(new MarkerOptions().position(KUGate).title("KU Gate").icon(BitmapDescriptorFactory.fromResource(R.drawable.blocks)));
        mKUBH = mMap.addMarker(new MarkerOptions().position(KUBH).title("KU Boys Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hostel)));



    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {
        Log.e("apkflow", "getDirectionsUrl started");


        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + KEY_GOOGLE_API;
        Log.e("apkflow", url);

        return url;
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            Log.e("apkflow", "doInBackground started");

            DownloadUrl downloadUrl = new DownloadUrl();

            String data = "";

            try {
                data = downloadUrl.readUrl(url[0]);
            } catch (Exception e) {
                Log.e("apkflow", "backgroundTask:" + e.toString());
            }
            return data;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("apkflow", "onPostExecute started");

            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);

        }

    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            Log.e("apkflow", "ParserTask started");

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            Log.e("apkflow", "onPostExecute started");

            ArrayList points;
            PolylineOptions lineOptions = null;

            try {
                for (int i = 0; i < result.size(); i++) {
                    points = new ArrayList();
                    lineOptions = new PolylineOptions();

                    List<HashMap<String, String>> path = result.get(i);

                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    lineOptions.addAll(points);
                    lineOptions.width(8);
                    lineOptions.color(Color.BLUE);
                    lineOptions.geodesic(true);

                }
            }catch (Exception e){
                e.fillInStackTrace();
            }

// Drawing polyline in the Google Map for the i-th route
            if (line){
                polyline.remove();
                line =false;
            }
            if (lineOptions != null) {
                line =true;
                polyline =  mMap.addPolyline(lineOptions);
            }
            else{
                Toast.makeText(MapsActivity.this, "Directions not found  Google Quota full!!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}







