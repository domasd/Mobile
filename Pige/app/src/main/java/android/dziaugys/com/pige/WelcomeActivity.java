package android.dziaugys.com.pige;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;


public class WelcomeActivity extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "WelcomeActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    private void formInitialApartmentList(RegionEnum region) {

        DataProvider provider = DataProvider.getInstance();
        provider.context = this;
        provider.loadByRegion(region);
    }

    public void onHelpClick(View view) {

        Uri pigeWebsite = Uri.parse("http://uosis.mif.vu.lt/~dodz1300/pige/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, pigeWebsite);
        startActivity(webIntent);
    }

    public void onClickVilnius(View view) {
        Log.d(ACTIVITY_NAME, "vilnius clicked");
        moveToMap(RegionEnum.Vilnius);
    }

    public void onClickUtena(View view) {
        Log.d(ACTIVITY_NAME, "utena clicked");
        moveToMap(RegionEnum.Utena);
    }

    public void onClickDruskininkai(View view) {
        Log.d(ACTIVITY_NAME, "druskininkai clicked");
        moveToMap(RegionEnum.Druskininkai);
    }


    public void onClickLazdijai(View view) {
        Log.d(ACTIVITY_NAME, "lazdijai clicked");
        moveToMap(RegionEnum.Lazdijai);
    }

    public void onClickKaunas(View view) {
        Log.d(ACTIVITY_NAME, "kaunas clicked");
        moveToMap(RegionEnum.Kaunas);
    }

    public void onClickMarijampole(View view) {
        Log.d(ACTIVITY_NAME, "marijampole clicked");
        moveToMap(RegionEnum.Marijampole);
    }

    public void onClickPanevezys(View view) {
        Log.d(ACTIVITY_NAME, "panevezys clicked");
        moveToMap(RegionEnum.Panevezys);
    }

    public void onClickSiauliai(View view) {
        Log.d(ACTIVITY_NAME, "siauliai clicked");
        moveToMap(RegionEnum.Siauliai);
    }

    public void onClickMazeikiai(View view) {
        Log.d(ACTIVITY_NAME, "mazeikiai clicked");
        moveToMap(RegionEnum.Mazeikiai);
    }

    public void onClickKlaipeda(View view) {
        Log.d(ACTIVITY_NAME, "klaipeda clicked");
        moveToMap(RegionEnum.Klaipeda);
    }

    private void moveToMap(final RegionEnum region){
        RelativeLayout layout =(RelativeLayout)findViewById(R.id.ltBordersLayout);

        final Intent mapIntent = new Intent(WelcomeActivity.this, MainActivity.class);
        mapIntent.putExtra("region", region);

        switch (region){
            case Vilnius:
                layout.setBackgroundResource(R.drawable.lt_vilnius);
                break;
            case Kaunas:
                layout.setBackgroundResource(R.drawable.lt_kaunas);
                break;
            case Klaipeda:
                layout.setBackgroundResource(R.drawable.lt_klaipeda);
                break;
            case Siauliai:
                layout.setBackgroundResource(R.drawable.lt_siauliai);
                break;
            case Marijampole:
                layout.setBackgroundResource(R.drawable.lt_marijampole);
                break;
            case Druskininkai:
                layout.setBackgroundResource(R.drawable.lt_druskininkai);
                break;
            case Lazdijai:
                layout.setBackgroundResource(R.drawable.lt_lazdijai);
                break;
            case Mazeikiai:
                layout.setBackgroundResource(R.drawable.lt_mazeikiai);
                break;
            case Panevezys:
                layout.setBackgroundResource(R.drawable.lt_panevezys);
                break;
            case Utena:
                layout.setBackgroundResource(R.drawable.lt_utena);
                break;
        }

        final ProgressDialog progressDialog = ProgressDialog.show(WelcomeActivity.this, getString(R.string.prasome_palaukti), getString(R.string.siunciama), true);

        new Thread() {
            public void run() {
                try{
                    Log.d(ACTIVITY_NAME,"forming inital list");
                    formInitialApartmentList(region);
                } catch (Exception e) {
                }
                // start next intent
                new Thread() {
                    public void run() {
                        // Dismiss the Dialog
                        progressDialog.dismiss();
                        // start selected activity
                        if ( WelcomeActivity.this != null) WelcomeActivity.this.startActivity(mapIntent);
                    }
                }.start();
            }
        }.start();
    }



}
