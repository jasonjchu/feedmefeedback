package starterhacks.jwj.starterhacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> incomplete = new ArrayList<String>();
    private ArrayList<String> coupon = new ArrayList<String>();

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
                    updateListToDo();
                    return true;
                case R.id.navigation_scan:
                    getScanner();
                    return true;
                case R.id.navigation_rewards:
                    TextView view2 = (TextView) findViewById(R.id.message);
                    view2.setText("Rewards");
                    updateCoupon();
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
        TextView view = (TextView) findViewById(R.id.message);
        if (resultCode == RESULT_OK) {
            code = data.getStringExtra("SCAN_RESULT");
            view.setVisibility(view.VISIBLE);
            view.setText("Success!");
            System.out.println(code);
            String append = code;
            incomplete.add(append);
        } else {
            view.setText("Failure!");
            view.setVisibility(view.VISIBLE);
        }
        ListView lview1 = (ListView) findViewById(R.id.lView1);
        lview1.setVisibility(lview1.GONE);
    }

    private void updateListToDo(){
        TextView view1 = (TextView) findViewById(R.id.message);
        if(incomplete.size() == 0){
            view1.setVisibility(view1.VISIBLE);
            view1.setText("To Do");
        } else{
            view1.setVisibility(view1.GONE);
        }
        ListView lv1 = (ListView) findViewById(R.id.lView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,incomplete.toArray(new String[incomplete.size()]));
        lv1.setAdapter(adapter);
        lv1.setVisibility(View.VISIBLE);
    }

    private void updateCoupon(){
        TextView view1 = (TextView) findViewById(R.id.message);
        if(incomplete.size() == 0){
            view1.setVisibility(view1.VISIBLE);
            view1.setText("Rewards");
        } else{
            view1.setVisibility(view1.GONE);
        }
        ListView lv1 = (ListView) findViewById(R.id.lView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,coupon.toArray(new String[coupon.size()]));
        lv1.setAdapter(adapter);
        lv1.setVisibility(lv1.VISIBLE);
    }
}
