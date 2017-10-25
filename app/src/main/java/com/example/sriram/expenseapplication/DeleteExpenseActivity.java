package com.example.sriram.expenseapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteExpenseActivity extends AppCompatActivity {
    List<Expense> expenses = null;
    Expense expense = null;
    int deleteExpenseId;
    EditText expenseNameET = null;
    Spinner categoryS = null;
    EditText amountET = null;
    EditText dateET = null;
    ImageView receiptIV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_expense);
        setTitle("Delete Expense");
        if (getIntent().getExtras() != null) {
            expenses = getIntent().getExtras().getParcelableArrayList("expenses");
        }
        Spinner categorySpinner = (Spinner) findViewById(R.id.category);
        categorySpinner.setEnabled(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        expenseNameET = (EditText) findViewById(R.id.expenseName);
        categoryS = (Spinner) findViewById(R.id.category);
        amountET = (EditText) findViewById(R.id.amount);
        dateET = (EditText) findViewById(R.id.date);
        receiptIV = (ImageView) findViewById(R.id.receipt);
    }

    public void selectExpenseToDelete(View view) {
        String[] expenseNames = new String[expenses.size()];
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Pick An Expense");
        for (int i = 0; i < expenseNames.length; i++) {
            expenseNames[i] = expenses.get(i).getName();
        }

        b.setItems(expenseNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteExpenseId = which;
                expense = (Expense) expenses.get(deleteExpenseId);
                expenseNameET.setText(expense.getName());
                categoryS.setSelection(expense.getCategoryId());
                amountET.setText(expense.getAmount().toString());
                dateET.setText(expense.getDate());
                if(expense.getReceiptPath() != null)
                receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
            }
        });
        b.show();
    }


    public void DeleteExpense(View view) {
        expenses.remove(deleteExpenseId);
        Intent addIntent = new Intent();
        addIntent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        setResult(Activity.RESULT_OK, addIntent);
        finish();
    }

    public void CancelDeleteExpense(View view) {
        Intent addIntent = new Intent();
        addIntent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        setResult(Activity.RESULT_OK, addIntent);
        finish();
    }
}
