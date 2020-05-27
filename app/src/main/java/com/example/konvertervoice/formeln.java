package com.example.konvertervoice;

public class formeln {
    public static double eurusd(double x){
        x *= 1.0875;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double usdeur(double x){
        x *= 0.919837;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double ctof(double x){
        double y = 1.8*x+32;
        y = Math.round(y *100)/100.0;
        return y;
    }
    public static double ftoc(double x){
        double y;
        y = (x-32)/1.8;
        y = Math.round(y *100)/100.0;
        return y;
    }
    public static double mtoft(double x){
        x *= 3.28;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double fttome(double x){
        x *= 0.3048;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double lkmtompg(double x){
        double y;
        y = (100/x)*(1/1.609)*(3.785/1);
        y = Math.round(y *100)/100.0;
        return y;
    }
    public static double mpgtolkm(double x){
        double y;
        y = 235.214/x;
        y = Math.round(y *100)/100.0;
        return y;
    }
    public static double kgtolbs(double x){
        x*=2.204;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double lbstokg(double x){
        x/=2.204;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double kmtomi(double x){
        x = x * 0.621371;
        x = Math.round(x *100)/100.0;
        return x;
    }
    public static double mitokm(double x){
        x = x * 1.60934;
        x = Math.round(x *100)/100.0;
        return x;
    }
}
