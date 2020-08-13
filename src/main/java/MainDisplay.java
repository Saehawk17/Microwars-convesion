import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MainDisplay extends javax.swing.JFrame {

    mapPanel map;

    notRunningPanel endMap;

    boolean gameRunning = false;

    boolean notEnded = true;

    public MainDisplay() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ArmyDisplayPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        redArmyDisplayPanel = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        blueArmyDisplayPanel = new javax.swing.JTextArea();
        InfoPanel = new javax.swing.JPanel();
        MapDisplayPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1400, 800));
        setName("Three Days In June");
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 5));
        TitlePanel.setBackground(new java.awt.Color(102, 102, 255));
        TitlePanel.setMaximumSize(new java.awt.Dimension(1400, 75));
        TitlePanel.setMinimumSize(new java.awt.Dimension(1400, 75));
        TitlePanel.setPreferredSize(new java.awt.Dimension(1400, 75));
        TitlePanel.setVerifyInputWhenFocusTarget(false);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48));
        jLabel1.setText("THREE DAYS IN JUNE");
        jLabel1.setPreferredSize(new java.awt.Dimension(103, 50));
        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitlePanelLayout.createSequentialGroup().addContainerGap(494, Short.MAX_VALUE).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(319, 319, 319)));
        TitlePanelLayout.setVerticalGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(TitlePanelLayout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE).addContainerGap()));
        getContentPane().add(TitlePanel);
        ArmyDisplayPanel.setBackground(new java.awt.Color(255, 102, 102));
        ArmyDisplayPanel.setMaximumSize(new java.awt.Dimension(350, 625));
        ArmyDisplayPanel.setMinimumSize(new java.awt.Dimension(350, 625));
        ArmyDisplayPanel.setPreferredSize(new java.awt.Dimension(350, 625));
        ArmyDisplayPanel.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setMaximumSize(new java.awt.Dimension(200, 200));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 200));
        redArmyDisplayPanel.setColumns(20);
        redArmyDisplayPanel.setRows(5);
        jScrollPane1.setViewportView(redArmyDisplayPanel);
        ArmyDisplayPanel.add(jScrollPane1, java.awt.BorderLayout.NORTH);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(200, 200));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 200));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 200));
        blueArmyDisplayPanel.setColumns(20);
        blueArmyDisplayPanel.setRows(5);
        jScrollPane2.setViewportView(blueArmyDisplayPanel);
        ArmyDisplayPanel.add(jScrollPane2, java.awt.BorderLayout.SOUTH);
        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 350, Short.MAX_VALUE));
        InfoPanelLayout.setVerticalGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 225, Short.MAX_VALUE));
        ArmyDisplayPanel.add(InfoPanel, java.awt.BorderLayout.CENTER);
        getContentPane().add(ArmyDisplayPanel);
        MapDisplayPanel.setBackground(new java.awt.Color(51, 255, 51));
        MapDisplayPanel.setMaximumSize(new java.awt.Dimension(1000, 625));
        MapDisplayPanel.setMinimumSize(new java.awt.Dimension(1000, 625));
        MapDisplayPanel.setPreferredSize(new java.awt.Dimension(1000, 625));
        MapDisplayPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(MapDisplayPanel);
        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);
        jMenu2.setText("Game");
        jMenuItem1.setText("Start Game");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenuItem2.setText("End Game");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenuBar1.add(jMenu2);
        setJMenuBar(jMenuBar1);
        pack();
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        gameRunning = true;
        runGame();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
       
        gameRunning = false;
        endGame();
    }

    public void runGame() {
        map = new mapPanel(this);
        map.gameStarted = true;
    }

    public void endGame() {
        MapDisplayPanel.remove(map);
        endMap = new notRunningPanel();
        MapDisplayPanel.add(endMap, SwingConstants.CENTER);
        this.pack();
        redArmyDisplayPanel.setText("Game No Running");
        blueArmyDisplayPanel.setText("Game Not Running");
        map.gameStarted = false;
    }

    public JPanel getMainDisplayPanel() {
        return this.MapDisplayPanel;
    }

    public JTextArea getRedArmyPanel() {
        return this.redArmyDisplayPanel;
    }

    public JTextArea getBlueArmyDisplayPanel() {
        return this.blueArmyDisplayPanel;
    }

    public JPanel getInfoPanel() {
        return this.InfoPanel;
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainDisplay md = new MainDisplay();
                md.setVisible(true);
            }
        });
    }

    private javax.swing.JPanel ArmyDisplayPanel;

    private javax.swing.JPanel InfoPanel;

    private javax.swing.JPanel MapDisplayPanel;

    private javax.swing.JPanel TitlePanel;

    private javax.swing.JTextArea blueArmyDisplayPanel;

    private javax.swing.JLabel jLabel1;

    private javax.swing.JMenu jMenu1;

    private javax.swing.JMenu jMenu2;

    private javax.swing.JMenuBar jMenuBar1;

    private javax.swing.JMenuItem jMenuItem1;

    private javax.swing.JMenuItem jMenuItem2;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JTextArea redArmyDisplayPanel;

     {
    }
}
