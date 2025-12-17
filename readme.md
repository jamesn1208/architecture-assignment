# Software Design & Architecture

This repository contains all code & documentation for my 3rd year unit "Software Design & Architecture" assignment.

## Authors
- **James Nash** - *Student @ MMU* - [GitHub Profile](https://github.com/jamesn1208)


## Detail
### Variations

The `WinConditions` package contains pre-made win conditions for a game, including:
- `StandardWinCondition`: A standard win condition where a player doesn't have to land exactly on the winning square to win.
- `VariationWinCondition`: A win condition where a player must land exactly on the winning square to win.

The `HitConditions` package contains pre-made hit conditions for a game, including:
- `StandardHitCondition`: A standard hit condition where players can share one tile without punishment.
- `VariationHitCondition`: A hit condition where if a player lands on a tile occupied by another player, the player's turn is forfeit.

The `Board` package contains pre-made board configurations for a game, including:
- `StandardBoard`: A board with 18 tiles, and 3 tiles in each tail.
- `LargeBoard`: A board with 36 tiles, and 6 tiles in each tail.

**Each of these are cross-operable, and can be run in any combination.**


### Logic
#### Player
Each player object stores their own fields;
- `Name`
- `Current Position`
- `Path to Victory`
- `Position in Path to Victory`

This allows for me to very simply 'move' the player by increasing the index of their current position in the `Path to Victory` Array. This continues until a win condition is met.

#### Game
The game class is responsible for orchestration & state management. It combines all helper objects & runs the game in a loop until a win condition is met. The game class also handles player turns, and checks for hit conditions before each move is made in a turn.
