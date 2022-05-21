package com.example.agritechproject.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.agritechproject.AccountFragment;
import com.example.agritechproject.DashbordFragment;
import com.example.agritechproject.FarmsListFragment;
import com.example.agritechproject.GestionParcelle.UpdateParcelle.StepOneFragment;
import com.example.agritechproject.GestionParcelle.UpdateParcelle.StepThreeFragment;
import com.example.agritechproject.GestionParcelle.UpdateParcelle.StepTwoFragment;
import com.example.agritechproject.R;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){

            case 0:
                return  new StepOneFragment();
            case 1:
                return new StepTwoFragment();

            case 2:
                return  new StepThreeFragment();

                    default:return null;


       }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
