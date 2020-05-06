Baseball Simulator (2020)
---
#### About

This program will simulate baseball games in a single series. Each game will be observable inning-by-inning and stats will be kept on each player's performance. Varying simulations can be run as custom teams can be created, potentially changing the way the teams interact. The game will allow the user to run 3 different games between two teams, displaying the overall results. Afterwards or during these games, the user can pull up statistics of each of the players involved, either their historic (2019) statistics or their series performance statistics.

##### Data

Each player will have prior performance stats (historic BA, ERA, etc) and the games will simulate using those stats as a baseline for event probabilities. The lineups will be stored and loaded. The ensuing player stats that come from each game will be recorded. All the players will be pulled from CSV files and put into an entire player library.

##### Data Structures

For the lineups, both an array-based and linked list-based queue are used so that we cycle through each player as the innings progress. All players are stored in both a hash table and a linked list so that they can be loaded into a game and also so that their stats can be viewed when users wish. For player storage, the data structures used will determine the speed of player retrieval and lookup, as well as storage itself. The linked list will allow for O(1) insertion of players, but O(n) search. With an ideal hash table, the search, insertion, and deletion of players will be close to O(1) time complexity.

##### Instructions

Before running the program, ensure the pitcherFile and batterFile locations fit the local path of the files. This is found in the Main class (lines 5 and 6).

To run the program, simply run the Main class. Then, you will be given options. Enter a letter to perform its action:

s: Simulate game

p: Look up a player's historic stats

l: Look up player stats from the current series

1: Change first team

2: Change second team

e: Exit

Simulating games will give you a play-by-play rundown of the game. Afterwards, the user is able to look up any player's stats from the current series with the letter "l". Type in a player name after this prompt to retrieve their stats.
