package com.gringottsbank.model.exceptions;

public class BaseException extends RuntimeException{
    private final int status;

    private BaseException(int status, String msg) {
        super(msg);
        this.status = status;
    }
    public static BaseExceptionBuilder exceptionBuilder() {
        return new BaseExceptionBuilder();
    }

    public int getErrorCode() {
        return status;
    }

    public static class BaseExceptionBuilder {

        private int status;
        private String msg;

        public BaseExceptionBuilder code(int errorCode) {
            this.status = errorCode;
            return this;
        }

        public BaseExceptionBuilder message(String msg) {
            this.msg = msg;
            return this;
        }

        public BaseException build() {
            return new BaseException(status, msg);
        }
    }
}
