import javax.swing.*;
import java.util.List;

class Main {

    public static void main(String[] args)
    {
        ReadData rd = new ReadData("iris.txt");
        List<String[]> data = rd.readData();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame(data);
            }
        });
    }
}
