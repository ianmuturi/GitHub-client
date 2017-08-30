package com.github.ian.githubusers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ian.githubusers.model.User;
import com.github.ian.githubusers.services.IGitHubHttp;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ian on 8/29/2017.
 */
public class DetailViewActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private int arg2;
    String message;
    String profile;

    TextView name;
    TextView followers;
    TextView url;
    ImageView avatar;
    Button button;
    User repos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arg2 = getIntent().getIntExtra("arg2", 0);
        setContentView(R.layout.github_list_item);
        Log.e(TAG, "onItemClick View " + arg2);

        name = (TextView)findViewById(R.id.name);
        followers = (TextView)findViewById(R.id.followers);
        url = (TextView)findViewById(R.id.html_url);
        avatar = (ImageView)findViewById(R.id.avatar_url);
        button = (Button)findViewById(R.id.button);

        //retrofit builder
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/").
                        addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        IGitHubHttp client = retrofit.create(IGitHubHttp.class);
        Call<User> call= client.findUserByID(arg2);
        call.enqueue(new Callback<User>() {
            public Context context;

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                repos = response.body();
                Log.i("Data", response.body().toString());

                //set the name textview
                name.setText(repos.getName());

                //loading image url in image view
                Picasso.with(getApplicationContext())
                        .load(repos.getAvatar_url())
                        .into(avatar);

                //set followers textview
                followers.setText("" + repos.getFollowers());
                //set github url
                url.setText(repos.getHtml_url());
                message = repos.getHtml_url();
                profile = repos.getHtml_url();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

                //error toast
                Toast.makeText(DetailViewActivity.this, "error", Toast.LENGTH_LONG).show();
            }
        });

        //url onclick event
        url.setMovementMethod(LinkMovementMethod.getInstance());
        url.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(profile));
                startActivity(browserIntent);
            }
        });



        //button onclick event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Github Profile"));


            }
        });
    }


}
