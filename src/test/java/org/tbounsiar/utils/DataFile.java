package org.tbounsiar.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class DataFile {

    public enum Type {
        JSON,
        //        BSON,
        CSV
    }

    private final String path;
    private final Type type;

    public List<Document> get() {
        var content = "/data/" + path;
        return switch (type) {
            case JSON -> TestHelper.readFromBson(content);
            case CSV -> TestHelper.readFromCsv(content);
        };
    }

    public String getContent() {
        var content = "/data/" + path;
        return TestHelper.readFile(content);
    }

    public Map<String, ?> getMap() {
        var content = "/data/" + path;
        return TestHelper.readFromJson(content, new TypeReference<>() {});
    }

}
