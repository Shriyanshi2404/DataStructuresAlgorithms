# LinkedList Problems - Quick Revision Guide

## Table of Contents
- [Easy Problems](#easy-problems)
- [Medium Problems](#medium-problems)
- [Hard Problems](#hard-problems)

---

## Easy Problems

### 1. Insertion in LinkedList
**Problem**: Insert a node at a given position in a linked list.

**Optimal Solution**:
```java
public static Node insert(Node head, int n, int pos, int val) {
    Node newNode = new Node(val);
    if(pos == 0 || head == null) {
        newNode.next = head;
        head = newNode;
        return head;
    }
    Node prev = head;
    Node curr = head;
    for(int i=0; i<pos; i++) {
        prev = curr;
        curr = curr.next;
    }
    prev.next = newNode;
    newNode.next = curr;
    return head;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Create new node, handle head insertion separately, traverse to position, update pointers.

### 2. Length of LinkedList
**Problem**: Find the length of a linked list.

**Optimal Solution**:
```java
public static int length(Node head) {
    int length = 0;
    Node curr = head;
    while(curr != null) {
        curr = curr.next;
        length++;
    }
    return length;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Simple traversal with counter.

### 3. Search Node in LinkedList
**Problem**: Search for a given value in a linked list.

**Optimal Solution**:
```java
public static int searchInLinkedList(Node head, int k) {
    if(head == null) return 0;
    Node curr = head;
    while(curr != null) {
        if(curr.val == k) return 1;
        curr = curr.next;
    }
    return 0;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Linear search with early return.

---

## Medium Problems

### Basic Operations

#### 4. Reverse LinkedList
**Problem**: Reverse a linked list.

**Iterative Solution**:
```java
public static ListNode reverseList(ListNode head) {
    if(head == null || head.next == null) return head;
    
    ListNode prev = null, curr = head, next = null;
    while(curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```
**Recursive Solution**:
```java
public static ListNode reverseLL(ListNode head) {
    if(head == null || head.next == null) return head;
    
    ListNode smallHead = reverseLL(head.next);
    head.next.next = head;
    head.next = null;
    return smallHead;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1) [Iterative], O(N) [Recursive]
**Quick Note**: 3-pointer technique for iterative, recursive uses call stack.

#### 5. Middle Node of LinkedList
**Problem**: Find the middle node of a linked list.

**Optimal Solution (Two Pointer)**:
```java
public static Node middleNode(Node head) {
    if(head == null || head.next == null) return head;
    
    Node slow = head;
    Node fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Fast pointer moves 2x speed, slow pointer will be at middle.

#### 6. Delete Middle Node
**Problem**: Delete the middle node of a linked list.

**Optimal Solution**:
```java
public static ListNode deleteMiddleNode(ListNode head) {
    if(head == null || head.next == null) return null;
    
    ListNode slow = head;
    ListNode fast = head.next.next;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    if(slow.next != null) {
        slow.next = slow.next.next;
    }
    return head;
}
```
**Time Complexity**: O(N/2) | **Space Complexity**: O(1)
**Quick Note**: Use two pointers, fast starts 2 nodes ahead.

#### 7. Remove Nth Node From End
**Problem**: Remove the nth node from the end of the list.

**Optimal Solution**:
```java
public static ListNode removeNthFromEndOfLL(ListNode head, int n) {
    if(head == null) return head;
    
    ListNode slow = head;
    ListNode fast = head;
    
    for(int i=0; i<n; i++) {
        fast = fast.next;
    }
    
    if(fast == null) return head.next;
    
    while(fast.next != null) {
        slow = slow.next;
        fast = fast.next;
    }
    slow.next = slow.next.next;
    return head;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Move fast pointer n steps ahead, then move both pointers.

### Cycle Detection Problems

#### 8. Detect Cycle in LinkedList
**Problem**: Detect if there's a cycle in the linked list.

**Optimal Solution (Two Pointer)**:
```java
public static boolean hasCycle(Node head) {
    if(head == null || head.next == null) return false;
    
    Node slow = head;
    Node fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) return true;
    }
    return false;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: If pointers meet, cycle exists.

#### 9. Starting Point of Loop
**Problem**: Find the starting point of the cycle.

**Optimal Solution**:
```java
public static Node detectCycle(Node head) {
    Node slow = head;
    Node fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {
            return findStartingPoint(slow, head);
        }
    }
    return null;
}

public static Node findStartingPoint(Node slow, Node head) {
    Node temp = head;
    while(slow != temp) {
        slow = slow.next;
        temp = temp.next;
    }
    return slow;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: After finding cycle, reset one pointer to head and move both at same speed.

#### 10. Length of Loop
**Problem**: Find the length of the cycle in linked list.

**Optimal Solution**:
```java
public static int countNodesinLoop(Node head) {
    if(head == null || head.next == null) return 0;
    
    Node slow = head;
    Node fast = head;
    while(fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if(slow == fast) {
            return findLength(slow, fast);
        }
    }
    return 0;
}

public static int findLength(Node slow, Node fast) {
    int count = 1;
    fast = fast.next;
    while(fast != slow) {
        fast = fast.next;
        count++;
    }
    return count;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: After finding cycle, count nodes from meeting point.

### Addition Problems

#### 11. Add Two Numbers
**Problem**: Add two numbers represented by linked lists (digits in reverse order).

**Optimal Solution**:
```java
public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    
    ListNode dummyNode = new ListNode(-1);
    ListNode curr = dummyNode;
    ListNode head1 = l1;
    ListNode head2 = l2;
    int carry = 0;
    
    while(head1 != null || head2 != null) {
        int sum = carry;
        if(head1 != null) sum += head1.val;
        if(head2 != null) sum += head2.val;
        
        carry = sum/10;
        int rem = sum%10;
        
        curr.next = new ListNode(rem);
        curr = curr.next;
        
        if(head1 != null) head1 = head1.next;
        if(head2 != null) head2 = head2.next;
    }
    
    if(carry != 0) {
        curr.next = new ListNode(carry);
    }
    return dummyNode.next;
}
```
**Time Complexity**: O(max(N,M)) | **Space Complexity**: O(max(N,M))
**Quick Note**: Simulate digit-by-digit addition with carry.

#### 12. Add 1 to Number
**Problem**: Add 1 to a number represented by linked list.

**Optimal Solution**:
```java
public static Node addOne(Node head) {
    if(head == null) return new Node(1);
    
    int length = findLength(head);
    int zeroes = length-1;
    
    Node head1 = reverse(head);
    Node head2 = new Node(1);
    head2 = addZeroes(head2, zeroes);
    
    Node dummyNode = new Node(-1);
    Node curr = dummyNode;
    int carry = 0;
    
    while(head1 != null || head2 != null) {
        int sum = carry;
        if(head1 != null) sum += head1.data;
        if(head2 != null) sum += head2.data;
        
        int rem = sum%10;
        carry = sum/10;
        
        curr.next = new Node(rem);
        curr = curr.next;
        
        if(head1 != null) head1 = head1.next;
        if(head2 != null) head2 = head2.next;
    }
    
    if(carry != 0) {
        curr.next = new Node(carry);
    }
    
    dummyNode = reverse(dummyNode.next);
    return dummyNode;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(N)
**Quick Note**: Reverse, add with carry, reverse back.

### Advanced Problems

#### 13. Intersection of Two Linked Lists
**Problem**: Find the intersection point of two linked lists.

**Optimal Solution**:
```java
public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int l1 = findLength(headA);
    int l2 = findLength(headB);
    
    if(l1 <= l2) {
        int diff = l2-l1;
        ListNode list2 = headB;
        while(diff != 0) {
            list2 = list2.next;
            diff--;
        }
        
        ListNode list1 = headA;
        while(list1 != list2) {
            list1 = list1.next;
            list2 = list2.next;
        }
        return list1;
    } else {
        int diff = l1-l2;
        ListNode list1 = headA;
        while(diff != 0) {
            list1 = list1.next;
            diff--;
        }
        
        ListNode list2 = headB;
        while(list1 != list2) {
            list1 = list1.next;
            list2 = list2.next;
        }
        return list2;
    }
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Find lengths, move longer list by difference, then move both together.

#### 14. Odd Even LinkedList
**Problem**: Group odd-indexed nodes followed by even-indexed nodes.

**Optimal Solution**:
```java
public static ListNode oddEvenList(ListNode head) {
    if(head == null || head.next == null) return head;
    
    ListNode even = null;
    ListNode tail1 = null;
    ListNode odd = null;
    ListNode tail2 = null;
    
    ListNode curr = head;
    int i = 1;
    while(curr != null) {
        int val = curr.val;
        if(i % 2 == 0) {
            if(even == null) {
                even = new ListNode(val);
                tail1 = even;
            } else {
                tail1.next = new ListNode(val);
                tail1 = tail1.next;
            }
        } else {
            if(odd == null) {
                odd = new ListNode(val);
                tail2 = odd;
            } else {
                tail2.next = new ListNode(val);
                tail2 = tail2.next;
            }
        }
        curr = curr.next;
        i++;
    }
    
    tail2.next = even;
    return odd;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(N)
**Quick Note**: Create separate lists for odd/even, then merge.

#### 15. Palindrome LinkedList
**Problem**: Check if linked list is palindrome.

**Optimal Solution**:
```java
public static boolean isPalindromeOptimal(Node head) {
    Node slow = head;
    Node fast = head;
    while(fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    
    Node firstList = head;
    Node secondList = reverse(slow.next);
    
    while(secondList != null) {
        if(secondList.val != firstList.val) return false;
        firstList = firstList.next;
        secondList = secondList.next;
    }
    return true;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Find middle, reverse second half, compare.

#### 16. Sort LinkedList
**Problem**: Sort a linked list.

**Optimal Solution (Merge Sort)**:
```java
public static ListNode sortLL(ListNode head) {
    if(head == null || head.next == null) return head;
    
    ListNode middleNode = findMiddle(head);
    ListNode secondHalf = middleNode.next;
    middleNode.next = null;
    ListNode firstHalf = head;
    
    firstHalf = sortLL(firstHalf);
    secondHalf = sortLL(secondHalf);
    
    return mergeLinkedLists(firstHalf, secondHalf);
}

public static ListNode mergeLinkedLists(ListNode head1, ListNode head2) {
    ListNode dummyNode = new ListNode(-1);
    ListNode head = dummyNode;
    
    while(head1 != null && head2 != null) {
        if(head1.data <= head2.data) {
            head.next = head1;
            head1 = head1.next;
        } else {
            head.next = head2;
            head2 = head2.next;
        }
        head = head.next;
    }
    
    if(head1 != null) head.next = head1;
    else head.next = head2;
    
    return dummyNode.next;
}
```
**Time Complexity**: O(N log N) | **Space Complexity**: O(1)
**Quick Note**: Divide and conquer with merge sort.

#### 17. Sort 0s, 1s, 2s LinkedList
**Problem**: Sort linked list containing only 0s, 1s, and 2s.

**Optimal Solution**:
```java
public static Node segregate(Node head) {
    HashMap<Integer, Integer> map = new HashMap<>();
    Node curr = head;
    while(curr != null) {
        map.put(curr.data, map.getOrDefault(curr.data, 0)+1);
        curr = curr.next;
    }
    
    Node dummyNode = new Node(-1);
    curr = dummyNode;
    
    for(int i=0; i<=2; i++) {
        if(map.containsKey(i)) {
            int freq = map.get(i);
            while(freq != 0) {
                curr.next = new Node(i);
                curr = curr.next;
                freq--;
            }
        }
    }
    return dummyNode.next;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Count frequencies, recreate list in order.

---

## Hard Problems

#### 18. Rotate List
**Problem**: Rotate linked list to the right by k places.

**Optimal Solution**:
```java
public static ListNode rotateRight(ListNode head, int k) {
    if(head == null || k == 0) return head;
    
    int length = 1;
    ListNode tail = head;
    while(tail.next != null) {
        tail = tail.next;
        length++;
    }
    
    if(k % length == 0) return head;
    k = k % length;
    
    tail.next = head;
    ListNode lastNode = findKthNode(head, length-k);
    head = lastNode.next;
    lastNode.next = null;
    return head;
}
```
**Time Complexity**: O(N) | **Space Complexity**: O(1)
**Quick Note**: Make circular, find kth node from end, break cycle.

#### 19. Reverse Nodes in K-Group
**Problem**: Reverse nodes in groups of k.

**Optimal Solution**:
```java
public static ListNode reverseKGroup(ListNode head, int k) {
    if(head == null || k == 0) return head;
    
    ListNode temp = head;
    ListNode prevNode = null;
    ListNode nextNode = null;
    
    while(temp != null) {
        ListNode kthNode = findKthNode(temp, k);
        if(kthNode == null) {
            prevNode.next = temp;
            break;
        }
        
        nextNode = kthNode.next;
        kthNode.next = null;
        reverse(temp);
        
        if(temp == head) head = kthNode;
        else prevNode.next = kthNode;
        
        prevNode = temp;
        temp = nextNode;
    }
    return head;
}
```
**Time Complexity**: O(2N) | **Space Complexity**: O(1)
**Quick Note**: Find kth node, reverse group, maintain links between groups.

#### 20. Flattening LinkedList
**Problem**: Flatten a multi-level linked list.

**Optimal Solution**:
```java
public static Node flattenLists(Node root) {
    if(root == null || root.next == null) return root;
    
    Node currentHead = flatten(root.next);
    return mergeTwoLists(root, currentHead);
}

public static Node mergeTwoLists(Node list1, Node list2) {
    Node dummyNode = new Node(-1);
    Node curr = dummyNode;
    
    while(list1 != null && list2 != null) {
        if(list1.data <= list2.data) {
            curr.bottom = list1;
            curr = list1;
            list1 = list1.bottom;
        } else {
            curr.bottom = list2;
            curr = list2;
            list2 = list2.bottom;
        }
        curr.next = null;
    }
    
    if(list1 != null) curr.bottom = list1;
    else curr.bottom = list2;
    
    return dummyNode.bottom;
}
```
**Time Complexity**: O(N*M) | **Space Complexity**: O(1)
**Quick Note**: Recursively flatten and merge sorted lists.

---

## Key Patterns & Techniques

### 1. Two Pointer Technique
- **Fast & Slow**: For finding middle, detecting cycles
- **Distance-based**: For nth node from end, intersection
- **Speed difference**: Fast moves 2x, slow moves 1x

### 2. Reversal Techniques
- **3-pointer**: prev, curr, next for iterative reversal
- **Recursive**: Base case + recursive call + pointer updates

### 3. Cycle Detection
- **Floyd's Algorithm**: Two pointers with different speeds
- **Starting Point**: Reset one pointer to head after finding cycle

### 4. Merge Operations
- **Dummy Node**: Always use dummy node for clean merging
- **Two Lists**: Compare and link smaller elements

### 5. Group Operations
- **K-Group**: Find kth node, reverse group, maintain links
- **Segregation**: Count frequencies, recreate in order

### 6. Addition Problems
- **Carry Management**: Track carry, handle overflow
- **Reverse Order**: Start from least significant digit

---

## Quick Reference

| Problem Type | Time Complexity | Space Complexity | Key Technique |
|-------------|----------------|------------------|---------------|
| Basic Operations | O(N) | O(1) | Simple traversal |
| Reversal | O(N) | O(1) | 3-pointer technique |
| Middle Node | O(N) | O(1) | Fast & Slow pointers |
| Cycle Detection | O(N) | O(1) | Floyd's Algorithm |
| Addition | O(max(N,M)) | O(max(N,M)) | Carry simulation |
| Sorting | O(N log N) | O(1) | Merge Sort |
| Group Operations | O(N) | O(1) | K-group segmentation |

---

## Common Mistakes to Avoid

1. **Null Pointer Checks**: Always check for null before accessing next
2. **Cycle Detection**: Don't forget to check fast.next != null
3. **Reversal**: Remember to update all three pointers
4. **Addition**: Don't forget to handle final carry
5. **Group Operations**: Maintain proper links between groups
6. **Memory Management**: Use dummy nodes for clean operations

---

*This guide covers all LinkedList problems from Easy to Hard difficulty levels, organized by problem type for easy revision.* 