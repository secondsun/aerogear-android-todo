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

package org.aerogear.proto.todos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import org.aerogear.android.AeroGear;
import org.aerogear.proto.todos.Constants;
import org.aerogear.proto.todos.R;
import org.aerogear.proto.todos.services.ToDoAPIService;

/**
 * @author <a href="mailto:marko.strukelj@gmail.com">Marko Strukelj</a>
 */
public class MainActivity extends SherlockFragmentActivity {
    Button postButton;
    EditText taskTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        postButton = (Button)findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = taskTitle.getText().toString();
                if (title.length() < 1) {
                    taskTitle.setError("Please enter a title");
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ToDoAPIService.class);
                intent.setAction(Constants.ACTION_POST_TASK);
                intent.putExtra(Constants.EXTRA_TASK_TITLE, title);
                startService(intent);

                taskTitle.setText(null);
            }
        });

        taskTitle = (EditText)findViewById(R.id.task_title);

        AeroGear.initialize(Constants.API_KEY, Constants.ROOT_URL);
    }
}
