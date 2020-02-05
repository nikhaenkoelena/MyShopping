package com.example.myshopping.ui.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myshopping.R;
import com.example.myshopping.repository.Purchase;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class AddNewPurchaseFragment extends Fragment {

    @BindView(R.id.editTextPurchaseText) EditText editText;
    @BindView(R.id.buttonAddImage) Button buttonAddImage;
    @BindView(R.id.imageViewImage) ImageView imageView;
    @BindView(R.id.buttonAddNewPurchaseSecond) Button buttonAddPurchase;
    @BindView(R.id.buttonCloseImage) ImageButton buttonCloseImage;

    private Uri imageUri;

    private NavController navController;
    private Unbinder unbinder;

    private final static int RC_GET_IMAGE = 100;
    private final static boolean UNDONE_PURCHASE = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_add_new_purchase, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        onClickAddImage();
        onClickAddPurchase();
    }

    private void onClickAddImage() {
        buttonAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/jpeg");
                        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                        startActivityForResult(intent, RC_GET_IMAGE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        closeImage();
        if (requestCode == RC_GET_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    imageUri = uri;
                    imageView.setVisibility(View.VISIBLE);
                    buttonCloseImage.setVisibility(View.VISIBLE);
                    Picasso.get().load(uri).into(imageView);
                }
            } else {
                Toast.makeText(getContext(), R.string.error_loading_image, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void closeImage () {
        buttonCloseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.GONE);
                buttonCloseImage.setVisibility(View.GONE);
                imageUri = null;
            }
        });
    }

    private void onClickAddPurchase () {
        buttonAddPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                if (text.isEmpty()) {
                    Toast.makeText(getContext(), getString(R.string.error_enter_purchase_text), Toast.LENGTH_SHORT).show();
                }
                Date currentTime = Calendar.getInstance().getTime();
                Purchase purchase = new Purchase(text, currentTime.toString(), imageUri.toString(), UNDONE_PURCHASE);
                navController.navigate(R.id.action_addNewPurchaseFragment_to_toBuyFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
