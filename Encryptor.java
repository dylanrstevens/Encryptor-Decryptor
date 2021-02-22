import java.io.*;

//VIGENERE CIPHER

public class Encryptor {

    private static String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ !@#$%^&*().,/1234567890";

    public static void toFile(String encryption, String sourceLink) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(sourceLink));
            bw.write(encryption);
            bw.close();
        }
        catch(Exception ex) {
            return;
        }
    }

    public static String toRead(String name) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(name));
            return br.readLine();
        }
        catch(Exception ex) {
            return null;
        }
    }

    public static String generateRepeatingKey(String key, String message) {

        int x = message.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == message.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }

    public static String encoder(String key, String message) {
        String encodedMessage = "";
        int keyPos;
        int txtPos;
        int encodedPos;

        for (int i = 0; i < message.length(); i++) {
            char encodedChar;
            txtPos = alpha.indexOf(message.charAt(i));
            keyPos = alpha.indexOf(key.charAt(i));
            encodedPos = txtPos + keyPos;
            if (encodedPos >= alpha.length()) {
                encodedPos -= alpha.length();
            }
            encodedChar = alpha.charAt(encodedPos);
            encodedMessage += encodedChar;
        }
        return encodedMessage;
    }

    public static String decoder(String key, String message) {
        String plainMessage = "";
        int keyPos;
        int txtPos;
        int plainPos;

        for (int i = 0; i < message.length(); i++) {
            char plainChar;
            txtPos = alpha.indexOf(message.charAt(i));
            keyPos = alpha.indexOf(key.charAt(i));
            plainPos = txtPos - keyPos;
            if (plainPos < 0) {
                plainPos += alpha.length();
            }
            plainChar = alpha.charAt(plainPos);
            plainMessage += plainChar;
        }
        return plainMessage;
    }
}
