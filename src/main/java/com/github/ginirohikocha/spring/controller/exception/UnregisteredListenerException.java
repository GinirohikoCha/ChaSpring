package com.github.ginirohikocha.spring.controller.exception;

/**
 * @author GinirohikoCha
 * @version 0.0.1
 * @date 2021/2/23 15:37
 */
public class UnregisteredListenerException extends Exception {

    public UnregisteredListenerException(String tag) {
        super(tag+":Corresponding listener is not registered! Use setter to register!");
    }
}
