package com.nextsap.counter.setup;

import com.nextsap.counter.Settings;
import com.nextsap.counter.logger.Log;
import com.nextsap.counter.logger.LogType;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Initializer {

    public Initializer() {
        createFolder();
        dlIcon();
    }

    public static void createFolder() {
        String name = System.getProperty("user.name");
        File file = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\MicOnCounter\\");

        if (!file.exists()) {
            file.mkdir();
            Log.create(LogType.SUCCESS, "Le répertoire a bien été créé.");
            return;
        }

        Log.create(LogType.INFORMATION, "Le répertoire existe déjà.");
    }

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
                Log.create(LogType.SUCCESS, "Image téléchargée");
            } catch (Exception e) {
                Log.create(LogType.ERROR, "Une erreur est survenue :");
                e.printStackTrace();
            }
        }

        Log.create(LogType.INFORMATION, "L'image existe déjà.");
    }
}
