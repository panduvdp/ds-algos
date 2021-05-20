package ds.arrays;

import ds.Util;
import java.util.Arrays;

/**
 * There are some random weights on a conveyor that can be loaded onto a ship. You are given to
 * select a ship which can carry upto a limited weight. Our goals is to finding a ship with minimum
 * capacity that can carry all the weights on the conveyor to  another port in the given number of days.
 * The ship can only take one trip per day. And weights on the conveyor can't be reordered.
 * They must be loaded in the same order as they come on conveyor.
 */

public class ConveyorBeltLoading {

  public static int minCapacity(int[] weights, int noOfTrips) {
    int maxWt = 0 ,totalWt = 0;
    int[] wtUntilIndex = new int[weights.length];
    for(int i=0;i < weights.length; i++) {
      totalWt += weights[i];
      wtUntilIndex[i] = totalWt;
      if(maxWt< weights[i])
        maxWt = weights[i];
    }

    int minCapacity = totalWt % noOfTrips == 0 ? totalWt / noOfTrips : totalWt / noOfTrips + 1;
    System.out.println("max wt:" + maxWt);
    System.out.println("wtUntilIndex: " + Arrays.toString(wtUntilIndex));
    System.out.println("LowerBound:" + minCapacity);

    long millis = System.currentTimeMillis();
    int bruteForceCount = simulateTripsBruteForce(weights, wtUntilIndex, noOfTrips, minCapacity);
    long afterBf = System.currentTimeMillis();
    System.out.println("bruteForceCount: "+bruteForceCount);
    int optimized = simulateTripsOptimized(weights, noOfTrips, minCapacity);
    System.out.println("BruteFroce time: "+(afterBf-millis)+". Optimized: "+ (System.currentTimeMillis()-afterBf));

    return  optimized;
  }

  public static int simulateTripsOptimized(int[] weights, int noOfTrips, int minCapacity) {
    int tripNo = 0, currentWt, i=0, minWt=Integer.MAX_VALUE;

    while (tripNo < noOfTrips) {
      tripNo++;
      currentWt= 0;
      for (; i < weights.length; ) {
        if (!(currentWt + weights[i] <= minCapacity)) {
          break;
        }
        currentWt += weights[i];
        i++;
      }
      if(i== weights.length) {
        return minCapacity;
      }
      //find lower weight to be added to the minCapacity when the current weight can't be sufficient
      if(minWt > currentWt+weights[i]) {
        minWt = currentWt+weights[i];
      }

      if(tripNo == noOfTrips) {
        System.out.println("******** "+ minCapacity+" ****** "+ minWt);
        minCapacity = minWt;
        i = 0;
        tripNo = 0;
        minWt = Integer.MAX_VALUE;
      }

    }
    return -1;
  }

  public static int simulateTripsBruteForce(int[] weights,int[] wtUntilIndex, int noOfTrips, int minCapacity) {
    int tripNo = 0;
    int i =0, currentWt;
    while (tripNo < noOfTrips) {
      tripNo++;
      currentWt= 0;
      for (; i < weights.length; ) {
        if (!(currentWt + weights[i] <= minCapacity)) {
          break;
        }
        currentWt += weights[i];
        i++;
      }
      if(i== weights.length) {
        return minCapacity;
      }
      int remTrips = noOfTrips - tripNo;
      int remWt = wtUntilIndex[weights.length-1] - wtUntilIndex[i-1];
      if(remTrips * minCapacity < remWt) {
        minCapacity++; // Can be optimized further. Revisit later.
        i = 0;
        tripNo = 0;
      }

    }
    return -1;

  }

  /**
   * Does Binary search on a sorted array and return the element if found otherwise returns
   * closest value which is less than the key.
   * @param a
   * @param start
   * @param end
   * @param key
   * @return
   */

  public static int closestIndex(int[] a, int start, int end, int key) {
    int index = -1;
    if(end<start) {
      return index;
    }
    int mid = (start+end)/2;
    if(a[mid] == key) {
      return mid;
    }  else if (a[mid] > key) {
      if(mid>-0 && a[mid-1] <= key) {
        return mid -1;
      }
      return closestIndex(a, start, mid - 1, key);
    } else {
      if(mid+1<end+1 && a[mid+1] > key) {
        return mid;
      }
      return closestIndex(a, mid+1, end, key);
    }

  }

//  /**
//   * Doesn't work as expected. Revisit later
//   */
//
//  public static int minCapacityOptimized(int[] weights, int noOfTrips) {
//    int maxWt = 0 , totalWt = 0;
//    int[] wtUntilIndex = new int[weights.length];
//    for(int i=0;i < weights.length; i++) {
//      totalWt += weights[i];
//      wtUntilIndex[i] = totalWt;
//      if(maxWt< weights[i])
//        maxWt = weights[i];
//    }
//
//    int idealCapacity = totalWt % noOfTrips == 0 ? totalWt / noOfTrips : totalWt / noOfTrips + 1;
//    int minCapacity = idealCapacity;
//    System.out.println(maxWt);
//    System.out.println(totalWt);
//    System.out.println("wtUntilIndex: " + Arrays.toString(wtUntilIndex));
//    int currentTrip = noOfTrips-1;
//    int highIdx = wtUntilIndex.length-1;
//    while(currentTrip>0) {
//      int maxCoveredByCurrentTrip =  currentTrip * minCapacity;
//      int closestIndex = closestIndex(wtUntilIndex, 0, highIdx, maxCoveredByCurrentTrip);
//      int diff = wtUntilIndex[highIdx] - wtUntilIndex[closestIndex];
//      if(diff > minCapacity) {
//        int i = 1;
//        while(wtUntilIndex[highIdx] - wtUntilIndex[closestIndex+i] > minCapacity){
//          i++;
//        }
//        minCapacity = wtUntilIndex[closestIndex+i] % currentTrip == 0 ?
//            wtUntilIndex[closestIndex+i] / currentTrip :
//            wtUntilIndex[closestIndex+i] / currentTrip + 1;
//      }
//      currentTrip--;
//      highIdx = closestIndex+1;
//    }
//    return minCapacity;
//
//  }

  public static void main(final String[] args) {
    int[] original = {23, 142, 77, 104, 66, 119, 105, 0, 40, 23, 93, 55, 22, 43, 107}; //{61, 82, 78, 89, 74, 26, 79, 39, 7, 49}; //{95, 8, 68, 37, 20, 3, 98, 83, 64, 24, 24, 98, 0, 12, 39};//
    original = Util.generateRandomArray(1500, 1500);
    System.out.println("Original: " + Arrays.toString(original));
    int minCapacityVal = 0; //minCapacityOptimized(original, 5);
    //System.out.println("minCapacityVal: "+minCapacityVal);
     minCapacityVal = minCapacity(original, 8);
    System.out.println("minCapacityVal: "+minCapacityVal);

  }

}
