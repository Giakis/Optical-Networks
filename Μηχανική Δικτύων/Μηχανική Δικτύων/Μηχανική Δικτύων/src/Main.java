import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        FIFO Queue_1 = new FIFO();
        FIFO Queue_2 = new FIFO();
        FIFO Queue_3 = new FIFO();
        FIFO Queue_4 = new FIFO();
        FIFO Queue_5 = new FIFO();
        FIFO Queue_6 = new FIFO();
        FIFO Queue_7 = new FIFO();
        FIFO Queue_8 = new FIFO();
        MK Wave_length= new MK();
        Integer TotalDelay_1=0,TotalDelay_2=0,TotalDelay_3=0,TotalDelay_4=0,TotalDelay_5=0,TotalDelay_6=0,TotalDelay_7=0,TotalDelay_8=0;

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        double prob=0;
        do {
            System.out.println("Please Enter the Possibility between 0,1 and 1,0");  // Output user input
            prob = scan.nextDouble();  // Read user input
        }while (prob<0.1 || prob>1.0);

        int TimeSlot=0,i=0;
        while (TimeSlot<50000){
            /**
             *  Ελέγχω με την συνάρτηση probability, αν θα προσθέσω στοιχείο στην ουρά των υπολογιστών μου
             */
            if (FIFO.probability(prob)==true){
                Queue_1.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_2.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_3.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_4.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_5.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_6.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_7.added(random.nextInt(),TimeSlot);
            }
            if (FIFO.probability(prob)==true){
                Queue_8.added(random.nextInt(),TimeSlot);
            }


            /**
            * Ελέγχω αν οι υπολογιστές μου θέλουν να μεταδώσουν πακέτο(temp == 1) & αν υπάρχει στην ουρά πακέτο προς μετάδοση(Queue_1.Length()).
            * Αν η συνάρτηση  Wave_length.Collision() επιστρέψει την  τιμή 1,τότε μεταδίδει ο πρώτος υπολογιστής από το ζεύγος.
            * Αν η συνάρτηση  Wave_length.Collision() επιστρέψει την τιμή 2, τότε μεταδίδει ο δεύτερος υπολογιστής απο το ζεύγος.
            * Σε περίπτωση που η συνάρτηση  Wave_length.Collision() επιστρέψει την τιμή 0, τότε υπάρχει περίπτωση να έχουμε collision είτε να μην θέλει να μεταδώσει κανένας υπολογιστής.
            */
            int temp_1= Wave_length.Collision();
            if (temp_1 == 1 && Queue_1.Length()) {
                Queue_1.remove(TimeSlot);
                TotalDelay_1+= Queue_1.getTotalDelay();
            }
            if (temp_1 == 2 && Queue_2.Length()) {
                Queue_2.remove(TimeSlot);
                TotalDelay_2+= Queue_2.getTotalDelay();
            }

            int temp_2 = Wave_length.Collision();
            if (temp_2 == 1 && Queue_3.Length()) {
                Queue_3.remove(TimeSlot);
                TotalDelay_3+= Queue_3.getTotalDelay();
            }
            if (temp_2 == 2 && Queue_4.Length()) {
                Queue_4.remove(TimeSlot);
                TotalDelay_4+= Queue_4.getTotalDelay();
            }

            int temp_3 = Wave_length.Collision();
            if (temp_3 == 1 && Queue_5.Length()) {
                Queue_5.remove(TimeSlot);
                TotalDelay_5+= Queue_5.getTotalDelay();
            }
            if (temp_3 == 2 && Queue_6.Length()) {
                Queue_6.remove(TimeSlot);
                TotalDelay_6+= Queue_6.getTotalDelay();
            }

            int temp_4 = Wave_length.Collision();
            if (temp_4 == 1 && Queue_7.Length()) {
                Queue_7.remove(TimeSlot);
                TotalDelay_7+= Queue_7.getTotalDelay();
            }
            if (temp_4 == 2 && Queue_8.Length()) {
                Queue_8.remove(TimeSlot);
                TotalDelay_8+= Queue_8.getTotalDelay();
            }
            TimeSlot++;
        }
        /**
         * Η μεταβλητή  Total_Packet_sent αναπαριστά το άθροισμα όλων των πακέτων που στάλθηκαν από τους υπολογιστές.
         * Η μεταβλητή Total_Packet_lost αναπαριστά το άθροιμσα όλων των πακέτων που δεν δέχτηκαν οι υπολογιστές στις ουρές τους.
         * Η μεταβλητή Total_Packet_creator αναπαριστά το άθροισμα όλων των πακέτων που είτε μπήκαν στην ουρά είτε χάθηκαν.
         * Η μεταβλητή Total_Delay αναπαριστά το άθροισμα της χρονικής στιγμής απο την στιγμή που μπήκαν στην ουρά - την χρονική στιγμή που στάλθηκαν.
         */
        int Total_packet_sent=Queue_1.getTotal_packets_sent()+Queue_2.getTotal_packets_sent()+Queue_3.getTotal_packets_sent()+Queue_4.getTotal_packets_sent()+Queue_5.getTotal_packets_sent()+Queue_6.getTotal_packets_sent()+Queue_7.getTotal_packets_sent()+Queue_8.getTotal_packets_sent();
        double Thoughput=(double) Total_packet_sent/TimeSlot;
        System.out.println("Thoughput: "+Thoughput);

        int Total_packet_lost=Queue_1.getOverflow()+Queue_2.getOverflow()+Queue_3.getOverflow()+Queue_4.getOverflow()+Queue_5.getOverflow()+Queue_6.getOverflow()+Queue_7.getOverflow()+Queue_8.getOverflow();
        int Total_packet_creator=Queue_1.getTotal_packet_creator()+Queue_2.getTotal_packet_creator()+Queue_3.getTotal_packet_creator()+Queue_4.getTotal_packet_creator()+Queue_5.getTotal_packet_creator()+Queue_6.getTotal_packet_creator()+Queue_7.getTotal_packet_creator()+Queue_8.getTotal_packet_creator();
        double Packet_loss_rate= (double) Total_packet_lost/ Total_packet_creator;
        System.out.println("Packet_loss_rate: "+Packet_loss_rate);

        int Total_Delay=Queue_1.getTotalDelay()+Queue_2.getTotalDelay()+Queue_3.getTotalDelay()+Queue_4.getTotalDelay()+Queue_5.getTotalDelay()+Queue_6.getTotalDelay()+Queue_7.getTotalDelay()+Queue_8.getTotalDelay();
        double Average_delay= (double) Total_Delay/Total_packet_sent;
        System.out.println("Average_delay: "+Average_delay);

    }
}
