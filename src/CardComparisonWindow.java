import java.io.IOException;
import java.net.URL;

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

public class CardComparisonWindow {

	protected Shell shlSifCardStrength;
	private Label lblAttribute;
	private Combo comboAttribute;
	private Label lblNozomi;

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
		shlSifCardStrength.setMinimumSize(new Point(550, 400));
		shlSifCardStrength.setSize(550, 400);
		shlSifCardStrength.setText("Love Live! School Idol Festival - Card Strength Calculator by Umidah");
		shlSifCardStrength.setLayout(null);
		
		Label lblCard1ID = new Label(shlSifCardStrength, SWT.NONE);
		lblCard1ID.setToolTipText("The 1st card's album number");
		lblCard1ID.setBounds(5, 8, 48, 15);
		lblCard1ID.setText("Card 1 ID");
		
		Spinner spinnerCard1 = new Spinner(shlSifCardStrength, SWT.BORDER);
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
		lblCard2ID.setToolTipText("The second card's album number; use 0 if you only want to calculate the strength of 1 card");
		lblCard2ID.setBounds(5, 35, 48, 15);
		lblCard2ID.setText("Card 2 ID");
		
		Spinner spinnerCard2 = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerCard2.setTextLimit(4);
		spinnerCard2.setBounds(115, 32, 57, 22);
		spinnerCard2.setMaximum(9999);
		
		Button checkIdolized2 = new Button(shlSifCardStrength, SWT.CHECK);
		checkIdolized2.setToolTipText("Is the card idolized?");
		checkIdolized2.setBounds(178, 35, 62, 16);
		checkIdolized2.setSelection(true);
		checkIdolized2.setText("Idolized");
		
		lblAttribute = new Label(shlSifCardStrength, SWT.NONE);
		lblAttribute.setToolTipText("Attribute of the song");
		lblAttribute.setBounds(5, 63, 47, 15);
		lblAttribute.setText("Attribute");
		
		comboAttribute = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboAttribute.setBounds(115, 59, 58, 23);
		
		comboAttribute.add("Smile");
		comboAttribute.add("Pure");
		comboAttribute.add("Cool");
		comboAttribute.setText("Smile");
		
		Label lblSong = new Label(shlSifCardStrength, SWT.NONE);
		lblSong.setToolTipText("10% modifier for matching song and members");
		lblSong.setBounds(178, 63, 27, 15);
		lblSong.setText("Song");
		
		Combo comboSong = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboSong.setBounds(226, 59, 67, 23);
		
		comboSong.add("μ's");
		comboSong.add("Aqours");
		comboSong.setText("μ's");
		
		Label lblNoteCount = new Label(shlSifCardStrength, SWT.NONE);
		lblNoteCount.setToolTipText("Number of notes in the song, required for Note-based and Combo-based skills");
		lblNoteCount.setBounds(5, 90, 62, 15);
		lblNoteCount.setText("Note Count");
		
		Spinner spinnerNoteCount = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerNoteCount.setTextLimit(4);
		spinnerNoteCount.setBounds(115, 87, 57, 22);
		spinnerNoteCount.setMaximum(1000);
		spinnerNoteCount.setMinimum(50);
		spinnerNoteCount.setSelection(50);
		
		Label lblTimes = new Label(shlSifCardStrength, SWT.NONE);
		lblTimes.setToolTipText("Song length in seconds, required for Time-based skills");
		lblTimes.setBounds(178, 90, 43, 15);
		lblTimes.setText("Time (s)");
		
		Spinner spinnerTime = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerTime.setTextLimit(3);
		spinnerTime.setBounds(226, 87, 67, 22);
		spinnerTime.setMaximum(600);
		spinnerTime.setMinimum(80);
		spinnerTime.setSelection(120);
		
		Label lblPerfect = new Label(shlSifCardStrength, SWT.NONE);
		lblPerfect.setToolTipText("Percentage of Perfect notes without PL assistance, required for Perfect-based skills");
		lblPerfect.setBounds(5, 117, 50, 15);
		lblPerfect.setText("Perfect %");
		
		Spinner spinnerPerfect = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerPerfect.setTextLimit(3);
		spinnerPerfect.setBounds(115, 114, 57, 22);
		spinnerPerfect.setSelection(75);
		
		Label lblScore = new Label(shlSifCardStrength, SWT.NONE);
		lblScore.setToolTipText("Expected final score, required for Score-based skills");
		lblScore.setBounds(178, 117, 29, 15);
		lblScore.setText("Score");
		
		Spinner spinnerScore = new Spinner(shlSifCardStrength, SWT.BORDER);
		spinnerScore.setTextLimit(7);
		spinnerScore.setMaximum(9999999);
		spinnerScore.setIncrement(1000);
		spinnerScore.setBounds(226, 114, 67, 22);
		
		Label lblStarNotes = new Label(shlSifCardStrength, SWT.NONE);
		lblStarNotes.setToolTipText("Star Note number, required for Star Note-based skills; daily songs are 1 difficulty higher, e.g. EX daily is Master");
		lblStarNotes.setBounds(5, 145, 54, 15);
		lblStarNotes.setText("Star Notes");
		
		
		Combo comboStar = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboStar.setToolTipText("");
		comboStar.setBounds(115, 141, 178, 23);
		
		comboStar.add("Easy - 0 Star Notes");
		comboStar.add("Normal - 15 Star Notes");
		comboStar.add("Hard - 50 Star Notes");
		comboStar.add("Expert - 65 Star Notes");
		comboStar.add("Master - 70 Star Notes");
		comboStar.setText("Easy - 0 Star Notes");
		
