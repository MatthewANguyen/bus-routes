/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.MyApp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.MyApp.content.RouteUtility;

import java.util.List;

/**
 * An activity representing a list of song titles (items). When one is
 * touched, an intent starts {@link RouteDetailActivity} representing
 * song details.
 */
public class RouteActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private boolean mTwoPane = false;

    /**
     * Sets up a route list as a RecyclerView.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        // Set the toolbar as the app bar.
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle(getTitle());

        // Get the song list as a RecyclerView.
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.song_list);
        recyclerView.setAdapter
                (new SimpleItemRecyclerViewAdapter(RouteUtility.ROUTE_ITEM));

        if(findViewById(R.id.song_detail_container) != null) {
            mTwoPane = true;
        }
    }

    /**
     * The ReyclerView for the song list.
     */
    class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter
            <SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<RouteUtility.Route> mValues;

        SimpleItemRecyclerViewAdapter(List<RouteUtility.Route> items) {
            mValues = items;
        }

        /**
         * This method inflates the layout for the song list.
         * @param parent ViewGroup into which the new view will be added.
         * @param viewType The view type of the new View.
         * @return
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.route_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * This method implements a listener with setOnClickListener().
         * When the user taps a route, the code checks if mTwoPane
         * is true, and if so uses a fragment to show the route detail.
         * If mTwoPane is not true, it starts RouteDetailActivity
         * using an intent with extra data about which route was selected.
         *
         * @param holder   ViewHolder
         * @param position Position of the song in the array.
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(String.valueOf(position + 1));
            holder.mRouteName.setText(mValues.get(position).route_title);
            holder.mAmpm.setText(mValues.get(position).details);
            holder.mStart.setText(mValues.get(position).start);
            holder.mEnd.setText(mValues.get(position).end);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mTwoPane){
                        int selectedSong = holder.getAdapterPosition();
                        RouteDetailFragment fragment = new RouteDetailFragment().newInstance(selectedSong);
                        getSupportFragmentManager().beginTransaction().replace(R.id.song_detail_container, fragment).addToBackStack(null).commit();
                    }else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context,
                                RouteDetailActivity.class);
                        intent.putExtra(RouteUtility.ROUTE_ID_KEY,
                                holder.getAdapterPosition());
                        context.startActivity(intent);
                    }
                }
            });
        }

        /**
         * Get the count of route list items.
         * @return
         */
        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * ViewHolder describes an item view and metadata about its place
         * within the RecyclerView.
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mIdView;
            final TextView mRouteName;
            final TextView mAmpm;
            final TextView mStart;
            final TextView mEnd;
            RouteUtility.Route mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mRouteName = (TextView) view.findViewById(R.id.content);
                mAmpm = (TextView) view.findViewById(R.id.ampm);
                mStart = (TextView) view.findViewById(R.id.start);
                mEnd = (TextView) view.findViewById(R.id.end);

            }
        }
    }
}
