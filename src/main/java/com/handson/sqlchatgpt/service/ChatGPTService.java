package com.handson.sqlchatgpt.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ChatGPTService {

    @Value("${authorization-key.chatgpt}")
    String authorizationKey;
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    public String createSQL(String text) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        String content = "{\"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": \n" +
                "    [{\"role\": \"system\",\n" +
                "      \"content\": \"Hey. I give you the text in row by row csv format that describes the details of the tables I have in the database. Here is the text in row by row csv format: " ;
         String tableJson =        "[\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Performance_Data\",\n" +
                "    \"column_name\": \"Student_ID\",\n" +
                "    \"ordinal_position\": 1,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Performance_Data\",\n" +
                "    \"column_name\": \"Semster_Name\",\n" +
                "    \"ordinal_position\": 2,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Performance_Data\",\n" +
                "    \"column_name\": \"Paper_ID\",\n" +
                "    \"ordinal_position\": 3,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Performance_Data\",\n" +
                "    \"column_name\": \"Paper_Name\",\n" +
                "    \"ordinal_position\": 4,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Performance_Data\",\n" +
                "    \"column_name\": \"Marks\",\n" +
                "    \"ordinal_position\": 5,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"integer\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Department_Information\",\n" +
                "    \"column_name\": \"Department_ID\",\n" +
                "    \"ordinal_position\": 1,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Department_Information\",\n" +
                "    \"column_name\": \"Department_Name\",\n" +
                "    \"ordinal_position\": 2,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Department_Information\",\n" +
                "    \"column_name\": \"DOE\",\n" +
                "    \"ordinal_position\": 3,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Employee_Information\",\n" +
                "    \"column_name\": \"Employee ID\",\n" +
                "    \"ordinal_position\": 1,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Employee_Information\",\n" +
                "    \"column_name\": \"DOB\",\n" +
                "    \"ordinal_position\": 2,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Employee_Information\",\n" +
                "    \"column_name\": \"DOJ\",\n" +
                "    \"ordinal_position\": 3,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Employee_Information\",\n" +
                "    \"column_name\": \"Department_ID\",\n" +
                "    \"ordinal_position\": 4,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Counceling_Information\",\n" +
                "    \"column_name\": \"Student_ID\",\n" +
                "    \"ordinal_position\": 1,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Counceling_Information\",\n" +
                "    \"column_name\": \"DOA\",\n" +
                "    \"ordinal_position\": 2,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Counceling_Information\",\n" +
                "    \"column_name\": \"DOB\",\n" +
                "    \"ordinal_position\": 3,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Counceling_Information\",\n" +
                "    \"column_name\": \"Department_Choices\",\n" +
                "    \"ordinal_position\": 4,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  },\n" +
                "  {\n" +
                "    \"table_catalog\": \"tzofiya\",\n" +
                "    \"table_schema\": \"public\",\n" +
                "    \"table_name\": \"Student_Counceling_Information\",\n" +
                "    \"column_name\": \"Department_ID\",\n" +
                "    \"ordinal_position\": 5,\n" +
                "    \"column_default\": null,\n" +
                "    \"is_nullable\": \"YES\",\n" +
                "    \"data_type\": \"text\",\n" +
                "  }]\n" ;

         String end = " Given this database, please bring me a SQL query.\\nPlease make sure that every time you use the name of a table you do it in the following format:  (\\\\\\\"public\\\\\\\".\\\\\\\"TABLE_NAME\\\\\\\"), also in the names of new tables you created. In addition, make sure that any name of a column you use is written in the following format: (\\\"TABLE_NAME\\\".\\\"COLUMN_NAME\\\") so that first the name of the table is inside a quote, then a point, then the name of the column is inside a quote.\\nbring me a SQL query that searches for" + text + "\"    }]}";
         String fullContent = content +jsonToCsv(tableJson).replaceAll("\n"," row:")+ end;
        RequestBody body = RequestBody.create(mediaType, fullContent);

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .method("POST", body)
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","Bearer " + authorizationKey)
                .build();
        Response response = client.newCall(request).execute();

        JSONObject jsonResponse= new JSONObject(response.body().string());
        String sql = jsonResponse.getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
        String cleanSql = sql.replaceAll("\\n", " ")
                .replaceAll("\\\\","");
        return (cleanSql);
    }

    public static String jsonToCsv(String json) {
        JSONArray jsonArray = new JSONArray(json);
        StringBuilder csv = new StringBuilder();

        // Add headers
        JSONObject firstObject = jsonArray.getJSONObject(0);
        for (String key : firstObject.keySet()) {
            csv.append(key).append(",");
        }
        csv.deleteCharAt(csv.length() - 1); // Remove the last comma
        csv.append("\n");

        // Add rows
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                csv.append(value instanceof JSONObject ? "\"" + value + "\"" : value).append(",");
            }
            csv.deleteCharAt(csv.length() - 1); // Remove the last comma
            csv.append("\n");
        }

        return csv.toString();
    }
}