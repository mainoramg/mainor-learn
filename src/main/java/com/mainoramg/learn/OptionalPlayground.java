package com.mainoramg.learn;

import java.util.HashMap;
import java.util.Optional;

public class OptionalPlayground {
    String domain = "mainoramg.com";

    void mapExample(Object requestHeader) {
        HashMap data = new HashMap();
        data.put("originalHost", Optional.ofNullable(requestHeader).orElse(domain));
        if (data.isEmpty()) {
            System.out.println("Map is empty!");
        } else {
            System.out.println("originalHost: "+data.get("originalHost").toString());
        }
    }

    public static void main(String[] args) {
        OptionalPlayground optionalPlayground = new OptionalPlayground();
        optionalPlayground.mapExample("mainoramg.com.test");
        optionalPlayground.mapExample(null);
    }
}
