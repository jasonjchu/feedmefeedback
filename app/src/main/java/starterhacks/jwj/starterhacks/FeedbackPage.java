package starterhacks.jwj.starterhacks;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class FeedbackPage extends AppCompatActivity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_page);

        HashMap<String, String> params = new HashMap<>();
        params.put("email", "will@mail.com" );

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, "http://feed-me-back.herokuapp.com/users/get-questions", new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Response: " + response.toString());
                        final TextView view = new TextView(getApplicationContext());
                        final LinearLayout layout = findViewById(R.id.feedPage);
                        view.setText(response.toString());
                        layout.addView(view);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Failure! " + error.toString());

                    }
                });
        queue.add(jsObjRequest);
    }
}
