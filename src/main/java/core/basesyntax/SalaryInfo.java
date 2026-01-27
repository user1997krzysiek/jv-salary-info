package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        Map<String, Integer> salaryMap = new HashMap<>();

        for (String line : data) {
            String[] parts = line.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];

            if (!Arrays.asList(names).contains(name)) {
                continue;
            }

            if (currentDate.isBefore(from) || currentDate.isAfter(to)) {
                continue;
            }
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            salaryMap.put(name, salaryMap.getOrDefault(name, 0) + hours * rate);
        }
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " _ " + dateTo + "\n");
        for (String name : names) {
            report.append(name).append(" _ ").append(salaryMap.getOrDefault(name, 0)).append("\n");
        }

        return report.toString().trim();
    }
}