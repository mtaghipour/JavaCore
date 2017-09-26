package com.mt.parsers;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ParseMoviesJSON {

    private JSONObject rootObject;
    private JSONArray projects;
    private List<JSONObject> projectsValues;

    public ParseMoviesJSON() {

    }

    public void parse() {

        try{

            File file = new File("movies.json");
            String content = FileUtils.readFileToString(file, "utf-8");

            rootObject = new JSONObject(content);

            ShowJSON(rootObject);
            SortJSON("Title","forward");
            SortJSON("Id","reverse");

        }catch (IOException e){
            System.out.println( "IOException : " + e.getMessage());
        }catch (JSONException e){
            System.out.println("JSONException : " + e.getMessage());
        }
    }

    private void ShowJSON(JSONObject jsonObject){

        Long count =  jsonObject.getLong("Count");
        Long currentPage =  jsonObject.getLong("CurrentPage");
        Long pages =  jsonObject.getLong("Pages");

        System.out.println("Count: " + count);
        System.out.println("CurrentPage : " + currentPage);
        System.out.println("Pages : " + pages);

        System.out.println("\nProjects \n");
        projects = jsonObject.getJSONArray("Projects");

        PrintJSONArray(projects);
    }

    private void SortJSON(final String sortBy , final String direction){

        projectsValues = new ArrayList<>();
        for (int i = 0; i < projects.length(); i++) {
            projectsValues.add(projects.getJSONObject(i));
        }

        Collections.sort( projectsValues , new Comparator<JSONObject>() {

            @Override
            public int compare(JSONObject a, JSONObject b) {

                String valA = null;
                String valB = null;

                try {
                    valA =  a.get(sortBy).toString();
                    valB =  b.get(sortBy).toString();
                }
                catch (JSONException e) {
                    System.out.println("JSONException : " + e.getMessage());
                }

                if(direction.equalsIgnoreCase("Forward"))
                    return valA.compareTo(valB);
                else if(direction.equalsIgnoreCase("Reverse"))
                    return -valA.compareTo(valB);

                return 0;
            }
        });

        JSONArray sortedJsonArray = new JSONArray();
        for (int i = 0; i < projects.length(); i++) {
            sortedJsonArray.put(projectsValues.get(i));
        }

        System.out.println("\n ==== Sorted JSON ==== " + "\n");

        PrintJSONArray(sortedJsonArray);
    }

    private void PrintJSONArray( JSONArray jsonArray){

        for(int i=0; i < jsonArray.length(); i++) {

            JSONObject row = jsonArray.getJSONObject(i);

            Long id_element = row.getLong("Id");
            String imagePath_element = row.getString("ImagePath");
            String title_element = row.getString("Title");

            System.out.println("Id :  " + id_element);
            System.out.println("Image Path :  " + imagePath_element);
            System.out.println("Title :  " + title_element);
        }
    }
}


