package com.example.myshopping.ui.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshopping.R;
import com.example.myshopping.repository.HistoryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryItem> historys;

    public HistoryAdapter() {
        historys = new ArrayList<>();
    }

    public void setHistorys(List<HistoryItem> historys) {
        this.historys = historys;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem item = historys.get(position);
        holder.textViewTextHistory.setText(item.getText());
        holder.textViewTimeHistory.setText(item.getTime());
        holder.textViewStatusHistory.setText(item.getStatus());
        if (item.getImage() != null) {
            holder.textViewTextHistory.setTextColor(Color.BLUE);
            Picasso.get().load(item.getImage()).into(holder.imageViewImageHistory);
        }
    }

    @Override
    public int getItemCount() {
        return historys.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewPurchTextHistory)
        TextView textViewTextHistory;
        @BindView(R.id.textViewPurchaseTimeHistory)
        TextView textViewTimeHistory;
        @BindView(R.id.textViewPurchaseStatusHistory)
        TextView textViewStatusHistory;
        @BindView(R.id.imageViewImageHistory)
        ImageView imageViewImageHistory;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageViewImageHistory.getVisibility() == View.VISIBLE) {
                        imageViewImageHistory.setVisibility(View.GONE);
                    } else {
                        imageViewImageHistory.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
