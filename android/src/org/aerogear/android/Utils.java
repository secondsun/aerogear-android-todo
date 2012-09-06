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

package org.aerogear.android;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Put specific network code behind a few utility APIs for the moment.
 *
 * TODO: Build this right, DRY it up, etc.
 */
class Utils {
    public static final String TAG = "AeroGear";

    static InputStream getDataStream(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(getServerURL(url));

        // TODO: Figure out appropriate headers, authentication, etc.
        get.addHeader("X-AeroGear-Client", AeroGear.apiKey);

        try {
            final HttpResponse response = client.execute(get);
            return response.getEntity().getContent();
        } catch (IOException e) {
            // TODO: Real error handling
            Log.e(TAG, "Error on GET of " + getServerURL(url), e);
            return null;
        }
    }

    static InputStream post(String url, String data) {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(getServerURL(url));
        BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(new ByteArrayInputStream(data.getBytes()));
        entity.setContentType("application/json");
        post.setEntity(entity);

        try {
            final HttpResponse response = client.execute(post);
            return response.getEntity().getContent();
        } catch (IOException e) {
            // TODO: Real error handling
            Log.e(TAG, "Error on POST of " + getServerURL(url), e);
            return null;
        }
    }

    static String getServerURL(String url) {
        return AeroGear.rootUrl + "/" + url;
    }

    public static void delete(String url) {
        HttpClient client = new DefaultHttpClient();
        HttpDelete delete = new HttpDelete(getServerURL(url));
        try {
            client.execute(delete);
        } catch (IOException e) {
            // TODO: Real error handling
            Log.e(TAG, "Error on DELETE of " + getServerURL(url), e);
        }
    }
}
