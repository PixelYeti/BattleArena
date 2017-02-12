package com.pixelyeti.Arena.GameMechs.Classes;

import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.BaseClass;
import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.ClassType;
import com.pixelyeti.Arena.GameMechs.Classes.BaseClasses.ManaClass;
import com.pixelyeti.Arena.GameMechs.Classes.SubClasses.Iceman;

import java.util.ArrayList;
import java.util.UUID;

public class ClassManager {

    private static ArrayList<BaseClass> players = new ArrayList<>();

    public static void addPlayer(UUID id, ClassType classType) {
        BaseClass b = null;
        switch (classType) {
            case MANA:
                b = new Iceman(id);
                players.add(b);
                break;
            case COMBO:
                break;
            case RAGE:
                break;
        }
        b.initiate();
    }

    public static BaseClass getPlayer(UUID id) {
        for (BaseClass b : players) {
            if (b.getPlayer().getUniqueId() == id)
                return b;
        }
        return null;
    }

}
