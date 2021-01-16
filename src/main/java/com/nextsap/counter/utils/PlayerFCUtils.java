package com.nextsap.counter.utils;

import com.nextsap.counter.graphics.FrameManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class PlayerFCUtils {

    private final boolean error;

    public PlayerFCUtils(FrameManager frame, List<String> players) {
        this.error = check(frame, players);
    }

    private boolean check(FrameManager frame, List<String> players) {
        boolean exist = true;
        boolean connect = true;
        StringBuilder builder = new StringBuilder();
        builder.append("Les joueurs ci-après n'existe pas sur FunCraft : \n");
        for (String player : players) {
            String search = search(player);
            if (search.equals("0")) {
                exist = false;
                builder.append(" - ").append(player).append("\n");
            }
            if (search.equals("2"))
                connect = false;
        }
        if (!exist) {
            frame.showErrorDialog("Erreur", builder.toString());
            return false;
        }
        if (!connect)
            frame.showErrorDialog("Erreur", "Le site de FunCraft n'a pas répondu.");
        return true;
    }

    private String search(String name) {
        String html = getHtmlCode("https://www.funcraft.net/fr/joueurs?q=" + name);
        if (html.contains(" Une ou plusieurs erreurs sont survenues "))
            return "0";
        if (html.equalsIgnoreCase("2"))
            return "2";
        System.out.println(name + " : 1");
        return "1";
    }

    public static String getHtmlCode(String urlToRead) {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        } catch (Exception ex) {
            return "2";
        }
    }

    public boolean isError() {
        return error;
    }
}
