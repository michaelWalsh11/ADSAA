package CryptoGRAM;

import java.util.Arrays;
import java.util.Comparator;

public class Enigma
{
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final char[] lookupTable;

    public Enigma(int length)
    {
        lookupTable = new char[length];
        for (int i = 0; i < length; i++)
        {
            lookupTable[i] = '-';
        }
    }

    public void setSubstitution(int i, char ch)
    {
        if (i >= 0 && i < lookupTable.length)
        {
            lookupTable[i] = ch;
        }
    }

    public String decode(String text)
    {
        String output = "";

        for (int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);

            if (Character.isLetter(c))
            {
                boolean isLower = Character.isLowerCase(c);
                c = Character.toUpperCase(c);

                for (int j = 0; j < alphabet.length(); j++)
                {
                    if (alphabet.charAt(j) == c)
                    {
                        c = lookupTable[j];

                        if (isLower)
                            c = Character.toLowerCase(c);

                        break;
                    }
                }
            }

            output += c;
        }

        return output.toString();
    }

    private int[] countLetters(String text)
    {
        int [] lettahs = new int[26];
        text = text.toLowerCase();

        for (int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (Character.isLetter(c))
            {
                lettahs[c - 'a']++;
            }
        }

        return lettahs;
    }

    public String getHints(String text, String lettersByFrequency)
    {
        int [] counts = countLetters(text);

        Integer [] indices = new Integer [26];

        for (int i = 0; i < 26; i++)
        {
            indices[i] = i;
        }


        // I don't understand this it autocorrected from a manual override I had with
        // changing the comparator.
        Arrays.sort(indices, Comparator.comparingInt((Integer a) -> counts[a]));

        char[] hints = new char[26];
        for (int i = 0; i < 26; i++)
        {
            int letterIndex = indices[i];
            char hintLetter = lettersByFrequency.charAt(i);
            hints[letterIndex] = hintLetter;
        }

        return new String(hints);
    }
}
