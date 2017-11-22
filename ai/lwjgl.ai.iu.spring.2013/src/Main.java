import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {
	public static void control_panel() {
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Control panel");
				// Variables declaration - do not modify
				javax.swing.JButton jButtonOpenFile;
				javax.swing.JButton jButtonStart;
				javax.swing.JLabel jLabel1;
				javax.swing.JLabel jLabel2;
				javax.swing.JTextField jTextFieldSpeed;
				// End of variables declaration
				jLabel1 = new javax.swing.JLabel();
				jButtonOpenFile = new javax.swing.JButton();
				jLabel2 = new javax.swing.JLabel();
				jTextFieldSpeed = new javax.swing.JTextField();
				jButtonStart = new javax.swing.JButton();

				frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

				jLabel1.setText("Sodoku");

				jButtonOpenFile.setText("Open data file...");

				jLabel2.setText("Speed");

				jTextFieldSpeed.setText("50");

				jButtonStart.setText("Start");

				javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
				frame.getContentPane().setLayout(layout);
				layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel1).addComponent(jButtonOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextFieldSpeed)).addComponent(jButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
				layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonOpenFile).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jTextFieldSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonStart).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

				frame.pack();

				frame.setSize(160, 160);
				frame.setVisible(true);
			}
		};

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {}

		EventQueue.invokeLater(runner);
	}
}
