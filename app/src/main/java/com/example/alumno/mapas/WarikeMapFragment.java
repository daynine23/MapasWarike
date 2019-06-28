package com.example.alumno.mapas;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class WarikeMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    GoogleMap map;
    private Marker markerIsil,markerHamnegra, pointer;
    double lat = 0.0;
    double lng = 0.0;

    public WarikeMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_warike_map, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        miUbicacion1();
        /*LatLng pp = new LatLng(-12.125108, -77.024879);
        MarkerOptions  option = new MarkerOptions();
        option.position(pp).title("Isil Miraflores");
        map.addMarker(option);*/

        //MARCADOR ISIL
        LatLng isil = new LatLng(-12.125108, -77.024879);
        markerIsil = googleMap.addMarker(new MarkerOptions().position(isil).title("Isil Miraflores"));
        //MARCADOR Hamnegra
        LatLng hamnegra = new LatLng(-12.1712825, -76.9307592);
        markerHamnegra = googleMap.addMarker(new MarkerOptions().position(hamnegra).title("La Hamburguesa Negra"));

        //CAMARA
        map.getUiSettings().setZoomControlsEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(isil,15));
        //CLICK EN EL MARCADOR
        googleMap.setOnMarkerClickListener(this);
    }

    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        final CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (pointer != null) pointer.remove();
        pointer = map.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pointer)));
        map.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion1() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(markerIsil)) {
            //Toast.makeText(this,"Mi primer evento",Toast.LENGTH_LONG).show();
            //Toast.makeText(getContext(), "mi primer evento", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(),InfoActivity.class);
            startActivity(intent);
        }
        if (marker.equals(markerHamnegra)) {
            //Toast.makeText(this,"Mi primer evento",Toast.LENGTH_LONG).show();
            //Toast.makeText(getContext(), "mi primer evento", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(),InfoActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
