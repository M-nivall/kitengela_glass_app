package com.example.Varsani.Staff.Technician;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Varsani.Clients.Models.UserModel;
import com.example.Varsani.Staff.Adapters.AdapterQuotItems;
import com.example.Varsani.Staff.Models.ClientItemsModal;
import com.example.Varsani.R;
import com.example.Varsani.Staff.Adapters.AdapterClientItems;
import com.example.Varsani.utils.SessionHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.Varsani.utils.Urls.URL_GET_CLIENT_ITEMS;
import static com.example.Varsani.utils.Urls.URL_MARK_ORDER;
import static com.example.Varsani.utils.Urls.URL_QUOTATION_ITEMS;
import static com.example.Varsani.utils.Urls.URL_SEND_QUOTATION;

public class VisitItems extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar,progressBar1;
    private TextView txv_name,txv_orderID,txv_orderStatus,
            txv_address,txv_town, txv_county;
   // private EditText edt_amount;
    private TextView edt_materials;
    private List<ClientItemsModal> list;
    private AdapterQuotItems adapterQuotItems;
    private SessionHandler session;
    private UserModel user;
    private RelativeLayout layout_bottom;
    private LinearLayout layoutmaterials;
    private EditText edt_service_fee, edt_service_notes;
    private Button btn_submit;
    private RadioGroup radioGroup;
    private RadioButton rb_no_materials,rb_yes_materials;
    private ArrayList<String> drivers;
    private CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;

    private String orderID ,amount, materials;
    private String orderStatus;
    int mcost=0;
    int grandtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_items);

        getSupportActionBar().setSubtitle("Fill Form");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout_bottom = findViewById(R.id.layout_bottom);
        progressBar = findViewById(R.id.progressBar);
        progressBar1 = findViewById(R.id.progressBar1);
        recyclerView = findViewById(R.id.recyclerView);
        txv_address = findViewById(R.id.txv_address);
        txv_town = findViewById(R.id.txv_town);
        txv_county = findViewById(R.id.txv_county);
        txv_name = findViewById(R.id.txv_name);
        txv_orderStatus = findViewById(R.id.txv_orderStatus);
        txv_orderID = findViewById(R.id.txv_orderID);
        edt_service_fee = findViewById(R.id.edt_service_fee);
        edt_service_notes = findViewById(R.id.edt_service_notes);
        btn_submit = findViewById(R.id.btn_submit);

        edt_materials = findViewById(R.id.edt_materials);
        radioGroup = findViewById(R.id.rb_materials);
        rb_no_materials = findViewById(R.id.rb_no_materials);
        rb_yes_materials = findViewById(R.id.rb_yes_materials);
        layoutmaterials = findViewById(R.id.materialslayout);
        //Getting instance of CheckBoxes and Button from the activty_main.xml file
        chk1 = (CheckBox) findViewById(R.id.checkBox);
        chk2 = (CheckBox) findViewById(R.id.checkBox2);
        chk3 = (CheckBox) findViewById(R.id.checkBox3);
        chk4 = (CheckBox) findViewById(R.id.checkBox4);
        chk5 = (CheckBox) findViewById(R.id.checkBox5);
        chk6 = (CheckBox) findViewById(R.id.checkBox6);

        session=new SessionHandler(getApplicationContext());
        user=session.getUserDetails();

        radioGroup.clearCheck();
        layoutmaterials.setVisibility(View.GONE);
        edt_materials.setVisibility(View.GONE);

        rb_no_materials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutmaterials.setVisibility(View.GONE);

            }
        });
        rb_yes_materials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutmaterials.setVisibility(View.VISIBLE);
                edt_materials.setVisibility(View.VISIBLE);

            }
        });

        StringBuilder result=new StringBuilder();
        StringBuilder materials=new StringBuilder();

        //result.append("Selected Materials:");


        //Displaying the message on the toast
        //Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();

        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk1.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Power drill");
                    edt_materials.setText(materials);
                }
            }
        });

        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk2.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Ceiling plate");
                    edt_materials.setText(materials);
                }
            }
        });

        chk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk3.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Tape measure");
                    edt_materials.setText(materials);
                }
            }
        });

        chk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk4.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Hammer");
                    edt_materials.setText(materials);
                }
            }
        });

        chk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk5.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Sealant");
                    edt_materials.setText(materials);
                }
            }
        });

        chk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chk5.isChecked()) {
                    if (materials.length() > 0) {
                        materials.append(", ");
                    }
                    materials.append("Junction box");
                    edt_materials.setText(materials);
                }
            }
        });

        layout_bottom.setVisibility(View.GONE);
        progressBar1.setVisibility(View.GONE);
