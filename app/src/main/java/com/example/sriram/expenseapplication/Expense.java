package com.example.sriram.expenseapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SRIRAM on 11-09-2016.
 */
public class Expense implements Parcelable {

    private String name;

    private String category;

    private Integer categoryId;

    private Double amount;

    private String date;

    public String getReceiptPath() {
        return receiptPath;
    }

    public void setReceiptPath(String receiptPath) {
        this.receiptPath = receiptPath;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    private String receiptPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Expense(String name, String category, Integer categoryId, Double amount, String date, String receiptPath) {
        this.name = name;
        this.category = category;
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.receiptPath = receiptPath;
    }

    protected Expense(Parcel in) {
        name = in.readString();
        category = in.readString();
        categoryId = in.readInt();
        amount = in.readDouble();
        date = in.readString();
        receiptPath = in.readString();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(categoryId);
        dest.writeDouble(amount);
        dest.writeString(date);
        dest.writeString(receiptPath);
    }
}
