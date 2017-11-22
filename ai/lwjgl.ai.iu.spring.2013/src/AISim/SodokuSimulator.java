package AISim;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import lab5.Csp.Sudoku.ISodokuListener;
import lab5.Csp.Sudoku.SodokuBoard;
import lab5.Csp.Sudoku.SodokuVariable;
import lab5.Csp.Sudoku.SodokuVariableCollection;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Inference;
import aima.core.search.csp.ImprovedBacktrackingStrategy.Selection;

public class SodokuSimulator {

	public static void main(String[] a) {
		LayoutApp();
	}

	public static void LayoutApp() {

		Runnable runner = new Runnable() {
			@Override
			public void run() {
				final JFrame frame = new JFrame();
				final SodokuOpenGL[] gui = new SodokuOpenGL[1];
				final String[] sfile = new String[1];

				final javax.swing.JLabel jLabel1;
				final javax.swing.JLabel jLabelDataPresent;
				final javax.swing.JLabel jLabel3;
				final javax.swing.JMenu jMenu1;
				final javax.swing.JMenuBar jMenuBar1;
				final javax.swing.JMenu jMenuFile;
				final javax.swing.JMenuItem jMenuItemStepByStep;
				final javax.swing.JMenuItem jMenuItemGotoResult;
				final javax.swing.JMenuItem jMenuItemHereQuickSolve;
				final javax.swing.JMenuItem jMenuItemStepbyStepHere;

				final javax.swing.JMenuItem jMenuItemExit;
				final javax.swing.JMenuItem jMenuItemOpenDefault;
				final javax.swing.JMenuItem jMenuItemOpenFile;
				final javax.swing.JMenu jMenuOpen;

				jLabel1 = new javax.swing.JLabel();
				jLabelDataPresent = new javax.swing.JLabel();
				jLabel3 = new javax.swing.JLabel();
				jMenuBar1 = new javax.swing.JMenuBar();
				jMenuFile = new javax.swing.JMenu();
				jMenuOpen = new javax.swing.JMenu();
				jMenuItemOpenDefault = new javax.swing.JMenuItem();
				jMenuItemOpenFile = new javax.swing.JMenuItem();
				jMenuItemExit = new javax.swing.JMenuItem();
				jMenu1 = new javax.swing.JMenu();
				jMenuItemStepByStep = new javax.swing.JMenuItem();
				jMenuItemGotoResult = new javax.swing.JMenuItem();
				jMenuItemHereQuickSolve = new javax.swing.JMenuItem();
				jMenuItemStepbyStepHere = new javax.swing.JMenuItem();

				frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
				frame.setTitle("lab5.Csp.Sudoku");

				jLabel1.setText("<html><H3>data presetation</H3></html>");

				jLabelDataPresent.setText("please load data file. or CTRL+D to open default data");

				jLabel3.setText("Author: ");

				jMenuFile.setText("File");

				jMenuOpen.setText("Open..");

				jMenuItemOpenDefault.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemOpenDefault.setText("Default");
				jMenuOpen.add(jMenuItemOpenDefault);

				jMenuItemOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemOpenFile.setText("File");
				jMenuOpen.add(jMenuItemOpenFile);

				jMenuFile.add(jMenuOpen);

				jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
				jMenuItemExit.setText("Exit");
				jMenuFile.add(jMenuItemExit);

				jMenuBar1.add(jMenuFile);

				jMenu1.setText("Solve");

				jMenuItemStepByStep.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemStepByStep.setText("'Step by step' Solve via OpenGL");
				jMenu1.add(jMenuItemStepByStep);

				jMenuItemGotoResult.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemGotoResult.setText("'Quick Solve' via OpenGL");
				jMenu1.add(jMenuItemGotoResult);

				jMenuItemStepbyStepHere.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemStepbyStepHere.setText("'Step by step' Solve via Swing App");
				jMenu1.add(jMenuItemStepbyStepHere);

				jMenuItemHereQuickSolve.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
				jMenuItemHereQuickSolve.setText("'Quick Solve' via Swing App");
				jMenu1.add(jMenuItemHereQuickSolve);

				jMenuBar1.add(jMenu1);

				frame.setJMenuBar(jMenuBar1);

				javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
				frame.getContentPane().setLayout(layout);
				layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabelDataPresent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel3)).addGap(0, 287, Short.MAX_VALUE))).addContainerGap()));
				layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelDataPresent, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3)));

				frame.pack();

				jMenuItemOpenDefault.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						sfile[0] = new File("").getAbsolutePath() + "\\res\\lab5\\sodoku\\sodoku.txt";
						System.out.println(sfile[0]);

						gui[0] = new SodokuOpenGL(sfile[0]);
						jLabelDataPresent.setText(gui[0].var.getHtml());
					}
				});
				jMenuItemOpenFile.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						JFileChooser fc = new JFileChooser(new File("..").getAbsolutePath());
						fc.setFileFilter(new FileFilter() {

							@Override
							public String getDescription() {
								return "txt file";
							}

							@Override
							public boolean accept(File f) {
								if (f.isDirectory())
									return true;

								if (f.getName().endsWith(".txt"))
									return true;
								return false;
							}
						});
						fc.showOpenDialog(null);

						File selFile = fc.getSelectedFile();
						sfile[0] = selFile.toString();

						gui[0] = new SodokuOpenGL(sfile[0]);
						jLabelDataPresent.setText(gui[0].var.getHtml());

						System.out.println(sfile);
					}
				});

				jMenuItemOpenDefault.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						sfile[0] = new File("").getAbsolutePath() + "\\res\\lab5\\sodoku\\sodoku.txt";
						System.out.println(sfile[0]);

						gui[0] = new SodokuOpenGL(sfile[0]);
						jLabelDataPresent.setText(gui[0].var.getHtml());
					}
				});

				jMenuItemStepByStep.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								gui[0] = new SodokuOpenGL(sfile[0]);
								gui[0].setSpeed(500);
								gui[0].start();
							}
						}).start();
					}
				});

				jMenuItemGotoResult.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								gui[0] = new SodokuOpenGL(sfile[0]);
								gui[0].setSpeed(0);
								gui[0].start();
							}
						}).start();

					}
				});

				jMenuItemHereQuickSolve.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								gui[0] = new SodokuOpenGL(sfile[0]);

								final SodokuVariableCollection var = new SodokuVariableCollection(sfile[0]);
								final SodokuBoard csp = new SodokuBoard(var);
								final ImprovedBacktrackingStrategy solver2 = new ImprovedBacktrackingStrategy();

								solver2.setVariableSelection(Selection.MRV);
								solver2.setInference(Inference.FORWARD_CHECKING);

								System.out.println("Apply Backtracking Strategy: ImprovedBacktrackingStrategy");

								ISodokuListener listener = new ISodokuListener() {

									@Override
									public void setVariable(SodokuVariable v, int assign) {
										v.value = assign;
										jLabelDataPresent.setText(var.getHtml());
										try {
											Thread.sleep(0);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								};

								csp.setSodokuListener(listener);

								// Solve the Problem, and Get the result
								long start = System.currentTimeMillis();
								Assignment results = solver2.solve(csp);
								long finish = System.currentTimeMillis();
								long diff = finish - start;

								System.out.println("execute time " + (diff / 1000) + " second");

								// Print the output
								for (int i = 0; i < 9; i++) {
									for (int j = 0; j < 9; j++) {
										SodokuVariable v = var.get(i, j);
										v.value = (Integer) results.getAssignment(v);
									}
								}

								System.out.println("\n------->>  result  <<-----------");
								System.out.println(var.getString());

								jLabelDataPresent.setText(gui[0].var.getHtml());
								gui[0].setSpeed(0);
								gui[0].start();
							}
						}).start();

					}
				});

				jMenuItemStepbyStepHere.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								gui[0] = new SodokuOpenGL(sfile[0]);

								final SodokuVariableCollection var = new SodokuVariableCollection(sfile[0]);
								final SodokuBoard csp = new SodokuBoard(var);
								final ImprovedBacktrackingStrategy solver2 = new ImprovedBacktrackingStrategy();

								solver2.setVariableSelection(Selection.MRV);
								solver2.setInference(Inference.FORWARD_CHECKING);

								System.out.println("Apply Backtracking Strategy: ImprovedBacktrackingStrategy");

								ISodokuListener listener = new ISodokuListener() {

									@Override
									public void setVariable(SodokuVariable v, int assign) {
										v.value = assign;
										jLabelDataPresent.setText(var.getHtml());
										try {
											Thread.sleep(500);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								};

								csp.setSodokuListener(listener);

								// Solve the Problem, and Get the result
								long start = System.currentTimeMillis();
								Assignment results = solver2.solve(csp);
								long finish = System.currentTimeMillis();
								long diff = finish - start;

								System.out.println("execute time " + (diff / 1000) + " second");

								// Print the output
								for (int i = 0; i < 9; i++) {
									for (int j = 0; j < 9; j++) {
										SodokuVariable v = var.get(i, j);
										v.value = (Integer) results.getAssignment(v);
									}
								}

								System.out.println("\n------->>  result  <<-----------");
								System.out.println(var.getString());

								jLabelDataPresent.setText(gui[0].var.getHtml());
								gui[0].setSpeed(0);
								gui[0].start();
							}
						}).start();

					}
				});
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
