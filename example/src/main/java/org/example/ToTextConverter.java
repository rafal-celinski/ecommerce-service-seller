package org.example;

public class ToTextConverter {
    String toText(int n) throws Exception {
        if (n == 0)
            return "zero";
        else if (n == 1)
            return "jeden";
        else if (n == 2)
            return "dwa";
        else if (n == 3)
            return "trzy";
        else
            throw new Exception("Umiem liczyć tylko do 3");
    }
}