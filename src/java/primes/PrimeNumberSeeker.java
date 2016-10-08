package primes;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a Thread that continuously finds new prime numbers<br> and
 * sleeps for a specified delay time afterwards. The list of all<br> discovered
 * prime numbers is always saved.
 *
 * @author Gabriel Pawlowsky
 * @version 2012-10-14
 */
public class PrimeNumberSeeker extends Thread {

    private static final int delay = 10000;
    private int current = 3;
    private ArrayList<Integer> primes;
    private Date dateOFLastDiscovery;

    public PrimeNumberSeeker() {
        primes = new ArrayList<Integer>();
        primes.add(2);
    }

    /**
     * This run method of the thread discoveres prime numbers by saving<br> the
     * old prime numbers and trying to divide the new prime number<br> by any of
     * the old. If it can be divided by one of them its not a<br> prime number.
     * Otherwise it is saved to the list of prime numbers.
     */
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            //boolean indicating if the current number is a prime.
            boolean isPrime = true;
            //trying to divide it by the old prime numbers
            for (int i = 0; i < primes.size(); i++) {
                if (current % primes.get(i) == 0) {
                    isPrime = false;
                }
            }
            //If it is still a prime after trying to divide it...
            if (isPrime) {
                //add the prime number to the list
                primes.add(current);
                //Set the date of discovering the last prime number
                this.setDateOFLastDiscovery(new Date());
            }
            //Sleep for the specified delay.
            try {
                sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimeNumberSeeker.class.getName()).log(Level.SEVERE, null, ex);
            }
            //increment the currently investigated number
            current++;
        }
    }

    /**
     * @return the primes
     */
    public ArrayList<Integer> getPrimes() {
        return primes;
    }

    /**
     * @param primes the primes to set
     */
    public void setPrimes(ArrayList<Integer> primes) {
        this.primes = primes;
    }

    /**
     * @return the dateOFLastDiscovery
     */
    public Date getDateOFLastDiscovery() {
        return dateOFLastDiscovery;
    }

    /**
     * @param dateOFLastDiscovery the dateOFLastDiscovery to set
     */
    public void setDateOFLastDiscovery(Date dateOFLastDiscovery) {
        this.dateOFLastDiscovery = dateOFLastDiscovery;
    }
}
