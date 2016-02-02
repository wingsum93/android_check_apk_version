package com.ericho.android.checkapkversion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UninstallActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    public RecyclerView mRecyclerView;
    private int UNINSTALL_REQUEST_CODE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uninstall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // find view id
        ButterKnife.bind(this);

        ///
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
//        mRecyclerView.setOnTouchListener();




    }

    public void removePackage(String packcageName){
        UNINSTALL_REQUEST_CODE = 1;

        Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
        intent.setData(Uri.parse("package:" + packcageName));
        intent.putExtra(Intent.EXTRA_RETURN_RESULT, true);
        startActivityForResult(intent, UNINSTALL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.d("TAG", "onActivityResult: user accepted the (un)install");
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("TAG", "onActivityResult: user canceled the (un)install");
            } else if (resultCode == RESULT_FIRST_USER) {
                Log.d("TAG", "onActivityResult: failed to (un)install");
            }
        }
    }

//    public SimpleOnItemTouchListener mTouchListener =new RecyclerView.SimpleOnItemTouchListener() {
//        public void onTouchEvent(RecyclerView rv, MotionEvent e){
//            String string = ((TextView) view).getText().toString();
//            String temp = "com.tradelink."+string;
//            removePackage(temp);
//            Log.d("NormalTextViewHolder", "onClick--> position = ");
//        }
//    };


}
