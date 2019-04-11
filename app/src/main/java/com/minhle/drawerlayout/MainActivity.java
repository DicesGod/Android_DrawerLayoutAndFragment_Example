package com.minhle.drawerlayout;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Country;
import model.CountryFragment;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listViewCountry;
    ArrayList<Country> listOfCountries;
    ArrayAdapter<Country> countryAdapter;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){
        listViewCountry = findViewById(R.id.listViewCountry);
        drawerLayout = findViewById(R.id.drawner_layout);
        listViewCountry.setOnItemClickListener(this);
        listOfCountries = new ArrayList<Country>();
        listOfCountries.add(new Country("France","Paris"));
        listOfCountries.add(new Country("India","New Dehil"));
        listOfCountries.add(new Country("Brasil","Brarisla"));

        countryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1
        ,listOfCountries);
        listViewCountry.setAdapter(countryAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //send data from the activity -> Fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("country",listOfCountries.get(position));

        //replace the linear layout with the fragment
        CountryFragment countryFragment = new CountryFragment();
        countryFragment.setArguments(bundle);
        //reference the fragment manager
        android.app.FragmentManager fragmentManager = getFragmentManager();
        //begin the transaction
        android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //replace the linear layout main_ui with the fragment
        fragmentTransaction.replace(R.id.main_ui,countryFragment);
        //commit the transaction
        fragmentTransaction.commit();
        setTitle(listOfCountries.get(position).getCountryName());

        drawerLayout.closeDrawer(listViewCountry);

    }

}
