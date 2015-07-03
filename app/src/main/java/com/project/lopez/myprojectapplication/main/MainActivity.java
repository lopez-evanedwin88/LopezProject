package com.project.lopez.myprojectapplication.main;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.lopez.myprojectapplication.R;

import java.util.ArrayList;

import static com.project.lopez.myprojectapplication.R.string.item_error;


public class MainActivity extends ActionBarActivity implements IitemView, View.OnClickListener {

    EditText edtname;
   // Button btnadd;
    ListView item;
    ArrayAdapter<String> list;
    ProgressBar progressBar;
    IitemPresenter itmpres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.bar);
        edtname = (EditText) findViewById(R.id.edtName);
        findViewById(R.id.addbtn).setOnClickListener(this);
        item = (ListView) findViewById(R.id.listView);
        progressBar.setVisibility(View.GONE);
        list = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>());
        item.setAdapter(list);
        populateView();
        itmpres = new ItemPresenterImp(this,this,this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sharebtn) {
            Cursor c = this.getContentResolver().query(Provider.CONTENT_URI, null, null, null, null);

            ArrayList<String> itemList = new ArrayList<String>();

            if (c.moveToFirst())
            {
                do
                {
                    itemList.add(c.getString(c.getColumnIndex("_name")));
                }
                while (c.moveToNext());
            }
            Toast.makeText(this,itemList.toString().trim(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();

            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putStringArrayListExtra("pass",itemList);
            intent.createChooser(intent,"Share via");
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItemError() {
        edtname.setError(getString(R.string.item_error));

    }

    @Override
    public void onClick(View v) {
        itmpres.validateItem(edtname.getText().toString());
        edtname.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void populateView()
    {
        Cursor c = this.getContentResolver().query(Provider.CONTENT_URI, null, null, null, null);

     //   ArrayList<String> itemList = new ArrayList<String>();

        if (c.moveToFirst())
        {
            do
            {
                list.add(c.getString(c.getColumnIndex("_name")));
            }
            while (c.moveToNext());
        }
        list.notifyDataSetChanged();

    }
}
