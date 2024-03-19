package org.tbounsiar.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.bson.BsonArray;
import org.bson.BsonDocumentReader;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String readFile(String path) {
        try (var in = getStream(path)) {
            assert in != null;
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getStream(String path) {
        return TestHelper.class.getResourceAsStream(path);
    }

    public static List<Document> readFromBson(String path) {
        DocumentCodec codec = new DocumentCodec();
        DecoderContext decoderContext = DecoderContext.builder().build();
        return BsonArray.parse(readFile(path))
                .stream().map(
                        bsonValue -> codec.decode(new BsonDocumentReader(bsonValue.asDocument()), decoderContext)
                ).collect(Collectors.toList());

    }

    public static List<Document> readFromCsv(String path) {
        try {
            CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(getStream(path)))
                    .withCSVParser(parser)
                    .build();
            List<String[]> csvData = reader.readAll();
            String[] headers = csvData.remove(0); // Remove and store the header row
            List<Document> documents = new ArrayList<>();
            for (String[] row : csvData) {
                Document document = new Document();
                for (int i = 0; i < row.length; i++) {
                    String fieldName = headers[i];
                    document.append(fieldName, row[i]);
                }
                documents.add(document);
            }
            return documents;
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readFromJson(String path, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(readFile(path), typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Matcher<JsonNode> json(String value) {
        try {
            return IsEqual.equalTo(mapper.readTree(value));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
