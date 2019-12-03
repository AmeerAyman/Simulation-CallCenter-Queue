/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcenter;

import java.util.Scanner;

/**
 *
 * @author Ameer Ayman
 */
public class CallCenter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //1 0.25 2 0.40 3 0.20 4 0.15
        //2 0.30 3 0.28 4 0.25 5 0.17
        //3 0.35 4 0.25 5 0.20 6 0.20 
        
        // TODO code application logic here
        Scanner input=new Scanner(System.in);
        //DataOfTime Da=new DataOfTime();
        Calender call=new Calender();
        int numOfServiceTimeForAble;
        int numOfServiceTimeForBaker;
        int numOfCustomers;
        int repeat;
        System.out.println("Enter how many possible values for inter Arrival Time");
        int numOfInterArrivalTime=input.nextInt();
        call.getInterArrivalTime_And_their_probability(numOfInterArrivalTime);
        call.calculateCumulativeProbabilityAndRanges_InterArrivalTime(numOfInterArrivalTime);
        do{
        System.out.println("---------------------------------------------------------------");
        System.out.print("1-For Single Channel Able only? \n"+"2-For multi Channel Able and Baker? \n");
        int choice=input.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter how many possible values for service Time for Able");
                numOfServiceTimeForAble=input.nextInt();
                call.getServiceTime_And_their_probabilityForAble(numOfServiceTimeForAble);
                call.calculateCumulativeProbabilityAndRanges_ServiceTimeForAble(numOfServiceTimeForAble);
                
                System.out.println("---------------------------------------------------------------");
                while(true){
                    System.out.println("Enter number of customers");
                    numOfCustomers=input.nextInt();
                    call.Assign_Random_Inter_Arrival_Times(numOfCustomers);
                    call.assignTimeBetweenArrivalsForEachCustomer(numOfCustomers);
                    call.generate_Random_Service_Times(numOfCustomers);
                    call.assignServiceTimeForAble(numOfCustomers, numOfServiceTimeForAble);
                    call.calculateCalanderForAble(numOfCustomers);
                    call.displayCalenderForAble(numOfCustomers);
                    call.AverageServiceTime(numOfCustomers);
                    call.AverageWaitingTime(numOfCustomers);
                    call.ProbabilityCustomerHasToWait(numOfCustomers);
                    call.thePortionOfIdleTimeOfTheServer(numOfCustomers);
                
                }
            case 2:
                System.out.println("Enter how many possible values for service Time for Able");
                numOfServiceTimeForAble=input.nextInt();
                call.getServiceTime_And_their_probabilityForAble(numOfServiceTimeForAble);
                call.calculateCumulativeProbabilityAndRanges_ServiceTimeForAble(numOfServiceTimeForAble);
                System.out.println("---------------------------------------------------------------");
                System.out.println("Enter how many possible values for service Time for Baker");
                numOfServiceTimeForBaker=input.nextInt();
                call.getServiceTime_And_their_probabilityForBaker(numOfServiceTimeForBaker);
                call.calculateCumulativeProbabilityAndRanges_ServiceTimeForBaker(numOfServiceTimeForBaker);
                System.out.println("---------------------------------------------------------------");
                while(true){
                    System.out.println("Enter number of customers");
                    numOfCustomers=input.nextInt();
                    call.Assign_Random_Inter_Arrival_Times(numOfCustomers);
                    call.assignTimeBetweenArrivalsForEachCustomer(numOfCustomers);
                    call.generate_Random_Service_Times(numOfCustomers);
                    call.assignServiceTimeForAble(numOfCustomers, numOfServiceTimeForAble);
                    call.assignServiceTimeForBaker(numOfCustomers, numOfServiceTimeForBaker);
                    call.calculateCalenderForAbleBaker(numOfCustomers);
                    call.displayCalenderForAbleBaker(numOfCustomers);
                    call.calculateEffeciency(numOfCustomers);
                    call.averageCallerDelay(numOfCustomers);
                
                }
                default:
                    System.out.println("invalid input!!!!!!");
                    repeat=1;
        }
        }while(repeat==1);
       
    }
    
}
