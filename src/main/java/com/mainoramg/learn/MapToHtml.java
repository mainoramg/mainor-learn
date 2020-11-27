package com.mainoramg.learn;

import java.util.HashMap;
import java.util.Map;

public class MapToHtml {
    HashMap data;

    public MapToHtml(HashMap data) {
        this.data = data;
    }

    public MapToHtml() {
        HashMap child = new HashMap();
        child.put("puesto", "programador");
        child.put("salario", "1000");
        child.put("start", "feb 14");

        HashMap data = new HashMap();
        data.put("firstName", "Mainor");
        data.put("lastName", "Miranda");
        data.put("work", child);
        data.put("email", "mainoramg@gmail.com");

        this.data = data;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }

    public String mapToHtml(HashMap data) {
        StringBuilder html = new StringBuilder();
        if (!data.isEmpty()) {
            html.append("<ul>");
            for (Object set : data.entrySet()) {
                Map.Entry entry = (Map.Entry) set;
                if (entry.getValue() instanceof HashMap) {
                    html.append("<li>")
                            .append("<b>")
                                .append(entry.getKey().toString()).append(": ")
                            .append("</b>")
                            .append(mapToHtml((HashMap) entry.getValue()))
                        .append("</li>");
                } else if (entry.getValue()  instanceof String) {
                    html.append("<li>")
                            .append("<b>")
                                .append(entry.getKey().toString()).append(": ")
                            .append("</b>")
                            .append(entry.getValue().toString())
                        .append("</li>");
                }
            }
            html.append("</ul>");
        }
        return html.toString();
    }

    public static void main(String[] args) {
        MapToHtml mapToHtml = new MapToHtml();
        System.out.println(mapToHtml.mapToHtml(mapToHtml.getData()));
    }
}
