/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callcenter;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Ameer Ayman
 */
public class Calender{

    public double[][] interArr_prob; /// array that holds inter arrival time and their probabilities
    public double[][] interArrialCumulativeAndRanges;// array that holds the cumulative probability and the ranges for the inter arrival time
    public int[][] customer_Random_TimeBetweenArrivals; //array that holds generated numbers and the arrival times 
    public double[][] serviceTime_probAble; // array taht holds service time with their probabilities   
    public double[][] serviceCumulativeAndRangesForAble; //array that holds the cumulative probability and the ranges for the service time for able
    public int[][] customer_Random_ServiceTime; // array that holds random number that use to get service time for both able and baker
    public int[] serviceForAble; // array holds the service time of able
    public int calenderArrayForAble[][]; // array holds the alla ttribute of the calender
    public double[][] serviceTime_probBaker; //array taht holds service time with their probabilities for baker
    public double [][] serviceCumulativeAndRangesForBaker; // array that holds the service cumulative for baker
    public int [] serviceForBaker; // array holds the service time for bakeer
    public int [][] calenderArrayForAbleBaker;//array that holds the cumulative probability and the ranges for the service time for baker
    public  String [] serverChosen; // array holds which server is chosen able or baker
    Scanner input=new Scanner(System.in);
    //int numOfServiceTimeOfAble;

    //function that take inter arrival time and their probabilities from user and holds them into array
    public void getInterArrivalTime_And_their_probability(int numOfInterArrivalTime) 
    {
        //numOfInterArrivalTime=Integer.parseInt(JOptionPane.showInputDialog("Enter how many possible values for inter-Arrival time"));
        System.out.println("Enter the inter arriavl time and their probabilities");
        interArr_prob = new double[numOfInterArrivalTime][2];
        for (int i = 0; i < numOfInterArrivalTime; i++) {
            for (int j = 0; j < 2; j++) {
                interArr_prob[i][j] =input.nextDouble();
            }
        }
    }
    // function to calcualte the cumulative probabiluty and the ranges for the inter arrival time
    public void calculateCumulativeProbabilityAndRanges_InterArrivalTime(int numOfInterArrivalTime) 
     {
        double sumForCum = 0.0;
        interArrialCumulativeAndRanges = new double[numOfInterArrivalTime][3];
        for (int i = 0; i < numOfInterArrivalTime; i++) {
            interArrialCumulativeAndRanges[i][1] = sumForCum*100;
            sumForCum += interArr_prob[i][1];
            interArrialCumulativeAndRanges[i][0] = sumForCum;
            interArrialCumulativeAndRanges[i][2] = sumForCum*100;
        }
    }
    //function to assign random to the inter arrival time
    public void Assign_Random_Inter_Arrival_Times(int numOfcustomers) 
    {
        customer_Random_TimeBetweenArrivals = new int[numOfcustomers][3];
        customer_Random_TimeBetweenArrivals[0][2]=0;
        for (int i = 0; i < numOfcustomers; i++) {
            customer_Random_TimeBetweenArrivals[i][0] = i + 1;
            Random rand = new Random();
            customer_Random_TimeBetweenArrivals[i][1] = rand.nextInt(90) + 10;
        }
         customer_Random_TimeBetweenArrivals[0][1]=0;
    }
    // to get the arrival time for each customer
    public void assignTimeBetweenArrivalsForEachCustomer(int numOfcustomers)
    {
        for (int i = 1; i <numOfcustomers; i++) {
            int value=customer_Random_TimeBetweenArrivals[i][1];
            for (int j = 0; j < 4; j++) {
                if(value>interArrialCumulativeAndRanges[j][1] && value<=interArrialCumulativeAndRanges[j][2])
                    customer_Random_TimeBetweenArrivals[i][2]=j+1;
            }
        }
    }
    
    
    
