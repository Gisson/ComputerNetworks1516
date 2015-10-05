package io.github.cnquiz;

public class TESOptions {
    private static final int GROUP_NUMBER = 33;
    private static final String ARG_ERROR_MSG = "Format: ./TES [-p TESport] [-n ECPname] [-e ECPport]";
    private static final String TES_PORT_OPT = "-p";
    private static final String ECP_NAME_OPT = "-n";
    private static final String ECP_PORT_OPT = "-e";

    private int TESport = 59000;
    private String ECPname = "localhost";
    private int ECPport = 58000 + GROUP_NUMBER;

    /**
     * create a TESOptions object from an args array
     */
    public TESOptions ( String[] args ) {

        if ( args.length % 2 != 0 || args.length > 6 ) {
            throw new IllegalArgumentException("Wrong number of options. "
                                                + ARG_ERROR_MSG);
        }

        for( int i=0; i < args.length - 1; i++) {
            switch( args[i] ) {
                case TES_PORT_OPT:
                    this.setTESPort( Integer.parseInt(args[++i]) );
                    break;
                case ECP_NAME_OPT:
                    this.setECPName( args[++i] );
                    break;
                case ECP_PORT_OPT:
                    this.setECPPort( Integer.parseInt(args[++i]) );
                    break;
                default:
                    throw new IllegalArgumentException("Unknown option '"
                                                        + args[i] + "'. "
                                                        + ARG_ERROR_MSG);
            }
        }

        if( Main.debug ) {
            System.out.println("TESOptions() " + this.toString() );
        }
    }

    @Override
    public String toString() {
        return "TESOptions:"
                + " TESPort="  + getTESPort()
                + " ECPName='" + getECPName() + "'"
                + " ECPPort="  + getECPPort();
    }

    /* ******************** setters ******************** */

    public void setTESPort( int portNumber ) {
        this.TESport = portNumber;
    }

    public void setECPName( String name ) {
        this.ECPname = name;
    }

    public void setECPPort( int portNumber ) {
        this.ECPport = portNumber;
    }

    /* ******************** getters ******************** */

    public int getTESPort() {
        return this.TESport;
    }

    public String getECPName() {
        return this.ECPname;
    }

    public int getECPPort() {
        return this.ECPport;
    }
}
