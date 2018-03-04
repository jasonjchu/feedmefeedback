package starterhacks.jwj.starterhacks;

import com.google.gson.Gson;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private Survey survey;
    JSONObject obj = new JSONObject();
    Gson gson = new Gson();

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String resp = "";
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
                        TextView view = new TextView(getApplicationContext());
                        final LinearLayout layout = findViewById(R.id.feedPage);
                        survey = gson.fromJson(response.toString(), Survey.class);
                        for(int i=0;i<survey.products.length;i++){
                            TextView product = new TextView(getApplicationContext());
                            TextView question = new TextView(getApplicationContext());
                            EditText entry = new EditText(getApplicationContext());
                            entry.setTextColor(Color.BLACK);
                            entry.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                            product.setTextColor(Color.BLACK);
                            product.setText(survey.products[i]);
                            product.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            product.setTextSize(32);
                            layout.addView(product);

                            question.setTextColor(Color.BLACK);
                            question.setText(survey.questions[i]);
                            question.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            question.setTextSize(24);
                            layout.addView(question);
                            layout.addView(entry);
                        }
                        Button butt = new Button(getApplicationContext());
                        butt.setText("Submit!");
                        butt.setId(View.generateViewId());
                        layout.addView(butt);
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
