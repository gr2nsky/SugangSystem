package VIEW.TimeTablePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TimeTablePanel extends JPanel{
	
	JLabel title;
	MakeChunk mc;

	public TimeTablePanel() {
		setLayout(new BorderLayout());
		
		setTitle();
		add(title,"First");
		
		mc = new MakeChunk();
		add(mc, "Center");
	}
	
	public void setTitle() {
		title = new JLabel("Time Table");
		title.setHorizontalAlignment(0);
		title.setOpaque(true);
		title.setFont(new Font("굴림", 0, 40));
	}
}

