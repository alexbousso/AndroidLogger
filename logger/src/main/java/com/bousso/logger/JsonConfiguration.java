package com.bousso.logger;

import android.provider.MediaStore;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonConfiguration implements IConfiguration {
    private JSONObject jsonObject;

    public JsonConfiguration(File file) throws IOException, JSONException {
        String text = readTextFromFile(file);
        jsonObject = new JSONObject(text);
    }

    @NonNull
    private String readTextFromFile(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(file));

        try {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } finally {
            br.close();
        }

        return sb.toString();
    }

    @Override
    public String getPattern() {
        try {
            return jsonObject.getString("pattern");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LogLevel getMinimumLogLevel() {
        try {
            return LogLevel.valueOf(jsonObject.getString("logLevel"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
