package android.dziaugys.com.pige;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class ImgGalleryPagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_ITEMS;
    private Context context;
    private int apartmentId;
    final String ADAPTER_NAME = "ImgGalleryPagerAdapter";

    public ImgGalleryPagerAdapter(
            FragmentManager fragmentManager,
            Context contextInput,
            int apartmentIdInput,
            int photoCountInput) {

        super(fragmentManager);
        Log.d(ADAPTER_NAME, "contructor called");
        context = contextInput;
        apartmentId = apartmentIdInput;
        NUM_ITEMS = photoCountInput;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        Log.d(ADAPTER_NAME, "getItem called");

        Bundle bundle = new Bundle();
        bundle.putInt("apartmentId",apartmentId);
        bundle.putInt("position",position);

        Fragment fragment = new ImgFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format("%s %d", context.getResources().getString(R.string.image), position+1);
    }
}
