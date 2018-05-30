/*
 *  Copyright (c) Microsoft. All rights reserved.
 *  Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.deps.twin;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Adapter for a gson object that serializes nulls
 */
public class JsonNullableAdapter extends TypeAdapter<TwinCollection>
{
    final TypeAdapter<JsonElement> elementAdapter = new Gson().getAdapter(JsonElement.class);
    final TypeAdapter<Object> objectAdapter = new Gson().getAdapter(Object.class);

    @Override
    public void write(JsonWriter out, TwinCollection value) throws IOException
    {
        out.setSerializeNulls(true);
        JsonElement tree = objectAdapter.toJsonTree(value);
        elementAdapter.write(out, tree);
        out.setSerializeNulls(false);
    }

    @Override
    public TwinCollection read(JsonReader in) throws IOException {
        in.beginObject();
        TwinCollection twinCollection = new TwinCollection();
        while (in.hasNext())
        {
            String name = in.nextName();
            String value = in.nextString();
            twinCollection.put(name, value);
            System.out.println();
        }
        in.endObject();
        return twinCollection;
    }
}
