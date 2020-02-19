package game;

final class Wizard
    extends
        Entity
{

    private int state;

    public Wizard(
        final int id,
        final String type,
        final Position position,
        final int state )
    {
        super( id, type, position );
        this.state = state;
    }

    /**
     * If state is 1 the wizard is holding a snaffle
     */
    public boolean isHoldingSnaffle()
    {
        return state == 1;
    }

    public void setState(
        final int state )
    {
        this.state = state;
    }
}
