package com.mainoramg.learn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
