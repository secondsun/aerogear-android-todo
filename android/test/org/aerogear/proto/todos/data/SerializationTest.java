/*
 * Copyright (c) RedHat, 2012.
 */

package org.aerogear.proto.todos.data;

import com.google.gson.Gson;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.aerogear.android.AeroGear;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class SerializationTest {
    Gson gson = new Gson();

    List<Task> items = new ArrayList<Task>();

    @Test
    public void testRoundTripSerialization() throws Exception {
        items = new ArrayList<Task>();
        items.add(new Task("Wash the car"));
        items.add(new Task("Walk the dog"));
        items.add(new Task("Finish the Android example app"));

        String jsonString = gson.toJson(items);

        StringReader reader = new StringReader(jsonString);
        final Task[] results = gson.fromJson(reader, Task[].class);

        assertThat(results.length, equalTo(3));
        Task item1 = results[0];
        assertThat(item1.getClass().getName(), equalTo(Task.class.getName()));
        assertThat(item1.getTitle(), equalTo("Wash the car"));
    }

    @Test
    public void testGet() throws Exception {
        Task exampleItem = new Task("Test title");
        Robolectric.addPendingHttpResponse(200, gson.toJson(exampleItem));

        Task item = AeroGear.get("tasks/1", Task.class);
        assertThat(item, equalTo(exampleItem));
    }
}
