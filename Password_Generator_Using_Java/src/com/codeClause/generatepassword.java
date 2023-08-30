package com.codeClause;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class generatepassword extends JFrame
{
	private JTextField passwordField;
	private JButton generateButton;
	private JTextField sizeField;
	private JButton copyButton;
	
	private static final String LOWERCASE_CHARACTERS = "qwertyuiopasdfghjklzxcvbnm";
	private static final String UPPERCASE_CHARACTERS = "QWERTYUIOPASDFGHJKLZXCVBNM";
	private static final String NUMBERS = "0123456789";
	private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-={}[]:<>?/";
	
	public generatepassword()
	{
		copyButton = new JButton("Copy");
		copyButton.setForeground(Color.magenta);
		copyButton.setFont(new Font("Arial",Font.BOLD,20));
		
		copyButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                String text =passwordField.getText();
                StringSelection stringSelection = new StringSelection(text);
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(generatepassword.this, "Text copied to clipboard!");
            }
        });
		
		setTitle("Password Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		passwordField = new JTextField(20);
		passwordField.setEditable(false);
		
		generateButton = new JButton("Generate");
		generateButton.setPreferredSize(new Dimension(100,100));
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setPreferredSize(new Dimension(300,30));
		
		
		add(buttonPanel,BorderLayout.CENTER);
		
		sizeField = new JTextField(3);
		
		
		generateButton.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int passwordLength =Integer.parseInt(sizeField.getText());
				String password = generatePassword(passwordLength);
				passwordField.setText(password);
			}
		});
		
		add(new JLabel("Enter size of Password"));
		add(sizeField);
		add(generateButton);
		add(passwordField);
		add(copyButton);
		
		pack();
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
		
    }
    
    // Moved outside the constructor and made static
    private static String generatePassword(int length)
    {
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        
        StringBuilder characters = new StringBuilder();
        characters.append(LOWERCASE_CHARACTERS);
        characters.append(UPPERCASE_CHARACTERS);
        characters.append(NUMBERS);
        characters.append(SPECIAL_CHARACTERS);
        
        for(int i=0;i<length;i++)
        {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }
        
        return password.toString();
    }
    
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				new generatepassword();
			}
		});
    }
}
