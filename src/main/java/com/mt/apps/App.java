package com.mt.apps;

import com.mt.controllers.DownloadThread;
//import com.mt.controllers.ParseMoviesPullParser;
import com.mt.parsers.ParseMoviesJSON;

public class App {

    public static void main(String[] args) {

        Thread downloadThread = new Thread(new DownloadThread());
        downloadThread.start();

        try {

            System.out.println("Please wait to fetch data ...");
            downloadThread.join();

        } catch (InterruptedException e) {
            System.out.println("Problem during fetching data from server.");
        }

        System.out.println("Fetch done.\n\n");

        ParseMoviesJSON parseMoviesJSON = new ParseMoviesJSON();
        parseMoviesJSON.parse();
    }
}