		Text textResult = new Text(shlSifCardStrength, SWT.READ_ONLY | SWT.WRAP);
		textResult.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		textResult.setBounds(299, 141, 235, 184);
		textResult.setText("Help Honoka with her studying by \r\ncalculating SIF card strength!\r\n\r\nHover your mouse over the labels to \r\nlearn about each field.\r\n\r\nYour results will show up here after you click the \"Calculate!\" button.");
		
		Label lblMemberModifier = new Label(shlSifCardStrength, SWT.NONE);
		lblMemberModifier.setToolTipText("SIF 4.0 additional UR center skill based on member type");
		lblMemberModifier.setBounds(5, 201, 93, 15);
		lblMemberModifier.setText("Member Modifier");
		
		Combo comboMemberModifier = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboMemberModifier.setBounds(115, 197, 178, 23);
		comboMemberModifier.setEnabled(false);
		
		comboMemberModifier.add("0% None");
		comboMemberModifier.add("3% μ's Members");
		comboMemberModifier.add("3% Aqours Members");
		comboMemberModifier.add("6% 1st Year Students");
		comboMemberModifier.add("6% 2nd Year Students");
		comboMemberModifier.add("6% 3nd Year Students");
		comboMemberModifier.add("6% Printemps Members");
		comboMemberModifier.add("6% lily white Members");
		comboMemberModifier.add("6% BiBi Members");
		comboMemberModifier.add("6% CYaRon! Members");
		comboMemberModifier.add("6% AZALEA Members");
		comboMemberModifier.add("6% Guilty Kiss Members");
		comboMemberModifier.setText("0% None");
		
		Label lblCenterSkill = new Label(shlSifCardStrength, SWT.NONE);
		lblCenterSkill.setToolTipText("Center Skill for your team (does not consider friend assist)");
		lblCenterSkill.setBounds(5, 173, 59, 15);
		lblCenterSkill.setText("Center Skill");
		
		Combo comboCenterSkill = new Combo(shlSifCardStrength, SWT.READ_ONLY);
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
		
		Label lblCardSi = new Label(shlSifCardStrength, SWT.NONE);
		lblCardSi.setToolTipText("School Idol Skill for Card 1");
		lblCardSi.setBounds(5, 229, 70, 15);
		lblCardSi.setText("Card 1 SI Skill");
		
		Combo comboCard1SIS = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboCard1SIS.setBounds(115, 225, 178, 23);
		
		comboCard1SIS.add("0% None");
		comboCard1SIS.add("250% Score Up Charm");
		comboCard1SIS.add("270x HP Recover Heel");
		comboCard1SIS.add("25% Stat Up PL Trick");
		comboCard1SIS.setText("0% None");
		
		Label lblCardSi_1 = new Label(shlSifCardStrength, SWT.NONE);
		lblCardSi_1.setToolTipText("School Idol Skill for Card 2");
		lblCardSi_1.setBounds(5, 257, 70, 15);
		lblCardSi_1.setText("Card 2 SI Skill");
		
		Combo comboCard2SIS = new Combo(shlSifCardStrength, SWT.READ_ONLY);
		comboCard2SIS.setBounds(115, 253, 178, 23);
		
		comboCard2SIS.add("0% None");
		comboCard2SIS.add("250% Score Up Charm");
		comboCard2SIS.add("270x HP Recover Heel");
		comboCard2SIS.add("25% Stat Up PL Trick");
		comboCard2SIS.setText("0% None");
		
		Label lblCalculationMethod = new Label(shlSifCardStrength, SWT.NONE);
		lblCalculationMethod.setToolTipText("Choose which metric to calculate card strength by");
		lblCalculationMethod.setBounds(5, 292, 105, 15);
		lblCalculationMethod.setText("Calculation Method");
		
		Button radioAverage = new Button(shlSifCardStrength, SWT.RADIO);
		radioAverage.setToolTipText("Takes the given Perfect % and the % skill activation chance");
		radioAverage.setBounds(115, 292, 64, 16);
		radioAverage.setText("Average");
		
		Button radioAbsolute = new Button(shlSifCardStrength, SWT.RADIO);
		radioAbsolute.setToolTipText("Assumes 100% Perfects and 100% skill activation");
		radioAbsolute.setSelection(true);
		radioAbsolute.setBounds(226, 292, 68, 16);
		radioAbsolute.setText("Absolute");
		
		Label lblHonoka = new Label(shlSifCardStrength, SWT.NONE);
		lblHonoka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				try {
					URL url = this.getClass().getClassLoader().getResource("img/Blah.wav");
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
					Clip clip = AudioSystem.getClip();
					clip.open(audioIn);
					clip.start();
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
		
		Button btnCalculate = new Button(shlSifCardStrength, SWT.NONE);
		btnCalculate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				lblHonoka.setVisible(false);
				lblNozomi.setVisible(true);
				textResult.setText("Nozomi says:\r\nStop making the UI look nice\r\nand code the actual program!");
				
				// Take user input and set it to one UserInput object
				userInput.setCard1ID(Integer.toString(spinnerCard1.getSelection()));
				userInput.setCard1Idolized(checkIdolized1.getSelection());
				
				userInput.setCard2ID(Integer.toString(spinnerCard2.getSelection()));
				userInput.setCard2Idolized(checkIdolized2.getSelection());
				
				userInput.setAttribute(comboAttribute.getText());
				userInput.setSong(comboSong.getText());
				
				userInput.setNoteCount(spinnerNoteCount.getSelection());
				userInput.setTime(spinnerTime.getSelection());
				
				// Convert Perfect interger to % double
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
				
				// Pass UserInput to CardComparison function
				try {
					CardComparison cardComparison = new CardComparison(userInput);
					cardComparison.getFinalAnswer();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnCalculate.setBounds(5, 326, 64, 25);
		btnCalculate.setText("Calculate!");
		
		
	}
}
