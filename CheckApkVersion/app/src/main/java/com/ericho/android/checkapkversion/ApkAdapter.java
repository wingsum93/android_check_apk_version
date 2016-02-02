package com.ericho.android.checkapkversion;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;


/**
 * Created by EricH on 26/11/2015.
 */
public class ApkAdapter extends BaseAdapter {
    private String t = "ApkAdapter";
    private File[] mFiles;
    private LayoutInflater mInflater;
    private PackageManager pm;
    private String[] iconArray = {"icon1","icon2","icon3"};
    private Context mContext;
    ApkAdapter(Context m,File[] input){
        this.mFiles=input;
        mInflater=LayoutInflater.from(m);
        pm = m.getPackageManager();
        this.mContext = m;
    }
    @Override
    public int getCount() {
        if(mFiles!=null && mFiles.length>0){
            return mFiles.length;
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.grid_file_item,null);
        AppCompatImageView imageView = (AppCompatImageView) view.findViewById(R.id.imageview);
        TextView textview1 = (TextView)view.findViewById(R.id.version_name);
        TextView textview2 = (TextView)view.findViewById(R.id.version_code);
        TextView textview3 = (TextView)view.findViewById(R.id.file_name);
        //
        PackageInfo info = pm.getPackageArchiveInfo(mFiles[i].getPath(), 0);

        textview1.setText(info.versionName);
        textview2.setText(String.valueOf(info.versionCode));
        textview3.setText(mFiles[i].getName());

        //
        int i2 = ((int)(Math.random()*3));
        int picId = mContext.getResources().getIdentifier(iconArray[i2], "drawable", mContext.getPackageName());
        imageView.setImageResource(picId);

        return view;
    }
    public void setFiles(File[] data){this.mFiles=data;}
    private void d(String string,String in){
        Log.d(string, in);
    }
}
