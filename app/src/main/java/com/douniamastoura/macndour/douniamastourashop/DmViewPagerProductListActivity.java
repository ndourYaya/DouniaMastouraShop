package com.douniamastoura.macndour.douniamastourashop;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.douniamastoura.macndour.DmModels.DmArticlesModel;
import com.douniamastoura.macndour.DmModels.DmCategoriesModel;
import com.douniamastoura.macndour.DmTasks.LoadArticlesByCategoriesTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class DmViewPagerProductListActivity extends ActionBarActivity implements DmViewPagerProductListFragment.OnFragmentInteractionListener {



    private int currentPosition;
    private ArrayList<DmViewPagerProductListFragment> gridFragment;
    private ArrayList<DmArticlesModel> itmLst;
    public ArrayList<String> catName;
    private ArrayList<String> productName;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm_view_pager_product_list);
        currentPosition = (int) getIntent().getExtras().get("catPos");

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        catName = (ArrayList<String>) getIntent().getExtras().get("catTitles");
        gridFragment = new ArrayList<DmViewPagerProductListFragment>();


        for(int i = 0; i<catName.size();i++)
        {
			/*accès base de données pour récuperer la liste des articles par categories*/
            LoadArticlesByCategoriesTask task = new LoadArticlesByCategoriesTask(this,i);
            Log.d("List view pager", "chargement de la liste: " + catName.get(i));
            try {
                itmLst = task.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            productName = new ArrayList<String>();
            for(DmArticlesModel article : itmLst)
            {
                productName.add(article.getNom());
            }
            gridFragment.add(new DmViewPagerProductListFragment(i, productName));
        }
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),gridFragment);
        mPager.setOffscreenPageLimit(catName.size());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(currentPosition);


    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<DmViewPagerProductListFragment> fragments;

        public ScreenSlidePagerAdapter(FragmentManager fm,ArrayList<DmViewPagerProductListFragment> fragList) {
            super(fm);
            this.fragments = fragList;


        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return catName.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catName.get(position);
        }
    }
}

