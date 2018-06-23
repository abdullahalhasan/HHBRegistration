package com.droidsoftbd.hhbregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.droidsoftbd.hhbregistration.Classes.DBManager;
import com.droidsoftbd.hhbregistration.Classes.DatePickerFragment;
import com.droidsoftbd.hhbregistration.Classes.NetworkStateChecker;
import com.droidsoftbd.hhbregistration.Classes.UserInfo;
import com.droidsoftbd.hhbregistration.Classes.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText fullNameET;
    private EditText ageET;
    private EditText mobileNumberET;
    private EditText bloodDonationDateET;
    private EditText fatherNameET;
    private EditText presentAddressET;
    private EditText permanentAddressET;
    private EditText emailFbIDET;
    private EditText otherDivisionET;
    private EditText otherOccupationET;

    private TextInputLayout otherDivisionLayout;
    private TextInputLayout otherOccupationLayout;

    private Spinner bloodGroupSpinner;
    private ArrayAdapter<CharSequence> bldGrpSpnAdepter;
    private Spinner divisionSpinner;
    private ArrayAdapter<CharSequence> divSpnAdepter;
    private Spinner occupationSpinner;
    private ArrayAdapter<CharSequence> ocpSpnAdepter;

    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioButton regularButton;
    private RadioButton occasionalButton;

    private Button submitButton;

    private String fullName;
    private String gender;
    private String bloodGroup;
    private String age;
    private String mobileNumber;
    private String lastBloodDonateDate;
    private String division;
    private String occupation;
    private String fatherName;
    private String presentAddress;
    private String permanentAddress;
    private String emailFbID;
    private String donnerType;
    private String syncStatusYes = "yes";
    private String syncStatusNo = "no";

    private ProgressDialog progressDialog;




    DBManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerReceiver(new NetworkStateChecker(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        init();
        maleButton.setChecked(true);
        femaleButton.setChecked(false);
        gender = "male";

        regularButton.setChecked(true);
        occasionalButton.setChecked(false);
        donnerType = "regular";

        otherDivisionLayout.setVisibility(View.INVISIBLE);
        otherOccupationLayout.setVisibility(View.INVISIBLE);

        bldGrpSpnAdepter = ArrayAdapter.createFromResource(this,R.array.bld_grp_array,R.layout.custom_spinner_view);
        bldGrpSpnAdepter.setDropDownViewResource(R.layout.custom_spinner_dropdown_view);
        bloodGroupSpinner.setAdapter(bldGrpSpnAdepter);
        bloodGroupSpinner.setOnItemSelectedListener(this);

        divSpnAdepter = ArrayAdapter.createFromResource(this,R.array.division_array,R.layout.custom_spinner_view);
        divSpnAdepter.setDropDownViewResource(R.layout.custom_spinner_dropdown_view);
        divisionSpinner.setAdapter(divSpnAdepter);
        divisionSpinner.setOnItemSelectedListener(this);

        ocpSpnAdepter = ArrayAdapter.createFromResource(this,R.array.occupation_array,R.layout.custom_spinner_view);
        ocpSpnAdepter.setDropDownViewResource(R.layout.custom_spinner_dropdown_view);
        occupationSpinner.setAdapter(ocpSpnAdepter);
        occupationSpinner.setOnItemSelectedListener(this);

    }

    private void init() {
        fullNameET = findViewById(R.id.full_name_tv);
        ageET = findViewById(R.id.age_tv);
        mobileNumberET = findViewById(R.id.mobile_number_tv);
        bloodDonationDateET = findViewById(R.id.last_date_donate);
        fatherNameET = findViewById(R.id.father_name);
        presentAddressET = findViewById(R.id.present_address);
        permanentAddressET = findViewById(R.id.permanent_address);
        emailFbIDET = findViewById(R.id.email_fb);
        bloodDonationDateET = findViewById(R.id.last_date_donate);
        otherDivisionET = findViewById(R.id.other_division_ET);
        otherOccupationET = findViewById(R.id.other_occupation_ET);
        maleButton = findViewById(R.id.radioMale);
        femaleButton = findViewById(R.id.radioFemale);
        regularButton = findViewById(R.id.radioRegular);
        occasionalButton = findViewById(R.id.radioOccasional);

        otherDivisionLayout = findViewById(R.id.other_division_layout);
        otherOccupationLayout =findViewById(R.id.other_occupation_layout);

        bloodGroupSpinner =findViewById(R.id.blood_group_spinner);
        divisionSpinner = findViewById(R.id.division_spinner);
        occupationSpinner = findViewById(R.id.occupation_spinner);

        submitButton = findViewById(R.id.submit_button);
        userManager = new DBManager(MainActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.view_list_menu:
                startActivity(new Intent(this,UserListActivity.class));
                break;
            case R.id.rating_app_menu:
                //startActivity(new Intent(this,UserListActivity.class));
                Toast.makeText(this, "Rating App Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share_menu_menu:
                //startActivity(new Intent(this,UserListActivity.class));
                Toast.makeText(this, "Share Menu Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_menu:
                startActivity(new Intent(this,AboutActivity.class));
                Toast.makeText(this, "About Menu Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sync_menu:
                //syncDB();
                Toast.makeText(this, "Sync Menu Clicked", Toast.LENGTH_SHORT).show();
                break;
        }/*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.view_list_menu) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    public void radioGroup(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioMale:
                if (checked)
                    femaleButton.setChecked(false);
                gender = "female";
                break;
            case R.id.radioFemale:
                if (checked)
                    maleButton.setChecked(false);
                gender = "male";
                break;
        }
    }

    public void radioRegularity(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioRegular:
                if (checked)
                    occasionalButton.setChecked(false);
                    donnerType = "regular";
                    break;
            case R.id.radioOccasional:
                if (checked)
                    regularButton.setChecked(false);
                    donnerType = "occasional";
                    break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.blood_group_spinner :
                bloodGroup = parent.getItemAtPosition(position).toString();
                break;
            case R.id.division_spinner :
                division = parent.getItemAtPosition(position).toString();
                if (division.contains("Other")) {
                    otherDivisionLayout.setVisibility(View.VISIBLE);
                } else {
                    otherDivisionLayout.setVisibility(View.INVISIBLE);
                    otherDivisionET.getText().clear();
                }
                break;
            case R.id.occupation_spinner :
                occupation = parent.getItemAtPosition(position).toString();
                if (occupation.contains("Other")) {
                    otherOccupationLayout.setVisibility(View.VISIBLE);
                } else {
                    otherOccupationLayout.setVisibility(View.INVISIBLE);
                    otherOccupationET.getText().clear();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setValues(){
        fullName = fullNameET.getText().toString().trim();
        age = ageET.getText().toString().trim();
        mobileNumber = mobileNumberET.getText().toString().trim();
        fatherName = fatherNameET.getText().toString().trim();
        presentAddress = presentAddressET.getText().toString().trim();
        permanentAddress = permanentAddressET.getText().toString().trim();
        emailFbID = emailFbIDET.getText().toString().trim();
        lastBloodDonateDate = bloodDonationDateET.getText().toString().trim();
        if (division.contains("Other")) {
            division = otherDivisionET.getText().toString().trim();
        }
        if (occupation.contains("Other")) {
            occupation = otherOccupationET.getText().toString().trim();
        }

        //Toast.makeText(this,division+" "+occupation,Toast.LENGTH_LONG).show();
    }
    private void clear() {
        fullNameET.getText().clear();
        ageET.getText().clear();
        mobileNumberET.getText().clear();
        fatherNameET.getText().clear();
        presentAddressET.getText().clear();
        permanentAddressET.getText().clear();
        emailFbIDET.getText().clear();
        bloodDonationDateET.getText().clear();
        otherDivisionET.getText().clear();
        otherOccupationET.getText().clear();
    }

    public void submitForm(View view) {


        if (fullNameET.getText().toString().isEmpty() || ageET.getText().toString().isEmpty() || mobileNumberET.getText().toString().isEmpty()
                || permanentAddressET.getText().toString().isEmpty() || presentAddressET.getText().toString().isEmpty()
                || bloodDonationDateET.getText().toString().isEmpty()|| emailFbIDET.getText().toString().isEmpty()) {


            Toast.makeText(this, "Please Fillup All Fields.", Toast.LENGTH_SHORT).show();

        } else {
            if ((occupation.contains("Other") && otherOccupationET.getText().toString().isEmpty())
                    || (division.contains("Other") && otherDivisionET.getText().toString().isEmpty())) {
                if (otherOccupationET.getText().toString().isEmpty()) {
                    otherOccupationET.setError("Required Field!");
                }
                if (otherDivisionET.getText().toString().isEmpty()) {
                    otherDivisionET.setError("Required Field!");
                }
            } else {
                if (fatherNameET.getText().toString().isEmpty()) {
                    fatherName = "";
                }
                Log.e("MainActivity","submit form");
                setValues();
                saveNameToServer();

            }
        }
    }

    public void datePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void saveNameToServer() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving Data...");
        progressDialog.show();

//        final String name = editTextName.getText().toString().trim();
        Log.e("MainAtc","savetoser");
        String URL_SAVE_NAME = "https://helpinghandbd.org/app/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE_NAME,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        Log.e("SaveToServer",response);
//                        if (response != null && response.length() > 0) {
                            try {
                                JSONObject obj = new JSONObject(response);
                                if (!obj.getBoolean("error")) {
                                    //if there is a success
                                    //storing the name to sqlite with status synced
                                    Log.e("MainActivity", "On Response if not error: "+obj.toString());
                                    saveToLocalStorage(syncStatusYes);
                                } else {
                                    //if there is some error
                                    //saving the name to sqlite with status unsynced
                                    Log.e("MainActivity", "On Response else not error: "+obj.toString());
                                    saveToLocalStorage(syncStatusNo);
                                }
                            } catch (JSONException e) {
                                Log.e("MainActivity", "" + e);
                                e.printStackTrace();
                            }
                        }

                    //}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //on error storing the name to sqlite with status unsynced
                        Log.e("MainActivity","vollee error - "+error);
                        saveToLocalStorage(syncStatusNo);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("name", fullName);
                params.put("gender", gender);
                params.put("bloodGroup", bloodGroup);
                params.put("donnerType", donnerType);
                params.put("age", age);
                params.put("mobileNumber", mobileNumber);
                params.put("lastBloodDonateDate", lastBloodDonateDate);
                params.put("division", division);
                params.put("occupation", occupation);
                params.put("fatherName", fatherName);
                params.put("presentAddress", presentAddress);
                params.put("permanentAddress", permanentAddress);
                params.put("emailFbID", emailFbID);
                Log.e("MainParam",params.toString());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        Log.e("MainActivity",""+stringRequest);
    }

    private void saveToLocalStorage(String status) {

        UserInfo newUser = new UserInfo(fullName, gender, bloodGroup, age, mobileNumber, lastBloodDonateDate, division,
                occupation, fatherName, presentAddress, permanentAddress, emailFbID, donnerType, status);
        Log.e("MainActivity","Save to local - "+newUser);
        boolean added = userManager.addUserInfo(newUser);
        if (added) {
            Toast.makeText(this, "Congratulation!! Successfully Added!!!", Toast.LENGTH_SHORT).show();
            clear();
        } else {
            Toast.makeText(this, "Can't Save Data", Toast.LENGTH_SHORT).show();
        }

    }

}