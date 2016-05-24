package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
    @FXML
	private TextField txtIncome;
    @FXML
    private TextField txtExpenses;
    @FXML
    private TextField txtCreditScore;
    @FXML
    private TextField txtHouseCost;
    @FXML
    private ComboBox comLoanTerm;
    @FXML
    private Label txtMortagagePmt;
    @FXML
    private Button btnPayment;
    
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		LoanRequest lq = new LoanRequest();
			
		Object message = null;
		Action a = new Action(eAction.CalculatePayment);
		
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setdHouseCost(Integer.parseInt(txtHouseCost.getText()));
		lq.setiTerm(Integer.parseInt(comLoanTerm.getValue().toString()));
		
		a.setLoanRequest(lq);
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		if(lRequest.getdPayment() >= 0.0)
		txtMortagagePmt.setText(""+lRequest.getdPayment());
		else if(lRequest.getdPayment() < 0.0)
			txtMortagagePmt.setText("House Cost too high");
		else
			txtMortagagePmt.setText(lRequest.getErrorMessage());
	}
}