//        edt_driver.setEnabled(false);

        drivers=new ArrayList<>();

        Intent intent=getIntent();

        orderID=intent.getStringExtra("orderID");
        orderStatus=intent.getStringExtra("orderStatus");
        String clientName=intent.getStringExtra("clientName");
        String address=intent.getStringExtra("address");
        String town=intent.getStringExtra("town");
        String county=intent.getStringExtra("county");


        txv_orderStatus.setText("Order status " +orderStatus );
        txv_name.setText("Name " +clientName );
        txv_town.setText("Town " +town );
        txv_county.setText("County " +county );
        txv_address.setText("Address " +address );
        txv_orderID.setText("Order ID " +orderID );

        list=new ArrayList<>();

        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAlert(v);
            }
        });

        getClientItems();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void getClientItems(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_QUOTATION_ITEMS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("RESPONSE", response);
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String msg=jsonObject.getString("message");

                            if(status.equals("1")){
                                JSONArray jsonArray=jsonObject.getJSONArray("details");
                                for(int i=0; i <jsonArray.length();i++){
                                    JSONObject jsn=jsonArray.getJSONObject(i);
                                    String itemName=jsn.getString("itemName");
                                    String quantity=jsn.getString("quantity");
                                    String itemPrice=jsn.getString("itemPrice");
                                    String subTotal=jsn.getString("subTotal");
                                    ClientItemsModal clientItemsModal=new ClientItemsModal(itemName,itemPrice,quantity,subTotal);
                                    list.add(clientItemsModal);
                                }
                                adapterQuotItems =new AdapterQuotItems(getApplicationContext(),list);
                                recyclerView.setAdapter(adapterQuotItems);
                                progressBar.setVisibility(View.GONE);
                                if(orderStatus.equals("Proceed To Quotation")){
                                    layout_bottom.setVisibility(View.VISIBLE);
                                }

                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast toast=Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP,0,250);
                                toast.show();
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                            Toast toast=Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP,0,250);
                            toast.show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast toast=Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,250);
                toast.show();
            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("orderID",orderID);
                Log.e("Params",""+ params);
                return  params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void markOrder(){
        progressBar1.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);

        final String service_notes = edt_service_notes.getText().toString().trim();
        final String service_fee = edt_service_fee.getText().toString().trim();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_SEND_QUOTATION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("RESPONSE",response);
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String msg=jsonObject.getString("message");
                            if (status.equals("1")) {

                                Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP, 0, 250);
                                toast.show();
                                finish();
                            }
                            else{

                                Toast toast= Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP,0,250);
                                toast.show();
                                progressBar1.setVisibility(View.GONE);
                                btn_submit.setVisibility(View.VISIBLE);
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                            Toast toast= Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP,0,250);
                            toast.show();

                            progressBar1.setVisibility(View.GONE);
                            btn_submit.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast toast= Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,250);
                toast.show();
                progressBar1.setVisibility(View.GONE);
                btn_submit.setVisibility(View.VISIBLE);
            }
        }){
            @Override
            protected Map<String,String>getParams()throws AuthFailureError{
                Map<String,String> params=new HashMap<>();
                params.put("orderID",orderID);
                params.put("serviceFee",service_fee);
                params.put("serviceNotes",service_notes);
                params.put("materials",materials);
                params.put("empID",user.getClientID());
                Log.e("PARAMS",""+params);
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void getAlert(View v){
        materials= edt_materials.getText().toString();

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Send Quotation Now!!!");
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                    markOrder();


                return;
            }
        });
        builder.show();

    }
}
