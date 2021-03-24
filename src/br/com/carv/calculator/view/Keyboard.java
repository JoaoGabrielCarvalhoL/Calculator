package br.com.carv.calculator.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.com.carv.calculator.model.Memory;

@SuppressWarnings("serial")
public class Keyboard extends JPanel implements ActionListener
{
	private final Color COLOR_DARK_GREY = new Color(68,68,68);
	private final Color COLOR_LIGTH_GREY = new Color(99,99,99);
	private final Color COLOR_ORANGE = new Color(242,163,60);
	
	public Keyboard()
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(layout);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridy=0;
		c.gridx =0;
		
		c.gridwidth = 3;
		addButton("AC", COLOR_DARK_GREY, c, 0, 0);
		c.gridwidth = 1;
		addButton("/", COLOR_ORANGE, c, 3, 0);
		
		addButton("7", COLOR_LIGTH_GREY, c, 0, 1);
		addButton("8", COLOR_LIGTH_GREY, c, 1, 1);
		addButton("9", COLOR_LIGTH_GREY, c, 2, 1);
		addButton("*", COLOR_ORANGE, c, 3, 1);
		
		addButton("4", COLOR_LIGTH_GREY, c, 0, 2);
		addButton("5", COLOR_LIGTH_GREY, c, 1, 2);
		addButton("6", COLOR_LIGTH_GREY, c, 2, 2);
		addButton("-", COLOR_ORANGE, c, 3, 2);
		
		addButton("1", COLOR_LIGTH_GREY, c, 0, 3);
		addButton("2", COLOR_LIGTH_GREY, c, 1, 3);
		addButton("3", COLOR_LIGTH_GREY, c, 2, 3);
		addButton("+", COLOR_ORANGE, c, 3, 3);
		
		c.gridwidth = 2;
		addButton("0", COLOR_LIGTH_GREY, c, 0, 4);
		c.gridwidth = 1;
		addButton(",", COLOR_LIGTH_GREY, c, 2, 4);
		addButton("=", COLOR_ORANGE, c, 3, 4);
		
}

	private void addButton(String text, Color color, GridBagConstraints c, int x, int y) 
	{
		c.gridx = x;
		c.gridy =y;
		Button button = new Button(text, color);
		button.addActionListener(this);
		add(button, c);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() instanceof JButton)
		{
			JButton button = (JButton) e.getSource();
			Memory.getInst().processComand(button.getText());
			
		}
		
		
	}

	
}
