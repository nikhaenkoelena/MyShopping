package com.example.myshopping.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopping.R;
import com.example.myshopping.repository.Purchase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private List<Purchase> purch;

    public PurchaseAdapter() {
        purch = new ArrayList<>();
    }

    public void setPurch(List<Purchase> purch) {
        this.purch = purch;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_item, parent, false);
        return new PurchaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        Purchase purchase = purch.get(position);
        holder.textViewPurchaseText.setText(purchase.getText());
        holder.textViewPurchsaseTime.setText(purchase.getTime());
        holder.checkBox.setChecked(purchase.getIsBought());
        if (!purchase.getImage().isEmpty()) {
            holder.textViewToSeeImage.setVisibility(View.VISIBLE);
            Picasso.get().load(purchase.getImage()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return purch.size();
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewPurchaseText) TextView textViewPurchaseText;
        @BindView(R.id.textViewPurchaseTime) TextView textViewPurchsaseTime;
        @BindView(R.id.checkBox) CheckBox checkBox;
        @BindView(R.id.imageViewImageMain) ImageView imageView;
        @BindView(R.id.textViewToSeeImage) TextView textViewToSeeImage;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textViewToSeeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageView.getVisibility() == View.VISIBLE) {
                        imageView.setVisibility(View.GONE);
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
