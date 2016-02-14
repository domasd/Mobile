package android.dziaugys.com.pige;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Domas on 2015-12-03.
 */

//TODO this is a dummy class. Replace with real DAO
public class DataProvider {

    private final String CLASS_NAME = "DataProvider";

    private Comparator<Apartment> comparatorById;

    private static DataProvider instance = new DataProvider();

    private List<Apartment> apartments;

    private DataProvider() {
        comparatorById = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment lhs, Apartment rhs) {
                if (lhs.getId() > rhs.getId()) return 1;
                else if (lhs.getId() < rhs.getId()) return -1;
                else return 0;
            }
        };
    }

    //context is used for loading images from resources
    public Context context;

    public List<Apartment> getApartments() {
        return apartments;
    }

    public Apartment getApartmentById(int id) {

        if (apartments.get(id - 1).getId() == id) {
            return apartments.get(id - 1);
        }

        for (Apartment apartment : apartments) {
            if (apartment.getId() == id) return apartment;
        }

        throw new IllegalArgumentException(context.getString(R.string.apartmentIdNotExists));
    }

    public static DataProvider getInstance() {
        if (instance == null) {
            return new DataProvider();
        }
        return instance;
    }

    public void loadByRegion(RegionEnum region) {

        apartments = new ArrayList<>();

        switch (region) {

            case Klaipeda:

            case Siauliai:

            case Panevezys:

            case Druskininkai:

            case Marijampole:

            case Lazdijai:

            case Utena:

            case Mazeikiai:

            case Vilnius:

                Log.d(CLASS_NAME, "creating apartment 1");
                Apartment apartment1 = new Apartment(1, "3 kambarių butas Mindaugo g. 14 ", 125000.00, 54.678602, 25.273884, true, true, "86000000, Erikas Erikauskas");
                apartment1.images = new ArrayList<>();
                apartment1.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment1_1));
                apartment1.setStreet("Mindaugo g. 14");
                apartment1.setDescription("3 kambarių butas Mindaugo g. 14. Prabangus būstas naujamiestyje, dvi vonios, 6 aukštas, miesto panorama pro langa");

                Log.d(CLASS_NAME, "creating apartment 2");
                Apartment apartment2 = new Apartment(2, "2 kambarių butas J.Jasinskio g. 3", 95000.00, 54.688091, 25.265631, false, true, "86000001, Petras Erikauskas");
                apartment2.images = new ArrayList<>();
                apartment2.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment2_2));
                apartment2.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment2_1));
                apartment2.setDescription("2 kambarių butas J.Jasinskio g. 3. Nedidelis, jaukus būstas. Yra dvi parkavimosi vietos požeminėje aikštelėje.");

                Log.d(CLASS_NAME, "creating apartment 3");
                Apartment apartment3 = new Apartment(3, "5 kambarių butas Gedimino pr. 31 su terasa ir balkonu", 400000.00, 54.688644, 25.273026, true, true, "86000002, Alfredas Erikauskas");
                apartment3.images = new ArrayList<>();
                apartment3.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_2));
                apartment3.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_1));
                apartment3.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_3));
                apartment3.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_4));
                apartment3.setStreet("Gedimino pr. 31");
                apartment3.setDescription("5 kambarių butas Gedimino pr. 31 su terasa ir balkonu, Centras. Už 50m viešojo transporto stotelė");

                Log.d(CLASS_NAME, "creating apartment 4");
                Apartment apartment4 = new Apartment(4, "6 kambarių kotedžas Žveryne", 400000.00, 54.694619, 25.243530, true, false, "86000003, Alfredas Erikauskas");
                apartment4.images = new ArrayList<>();
                apartment4.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_2));
                apartment4.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_3));
                apartment4.images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.apartment3_4));
                apartment4.setStreet("Žveryno g. 15");
                apartment4.setDescription("6 kambarių kotedžas Žveryne, turintis puikų vaizdą į miškelį, šalia vaikų žaidimų aikštelė. Už 100m viešojo transporto stotelė");

                apartments.add(apartment1);
                apartments.add(apartment2);
                apartments.add(apartment3);
                apartments.add(apartment4);
                break;
        }

        Collections.sort(apartments, comparatorById);

        //imitate real work
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cleanList() {

        for (Apartment apartment :
                apartments) {
            for (Bitmap img : apartment.images) {
                img.recycle();
            }
            apartment = null;
        }
        apartments = null;
    }


}
