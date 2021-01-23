package com.jcs.jcsdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jcs.jcsdialog.ui.JCSDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnShowDialog = findViewById(R.id.btn_show_dialog);

        final JCSDialog jcsDialog = new JCSDialog(this);

        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jcsDialog.setTitulo("Sobre");
                jcsDialog.setMensagem("Desculpe-me, mas eu não te conheço o suficiente para falar sobre mim, no entanto, fale um pouco sobre você!");
                jcsDialog.setRoundShapeColor(getResources().getColor(R.color.shape_icon_background));
                jcsDialog.setNegativeButton("Cancelar", new JCSDialog.DialogClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Também não vou falar sobre mim!", Toast.LENGTH_SHORT).show();
                    }
                });
                jcsDialog.setPositiveButton("Ok", new JCSDialog.DialogClickListener() {
                    @Override
                    public void onClick(View v) {
                       Toast.makeText(getApplicationContext(), "Ok, vou falar mais um pouco sobre mim!", Toast.LENGTH_SHORT).show();
                    }
                });
                jcsDialog.show();
            }
        });
    }
}