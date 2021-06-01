package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ParserService {

    public String parse(String input){
        Map<String, String> variables  = new HashMap<>();
        AtomicReference<String> message = new AtomicReference<>("");
        String [] lines  = input.split("\n");
        Arrays.stream(lines).sequential().forEach(line ->{
            line = line.trim();
            if(line.startsWith("!")){
                variables.put(line.substring(1,line.indexOf("=")),replaceVariables(line.substring(line.indexOf("=")+1), variables));
            }
            else {
                String modifiedText = replaceVariables(line, variables);
                if(message.toString().equals("")){
                    message.set(modifiedText);
                }else {
                    message.set(message + "\n" + modifiedText);
                }
            }
        });
        return message.toString();
    }

    private String replaceVariables(String line, Map<String, String> variables){
        AtomicReference<String> modifiedText = new AtomicReference<>(line);
        variables.forEach( (k,v) ->{
            modifiedText.set(modifiedText.toString().replaceAll("@" + k, v)
                    .replaceAll("@\\{" + k, v)
                    .replaceAll("@@", "@")
                    .replaceAll("}", "")
                    .replaceAll("!!","!"));
        });
        return modifiedText.toString();
    }

}
