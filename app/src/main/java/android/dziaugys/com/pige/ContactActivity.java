package android.dziaugys.com.pige;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }

    public void onSend(View view) {
        Toast.makeText(ContactActivity.this, R.string.sent, Toast.LENGTH_LONG).show();

        onBackPressed();
    }
}
