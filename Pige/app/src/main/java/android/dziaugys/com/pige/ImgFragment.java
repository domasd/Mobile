package android.dziaugys.com.pige;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImgFragment extends Fragment {
    // Store instance variables
    private Bitmap img;


    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.img_content, container, false);

        Bundle bundle = this.getArguments();
        int apartmentId = bundle.getInt("apartmentId");
        int position = bundle.getInt("position");

        img = DataProvider.getInstance().getApartmentById(apartmentId).images.get(position);

        ImageView imgView = (ImageView)view.findViewById(R.id.sliding_layout_image);
        imgView.setImageBitmap(img);
        return view;
    }
}
