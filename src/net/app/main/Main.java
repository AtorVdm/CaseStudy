package net.app.main;

import net.ponder2.SelfManagedCell;

/**
 * Created by Ator on 18/08/16.
 */
public class Main {
    public static void main(String[] args) {
        String params[] = {"-port", "13570", "-boot", "p2src/bfInit.p2,p2src/xssInit.p2,p2src/bfPolicy.p2,p2src/xssPolicy.p2"};
        SelfManagedCell.start(params);
    }
}
