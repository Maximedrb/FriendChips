package com.example.friendschips.menu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.example.friendschips.classe.Utilisateur;
import com.example.friendschips.fragment.AddFoodFragment;
import com.example.friendschips.fragment.AddFriendFragment;
import com.example.friendschips.fragment.GestionFragment;
import com.example.friendschips.fragment.GestionFriendFragment;
import com.example.friendschips.fragment.GestionUtilisateursFragment;
import com.example.friendschips.fragment.ProfilFragment;
import com.example.friendschips.friendschips.R;

/**
 * Created by MaximeDrx on 22/09/2016.
 */
public class FriendActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_tab_favourite,
            R.drawable.ic_tab_call,
            R.drawable.ic_tab_contacts
    };

    private GestionUtilisateursFragment gestionUtilisateurs = new GestionUtilisateursFragment();
    private GestionFriendFragment gesstionFriend = new GestionFriendFragment();
    private Utilisateur user = new Utilisateur();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        Intent intent = getIntent();
        user = intent.getExtras().getParcelable("userConnecter");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FriendActivity.this, Menu_App.class);
                i.putExtra("userConnecter",user);
                startActivity(i);
                finish();
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 1)
                {
                    //c'est ici qu'il faut  poser la methode initListView.
                   // gestion.InitListEvent(gestion.getView());

                }else if(position == 2)
                {
                    gestionUtilisateurs.InitListEvent(gestionUtilisateurs.getView());
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(FriendActivity.this, Menu_App.class);
        i.putExtra("userConnecter",user);
        startActivity(i);
        finish();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ProfilFragment(), "Profil");
        adapter.addFrag(new GestionFriendFragment(), "Vos amis");
        adapter.addFrag(new GestionUtilisateursFragment(),"...");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}
