package Model;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

public class Status extends JPanel {
    
    
    
    public Status(Object[][] rows,Object[] columns) {
        
        setLayout(new BorderLayout());
        JTable table =new JTable(rows,columns );
        JTableHeader header = table.getTableHeader();
        add(header, BorderLayout.NORTH);
        add(table,BorderLayout.CENTER);
        
    }

   int y=200;
    protected void paintComponent(Graphics g) {
//        y=10;
//         Font font=new Font("TimesRoman", Font.BOLD, 14);
//         g.setFont(font);         
//        for(String s:status){
//        g.setColor(Color.red); 
//        y=y+20;
//        g.drawString(s, 50,y%(200+(status.length+1)*20));                   
        
    }
        }
//    }

    