    // to take service time of able and their probabi;ities 
    public void getServiceTime_And_their_probabilityForAble(int numOfServiceTimeOfAble) {
        System.out.println("Enter serviceTime And Probability For Able");
        serviceTime_probAble = new double[numOfServiceTimeOfAble][2];
        for (int i = 0; i < numOfServiceTimeOfAble; i++) {
            for (int j = 0; j < 2; j++) {
                serviceTime_probAble[i][j] = input.nextDouble();
            }
        }
    }
// calculate the  the cumulative probabilities and ranges for service for able
    public void calculateCumulativeProbabilityAndRanges_ServiceTimeForAble(int numOfServiceTimeOfAble) {
        double sumForCum = 0.0;
        serviceCumulativeAndRangesForAble = new double[numOfServiceTimeOfAble][3];
        for (int i = 0; i < numOfServiceTimeOfAble; i++) {
            serviceCumulativeAndRangesForAble[i][1] = sumForCum * 100;
            sumForCum += serviceTime_probAble[i][1];
            serviceCumulativeAndRangesForAble[i][0] = sumForCum;
            serviceCumulativeAndRangesForAble[i][2] = sumForCum * 100;
        }
    }
// generate random number for service time
    public void generate_Random_Service_Times(int numOfcustomers) {
        //array of 2 columns holds cutomers and random number for each customer
        customer_Random_ServiceTime = new int[numOfcustomers][2];
        for (int i = 0; i < numOfcustomers; i++) {
            //fill customer column from 1 to n of customers
            customer_Random_ServiceTime[i][0] = i + 1;
            //initalize object from random class
            Random rand = new Random();
            //fill random number column with random numbers for each customers
            customer_Random_ServiceTime[i][1] = rand.nextInt(90) + 10;
        }
    }
// assign service time for able
    public void assignServiceTimeForAble(int numOfcustomers,int numOfServiceTimeOfAble) {
        //array of one column holds service time for each customer for able service time
        serviceForAble = new int[numOfcustomers];
        for (int i = 0; i < numOfcustomers; i++) {
            //get random value from the random column for each customer
            int value = customer_Random_ServiceTime[i][1];
            for (int j = 0; j < numOfServiceTimeOfAble; j++) {
                // check which range the random number place in 
                if (value > serviceCumulativeAndRangesForAble[j][1] && value <= serviceCumulativeAndRangesForAble[j][2]) {
                    serviceForAble[i] = j + 1;
                }
            }
        }
    }
// calculate the calender for able only
    public void calculateCalanderForAble(int numOfcustomers) {
        int sumArrivalTime = 0;
        //String result="";

        calenderArrayForAble = new int[numOfcustomers][6];

        calenderArrayForAble[0][0] = 0;
        calenderArrayForAble[0][1] = 0;
        calenderArrayForAble[0][2] = 0;
        calenderArrayForAble[0][3] = calenderArrayForAble[0][1] + serviceForAble[0];
        calenderArrayForAble[0][4] = calenderArrayForAble[0][2] + serviceForAble[0];
        calenderArrayForAble[0][5] = 0;
        //result+="ArrTime"+"\t"+"TimeBegin"+"\t"+"WaitTime"+"\t"+"TimeEnds"+"\t"+"TimeSpent"+"\t"+"IdleOfServer";
        //result+=calenderArrayForAble[0][0]+"\t"+calenderArrayForAble[0][1]+"\t"+calenderArrayForAble[0][2]+"\t"+calenderArrayForAble[0][3]+"\t"+calenderArrayForAble[0][4]+"\t"+calenderArrayForAble[0][5]+"\n";
        //int serviceEnds=calenderArray[0][3];
        for (int i = 1; i < numOfcustomers; i++) {
            sumArrivalTime += customer_Random_TimeBetweenArrivals[i][2];
            calenderArrayForAble[i][0] = sumArrivalTime;
            //calenderArray[i][5]=Math.abs(calenderArray[i][0]-serviceEnds) ;
            if (calenderArrayForAble[i - 1][3] > calenderArrayForAble[i][0]) {
                calenderArrayForAble[i][1] = calenderArrayForAble[i - 1][3];
            } else if (calenderArrayForAble[i - 1][3] < calenderArrayForAble[i][0]) {
                calenderArrayForAble[i][1] = calenderArrayForAble[i][0];
            } else {
                calenderArrayForAble[i][1] = calenderArrayForAble[i][0];
            }
            calenderArrayForAble[i][2] = calenderArrayForAble[i][1] - calenderArrayForAble[i][0];
            calenderArrayForAble[i][3] = calenderArrayForAble[i][1] + serviceForAble[i];
            calenderArrayForAble[i][4] = calenderArrayForAble[i][2] + serviceForAble[i];
            calenderArrayForAble[i][5] = calenderArrayForAble[i][1] - calenderArrayForAble[i - 1][3];

            //serviceEnds=calenderArray[i][3];
        }
//        for (int i = 0; i <numOfcustomers; i++) {
//            for (int j = 0; j < 6; j++) {
//                result+=calenderArrayForAble[i][j]+"\t";
//            }
//            System.out.println();
//        }
//        return result;
    }
    
    
    
