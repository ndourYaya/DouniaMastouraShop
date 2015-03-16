package com.douniamastoura.macndour.DmAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
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
public class MainListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> imgIds;
    private  ArrayList<String> txtDescriptions;

    static class ViewHolder
    {
        ImageView imgView;
        TextView txtView;
        int position;
    }

    public MainListAdapter(Context context, ArrayList<String> imgId, ArrayList<String> txt)
    {
        mContext = context;
        imgIds = imgId;
        txtDescriptions = txt;


    }
    @Override
    public int getCount() {
        return imgIds.size();
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


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.activity_dm_main_item,null);
            viewHolder = new ViewHolder();
            viewHolder.imgView = (ImageView)convertView.findViewById(R.id.iv_main_list);
            viewHolder.txtView = (TextView)convertView.findViewById(R.id.tv_main_list);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        int id = mContext.getResources().getIdentifier(imgIds.get(position),"drawable",mContext.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),id);
        Bitmap thumbBitmap = ThumbnailUtils.extractThumbnail(bitmap, 300, 200);


        viewHolder.imgView.setImageBitmap(thumbBitmap);
        viewHolder.txtView.setText(txtDescriptions.get(position));
        return convertView;
    }
}
