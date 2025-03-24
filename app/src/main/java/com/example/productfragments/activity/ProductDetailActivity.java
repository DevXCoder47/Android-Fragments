package com.example.productfragments.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.productfragments.R;
import com.example.productfragments.entity.Product;
import com.example.productfragments.fragment.ProductDetailFragment;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        setContentView(R.layout.activity_product_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Product product = extras.getParcelable("product");
            if (product != null && savedInstanceState == null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.detail_container, ProductDetailFragment.newInstance(product))
                        .commit();
            }
        }
    }
}