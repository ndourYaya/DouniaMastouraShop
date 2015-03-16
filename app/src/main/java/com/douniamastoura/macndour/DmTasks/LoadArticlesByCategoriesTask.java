package com.douniamastoura.macndour.DmTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.douniamastoura.macndour.DmDatas.DmCategoriesParser;
import com.douniamastoura.macndour.DmModels.DmArticlesModel;
import com.douniamastoura.macndour.DmModels.DmCategoriesModel;
import com.douniamastoura.macndour.JSonHelper.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by macndour on 15/02/15.
 */
public class LoadArticlesByCategoriesTask extends AsyncTask<Void, Void, ArrayList<DmArticlesModel> > {

    private DmCategoriesParser catParser = new DmCategoriesParser();
    private ArrayList<DmArticlesModel> articleList;
    private static String url_articles_cat = "http://192.168.0.32:8888/android_connect/get_cat_products.php";
    private static String url_articles_all = "http://192.168.0.32:8888/android_connect/get_all_products.php";
    JSONParser jParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_ARTICLES = "Products";
    JSONArray categories = null;
    private int catNum;
    //private GridView mainGrid;
    private Context mContext;


    public LoadArticlesByCategoriesTask(Context context, int i) {
        //this.mainGrid = mainGrid;
        this.mContext =context;
        this.articleList = new ArrayList<DmArticlesModel>();
        this.catNum = i+1;
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
    protected ArrayList<DmArticlesModel> doInBackground(Void... args) {
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Id", Integer.toString(catNum)));
        // getting JSON string from URL
        JSONObject json;
        if(catNum == 1)
            json  = jParser.makeHttpRequest(url_articles_all, "GET", params);
        else
            json = jParser.makeHttpRequest(url_articles_cat, "GET", params);

        // Check your log cat for JSON reponse
        Log.d("All Products: ", json.toString());

        try {
            // Checking for SUCCESS TAG
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {
                // products found
                // Getting Array of Products
                categories = json.getJSONArray(TAG_ARTICLES);

                // looping through All Products
                for (int i = 0; i < categories.length(); i++) {
                    JSONObject c = categories.getJSONObject(i);

                    // Storing each json item in variable
                    String id = c.getString("Id");
                    String name = c.getString("Nom");
                    List<String> photos = new ArrayList<String>(Arrays.asList(c.getString("Photo").split(";")));
                    int qte = c.getInt("Quantite");
                    String desc = c.getString("Description");
                    List<String> tailles = new ArrayList<String>(Arrays.asList(c.getString("Tailles").split(";")));
                    List<String> couleurs = new ArrayList<String>(Arrays.asList(c.getString("Couleurs").split(";")));
                    String idCat = c.getString("idCategories");
                    double prix = c.getDouble("Prix");

                    // creating new HashMap
                    DmArticlesModel articleModel = new DmArticlesModel();

                    // adding each child node to HashMap key => value
                    articleModel.setId(Integer.parseInt(id));
                    articleModel.setNom(name);
                    articleModel.setPhotos((ArrayList<String>)photos);
                    articleModel.setCouleurs((ArrayList<String>)couleurs);
                    articleModel.setTailles((ArrayList<String>)tailles);
                    articleModel.setQuantite(qte);
                    articleModel.setPrix(prix);

                    // adding HashList to ArrayList
                    articleList.add(articleModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return articleList;
    }
    @Override
    protected void onPostExecute(ArrayList<DmArticlesModel> result) {

    }

}