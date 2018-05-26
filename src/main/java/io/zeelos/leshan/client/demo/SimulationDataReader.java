package io.zeelos.leshan.client.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SimulationDataReader {

    public static List<String> seasonalModels = Collections.unmodifiableList(Arrays.asList(
            "sineLowVarShort", "sineLowVarMedium", "sineLowVarLong",
            "sineTrendLowVar", "austourists"));

    public static List<String> nonSeasonalModels = Collections.unmodifiableList(Arrays.asList(
            "wnLowVariance", "wnHighVariance",
            "trendStatUpwardLowVar", "trendStatUpwardHighVar",
            "trendStatDownwardLowVar", "trendStatDownwardHighVar"));

    public static List<DataPoint> getData(String fileName) throws IOException {
        InputStream in = SimulationDataReader.class.getResourceAsStream("/simulation/" + fileName);
        Reader reader = new BufferedReader(new InputStreamReader(in));

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withAllowMissingColumnNames(true)
                .withHeader("")
                .parse(reader);

        List<DataPoint> dataPoints = new ArrayList<>();
        long counter = 0;
        for (CSVRecord record : records) {
            String value = record.get(0);

            Double doubleValue = null;
            try {
                doubleValue = Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                continue;
            }

            DataPoint dataPoint = new DataPoint(doubleValue, counter++);
            dataPoints.add(dataPoint);
        }

        return dataPoints;
    }
}
