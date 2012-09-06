/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.aerogear.proto.todos.fragments;

import android.R;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import org.aerogear.proto.todos.Constants;
import org.aerogear.proto.todos.data.Task;
import org.aerogear.proto.todos.services.ToDoAPIService;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends ListFragment {
    // TODO: Sharing this via static fields isn't great.
    // TODO: Move to Parcelables (which can go in the broadcast) or local DB/ContentProvider (persistent caching)
    public static List<Task> tasks = new ArrayList<Task>();

    private BroadcastReceiver taskRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Tasks have been refreshed.
            adapter.notifyDataSetChanged();
        }
    };

    ArrayAdapter<Task> adapter;

    public ToDoListFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<Task>(getActivity(), R.layout.simple_list_item_1,
                                             tasks);
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final Task task = tasks.get(position);
                new AlertDialog.Builder(getActivity())
                        .setMessage("Delete '" + task.getTitle() + "'?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startDelete(task);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // TODO: Helper APIs to make this simpler (callbacks instead of broadcasts)?
        getActivity().registerReceiver(taskRefreshReceiver, new IntentFilter(Constants.ACTION_REFRESH_TASKS));
        startRefresh();
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(taskRefreshReceiver);
    }

    private void startRefresh() {
        Intent intent = new Intent(getActivity(), ToDoAPIService.class);
        intent.setAction(Constants.ACTION_REFRESH_TASKS);
        getActivity().startService(intent);
    }

    private void startDelete(Task task) {
        Intent intent = new Intent(getActivity(), ToDoAPIService.class);
        intent.setAction(Constants.ACTION_DELETE_TASK);
        intent.putExtra(Constants.EXTRA_TASK_ID, task.getId());
        getActivity().startService(intent);
    }
}
