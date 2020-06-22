package com.rust.sip.GB28181.tools;
import android.text.TextUtils;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONHelper {

    public static <T> T fromJson(@NonNull String json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(double version, @NonNull String json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            return new GsonBuilder()
                    .setVersion(version)
                    .create()
                    .fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(@NonNull String json, Class<T> classOf, Type innerType) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOf);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    classOf,
                    innerType);
            return new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(double version, @NonNull String json, Class<T> classOf, Type innerType) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOf);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    classOf,
                    innerType);
            return new GsonBuilder()
                    .setVersion(version)
                    .create()
                    .fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(@NonNull String json, Type typeOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + typeOfT);
        try {
            return new Gson().fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(double version, @NonNull String json, Type typeOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + typeOfT);
        try {
            return new GsonBuilder()
                    .setVersion(version)
                    .create()
                    .fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(@NonNull Reader json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            return new Gson().fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(@NonNull Reader json, Type typeOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + typeOfT);
        try {
            return new Gson().fromJson(json, typeOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> fromJsonList(@NonNull String json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            return new Gson().fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> fromJsonList(double version, @NonNull String json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            return new GsonBuilder()
                    .setVersion(version)
                    .create()
                    .fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> fromJsonListWithDate(@NonNull String json, Class<T> classOfT) {
        Logger.d("Start to parse the json: " + json + " ;class: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String toJson(Object src) {
        Logger.d("Start to create the json by object: " + src);
        try {
            return new Gson().toJson(src);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toJson(Object src, Type typeOfSrc) {
        Logger.d("Start to create the json by object: " + src + " ;type: " + typeOfSrc);
        try {
            return new Gson().toJson(src, typeOfSrc);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static JSONObject toJsonObject(@NonNull Object src) {
        Logger.d("Start to create the json by object: " + src);
        try {
            return new JSONObject(new Gson().toJson(src));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject toJsonObject(@NonNull Object src, Type typeOfSrc) {
        Logger.d("Start to create the json by object: " + src + " ;type: " + typeOfSrc);
        try {
            return new JSONObject(new Gson().toJson(src, typeOfSrc));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String toJsonList(@NonNull Object json, Class<T> classOfT) {
        Logger.d("Start to create the json by object: " + json + " ;type: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            return new Gson().toJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> String toJsonList(double version, @NonNull Object json, Class<T> classOfT) {
        Logger.d("Start to create the json by object: " + json + " ;type: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            return new GsonBuilder()
                    .setVersion(version)
                    .create()
                    .toJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toJsonList(@NonNull Object json, Type type) {
        Logger.d("Start to create the json by object: " + json + " ;type: " + type);
        try {
            return new Gson().toJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String toJsonExpose(@NonNull Object src, Type typeOfSrc) {
        Logger.d("Start to create the json by object: " + src + " ;type: " + typeOfSrc);
        try {
            return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(src, typeOfSrc);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> String toJsonListExpose(@NonNull Object json, Class<T> classOfT) {
        Logger.d("Start to create the json by object: " + json + " ;type: " + classOfT);
        try {
            Type type = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(
                    null,
                    List.class,
                    classOfT);
            return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> String toJsonListExpose(@NonNull Object json, Type type) {
        Logger.d("Start to create the json by object: " + json + " ;type: " + type);
        try {
            return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Object getObject(@NonNull String json, @NonNull String key) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isJsonArray(@NonNull String json, @NonNull String key) {
        if (isString(json, key)) {
            return false;
        }

        String subJson = getString(json, key);
        return !TextUtils.isEmpty(subJson) && new JsonParser().parse(subJson).isJsonArray();
    }

    public static boolean isJsonArray(@NonNull String json) {
        return !TextUtils.isEmpty(json) && new JsonParser().parse(json).isJsonArray();
    }

    public static boolean isJsonObject(@NonNull String json, @NonNull String key) {
        if (isString(json, key)) {
            return true;
        }

        String subJson = getString(json, key);
        return !TextUtils.isEmpty(subJson) && new JsonParser().parse(subJson).isJsonObject();
    }

    public static boolean isJsonObject(@NonNull String json) {
        return !TextUtils.isEmpty(json) && new JsonParser().parse(json).isJsonObject();
    }

    public static JSONArray getJSONArray(@NonNull String json) {
        Logger.d("Get the JSONArray by json: " + json);
        try {
            return new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSONObject(@NonNull String json, @NonNull String key) {
        Logger.d("Get the JSONObject by json: " + json);
        try {
            return new JSONObject(json).getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static JSONObject getJSONObject(@NonNull String json) {
        Logger.d("Get the JSONObject by json: " + json);
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSONObject(@NonNull JSONArray jsonArray, @IntRange(from = 0) int index) {
        Logger.d("Get the JSONObject by jsonArray:");
        try {
            JSONObject jsonObject = jsonArray.optJSONObject(index);
            if (null == jsonObject) {
                jsonObject = new JSONObject(jsonArray.getString(index));
            }

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getJSONObjectString(@NonNull JSONArray jsonArray, @IntRange(from = 0) int index) {
        Logger.d("Get the JSONObject by jsonArray.");
        try {
            return jsonArray.getJSONObject(index).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isString(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return false;
        }

        Logger.d("Get the JSONObject by jsonStr: " + jsonStr);
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            return jsonObject.has(key) && jsonObject.get(key) instanceof String;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getString(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return "";
        }

        Logger.d("Get the JSONObject by jsonStr: " + jsonStr);
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (jsonObject.has(key)) {
                return jsonObject.getString(key);
            }
            return "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getString(@NonNull String jsonStr, @NonNull String key, String defaultValue) {
        if (TextUtils.isEmpty(jsonStr)) {
            return defaultValue;
        }

        Logger.d("Get the JSONObject by jsonStr: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean getBoolean(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return false;
        }

        Logger.d("Get the JSONObject by boolean: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getBoolean(@NonNull String jsonStr, @NonNull String key, boolean defaultValue) {
        if (TextUtils.isEmpty(jsonStr)) {
            return defaultValue;
        }

        Logger.d("Get the JSONObject by boolean: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getBoolean(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static int getInt(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return 0;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getInt(@NonNull String jsonStr, @NonNull String key, int defaultValue) {
        if (TextUtils.isEmpty(jsonStr)) {
            return defaultValue;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getInt(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static long getLong(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return 0L;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getLong(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static long getLong(@NonNull String jsonStr, @NonNull String key, long defaultValue) {
        if (TextUtils.isEmpty(jsonStr)) {
            return defaultValue;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getLong(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static double getDouble(@NonNull String jsonStr, @NonNull String key) {
        if (TextUtils.isEmpty(jsonStr)) {
            return 0.0;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static double getDouble(@NonNull String jsonStr, @NonNull String key, double defaultValue) {
        if (TextUtils.isEmpty(jsonStr)) {
            return defaultValue;
        }

        Logger.d("Get the JSONObject by int: " + jsonStr);
        try {
            return new JSONObject(jsonStr).getDouble(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static boolean isNull(@NonNull String jsonStr, @NonNull String key) {
        try {
            return (new JSONObject(jsonStr)).isNull(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return true;
        }
    }

    public static JSONBuild buildJSON() {
        return new JSONBuild();
    }

    public static class JSONBuild {

        private Map<String, Object> map;

        private JSONBuild() {
            this.map = new HashMap<>();
        }

        public JSONBuild put(String key, String value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, CharSequence value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, char value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, int value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, Integer value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, long value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, Long value) {
            this.map.put(key, value);
            return this;
        }

        public JSONBuild put(String key, Object value) {
            this.map.put(key, value);
            return this;
        }

        public String build() {
            return toJson(map);
        }

    }

}

