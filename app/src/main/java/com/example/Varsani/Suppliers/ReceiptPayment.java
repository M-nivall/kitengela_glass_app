package com.example.Varsani.Suppliers;

import static com.example.Varsani.utils.Urls.URL_PAYMENT_DETAILS;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Varsani.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReceiptPayment extends AppCompatActivity {

    private TextView tvRequestID, tvItems, tvRequestDate, tvRequestStatus, tvQuantity,tv_request_amount;
    private String requestID, items, requestDate, requestStatus, quantity,amount;
    private ImageView btn_printfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_payment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Initialize TextViews
        tvRequestID = findViewById(R.id.tv_request_id);
        tvItems = findViewById(R.id.tv_items);
        tvRequestDate = findViewById(R.id.tv_request_date);
        tvRequestStatus = findViewById(R.id.tv_request_status);
        tvQuantity = findViewById(R.id.tv_quantity);
        btn_printfile = findViewById(R.id.btn_printfile);
        tv_request_amount = findViewById(R.id.tv_request_amount);

        // Get the requestID from the Intent
        requestID = getIntent().getStringExtra("requestID");

        btn_printfile.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                print();
           }
        });

        // Call API to fetch payment details
        requests();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void requests() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PAYMENT_DETAILS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RESPONSE", response);
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String msg = jsonObject.getString("message");
                            if (status.equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("details");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsn = jsonArray.getJSONObject(i);
                                    //requestID = jsn.getString("requestID");
                                    items = jsn.getString("items");
                                    requestDate = jsn.getString("requestDate");
                                    requestStatus = jsn.getString("requestStatus");
                                    quantity = jsn.getString("quantity");
                                    amount = jsn.getString("amount");

                                    // Set the values in the TextViews
                                    tvRequestID.setText("Request ID: " + requestID);
                                    tvItems.setText("Items: " + items);
                                    tvRequestDate.setText("Date: " + requestDate);
                                    tvRequestStatus.setText("Status: " + requestStatus);
                                    tvQuantity.setText("Quantity: " + quantity);
                                    tv_request_amount.setText("Amount: KSH " + amount);
                                }
                            } else {
                                showToast(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showToast(e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                showToast(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("requestID", requestID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void print(){
        btn_printfile.setVisibility(View.GONE);

        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        view.setDrawingCacheEnabled(true);
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),View. MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        PrintHelper photoPrinter = new PrintHelper(this); // Assume that 'this' is your activity
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("print", bitmap);

        btn_printfile.setVisibility(View.VISIBLE);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 250);
        toast.show();
    }
}
