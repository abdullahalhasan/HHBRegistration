package com.droidsoftbd.hhbregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        /*ProgressDialog dialog = new ProgressDialog(AboutActivity.this);

        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
        dialog.cancel();*/

    }

    public void droidSoft(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://droidsoftbd.com"));
        startActivity(intent);
    }

    public void facebookGroup(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.facebook.com/groups/419967898460344"));
        startActivity(intent);
    }

    public void facebookPage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.facebook.com/Helpinghandbangladeshpage/"));
        startActivity(intent);
    }
}
