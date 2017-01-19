package android.dziaugys.com.pige;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Domas on 2015-12-04.
 */
public class SearchDialog extends Dialog implements
        android.view.View.OnClickListener {

    Activity c;
    MainActivity mainActivity;

    final double start = 0.0;
    final double end = 1000000.0;
    private double MaxCost;
    private boolean Apartment;
    private boolean House;



    public SearchDialog(Activity a,double maxCost, boolean apartment, boolean house) {
        super(a);

        this.c = a;

        if (c instanceof MainActivity){
            mainActivity =  ((MainActivity)c);
        }

        MaxCost = maxCost;
        Apartment = apartment;
        House = house;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_main);
        bindControlsToSearchDialog();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private void bindControlsToSearchDialog(){
        final TextView maxCost = (TextView) findViewById(R.id.seek_bar_max_cost);
        final CheckBox apartment = (CheckBox) findViewById(R.id.search_butas);
        final CheckBox house = (CheckBox) findViewById(R.id.search_namas);

        maxCost.setText(String.format("%1$.3f Eur", MaxCost));
        apartment.setChecked(Apartment);
        house.setChecked(House);

        ImageButton cancel = (ImageButton) findViewById(R.id.search_cancel);
        ImageButton save = (ImageButton) findViewById(R.id.search_save);

        final SeekBar maxCostSeekBar = (SeekBar) findViewById(R.id.seekBar);
        int progress = (int)MaxCost;
        maxCostSeekBar.setProgress(progress);

        maxCostSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("search dialog",String.valueOf(progress));
                maxCost.setText(String.format("%1$.2f", (double)progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apartment = apartment.isChecked();
                House = house.isChecked();

                double temp = maxCostSeekBar.getProgress();
                MaxCost = temp;

                mainActivity.refreshMap(MaxCost,Apartment,House);
                onBackPressed();

            }
        });


    }
}