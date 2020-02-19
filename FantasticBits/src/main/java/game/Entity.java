package game;

class Entity
{
    private final int id;
    private final EntityType type;
    private Position position;
    private SpeedVector velocity;

    public Entity(
        final int id,
        final String type,
        final Position position )
    {
        this.id = id;
        this.type = EntityType.valueOf( type );
        this.position = position;
    }

    public int getId()
    {
        return id;
    }

    public EntityType getType()
    {
        return type;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(
        final Position newPosition )
    {
        position = newPosition;
    }

    public SpeedVector getVelocity()
    {
        return velocity;
    }

    public void setVelocity(
        final SpeedVector velocity )
    {
        this.velocity = velocity;
    }

}