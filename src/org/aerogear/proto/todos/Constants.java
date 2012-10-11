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

public class Constants {
    public static final String ROOT_URL = "http://todo-aerogear.rhcloud.com/todo-server";

    public static final String TAGS = "tags";
    public static final String TASKS = "tasks";
    public static final String PROJECTS = "projects";

    public static final String ACTION_REFRESH_COLLECTIONS = "org.aerogear.actions.refresh_collections";
    public static final String EXTRA_COLLECTION_NAME = "org.aerogear.extras.collection_name";

    public static final String ACTION_POST_TASK = "org.aerogear.actions.post_task";
    public static final String ACTION_DELETE_TASK = "org.aerogear.action.delete_task";
    public static final String EXTRA_TASK = "org.aerogear.extras.task";
    public static final String EXTRA_TASK_ID = "org.aerogear.extras.task_id";

    public static final String ACTION_POST_PROJECT = "org.aerogear.actions.post_project";
    public static final String ACTION_DELETE_PROJECT = "org.aerogear.action.delete_project";
    public static final String EXTRA_PROJECT = "org.aerogear.extras.project";
    public static final String EXTRA_PROJECT_ID = "org.aerogear.extras.project_id";
    
    public static final String ACTION_POST_TAG = "org.aerogear.actions.post_tag";
    public static final String ACTION_DELETE_TAG = "org.aerogear.action.delete_tag";
    public static final String EXTRA_TAG = "org.aerogear.extras.tag";
    public static final String EXTRA_TAG_ID = "org.aerogear.extras.tag_id";
}
