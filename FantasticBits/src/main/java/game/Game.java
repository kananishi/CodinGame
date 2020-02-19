package game;

import static game.InputHolder.SCANNER;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Game
{
    private final Team myTeam;
    private final Team opponentTeam;

    private final List<Entity> entities = new ArrayList<>();

    public Game(
        Team myTeam,
        Team opponentTeam )
    {
        this.myTeam = myTeam;
        this.opponentTeam = opponentTeam;
    }

    public void run()
    {
        getUpdates();
        // AA();
        System.out.println( "MOVE 8000 3750 100" );
        System.out.println( "MOVE 8000 3750 100" );
    }

    private void AA()
    {
        for( final Wizard wiz : myTeam.getWizards() ) {
            System.out.println( "MOVE 8000 3750 100" );
        }
    }

    private void getUpdates()
    {
        final int myScore = SCANNER.nextInt();
        log( "Entrou aqui" );
        myTeam.setScore( myScore );
        myTeam.setMagic( SCANNER.nextInt() );
        opponentTeam.setScore( SCANNER.nextInt() );
        opponentTeam.setMagic( SCANNER.nextInt() );

        // number of entities in game
        final int entitiesInGame = SCANNER.nextInt();
        for( int i = 0; i < entitiesInGame; i++ ) {
            final int entityId = SCANNER.nextInt();
            final String entityType = SCANNER.next();
            final Position pos = Position.create( SCANNER.nextInt(), SCANNER.nextInt() );
            final SpeedVector vel = new SpeedVector( SCANNER.nextInt(), SCANNER.nextInt() );
            final int state = SCANNER.nextInt();

            final Optional<Entity> actualEntity = entities.stream()
                .filter( e -> e.getId() == entityId )
                .findFirst();

            if( ! actualEntity.isPresent() ) {
                if( entityType.equals( EntityType.WIZARD.toString() ) || entityType.equals( EntityType.OPPONENT_WIZARD.toString() ) ) {
                    final Entity entity = new Wizard( entityId, entityType, pos, state );
                    entities.add( entity );
                }
                if( entityType.equals( EntityType.SNAFFLE.toString() ) ) {
                    final Entity entity = new Entity( entityId, entityType, pos );
                    entities.add( entity );
                }
                continue;
            }

            final Entity entity = actualEntity.get();
            entity.setPosition( pos );
            entity.setVelocity( vel );
        }
    }

    static void log(
        Object msg )
    {
        System.err.println( msg.toString() );
    }
}
