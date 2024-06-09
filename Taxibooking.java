package taxibooking;

import taxiBooking.Taxi;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Taxibooking {

    public static ArrayList<Taxi> taxilist=new ArrayList<Taxi>();
    public static int taxiLimit=4,idgenerator=1;
    private static ArrayList<Taxi> taxibookinglist=new ArrayList<Taxi>();

    public static String booking(char picklocation,char droplocation, int picuptime) throws CloneNotSupportedException{
         if(taxiLimit>taxilist.size()){
             taxilist.add(new Taxi());
         }
         int min= Integer.MAX_VALUE;
         Taxi taxiready=null;

         for(Taxi t:taxilist){
             if(t.getDropTime()<=picuptime && abs(picklocation-droplocation)<min){
                 if(abs(picklocation-droplocation)==min){
                        if(taxiready!=null && t.getEarnings()<taxiready.getEarnings()){
                            taxiready=t;
                        }
                 }
                 else{
                     taxiready=t;
                     min=abs(picklocation-t.getCurrentLocation());
                 }
             }
         }
        if(taxiready!=null)
        {
            taxiready.setCustomerId(idgenerator++);
            taxiready.setPickupTime(picuptime);
            taxiready.setPickupLocation(picklocation);
            taxiready.setDropLocation(droplocation);
            taxiready.setCurrentLocation(droplocation);
            taxiready.setDropTime(picuptime + Math.abs(droplocation-picklocation));
            taxiready.setEarnings((taxiready.getEarnings()) + (Math.abs(droplocation-picklocation)*15-5)*10 + 100);
            taxiready.setTaxiId(taxilist.indexOf(taxiready)+1);
            taxibookinglist.add((Taxi)taxiready.clone()); //clone object
        }

        return taxiready!=null?"Taxi number "+taxiready.getTaxiId()+" is booked!":"Taxis not available";

    }
    public static void display(){
        System.out.print("-------------");
        for(Taxi t:taxibookinglist){
            System.out.println(t.toString());
            System.out.print("---------------");
        }
    }


}
