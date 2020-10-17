package com.example.myapplication.ui.contactos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;

public class ContactosFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactosViewModel contactosViewModel = new ViewModelProvider(this).get(ContactosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contactos, container, false);



        ImageButton btnmorada = (ImageButton) root.findViewById(R.id.btn_morada);
        btnmorada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Celeiro+Gamespot+Nisa/@39.511838,-7.6505013,19.75z/data=!4m5!3m4!1s0x0:0xb57c81f89ae8f24!8m2!3d39.511708!4d-7.64943"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbutmorada = (LinearLayout) root.findViewById(R.id.llbut_morada);
        llbutmorada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Celeiro+Gamespot+Nisa/@39.511838,-7.6505013,19.75z/data=!4m5!3m4!1s0x0:0xb57c81f89ae8f24!8m2!3d39.511708!4d-7.64943"));
                startActivity(browserIntent);
            }
        });

        ImageButton btntelemovel = (ImageButton) root.findViewById(R.id.btn_telemovel);
        btntelemovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:913387635"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbuttm = (LinearLayout) root.findViewById(R.id.llbut_tm);
        llbuttm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:913387635"));
                startActivity(browserIntent);
            }
        });

        ImageButton btntelefone = (ImageButton) root.findViewById(R.id.btn_telefone);
        btntelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:245092926"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbuttel = (LinearLayout) root.findViewById(R.id.llbut_tel);
        llbuttel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:245092926"));
                startActivity(browserIntent);
            }
        });

        ImageButton btnwhatsapp = (ImageButton) root.findViewById(R.id.btn_whatsapp);
        btnwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=351913387635"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbutwa = (LinearLayout) root.findViewById(R.id.llbut_wa);
        llbutwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=351913387635"));
                startActivity(browserIntent);
            }
        });

        ImageButton btnmessenger = (ImageButton) root.findViewById(R.id.btn_messenger);
        btnmessenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/CeleiroNisa"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbutme = (LinearLayout) root.findViewById(R.id.llbut_me);
        llbutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/CeleiroNisa"));
                startActivity(browserIntent);
            }
        });

        ImageButton btnemail = (ImageButton) root.findViewById(R.id.btn_mail);
        btnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:celeironisa@gmail.com"));
                startActivity(browserIntent);
            }
        });

        LinearLayout llbutem = (LinearLayout) root.findViewById(R.id.llbut_em);
        llbutem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:celeironisa@gmail.com"));
                startActivity(browserIntent);
            }
        });

        return root;
    }
}
