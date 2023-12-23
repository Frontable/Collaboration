The task involves finding the longest working pairs of employees per projects.
Algorithm Overview

CSV Parsing:
  Read the CSV file and parse employee data, considering multiple date formats and handling NULL end dates.

Date Parsing:
  Implement a flexible date parsing mechanism using SimpleDateFormat and DateTimeFormatter to accommodate different date formats.

Pair Identification:
  Iterate through the list of employees and identify pairs who have worked on the same project.
  Calculate the duration of their collaboration, considering start and end dates.

Longest Working Pairs:
  Keep track of the longest working pairs for each project using a HashMap.
   If a new pair exceeds the duration of an existing pair, update the record in the HashMap.


Pair algorit:
    Initialization:
        A HashMap  longestPairsByProject is created to store the longest working pairs for each project. 
The key is the project ID, and the value is a Pair object representing the longest working pair on that project.    
        Two nested loops iterate over all unique pairs of employees. 
    Project Matching:
        Checks if the two employees worked on the same project.
    Collaboration Period:
        Determines the overlapping period of their collaboration by finding the maximum start date and minimum end date. 
    Days Worked Calculation:
        Calculates the number of days they worked together by using ChronoUnit.DAYS.between. Adding 1 ensures that if they worked on the 
        same day, the duration is at least 1 day.
    Pair Creation:
        If they worked together for at least one day, a Pair object is created to represent the working pair.
