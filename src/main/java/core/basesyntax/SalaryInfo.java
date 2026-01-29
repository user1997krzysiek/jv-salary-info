package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());

        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                String[] parts = line.trim().split("\\s+");
                LocalDate currentDate = LocalDate.parse(parts[0], formatter);
                String currentName = parts[1];

                if (!name.equalsIgnoreCase(currentName)) {
                    continue;
                }

                if (currentDate.isBefore(from) || currentDate.isAfter(to)) {
                    continue;
                }
                int hours = Integer.parseInt(parts[2]);
                int rate = Integer.parseInt(parts[3]);
                totalSalary += hours * rate;
            }
            report.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
