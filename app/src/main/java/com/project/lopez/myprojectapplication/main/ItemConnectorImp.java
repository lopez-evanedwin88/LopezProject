package com.project.lopez.myprojectapplication.main;

/**
 * Created by Administrator on 7/1/2015.
 */


import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.net.Uri;

public class ItemConnectorImp implements  IitemConnector{

    @Override
    public void put(final String name, final onFinishListener listen, final Context context, final MainActivity main) {

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(name))
                {
                    listen.onItemError();
                    error = true;
                }

                if (!error) {
                    listen.Success();
                    ContentValues values = new ContentValues();
                    values.put("_name", name);
                    Uri uri = context.getContentResolver().insert(Provider.CONTENT_URI,values);
                    main.list.add(name);
                    main.list.notifyDataSetChanged();
                }

            }
        },2000);
    }
}
