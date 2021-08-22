package com.mainoramg.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsPlayground {

    public static void main(String[] args) {
        List<Map<String, String>> managers = new ArrayList<>();

        Map<String, String> manager1 = new HashMap<>();
        manager1.put("id", "15");
        manager1.put("name", "Agent1 Name1");
        managers.add(manager1);

        Map<String, String> manager2 = new HashMap<>();
        manager2.put("id", "200");
        manager2.put("name", "Agent2 Name2");
        managers.add(manager2);

        Map<String, String> manager3 = new HashMap<>();
        manager3.put("id", "30");
        manager3.put("name", "Agent3");
        managers.add(manager3);

        int agentId = 30;

        boolean isManager = managers
                .stream()
                .parallel()
                .filter(agent -> Integer.parseInt(agent.get("id").toString()) == agentId)
                .findAny()
                .isPresent();

        System.out.println("is manager? = " + isManager);
    }

    private static void codeToOrganize(){
        String value = "yahoo.com,comcast.net,aol.com";
        String valueFromDb = Optional.ofNullable(value).orElse("");

        List<String> blockedEmailDomains = Stream.of(valueFromDb.split(",",-1)).filter(emailDomain -> !emailDomain.isEmpty() && !"null".equals(emailDomain)).collect(Collectors.toList());

        System.out.println("is blockedDomains empty="+blockedEmailDomains.isEmpty());

        System.out.println("Display blocked domains:");
        for (String item : blockedEmailDomains
        ) {
            System.out.println("domain = "+item);
        }

        String sqlValue = blockedEmailDomains.stream().collect(Collectors.joining("|"));
        System.out.println("SQL value = " + sqlValue);
    }
}
