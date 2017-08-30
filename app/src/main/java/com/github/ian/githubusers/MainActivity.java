package com.github.ian.githubusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.ian.githubusers.adapter.UserAdapter;
import com.github.ian.githubusers.model.Users;
import com.github.ian.githubusers.services.IGitHubHttp;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    String TAG = this.getClass().getSimpleName();
    ArrayList<Users> users = new ArrayList<Users>();
    ListView listView;
    UserAdapter adapter;
    EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.searchText);
        listView = (ListView) findViewById(R.id.listView);

        initList();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equals("")){
                    //reset list View
                    initList();
                }else{
                    //perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //click item event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Intent intent;

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                Users repo = users.get(arg2);
                Log.e(TAG, "onItemClick " + arg2);
                intent = new Intent(getApplicationContext(), DetailViewActivity.class);
                intent.putExtra("arg2", repo.getId());
                intent.putExtra("arg3", repo.getLogin());
                startActivity(intent);
            }

        });


    }

    public void initList(){
        //retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/").
                        addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        IGitHubHttp client = retrofit.create(IGitHubHttp.class);
        Call<ArrayList<Users>> call = client.findAllUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {

                users = response.body();
                Log.i("Data", response.body().toString());
                adapter = new UserAdapter(getApplicationContext(), users);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchItem(String text){

        // Get an iterator.

        Iterator<Users> ite = users.iterator();
        while(ite.hasNext()) {
            Users value = ite.next();
            if(!value.getLogin().contains(text)) {
                ite.remove();
            }


            Log.i("New Data#########", ite.toString());


        }
        adapter.notifyDataSetChanged();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}



