import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

public class CardComparisonWindow {

	protected Shell shlSifCardStrength;
	private Label lblAttribute;
	private Combo comboAttribute;
	private Label lblNozomi;
	
	public static FinalInformation info1;
	public static FinalInformation info2;
	
	private int tap;
	
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CardComparisonWindow window = new CardComparisonWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlSifCardStrength.open();
		shlSifCardStrength.layout();
		while (!shlSifCardStrength.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		UserInput userInput = new UserInput();
		shlSifCardStrength = new Shell();
		shlSifCardStrength.setSize(550, 400);
		shlSifCardStrength.setText("Love Live! School Idol Festival Card Strength Calculator by Umidah");
		shlSifCardStrength.setLayout(null);
		
		Label lblScore = new Label(shlSifCardStrength, SWT.NONE);
		lblScore.setToolTipText("Expected final score, required for Score-based skills");
		lblScore.setBounds(178, 117, 29, 15);
		lblScore.setText("Score");
		lblScore.setVisible(false);
		
		Spinner spinnerScore = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerScore.setTextLimit(7);
		spinnerScore.setMaximum(9999999);
		spinnerScore.setIncrement(1000);
		spinnerScore.setBounds(226, 114, 67, 22);
		spinnerScore.setVisible(false);
		
		Label lblStarNotes = new Label(shlSifCardStrength, SWT.NONE);
		lblStarNotes.setToolTipText("Star Note number, required for Star Note-based skills; daily songs are 1 difficulty higher, e.g. EX daily is Master");
		lblStarNotes.setBounds(5, 145, 54, 15);
		lblStarNotes.setText("Star Notes");
		lblStarNotes.setVisible(false);
		
		Combo comboStar = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboStar.setToolTipText("");
		comboStar.setBounds(115, 141, 178, 23);
		
		comboStar.add("Easy - 0 Star Notes");
		comboStar.add("Normal - 15 Star Notes");
		comboStar.add("Hard - 50 Star Notes");
		comboStar.add("Expert - 65 Star Notes");
		comboStar.add("Master - 70 Star Notes");
		comboStar.setText("Easy - 0 Star Notes");
		comboStar.setVisible(false);
		
		Label lblCard1SIS = new Label(shlSifCardStrength, SWT.NONE);
		lblCard1SIS.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCard1SIS.setToolTipText("School Idol Skill for Card 1");
		lblCard1SIS.setBounds(5, 229, 70, 15);
		lblCard1SIS.setText("Card 1 SI Skill");
		
		Combo comboCard1SIS = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboCard1SIS.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboCard1SIS.setBounds(115, 225, 178, 23);
		
		comboCard1SIS.add("0% None");
		comboCard1SIS.add("250% Score Up Charm");
		comboCard1SIS.add("270x HP Recover Heel");
		comboCard1SIS.add("25% Stat Up PL Trick");
		comboCard1SIS.setText("0% None");
		
		Label lblCard2SIS = new Label(shlSifCardStrength, SWT.NONE);
		lblCard2SIS.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCard2SIS.setToolTipText("School Idol Skill for Card 2");
		lblCard2SIS.setBounds(5, 257, 70, 15);
		lblCard2SIS.setText("Card 2 SI Skill");
		
		Combo comboCard2SIS = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboCard2SIS.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboCard2SIS.setBounds(115, 253, 178, 23);
		
		comboCard2SIS.add("0% None");
		comboCard2SIS.add("250% Score Up Charm");
		comboCard2SIS.add("270x HP Recover Heel");
		comboCard2SIS.add("25% Stat Up PL Trick");
		comboCard2SIS.setText("0% None");
		comboCard2SIS.setEnabled(false);
		
		Label lblCard1ID = new Label(shlSifCardStrength, SWT.NONE);
		lblCard1ID.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCard1ID.setToolTipText("The 1st card's #ID as seen on School Idol Tomodachi, NOT the in-game album number");
		lblCard1ID.setBounds(5, 8, 48, 15);
		lblCard1ID.setText("Card 1 ID");
		
		Spinner spinnerCard1 = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerCard1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		spinnerCard1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if (spinnerCard1.getText() == "") {
					return;
				}
				
				
				// Check for special parameters
				if (Integer.parseInt(spinnerCard1.getText()) == 90  ||
				    Integer.parseInt(spinnerCard1.getText()) == 107 ||
				    Integer.parseInt(spinnerCard1.getText()) == 162 ||
					Integer.parseInt(spinnerCard1.getText()) == 182 || 
					Integer.parseInt(spinnerCard1.getText()) == 206) 
				{
					
					// If Star Note Umi
					if (Integer.parseInt(spinnerCard1.getText()) == 206) {
						
						lblStarNotes.setVisible(true);
						comboStar.setVisible(true);
						
					}
					
					// Else score-based skill
					else {
						
						comboCard2SIS.setEnabled(true);
						lblScore.setVisible(true);
						spinnerScore.setVisible(true);
						
					}

				}
				
			}
		});
		
		
		spinnerCard1.setTextLimit(4);
		spinnerCard1.setBounds(115, 5, 57, 22);
		spinnerCard1.setMaximum(9999);
		spinnerCard1.setMinimum(1);
		
		Button checkIdolized1 = new Button(shlSifCardStrength, SWT.CHECK);
		checkIdolized1.setToolTipText("Is the card idolized?");
		checkIdolized1.setBounds(178, 8, 62, 16);
		checkIdolized1.setSelection(true);
		checkIdolized1.setText("Idolized");
		
		Label lblCard2ID = new Label(shlSifCardStrength, SWT.NONE);
		lblCard2ID.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCard2ID.setToolTipText("The second card's album number; use 0 if you only want to calculate the strength of 1 card");
		lblCard2ID.setBounds(5, 35, 48, 15);
		lblCard2ID.setText("Card 2 ID");
		
		Spinner spinnerCard2 = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerCard2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		spinnerCard2.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				
				if (spinnerCard2.getText() == "") {
					return;
				}
				
				// No second card
				if (Integer.parseInt(spinnerCard2.getText()) == 0) {
					comboCard2SIS.setEnabled(false);
				}
				
				else {
					comboCard2SIS.setEnabled(true);
				}
				
				// Check for special parameters
				if (Integer.parseInt(spinnerCard2.getText()) == 90  ||
				    Integer.parseInt(spinnerCard2.getText()) == 107 ||
				    Integer.parseInt(spinnerCard2.getText()) == 162 ||
					Integer.parseInt(spinnerCard2.getText()) == 182 || 
					Integer.parseInt(spinnerCard2.getText()) == 206) 
				{
					
					// If Star Note Umi
					if (Integer.parseInt(spinnerCard2.getText()) == 206) {
						
						lblStarNotes.setVisible(true);
						comboStar.setVisible(true);
						
					}
					
					// Else score-based skill
					else {
						
						comboCard2SIS.setEnabled(true);
						lblScore.setVisible(true);
						spinnerScore.setVisible(true);
						
					}

				}
				
			}
		});
		spinnerCard2.setTextLimit(4);
		spinnerCard2.setBounds(115, 32, 57, 22);
		spinnerCard2.setMaximum(9999);
		
		Button checkIdolized2 = new Button(shlSifCardStrength, SWT.CHECK);
		checkIdolized2.setToolTipText("Is the card idolized?");
		checkIdolized2.setBounds(178, 35, 62, 16);
		checkIdolized2.setSelection(true);
		checkIdolized2.setText("Idolized");
		
		lblAttribute = new Label(shlSifCardStrength, SWT.NONE);
		lblAttribute.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblAttribute.setToolTipText("Attribute of the song");
		lblAttribute.setBounds(5, 63, 47, 15);
		lblAttribute.setText("Attribute");
		
		comboAttribute = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboAttribute.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboAttribute.setBounds(115, 59, 58, 23);
		
		comboAttribute.add("Smile");
		comboAttribute.add("Pure");
		comboAttribute.add("Cool");
		comboAttribute.setText("Smile");
		
		Label lblSong = new Label(shlSifCardStrength, SWT.NONE);
		lblSong.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblSong.setToolTipText("10% modifier for matching song and members");
		lblSong.setBounds(178, 63, 27, 15);
		lblSong.setText("Song");
		
		Combo comboSong = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboSong.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboSong.setBounds(226, 59, 67, 23);
		
		comboSong.add("μ's");
		comboSong.add("Aqours");
		comboSong.add("None");
		comboSong.setText("μ's");
		
		Label lblNoteCount = new Label(shlSifCardStrength, SWT.NONE);
		lblNoteCount.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblNoteCount.setToolTipText("Number of notes in the song, required for Note-based and Combo-based skills");
		lblNoteCount.setBounds(5, 90, 62, 15);
		lblNoteCount.setText("Note Count");
		
		Spinner spinnerNoteCount = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerNoteCount.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		spinnerNoteCount.setTextLimit(4);
		spinnerNoteCount.setBounds(115, 87, 57, 22);
		spinnerNoteCount.setMaximum(9999);
		spinnerNoteCount.setMinimum(50);
		spinnerNoteCount.setSelection(50);
		
		Label lblTimes = new Label(shlSifCardStrength, SWT.NONE);
		lblTimes.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblTimes.setToolTipText("Song length in seconds, required for Time-based skills");
		lblTimes.setBounds(178, 90, 43, 15);
		lblTimes.setText("Time (s)");
		
		Spinner spinnerTime = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerTime.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		spinnerTime.setTextLimit(3);
		spinnerTime.setBounds(226, 87, 67, 22);
		spinnerTime.setMaximum(600);
		spinnerTime.setMinimum(80);
		spinnerTime.setSelection(120);
		
		Label lblPerfect = new Label(shlSifCardStrength, SWT.NONE);
		lblPerfect.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblPerfect.setToolTipText("Percentage of Perfect notes without PL assistance, required for Perfect-based skills");
		lblPerfect.setBounds(5, 117, 50, 15);
		lblPerfect.setText("Perfect %");
		
		Spinner spinnerPerfect = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerPerfect.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		spinnerPerfect.setTextLimit(3);
		spinnerPerfect.setBounds(115, 114, 57, 22);
		spinnerPerfect.setSelection(75);
		
		Text textResult = new Text(shlSifCardStrength, SWT.READ_ONLY | SWT.WRAP);
		textResult.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		textResult.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		textResult.setBounds(299, 169, 235, 182);
		textResult.setText("Help Honoka with her studying by \r\ncalculating SIF card strength!\r\n\r\nHover your mouse over the labels to \r\nlearn about each field.\r\n\r\nYour results will show up on the bottom after you click the \"Calculate!\" button.");
		
		Label lblMemberModifier = new Label(shlSifCardStrength, SWT.NONE);
		lblMemberModifier.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblMemberModifier.setToolTipText("SIF 4.0 additional UR center skill based on member type");
		lblMemberModifier.setBounds(5, 201, 93, 15);
		lblMemberModifier.setText("Member Modifier");
		
		Combo comboMemberModifier = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboMemberModifier.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboMemberModifier.setBounds(115, 197, 178, 23);
		comboMemberModifier.setEnabled(false);
		
		comboMemberModifier.add("0% None");
		comboMemberModifier.add("3% μ's Members");
		comboMemberModifier.add("3% Aqours Members");
		comboMemberModifier.add("6% First Year Students");
		comboMemberModifier.add("6% Second Year Students");
		comboMemberModifier.add("6% Third Year Students");
		comboMemberModifier.add("6% Printemps Members");
		comboMemberModifier.add("6% Lily White Members");
		comboMemberModifier.add("6% Bibi Members");
		comboMemberModifier.add("6% CYaRon! Members");
		comboMemberModifier.add("6% AZALEA Members");
		comboMemberModifier.add("6% Guilty Kiss Members");
		comboMemberModifier.setText("0% None");
		
		Label lblCenterSkill = new Label(shlSifCardStrength, SWT.NONE);
		lblCenterSkill.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCenterSkill.setToolTipText("Center Skill for your team (does not consider friend assist)");
		lblCenterSkill.setBounds(5, 173, 59, 15);
		lblCenterSkill.setText("Center Skill");
		
		Combo comboCenterSkill = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboCenterSkill.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		comboCenterSkill.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (comboCenterSkill.getText().contains("None")) {
					comboMemberModifier.setText("0% None");
					comboMemberModifier.setEnabled(false);
				}
				else {
					comboMemberModifier.setEnabled(true);
				}
			}
		});
		comboCenterSkill.setToolTipText("");
		comboCenterSkill.setBounds(115, 169, 178, 23);
		
		comboCenterSkill.add("0% None");
		comboCenterSkill.add("9% Smile");
		comboCenterSkill.add("9% Pure");
		comboCenterSkill.add("9% Cool");
		comboCenterSkill.add("12% Smile based on Pure");
		comboCenterSkill.add("12% Smile based on Cool");
		comboCenterSkill.add("12% Pure based on Smile");
		comboCenterSkill.add("12% Pure based on Cool");
		comboCenterSkill.add("12% Cool based on Smile");
		comboCenterSkill.add("12% Cool based on Pure");
		comboCenterSkill.setText("0% None");
		
		Label lblCalculationMethod = new Label(shlSifCardStrength, SWT.NONE);
		lblCalculationMethod.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		lblCalculationMethod.setToolTipText("Choose which metric to calculate card strength by");
		lblCalculationMethod.setBounds(5, 292, 105, 15);
		lblCalculationMethod.setText("Calculation Method");
		
		Button radioAverage = new Button(shlSifCardStrength, SWT.RADIO);
		radioAverage.setToolTipText("Takes the given Perfect % and the % skill activation chance");
		radioAverage.setSelection(true);
		radioAverage.setBounds(115, 292, 64, 16);
		radioAverage.setText("Average");
		
		Button radioAbsolute = new Button(shlSifCardStrength, SWT.RADIO);
		radioAbsolute.setToolTipText("Assumes 100% Perfects and 100% skill activation");
		radioAbsolute.setBounds(226, 292, 68, 16);
		radioAbsolute.setText("Absolute");
		
		Label lblCounter = new Label(shlSifCardStrength, SWT.CENTER);
		lblCounter.setBounds(440, 145, 55, 15);
		lblCounter.setText("0");
		lblCounter.setVisible(false);
		
		Label lblAlpacaKing = new Label(shlSifCardStrength, SWT.NONE);
		lblAlpacaKing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url = this.getClass().getClassLoader().getResource("img/Alpaca.wav");
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblAlpacaKing.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/629RoundAlpaca.png"));
		lblAlpacaKing.setBounds(404, 0, 130, 130);
		lblAlpacaKing.setVisible(false);
		
		Label lblHanayo = new Label(shlSifCardStrength, SWT.NONE);
		lblHanayo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 665) {
						url = this.getClass().getClassLoader().getResource("img/Hanayo.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/Alpaca.wav");
						lblHanayo.setVisible(false);
						lblAlpacaKing.setVisible(true);
						
						spinnerCard1.setSelection(666);
						spinnerCard1.setEnabled(false);
						
						checkIdolized1.setText("HELL");
						checkIdolized1.setSelection(true);
						checkIdolized1.setEnabled(false);
						
						checkIdolized2.setText("HELL");
						checkIdolized2.setSelection(true);
						checkIdolized2.setEnabled(false);
						
						spinnerCard2.setSelection(666);
						spinnerCard2.setEnabled(false);
						
						comboAttribute.add("HELL");
						comboAttribute.setText("HELL");
						comboAttribute.setEnabled(false);
						
						comboSong.add("HELL");
						comboSong.setText("HELL");
						comboSong.setEnabled(false);
						
						spinnerNoteCount.setSelection(666);
						spinnerNoteCount.setEnabled(false);
						
						spinnerTime.setMaximum(666);
						spinnerTime.setSelection(666);
						spinnerTime.setEnabled(false);
						
						spinnerPerfect.setMaximum(666);
						spinnerPerfect.setSelection(666);
						spinnerPerfect.setEnabled(false);
						
						comboCenterSkill.add("666%");
						comboCenterSkill.setText("666%");
						comboCenterSkill.setEnabled(false);
						
						comboMemberModifier.add("666%");
						comboMemberModifier.setText("666%");
						comboMemberModifier.setEnabled(false);
						
						comboCard1SIS.add("666%");
						comboCard1SIS.setText("666%");
						comboCard1SIS.setEnabled(false);
						
						comboCard2SIS.add("666%");
						comboCard2SIS.setText("666%");
						comboCard2SIS.setEnabled(false);
						
						radioAverage.setText("HELL");
						radioAverage.setSelection(true);
						radioAbsolute.setText("HELL");
						radioAbsolute.setSelection(true);
						
						textResult.setText("WELCOME TO IDOL HELL");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblHanayo.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/843RoundIdolizedHanayo.png"));
		lblHanayo.setBounds(404, 0, 130, 130);
		lblHanayo.setVisible(false);
		
		Label lblRuby = new Label(shlSifCardStrength, SWT.NONE);
		lblRuby.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 477) {
						url = this.getClass().getClassLoader().getResource("img/Ruby.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/Hanayo.wav");
						lblRuby.setVisible(false);
						lblHanayo.setVisible(true);
						textResult.setText("Save me from KLab befor-");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblRuby.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/985RoundRuby.png"));
		lblRuby.setBounds(404, 0, 130, 130);
		lblRuby.setVisible(false);
		
		Label lblURNico = new Label(shlSifCardStrength, SWT.NONE);
		lblURNico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 332) {
						url = this.getClass().getClassLoader().getResource("img/LoveNico.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/Ruby.wav");
						lblURNico.setVisible(false);
						lblRuby.setVisible(true);
						textResult.setText("Ruby says:\r\nYou should stop while you're at your Rubesty!");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblURNico.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/909RoundNico.png"));
		lblURNico.setBounds(404, 0, 130, 130);
		lblURNico.setVisible(false);
		
		Label lblAlpaca = new Label(shlSifCardStrength, SWT.NONE);
		lblAlpaca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 251) {
						url = this.getClass().getClassLoader().getResource("img/Alpaca.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/LoveNico.wav");
						lblAlpaca.setVisible(false);
						lblURNico.setVisible(true);
						textResult.setText("Nico says:\r\nDon't you have anything better to do!?");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblAlpaca.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/83RoundAlpaca.png"));
		lblAlpaca.setBounds(404, 0, 130, 130);
		lblAlpaca.setVisible(false);
		
		Label lblYou = new Label(shlSifCardStrength, SWT.NONE);
		lblYou.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 99) {
						url = this.getClass().getClassLoader().getResource("img/You.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/Alpaca.wav");
						lblYou.setVisible(false);
						lblAlpaca.setVisible(true);
						textResult.setText("Nothing to see here~");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblYou.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/957RoundIdolizedYou.png"));
		lblYou.setBounds(404, 0, 130, 130);
		lblYou.setVisible(false);
		
		Label lblSRNico = new Label(shlSifCardStrength, SWT.NONE);
		lblSRNico.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 43) {
						url = this.getClass().getClassLoader().getResource("img/Nico.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/You.wav");
						lblSRNico.setVisible(false);
						lblYou.setVisible(true);
						textResult.setText("You says:\r\nYosoro~ You've reached the final stop! Why not try using the calculator for its intended purpose now?");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblSRNico.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/899RoundNico.png"));
		lblSRNico.setBounds(404, 0, 130, 130);
		lblSRNico.setVisible(false);
		
		Label lblHonoka = new Label(shlSifCardStrength, SWT.NONE);
		lblHonoka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url;
					if (tap < 24) {
						url = this.getClass().getClassLoader().getResource("img/Blah.wav");
					}
					
					else {
						url = this.getClass().getClassLoader().getResource("img/Nico.wav");
						lblHonoka.setVisible(false);
						lblSRNico.setVisible(true);
						textResult.setText("Nico says:\r\nNico Nico Nii~ I Nico Nico Need you to help me with my homework by using the calculator!");
					}
					
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
					tap++;
					lblCounter.setText(Integer.toString(tap));
					lblCounter.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		lblHonoka.setBounds(404, 0, 130, 130);
		lblHonoka.setAlignment(SWT.RIGHT);
		lblHonoka.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/900RoundHonoka.png"));
		
		lblNozomi = new Label(shlSifCardStrength, SWT.NONE);
		lblNozomi.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/890RoundNozomi.png"));
		lblNozomi.setBounds(404, 0, 130, 130);
		lblNozomi.setVisible(false);
		
		// Render results table
		table = new Table(shlSifCardStrength, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(17, 380, 500, 142);
		
		TableColumn tblclmnResult = new TableColumn(table, SWT.NONE);
		tblclmnResult.setWidth(125);
		tblclmnResult.setText("Result");
	    
	    TableColumn tblclmnCard1 = new TableColumn(table, SWT.NONE);
	    tblclmnCard1.setWidth(123);
	    tblclmnCard1.setText("Card 1");
	    
	    TableColumn tblclmnCard2 = new TableColumn(table, SWT.NONE);
	    tblclmnCard2.setWidth(123);
	    tblclmnCard2.setText("Card 2");
	    
	    TableColumn tblclmnDifference = new TableColumn(table, SWT.NONE);
	    tblclmnDifference.setWidth(125);
	    tblclmnDifference.setText("Difference");
	    
		// Render 1 card   
	    String cardName[] = { "Card Name" };
	    
	    TableItem tableItemCardName = new TableItem(table, SWT.NONE);
	    tableItemCardName.setText(cardName);
	    
	    String collection[] = { "Collection" };
	    
	    TableItem tableItemCollection = new TableItem(table, SWT.NONE);
	    tableItemCollection.setText(collection);
	    
	    String baseStat[] = { "Base Stat" }; 
	    
	    TableItem tableItemBaseStat = new TableItem(table, SWT.NONE);
	    tableItemBaseStat.setText(baseStat);
	
	    String scoreContribution[] = { "Score Contribution" };
	    
	    TableItem tableItemScoreContribution = new TableItem(table, SWT.NONE);
	    tableItemScoreContribution.setText(scoreContribution);
	    
	    String skillContribution[] = { "Skill Contribution" };
	    
	 
	    TableItem tableItemSkillContribution = new TableItem(table, SWT.NONE);
	    tableItemSkillContribution.setText(skillContribution);
	    
	    String finalScore[] = { "Final Score" };
	    
	    TableItem tableItemFinalScore = new TableItem(table, SWT.NONE);
	    tableItemFinalScore.setText(finalScore);
	    
		Button btnCalculate = new Button(shlSifCardStrength, SWT.NONE);
		btnCalculate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if (tap >= 666) {
					
					for (int i = 1; i <= 666; i++) {
						
						try {
							URL url = this.getClass().getClassLoader().getResource("img/Alpaca.wav");
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
							Clip clip = AudioSystem.getClip();
							clip.open(audioIn);
							clip.start();
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
					}
					
					try {
						TimeUnit.SECONDS.sleep(2);
						System.exit(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return;
					
				}
				
				lblCounter.setVisible(false);
				
				// Take user input and set it to one UserInput object
				userInput.setCard1ID(Integer.toString(spinnerCard1.getSelection()));
				userInput.setCard1Idolized(checkIdolized1.getSelection());
				
				userInput.setCard2ID(Integer.toString(spinnerCard2.getSelection()));
				userInput.setCard2Idolized(checkIdolized2.getSelection());
				
				userInput.setAttribute(comboAttribute.getText());
				userInput.setSong(comboSong.getText());
				
				userInput.setNoteCount(spinnerNoteCount.getSelection());
				userInput.setTime(spinnerTime.getSelection());
				
				// Convert Perfect integer to % double
				userInput.setPerfectPercent(spinnerPerfect.getSelection() * 0.01);
				userInput.setFinalScore(spinnerScore.getSelection());
				
				// Convert Star Notes string to integer
				if (comboStar.getText().contains("Normal")) {
					userInput.setStarNotes(15);
				}
				
				else if (comboStar.getText().contains("Hard")) {
					userInput.setStarNotes(50);
				} 
				
				else if (comboStar.getText().contains("Expert")) {
					userInput.setStarNotes(65);
				}
				
				else if (comboStar.getText().contains("Master")) {
					userInput.setStarNotes(70);
				}
				
				else {
					userInput.setStarNotes(0);
				}
				
				// The rest of the user input
				userInput.setCenterSkill(comboCenterSkill.getText());
				userInput.setMemberModifier(comboMemberModifier.getText());
				userInput.setCard1SIS(comboCard1SIS.getText());
				userInput.setCard2SIS(comboCard2SIS.getText());
				
				if (radioAbsolute.getSelection()) {
					userInput.setCalculationMethod("Absolute");
				}
				
				else {
					userInput.setCalculationMethod("Average");
				}
				
				// Pass UserInput to CardComparison function
				try {
					CardComparison cardComparison = new CardComparison(userInput);
					
					
					if (cardComparison.getOver()) {	
						textResult.setText("Honoka says:\r\nYou picked a card ID that doesn't exist!");
						
						try {
							URL url = this.getClass().getClassLoader().getResource("img/Honoka.wav");
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
							Clip clip = AudioSystem.getClip();
							clip.open(audioIn);
							clip.start();
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
					}
					
					else if (cardComparison.getSpecial()) {
						textResult.setText("Honoka says:\r\nYou picked a card ID that can't be put on your team!");
						
						try {
							URL url = this.getClass().getClassLoader().getResource("img/Honoka.wav");
							AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
							Clip clip = AudioSystem.getClip();
							clip.open(audioIn);
							clip.start();
							
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
					}
					
					else {
						
						lblHonoka.setVisible(false);
						lblNozomi.setVisible(true);
						shlSifCardStrength.setSize(550, 600);
						
						// If two cards
						if (info1 != info2) {
							
							URL url;
							
							DifferenceInformation difference = new DifferenceInformation(info1, info2);
							
							// Render both cards and compare   
						    String cardName[] = { "Card Name", 
						    					  cardComparison.getCard1().getName(), 
						    					  cardComparison.getCard2().getName()};
						    
						    tableItemCardName.setText(cardName);
						    
						    String collection[] = { "Collection", 
						    						cardComparison.getCard1().getCollection(), 
						    						cardComparison.getCard2().getCollection() };
						    
						    tableItemCollection.setText(collection);
						    
						    String baseStat[] = { "Base Stat", 
						    					  info1.getFinalBaseStats(), 
						    					  info2.getFinalBaseStats(), difference.getFinalBaseStatsDifference()}; 

						    tableItemBaseStat.setText(baseStat);
						
						    String scoreContribution[] = { "Score Contribution", 
						    		 					   info1.getScoreContribution(), 
						    		 					   info2.getScoreContribution(), difference.getScoreContributionDifference() };

						    tableItemScoreContribution.setText(scoreContribution);
						    
						    String skillContribution[] = { "Skill Contribution", 
						    							   info1.getSkillContribution(),
						    							   info2.getSkillContribution(),
						    							   difference.getSkillContributionDifference() };

						    tableItemSkillContribution.setText(skillContribution);
						    
						    String finalScore[] = { "Final Score",
						    						info1.getFinalScore(), 
						    						info2.getFinalScore(), 
						    						difference.getFinalScoreDifference()};

						    tableItemFinalScore.setText(finalScore);
						    
						    // Nozomi's evaluation
						    String evaluation = "Nozomi's evaluation:\r\n\r\nHere is the breakdown for your cards.\r\n\r\n";
						    int card1Counter = 0;
						    int card2Counter = 0;
						    
						    // Evaluate Score Contribution
						    if (Integer.parseInt(info1.getScoreContribution()) > Integer.parseInt(info2.getScoreContribution())) {
						    	card1Counter++;
						    	evaluation = evaluation + "Card 1's Score Contribution is better than Card 2's.\r\n";
						    }
						    
						    else if (Integer.parseInt(info1.getScoreContribution()) < Integer.parseInt(info2.getScoreContribution())) {
						    	card2Counter++;
						    	evaluation = evaluation + "Card 2's Score Contribution is better than Card 1's.\r\n";
						    }
						    
						    else {
						    	evaluation = evaluation + "Both cards have the same Score Contribution!\r\n";
						    }
						    
						    // Evaluate Skill Contribution
						    if (Integer.parseInt(info1.getSkillContribution()) > Integer.parseInt(info2.getSkillContribution())) {
						    	
						    	if (card1Counter == 1) {
						    		evaluation = evaluation + "Card 1's Skill Contribution is also better.\r\n";
						    	}
						    	
						    	else  {
						    		evaluation = evaluation + "However, Card 1 has the better Skill Contribution.\r\n";
						    	}
						    	
						    }
						    
						    else if (Integer.parseInt(info1.getSkillContribution()) < Integer.parseInt(info2.getSkillContribution())) {
						    	
						    	if (card2Counter == 1) {
						    		evaluation = evaluation + "Card 2's Skill Contribution is also better.\r\n";
						    	}
						    	
						    	else  {
						    		evaluation = evaluation + "However, Card 2 has the better Skill Contribution.\r\n";
						    	}
						    	
						    }
						    
						    else {
						    	evaluation = evaluation + "Both cards have the same Skill Contribution!\r\n";
						    }
						    
						    // Evaluate Final Score
						    if (Integer.parseInt(info1.getFinalScore()) > Integer.parseInt(info2.getFinalScore())) {
						    	evaluation = evaluation + "Overall, Card 1 is the stronger card under these conditions.\r\n\r\n";
								url = this.getClass().getClassLoader().getResource("img/NozomiEvaluation.wav");
						    }
						    
						    else if (Integer.parseInt(info1.getFinalScore()) < Integer.parseInt(info2.getFinalScore())) {
						    	evaluation = evaluation + "Overall, Card 2 is the stronger card under these conditions.\r\n\r\n";
						    	url = this.getClass().getClassLoader().getResource("img/NozomiEvaluation.wav");
						    }
						    
						    else {
							    evaluation = evaluation + "It looks like these two cards are evenly matched!\r\n\r\n";
								url = this.getClass().getClassLoader().getResource("img/NozomiTie.wav");
						    }
						    
						    // Final words
						    if ("Absolute".equals(userInput.getCalculationMethod())) {
						    	evaluation = evaluation + "This is the best case scenario, though. Try calculating using Average for more realistic results!";
						    }
						    
						    else {
						    	evaluation = evaluation + "This is the average scenario, though. Try calculating using Absolute to see the cards' true potential!";
						    }
						    
						    textResult.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
						    textResult.setText(evaluation);
						    
							try {
								AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
								Clip clip = AudioSystem.getClip();
								clip.open(audioIn);
								clip.start();
								
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							
						}
						
						// Else render 1 card
						else {
							
							// Render 1 card   
						    String cardName[] = { "Card Name", 
						    					  cardComparison.getCard1().getName() };
						    
						    tableItemCardName.setText(cardName);
						    
						    String collection[] = { "Collection", 
						    						cardComparison.getCard1().getCollection() };
						    
						    tableItemCollection.setText(collection);
						    
						    String baseStat[] = { "Base Stat", 
						    					  info1.getFinalBaseStats() }; 
						    
						    tableItemBaseStat.setText(baseStat);
						
						    String scoreContribution[] = { "Score Contribution", 
						    		 					   info1.getScoreContribution() };
						    
						    tableItemScoreContribution.setText(scoreContribution);
						    
						    String skillContribution[] = { "Skill Contribution", 
						    							   info1.getSkillContribution() };
						    
						 
						    tableItemSkillContribution.setText(skillContribution);
						    
						    String finalScore[] = { "Final Score",
						    						info1.getFinalScore() };
						    
						    tableItemFinalScore.setText(finalScore);
						    
						    textResult.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
						    
						    if (userInput.getCard1ID().equals(userInput.getCard2ID())) {
							    textResult.setText("Nozomi's evaluation:\r\n\r\nDid you know that Insanity is doing the same thing over and over again and expecting different results?");
							    
								try {
									URL url = this.getClass().getClassLoader().getResource("img/NozomiFail.wav");
									AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
									Clip clip = AudioSystem.getClip();
									clip.open(audioIn);
									clip.start();
									
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							    
						    }
						    
						    else {
							    textResult.setText("Nozomi's evaluation:\r\n\r\nHere is the breakdown for your card.\r\n\r\nTry comparing it with other cards to see how strong it really is!");
							    
								try {
									URL url = this.getClass().getClassLoader().getResource("img/NozomiEvaluation.wav");
									AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
									Clip clip = AudioSystem.getClip();
									clip.open(audioIn);
									clip.start();
									
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								
						    }
							
						}
						
						
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					textResult.setText("Something went wrong! Please send @Umidahh a message on Twitter with a screenshot so it can be fixed!");
					e.printStackTrace();
				}

			}
		});
		btnCalculate.setBounds(5, 315, 64, 25);
		btnCalculate.setText("Calculate!");
		
		shlSifCardStrength.setTabList(new Control[]{spinnerCard1, checkIdolized1, spinnerCard2, checkIdolized2, comboAttribute, comboSong, spinnerNoteCount, spinnerTime, spinnerPerfect, spinnerScore, comboStar, comboCenterSkill, comboMemberModifier, comboCard1SIS, comboCard2SIS, radioAverage, radioAbsolute, btnCalculate, textResult});
		
		
	}
}
