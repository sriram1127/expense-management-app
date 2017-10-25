package com.example.sriram.expenseapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    List<Expense> expenses = null;
    private static final int ADD_EXPENSE_REQUEST_CODE = 1;
    private static final int EDIT_EXPENSE_REQUEST_CODE = 2;
    private static final int DELETE_EXPENSE_REQUEST_CODE = 3;
    //private static final int SHOW_EXPENSE_REQUEST_CODE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Expense App");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        expenses = new ArrayList<Expense>();

    }

    @Override
    public void onClick(View v) {
        Log.d("button clicked", "btn");

        Intent intent = new Intent(this.getBaseContext(), AddExpenseActivity.class);
        intent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        startActivityForResult(intent, ADD_EXPENSE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EXPENSE_REQUEST_CODE || requestCode == EDIT_EXPENSE_REQUEST_CODE || requestCode == DELETE_EXPENSE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    if (data.getExtras() != null)
                        expenses = (List<Expense>) data.getExtras().get("expenses");
                } catch (Exception e) {
                    Log.d(e.toString(), "");
                }
                Log.d("Expenses", expenses.size() + "");
            }
        }
    }

    public void editExpenseOnClick(View v) {
        Log.d("editExpenseOnClick", "btn");

        Log.d("button clicked", "btn");
        Intent intent = new Intent(this.getBaseContext(), EditExpenseActivity.class);
        intent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        startActivityForResult(intent, EDIT_EXPENSE_REQUEST_CODE);
    }

    public void deleteExpenseOnClick(View v) {
        Log.d("deleteExpenseOnClick", "btn");
        Intent intent = new Intent(this.getBaseContext(), DeleteExpenseActivity.class);
        intent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        startActivityForResult(intent, DELETE_EXPENSE_REQUEST_CODE);
    }


    public void showExpensesOnClick(View v) {
        Log.d("showExpensesOnClick", "btn");
        Intent intent = new Intent(this.getBaseContext(), ShowExpensesActivity.class);
        intent.putParcelableArrayListExtra("expenses", (ArrayList<? extends Parcelable>) expenses);
        startActivity(intent);
    }


    public void FinishOnClick(View v) {
        Log.d("FinishOnClick", "btn");

        finish();
        System.exit(0);
    }


}
