package kh.edu.rupp.fe.ruppmad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import kh.edu.rupp.fe.ruppmad.adapter.Document;

public class DocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);

        TextView txtTitle = (TextView)findViewById(R.id.txt_title);
        TextView txtSize = (TextView)findViewById(R.id.txt_size);
        TextView txtHits = (TextView)findViewById(R.id.txt_hits);

        String serializedDocument = getIntent().getStringExtra("serializedDocument");
        Gson gson = new Gson();
        Document document = gson.fromJson(serializedDocument, Document.class);

        txtTitle.setText(document.getTitle());
        txtSize.setText("Size: " + document.getSize() + " M");
        txtHits.setText("Hits: " + document.getHits());

        processSaveHitHistory(document.getId());
    }

    private void processSaveHitHistory(int documentId){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject params = new JSONObject();
        String serverUrl = "http://10.0.2.2/test/ruppmad-api/save-document-hits.php?document_id=" + documentId;
        JsonObjectRequest request = new JsonObjectRequest(serverUrl, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("rupp", "Save hits history success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("rupp", "Save hits history fail: " + error.getMessage());
            }
        });
        requestQueue.add(request);
    }

}
