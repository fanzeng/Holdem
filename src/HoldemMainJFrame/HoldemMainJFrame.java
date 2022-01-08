package HoldemMainJFrame;

import java.util.*;
import holdem.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.nio.file.Path;
import java.nio.file.Paths;


public class HoldemMainJFrame extends javax.swing.JFrame {

    public HoldemMainJFrame() {
        initComponents();
        try {
            URL holdenMainJFrameURL = HoldemMainJFrame.class.getResource("HoldemMainJFrame.class");
            if (holdenMainJFrameURL.getProtocol()=="jar") {
                System.setProperty("user.dir", new File(HoldemMainJFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent());
            }
        } catch (URISyntaxException ex) {
            System.err.println(ex.toString());
        } catch(IllegalArgumentException ex) {
            System.err.println(ex.toString());
            System.out.println("Are you running on a network drive, e.g. your desktop could be one?");
            System.out.println("Does the path contain space?");
            System.out.println("Please try running it in a more regular directory.");
        }
        lblMessage.setText("Current directory: " + System.getProperty("user.dir"));
        arrLblPlayerCard[0] = lblPlayer1Card;
        arrLblPlayerCard[1] = lblPlayer2Card;
        arrPnlPlayer[0] = pnlPlayer1;
        arrPnlPlayer[1] = pnlPlayer2;
        arrLblPlayerStack[0] = lblPlayer1Stack;
        arrLblPlayerStack[1] = lblPlayer2Stack;
        shuffle();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlGameControl = new javax.swing.JPanel();
        btnShuffle = new javax.swing.JButton();
        btnShowCardImage = new javax.swing.JToggleButton();
        lblMessage = new javax.swing.JLabel();
        pnlBoard = new javax.swing.JPanel();
        pnlBoardImg = new javax.swing.JPanel();
        lblBoardImg0 = new javax.swing.JLabel();
        lblBoardImg1 = new javax.swing.JLabel();
        lblBoardImg2 = new javax.swing.JLabel();
        lblBoardImg3 = new javax.swing.JLabel();
        lblBoardImg4 = new javax.swing.JLabel();
        lblBoard = new javax.swing.JLabel();
        jPanelPot = new javax.swing.JPanel();
        lblPot = new javax.swing.JLabel();
        lblPotValue = new javax.swing.JLabel();
        jPanelPlayerConsole = new javax.swing.JPanel();
        btnPlayerBet = new javax.swing.JButton();
        btnPlayerFold = new javax.swing.JButton();
        jSliderPlayer = new javax.swing.JSlider();
        btnPlayerShow = new javax.swing.JButton();
        jPanelPlayers = new javax.swing.JPanel();
        pnlPlayer1 = new javax.swing.JPanel();
        pnlPlayer1Img = new javax.swing.JPanel();
        lblPlayer1Img0 = new javax.swing.JLabel();
        lblPlayer1Img1 = new javax.swing.JLabel();
        lblPlayer1Card = new javax.swing.JLabel();
        lblPlayer1Stack = new javax.swing.JLabel();
        pnlPlayer2 = new javax.swing.JPanel();
        pnlPlayer2Img = new javax.swing.JPanel();
        lblPlayer2Img0 = new javax.swing.JLabel();
        lblPlayer2Img1 = new javax.swing.JLabel();
        lblPlayer2Card = new javax.swing.JLabel();
        lblPlayer2Stack = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(1200, 800));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {200};
        layout.rowHeights = new int[] {100};
        getContentPane().setLayout(layout);

        pnlGameControl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlGameControl.setMaximumSize(null);
        pnlGameControl.setMinimumSize(null);
        pnlGameControl.setName(""); // NOI18N
        pnlGameControl.setPreferredSize(null);
        pnlGameControl.setLayout(new java.awt.GridBagLayout());

        btnShuffle.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnShuffle.setText("New round");
        btnShuffle.setMaximumSize(null);
        btnShuffle.setMinimumSize(null);
        btnShuffle.setPreferredSize(null);
        btnShuffle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShuffleMouseClicked(evt);
            }
        });
        btnShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShuffleActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlGameControl.add(btnShuffle, gridBagConstraints);

        btnShowCardImage.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnShowCardImage.setText("Show Image");
        btnShowCardImage.setMaximumSize(null);
        btnShowCardImage.setMinimumSize(null);
        btnShowCardImage.setPreferredSize(null);
        btnShowCardImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowCardImageMouseClicked(evt);
            }
        });
        btnShowCardImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowCardImageActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlGameControl.add(btnShowCardImage, gridBagConstraints);

        lblMessage.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMessage.setText("Message:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 753;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlGameControl.add(lblMessage, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlGameControl, gridBagConstraints);

        pnlBoard.setLayout(new java.awt.GridBagLayout());

        pnlBoardImg.setBorder(new javax.swing.border.MatteBorder(null));
        pnlBoardImg.setLayout(new java.awt.GridBagLayout());

        lblBoardImg0.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBoardImg0.setMaximumSize(null);
        lblBoardImg0.setMinimumSize(new java.awt.Dimension(80, 120));
        lblBoardImg0.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoardImg.add(lblBoardImg0, gridBagConstraints);

        lblBoardImg1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBoardImg1.setMaximumSize(null);
        lblBoardImg1.setMinimumSize(new java.awt.Dimension(80, 120));
        lblBoardImg1.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoardImg.add(lblBoardImg1, gridBagConstraints);

        lblBoardImg2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBoardImg2.setMaximumSize(null);
        lblBoardImg2.setMinimumSize(new java.awt.Dimension(80, 120));
        lblBoardImg2.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoardImg.add(lblBoardImg2, gridBagConstraints);

        lblBoardImg3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBoardImg3.setMaximumSize(null);
        lblBoardImg3.setMinimumSize(new java.awt.Dimension(80, 120));
        lblBoardImg3.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoardImg.add(lblBoardImg3, gridBagConstraints);

        lblBoardImg4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBoardImg4.setMaximumSize(null);
        lblBoardImg4.setMinimumSize(new java.awt.Dimension(80, 120));
        lblBoardImg4.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoardImg.add(lblBoardImg4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlBoard.add(pnlBoardImg, gridBagConstraints);

        lblBoard.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBoard.setMaximumSize(new java.awt.Dimension(10000, 10000));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 12);
        pnlBoard.add(lblBoard, gridBagConstraints);

        lblPot.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPot.setText("Pot");

        lblPotValue.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPotValue.setText("0");

        javax.swing.GroupLayout jPanelPotLayout = new javax.swing.GroupLayout(jPanelPot);
        jPanelPot.setLayout(jPanelPotLayout);
        jPanelPotLayout.setHorizontalGroup(
            jPanelPotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
            .addGroup(jPanelPotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPotLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblPot)
                    .addGap(87, 87, 87)
                    .addComponent(lblPotValue)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanelPotLayout.setVerticalGroup(
            jPanelPotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelPotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPotLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanelPotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPot)
                        .addComponent(lblPotValue))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        pnlBoard.add(jPanelPot, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(pnlBoard, gridBagConstraints);

        jPanelPlayerConsole.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPlayerBet.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnPlayerBet.setText("Bet");
        btnPlayerBet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayerBetMouseClicked(evt);
            }
        });
        btnPlayerBet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerBetActionPerformed(evt);
            }
        });

        btnPlayerFold.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnPlayerFold.setText("Fold");
        btnPlayerFold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayerFoldMouseClicked(evt);
            }
        });

        btnPlayerShow.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnPlayerShow.setText("Show");
        btnPlayerShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayerShowMouseClicked(evt);
            }
        });
        btnPlayerShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPlayerConsoleLayout = new javax.swing.GroupLayout(jPanelPlayerConsole);
        jPanelPlayerConsole.setLayout(jPanelPlayerConsoleLayout);
        jPanelPlayerConsoleLayout.setHorizontalGroup(
            jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 832, Short.MAX_VALUE)
            .addGroup(jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPlayerConsoleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSliderPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPlayerBet)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPlayerFold)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnPlayerShow)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelPlayerConsoleLayout.setVerticalGroup(
            jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelPlayerConsoleLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelPlayerConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnPlayerFold)
                                .addComponent(btnPlayerShow))
                            .addComponent(jSliderPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnPlayerBet, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanelPlayerConsole, gridBagConstraints);

        jPanelPlayers.setLayout(new java.awt.GridBagLayout());

        pnlPlayer1.setBorder(javax.swing.BorderFactory.createTitledBorder("Player 1"));
        pnlPlayer1.setMinimumSize(null);
        pnlPlayer1.setLayout(new java.awt.GridBagLayout());

        pnlPlayer1Img.setLayout(new java.awt.GridBagLayout());

        lblPlayer1Img0.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPlayer1Img0.setMaximumSize(null);
        lblPlayer1Img0.setMinimumSize(new java.awt.Dimension(80, 120));
        lblPlayer1Img0.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer1Img.add(lblPlayer1Img0, gridBagConstraints);

        lblPlayer1Img1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPlayer1Img1.setMaximumSize(null);
        lblPlayer1Img1.setMinimumSize(new java.awt.Dimension(80, 120));
        lblPlayer1Img1.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer1Img.add(lblPlayer1Img1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer1.add(pnlPlayer1Img, gridBagConstraints);

        lblPlayer1Card.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPlayer1Card.setMaximumSize(null);
        lblPlayer1Card.setMinimumSize(null);
        lblPlayer1Card.setPreferredSize(new java.awt.Dimension(129, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer1.add(lblPlayer1Card, gridBagConstraints);

        lblPlayer1Stack.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPlayer1Stack.setText("Player 1: 1000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer1.add(lblPlayer1Stack, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelPlayers.add(pnlPlayer1, gridBagConstraints);

        pnlPlayer2.setBorder(javax.swing.BorderFactory.createTitledBorder("Player 2"));
        pnlPlayer2.setMinimumSize(null);
        pnlPlayer2.setLayout(new java.awt.GridBagLayout());

        pnlPlayer2Img.setMinimumSize(null);
        pnlPlayer2Img.setPreferredSize(null);
        pnlPlayer2Img.setLayout(new java.awt.GridBagLayout());

        lblPlayer2Img0.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPlayer2Img0.setMaximumSize(null);
        lblPlayer2Img0.setMinimumSize(new java.awt.Dimension(80, 120));
        lblPlayer2Img0.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer2Img.add(lblPlayer2Img0, gridBagConstraints);

        lblPlayer2Img1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblPlayer2Img1.setMaximumSize(null);
        lblPlayer2Img1.setMinimumSize(new java.awt.Dimension(80, 120));
        lblPlayer2Img1.setPreferredSize(new java.awt.Dimension(80, 120));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer2Img.add(lblPlayer2Img1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer2.add(pnlPlayer2Img, gridBagConstraints);

        lblPlayer2Card.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPlayer2Card.setMaximumSize(null);
        lblPlayer2Card.setMinimumSize(null);
        lblPlayer2Card.setPreferredSize(new java.awt.Dimension(129, 22));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer2.add(lblPlayer2Card, gridBagConstraints);

        lblPlayer2Stack.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPlayer2Stack.setText("Player 2: 1000");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        pnlPlayer2.add(lblPlayer2Stack, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanelPlayers.add(pnlPlayer2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanelPlayers, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShuffleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShuffleActionPerformed

    private void btnShuffleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShuffleMouseClicked
        // TODO add your handling code here:
        shuffle();
    }//GEN-LAST:event_btnShuffleMouseClicked
    private void dealFlop() {
        String[] flopArray = holdem.getFlop();
        flop = toString(flopArray, " ");
        System.arraycopy(flopArray, 0, board, 0, flopArray.length);
        lblBoard.setText(flop);
        if (cardImageShow) {
            showBoardImage();
        }
    }
    private void dealTurn() {
        turn = holdem.dealTurn();
        board[3] = turn;
        lblBoard.setText(flop + " " + turn);
        if (cardImageShow) {
            showBoardImage();
        }
    }
    private void dealRiver() {
        river = holdem.dealRiver();
        board[4] = river;
        lblBoard.setText(flop + " " + turn + " " + river);
        if (cardImageShow) {
            showBoardImage();
        }            
    }
    
    private void onStageChange() {
        switch (holdem.holdemState.cardStage) {
            case PRE_FLOP:
                break;
            case FLOP:
                dealFlop();
                break;
            case TURN:
                dealTurn();
                break;
            case RIVER:
                dealRiver();
                break;
            case SHOW_DOWN:
                showDown();
                break;
        }
        int playerId = holdem.holdemState.playerStage;
        TitledBorder border;
        border = javax.swing.BorderFactory.createTitledBorder("Player " + (playerId + 1));
        border.setBorder(new EtchedBorder(Color.GREEN, Color.GRAY));
        arrPnlPlayer[playerId].setBorder(border);

        border = javax.swing.BorderFactory.createTitledBorder("Player " + (1 - playerId + 1));
        border.setBorder(new EtchedBorder(Color.BLACK, Color.GRAY));
        arrPnlPlayer[1 - playerId].setBorder(border);
        
        hidePlayerImage();
    }
    
    private void btnShowCardImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowCardImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnShowCardImageActionPerformed

    private void btnShowCardImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowCardImageMouseClicked
        // TODO add your handling code here:
        if (!cardImageShow) {
            showBoardImage();
        } else {
            hideBoardImage();
            hidePlayerImage();
        }
        cardImageShow = !cardImageShow;
    }//GEN-LAST:event_btnShowCardImageMouseClicked

    private void showDown() {
        String[] showDownResultString = holdem.showDown();
        String s = "<html>" + showDownResultString[0] + 
                "<br>" + showDownResultString[1] + 
                "<br>" + showDownResultString[2];
        lblMessage.setText(s);
        List<Integer> winners = holdem.getWinners();
        int numOfWinners = winners.size();
        System.out.println("distributing the pot among " + numOfWinners + " winners.");
        double prize = holdem.pot * 1.0 / numOfWinners;
        holdem.pot = 0;
        for (int i = 0; i < holdem.players.length; i++) {
            for (int j = 0; j < winners.size(); j++) {
                if (i == winners.get(j)) {
                    System.out.println("Adding " + prize + " to Player " + i + "'s stack.");
                    holdem.players[i].incrStack((int)prize);
                    continue;
                }
            }
        }
        lblPlayer1Stack.setText("Player 1: " + Integer.toString(holdem.players[0].incrStack(0)));
        lblPlayer2Stack.setText("Player 2: " + Integer.toString(holdem.players[1].incrStack(0)));
        lblPotValue.setText(Integer.toString(holdem.pot));
    }
    private void btnPlayerShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayerShowActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayerShowActionPerformed

    private void btnPlayerShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayerShowMouseClicked
        // TODO add your handling code here:
        int playerId = holdem.holdemState.playerStage;
        if (!arrPlayerShow[playerId]) {
            arrLblPlayerCard[playerId].setText(toString(holdem.players[playerId].getPrivateCard(), " "));
            btnPlayerShow.setText("Hide");
            if (cardImageShow) {
                showPlayerImage(playerId);
            }
            hidePlayerImage(1 - playerId);
            arrPlayerShow[playerId] = true;
        } else {
            arrLblPlayerCard[playerId].setText("");
            btnPlayerShow.setText("Show");
            arrPlayerShow[playerId] = false;
            hidePlayerImage();
        }
    }//GEN-LAST:event_btnPlayerShowMouseClicked

    private void btnPlayerFoldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayerFoldMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayerFoldMouseClicked

    private void btnPlayerBetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayerBetMouseClicked
        // TODO add your handling code here:
        int playerId = holdem.holdemState.playerStage;
        int playerBetValue = jSliderPlayer.getValue();
        holdem.players[playerId].incrStack(-playerBetValue);
        arrLblPlayerStack[playerId].setText("Player " + playerId + ": " + Integer.toString(holdem.players[playerId].incrStack(0)));
        holdem.pot += playerBetValue;
        lblPotValue.setText(Integer.toString(holdem.pot));
        int[] playerBets = holdem.holdemState.getPlayerBets();
        playerBets[playerId] += playerBetValue;
        holdem.holdemState = holdem.holdemState.next(playerBets);
        onStageChange();
    }//GEN-LAST:event_btnPlayerBetMouseClicked

    private void btnPlayerBetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayerBetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPlayerBetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HoldemMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoldemMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoldemMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoldemMainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoldemMainJFrame().setVisible(true);
            }
        });
    }
    
    int numOfPlayers = 2;
    private Holdem holdem = new Holdem(numOfPlayers);
    String[] board = new String[5];
    String flop;
    String turn;
    String river;
    public boolean[] arrPlayerShow = {false, false};
    public boolean cardImageShow = false;
    javax.swing.JLabel[] arrLblPlayerCard = new javax.swing.JLabel[2];
    javax.swing.JPanel[] arrPnlPlayer = new javax.swing.JPanel[2];
    javax.swing.JLabel[] arrLblPlayerStack = new javax.swing.JLabel[2];
    private String toString(String[] stringArray, String delimiter) {
        String s = "";
        for (int i = 0; i < stringArray.length-1; i++) {
            s += stringArray[i] + delimiter;
        }
        s += stringArray[stringArray.length-1];
        return s;
    }

    private void hideBoardImage() {
        for (int i = 0; i < board.length; i++) {
            hideBoardImageAt(i);
        }
    }

    private void hideBoardImageAt(int i) {
        JLabel lblBoardImg = (JLabel) pnlBoardImg.getComponent(i);
        hideCardImage(lblBoardImg);
    }
    
    private void hideCardImage(JLabel lbl) {
        lbl.setIcon(null);
    }

    private void showBoardImage() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                break;
            }
            showBoardImageAt(i);
        }
    }

    private void showPlayerImage() {
        for (int i = 0; i < numOfPlayers; i++) {
            showPlayerImage(i);
        }
    }
    
    private void showPlayerImage(int playerNum) {
        String[] playerCardString = holdem.players[playerNum].getPrivateCard();
        JLabel[] cardLabel = new JLabel[2];
        JLabel lblPlayerCardImg0 = null;
        JLabel lblPlayerCardImg1 = null;
        if (playerNum == 0) {
            lblPlayerCardImg0 = (JLabel) pnlPlayer1Img.getComponent(0);
            lblPlayerCardImg1 = (JLabel) pnlPlayer1Img.getComponent(1);
        } else if (playerNum == 1) {
            lblPlayerCardImg0 = (JLabel) pnlPlayer2Img.getComponent(0);
            lblPlayerCardImg1 = (JLabel) pnlPlayer2Img.getComponent(1);
        }
        cardLabel[0] = lblPlayerCardImg0;
        cardLabel[1] = lblPlayerCardImg1;
        for (int i = 0; i < playerCardString.length; i++) {
            showCardImage(playerCardString[i], cardLabel[i]);
        }
    }

    private void hidePlayerImage() {
        for (int i = 0; i < numOfPlayers; i++) {
            hidePlayerImage(i);
        }
    }
    
    private void hidePlayerImage(int playerId) {
        JLabel[] cardLabel = new JLabel[2];
        JLabel lblPlayerCardImg0 = null;
        JLabel lblPlayerCardImg1 = null;
        if (playerId == 0) {
            lblPlayerCardImg0 = (JLabel) pnlPlayer1Img.getComponent(0);
            lblPlayerCardImg1 = (JLabel) pnlPlayer1Img.getComponent(1);
        } else if (playerId == 1) {
            lblPlayerCardImg0 = (JLabel) pnlPlayer2Img.getComponent(0);
            lblPlayerCardImg1 = (JLabel) pnlPlayer2Img.getComponent(1);
        }
        cardLabel[0] = lblPlayerCardImg0;
        cardLabel[1] = lblPlayerCardImg1;
        for (int i = 0; i < holdem.players[playerId].getPrivateCard().length; i++) {
            hideCardImage(cardLabel[i]);
        }
        arrLblPlayerCard[playerId].setText("");
    }
        
    private int showBoardImageAt(int i) {
        if (i >= board.length) {
            return 1;
        }
        JLabel lblBoardImg = (JLabel) pnlBoardImg.getComponent(i);
        showCardImage(board[i], lblBoardImg);
        return 0;
    }

    int showCardImage(String cardString, JLabel cardLabel) {
        BufferedImage image = null;
        try {
            System.out.println(cardString);
            File imageFile = Paths.get(System.getProperty("user.dir"), "resource", "deck", cardString + ".png").toFile();
            if (!imageFile.exists()) {
                String cardName = "";
                if (cardString.charAt(0) == 'T') {
                    cardName = "10" + cardString.charAt(1);
                }
                
                imageFile = Paths.get("resource", "deck", cardName + ".png").toFile();
            }
            if (!imageFile.exists()) {
                throw new IOException("Cannot load card image. Maybe resource folder does not exist?");
            }
            image = ImageIO.read(imageFile);
            int imageWidth = cardLabel.getWidth();
            int imageHeight = cardLabel.getHeight();
            Image cardImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
            ImageIcon cardImageIcon = new ImageIcon(cardImage);
            cardLabel.setIcon(cardImageIcon);
        } catch (IOException ex) {
            lblMessage.setText(ex.toString());
            System.err.println(ex);
        }
        return 0;
    }

    final void shuffle() {
        holdem.shuffle();
        lblBoard.setText("");
        for (int i = 0; i < board.length; i++) {
            board[i] = null;
        }
        hideBoardImage();
        for (int i = 0; i < numOfPlayers; i++) {
            holdem.dealCardForPlayer(i);
        }
        onStageChange();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayerBet;
    private javax.swing.JButton btnPlayerFold;
    private javax.swing.JButton btnPlayerShow;
    private javax.swing.JToggleButton btnShowCardImage;
    private javax.swing.JButton btnShuffle;
    private javax.swing.JPanel jPanelPlayerConsole;
    private javax.swing.JPanel jPanelPlayers;
    private javax.swing.JPanel jPanelPot;
    private javax.swing.JSlider jSliderPlayer;
    private javax.swing.JLabel lblBoard;
    private javax.swing.JLabel lblBoardImg0;
    private javax.swing.JLabel lblBoardImg1;
    private javax.swing.JLabel lblBoardImg2;
    private javax.swing.JLabel lblBoardImg3;
    private javax.swing.JLabel lblBoardImg4;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPlayer1Card;
    private javax.swing.JLabel lblPlayer1Img0;
    private javax.swing.JLabel lblPlayer1Img1;
    private javax.swing.JLabel lblPlayer1Stack;
    private javax.swing.JLabel lblPlayer2Card;
    private javax.swing.JLabel lblPlayer2Img0;
    private javax.swing.JLabel lblPlayer2Img1;
    private javax.swing.JLabel lblPlayer2Stack;
    private javax.swing.JLabel lblPot;
    private javax.swing.JLabel lblPotValue;
    private javax.swing.JPanel pnlBoard;
    private javax.swing.JPanel pnlBoardImg;
    private javax.swing.JPanel pnlGameControl;
    private javax.swing.JPanel pnlPlayer1;
    private javax.swing.JPanel pnlPlayer1Img;
    private javax.swing.JPanel pnlPlayer2;
    private javax.swing.JPanel pnlPlayer2Img;
    // End of variables declaration//GEN-END:variables
}
