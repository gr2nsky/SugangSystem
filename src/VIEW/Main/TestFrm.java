package VIEW.Main;
 
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import CONTROLLER.Main;
import VIEW.LetureListPanel.ChooseLecturePanel;
import VIEW.LetureListPanel.LetureListPanel;
import VIEW.LetureListPanel.SearchPanel;
import VIEW.Login.LoginInfo;
import VIEW.TimeTablePanel.TimeTablePanel;
 
public class TestFrm extends JFrame{
	
	Main main;
	
	JPanel left;
	JPanel leftTop;
	JPanel right;
	GridBagConstraints[] gbc;
	
	LoginInfo li;
	SearchPanel sp;
	LetureListPanel llp;
	ChooseLecturePanel clp;
	
    public TestFrm(Main main) {
    	this.main = main;
    	setLayout(new BorderLayout());
    	left = new JPanel();
    	right = new JPanel();
    	
    	makeLeftTop();
        makeLeft();
        add(left, BorderLayout.CENTER);
        
        setRight();
        add(right, BorderLayout.EAST);
        
        setTitle("수강신청 시스템");
        setSize(1400,800); // 600,800(1,4,3) : 800/800
    }

	public void makeLeftTop() {
    	leftTop = new JPanel(new  BorderLayout());
    	
    	li = new LoginInfo();
    	leftTop.add(li.getLoginInfoPanel(),"First");
    	
    	sp = new SearchPanel();
    	sp.setBorder(TitleBorderMaker("검색"));
    	leftTop.add(sp.getSearchPanel(), "Center");
    	
    }
    
    public void makeLeft() {
    	left.setLayout(new BorderLayout());
    	
    	left.add(leftTop, "First");
    	
    	llp = new LetureListPanel(main);
    	llp.setBorder(TitleBorderMaker("강의목록"));
    	left.add(llp, "Center");
    	
    	clp = new ChooseLecturePanel();
    	clp.setBorder(TitleBorderMaker("수강신청목록"));
    	left.add(clp, "Last");
    }
    
    public void setRight() {
    	right = new TimeTablePanel();
    }
    
    public Container getLeft() {
    	return left;
    }
    
    public TitledBorder TitleBorderMaker(String title) {
    	TitledBorder titledBorder = new TitledBorder(title);
    	titledBorder.setTitleJustification(TitledBorder.CENTER);
    	return titledBorder;
    }
}