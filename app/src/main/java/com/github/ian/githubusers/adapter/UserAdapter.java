
package com.github.ian.githubusers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.github.ian.githubusers.R;
import com.github.ian.githubusers.model.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ian on 8/29/2017.
 */

public class UserAdapter extends ArrayAdapter<Users> implements Filterable {

    private Context context;
    private List<Users> values;
    private List<Users> users =null;
    private ArrayList<Users> arraylist;
    private ArrayList<Users> mOriginalValues; // Original Values
    private ArrayList<Users> mDisplayedValues;

    public UserAdapter(Context context, ArrayList<Users> values) {
        super(context, R.layout.activity_main, values);

        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row, parent, false);
        }

        TextView name = (TextView) row.findViewById(R.id.textView2);
        ImageView avatar = (ImageView) row.findViewById(R.id.imageView);

        Users item = values.get(position);
        name.setText(item.getLogin());
        Picasso.with(context).load(item.getAvatar_url()).into(avatar);

        return row;
    }


}


