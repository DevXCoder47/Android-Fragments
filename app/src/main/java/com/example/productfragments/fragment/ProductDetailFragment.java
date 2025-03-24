package com.example.productfragments.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.productfragments.R;
import com.example.productfragments.entity.Product;

public class ProductDetailFragment extends Fragment {
    private static final String ARG_PRODUCT = "product";
    private Product product;

    public static ProductDetailFragment newInstance(Product product) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable(ARG_PRODUCT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        TextView nameText = view.findViewById(R.id.product_name);
        TextView priceText = view.findViewById(R.id.product_price);
        TextView descriptionText = view.findViewById(R.id.product_description);

        if (product != null) {
            nameText.setText(product.getName());
            priceText.setText(String.format("$%.2f", product.getPrice()));
            descriptionText.setText(product.getDescription());
        }

        return view;
    }
}