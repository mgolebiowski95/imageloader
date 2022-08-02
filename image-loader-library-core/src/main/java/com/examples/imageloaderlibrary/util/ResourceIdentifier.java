package com.examples.imageloaderlibrary.util;

import android.content.res.Resources;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mariusz
 */
public class ResourceIdentifier {
    private String entry;
    private String defType;
    private String defPackage;

    public ResourceIdentifier() {
    }

    public ResourceIdentifier(String entry, String defType, String defPackage) {
        this.entry = entry;
        this.defType = defType;
        this.defPackage = defPackage;
    }

    /**
     * @param fullyQualifiedResourceName package:type/entry
     */
    public ResourceIdentifier(String fullyQualifiedResourceName) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("^[a-zA-Z.]+:[a-zA-Z]+\\/[1-9a-z_]+$");
        Matcher matcher = pattern.matcher(fullyQualifiedResourceName);
        if (matcher.matches()) {
            pattern = Pattern.compile("[1-9a-zA-Z._]+");
            matcher = pattern.matcher(fullyQualifiedResourceName);
            matcher.find();
            defPackage = matcher.group();
            matcher.find();
            defType = matcher.group();
            matcher.find();
            entry = matcher.group();
        }
    }

    public ResourceIdentifier(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            if (jsonObject.has("entry"))
                entry = jsonObject.getString("entry");
            if (jsonObject.has("defType"))
                defType = jsonObject.getString("defType");
            if (jsonObject.has("defPackage"))
                defPackage = jsonObject.getString("defPackage");
        }
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getDefType() {
        return defType;
    }

    public void setDefType(String defType) {
        this.defType = defType;
    }

    public String getDefPackage() {
        return defPackage;
    }

    public void setDefPackage(String defPackage) {
        this.defPackage = defPackage;
    }

    public String getFullyQualifiedResourceName() {
        return defPackage + ":" + defType + "/" + entry;
    }

    public int getIdentifier(Resources resources) {
        try {
            return resources.getIdentifier(entry, defType, defPackage);
        } catch (Exception ignore) {
            return View.NO_ID;
        }
    }

    @Override
    public String toString() {
        return getFullyQualifiedResourceName();
    }
}
