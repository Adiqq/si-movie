package com.sample;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Main {
	private static JLabel result;
    private static KieServices ks;
    private static KieContainer kc;
    
    private static void start() {
    	KieSession ksession = kc.newKieSession("ksession-rules");
        ksession.setGlobal( "resultLabel",
                Main.result );
        ksession.fireAllRules();
    }
	
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Romantic movie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,280));
        frame.setLayout(new FlowLayout());
        JButton button = new JButton();
        button.setText("Start");
        button.setMaximumSize(new Dimension(20,30));
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        result = new JLabel("");
        frame.add(button);
        frame.add(result);
        frame.pack();
        frame.setVisible(true);
        ks = KieServices.Factory.get();
        kc = ks.getKieClasspathContainer();
    }

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

}
