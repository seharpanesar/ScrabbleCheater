import javax.imageio.IIOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    JList outputList;
    JTextField jTextField;
    JLabel inputPrompt;
    JButton button;
    JPanel input;
    JFrame mainFrame;

    public GUI() {
        //input panel components
        jTextField = new JTextField(30);
        inputPrompt = new JLabel("Enter upto 7 letters consecutively below: ");
        button = new JButton("Get the words!");
        button.setSize(new Dimension( 20, 40));
        button.addActionListener(this);

        input = new JPanel();
        input.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        input.setLayout(new FlowLayout());
        input.add(inputPrompt);
        input.add(jTextField);
        input.add(button);

        //Output List
        outputList = new JList();
        outputList.setVisibleRowCount(10);
        outputList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //main frame initializing
        mainFrame = new JFrame();
        mainFrame.setTitle("Scrabble cheater");
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setBounds(150, 70, 1000, 700);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);

        mainFrame.add(input, BorderLayout.NORTH);
        mainFrame.add(new JScrollPane(outputList), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentLetters = jTextField.getText();
        checkIfInputValid(jTextField);
        Dictionary dictionary = null;
        try {
            dictionary = new Dictionary(currentLetters);
            ArrayList<Word> words = dictionary.getValidWords();
            outputList.setListData(words.toArray());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private void checkIfInputValid(JTextField jTextField) {
    }
}
