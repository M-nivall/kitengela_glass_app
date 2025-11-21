package com.example.Varsani.Suppliers;

import static com.example.Varsani.utils.Urls.URL_ACCEPT;
import static com.example.Varsani.utils.Urls.URL_BID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Varsani.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Accept extends AppCompatActivity {

    private TextView txv_requestID, txv_items, txv_requestDate, txv_requestStatus, txt_unitprice, txt_quantity;
    private Button btn_bid;
    private ProgressBar progressBar;
    private EditText edt_unit_price_input;

    private String requestID, product;
    private int unitprice, quantity;

    // ***************************************
    // PRODUCT PRICE MAPPING (Cleaned)
    // ***************************************
    private int getProductUnitPrice(String productName) {

        switch (productName) {
            case "Blown-glass-vese": return 2500;
            case "Blown-glass-jug": return 1700;
            case "Garden-jewellery": return 2000;
            case "Beady-chandelier": return 12500;
            case "Light-cups-chandeliar": return 15000;
            case "Wall-brackets": return 4500;
            case "Gurden-gurdian": return 3000;
            case "Propellor-bowl": return 950;
            case "Armchair": return 4500;
            case "Dalle-stool": return 4500;
            default: return 0;
        }
    }

    // ***************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // UI elements
        txv_items = findViewById(R.id.txv_items);
        txv_requestID = findViewById(R.id.txv_requestID);
        txv_requestStatus = findViewById(R.id.txv_requestStatus);
        txv_requestDate = findViewById(R.id.txv_requestDate);
        txt_unitprice = findViewById(R.id.unitprice);
        txt_quantity = findViewById(R.id.request_quantity);
        edt_unit_price_input = findViewById(R.id.edt_unit_price_input);
        btn_bid = findViewById(R.id.btn_bid);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Receive Intent data
        Intent in = getIntent();
        requestID = in.getStringExtra("requestID");
        quantity = Integer.parseInt(in.getStringExtra("quantity"));
        product = in.getStringExtra("item");

        txv_requestID.setText("ID: " + requestID);
        txv_items.setText("Item: " + product);
        txv_requestDate.setText("Date: " + in.getStringExtra("requestDate"));
        txv_requestStatus.setText("Status: " + in.getStringExtra("requestStatus"));
        txt_quantity.setText("Quantity: " + quantity + " units");

        // Set unit price
        unitprice = getProductUnitPrice(product);
        txt_unitprice.setText("Selling Price: KES " + unitprice + " / unit");

        // Now submit will validate bid
        btn_bid.setOnClickListener(v -> validateBid());
    }

    // Toolbar back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    // ***************************************
    // VALIDATE THE BID PRICE
    // ***************************************
    private void validateBid() {
        String entered = edt_unit_price_input.getText().toString().trim();

        if (entered.isEmpty()) {
            Toast.makeText(this, "Enter your bid price", Toast.LENGTH_SHORT).show();
            return;
        }

        int bid = Integer.parseInt(entered);

        if (bid > unitprice) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Bid");
            builder.setMessage("Your entered price is higher than the selling price.\n\nSelling price = KES " + unitprice);
            builder.setPositiveButton("OK", null);
            builder.show();
            return;
        }

        // Bid is valid
        alertApprove(bid);
    }

    // ***************************************
    // CONFIRMATION
    // ***************************************
    public void alertApprove(int bidPrice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Submit your bid of KES " + bidPrice + "?");
        builder.setCancelable(false);
        builder.setPositiveButton("Submit", (dialog, which) -> approve(bidPrice));
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    // ***************************************
    // SEND BID TO SERVER (ONLY requestID + bid)
    // ***************************************
    public void approve(int bidPrice) {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BID,
                response -> {
                    progressBar.setVisibility(View.GONE);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        String msg = jsonObject.getString("message");

                        Toast toast = Toast.makeText(Accept.this, msg, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 250);
                        toast.show();

                        if (status.equals("1")) finish();

                    } catch (Exception e) {
                        Toast.makeText(Accept.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Accept.this, error.toString(), Toast.LENGTH_LONG).show();
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("requestID", requestID);
                params.put("bid_price", String.valueOf(bidPrice));  // ONLY BID PRICE SENT

                Log.e("PARAMS", "" + params);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
