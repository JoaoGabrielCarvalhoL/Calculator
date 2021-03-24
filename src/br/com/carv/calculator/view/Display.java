package br.com.carv.calculator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.com.carv.calculator.model.Memory;
import br.com.carv.calculator.model.MemoryObserver;

@SuppressWarnings("serial")
public class Display extends JPanel implements MemoryObserver
{
	
	private final JLabel label;
	
	public Display()
	{
		Memory.getInst().addObserver(this);
		
		setBackground(Color.GRAY);
		label = new JLabel(Memory.getInst().getCurrentText());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("courier", Font.PLAIN, 30));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 25));
		add(label);
		
	}

	@Override
	public void changedValue(String newValue) 
	{
		label.setText(newValue);
		
	}
}
