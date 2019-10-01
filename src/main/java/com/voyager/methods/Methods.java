/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.voyager.methods;


/**
 *
 * @author pasindu
 */
public class Methods {
    
    public static boolean isFloat(String inputString){
        
        try{
            Float.parseFloat(inputString);
        }
        catch(NumberFormatException e){
            return false;
        }
        catch(NullPointerException e){
            return false;
        }
        
        return true;
    }
    
    public static boolean isInteger(String inputString){
        
        try{
            Integer.parseInt(inputString);
        }
        catch(NumberFormatException e){
            return false;
        }
        catch(NullPointerException e){
            return false;
        }
        
        return true;
    }
    
    
}
