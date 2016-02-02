package com.ericho.android.checkapkversion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by EricH on 10/12/2015.
 */
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Activity mContext;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Activity context) {
        mTitles = Constant.mTitles;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId( int position ) {
        return super.getItemId(position);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final NormalTextViewHolder holder,final int position) {
        holder.mTextView.setText(mTitles[position]);
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("NormalTextViewHolder", "onClick--> position = " + position);
                View view = holder.mTextView;
                String temp = "com.tradelink." + ((TextView) view).getText().toString();
                removePackage(temp);
                Log.d("NormalTextViewHolder", "onClick--> position = ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;

        NormalTextViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
    public void removePackage(String packcageName){
        int UNINSTALL_REQUEST_CODE = 1;

        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + packcageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        mContext.startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
    }

}