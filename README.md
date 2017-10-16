# WordProbelm

Problem Statement
Write a program that: 
Reads a file containing alphabetically sorted words list (one word per line, no spaces, all lower case) 
Identifies & displays 2 longest words in the file that can be constructed by combining (concatenating) shorter words also found in the file

Example 1

Words.txt: Small word list, consisting following words
cat
cats
catsdogcats 
catxdogcatsrat
dog
dogcatsdog
hippopotamuses
rat
ratcatdogcat

Answer: 
1. Compound Word: ratcatdogcat
2. Compound Word: catsdogcats

By Iteration Method : 

1.Sort the Strings Descending order "Length Wise" and insert in Map.
2.Start with the first String and Loop over Sorted Strings
3.check it can made of other words by dividing into all possible combination.
4.check left and right string contains in a Map.


By Trie Method : 

1.Building the Trie and Map (Insert orginal word as a Key and suffix as a value in a Tree Map).
2.if word and suffix are same don't insert in a Map.
3.Sort the Map according to the length in Descending Order.
4.Now iterate the Map and check each suffix of each word are prefix in trie.
