package org.utility.autogenerate.hob.helpers;


public class GenerateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenerateException(String str) {
        super(str);
    }

    public GenerateException(Throwable t) {
        super(t);
    }
}
