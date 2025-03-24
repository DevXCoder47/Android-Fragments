package com.example.productfragments.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.example.productfragments.R;
import com.example.productfragments.entity.Product;
import com.example.productfragments.fragment.ProductDetailFragment;
import com.example.productfragments.fragment.ProductListFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductListFragment.OnProductSelectedListener {
    private FragmentContainerView detailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detailContainer = findViewById(R.id.detail_container); // Буде null у портретному режимі

        // Ініціалізація списку продуктів
        ArrayList<Product> products = generateSampleProducts();

        // Додаємо список продуктів
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_container, ProductListFragment.newInstance(products))
                    .commit();
        }
    }

    @Override
    public void onProductSelected(Product product) {
        if (detailContainer != null) { // Ландшафтний режим на планшеті
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, ProductDetailFragment.newInstance(product))
                    .commit();
        } else { // Портретний режим на телефоні
            Intent intent = new Intent(this, ProductDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("product", product);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    private ArrayList<Product> generateSampleProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Phone", 599.99, "Smartphone description"));
        products.add(new Product("Laptop", 999.99, "Laptop description"));
        return products;
    }
}