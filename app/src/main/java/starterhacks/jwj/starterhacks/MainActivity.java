package starterhacks.jwj.starterhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    public String code = "INIT";

    private void getScanner(){
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_to_do:
                    return true;
                case R.id.navigation_scan:
                    getScanner();
                    return true;
                case R.id.navigation_rewards:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            code = data.getStringExtra("SCAN_RESULT");
            TextView view = (TextView) findViewById(R.id.message);
            view.setText("Success!\nCode: " + code);
            System.out.println(code);
        }
    }
}
