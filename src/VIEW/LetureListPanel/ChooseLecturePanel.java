package VIEW.LetureListPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import MODEL.data.Lecture;
import MODEL.mgr.Manager;
import VIEW.TimeTablePanel.MakeChunk;

public class ChooseLecturePanel extends JPanel
				implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    JFrame frame;
    JTable table = null;
    int selectedIndex = -1;
    static DefaultTableModel tableModel;
    public ArrayList<Lecture> userLecMgr;
    
    public ChooseLecturePanel() {
    	super(new BorderLayout());
    	songTableInit();
    	JPanel pane = makeBottomPane();
        add(pane, BorderLayout.PAGE_END);
    }
    void songTableInit() {
    	userLecMgr = MODEL.data.User.getManager();
		//dept 이수구분, name 과목이름 ,grade학년, lecNum 과목번호 ,professor담당교수, point 학점 ,day 요일, ,time 시간 ,room 강의실
        final String[] columnNames = {"이수구분", "과목이름", "학년", "과목번호", "담당교수", "학점", "시간", "강의실"};
    	tableModel = new DefaultTableModel(columnNames, 0);
    	
    	for(Lecture t : userLecMgr) 
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
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        JButton buttons = new JButton("삭제");
    	buttons.addActionListener(this);
        bottom.add(buttons);
        pane.add(bottom);
        return pane;
    }
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {
        	selectedIndex = lsm.getMinSelectionIndex();
        	System.out.println("수강신청목록에서 선택된 객체 인덱스 : " + selectedIndex);
        	System.out.println("수강신청목록 선택된 객체 내용 : "+CONTROLLER.Main.getUser().lecList.get(selectedIndex).toString());
        }
    }
    //삭제 - 추가 반복시 arraylist와 선택된 data의 index가 일치하지 않는 현상 -> 과목번호 검색을 통한 객체 삭제로 방식을 수정
    public void actionPerformed(ActionEvent e) {
    	DefaultTableModel data = (DefaultTableModel)(table.getModel());
    	if (e.getActionCommand().equals("삭제")) {
    		System.out.println("삭제버튼 클릭");
           	System.out.println("수강신청목록에서 삭제된 객체 인덱스 : " + selectedIndex);
        	System.out.println("수강신청목록 삭제된 객체 내용 : "+CONTROLLER.Main.getUser().lecList.get(selectedIndex).toString());
        	System.out.println("현재수강신청목록 : ");
        	for(Lecture l : CONTROLLER.Main.getUser().lecList) {
        		System.out.println(l.name);
        	}
			data.removeRow(selectedIndex);
			MakeChunk.delChunkSet(CONTROLLER.Main.getUser().lecList.get(selectedIndex));
			CONTROLLER.Main.getUser().removeAt(selectedIndex);
    	}
    }
    
	static void addedLeture(Lecture source) {
		if( source == null) {
			System.out.println("객체가 빈체로 옮겨왔습니다.");
		}
    	System.out.println("추가할 객체 내용 : "+source.toString());
    	CONTROLLER.Main.getUser().lecList.add(source);
		tableModel.addRow(source.getData());
		MakeChunk.makeChunkSet(source);
		}
}