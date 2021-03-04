package io.polytech.sportable.activities.statistics;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class StatActivityPagerAdapter extends FragmentStateAdapter {

    final int PAGE_COUNT = 3;

    public StatActivityPagerAdapter(@NonNull FragmentManager fm, Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TotalPageFragment.newInstance();
            case 1:
                return CertainActivityPageFragment.newInstance();
            case 2:
                return CertainDayFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
