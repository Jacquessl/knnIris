import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

class MyFrame
        extends JFrame
        implements ActionListener {

    private Container c;
    private JLabel title;
    private JLabel name;
    private JFormattedTextField dlugoscListka;
    private JFormattedTextField szerokoscListka;
    private JFormattedTextField dlugoscPlatka;
    private JFormattedTextField szerokoscPlatka;
    private JFormattedTextField k;
    private JButton sub;
    private JButton reset;
    private JButton zbiorTestowy;
    private JTextPane tout;
    private List<String[]> data;
    private NumberFormatter intFormatter;
    private NumberFormatter formatter;
    private boolean workerDone = true;
    private List<String[]> testData;
    private int testIndex;
    private int accurateTest;
    private int possibleTest;
    private boolean wypisywacDokladnosc = false;
    public MyFrame(List<String[]> data)
    {
        this.data = data;

        NumberFormat floatFormat = DecimalFormat.getInstance();
        formatter = new NumberFormatter(floatFormat);
        formatter.setValueClass(Float.class);
        formatter.setMinimum(0.0f);
        formatter.setMaximum(Float.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);


        DecimalFormat decimalFormat = (DecimalFormat) floatFormat;
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setGroupingUsed(false);

        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        intFormatter = new NumberFormatter(integerFormat);
        intFormatter.setValueClass(Integer.class);
        intFormatter.setMinimum(1);
        intFormatter.setMaximum(Integer.MAX_VALUE);
        intFormatter.setAllowsInvalid(true);
        intFormatter.setCommitsOnValidEdit(true);

        setTitle("Rozpoznawanie Roślin");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Rozpoznawanie Roślin");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(400, 30);
        title.setLocation(250, 30);
        c.add(title);

        name = new JLabel("Długość Listka (cm)");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(180, 30);
        name.setLocation(20, 100);
        c.add(name);

        dlugoscListka = new JFormattedTextField(formatter);
        dlugoscListka.setFont(new Font("Arial", Font.PLAIN, 15));
        dlugoscListka.setSize(190, 30);
        dlugoscListka.setLocation(220, 100);
        c.add(dlugoscListka);

        name = new JLabel("Szerokość Listka (cm)");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 30);
        name.setLocation(20, 150);
        c.add(name);

        szerokoscListka = new JFormattedTextField(formatter);
        szerokoscListka.setFont(new Font("Arial", Font.PLAIN, 15));
        szerokoscListka.setSize(190, 30);
        szerokoscListka.setLocation(220, 150);
        c.add(szerokoscListka);

        name = new JLabel("Długość Płatka (cm)");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 30);
        name.setLocation(20, 200);
        c.add(name);

        dlugoscPlatka = new JFormattedTextField(formatter);
        dlugoscPlatka.setFont(new Font("Arial", Font.PLAIN, 15));
        dlugoscPlatka.setSize(190, 30);
        dlugoscPlatka.setLocation(220, 200);
        c.add(dlugoscPlatka);

        name = new JLabel("Szerokość Płatka (cm)");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 30);
        name.setLocation(20, 250);
        c.add(name);

        szerokoscPlatka = new JFormattedTextField(formatter);
        szerokoscPlatka.setFont(new Font("Arial", Font.PLAIN, 15));
        szerokoscPlatka.setSize(190, 30);
        szerokoscPlatka.setLocation(220, 250);
        c.add(szerokoscPlatka);

        name = new JLabel("Ilość sąsiadów");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(200, 30);
        name.setLocation(20, 300);
        c.add(name);

        k = new JFormattedTextField(intFormatter);
        k.setFont(new Font("Arial", Font.PLAIN, 15));
        k.setSize(190, 30);
        k.setLocation(220, 300);
        c.add(k);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 15));
        sub.setSize(100, 20);
        sub.setLocation(150, 400);
        sub.addActionListener(this);
        c.add(sub);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 400);
        reset.addActionListener(this);
        c.add(reset);

        zbiorTestowy = new JButton("Uruchom Testowy Zbiór");
        zbiorTestowy.setFont(new Font("Arial", Font.PLAIN, 15));
        zbiorTestowy.setSize(220, 20);
        zbiorTestowy.setLocation(150, 450);
        zbiorTestowy.addActionListener(this);
        c.add(zbiorTestowy);

        tout = new JTextPane();
        tout.setFont(new Font("Arial", Font.PLAIN, 15));
        tout.setSize(300, 400);
        tout.setLocation(500, 100);
        tout.setEditable(false);
        c.add(tout);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == sub) {
            String dataToPrint = "Długość Listka : "
                    + dlugoscListka.getText() + "cm<br>"
                    + "Szerokość Listka : "
                    + szerokoscListka.getText() + "cm<br>"
                    + "Długość Płatka : "
                    + dlugoscPlatka.getText() + "cm<br>"
                    + "Szerokość Płatka : "
                    + szerokoscPlatka.getText() + "cm<br>"
                    + "Ilość sąsiadów : "
                    + k.getText() +"<br>";
            String[] dataToAnalyze = {dlugoscListka.getText(), szerokoscListka.getText(),
                    dlugoscPlatka.getText(), szerokoscPlatka.getText()};
            AnalyzeData ad = new AnalyzeData(data, dataToAnalyze, (Integer) k.getValue());
            String result = ad.analyze();

            dataToPrint+=result+"<br>";
            tout.setContentType("text/html");
            if(wypisywacDokladnosc) {
                if (result.equals(testData.get(testIndex)[4].replace("\rn", ""))) {
                    accurateTest++;
                }
                double dokladnosc = (double) (accurateTest) / (double) possibleTest;
                int dokladnoscDoWypisania = (int) (dokladnosc * 100);

                tout.setText("<html><body><div style='font-family: Arial, Helvetica, sans-serif; font-size: 15pt; text-align: center;'>" + dataToPrint +
                        "<img src=\'file:img/" + result.toLowerCase().trim() + ".jpg\'/><br>Dokladność: " + dokladnoscDoWypisania + "%</div></body></html>");
            }
            else{
                tout.setText("<html><body><div style='font-family: Arial, Helvetica, sans-serif; font-size: 15pt; text-align: center;'>" + dataToPrint +
                        "<img src=\'file:img\\" + result.toLowerCase().trim() + ".jpg\'/></div></body></html>");
            }
            tout.setEditable(false);
        }
        else if (e.getSource() == reset) {
            formatter.setAllowsInvalid(true);
            intFormatter.setAllowsInvalid(true);
            String def = "";
            dlugoscListka.setText(def);
            szerokoscListka.setText(def);
            dlugoscPlatka.setText(def);
            szerokoscPlatka.setText(def);
            k.setText(def);
            tout.setText(def);
            formatter.setAllowsInvalid(false);
            intFormatter.setAllowsInvalid(false);
        }
        else if(e.getSource() == zbiorTestowy) {
            try {
                int kValue = (int) k.getValue();
                ReadData rd = new ReadData("irisTest.txt");
                testData = rd.readData();
                accurateTest = 0;
                possibleTest = 1;
                JFormattedTextField[] textFields = {dlugoscListka, szerokoscListka, dlugoscPlatka, szerokoscPlatka};
                SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (String[] str : testData) {
                            for (int i = 0; i < textFields.length; i++) {
                                textFields[i].setText(str[i].replace(".", ","));
                                Thread.sleep(100);
                            }
                            wypisywacDokladnosc = true;
                            k.setText("" + kValue);
                            sub.doClick();
                            testIndex++;
                            possibleTest++;
                            Thread.sleep(2000);
                            reset.doClick();
                            wypisywacDokladnosc=false;
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        workerDone = true;
                    }
                };
                worker.execute();
            } catch (NullPointerException exc) {
                tout.setText("Wpisz ilość sąsiadów");
            }
        }
    }
}
