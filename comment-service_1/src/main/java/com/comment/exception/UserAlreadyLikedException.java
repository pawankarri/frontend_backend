package com.comment.exception;

public class UserAlreadyLikedException extends RuntimeException {
    public UserAlreadyLikedException()
    {

    }

    public UserAlreadyLikedException(String s) {
        super(s);
    }
}
