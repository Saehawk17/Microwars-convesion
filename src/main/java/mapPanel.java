import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class mapPanel extends JPanel implements Runnable {

    private Image dbImage = null;

    displayBackground backDisplay;

    private Graphics2D dbg2D;

    private Thread animator;

    CSVReader corpReader;

    ArmyFactory factory;

    Army redArmy;

    Army blueArmy;

    MainDisplay mainDisplay;

    JPanel mapDisplayPanel;

    JPanel infoPanel;

    JTextArea blueArmyDisplay;

    JTextArea redArmyDisplay;

    Set<ArmyCorp> redCorp;

    Set<ArmyCorp> blueCorp;

    Set<ArmyCorp> battleCorps;

    Boolean gameStarted = false;

    Battle closeBattle;

    Set<Battle> skirmishes = new HashSet();

    private Timer timer;

    private boolean redOrders = false;

    private boolean blueOrders = false;

    public mapPanel(MainDisplay MD) {
        mainDisplay = MD;
        setPreferredSize(new Dimension(1000, 625));
        backDisplay = new displayBackground(1000, 625);
        factory = new ArmyFactory();
        redArmy = factory.getArmy("red");
        blueArmy = factory.getArmy("blue");
        mapDisplayPanel = MD.getMainDisplayPanel();
        redArmyDisplay = MD.getRedArmyPanel();
        blueArmyDisplay = MD.getBlueArmyDisplayPanel();
        mapDisplayPanel.add(this);
        mapDisplayPanel.setFocusable(true);
        mapDisplayPanel.requestFocus();
        infoPanel = MD.getInfoPanel();
        redCorp = new HashSet(redArmy.listOfCorp());
        blueCorp = new HashSet(blueArmy.listOfCorp());
        battleCorps = new HashSet<ArmyCorp>();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        startGame();
    }

    public void startGame() {
        if (animator == null) {
            animator = new Thread(this);
            animator.start();
        }
    }

    @Override
    public void run() {
        gameRender();
        paintScreen();
        while (gameStarted) {
            gameUpdate();
            gameRender();
            paintScreen();
            JOptionPane.showMessageDialog(infoPanel, "End of orders input");
            JOptionPane.showMessageDialog(infoPanel, "Now Checking For Combat");
            checkForCombat();
        }
        System.exit(0);
    }

    public void gameRender() {
        if (dbImage == null) {
            dbImage = createImage(1000, 625);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            }
        }
        dbg2D = (Graphics2D) dbImage.getGraphics();
        dbg2D.setColor(Color.yellow);
        dbg2D.fillRect(0, 0, 1000, 625);
        backDisplay.draw(dbg2D);
        redCorp.forEach( ac->{
    ac.draw(dbg2D);
});
        blueCorp.forEach( ac->{
    ac.draw(dbg2D);
});
    }

    private void paintScreen() {
        Graphics g;
        try {
            g = mapDisplayPanel.getGraphics();
            if ((g != null) && (dbImage != null)) {
                dbg2D.setColor(Color.black);
            }
            g.drawImage(dbImage, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics content error: " + e);
        }
    }

    public void gameUpdate() {
        redArmyDisplay.setText(redArmy.displayArmyTextBox());
        blueArmyDisplay.setText(blueArmy.displayArmyTextBox());
        redOrders = false;
        while (!redOrders) {
            redCorp.forEach( ac->{
    ac.inputOrders();
});
            mapDisplayPanel.requestFocus(true);
            redCorp.forEach( ac->{
    ac.move(ac.getOrders());
});
            redOrders = true;
        }
    }

    public void checkForCombat() {
        skirmishes = new HashSet();
        Iterator<ArmyCorp> fi = redCorp.iterator();
        while (fi.hasNext()) {
            ArmyCorp rc = fi.next();
            int FrenchSquare = rc.getPosition();
            System.out.print("\n" + " Checking " + rc.getLeader() + " against ");
            Iterator<ArmyCorp> it = blueCorp.iterator();
            while (it.hasNext()) {
                ArmyCorp bc = it.next();
                int AlliedSquare = bc.getPosition();
                int close = FrenchSquare - AlliedSquare;
                System.out.print(" " + bc.getLeader());
                switch(close) {
                    case 40:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case 41:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case 39:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case 1:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case -1:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case -40:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case -39:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                    case -41:
                        battleCorps.add(bc);
                        battleCorps.add(rc);
                        break;
                }
            }
            if (!battleCorps.isEmpty()) {
                closeBattle = new Battle(battleCorps);
                skirmishes.add(closeBattle);
                battleCorps.clear();
            }
        }
        System.out.print("\n The size of Skirmishes is " + skirmishes.size() + "\n");
        for (Battle b : skirmishes) {
            System.out.print(b.printCorpsInBattle() + "\n");
        }
    }

    public double artillaryLosses(ArmyCorp ac) {
        Random rand = new Random();
        double mod = 0.01;
        int dice = rand.nextInt(6);
        int guns = ac.getArtllary() / 20;
        int maxCas = guns * 10;
        if (dice == 3 | dice == 4) {
            mod = 0.02;
        } else if (dice == 5 | dice == 6) {
            mod = 0.03;
        }
        double casPercent = (maxCas * mod * ac.getMorale() / maxCas * 100);
        return casPercent;
    }

    public void applyLoss(ArmyCorp a, Double l) {
        a.setInfantry((int) (a.getInfantry() - (a.getInfantry() * l / 100)));
        a.setCavelry((int) (a.getCavelry() - (a.getCavelry() * l / 100)));
        a.setArtillary((int) (a.getArtllary() - (a.getArtllary() * l / 100)));
    }

    public void artillaryPhase(ArmyCorp a, ArmyCorp b) {
        JOptionPane.showMessageDialog(infoPanel, "Artillary Phase" + "\n" + a.getLeader() + " is fighting " + b.getLeader());
        double rHitPercent = artillaryLosses(a);
        double bHitPercent = artillaryLosses(b);
        System.out.print("\n" + "French Hits " + rHitPercent + " Allied Hits " + bHitPercent);
        applyLoss(a, bHitPercent);
        applyLoss(b, rHitPercent);
    }

    
}
