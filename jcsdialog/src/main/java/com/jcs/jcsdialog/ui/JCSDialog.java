package com.jcs.jcsdialog.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.jcs.jcsdialog.R;

public class JCSDialog extends Dialog implements OnClickListener {
    private boolean isVisibleNegativeButton;
    private boolean isVisiblePositiveButton;
    private DialogClickListener listener;
    private ImageView mBackgroundIcon;
    private Button mBtnNegative;
    private Button mBtnPositive;
    private ImageView mIcon;
    private TextView mMessage;
    private TextView mTitle;

    private CharSequence message;
    private CharSequence textNegativeButton;
    private CharSequence textPositiveButton;
    private CharSequence title;

    private int mensagemColor = 0;
    private int titleColor = 0;

    private int topShapeColor = 0;
    private int topShapeColorB = -1;
    private int topShapeColorG = -1;
    private int topShapeColorR = -1;

    public interface DialogClickListener {
        void onClick(View view);
    }

    public JCSDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.mIcon = (ImageView) findViewById(R.id.shape_icon);
        this.mBackgroundIcon = (ImageView) findViewById(R.id.shape_background_icon);
        this.mTitle = (TextView) findViewById(R.id.dialog_title);
        this.mMessage = (TextView) findViewById(R.id.dialog_message);
        this.mBtnNegative = (Button) findViewById(R.id.btn_negative);
        this.mBtnPositive = (Button) findViewById(R.id.btn_positive);

        GradientDrawable gradientDrawableShape = (GradientDrawable) ((LayerDrawable) ContextCompat.getDrawable(getContext(), R.drawable.round_background)).findDrawableByLayerId(R.id.top_shape_background);
        gradientDrawableShape.setStroke(5, Color.WHITE);

        this.mBtnNegative.setOnClickListener(this);
        this.mBtnPositive.setOnClickListener(this);

        if (topShapeColor != 0) {
            gradientDrawableShape.setColor(topShapeColor);
            this.mBackgroundIcon.setBackground(gradientDrawableShape);
        } else if (topShapeColorR >= 0 && topShapeColorG >= 0 && topShapeColorB >= 0) {
            gradientDrawableShape.setColor(Color.rgb(topShapeColorR, topShapeColorG, topShapeColorB));

        }
        if (this.isVisibleNegativeButton) {
            this.mBtnNegative.setVisibility(View.VISIBLE);
        } else {
            this.mBtnNegative.setVisibility(View.GONE);
        }
        if (this.isVisiblePositiveButton) {
            this.mBtnPositive.setVisibility(View.VISIBLE);
        } else {
            this.mBtnPositive.setVisibility(View.GONE);
        }

        if (titleColor != 0) {
            this.mTitle.setTextColor(titleColor);
        }

        if (mensagemColor != 0) {
            this.mMessage.setTextColor(mensagemColor);
        }

        if (textNegativeButton != null) {
            this.mBtnNegative.setText(textNegativeButton);
        }

        if (textPositiveButton != null) {
            this.mBtnPositive.setText(textPositiveButton);
        }

        if (title != null) {
            this.mTitle.setText(title);
        }
        if (message != null) {
            this.mMessage.setText(message);
        }
    }

    public JCSDialog setTitulo(@Nullable CharSequence title) {
        this.title = title;
        return this;
    }

    public JCSDialog setMensagem(@Nullable CharSequence message) {
        this.message = message;
        return this;
    }

    public JCSDialog setTitleColor(int color) {
        this.titleColor = color;
        return this;
    }

    public JCSDialog setMessageColor(int color) {
        this.mensagemColor = color;
        return this;
    }

    public JCSDialog setRoundShapeColor(int color) {
        this.topShapeColor = color;
        return this;
    }

    public JCSDialog setRoundShapeColor(int r, int b, int g) {
        this.topShapeColorR = r;
        this.topShapeColorB = b;
        this.topShapeColorG = g;
        return this;
    }

    public JCSDialog setNegativeButton(CharSequence text, DialogClickListener listener) {
        this.isVisibleNegativeButton = true;
        this.textNegativeButton = text;
        this.listener = listener;
        return this;
    }

    public JCSDialog setPositiveButton(CharSequence text, DialogClickListener listener) {
        this.isVisiblePositiveButton = true;
        this.textPositiveButton = text;
        this.listener = listener;
        return this;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (this.listener == null) {
            dismiss();
        } else if (id == R.id.btn_negative) {
            this.listener.onClick(v);
            dismiss();
        } else if (id == R.id.btn_positive) {
            this.listener.onClick(v);
        }
    }

}