    // get service time and their probability for baker
     public void getServiceTime_And_their_probabilityForBaker(int numOfServiceTimeOfBaker) 
    {
        //("Enter how many possible values for serviceTime for Baker");
        System.out.println("Enter serviceTime And Probability For Baker");
        serviceTime_probBaker = new double[numOfServiceTimeOfBaker][2];
        for (int i = 0; i < numOfServiceTimeOfBaker; i++) {
            for (int j = 0; j < 2; j++) {
                serviceTime_probBaker[i][j] = input.nextDouble();
            }
        }
    }
     // calculate cumulative and ranges for service time of baker
    public void calculateCumulativeProbabilityAndRanges_ServiceTimeForBaker(int numOfServiceTimeOfBaker) 
    {
        double sumForCum = 0.0;
        serviceCumulativeAndRangesForBaker = new double[numOfServiceTimeOfBaker][3];
        for (int i = 0; i < numOfServiceTimeOfBaker; i++) {
            serviceCumulativeAndRangesForBaker[i][1] = sumForCum*100;
            sumForCum += serviceTime_probBaker[i][1];
            serviceCumulativeAndRangesForBaker[i][0] = sumForCum;
            serviceCumulativeAndRangesForBaker[i][2] = sumForCum*100;
        }
    }
    // assign service time of baker
    public void assignServiceTimeForBaker(int numOfcustomers,int numOfServiceTimeOfBaker)
    {
         serviceForBaker=new int [numOfcustomers];
         for (int i = 0; i <numOfcustomers; i++) {
            int value=customer_Random_ServiceTime[i][1];
            for (int j = 0; j < numOfServiceTimeOfBaker; j++) {
                if(value>serviceCumulativeAndRangesForBaker[j][1] && value<=serviceCumulativeAndRangesForBaker[j][2])
               serviceForBaker[i]=j+1;
            }
        }
    }
    // calculate calender for able and abker together
    public void calculateCalenderForAbleBaker(int numOfcustomers)
    {
        serverChosen= new String [numOfcustomers];
        calenderArrayForAbleBaker=new int[numOfcustomers][9];
        int sumArrivalTime=0;
        calenderArrayForAbleBaker[0][0]=0;
        calenderArrayForAbleBaker[0][1]=0;
        calenderArrayForAbleBaker[0][2]=0;
        serverChosen[0]="Able";
        calenderArrayForAbleBaker[0][3]=serviceForAble[0];
        calenderArrayForAbleBaker[0][4]=0;
        calenderArrayForAbleBaker[0][5]=calenderArrayForAbleBaker[0][4]+serviceForAble[0];
        calenderArrayForAbleBaker[0][6]=0;
        calenderArrayForAbleBaker[0][7]=0;
        calenderArrayForAbleBaker[0][8]=calenderArrayForAbleBaker[0][7]+serviceForAble[0];
        int AbleMax=0;
        int BakerMax=0;
        for (int i = 1; i < numOfcustomers; i++) {
            sumArrivalTime+=customer_Random_TimeBetweenArrivals[i][2];
            calenderArrayForAbleBaker[i][0]=sumArrivalTime;
            for (int j = i; j >=0; j--) {
                
                if(calenderArrayForAbleBaker[j][5]>=AbleMax)
                    AbleMax=calenderArrayForAbleBaker[j][5];
            }
            calenderArrayForAbleBaker[i][1]=AbleMax;
            for (int j = i; j >=0; j--) {
                
                if(calenderArrayForAbleBaker[j][6]>=BakerMax)
                    BakerMax=calenderArrayForAbleBaker[j][6];
            }
            calenderArrayForAbleBaker[i][2]=BakerMax;
            if(calenderArrayForAbleBaker[i][0]>=calenderArrayForAbleBaker[i][1]||calenderArrayForAbleBaker[i][1]<calenderArrayForAbleBaker[i][2]){
                calenderArrayForAbleBaker[i][3]=serviceForAble[i];
                serverChosen[i]="Able";
            }
            else if(calenderArrayForAbleBaker[i][0]>=calenderArrayForAbleBaker[i][2]||calenderArrayForAbleBaker[i][1]>calenderArrayForAbleBaker[i][2]){
                calenderArrayForAbleBaker[i][3]=serviceForBaker[i];
                serverChosen[i]="Baker";
            }
            else{
                calenderArrayForAbleBaker[i][3]=serviceForAble[i];
                serverChosen[i]="Able";
            }
                
            if(calenderArrayForAbleBaker[i][0]>=calenderArrayForAbleBaker[i][1]||calenderArrayForAbleBaker[i][0]>=calenderArrayForAbleBaker[i][2])
                calenderArrayForAbleBaker[i][4]=calenderArrayForAbleBaker[i][0];
            else if(calenderArrayForAbleBaker[i][2]>calenderArrayForAbleBaker[i][0]&&calenderArrayForAbleBaker[i][2]<calenderArrayForAbleBaker[i][1])
                calenderArrayForAbleBaker[i][4]=calenderArrayForAbleBaker[i][2];
            else if(calenderArrayForAbleBaker[i][1]>=calenderArrayForAbleBaker[i][0]||calenderArrayForAbleBaker[i][1]==calenderArrayForAbleBaker[i][2])
                calenderArrayForAbleBaker[i][4]=calenderArrayForAbleBaker[i][1];
            
            if("Able".equals(serverChosen[i]))
                calenderArrayForAbleBaker[i][5]=calenderArrayForAbleBaker[i][3]+calenderArrayForAbleBaker[i][4];
            else if("Baker".equals(serverChosen[i]))
                calenderArrayForAbleBaker[i][6]=calenderArrayForAbleBaker[i][3]+calenderArrayForAbleBaker[i][4];
            
            calenderArrayForAbleBaker[i][7]=Math.abs(calenderArrayForAbleBaker[i][0]-calenderArrayForAbleBaker[i][4]);
            calenderArrayForAbleBaker[i][8]=calenderArrayForAbleBaker[i][7]+calenderArrayForAbleBaker[i][3];
        }
        
    }
    
    
    
