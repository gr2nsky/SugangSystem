package VIEW.LetureListPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MODEL.data.Lecture;

public class SearchPanel extends JPanel implements ActionListener{

	JLabel pro;
	JLabel lec;
	JButton search;
	JButton rec;
	
    JTextField lecName;
    JTextField proName;
	
    JComboBox<String> combo1;
    JComboBox<String> combo2;
    JComboBox<String> combo3;
    JComboBox<String> combo4;
	
	String grade[] = {"전체", "1학년", "2학년", "3학년", "4학년"};
	String nec[] = {"전체", "전필", "선필"};
	String date[] = {"전체", "월","화","수","목","금"};
	String time[] = {"전체", "123","678"};

	public SearchPanel() {
		
		lec = new JLabel("과목명");
		add(lec);
		lecName = new JTextField(8);
		add(lecName);
        
		pro = new JLabel("교수명");
		add(pro);
        proName = new JTextField(5);
        add(proName);	
		
		combo1 = new JComboBox<String>(grade);
		add(combo1);
		combo2 = new JComboBox<String>(nec);
		add(combo2);
		combo3 = new JComboBox<String>(date);
		add(combo3);
		combo4 = new JComboBox<String>(time);
		add(combo4);
		
		search = new JButton("검색");
		search.addActionListener(this);
		add(search);
		rec = new JButton("추천");
		rec.addActionListener(this);
		add(rec);
	
	}
	
    public void actionPerformed(ActionEvent e) {
	   	 if (e.getActionCommand().equals("검색")) {
	   		 System.out.println("검색버튼 클릭");
	   	 }
	   	 if (e.getActionCommand().equals("추천")) {
	   		 System.out.println("추천버튼 클릭");
	   	 }
   	 }
	
	public JPanel getSearchPanel() {
		return this;
	}

}
