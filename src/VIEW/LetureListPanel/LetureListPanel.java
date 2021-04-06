package VIEW.LetureListPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import CONTROLLER.Main;
import CONTROLLER.addControl;
import MODEL.data.Lecture;
import MODEL.mgr.Manager;

public class LetureListPanel extends JPanel
				implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    Main main;
    addControl ac;
    public Manager<Lecture> lecMgr;
    JFrame frame;
    JTable table = null;
    int selectedIndex = -1;
    
    public LetureListPanel(Main main) {
    	super(new BorderLayout());
    	this.main = main;
    	this.ac = main.ac;
    	songTableInit();
    	JPanel pane = makeBottomPane();
        add(pane, BorderLayout.PAGE_END);
    }
    void songTableInit() {
    	lecMgr = CONTROLLER.Main.lecMgr.getManager();
		//dept 이수구분, name 과목이름 ,grade학년, lecNum 과목번호 ,professor담당교수, point 학점 ,day 요일, ,time 시간 ,room 강의실
        final String[] columnNames = {"이수구분", "과목이름", "학년", "과목번호", "담당교수", "학점", "시간", "강의실"};
    	DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    	
    	for(Lecture t : lecMgr.getLecArr()) 
    		tableModel.addRow(t.getData());
    	
       	table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 100));
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);    	
    }
    
    JPanel makeBottomPane() {
    	JPanel pane = new JPanel();
    	pane.setLayout(new GridLayout(2, 1));
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        JButton buttons = new JButton("추가");
    	buttons.addActionListener(this);
        bottom.add(buttons);
        pane.add(bottom);
        return pane;
    }
    
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {
        	selectedIndex = lsm.getMinSelectionIndex();
        	//차후 인덱스번호 -> 수강번호로 정정요함
        	System.out.println("강의목록에서 선택된 객체 인덱스 : " + selectedIndex);
        	System.out.println("강의목록 선택된 객체 내용 : "+lecMgr.getIndexData(selectedIndex).toString());
        }
    }
    public void actionPerformed(ActionEvent e) {
    	 if (e.getActionCommand().equals("추가")) {
    		System.out.println("추가버튼 클릭");
    		Lecture source = (Lecture)lecMgr.getIndexData(selectedIndex);
    		int status = ac.selectLec(source.getlecNum());
    		switch (status){
    			case 0:
    				JOptionPane.showMessageDialog(null, "수강신청 실패:수강신청할 과목을 선택해 주세요");
    				break;
    			case 1:
    				JOptionPane.showMessageDialog(null, "수강신청 실패: 이미 수강신청한 과목입니다.");
    				break;
    			case 2:
    				JOptionPane.showMessageDialog(null, "수강신청 실패: 동일한 과목이 이미 선택되어 잇습니다.");
    				break;
    			case 3:
    				JOptionPane.showMessageDialog(null, "수강신청 실패: 시간이 겹치는 과목입니다.");
    				break;
    			case 4:
    				ChooseLecturePanel.addedLeture(source);
    				break;
    		}
    	} 
    }
}