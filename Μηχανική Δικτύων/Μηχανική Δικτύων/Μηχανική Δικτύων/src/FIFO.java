//package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class FIFO {
    public Queue<Integer> queue= new LinkedList<Integer>();
    public Queue<Integer> TimeQueue= new LinkedList<Integer>();
    public Integer Timeremovedele;


    /**
     * Getter και Setter
     * overflow = Total packet loss
     */
    private Integer overflow=0,total_packets_sent=0,totalDelay=0,total_packet_creator=0;
    // Getter overflow
    public Integer getOverflow() {
        return overflow;
    }
    // Setter overflow
    public void setOverflow(Integer newOverflow) {
        this.overflow = newOverflow;
    }

    // Getter total_packets_sent
    public Integer getTotal_packets_sent() {
        return total_packets_sent;
    }
    // Setter total_packets_sent
    public void setTotal_packets_sent(Integer newTotal_packets_sent) {
        this.total_packets_sent = newTotal_packets_sent;
    }

    // Getter totalDelay
    public Integer getTotalDelay() {
        return totalDelay;
    }
    // Setter total_packets_sent
    public void setTotalDelay(Integer newTotalDelay) {
        this.totalDelay = newTotalDelay;
    }

    // Getter TotalPacketCreator
    public Integer getTotal_packet_creator() {
        return total_packet_creator;
    }
    // Setter TotalPacketCreator
    public void settotal_packet_creator(Integer newtotal_packet_creator) {
        this.totalDelay = newtotal_packet_creator;
    }


    /**
     * Η συνάρτηση added ελέγχει αν χωράει πακέτο στην ουρά.
     * Στην περίπτωση που δεν χωράει το πακέτο στην ουρά,γίνεται εμφάνιση του  μηνύματος όπως ζητείται.
     * Ακόμα γίνεται αύξηση του κατάλληλου μετρητή(overflow) κατά 1.
     * Αντίθετα στην περίπτωση που χωράει το πακέτο στην ουρά, μπαίνει στην ουρά και σε μια διαφορετική ουρά αποθηκεύεται η χρονική στιγμή που μπήκε.
     * Και στις δύο περιπτώσεις αυξάνεται ο μετρητής Total_packet_creator κατά 1.
     */
    public void added(int x,int TimeSlot){
        if (queue.size()>4) {
            System.out.println("Buffer Full");
            overflow++;
        }else{
            queue.add(x);
            TimeQueue.add(TimeSlot);
        }
        total_packet_creator++;
    }

    /**
     * Η συνάρτηση remove διαγράφει τα στοιχεία της ουράς όταν ζητείται.
     * Αρχικά ελέγχει αν υπάρχουν δεδομένα στην ουρά (SAFETY).
     * Αν υπάρχουν δεδομένα διαγράφει το πρώτο στοιχείο και από τις δύο ουρές (ουρά υπολογιστή και ουρά χρόνου).
     * Όταν διαγραφεί ένα πακέτο, το πακέτο μεταδόθηκε χωρίς κανένα πρόβλημα (Collision) και αυξάνει τον μετρητή total_packets_sent κατά 1.
     * Αφού έχει σταλθεί και η χρονική στιγμή που βρισκόμαστε, υπολογίζουμε το Delay (totalDelay) αφαιρώντας την χρονική στιγμή που βρισκόμαστε απο την χρονική στιγμή που μπήκε το πακέτο.
     */
    public void remove(int Timeslot) {
        // To remove the head of queue.
        // In this the oldest element '0' will be removed
        if (queue.size() > 0) {
            queue.remove();
            Timeremovedele = TimeQueue.remove();
            int temp=Timeslot-Timeremovedele;
            totalDelay+=temp;
            total_packets_sent++;
      }
    }

    /**
     * Η συνάρτηση probability ελέγχει αν θα μεταδοθεί πακέτο στον υπολογιστή με βάση την πιθανότητα που έδωσε ο χρήστης στο input.
     * Στην περίπτωση που η πιθανότητα που προκύπτει απο την ψευδοτυχαία μεταβλητή prob είναι μικρότερη απο την τιμή της πιθανότητας που εισήγαγε ο χρήστης επιστρέφει την τιμή True.
     * Αντίθετα επιστρέφει την τιμή false.
     */
    public static boolean probability(double x){
        Random random =new Random();
        double prob =random.nextDouble();
        if (prob <= x){
            return true;
        }
        return false;
    }

    /**
     *  Η συνάρτηση Length ελέγχει αν υπάρχουν στοιχεία στην ουρά.
     * Αν υπάρχουν επιστρέφει True.
     * Στην περίπτωση που δεν υπάρχουν στοιχεία στην ουρά επιστρέφει false.
     */
    public boolean Length() {
        if (queue.size() >= 1){
            return true;
        }
        return false;
    }

}


















//
//
//// Adds radom elements to queue
//                for (int i = 0; i < 5; i++)
//        q.add(random.nextInt());
//
//        // Display contents of the queue.
//        System.out.println("Elements of queue:" + q);
//
//        // To remove the head of queue.
//        // In this the oldest element '0' will be removed
//        removedele = q.remove();
//        System.out.println("removed element:" + removedele);
//        System.out.println(q);
//
//        // To view the head of queue
//        head = q.peek();
//        System.out.println("head of queue:" + head);
//
//        // Rest all methods of collection interface,
//        // Like size and contains can be used with this
//        // implementation.
//        size = q.size();
//        System.out.println("Size of queue:" + size);
//
//
//
//







