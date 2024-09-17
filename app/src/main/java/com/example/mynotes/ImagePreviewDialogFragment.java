package com.example.mynotes;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import org.jetbrains.annotations.NotNull;

public class ImagePreviewDialogFragment extends DialogFragment {

    private static final String ARG_PHOTO_PATH = "photo_path";

    public static ImagePreviewDialogFragment newInstance(String photoPath) {
        ImagePreviewDialogFragment fragment = new ImagePreviewDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PHOTO_PATH, photoPath);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_image_preview, container, false);
        ImageView ivPreview = view.findViewById(R.id.ivPreview);

        if (getArguments() != null) {
            String photoPath = getArguments().getString(ARG_PHOTO_PATH);
            if (photoPath != null && !photoPath.isEmpty()) {
                Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
                ivPreview.setImageBitmap(bitmap);
            }
        }

        return view;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return dialog;
    }
}
