package controllers;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import play.*;
import play.mvc.*;
import views.html.*;
import java.lang.Exception;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.*;

public class Application extends Controller {

    public Result index(String q) {

        //        String q = "979b8190: what is 1 plus 3";
//        String q = "5fbb9030: which of the following numbers is the largest: 61, 212, 412, 1004";
//        String q = "bd1f1ec0: which of the following numbers is both a square and a cube: 562, 729, 747, 2401, 64, 1000000";
//        String q = "1e9bf2a0: what is 13 multiplied by 16";
//        String q = "9e754d70: what currency did Spain use before the Euro";
//        String q = "9e754d70: who played James Bond in the film Dr No";
//        Sean Connery
        String q = "9e754d70: which of the following numbers are primes: 525, 337, 41, 3, 5, 7, 9, 11, 23, 29, 1, 31, 36, 151"; // Check for number 1!!!!
//        String q = "9e754d70: which city is the Eiffel tower in";
//        String q = "9e754d70: who is the Prime Minister of Great Britain";
//        String q = "dbb9b320: what colour is a banana";
        
        int result = 0;
        String answer = "";
        System.out.println(q);


        String pattern1 = "[^,]+: which of the following numbers is the largest: ([\\d\\s,]+)";
        Pattern p1 = Pattern.compile(pattern1);
        Matcher m1 = p1.matcher(q);
        if(m1.find()) {
            String[] qList = m1.group(1).split(",");
            for(String s : qList){
                int e = Integer.parseInt(s.trim());
                if (result == 0) {
                    result = e;
                } else if (result < e) {
                    result = e;
                }
            }
            answer = "" + result;
        }

        String pattern2 = "[\\w]+: what is ([\\d]+) plus ([\\d]+)";
        Pattern p2 = Pattern.compile(pattern2);
        Matcher m2 = p2.matcher(q);
        if(m2.find()){
            result = Integer.parseInt(m2.group(1)) + Integer.parseInt(m2.group(2));
            answer = "" + result;
        }

        String pattern3 = "[\\w]+: which of the following numbers is both a square and a cube: ([\\d\\s,]+)";
        Pattern p3 = Pattern.compile(pattern3);
        Matcher m3 = p3.matcher(q);
        if(m3.find()){
            String[] qList = m3.group(1).split(",");
            for(String s : qList){
                int e = Integer.parseInt(s.trim());
                double sqr = Math.sqrt(e);
                double cube = Math.cbrt(e);
                if(sqr == (int)sqr && cube == (int)cube){
                    result = e;
                    answer += result + " ";
                }
            }
        }

        String pattern4 = "[\\w]+: what is ([\\d]+) multiplied by ([\\d]+)";
        Pattern p4 = Pattern.compile(pattern4);
        Matcher m4 = p4.matcher(q);
        if(m4.find()){
            result = Integer.parseInt(m4.group(1)) * Integer.parseInt(m4.group(2));
            answer = "" + result;
        }

        String pattern5 = "[\\w]+: what currency did ([\\w]+) use before the Euro";
        Pattern p5 = Pattern.compile(pattern5);
        Matcher m5 = p5.matcher(q);
        if(m5.find()){
            if(m5.group(1).equals("Spain")){
                answer = "Peseta";
            }
        }

        String pattern6 = "[\\w]+: what colour is a ([\\w]+)";
        Pattern p6 = Pattern.compile(pattern6);
        Matcher m6 = p6.matcher(q);
        if(m6.find()){
            if(m6.group(1).equals("banana")) {
                answer = "yellow";
            }
        }

        String pattern7 = "[\\w]+: who played James Bond in the film ([\\w\\s]+)";
        Pattern p7 = Pattern.compile(pattern7);
        Matcher m7 = p7.matcher(q);
        if(m7.find()){
            if(m7.group(1).equals("Dr No")){
                answer = "Sean Connery";
            }
        }

        String pattern8 = "[\\w]+: which city is the ([\\w]+) tower in";
        Pattern p8 = Pattern.compile(pattern8);
        Matcher m8 = p8.matcher(q);
        if(m8.find()){
            if(m8.group(1).equals("Eiffel")){
                answer = "Paris";
            }
        }

        String pattern9 = "[\\w]+: who is the Prime Minister of ([\\w\\s]+)";
        Pattern p9 = Pattern.compile(pattern9);
        Matcher m9 = p9.matcher(q);
        if(m9.find()){
            if(m9.group(1).equals("Great Britain")){
                answer = "David Cameron";
            }
        }


        String pattern10 = "[^,]+: which of the following numbers are primes: ([\\d\\s,]+)";
        Pattern p10 = Pattern.compile(pattern10);
        Matcher m10 = p10.matcher(q);
        if(m10.find()){
            String[] strNums = m10.group(1).split(",");
            boolean prime = false;
            for(String s : strNums){

                double maxD = Math.sqrt(Integer.parseInt(s.trim()));
                int max = (int) Math.ceil(maxD);
                int control = 2;
                while(control <= max){
                    if(Integer.parseInt(s.trim()) % control != 0){
                        prime = true;
                    } else {
                        prime = false;
                        break;
                    }
                    control++;
                }
                if(prime == true){
                    if(control > 1) {
                        answer += s.trim() + ", ";
                    } else {
                        answer = s.trim();
                    }
                }
            }
        }



        String pattern11 = "[\\w]+: what is ([\\d]+) minus ([\\d]+)";
        Pattern p11 = Pattern.compile(pattern11);
        Matcher m11 = p11.matcher(q);
        if(m11.find()){
            result = Integer.parseInt(m11.group(1)) - Integer.parseInt(m11.group(2));
            answer = "" + result;
        }




        System.out.println(answer);
        return ok(answer);
    }
}
