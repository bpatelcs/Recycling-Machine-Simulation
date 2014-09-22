package Model;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Graph extends BarChart {

  
    
    public Graph(int[] weight,String[] names) {
        super(names,weight);
        Color[] colors=new Color[]{Color.red,Color.BLUE,Color.GREEN};
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.white);                                       
        
        int i=0;
        for(int w:weight){
           addBar(colors[i], w+2);
            System.out.println("one");
            i++;
        }
        repaint();
        setVisible(true);

    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);                                             
    }
}

class BarChart extends JPanel {
    int j=0;
     volatile String[] name;
    volatile int[] weight;
    
    private Map<Color, Integer> bars =
            new LinkedHashMap<Color, Integer>();
   
    public BarChart(String[] names, int[] weight) {
        setBackground(Color.white);
        
        setPreferredSize(new Dimension(500, 500));
        this.name=names;
        this.weight=weight;

    }

   
    public void addBar(Color color, int value) {
        
        
        bars.put(color, value);                
        repaint();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        // determine longest bar
        
        int max = Integer.MIN_VALUE;
        for (Integer value : bars.values()) {
            max = Math.max(max, value);
        }
        int width;
//        if(bars.size()!=1){
//         width = (getWidth() / bars.size()) - 100;
//        }
//        else{
            width=150;
//        }
        int x = 1;
        setBackground(Color.white);
        for (Color color : bars.keySet()) {
            
            int value = bars.get(color);
            int height = (int) ((getHeight() - 5) * ((double) value / max));
            g.setColor(color);
            g.fillRect(x, getHeight() - height, width, height);
            g.setColor(color.white);
            
            System.out.println(name[j]+" "+weight[j]);
            Font font=new Font("TimesRoman", Font.BOLD, 14);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);
            String s=name[j]+" "+weight[j]+" K.g.";
            int stringWidth=metrics.stringWidth(s);
            g.drawString(s, x+((width-(stringWidth))/2), 470);
           
            //System.out.println("x="+x+" width="+width);
            g.setColor(Color.black);
            g.drawRect(x, getHeight() - height, width, height);
            x += (width + 2);
            j=((j+1)%(name.length));
            
        }
    }

    
    public Dimension getPreferredSize() {
        return new Dimension(bars.size() * 10 + 2, 50);
    }
}
