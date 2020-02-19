package game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class Team
{
    private final int id;
    private int score;
    private int magic;

    private final List<Wizard> wizards = new ArrayList<>();

    public Team(
        int id )
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(
        int score )
    {
        this.score = score;
    }

    public int getMagic()
    {
        return magic;
    }

    public void setMagic(
        int magic )
    {
        this.magic = magic;
    }

    public Collection<Wizard> getWizards()
    {
        return Collections.unmodifiableList( wizards );
    }

}