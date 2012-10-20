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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import org.aerogear.proto.todos.R;
import org.aerogear.proto.todos.data.Project;
import org.aerogear.proto.todos.data.Tag;
import org.aerogear.proto.todos.data.Task;
import org.aerogear.proto.todos.fragments.ProjectFormFragment;
import org.aerogear.proto.todos.fragments.ProjectListFragment;
import org.aerogear.proto.todos.fragments.TagFormFragment;
import org.aerogear.proto.todos.fragments.TagListFragment;
import org.aerogear.proto.todos.fragments.TaskFormFragment;
import org.aerogear.proto.todos.fragments.TaskListFragment;

/**
 * @author <a href="mailto:marko.strukelj@gmail.com">Marko Strukelj</a>
 */
public class MainActivity extends SherlockFragmentActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);

        if (savedInstanceState == null) {

            fragmentTransaction = getSupportFragmentManager().beginTransaction();

            if (isTablet()) {
                fragmentTransaction
                        .replace(R.id.project, new ProjectListFragment())
                        .replace(R.id.tag, new TagListFragment())
                        .replace(R.id.task, new TaskListFragment());
            } else {
                fragmentTransaction.replace(R.id.todo, new TaskListFragment());
            }

            fragmentTransaction.commit();

        }

    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.todo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return !isTablet();
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_project:
                showProjectList();
                break;
            case R.id.menu_item_tag:
                showTagList();
                break;
            case R.id.menu_item_task:
                showTaskList();
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public void showProjectForm() {
        transaction(R.id.project, new ProjectFormFragment());
    }

    public void showProjectForm(Project project) {
        transaction(R.id.project, new ProjectFormFragment(project));
    }

    public void showProjectList() {
        transaction(R.id.project, new ProjectListFragment());
    }

    public void showTagForm() {
        transaction(R.id.tag, new TagFormFragment());
    }

    public void showTagForm(Tag tag) {
        transaction(R.id.tag, new TagFormFragment(tag));
    }

    public void showTagList() {
        transaction(R.id.tag, new TagListFragment());
    }

    public void showTaskForm() {
        transaction(R.id.task, new TaskFormFragment());
    }

    public void showTaskForm(Task task) {
        transaction(R.id.task, new TaskFormFragment(task));
    }

    public void showTaskList() {
        transaction(R.id.task, new TaskListFragment());
    }

    private void transaction(int tabletFragmentId, Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                     android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        if (isTablet()) {
            fragmentTransaction.replace(tabletFragmentId, fragment);
        } else {
            fragmentTransaction.replace(R.id.todo, fragment);
        }
        fragmentTransaction.commit();
    }
}
