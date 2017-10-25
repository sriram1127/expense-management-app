package com.example.sriram.expenseapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShowExpensesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nameET;
    TextView categoryET;
    TextView amountET;
    TextView dateET;
    List<Expense> expenses = null;
    Expense expense;
    int noOfExpenses;
    ImageView receiptIV = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses);
        setTitle("Show Expense");
        nameET = (TextView) findViewById(R.id.expenseName);
        categoryET = (TextView) findViewById(R.id.category);
        amountET = (TextView) findViewById(R.id.amount);
        dateET = (TextView) findViewById(R.id.date);
        receiptIV = (ImageView) findViewById(R.id.receipt);

        if (getIntent().getExtras() != null) {
            expenses = getIntent().getExtras().getParcelableArrayList("expenses");
            if (expenses.size() > 0) {
                expense = expenses.get(0);
                nameET.setText(expense.getName());
                categoryET.setText(expense.getCategory());
                amountET.setText(expense.getAmount().toString());
                dateET.setText(expense.getDate());
                if(expense.getReceiptPath() != null)
                receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
                noOfExpenses = expenses.size();
            }
        }


    }

    @Override
    public void onClick(View v) {

    }


    public void firstShowExpense(View v) {
        if (noOfExpenses != 0) {
            expense = expenses.get(0);
            nameET.setText(expense.getName());
            categoryET.setText(expense.getCategory());
            amountET.setText(expense.getAmount().toString());
            receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
            dateET.setText(expense.getDate());
        }
    }

    public void showPreviousExpenes(View v) {
        if (noOfExpenses != 0) {
            int index = expenses.indexOf(expense) - 1;
            if (0 <= index && index < noOfExpenses) {
                expense = expenses.get(index);
                nameET.setText(expense.getName());
                categoryET.setText(expense.getCategory());
                amountET.setText(expense.getAmount().toString());
                receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
                dateET.setText(expense.getDate());
            }
        }
    }

    public void finish(View v) {
        finish();
    }

    public void showNextExpense(View v) {
        if (noOfExpenses != 0) {
            int index = expenses.indexOf(expense) + 1;
            if (0 <= index && index < noOfExpenses) {
                expense = expenses.get(index);
                nameET.setText(expense.getName());
                categoryET.setText(expense.getCategory());
                amountET.setText(expense.getAmount().toString());
                receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
                dateET.setText(expense.getDate());
            }
        }
    }

    public void showLastExpense(View v) {
        if (noOfExpenses != 0) {
            expense = expenses.get(noOfExpenses - 1);
            nameET.setText(expense.getName());
            categoryET.setText(expense.getCategory());
            amountET.setText(expense.getAmount().toString());
            receiptIV.setImageURI(Uri.parse(expense.getReceiptPath()));
            dateET.setText(expense.getDate());
        }
    }
}
