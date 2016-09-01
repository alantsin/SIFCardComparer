import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CardComparisonWindow {

	protected Shell shell;
	private Label lblAttribute;
	private Combo comboAttribute;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SIF Card Comparison Application");
		shell.setLayout(null);
		
		Label lblCard1ID = new Label(shell, SWT.NONE);
		lblCard1ID.setBounds(5, 8, 48, 15);
		lblCard1ID.setText("Card 1 ID");
		
		Spinner spinnerCard1 = new Spinner(shell, SWT.BORDER);
		spinnerCard1.setMaximum(1000);
		spinnerCard1.setMinimum(1);
		spinnerCard1.setBounds(103, 5, 56, 22);
		
		Label lblCard2ID = new Label(shell, SWT.NONE);
		lblCard2ID.setBounds(5, 35, 48, 15);
		lblCard2ID.setText("Card 2 ID");
		
		Spinner spinnerCard2 = new Spinner(shell, SWT.BORDER);
		spinnerCard2.setMaximum(1000);
		spinnerCard2.setMinimum(1);
		spinnerCard2.setBounds(103, 32, 56, 22);
		
		lblAttribute = new Label(shell, SWT.NONE);
		lblAttribute.setBounds(5, 63, 47, 15);
		lblAttribute.setText("Attribute");
		
		comboAttribute = new Combo(shell, SWT.READ_ONLY);
		comboAttribute.setBounds(103, 59, 56, 23);
		
		comboAttribute.add("Smile");
		comboAttribute.add("Pure");
		comboAttribute.add("Cool");
		
		Label lblNoteCount = new Label(shell, SWT.NONE);
		lblNoteCount.setBounds(5, 90, 62, 15);
		lblNoteCount.setText("Note Count");
		
		Spinner spinnerNoteCount = new Spinner(shell, SWT.BORDER);
		spinnerNoteCount.setMaximum(1000);
		spinnerNoteCount.setMinimum(1);
		spinnerNoteCount.setBounds(103, 87, 56, 22);
		
		Label lblCenterSkill = new Label(shell, SWT.NONE);
		lblCenterSkill.setBounds(5, 118, 59, 15);
		lblCenterSkill.setText("Center Skill");
		
		Combo comboCenterSkill = new Combo(shell, SWT.READ_ONLY);
		comboCenterSkill.setBounds(103, 114, 159, 23);
		
		comboCenterSkill.add("9% Smile");
		comboCenterSkill.add("9% Pure");
		comboCenterSkill.add("9% Cool");
		comboCenterSkill.add("12% Smile based on Pure");
		comboCenterSkill.add("12% Smile based on Cool");
		comboCenterSkill.add("12% Pure based on Smile");
		comboCenterSkill.add("12% Pure based on Cool");
		comboCenterSkill.add("12% Cool based on Smile");
		comboCenterSkill.add("12% Cool based on Pure");
		
		Label lblMemberModifier = new Label(shell, SWT.NONE);
		lblMemberModifier.setBounds(5, 146, 93, 15);
		lblMemberModifier.setText("Member Modifier");
		
		Combo comboMemberModifier = new Combo(shell, SWT.READ_ONLY);
		comboMemberModifier.setBounds(103, 142, 159, 23);
		
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
		
		Label lblCardSi = new Label(shell, SWT.NONE);
		lblCardSi.setBounds(5, 174, 70, 15);
		lblCardSi.setText("Card 1 SI Skill");
		
		Combo comboCard1SISkill = new Combo(shell, SWT.READ_ONLY);
		comboCard1SISkill.setBounds(103, 170, 159, 23);
		
		comboCard1SISkill.add("250% Score Up Charm");
		comboCard1SISkill.add("270x HP Recover Heel");
		comboCard1SISkill.add("25% Stat Up PL Trick");
		
		Label lblCardSi_1 = new Label(shell, SWT.NONE);
		lblCardSi_1.setBounds(5, 202, 70, 15);
		lblCardSi_1.setText("Card 2 SI Skill");
		
		Combo comboCard2SISkill = new Combo(shell, SWT.READ_ONLY);
		comboCard2SISkill.setBounds(103, 198, 159, 23);
		
		comboCard2SISkill.add("250% Score Up Charm");
		comboCard2SISkill.add("270x HP Recover Heel");
		comboCard2SISkill.add("25% Stat Up PL Trick");
		
		Label lblResult = new Label(shell, SWT.NONE);
		lblResult.setBounds(317, 145, 107, 76);
		
		Button btnIdolized1 = new Button(shell, SWT.CHECK);
		btnIdolized1.setBounds(169, 8, 93, 16);
		btnIdolized1.setText("Idolized");
		
		Button btnIdolized2 = new Button(shell, SWT.CHECK);
		btnIdolized2.setBounds(169, 34, 93, 16);
		btnIdolized2.setText("Idolized");
		
		Label lblTimes = new Label(shell, SWT.NONE);
		lblTimes.setBounds(169, 90, 48, 15);
		lblTimes.setText("Time (s)");
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setMaximum(600);
		spinner.setMinimum(80);
		spinner.setSelection(120);
		spinner.setBounds(218, 87, 44, 22);
		
		Label lblLogo = new Label(shell, SWT.NONE);
		lblLogo.setAlignment(SWT.RIGHT);
		lblLogo.setImage(SWTResourceManager.getImage(CardComparisonWindow.class, "/img/900RoundHonoka.png"));
		lblLogo.setBounds(301, 0, 133, 130);
		
		Button btnCompare = new Button(shell, SWT.NONE);
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				lblResult.setText("Help");
			}
		});
		
		btnCompare.setBounds(5, 226, 64, 25);
		btnCompare.setText("Compare!");
		
	}
}
