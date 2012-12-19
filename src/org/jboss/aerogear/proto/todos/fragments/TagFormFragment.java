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

package org.jboss.aerogear.proto.todos.fragments;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.pipeline.Pipe;
import org.jboss.aerogear.proto.todos.R;
import org.jboss.aerogear.proto.todos.ToDoApplication;
import org.jboss.aerogear.proto.todos.activities.TodoActivity;
import org.jboss.aerogear.proto.todos.data.Tag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TagFormFragment extends Fragment {

	private final Tag tag;

	public TagFormFragment() {
		tag = new Tag();
	}

	public TagFormFragment(Tag tag) {
		this.tag = tag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.form, null);

		final TextView title = (TextView) view.findViewById(R.id.title);
		final EditText name = (EditText) view.findViewById(R.id.name);
		final Button buttonSave = (Button) view.findViewById(R.id.buttonSave);
		final Button buttonCancel = (Button) view
				.findViewById(R.id.buttonCancel);

		title.setText(getResources().getString(R.string.tags));

		if (tag.getId() != null) {
			buttonSave.setText(R.string.update);
		}

		name.setText(tag.getTitle());

		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (name.getText().toString().length() < 1) {
					name.setError("Please enter a title");
					return;
				}

				tag.setTitle(name.getText().toString());

				Pipe<Tag> pipe = ((ToDoApplication) getActivity()
						.getApplication()).getPipeline().get("tags");
				pipe.save(tag, new Callback<Tag>() {
					@Override
					public void onSuccess(Tag data) {
						((TodoActivity) getActivity()).showTagList();
					}

					@Override
					public void onFailure(Exception e) {
						Toast.makeText(getActivity(),
								"Error saving tag: " + e.getMessage(),
								Toast.LENGTH_LONG).show();
					}
				});
			}
		});

		buttonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				((TodoActivity) getActivity()).showTagList();
			}
		});

		return view;

	}
}
