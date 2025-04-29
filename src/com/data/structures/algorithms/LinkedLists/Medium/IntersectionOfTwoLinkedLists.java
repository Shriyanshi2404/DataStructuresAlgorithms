package com.data.structures.algorithms.LinkedLists.Medium;

public class IntersectionOfTwoLinkedLists {

    static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    /*
    Brute Force
    T(n)=O(N^2) S(n)=O(1)
    Idea: We know intersection means a common attribute present between two entities. Here, we have linked lists as given entities.
    1. Keep any one of the list to check its node present in the other list. Here, we are choosing the list2 for this task.
    2. Iterate through the other list. Here, it is the list1
    3. Check if the both nodes are the same. If yes, we got our first intersection node.
    4. If not, continue iteration.
    5. If we did not find an intersection node and completed the entire iteration of the list2, then there is no intersection between the provided lists. Hence, return null.
    */
    public static ListNode getIntersectionNodeBruteForce(ListNode headA, ListNode headB) {
        while(headB != null)
        {
            ListNode curr = headA;
            while(curr != null)
            {
                if(curr == headB)
                    return headB;

                curr = curr.next;
            }
            headB = headB.next;
        }
        return null;
    }

    /*
    Optimal Approach
    T(n)=O(N) S(n)=O(1)
    1. Find the length of both lists.
    2. Find the positive difference between these lengths.
    3. Move the dummy pointer of the larger list by the difference achieved. This makes our search length reduced to a smaller list length.
    4. Move both pointers, each pointing two lists, ahead simultaneously if both do not collide.
    */
    public static int findLength(ListNode head)
    {
        int count = 0;
        ListNode curr = head;
        while(curr != null)
        {
            curr = curr.next;
            count++;
        }
        return count;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = findLength(headA);
        int l2 = findLength(headB);
        if(l1<=l2)
        {
            int diff = l2-l1;
            ListNode list2 = headB;
            while(diff != 0)
            {
                list2 = list2.next;
                diff--;
            }

            ListNode list1 = headA;
            while(list1 != list2)
            {
                list1 = list1.next;
                list2 = list2.next;
            }
            return list1;
        }
        else
        {
            int diff = l1-l2;
            ListNode list1 = headA;
            while(diff != 0)
            {
                list1 = list1.next;
                diff--;
            }

            ListNode list2 = headB;
            while(list1 != list2)
            {
                list1 = list1.next;
                list2 = list2.next;
            }
            return list2;
        }
    }

    public static void main(String[] args)
    {
        // Shared tail: 8 -> 4 -> 5
        ListNode shared = new ListNode(8);
        shared.next = new ListNode(4);
        shared.next.next = new ListNode(5);

        // First list: 4 -> 1 -> [8 -> 4 -> 5]
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(1);
        head1.next.next = shared;

        // Second list: 5 -> 6 -> 1 -> [8 -> 4 -> 5]
        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(1);
        head2.next.next.next = shared;

        ListNode curr = getIntersectionNode(head1, head2);
        if (curr != null) {
            System.out.print("Intersection at node with value: " + curr.data);
        } else {
            System.out.println("No intersection found.");
        }
    }
}
