package syndrome.ui.impl;

import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Crash interface for when the application has a 
 * severe error.
 * 
 * @author Axel Wallin
 */
public class CrashInterface {

    /**
     * Creates a crashdump and shows an error to the user.
     * 
     * @param throwable the error to dump.
     */
    public void show(Throwable throwable) {
        createCrashDump(throwable);
        JOptionPane.showMessageDialog(null,
            "The game has encountered a fatal error.\n"
                    + "A crashlog has been created.",
            "Error - exiting",
            JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
    
    private void createCrashDump(Throwable throwable) {
        StringBuilder bldr = new StringBuilder();
        bldr.append(System.getProperty("user.home"));
        bldr.append(System.currentTimeMillis() / 100000);
        bldr.append(".txt");
        try {
            File file = new File(bldr.toString());
            file.createNewFile();
            try(PrintWriter writer = new PrintWriter(file)) {
                writer.println(throwable);
                writer.println(throwable.getMessage());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
