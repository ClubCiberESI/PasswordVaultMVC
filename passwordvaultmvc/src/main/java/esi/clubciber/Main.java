package esi.clubciber;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            InputStream input = Main.class.getClassLoader().getResourceAsStream("project.properties");
            if (input != null) {
                properties.load(input);
                String viewType = properties.getProperty("view.type");
                if ("Cli".equals(viewType)) {
                    // TODO: Launch CliView
                    System.out.println("Launching CLI View");
                    // For now, just print
                } else if ("Gui".equals(viewType)) {
                    // TODO: Launch GuiView
                    Throw new UnsupportedOperationException("GUI View not implemented yet");
                } else {
                    System.out.println("Unknown view type");
                }
            } else {
                System.out.println("Properties file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}