    public void displayCalenderForAble(int numOfCustomers)
    {
        System.out.println("ArrT"+"\t"+"TBegin"+"\t"+"WaitT"+"\t"+"TEnds"+"\t"+"TSpend"+"\t"+"Idle");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < numOfCustomers; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(calenderArrayForAble[i][j]+"\t");
            }
            System.out.println();
            System.out.println("-----------------------------------------------");
        }
    
    }
    
    public void displayCalenderForAbleBaker(int numOfCustomers)
    {
        System.out.println("AvT"+"\t"+"AbAv"+"\t"+"BaAv"+"\t"+"ST"+"\t"+"TsB"+"\t"+"AsE"+"\t"+"BsE"+"\t"+"CD"+"\t"+"TS"+"\t"+"serverChosen");
        System.out.println("----------------------------------------------------------------------------");
        for (int i = 0; i < numOfCustomers; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(calenderArrayForAbleBaker[i][j]+"\t");
            }
            System.out.print(serverChosen[i]);
            System.out.println();
            System.out.println("---------------------------------------------------------------------");
        }
    
    }
    
    public void AverageServiceTime(int numOfCustomers){
        double sum=0;
        for (int i = 0; i < numOfCustomers; i++) {
            sum+=serviceForAble[i];
        }
    
        double average=sum/numOfCustomers;
        System.out.println("Average service time= "+average);
    
    }
    public void AverageWaitingTime(int numOfCustomers){
        double sum=0;
        for (int i = 0; i < numOfCustomers; i++) {
            sum+=calenderArrayForAble[i][2];
        }
    
        double average=sum/numOfCustomers;
        System.out.println("Average waiting time= "+average);
    
    }
    public void ProbabilityCustomerHasToWait(int numOfCustomers){
        double counter=0;
        for (int i = 0; i < numOfCustomers; i++) {
            if(calenderArrayForAble[i][2]>0)
                counter++;
        }
    
        double probability=counter/numOfCustomers;
        System.out.println("Probability that a customer has to wait in the queue= "+probability);
    
    }
    public void thePortionOfIdleTimeOfTheServer(int Cnum){
        double totalIdleTimeOfServer=0;
        double totalRunTimeOfSimulation=0;
        for (int i = 0; i < Cnum; i++) {
            totalIdleTimeOfServer+=calenderArrayForAble[i][5];
            totalRunTimeOfSimulation+=calenderArrayForAble[i][3];
        }
    
        double portion=totalIdleTimeOfServer/totalRunTimeOfSimulation;
        System.out.println("the Portion Of Idle Time Of The Server= "+portion);
    
    }
    
    public void calculateEffeciency(int numOfCustomers)
    {
        int counterOfCustomersThatAbleServe=1;
        int counterOfCustomersThatBakerServe=1;
        for (int i = 0; i < numOfCustomers; i++) {
            if(calenderArrayForAbleBaker[i][5]>0)
                counterOfCustomersThatAbleServe++;
            else
                counterOfCustomersThatBakerServe++;
        }
        double ableEfficency,bakerEfficency;
        ableEfficency=counterOfCustomersThatAbleServe/numOfCustomers;
        bakerEfficency=counterOfCustomersThatBakerServe/numOfCustomers;
        System.out.print("Able Efficency: "+ableEfficency+"%"+"\n"+"Baker Efficency: "+bakerEfficency+"%"+"\n");
    }
    
    public void averageCallerDelay(int numOfCustomers){
        double sum=0;
        for (int i = 0; i < numOfCustomers; i++) {
            sum+=calenderArrayForAbleBaker[i][7];
        }
        double average=sum/numOfCustomers;
        System.out.println("the average caller delay= "+average);
    }
    public void getDataForDisplay()
    {
        
    
    }
}
 