package com.example.bhagy.oolsum.activities;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bhagy.oolsum.R;
import com.example.bhagy.oolsum.utils.PrefManager;
import com.example.bhagy.oolsum.utils.ProfileImageTransform;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

    static final int CAM_REQUEST = 1;
    private static final int RESULT_LOAD_IMAGE = 0;
    String[] listHealthConditions;
    boolean[] checkedHealthConditions;
    ArrayList<Integer> diseases = new ArrayList<>();
    private ImageView iv_profile_view, iv_camera, iv_gallery_view;
    private TextInputLayout datePickerLayout;
    private AppCompatEditText et_username, et_height_name, et_weight, et_date_of_birth, et_mobile_number, et_residence_number, et_address;
    private Spinner spn_meter, spn_kilogram, spn_gender, spn_marital_status, spn_food_status, spn_nature_of_work,
            spn_job_status, spn_goal;
    private TextView tv_age_info, tv_gender, tv_marital_status, tv_email, tv_food_status, tv_nature_of_work,
            tv_job_status, tv_goal, tv_health_conditions, mandatory_status;
    private Button btn_add_health_conditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        findViews();

        listHealthConditions = getResources().getStringArray(R.array.Health_Conditions);
        checkedHealthConditions = new boolean[listHealthConditions.length];
    }

    public void findViews() {
        iv_profile_view = findViewById(R.id.iv_profile_view);
        iv_camera = findViewById(R.id.iv_camera);
        iv_gallery_view = findViewById(R.id.iv_gallery_view);
        et_username = findViewById(R.id.et_username);
        et_height_name = findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);

        et_date_of_birth = findViewById(R.id.et_date_of_birth);
        et_mobile_number = findViewById(R.id.et_mobile_number);
        et_residence_number = findViewById(R.id.et_residence_number);
        et_address = findViewById(R.id.et_address);
        spn_meter = findViewById(R.id.spn_height_units);
        spn_kilogram = findViewById(R.id.spn_weight_units);
        spn_gender = findViewById(R.id.spn_gender);
        spn_marital_status = findViewById(R.id.spn_marital_status);
        spn_food_status = findViewById(R.id.spn_food_status);
        spn_nature_of_work = findViewById(R.id.spn_nature_of_work);
        spn_job_status = findViewById(R.id.spn_job_status);
        spn_goal = findViewById(R.id.spn_goal);
        tv_age_info = findViewById(R.id.tv_age_info);
        tv_gender = findViewById(R.id.tv_gender);
        tv_marital_status = findViewById(R.id.tv_marital_status);
        tv_email = findViewById(R.id.tv_email);
        tv_food_status = findViewById(R.id.tv_food_status);
        tv_nature_of_work = findViewById(R.id.tv_nature_of_work);
        tv_job_status = findViewById(R.id.tv_job_status);
        tv_goal = findViewById(R.id.tv_goal);
        tv_health_conditions = findViewById(R.id.tv_health_conditions);
        mandatory_status = findViewById(R.id.mandatory_status);
        btn_add_health_conditions = findViewById(R.id.btn_add_health_conditions);

        setListenersForDate(et_date_of_birth);
        iv_gallery_view.setOnClickListener(this);
        iv_camera.setOnClickListener(this);

        AddingMedicalConditions();
    }

    public void AddingMedicalConditions() {
        btn_add_health_conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditProfileActivity.this);
                mBuilder.setTitle(R.string.medical_conditions);
                mBuilder.setMultiChoiceItems(listHealthConditions, checkedHealthConditions, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!diseases.contains(position)) {
                                diseases.add(position);
                            }
                        } else if (diseases.contains(position)) {
                            diseases.remove(Integer.valueOf(position));
                        }

                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        StringBuilder health = new StringBuilder();
                        for (int i = 0; i < diseases.size(); i++) {
                            health.append(listHealthConditions[diseases.get(i)]);
                            if (i != diseases.size() - 1) {
                                health.append("\n");
                            }
                        }

                        tv_health_conditions.setText(health.toString());
                    }
                });
                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedHealthConditions.length; i++) {
                            checkedHealthConditions[i] = false;
                            diseases.clear();
                            tv_health_conditions.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_gallery_view:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                i.setType("image/*");
                i.putExtra("crop", "true");
                i.putExtra("outputX", 200);
                i.putExtra("outputY", 200);
                i.putExtra("aspectX", 1);
                i.putExtra("aspectY", 1);
                i.putExtra("scale", true);
                i.putExtra(MediaStore.EXTRA_OUTPUT, true);
                i.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                startActivityForResult(i, RESULT_LOAD_IMAGE);
                break;

            case R.id.iv_camera:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAM_REQUEST);

                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            Picasso.get().load(selectedImage).transform(new ProfileImageTransform()).noPlaceholder().
                    resize(200, iv_profile_view.getMinimumHeight()).into(iv_profile_view);

        } else if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv_profile_view.setImageBitmap(imageBitmap);
        }
    }

    public void setListenersForDate(final AppCompatEditText dateOfBirth) {
        dateOfBirth.setInputType(InputType.TYPE_NULL);
        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setDateOfBirth(dateOfBirth);
                }
            }
        });

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateOfBirth(dateOfBirth);
            }
        });
    }

    public void setDateOfBirth(final AppCompatEditText dateOfBirth) {
        final Calendar currentDate = Calendar.getInstance();
        int mYear = currentDate.get(Calendar.YEAR);
        int mMonth = currentDate.get(Calendar.MONTH);
        int mDay = currentDate.get(Calendar.DAY_OF_MONTH);
        final String myFormat = "dd MMM yyyy";
        DatePickerDialog mDatePicker = new DatePickerDialog(EditProfileActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        currentDate.set(Calendar.YEAR, year);
                        currentDate.set(Calendar.MONTH, month);
                        currentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        dateOfBirth.setText(sdf.format(currentDate.getTime()));
                    }
                }, mYear, mMonth, mDay);

        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.save_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_profile:
              /*  ProgressDialog dialog = ProgressDialog.show(EditProfileActivity.this, "",
                        "Loading...");
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMax(500);
                dialog.show();
                dialog.setCancelable(false);*/
                saveUserSharedPreferences();
                //   dialog.dismiss();
                Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveUserSharedPreferences() {
        PrefManager.putSharedPreferencesString(this, "UserName", et_username.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Height", et_height_name.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Height Measurement", spn_meter.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Weight ", et_weight.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Weight Measurement ", spn_kilogram.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "DateOfBirth ", et_date_of_birth.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Age ", tv_age_info.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Gender ", spn_gender.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "MaritalStatus ", spn_marital_status.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Email ", tv_email.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Mobile Number ", et_mobile_number.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Residence Number ", et_residence_number.getText().toString());
        PrefManager.putSharedPreferencesString(this, "Address ", et_address.getText().toString());
        PrefManager.putSharedPreferencesString(this, "FoodStatus ", spn_food_status.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Nature Of Work ", spn_nature_of_work.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Job Status ", spn_job_status.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Goal ", spn_goal.getSelectedItem().toString());
        PrefManager.putSharedPreferencesString(this, "Health Conditions ", tv_health_conditions.getText().toString());

    }

}