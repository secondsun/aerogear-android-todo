/*
 * Copyright (c) RedHat, 2012.
 */

package org.aerogear.proto.todos.fragments;

import android.R;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import org.aerogear.proto.todos.data.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFragment extends ListFragment {
    ArrayAdapter<ToDoItem> adapter;

    List<ToDoItem> items = new ArrayList<ToDoItem>();

    public ToDoListFragment() {
        items = new ArrayList<ToDoItem>();
        items.add(new ToDoItem("Wash the car"));
        items.add(new ToDoItem("Walk the dog"));
        items.add(new ToDoItem("Finish the Android example app"));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<ToDoItem>(getActivity(), R.layout.simple_list_item_1,
                                             items);
        setListAdapter(adapter);
    }
}
