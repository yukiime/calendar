package team.utils;

import java.awt.Component;

import javax.swing.JPanel;

public class SpaceWrapper extends JPanel {
    private Component[] objList;

    public SpaceWrapper(Component[] objectList) {
        this.objList = objectList;
        for (Component item : objectList) {
            this.add((Component) item);
        }
    }

    public Component getInstance(int index) {
        return this.objList[index];
    }

}
