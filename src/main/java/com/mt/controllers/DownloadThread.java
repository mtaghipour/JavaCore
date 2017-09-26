package com.mt.controllers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadThread extends Thread {

    private String feedUrl = "http://cineport.ir/api/catalogApi";

    @Override
    public void run() {

        downloadXML(feedUrl);
    }

    private void downloadXML(String urlPath) {

        try {

            URL url = new URL(urlPath);
            File inputFile = new File("movies.json");

            FileUtils.copyURLToFile(url,inputFile);

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException : " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException : " + e.getMessage());
        }
    }
}
