package com.ericho.android.checkapkversion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FilenameFilter;

public class MainActivity extends AppCompatActivity {
    private String folderPath;
    private File[] result;
    private String t = "MainActivity";
    private SwipeRefreshLayout mRefreshLayout;
    private ApkAdapter mApkAdapter;
    private File mFileDirectory;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final InterstitialAd interstitialAd = new InterstitialAd(MainActivity.this); //initialize
        interstitialAd.setAdUnitId(getString(R.string.page_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Toast.makeText(MainActivity.this, "close ads", Toast.LENGTH_LONG).show();
                interstitialAd.loadAd(generateRequest());
            }
        });

        interstitialAd.loadAd(generateRequest());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });
        //find view
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mRefreshLayout.setOnRefreshListener(mRefreshListener);
        GridView mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setNumColumns(2);





        //find
        mFileDirectory = Environment.getExternalStorageDirectory();
        File[] tet = mFileDirectory.listFiles();
        result = mFileDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return (name.endsWith(".apk"));
            }
        });
        if (result != null) {
            Gog.d(t, result.length + "\n" + tet.length);
        }
        //adapter
        mApkAdapter = new ApkAdapter(this, result);

        //
        mGridView.setAdapter(mApkAdapter);
        mGridView.setOnItemClickListener(mItemClickListener);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //do ads
        AdView adView = (AdView) findViewById(R.id.adview);
        adView.loadAd(generateRequest());//show ads
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent setting = new Intent(this, UninstallActivity.class);
            startActivityForResult(setting, ReqeustCode.START_SETTING_ACTIVITY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            updateFileConstant();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ReqeustCode.START_SETTING_ACTIVITY &&
                resultCode == RESULT_OK) {
            // get the new URL
            this.folderPath = getSharedPreferences(getPackageName(), MODE_PRIVATE).getString(Key.last_file_directory, Constant.default_file_directory);
            mFileDirectory = new File(folderPath);
            updateFileConstant();
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateFileConstant() {
        result = mFileDirectory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return (name.endsWith(".apk"));
            }
        });
        mApkAdapter.setFiles(result);
        mApkAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);//end the refresh icon
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ericho.android.checkapkversion/http/host/path")
        );
        AppIndex.AppIndexApi.start(mClient, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.ericho.android.checkapkversion/http/host/path")
        );
        AppIndex.AppIndexApi.end(mClient, viewAction);
        mClient.disconnect();
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d(t,"start select ");
            Log.d(t,mFileDirectory.toString());
            Log.d(t,result[i].getPath());
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile( result[i]), "application/vnd.android.package-archive");
            startActivity(intent);

        }
    };

    private AdRequest generateRequest(){
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(Constant.default_serial_number)
                .addTestDevice(Constant.motorola_serial_number)
                .build();
        return request;
    }
}
