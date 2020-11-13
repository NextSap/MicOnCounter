package com.nextsap.counter.initialize;

import com.nextsap.counter.Settings;
import com.nextsap.counter.logger.Log;
import com.nextsap.counter.logger.LogType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

public class Initialize {

    public Initialize() throws IOException {
        createFolder();
        dlIcon();
    }

    private void createFolder() throws IOException {
        String name = InetAddress.getLocalHost().getHostName();
        File folder = new File("C:\\Users\\" + name + "\\AppData\\Roaming\\MicOnCounter");
        if (!folder.exists()) {
            folder.mkdirs();
            Log.create(LogType.SUCCESS, "Folder create");
        }
    }

    private void dlIcon() throws IOException {
        String name = InetAddress.getLocalHost().getHostName();
        BufferedImage image;
        try {
            URL url = new URL("https://www.mic-on.fr/img/banner_bg%20logo");
            image = ImageIO.read(url);
            ImageIO.write(image, "png", new File(Settings.getIconPath(name)));
            Log.create(LogType.SUCCESS, "Icon downloaded");
        } catch (IOException e) {
            e.printStackTrace();
            Log.create(LogType.ERROR, "An error occured : "+e.getMessage());
        }
    }
}
