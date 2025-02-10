package id.asqi.idesa.bumdes.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Constants {
    private static final List<String> message = new ArrayList<>();
    private static final Map<String, String> mapMessage = new HashMap<>();
    public static Type typeMapString = new TypeToken<Map<String, String>>() {}.getType();


    public static Long idGenerator () {
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        return Long.valueOf(Timestamp.valueOf(LocalDateTime.now()).getTime() + "" + x);
    }

    static String splitCamelCase (String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

    public static String splitCamelCaseToSnake (String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                "_"
        );
    }

    public static String pascalToSentenceCase (String pascalCaseString) {
        String result = pascalCaseString.replaceAll("([a-z])([A-Z])", "$1 $2");
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

    public static List<String> errorMessage (Errors errors) {
        message.clear();
        errors.getAllErrors().forEach((error) -> {
            String fieldName = Constants.splitCamelCase(((FieldError) error).getField());
            String errorMessage;
            if (Objects.equals(error.getCode(), "typeMismatch")) {
                errorMessage = "Type data salah";
            } else {
                errorMessage = error.getDefaultMessage();
            }
            message.add(fieldName + " " + errorMessage);
        });
        return message;
    }

    public static Map<String, String> validateErrorMessage (Errors errors) {
        mapMessage.clear();
        errors.getAllErrors().forEach((error) -> {
            String errorMessage;
            if (Objects.equals(error.getCode(), "typeMismatch")) {
                errorMessage = "Type data salah";
            } else {
                errorMessage = error.getDefaultMessage();
            }
            mapMessage.put(((FieldError) error).getField(), errorMessage);
        });
        return mapMessage;
    }

    public static List<Map<String, Object>> getProcedureName (String step, String map) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (map.isEmpty()) {
            Map<String, Object> data1 = new HashMap<>();
            data1.put("step", "1");
            data1.put("title", "Data Usaha/Instansi");
            data1.put("done", "false");

            Map<String, Object> data2 = new HashMap<>();
            data2.put("step", "2");
            data2.put("title", "Pemilik / Penanggung Jawab");
            data2.put("done", "false");

            Map<String, Object> data3 = new HashMap<>();
            data3.put("step", "3");
            data3.put("title", "Rekening & Adm");
            data3.put("done", "false");

            Map<String, Object> data4 = new HashMap<>();
            data4.put("step", "4");
            data4.put("title", "Dokumen");
            data4.put("done", "false");

            dataList.add(data1);
            dataList.add(data2);
            dataList.add(data3);
            dataList.add(data4);
        } else {
            dataList = new Gson().fromJson(map, new TypeToken<List<Map<String, Object>>>() {}.getType());
        }
        for (Map<String, Object> data : dataList) {
            if (step.equals(data.get("step"))) {
                data.put("done", "true");
            }
        }
        return dataList;
    }

    public static List<String> getMonths (LocalDate firstDate, LocalDate finalDate) {
        List<String> months = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.forLanguageTag("id"));
        LocalDate currentDate = firstDate;
        while (! currentDate.isAfter(finalDate)) {
            months.add(currentDate.format(formatter));
            currentDate = currentDate.plusMonths(1);
        }
        return months;
    }

}