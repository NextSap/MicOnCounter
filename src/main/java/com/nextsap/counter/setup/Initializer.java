package com.nextsap.counter.setup;

import com.nextsap.counter.Settings;
import com.nextsap.counter.logger.FileType;
import com.nextsap.counter.logger.Log;
import com.nextsap.counter.logger.LogType;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Initializer {

    /**
     * {@link Initializer} Constructor
     */
    public Initializer() {
        createFolder();
        createLogsFiles();
        dlIcon();
        Log.create(LogType.SUCCESS, FileType.CURRENT, "Programme lancé.");
    }

    /**
     * Create program's folder
     */
    private void createFolder() {
        String name = System.getProperty("user.home").split("\\\\")[2];
        File file = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\MicOnCounter\\"); // TODO: CUSTOMER

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Le répertoire a bien été créé.");
            return;
        }

        System.out.println("Le répertoire existe déjà.");
    }

    /**
     * Create program's logs files
     */
    private void createLogsFiles() {
        try {
            String name = System.getProperty("user.home").split("\\\\")[2];
            File currentLogs = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\MicOnCounter\\CurrentLogs.txt"); // TODO: CUSTOMER
            File matchLogs = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\MicOnCounter\\MatchLogs.txt");  // TODO: CUSTOMER

            if (!currentLogs.exists()) {
                currentLogs.createNewFile();
                System.out.println("Le fichier 'CurrentLogs.txt' a bien été créé.");
            } else
                System.out.println("Le fichier 'CurrentLogs.txt' existe déjà.");
            if (!matchLogs.exists()) {
                matchLogs.createNewFile();
                System.out.println("Le fichier 'MatchLogs.txt' a bien été créé.");
            } else
                System.out.println("Le fichier 'MatchLogs.txt' existe déjà.");
        } catch (Exception e) {
            System.out.println("Une erreur est survenue : ");
            e.printStackTrace();
        }
    }

    /**
     * Download an icon from the web
     */
    private void dlIcon() {
        File file = new File(Settings.getIconPath());
        if (!file.exists()) {
            try {
                URL url = new URL("https://mic-on.fr/img/TournoiExplo/Tournoi_explo_logo.png");
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "NING/1.0");
                InputStream bufferedReader = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n;
                while (-1 != (n = bufferedReader.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                bufferedReader.close();
                byte[] response = out.toByteArray();
                FileOutputStream fos = new FileOutputStream(Settings.getIconPath());
                fos.write(response);
                fos.close();
                Log.create(LogType.SUCCESS, FileType.CURRENT, "Image téléchargée.");
            } catch (Exception e) {
                Log.create(LogType.ERROR, FileType.CURRENT, "Une erreur est survenue :");
                e.printStackTrace();
            }
        }

        Log.create(LogType.INFORMATION, FileType.CURRENT, "L'image existe déjà.");
    }
}
