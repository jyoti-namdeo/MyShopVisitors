package com.shop.visitors.Service;

import android.content.Context;

import com.google.gson.Gson;
import com.shop.visitors.PeopleHereJsonResponse;
import com.shop.visitors.model.Shop;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Simple data store to retrieve Shop data.
 * ***DO NOT EDIT***
 */
public final class ShopService {
    private static ShopService instance;

    public static String peopleFile = "visitors.json";

    public static ShopService get() {
        if (instance == null) {
            instance = new ShopService();
        }
        return instance;
    }

    private ShopService() {
    }

    public Shop getVenue(Context context) {
        try {
            InputStream is = context.getAssets().open(peopleFile);
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            PeopleHereJsonResponse response = new Gson().fromJson(inputStreamReader, PeopleHereJsonResponse.class);
            return response.getVenue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
