/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import primes.PrimeNumberSeeker;

/**
 *
 * @author Gabriel
 */
public class PrimeNumberSeekerTest {

    PrimeNumberSeeker numberSeeker;

    public PrimeNumberSeekerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Sets up the PrimeNumberSeeker before the test start.
     */
    @Before
    public void setUp() {
        this.numberSeeker = new PrimeNumberSeeker();
    }

    @After
    public void tearDown() {
    }

    /**
     * Tests if the last discovered number is a prime number. This is tested<br>
     * by a working algorithm from stackoverflow.
     */
    @Test
    public void testRun() {
        this.numberSeeker.start();
        assertTrue(isPrime(this.numberSeeker.getPrimes().get(0)));
        this.numberSeeker.interrupt();
    }

    /**
     * Tests if the getPrimes()-methods throws an exception when you haven´t<br>
     * executed the start/run method before.
     */
    @Test
    public void testGetPrimesWithoutRunExecution() {
        numberSeeker.getPrimes();
    }

    /**
     * Method from
     * http://stackoverflow.com/questions/2385909/most-elegant-way-to-write-isprime-in-java<br>
     * that indicates whether a number is a prime or not. This is a working<br>
     * version and I use it to test if my method works correctly;
     *
     * @param n the number that shall be tested
     * @return whether the the number is prime or not
     */
    boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        long sqrtN = (long) Math.sqrt(n) + 1;
        for (long i = 6L; i <= sqrtN; i += 6) {
            if (n % (i - 1) == 0 || n % (i + 1) == 0) {
                return false;
            }
        }
        return true;
    }
}
