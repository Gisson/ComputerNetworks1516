package io.github.cnquiz;


public class Main {
    public static final boolean debug = true;

    public static void main(String[] args) {
        TES tes;
        try {
            tes = new TES( new TESOptions(args) );
            // TODO
        } catch (IllegalArgumentException e) {
            System.out.println( e.getMessage() );
        }
    }

}
