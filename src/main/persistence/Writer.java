package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    protected static final int TAB = 4;
    protected String destination;
    private PrintWriter writer;

    //EFFECTS: construct writer to write to destination file
    public Writer(String destination) {
        this.destination = destination;
    }


    //EFFECTS: open the writer
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //EFFECTS: close the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        writer.print(json);
    }
}
