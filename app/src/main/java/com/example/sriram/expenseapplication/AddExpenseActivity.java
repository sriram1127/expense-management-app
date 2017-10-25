package com.example.sriram.expenseapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    List<Expense> expenses = null;
    Expense expense = null;
    EditText expenseNameET = null;
    Spinner categoryS = null;
    EditText amountET = null;
    EditText dateET = null;
    ImageView receiptIV = null;
    String receiptPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        setTitle("Add Expense");
        Spinner categorySpinner = (Spinner) findViewById(R.id.category);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        receiptIV = (ImageView) findViewById(R.id.receipt);

        if (getIntent().getExtras() != null) {
            expenses = getIntent().getExtras().getParcelableArrayList("expenses");
            System.out.print("");
        }

        // ImageView calenderIV = (ImageView) findViewById(R.id.calender);

    }

    @Override
    public void onClick(View v) {
        Log.d("calender", "click");


        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        System.out.println("the selected " + mDay);
        DatePickerDialog dialog = new DatePickerDialog(AddExpenseActivity.this,
                this, mYear, mMonth, mDay);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        EditText dateET = (EditText) findViewById(R.id.date);
        dateET.setText(new StringBuilder()
                .append(monthOfYear + 1).append("/").append(dayOfMonth).append("/")
                .append(year).append(" "));
    }

    public void addButtonFunction(View view) {

        try {

            expenseNameET = (EditText) findViewById(R.id.expenseName);
            String expenseName = expenseNameET.getText().toString();
            if ("".equals(expenseName.trim())) {
                Toast.makeText(this, getResources().getString(R.string.error_name), Toast.LENGTH_SHORT).show();
                return;
            }
            categoryS = (Spinner) findViewById(R.id.category);
            String category = categoryS.getSelectedItem().toString();
            if ("Select Category".equals(category.trim())) {
                Toast.makeText(this, getResources().getString(R.string.error_category), Toast.LENGTH_SHORT).show();
                return;
            }
            Integer categoryId = categoryS.getSelectedItemPosition();
            amountET = (EditText) findViewById(R.id.amount);
            if ("".equals(amountET.getText().toString().trim())) {
                Toast.makeText(this, getResources().getString(R.string.error_amount), Toast.LENGTH_SHORT).show();
                return;
            }
            Double amount = Double.parseDouble(amountET.getText().toString());
            dateET = (EditText) findViewById(R.id.date);
            String date = dateET.getText().toString();
            if ("".equals(date.trim())) {
                Toast.makeText(this, getResources().getString(R.string.error_date), Toast.LENGTH_SHORT).show();
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date expenseDate = sdf.parse(date);
                Date currentDate = new Date();
                if (expenseDate.compareTo(currentDate) > 0) {
                    Toast.makeText(this, getResources().getString(R.string.wrong_date), Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

           /* if (receiptPath == null) {
                Toast.makeText(this, getResources().getString(R.string.error_image), Toast.LENGTH_SHORT).show();
                return;
            }*/
            expense = new Expense(expenseName, category, categoryId, amount, date, receiptPath);
            expenses.add(expense);
            Intent addIntent = new Intent();
            addIntent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
            setResult(Activity.RESULT_OK, addIntent);
            finish();
        } catch (NullPointerException e) {
            Log.d(e.toString(), "Null Pointer Exception Occured");
        } catch (Exception e) {
            Log.d(e.toString(), "Exception Occured");
        }
    }

    public void browseImage(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 5) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap receiptImg = extras.getParcelable("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                receiptImg.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                receiptPath = MediaStore.Images.Media.insertImage(getBaseContext().getContentResolver(), receiptImg, "Title", null);
                receiptIV.setImageURI(Uri.parse(receiptPath));
            }
        }
    }
}

