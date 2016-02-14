package android.dziaugys.com.pige;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private FloatingActionButton searchButton;// = (FloatingActionButton) findViewById(R.id.fab);
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    final String ACTIVITY_NAME = "MainActivity";
    private ArrayList<Apartment> loadedList;
    private RegionEnum region;
    private HashMap<Marker, Apartment> markerList;
    private CoordinatorLayout layout;
    private SlidingUpPanelLayout slidingLayout;
    private Resources res;
    public double MaxCost;
    public boolean flat;
    public boolean House;
    GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ACTIVITY_NAME, "OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (CoordinatorLayout) findViewById(R.id.activity_main);
        region = (RegionEnum) getIntent().getSerializableExtra("region");

        //Load resources
        res = getResources();

        // Search button
        searchButton = (FloatingActionButton) findViewById(R.id.fab);
        searchButton.setOnClickListener(onSearchButtonClick());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        forceLoadApartments();

        // Sliding panel
        slidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingLayout.setPanelSlideListener(onSlideListener());

        Log.d(ACTIVITY_NAME, "before hiding");

        loadDefaultfilters();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void loadDefaultfilters() {
        MaxCost = Double.parseDouble(res.getString(R.string.MaxCost));
        flat = res.getBoolean(R.bool.FlatBool);
        House = res.getBoolean(R.bool.HouseBool);
    }


    private void forceLoadApartments() {
        loadedList = new ArrayList<>(DataProvider.getInstance().getApartments());
        markerList = new HashMap<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        hideSlidingPanel();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_contact) {
            onContactButtonClick();
            return true;
        } else if (id == android.R.id.home) {
            if (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                Log.d(ACTIVITY_NAME, "clicked back toolbar button on EXPANDED sliding panel");
                hideSlidingPanel();
                return true;
            } else {
                Log.d(ACTIVITY_NAME, "clicked back toolbar button on not expanded sliding panel");
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY_NAME, "onPause called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY_NAME, "onResume called");
        hideSlidingPanel();
    }

    private View.OnClickListener onSearchButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchDialog cdd = new SearchDialog(MainActivity.this, MaxCost, flat, House);
                cdd.show();
                Log.d(ACTIVITY_NAME, "Search button method ends");
            }
        };
    }


    @Override
    public void onStart() {
        super.onStart();
        hideSlidingPanel();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://android.dziaugys.com.pige/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(ACTIVITY_NAME, "on stop");

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://android.dziaugys.com.pige/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();

    }

    public void onContactButtonClick() {
        final Intent contactIntent = new Intent(MainActivity.this, ContactActivity.class);
        MainActivity.this.startActivity(contactIntent);
        Log.d(ACTIVITY_NAME, "contact button clicked");
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        setupShowingInfo(map);

        if (loadedList == null) {
            forceLoadApartments();
        }

        for (Apartment apartment : loadedList) {
            LatLng coords = new LatLng(apartment.getLatitude(), apartment.getLongitude());
            Marker marker = map.addMarker(new MarkerOptions().position(coords).title(apartment.getTitle()));
            markerList.put(marker, apartment);
        }

        double latitude = Double.parseDouble(res.getString(R.string.Vilnius_Latitude));
        double longtitude = Double.parseDouble(res.getString(R.string.Vilnius_Longtitude));
        LatLng cityCoords = new LatLng(latitude, longtitude);

        Log.d(ACTIVITY_NAME, "coord " + latitude + longtitude);

        if (region != null) {

            switch (region) {
                case Kaunas:
                    latitude = Double.parseDouble(res.getString(R.string.Kaunas_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Kaunas_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Klaipeda:
                    latitude = Double.parseDouble(res.getString(R.string.Klaipeda_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Klaipeda_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Siauliai:
                    latitude = Double.parseDouble(res.getString(R.string.Siauliai_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Siauliai_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Panevezys:
                    latitude = Double.parseDouble(res.getString(R.string.Panevezys_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Panevezys_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Druskininkai:
                    latitude = Double.parseDouble(res.getString(R.string.Druskininkai_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Druskininkai_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Marijampole:
                    latitude = Double.parseDouble(res.getString(R.string.Marijampole_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Marijampole_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Lazdijai:
                    latitude = Double.parseDouble(res.getString(R.string.Lazdijai_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Lazdijai_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Utena:
                    latitude = Double.parseDouble(res.getString(R.string.Utena_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Utena_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
                case Mazeikiai:
                    latitude = Double.parseDouble(res.getString(R.string.Mazeikiai_Latitude));
                    longtitude = Double.parseDouble(res.getString(R.string.Mazeikiai_Longtitude));
                    cityCoords = new LatLng(latitude, longtitude);
                    break;
            }
        }

        Log.d(ACTIVITY_NAME, String.format("coord %s %s", latitude, longtitude));

        map.moveCamera(CameraUpdateFactory.newLatLng(cityCoords));
        map.animateCamera(CameraUpdateFactory.zoomTo(11.0f));
    }

    private void setupShowingInfo(GoogleMap map) {
        // Setting a custom info window adapter for the google map
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker marker) {
                ImageHelper roundedImageHelper = new ImageHelper();
                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.marker_info_window, null);

                Apartment apartment = markerList.get(marker);

                TextView title = (TextView) v.findViewById(R.id.info_window_title);
                title.setText(apartment.getTitle());
                TextView cost = (TextView) v.findViewById(R.id.info_window_price);

                cost.setText(String.format(getString(R.string.PriceEur), apartment.getCost()));
                ImageView image = (ImageView) v.findViewById(R.id.info_window_image);
                image.setImageBitmap(roundedImageHelper.getRoundedCornerBitmap(apartment.images.get(0), 180));

                // Returning the view containing InfoWindow contents
                return v;

            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Log.d(ACTIVITY_NAME, "on marker click");
                final Apartment selectedApartment = markerList.get(marker);

                TextView title = (TextView) findViewById(R.id.sliding_panel_title);
                title.setText(selectedApartment.getTitle());

                // Star
                final ImageButton starApartment = (ImageButton) findViewById(R.id.star_apartment);
                setStar(starApartment, selectedApartment.isStarred());

                // ViewPager
                ViewPager vpPager = (ViewPager) findViewById(R.id.viewpager);

                PagerAdapter adapterViewPager
                        = new ImgGalleryPagerAdapter(
                        getSupportFragmentManager(),
                        MainActivity.this,
                        selectedApartment.getId(),
                        selectedApartment.images.size());

                vpPager.setAdapter(adapterViewPager);


                // Detail info
                TextView titleFull = (TextView) findViewById(R.id.apartment_title_full);
                titleFull.setText(selectedApartment.getTitle());

                TextView address = (TextView) findViewById(R.id.detail_info_address);
                address.setText(selectedApartment.getStreet());

                TextView contacts = (TextView) findViewById(R.id.detail_info_contacts);
                contacts.setText(selectedApartment.getContact());

                TextView cost = (TextView) findViewById(R.id.detail_info_cost);
                cost.setText(selectedApartment.getCost() + " Eur");

                TextView description = (TextView) findViewById(R.id.detail_info_description);
                description.setText(selectedApartment.getDescription());

                // Sliding panel items
                starApartment.setOnClickListener(new View.OnClickListener()

                                                 {

                                                     @Override
                                                     public void onClick(View v) {
                                                         Log.d(ACTIVITY_NAME, "on star click");
                                                         if (selectedApartment.isStarred()) {
                                                             selectedApartment.setStarred(false);
                                                             setStar(starApartment, false);
                                                         } else {
                                                             selectedApartment.setStarred(true);
                                                             setStar(starApartment, true);
                                                         }
                                                     }
                                                 }
                );

                showSlidingPanel();
            }
        });

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                hideSlidingPanel();
            }
        });
    }

    private void setStar(ImageButton icon, boolean value) {
        if (value) {
            icon.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            icon.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }

    private SlidingUpPanelLayout.PanelSlideListener onSlideListener() {
        return new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View view, float v) {
            }

            @Override
            public void onPanelCollapsed(View view) {
                Log.d(ACTIVITY_NAME, "on sliding panel collapse");
            }

            @Override
            public void onPanelExpanded(View view) {
            }

            @Override
            public void onPanelAnchored(View view) {
            }

            @Override
            public void onPanelHidden(View view) {
            }
        };
    }

    private void hideSlidingPanel() {
        if (slidingLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
            Log.d(ACTIVITY_NAME, "hide sliding panel");
            slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            showSearchButton();
        }
    }


    private void showSlidingPanel() {
        if (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
            Log.d(ACTIVITY_NAME, "show sliding panel");
            slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            hideSearchButton();
        }
    }

    private void hideSearchButton() {
        TranslateAnimation mAnimation = new TranslateAnimation(0, 500, 0, 0);
        mAnimation.setDuration(400);
        mAnimation.setFillAfter(true);
        searchButton.setAnimation(mAnimation);
        searchButton.hide();
    }


    private void showSearchButton() {
        searchButton.show();
        TranslateAnimation mAnimation = new TranslateAnimation(500, 0, 0, 0);
        mAnimation.setDuration(400);
        mAnimation.setFillAfter(true);
        searchButton.setAnimation(mAnimation);
    }

    public void refreshMap(double maxCost, boolean apartment, boolean house) {
        Log.d(ACTIVITY_NAME, "refresh map called" + maxCost);

        MaxCost = maxCost;
        flat = apartment;
        House = house;

        ArrayList<Apartment> notShow = new ArrayList<>(loadedList);
        markerList.clear();
        googleMap.clear();

        for (android.dziaugys.com.pige.Apartment apartm : notShow) {
            if (apartm.getCost() <= MaxCost && (apartm.isFlat() == flat || apartm.isFlat() != House)) {
                LatLng coords = new LatLng(apartm.getLatitude(), apartm.getLongitude());

                Marker marker = googleMap.addMarker(new MarkerOptions().position(coords).title(apartm.getTitle()));
                markerList.put(marker, apartm);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(ACTIVITY_NAME, "back pressed");
        if (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
            hideSlidingPanel();
        } else {
            super.onBackPressed();
        }
    }


}
