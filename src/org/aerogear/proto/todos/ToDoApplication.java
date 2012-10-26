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

package org.aerogear.proto.todos;

import android.app.Application;
import org.aerogear.android.Pipeline;
import org.aerogear.proto.todos.data.Project;
import org.aerogear.proto.todos.data.Tag;
import org.aerogear.proto.todos.data.Task;

public class ToDoApplication extends Application {
    private Pipeline pipeline;

    @Override
    public void onCreate() {
        super.onCreate();

        // Set up Pipeline
        pipeline  = new Pipeline(Constants.ROOT_URL);
        pipeline.add("tasks", Task.class);
        pipeline.add("tags", Tag.class);
        pipeline.add("projects", Project.class);
    }

    public Pipeline getPipeline() {
        return pipeline;
    }
}
