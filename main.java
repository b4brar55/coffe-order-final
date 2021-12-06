package com.mycompany.coffe_order;

import android.app.Activity;

import android.os.Bundle;

import android.view.View;

import android.widget.TextView;

import java.text.NumberFormat;

import android.widget.Toast;

import android.widget.CheckBox;

import android.widget.EditText;

import android.content.Context;

import android.content.Intent;

import android.net.Uri;

public class MainActivity extends Activity { 

	int quantity =0;    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

	}

	public void plusBtn(View view) {

		if (quantity == 40) {

			Toast ifHigh = Toast.makeText(this, "You cant,Order more than 40 Coffe", Toast.LENGTH_LONG);

			ifHigh.show();

			return;

		}

		quantity = quantity + 1;

		displayQuantity(quantity);

	} 

	public void minusBtn(View view) {

		if (quantity == 0) {

			Toast errorIfZero = Toast.makeText(this, "You have 0 Coffe,Pls order now!", Toast.LENGTH_LONG);

			errorIfZero.show();

			return;

		}

		quantity = quantity - 1;

		displayQuantity(quantity);

	}

	public void orderBtn(View view) {

		EditText nameField = (EditText) findViewById(R.id.text_field);

		String name = nameField.getText().toString();

		CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.has_cream);

		boolean hasWippedCream = whippedCreamCheckBox.isChecked();

		CheckBox choclateCheckBox = (CheckBox) findViewById(R.id.has_choclate);

		boolean hasChoclate = choclateCheckBox.isChecked();

		int price = calCulatePrice(hasWippedCream, hasChoclate);

		String priceMsg = createOrder(name, price, hasWippedCream, hasChoclate);

		Intent myintent = new Intent(Intent.ACTION_SENDTO);

		myintent.setData(Uri.parse("Mail to:"));

		myintent.putExtra(Intent.EXTRA_SUBJECT, "Just java order for: " + name);

		myintent.putExtra(Intent.EXTRA_TEXT, price);

		if (myintent.resolveActivity(getPackageManager()) != null) {

			startActivity(myintent);

		}

		displayPrice(priceMsg);

	}

	private String createOrder(String name, int price, boolean hasWippedCream, boolean hasChoclate) {

		

		String strMsg = "Name: " + name;

		if (hasWippedCream) {

			strMsg = strMsg + "\nWipped cream : " + "yes";}

		if (hasChoclate) {

			strMsg = strMsg + "\nHas choclate : " + "yes";}

		strMsg = strMsg + "\nQuantity" + quantity;

		strMsg = strMsg + "\nTotal: $" + price;

		strMsg = strMsg + "\n Thank you";

		return strMsg;

	}

	private int calCulatePrice(boolean hasWippedCream, boolean hasChoclate) {

		int basePrice =5;

		if (hasWippedCream) {

			basePrice = basePrice + 1;

		}

		if (hasChoclate) {

			basePrice = basePrice + 2;

		}

		return quantity * basePrice;

		//basePrice;

	}

	private void displayPrice(String numbers) {

		TextView orderSummeryView = (TextView) findViewById(R.id.order_summery_text_view);

		orderSummeryView.setText(" " + numbers);

	}

	private void displayQuantity(int numbers) {

		TextView quantity = (TextView) findViewById(R.id.quantity_text_view);

		quantity.setText(" " + numbers);

	}

}
