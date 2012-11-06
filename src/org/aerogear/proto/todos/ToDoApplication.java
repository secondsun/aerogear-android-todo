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
import org.aerogear.android.impl.pipeline.PipeConfig;
import org.aerogear.proto.todos.data.Project;
import org.aerogear.proto.todos.data.Tag;
import org.aerogear.proto.todos.data.Task;

import java.net.MalformedURLException;
import java.net.URL;

public class ToDoApplication extends Application {

    private Pipeline pipeline;

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            URL baseURL = new URL("http://todo-aerogear.rhcloud.com/todo-server");

            // Set up Pipeline
            pipeline  = new Pipeline(baseURL);

            PipeConfig pipeConfigTask = new PipeConfig(baseURL, Task.class);
            pipeConfigTask.setName("tasks");
            pipeConfigTask.setEndpoint("tasks");
            pipeline.pipe(Task.class, pipeConfigTask);

            PipeConfig pipeConfigTag = new PipeConfig(baseURL, Tag.class);
            pipeConfigTag.setName("tags");
            pipeConfigTag.setEndpoint("tags");
            pipeline.pipe(Tag.class, pipeConfigTag);

            PipeConfig pipeConfigProject = new PipeConfig(baseURL, Project.class);
            pipeConfigProject.setName("projects");
            pipeConfigProject.setEndpoint("projects");
            pipeline.pipe(Project.class, pipeConfigProject);
        } catch (MalformedURLException e) {
            // TODO Logger?
        }

    }

    public Pipeline getPipeline() {
        return pipeline;
    }
}
