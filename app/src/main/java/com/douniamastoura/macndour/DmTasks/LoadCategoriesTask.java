package com.douniamastoura.macndour.DmTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.douniamastoura.macndour.DmDatas.DmCategoriesParser;
import com.douniamastoura.macndour.DmModels.DmCategoriesModel;
import com.douniamastoura.macndour.JSonHelper.JSONParser;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macndour on 15/02/15.
 */
public class LoadCategoriesTask extends AsyncTask<Void, Void, ArrayList<DmCategoriesModel> > {

    private DmCategoriesParser catParser = new DmCategoriesParser();
    private ArrayList<DmCategoriesModel> catList;
    private static String url_all_categories = "http://192.168.0.32:8888/android_connect/get_all_categories.php";
    JSONParser jParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CATEGORIES = "Categories";
    private static final String TAG_ID = "Id";
    private static final String TAG_NOM = "Nom";
    private static final String TAG_PHOTO = "Photo";
    JSONArray categories = null;
    //private GridView mainGrid;
    private Context mContext;


    public LoadCategoriesTask(Context context,ListView mainList) {
        //this.mainList = mainList;
        this.mContext =context;
        catList = new ArrayList<DmCategoriesModel>();
    }

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    /**
     * getting All products from url
     * */
    protected ArrayList<DmCategoriesModel> doInBackground(Void... args) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_all_categories, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Products: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                categories = json.getJSONArray(TAG_CATEGORIES);

                // looping through All Products
                for (int i = 0; i < categories.length(); i++) {
                    JSONObject c = categories.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NOM);
                    String photo = c.getString(TAG_PHOTO);

                    // creating new HashMap
                    DmCategoriesModel catModel = new DmCategoriesModel();

                    // adding each child node to HashMap key => value
                    catModel.setId(Integer.parseInt(id));
                    catModel.setNom(name);
                    catModel.setPhoto(photo);

                    // adding HashList to ArrayList
                    catList.add(catModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return catList;
    }

}