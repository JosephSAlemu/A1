# Part 1
1.
A) On = 570, Off = 571
Why? The condition holds true if the value is equal to or less than 570. 570 is the highest value where the statement may be true so it’s the on-point. 571 is the lowest integer value before the condition is true, therefore it’s the off-point.

2.
On point: 10. That’s the only value of x that can make the statement true.
Off points: 9 and 11. Those values are the closest to the on-point where the condition is false.

3.
letters.length() < 2: invalid 
letters.length() > 2: invalid
letters.length() == 2: valid
letters == null: invalid

These boundary cases may exercise the program based on my assumptions on what the string should be. The string length should be exactly two. Not over or under in terms of length. Additionally, the string should be ensured that it’s not null before hand. By ensuring these edge cases, we check for all the possible states of a string.


# Part 3
Yes, the program has a bug.

In the comments for the add method, it says that an empty List is 0. However, adding two empty lists outputs with [] (empty list) instead of [0] (a list with zero). We can prove that this is a bug as I have added two lists with just zero, and received [0] as an output. 
