package com.bousso.logger;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eytan on 05/08/17.
 */

public abstract class LogBackend implements ILogBackend {
    IConfiguration configuration;
    Map<String, LogMessageStringExtractor> extractors;
    Pattern expressionPattern;

    public LogBackend(IConfiguration configuration) {
        this.configuration = configuration;
        extractors = new HashMap<>();
        expressionPattern = Pattern.compile("<\\*.*\\*>");
        extractors.put("component", new ComponentExtractor());
        extractors.put("error", new ErrorExtractor());
        extractors.put("message", new MessageTextExtractor());
        extractors.put("time", new TimeExtractor());
    }

    public void addPatternVariable(String varName, LogMessageStringExtractor func) {
        if (extractors.containsKey(varName)) {
            throw new IllegalArgumentException(
                    String.format("Pattern %s already exists in the Backend parser", varName));
        }

        extractors.put(varName, func);
    }

    // Returns message after replacing tokens
    private String parseMessage(LogMessage message) {
        String pattern = this.configuration.getPattern();
        for (String key : extractors.keySet()) {
            key = key.toLowerCase();
            pattern = pattern.replaceAll("<\\*"+key+"\\*>",
                    extractors.get(key).extractFromMessage(message));
        }

        if (expressionPattern.matcher(pattern).find()) {
            throw new IllegalArgumentException(
                    "Pattern for log messages should not contain unknown keys. (e.g. <*some unknown string*>)");
        }

        return pattern;
    }
}

class MessageTextExtractor implements LogMessageStringExtractor {
    @Override
    public String extractFromMessage(LogMessage message) {
        return message.getMessage();
    }
}

class ErrorExtractor implements LogMessageStringExtractor {
    @Override
    public String extractFromMessage(LogMessage message) {
        Error error = message.getError();
        if (error == null) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(error.getMessage());
        builder.append("\n");
        for (StackTraceElement element : error.getStackTrace()) {
            builder.append(element.toString());
            builder.append("\n");
        }

        return builder.toString();
    }
}

class TimeExtractor implements LogMessageStringExtractor {
    @Override
    public String extractFromMessage(LogMessage message) {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }
}

class ComponentExtractor implements LogMessageStringExtractor {
    @Override
    public String extractFromMessage(LogMessage message) {
        return message.getComponent();
    }
}