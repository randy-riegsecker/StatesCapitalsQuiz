package com.randyriegsecker.statescapitalsquiz;

/*
Author: Randy Riegsecker
Date: 2022/05/31

Randomizer: A class that creates a random sized array of integers of size passed.
This is a variant of the Fisher-Yates shuffle for excellent randomizing.
It avoids the negative effects of over-shuffling.
Here is an excellent video demo by Adam Khoury:  https://youtu.be/tLxBwSL3lPQ
Wikipedia explanation:  https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
*/

import java.util.ArrayList;
import java.util.Random;
import java.security.SecureRandom;

/*
To Instantiate:
sizePassed = Length of array to randomize (integer)
Randomizer n = new Randomizer(sizePassed);
Integer range starts at 0 and ends at sizePassed-1
For example, if {sizePassed} is 10, the integer range is from 0-9.
*/

public class Randomizer {

    // Create a dynamic list
    ArrayList<Integer> numberList = new ArrayList<Integer>();

    int size;   // size of the ArrayList, i.e. size 15 will shuffle integers 0-14
    private int counter = 0;  // Temporary use
    private int returnIndex = 0;  // Tracks current index position in the ArrayList

    // Instantiate a randomized ArrayList of integers size 'sizePassed'
    public Randomizer(int sizePassed) {

        if (sizePassed < 2) {
            System.out.println("Size must be between 2 and 2147483648");
        }
        // save size to instance variable;
        size = sizePassed;

        // fill the array;
        fillNumberList();

        // shuffle the array
        shuffleNumberList();
    }

    // Return the integer value in the current position from the ArrayList
    // and increment the position; automatically reshuffle at the end
    public int nextInt() {
        returnIndex = counter;
        // If you've gone through the entire array, the default behavior is to
        // shuffle the array and start at the element 0.
        if (counter >= size) {
            counter = 0;
            returnIndex = 0;
            // no need to refill the array, instead reshuffle the current shuffled array
            shuffleNumberList();
        }
        ++counter;
        return numberList.get(returnIndex);
    }

    // Return the integer value in the current position from the ArrayList
    public int getNext() {
        return numberList.get(returnIndex);
    }

    // Print the integer value in the current position from the ArrayList
    public void showNext() {
        System.out.println("Current counter = " + returnIndex + " Current value = " + numberList.get(returnIndex));
    }

    // Return the integer value in a specific position from the ArrayList
    public int getInt(int indexPosition) {
        return (numberList.get(indexPosition));
    }

    // Return the whole array
    public ArrayList<Integer> returnList() {
        return ((ArrayList<Integer>) numberList.clone());
    }

    // Shuffle or Reshuffle the ArrayList
    private void shuffleNumberList() {

        // SecureRandom is much better than Random
        Random random = new SecureRandom();

        // Iterate through every element except to {sizeOfArray - 1}
        // For element in position {i}, randomly choose an array position between
        // itself and the last element, inclusive, to swap.
        for (int i = 0; i < size - 1; i++) {
            // Get a random index of the array past the current index
            // ... The argument is an exclusive bound.
            //     It will not go past the array's end.
            //  System.out.print("Shuffle Index: " + i);
            int randomValue = i + random.nextInt(size - i);

            // Now swap the random element with the current element i
            int randomElement = numberList.get(randomValue);
            numberList.set(randomValue, numberList.get(i));
            numberList.set(i, randomElement);
        }
    }

    // Initially fill the ArrayList with integers in order (0 to size-1).
    public void fillNumberList() {
        for (int j = 0; j < size; j++) {
            numberList.add(j);
        }
    }
}

