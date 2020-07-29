package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;


import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 *
 *
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private static final double moveLoss = 0.03;
    private static final double stayLoss = 0.01;
    private double moveProbability = 0.5;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     return color of clorus.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature,it should gain that creature’s energy.
     */
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    /**
     * Cloruses should lose 0.03 units of energy on a MOVE action.
     */
    public void move() {
        energy = energy - moveLoss;

    }


    /**
     * Cloruses should lose 0.01 units of energy on a STAY action.
     */
    public void stay() {
        energy = energy - stayLoss;

    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        Clorus offspring = new Clorus (energy);
        return offspring;
    }

    /**
     * 1.If there are no empty squares, the Clorus will STAY (even if
     * there are Plips nearby they could attack since plip squares do not count as empty squares).
     * 2.Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3.Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4.Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for(Direction key: neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.addLast(key);
            } else {
                if (neighbors.get(key).name().equals("plip")) {
                    anyPlip = true;
                    plipNeighbors.addLast(key);
                }
            }

        }
        // Rule 1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        if (anyPlip) {
            Direction d = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        }
        // Rule 3
        if (energy >= 1) {
            Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        // Rule 4
        Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE,d);


    }
}



