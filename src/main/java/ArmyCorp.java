import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ArmyCorp {

    private int artillary;

    private int cavelry;

    private int infantry;

    private int hitPoints;

    private int pos;

    private String leader;

    private String side;

    private int morale;

    private String orders;

    private final int orderLength = 2;

    public ArmyCorp(int a, int c, int i, String l, int m, int p, String s) {
        this.artillary = a;
        this.cavelry = c;
        this.infantry = i;
        this.leader = l;
        this.morale = m;
        this.pos = p;
        this.side = s;
        this.hitPoints = calculateHitPoints();
    }

    public void setArtillary(int a) {
        this.artillary = a;
    }

    public int getArtllary() {
        return this.artillary;
    }

    public void setCavelry(int a) {
        this.cavelry = a;
    }

    public int getCavelry() {
        return this.cavelry;
    }

    public void setInfantry(int a) {
        this.infantry = a;
    }

    public int getInfantry() {
        return this.infantry;
    }

    public void setMorale(int a) {
        this.morale = a;
    }

    public int getMorale() {
        return this.morale;
    }

    public void setLeader(String s) {
        this.leader = s;
    }

    public String getLeader() {
        return this.leader;
    }

    public void setSide(String s) {
        this.side = s;
    }

    public String getSide() {
        return this.side;
    }

    public void setPosition(int p) {
        if (p >= 0 & p < 1000) {
            this.pos = p;
        }
    }

    public int getPosition() {
        return this.pos;
    }

    public int calcY() {
        int row = this.pos / 40;
        return row * 25;
    }

    public int calcX() {
        int row = this.pos / 40;
        int colum = pos - (row * 40);
        return colum * 25;
    }

    public int calculateHitPoints() {
        return (this.getInfantry() / 500 + this.artillary / 100 + this.cavelry / 250);
    }

    public void draw(Graphics g) {
        if (this.side.equalsIgnoreCase("allies")) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.yellow);
        }
        g.drawOval(this.calcX(), this.calcY(), 25, 25);
        g.fillOval(this.calcX(), this.calcY(), 25, 25);
        if (this.side.equalsIgnoreCase("allies")) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.red);
        }
        g.drawString(this.leader, this.calcX(), this.calcY());
        System.out.print(" " + this.leader + " right side is " + this.checkRightSide(this) + " " + this.getPosition() + "\n");
    }

    @Override
    public String toString() {
        return "Artillary " + this.getArtllary() + " Cavelry " + this.getCavelry() + " Infantry " + this.getInfantry() + " Leader " + this.getLeader();
    }

    public boolean checkRightSide(ArmyCorp ac) {
        int remainder = (ac.getPosition() + 1) % 40;
        return remainder == 0;
    }

    public boolean checkLeftSide(ArmyCorp ac) {
        int remainder = ac.getPosition() % 40;
        return remainder == 0;
    }

    public boolean checkTopRow(ArmyCorp ac) {
        boolean returnValue = false;
        if (ac.getPosition() >= 0 & ac.getPosition() < 40) {
            returnValue = true;
        }
        return returnValue;
    }

    public boolean checkBottomRow(ArmyCorp ac) {
        boolean returnValue = false;
        if (ac.getPosition() > 959 & ac.getPosition() < 1000) {
            returnValue = true;
        }
        return returnValue;
    }

    public void move(String o) {
        char dir = o.charAt(0);
        char mov = o.charAt(1);
        int offset = 0;
        int dist;
        dist = Character.getNumericValue(mov);
        int curr = this.getPosition();
        switch(dir) {
            case 'n':
                if (!checkTopRow(this)) {
                    int CheckWithin = this.getPosition() - 40;
                    if (CheckWithin < 40) {
                        offset = -40;
                    } else {
                        offset = -40 * dist;
                    }
                }
                break;
            case 's':
                if (!checkBottomRow(this)) {
                    int CheckWithin = this.getPosition() + 40;
                    if (CheckWithin > 959 & CheckWithin < 1000) {
                        offset = 40;
                    } else {
                        offset = 40 * dist;
                    }
                }
                break;
            case 'e':
                if (!checkRightSide(this)) {
                    int checkWithin = this.getPosition() + 1;
                    if (checkWithin % 39 == 0) {
                        offset = 1;
                    } else {
                        offset = 1 * dist;
                        break;
                    }
                }
                break;
            case 'w':
                if (!checkLeftSide(this)) {
                    int checkWithin = this.getPosition() - 1;
                    if (checkWithin % 40 == 0) {
                        offset = -1;
                    } else {
                        offset = -1 * dist;
                        break;
                    }
                }
            default:
                break;
        }
        this.setPosition(curr + offset);
    }

    public String getOrders() {
        return this.orders;
    }

    public void inputOrders() {
        String name = this.getLeader();
        String newOrders;
        Boolean acceptable = false;
        while (!acceptable) {
            newOrders = JOptionPane.showInputDialog("Enter orders" + " for " + name);
            if (newOrders.length() == orderLength) {
                char dirChar = newOrders.charAt(0);
                char distChar = newOrders.charAt(1);
                if (dirChar == 'n' || dirChar == 's' || dirChar == 'w' || dirChar == 'e' || dirChar == '0') {
                    if (distChar == '1' || distChar == '2' || distChar == '0') {
                        acceptable = true;
                        orders = newOrders;
                        newOrders = "";
                    }
                }
            }
        }
    }
}
