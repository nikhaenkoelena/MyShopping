package com.example.myshopping.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopping.R;
import com.example.myshopping.presenter.ToBuyPresenter;
import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.ui.fragments.ToBuyInterface;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private List<Purchase> purch;
    private Purchase purchaseTemp;
    private ToBuyPresenter presenter;
    private Context context;

    private final String STATUS_BOUGHT = "Bought";
    private final String STATUS_ADDED = "Added";

    public PurchaseAdapter(Context context, ToBuyInterface toBuyInterface) {
        purch = new ArrayList<>();
        presenter = new ToBuyPresenter(context, toBuyInterface);
        this.context = context;
    }

    public void setPurch(List<Purchase> purch) {
        this.purch = purch;
        notifyDataSetChanged();
    }

    public Purchase getPurchaseTemp() {
        return purchaseTemp;
    }

    public void removePurchase(int position) {
        purch.remove(position);
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
        purchaseTemp = purchase;
        holder.textViewPurchaseText.setText(purchase.getText());
        holder.textViewPurchsaseTime.setText(purchase.getTime());
        holder.checkBox.setTag(purchase);
        holder.checkBox.setChecked(purchase.getIsBought());
        if (purchase.getImage() != null) {
            holder.textViewPurchaseText.setTextColor(Color.BLUE);
            Picasso.get().load(purchase.getImage()).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return purch.size();
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewPurchaseText)
        TextView textViewPurchaseText;
        @BindView(R.id.textViewPurchaseTime)
        TextView textViewPurchsaseTime;
        @BindView(R.id.checkBox)
        CheckBox checkBox;
        @BindView(R.id.imageViewImageMain)
        ImageView imageView;
        @BindView(R.id.textViewToSeeImage)
        TextView textViewToSeeImage;

        public PurchaseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textViewPurchaseText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageView.getVisibility() == View.VISIBLE) {
                        imageView.setVisibility(View.GONE);
                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            });
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = checkBox.isChecked();
                    Purchase purchase = (Purchase) checkBox.getTag();
                    int id = purchase.getUniqId();
                    presenter.updatePurchaseState(id, isChecked);
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern(" MMM dd, hh:mm ");
                    String time = LocalDateTime.now().format(formater);
                    if (isChecked) {
                        presenter.insertHistoryItem(new HistoryItem(purchase.getText(), time, purchase.getImage(), STATUS_BOUGHT));
                    } else {
                        presenter.insertHistoryItem(new HistoryItem(purchase.getText(), time, purchase.getImage(), STATUS_ADDED));
                    }
                }
            });
        }
    }
}
