package com.douniamastoura.macndour.douniamastourashop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.douniamastoura.macndour.DmAdapters.MainListAdapter;
import com.douniamastoura.macndour.DmModels.DmCategoriesModel;
import com.douniamastoura.macndour.DmTasks.LoadCategoriesTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class DmMainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ListView mainList;
    private ArrayList<DmCategoriesModel> catList = new ArrayList<DmCategoriesModel>();
    private ArrayList<String> catTitle;
    private ArrayList<String> catPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dm_main);
        mainList=(ListView)findViewById(R.id.listview_main);
        LoadCategoriesTask task = new LoadCategoriesTask(this,mainList);
        try {
            catList = task.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        catTitle = new ArrayList<String>();
        catPhoto = new ArrayList<String>();
        for(DmCategoriesModel cat : catList)
        {
            catTitle.add(cat.getNom());
            catPhoto.add(cat.getPhoto());

        }

        //mainGrid.setAdapter(adapter);
        MainListAdapter adapter = new MainListAdapter(this,catPhoto,catTitle);
        // updating listview
        mainList.setAdapter(adapter);
        mainList.setOnItemClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dm_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intentViewPager = new Intent(this,DmViewPagerProductListActivity.class);
        intentViewPager.putExtra("catPos",position);
        intentViewPager.putExtra("catTitles",catTitle);
        startActivity(intentViewPager);

    }


}
