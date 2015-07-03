package com.project.lopez.myprojectapplication.main;

import android.content.Context;
import android.widget.Toast;

import com.project.lopez.myprojectapplication.R;

import static com.project.lopez.myprojectapplication.R.string.action_settings;
import static com.project.lopez.myprojectapplication.R.string.hello_world;

/**
 * Created by Administrator on 7/1/2015.
 */
public class ItemPresenterImp implements IitemPresenter , onFinishListener{

    Context context;
    private IitemView view;
    private IitemConnector connect;
    MainActivity yeah;


    public ItemPresenterImp(IitemView item, Context context, MainActivity main)
    {
        this.view = item;
        this.context = context;
        this.connect = new ItemConnectorImp();
        this.yeah=main;
    }


    @Override
    public void validateItem(String name) {
        view.showProgress();
        connect.put(name,this,context,yeah);
    }

    @Override
    public void onItemError() {
        view.setItemError();
        view.hideProgress();
    }

    @Override
    public void Success() {

        Toast.makeText(context,"asdsa",Toast.LENGTH_SHORT).show();
        view.hideProgress();
    }
}
