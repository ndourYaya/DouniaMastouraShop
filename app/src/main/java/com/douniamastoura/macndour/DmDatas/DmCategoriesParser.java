package com.douniamastoura.macndour.DmDatas;

import android.util.Log;

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
public class DmCategoriesParser{

    private ArrayList<DmCategoriesModel> categoriesList;
    // url to get all products list
    private static String url_all_categories = "http://192.168.0.32:8888/android_connect/get_all_categories.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_CATEGORIES = "Categories";
    private static final String TAG_ID = "Id";
    private static final String TAG_NOM = "Nom";
    private static final String TAG_PHOTO = "Photo";
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    // products JSONArray
    JSONArray categories = null;


    public void initCategoriesList()
    {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_all_categories, "GET", params);

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
                    categoriesList.add(catModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DmCategoriesModel> getCategoriesList()
    {
        return categoriesList;
    }


}
