package esi.clubciber;

import java.io.InputStream;
import java.util.Properties;
import esi.clubciber.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            InputStream input = Main.class.getClassLoader().getResourceAsStream("project.properties");
            if (input != null) {
                properties.load(input);
                String viewType = properties.getProperty("view.type");
                if ("Cli".equals(viewType)) {
                    ConsoleView consoleView = new ConsoleView();
                    consoleView.run();
                } else if ("Gui".equals(viewType)) {
                    // TODO: Launch GuiView
                    System.out.println("Launching GUI View");
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