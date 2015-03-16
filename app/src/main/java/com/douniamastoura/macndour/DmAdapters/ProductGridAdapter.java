package com.douniamastoura.macndour.DmAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.douniamastoura.macndour.douniamastourashop.R;

import java.util.ArrayList;

/**
 * Created by macndour on 10/02/15.
 */
public class ProductGridAdapter extends BaseAdapter {

    private Context mContext;
    private int[] imgIds;
    private  ArrayList<String> txtDescriptions;

    public ProductGridAdapter(Context context, int[] imgId, ArrayList<String> txt)
    {
        mContext = context;
        imgIds = imgId;
        txtDescriptions = txt;

    }
    @Override
    public int getCount() {
        return txtDescriptions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View mainGrid = (View)convertView;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null)
        {

            mainGrid = inflater.inflate(R.layout.fragment_dm_view_pager_product_list_item,null);
            ImageView imgView = (ImageView)mainGrid.findViewById(R.id.iv_product_grid_image);
            TextView txtView = (TextView)mainGrid.findViewById(R.id.tv_product_grid_title);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.ic_launcher);
            imgView.setImageBitmap(bitmap);
            txtView.setText(txtDescriptions.get(position));
        }
        else
        {
            mainGrid = (View) convertView;
        }

        return mainGrid;
    }
}
