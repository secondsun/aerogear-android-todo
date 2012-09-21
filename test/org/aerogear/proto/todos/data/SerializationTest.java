/*
 * Copyright (c) RedHat, 2012.
 */

package org.aerogear.proto.todos.data;

import com.google.gson.Gson;
import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.aerogear.android.AeroGear;
import org.aerogear.android.Utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    public void testRobolectricHTTP() throws Exception {
        String TEST = "adf goi g ";
        Robolectric.addPendingHttpResponse(200, TEST);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("url");
        final HttpResponse response = client.execute(get);
        final InputStream inputStream = response.getEntity().getContent();
        final String next = new Scanner(inputStream).useDelimiter("\\A").next();
        assertThat(next, equalTo(TEST));
    }

    @Test
    public void testGet() throws Exception {
        Task exampleItem = new Task("Test title");
        Robolectric.addPendingHttpResponse(200, gson.toJson(exampleItem));

        AeroGear.setUtils(new Utils()); // TODO: Remove this after fixing test concurrency/mocking issues

        Task item = AeroGear.get("tasks/1", Task.class);
        assertThat(item, equalTo(exampleItem));
    }
}
