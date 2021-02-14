import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class GUI extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public GUI() {
        //Panel initiation
        JFrame window = new JFrame();
        JPanel panel = new JPanel();
        JTextField inputPlainMessage = new JTextField("Enter your message here",30);
        inputPlainMessage.setBackground(Color.DARK_GRAY);
        inputPlainMessage.setForeground(Color.WHITE);
        JTextField enterFilename = new JTextField("Enter the file link (Example: C:\\Users\\file.txt) of your encrypted text file here",30);
        enterFilename.setBackground(Color.DARK_GRAY);
        enterFilename.setForeground(Color.WHITE);
        JTextField enterFileSource = new JTextField("Enter the file link (Example: C:\\Users\\file.txt) of your empty text file here");
        enterFileSource.setBackground(Color.DARK_GRAY);
        enterFileSource.setForeground(Color.WHITE);
        JTextField password = new JTextField("Enter your password here",30);
        password.setBackground(Color.DARK_GRAY);
        password.setForeground(Color.WHITE);
        JTextField password2 = new JTextField("Enter your password here",30);
        password2.setBackground(Color.DARK_GRAY);
        password2.setForeground(Color.WHITE);
        JLabel encodeTitle = new JLabel("Encryptor", SwingConstants.CENTER);
        encodeTitle.setForeground(Color.GREEN);
        encodeTitle.setFont(encodeTitle.getFont().deriveFont(32f));
        JLabel decodeTitle = new JLabel("Decryptor", SwingConstants.CENTER);
        decodeTitle.setForeground(Color.GREEN);
        decodeTitle.setFont(encodeTitle.getFont().deriveFont(32f));

        panel.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));
        panel.setLayout(new GridLayout(0, 1));


        //Add encoder text box
        panel.add(encodeTitle);
        inputPlainMessage.setPreferredSize(new Dimension(430,40));
        panel.add(inputPlainMessage);
        password.setPreferredSize(new Dimension(430,40));
        panel.add(password);
        enterFileSource.setPreferredSize(new Dimension(430,40));
        panel.add(enterFileSource);

        JButton enterPlain = new JButton("Enter");
        enterPlain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageToEncode = inputPlainMessage.getText();
                String passForEncode = password.getText();
                String fileSource = enterFileSource.getText();

                String repeatedKey = Encryptor.generateRepeatingKey(passForEncode, messageToEncode);
                String code = Encryptor.encoder(repeatedKey, messageToEncode);
                System.out.println("Successfully encrypted");
                Encryptor.toFile(code, fileSource);

            }
        });
        enterPlain.setBackground(Color.DARK_GRAY);
        enterPlain.setForeground(Color.GREEN);
        panel.add(enterPlain);

        //add decoder text boxes
        panel.add(decodeTitle);
        enterFilename.setPreferredSize(new Dimension(430,40));
        panel.add(enterFilename);
        password2.setPreferredSize(new Dimension(430,40));
        panel.add(password2);

        JButton enterCipher = new JButton("Enter");
        enterCipher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filelink = enterFilename.getText();
                String passForEncode = password2.getText();

                String encodedText = Encryptor.toRead(filelink);
                String repeatedKey = Encryptor.generateRepeatingKey(passForEncode, encodedText);
                String plain = Encryptor.decoder(repeatedKey, encodedText);
                System.out.println("Decoded message: "+plain);
            }
        });
        enterCipher.setBackground(Color.DARK_GRAY);
        enterCipher.setForeground(Color.GREEN);
        panel.add(enterCipher);



        //Window initiation
        window.add(panel, BorderLayout.CENTER);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Encryptor/Decryptor - By Dylan Stevens V1.01.02");
        window.pack();
        window.setVisible(true);
        panel.setBackground(Color.BLACK);


        //Set the new frame location
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = (screenSize.height - window.getHeight()) / 2;
        window.setLocation(x, y);




    }


}
