package com.example.samplelinearlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    //Deklarasi variabel dengan jenis data Listview
    private ListView list;

    //Memanggil class ListviewAdapter dan diinisiasi menjadi variabel adapter
    private ListViewAdepter adapter;

    //Deklarasi array untuk menyimpan nama
    String[] listnama;

    //Memanggil class CmassNama
    public static ArrayList<ClassNama> classNamaArrayList = new ArrayList<ClassNama>();

    //Memanggil objek Bundle
    Bundle bundle = new Bundle();

    //Membuat objek intent
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Menyimpan nama didalam array listNama
        listnama = new String[]{"Inayah", "Ilham", "Eris",
                "Fikri", "Maul", "Intan", "Vina", "Gita",
                "Vian", "Lutfi"};
        list = findViewById(R.id.listKontak);

        //membuat objek dari class classNama menjadi array list
        classNamaArrayList = new ArrayList<>();

        //Membaca seluruh data pada array ListNama
        for (int i = 0; i < listnama.length; i++) {
            //Membuat objek class nama da
            ClassNama classNama = new ClassNama(listnama[i]);
            // Binds strings ke array
            classNamaArrayList.add(classNama);
        }
        //Membuat objek dari ListviewAdapter
        adapter = new ListViewAdepter(this);

        //Binds Adapter ke ListView
        list.setAdapter(adapter);
        //Membuat event dari List oncLick
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Deklarasi variabeL nama yong berisi item yarg diklik
                String nama = classNamaArrayList.get(position).getNama();

                //memsukkan value dari variabel nama dengan kunci "a"
                // dan dimasukkan kedaLam bundle
                bundle.putString("a", nama.trim());

                //Membuat objek popup menu
                PopupMenu pm = new PopupMenu(getApplicationContext(), view);

                // Membuat event untuk popup menu ketika dipilih
                pm.setOnMenuItemClickListener(Home_Activity.this);

                //Menampilkan popup menu Layout
                pm.inflate(R.menu.popup_menu);

                //Menampilkan popup menu
                pm.show();
            }
        });
    }

    //event yang terjadi ketika menu dipilih
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.mnview:
                intent = new Intent(getApplicationContext(),ActivityLihatData.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mnedit:
                Toast.makeText(getApplicationContext(), "Ini untuk edit kontak",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }
}

