package Player;

import Entity.EntityMaster;

public class PlayerMaster extends EntityMaster {

    private int characterCurrentExp;

    public int getCharacterCurrentExp() {
        return characterCurrentExp;
    }

    @Override
    public String toString() {
        return getEntityName();
    }
}
