package errors;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import parser.CTLParser;

public class DialogListener extends BaseErrorListener {
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		// TODO Auto-generated method stub
		List<String> stack = ((CTLParser) recognizer).getRuleInvocationStack();
		Collections.reverse(stack);
		StringBuilder buf = new StringBuilder();
		buf.append("rulestack:" + stack + "");
		buf.append("line" + line + ":" + charPositionInLine + "at" + offendingSymbol + ":" + msg);
		JDialog dialog = new JDialog();
		Container contentPane = dialog.getContentPane();
		contentPane.add(new JLabel(buf.toString()));
		contentPane.setBackground(Color.white);
		dialog.setTitle("Syntaxerror");
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

	}
}