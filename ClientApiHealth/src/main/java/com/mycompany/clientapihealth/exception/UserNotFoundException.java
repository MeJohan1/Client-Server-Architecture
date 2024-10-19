/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientapihealth.exception;

/**
 *
 * @author johanahmedchowdhury
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException (String massage){
        super (massage);
    }
}
