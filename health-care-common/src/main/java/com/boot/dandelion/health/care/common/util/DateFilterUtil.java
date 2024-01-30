package com.boot.dandelion.health.care.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DateFilterUtil<T> {
    public List<T> filterDataByDate(List<T> data, String start, String end, DateExtractor<T> dateExtractor) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Predicate<T> dateFilter = dataItem -> {
            Date dataDate = dateExtractor.extractDate(dataItem);
            return dataDate.compareTo(startDate) >= 0 && dataDate.compareTo(endDate) <= 0;
        };

        List<T> filteredData = data.stream()
                .filter(dateFilter)
                .collect(Collectors.toList());
        return filteredData;
    }

    public interface DateExtractor<T> {
        Date extractDate(T object);
    }
}