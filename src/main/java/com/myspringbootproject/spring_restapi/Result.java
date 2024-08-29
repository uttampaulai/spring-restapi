package com.myspringbootproject.spring_restapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Result {

    public static int getTotalGoals(String team, int year) throws IOException  {
        final String EndPoint = "https://jsonmock.hackerrank.com/api/football_matches";

        int totalGoalsAtHome = getPageTotalGoals(
            String.format(EndPoint +"?team1=%s&year=%d", URLEncoder.encode(team, "UTF-8"), year), "team1", 1);


        int totalGoalsAtVisiting = getPageTotalGoals(
            String.format(EndPoint +"?team2=%s&year=%d", URLEncoder.encode(team, "UTF-8"), year), "team2", 1);    

        return totalGoalsAtHome + totalGoalsAtVisiting;
    }

    private static int getPageTotalGoals(String request, String team, int page) throws IOException{
        URL url = new URL(request + "&page=" + page);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.addRequestProperty("Content-Type", "application/json");

        int status = httpURLConnection.getResponseCode();

        InputStream in = (status < 200 || status > 299) ?
            httpURLConnection.getErrorStream(): httpURLConnection.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String responseLine;
        StringBuffer responseContent = new StringBuffer();
        while ((responseLine = br.readLine()) != null)
            responseContent.append(responseLine);

        br.close();
        httpURLConnection.disconnect();

        //ScriptEngineManager manager = new ScriptEngineManager();
        //ScriptEngine engine =manager.getEngineByName("JavaScript");
        //Context context = Context.enter();

        String script = "var obj = JSON.parse('" + responseContent.toString() +  "');";
        script += "var total_pages = obj.total_pages;";
        script += "var total_goals = obj.data.reduce(function(accumulator, current) { return accumulator + parseInt(current." + team + "goals); },0);";

        /*try {
            engine.eval(script);
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        //Object result;
        /*try {
            Scriptable scope = context.initStandardObjects();
            Object result = context.evaluateString(scope, script, "script", 1, null);
            System.out.println("javascript result: "+Context.toString(result)); // Output: 3
        } finally {
            Context.exit();
        }*/
        
        //if(engine.get("total_pages") == null) 
            //throw new RuntimeException("Cannot retrive data from the server");

        int totalPages = 0;//(int) engine.get("total_pages");    
        int totalGoals = 20;//(int) Double.parseDouble(engine.get("total_goals").toString());

        return (page < totalPages) ? getPageTotalGoals(request, team, page + 1) : totalGoals;

    }

}
