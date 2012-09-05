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

package org.aerogear.proto.todos.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import org.aerogear.android.AeroGear;
import org.aerogear.proto.todos.Constants;
import org.aerogear.proto.todos.data.Task;
import org.aerogear.proto.todos.fragments.ToDoListFragment;

import java.util.Collections;

public class ToDoAPIService extends IntentService {
    public static final String TAG = ToDoAPIService.class.getName();

    public ToDoAPIService() {
        super(TAG);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (Constants.ACTION_REFRESH_TASKS.equals(action)) {
            Log.d(TAG, "Refreshing tasks from server...");
            try {
                refreshTasks();
            } catch (Exception e) {
                Log.d(TAG, "Coudldn't refresh tasks", e);
            }
        }
    }

    private void refreshTasks() throws Exception {
        Task[] responseItems = AeroGear.get("tasks", Task[].class);

        // TODO: Sharing this via static fields isn't great.
        // TODO: Move to Parcelables (which can go in the broadcast) or local DB/ContentProvider (caching)
        ToDoListFragment.tasks.clear();
        Collections.addAll(ToDoListFragment.tasks, responseItems);

        // All set, let everyone know...
        sendBroadcast(new Intent(Constants.ACTION_REFRESH_TASKS));
    }
}
