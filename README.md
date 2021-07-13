**MEET THE FAMILY**

The "Game of Dice" is a multiplayer game where N players roll a 6 faced dice in a round-robin fashion. Each time a player rolls the dice their points increase by the number (1 to 6) achieved by the roll.

As soon as a player accumulates M points they complete the game and are assigned a rank. Remaining players continue to play the game till they accumulate at least M points. The game ends when all players have accumulated at least M points.



**Build The Code**

```./gradlew clean build```

Run All Unit Tests

```./gradlew test```

**Steps to run the code**

* Run `./gradlew run --args="2 5"`
where the first argument is the no of player and second argument is the winning points.