package com.data.structures.algorithms.Stack.Medium;

import java.util.Stack;

/**
 * Problem Link: https://leetcode.com/problems/asteroid-collision/
 *
 * This problem involves simulating the collision of asteroids moving in a straight line.
 * The solution uses a stack to manage the state of the asteroids as they collide.
 */
public class AsteroidCollision {


    /**
     * Collision occurs in below conditions:
     * - when a right-moving asteroid (positive value) collides with a left-moving asteroid (negative value).
     * - If two asteroids meet, the smaller one will explode.
     * - If both are the same size, both will explode.
     * Algorithm:
     * 1. We will use a stack to keep track of the asteroids that are currently moving right.
     * 2. We will iterate through each asteroid in the input array.
     * 3. If the asteroid is moving right (positive value), we will push it onto the stack.
     * 4. If the asteroid is moving left (negative value), we will check for collisions:
     *    - While the stack is not empty and the top of the stack is a right-moving asteroid (positive value),
     *    - we will compare the absolute values of the current left-moving asteroid and the top of the stack.
     *    - If the absolute value of the left-moving asteroid is greater than the top of the stack, we pop the stack (the right-moving asteroid is destroyed).
     * 5. If the absolute value of the left-moving asteroid is equal to the top of the stack, we pop the stack (both asteroids are destroyed).
     * 6. If the top of the stack is also a left-moving asteroid or the stack is empty, we push the current left-moving asteroid onto the stack.
     * 7. Finally, we convert the stack to an array and return it as the result.
     * Time Complexity: O(n), where n is the number of asteroids.
     * Space Complexity: O(n), in the worst case, if all asteroids are moving in the same direction.
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int ele : asteroids) {
            // If the current element is positive, we can safely push it onto the stack
            if (ele > 0) {
                st.push(ele);
                continue;
            }
            else {
                // if the current element is negative, we need to check for collision and pop elements from stack
                while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(ele)) {
                    st.pop();
                }
            }

            // If a top element is positive and equal to the current negative element, then pop it
            if (!st.isEmpty() && st.peek() == Math.abs(ele))
                st.pop();

            // If the stack is empty or top of the stack is also a left-moving asteroid, then add the current element
            else if (st.isEmpty() || st.peek() < 0)
                st.push(ele);
        }

        int[] arr = new int[st.size()];
        int i = 0;
        for(int ele : st)
        {
            arr[i] = ele;
            i++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        int[] result = asteroidCollision(asteroids);
        for (int asteroid : result) {
            System.out.print(asteroid + " ");
        }
    }
}
