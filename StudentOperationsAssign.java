package com.assignment;

import java.util.Scanner;

public class StudentOperationsAssign {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("enter student id: ");
        int id=sc.nextInt();

        System.out.println("enter student name: ");
        String name=sc.next();

        System.out.println("enter marks b/w 0 to 100: ");
        int marks=sc.nextInt();

        System.out.println("enter fee paid status: ");
        boolean feePaid=sc.nextBoolean();

        //part 1: student pass or fail
        String result;
        char grade='-';

        if(marks>=40){
            result="Student Passed";


            //part 2: grade assignment
            if(marks<50){
                grade='D';
            } else if (marks>=50 && marks<70) {
                grade='C';
            } else if (marks>=70 && marks<85) {
                grade='B';
            }else {
                grade='A';
            }
        }else {
            result="Student Failed";
        }

        String certificate;
        //part 3: certificate Eligibility
        if (result.equals("Student Passed") && feePaid==true)
        {
            certificate="Eligible for Certificate";
        } else {
            certificate="Not Eligible for Certificate";
        }

        String course;
        //part 4: Course Allocation
        switch (grade){
            case 'A':
                course="Data Science";
                break;
            case 'B':
                course="Java Full Stack";
                break;
            case 'C':
                course="Web development";
                break;
            case 'D':
                course="Basic Programming";
                break;
            default:
                course="Counselling";
                break;
        }

        //Final output
        System.out.println("Student id: "+ id + " Name: "+ name);
        System.out.println("Marks: "+marks+"-->"+ result );
        System.out.println("Grade : "+grade);
        System.out.println("fee paid :"+ feePaid);
        System.out.println(certificate);
        System.out.println("course :"+course);
    }
}
