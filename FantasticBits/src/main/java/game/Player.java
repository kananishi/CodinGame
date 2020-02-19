package game;

import static game.InputHolder.SCANNER;

/**
 * Grab Snaffles and try to throw them through the opponent's goal! Move towards
 * a Snaffle and use your team id to determine where you need to throw it.
 **/
class Player
{

    public static void main(
        final String args[] )
    {

        // if 0 you need to score on the
        // right of the map, if 1 you need to
        // score on the left
        final int myTeamId = SCANNER.nextInt();
        final Team myTeam = new Team( myTeamId );

        final int opponentTeamId = Math.abs( myTeamId - 1 );
        final Team opponentTeam = new Team( opponentTeamId );

        final Game game = new Game( myTeam, opponentTeam );
        // game loop
        while( true ) {
            game.run();
        }

    }
}