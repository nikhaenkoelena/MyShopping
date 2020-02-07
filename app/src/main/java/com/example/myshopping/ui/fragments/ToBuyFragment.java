package com.example.myshopping.ui.fragments;


import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myshopping.R;
import com.example.myshopping.presenter.ToBuyPresenter;
import com.example.myshopping.repository.HistoryItem;
import com.example.myshopping.repository.Purchase;
import com.example.myshopping.ui.adapter.PurchaseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ToBuyFragment extends Fragment implements ToBuyInterface {

    @BindView(R.id.recyclerViewPurchasesList)
    RecyclerView recyclerView;
    @BindView(R.id.textViewToBuyLabel)
    TextView textViewToBuy;
    @BindView(R.id.textViewBoughtLabel)
    TextView textViewBought;
    @BindView(R.id.buttonAddNewPurchaseMain)
    FloatingActionButton button;
    private PurchaseAdapter adapter;
    private NavController navController;
    private ToBuyPresenter presenter;
    private Unbinder unbinder;

    private static final Boolean ISNOTBOUGHT = false;
    private static final Boolean ISBOUGHT = true;
    private final String STATUS_DELETED = "Deleted";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_buy, container, false);
        Objects.requireNonNull(getActivity()).setTitle(R.string.app_title);
        unbinder = ButterKnife.bind(this, view);
        presenter = new ToBuyPresenter(getContext(), this);
        adapter = new PurchaseAdapter(getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT || direction == ItemTouchHelper.LEFT) {
                    Purchase purchase = adapter.getPurchaseTemp();
                    adapter.removePurchase(viewHolder.getAdapterPosition());
                    presenter.deletePurchase(purchase.getUniqId());
                    DateTimeFormatter formater = DateTimeFormatter.ofPattern(" MMM dd, hh:mm ");
                    String time = LocalDateTime.now().format(formater);
                    presenter.insertHistoryItem(new HistoryItem(purchase.getText(), time, purchase.getImage(), STATUS_DELETED));
                }
            }
        }).attachToRecyclerView(recyclerView);

        textViewToBuy.setTextColor(getResources().getColor(R.color.colorAccent));
        textViewToBuy.setTypeface(Typeface.DEFAULT_BOLD);
        textViewBought.setTextColor(getResources().getColor(R.color.colorPrimary));
        presenter.getAllPurchases(ISNOTBOUGHT);
        onClickAddNewPurchase();
        onClickToBuy();
        onClickBought();
    }

    @Override
    public void setPurchases(List<Purchase> purchases) {
        Comparator<Purchase> comparator = Comparator
                .comparing(Purchase::getTime).reversed();
        Collections.sort(purchases, comparator);
        adapter.setPurch(purchases);
    }

    private void onClickAddNewPurchase() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_toBuyFragment_to_addNewPurchaseFragment);
            }
        });
    }

    private void onClickToBuy() {
        textViewToBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAllPurchases(ISNOTBOUGHT);
                textViewToBuy.setTextColor(getResources().getColor(R.color.colorAccent));
                textViewToBuy.setTypeface(Typeface.DEFAULT_BOLD);
                textViewBought.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

    }

    private void onClickBought() {
        textViewBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAllPurchases(ISBOUGHT);
                textViewBought.setTextColor(getResources().getColor(R.color.colorAccent));
                textViewBought.setTypeface(Typeface.DEFAULT_BOLD);
                textViewToBuy.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.dispose();
    }

}
