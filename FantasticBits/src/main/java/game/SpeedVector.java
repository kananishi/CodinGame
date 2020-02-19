package game;

final class SpeedVector
{
    private final int velocityInX;
    private final int velocityInY;

    public SpeedVector(
        final int vx,
        final int vy )
    {
        velocityInX = vx;
        velocityInY = vy;
    }

    public int getVelocityInX()
    {
        return velocityInX;
    }

    public int getVelocityInY()
    {
        return velocityInY;
    }

}
