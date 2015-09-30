package io.github.cnquiz.parser;

/**
 * Parses arguments from the command line and returns a {@link io.github.cnquiz.parser.ECPOptions ECPOptions} instance.
 */
public final class ArgumentParser {

    private static final String ARG_ERROR_MSG = "Format: ./ECP [-p ECPport]";
    private static final String PORT_OPT = "-p";

    public static ECPOptions parse(String[] args) {
        ECPOptions ecpOptions = null;

        if (args.length == 1 || args.length > 2) {
            throw new IllegalArgumentException(ARG_ERROR_MSG);
        }

        if (args.length == 2) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case (PORT_OPT) :
                        ecpOptions = new ECPOptions(args[++i]);
                        break;
                }
            }
        } else {
            // set the default port number
            ecpOptions = new ECPOptions();
        }

        return ecpOptions;
    }
}
