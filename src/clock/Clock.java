package clock;
import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class Clock extends JFrame{
	private static final long serialVersionUID = 1L;
	int clockCentreX = 210;
	int clockCentreY = 150;
	int clockDiameter = 350;
	int hourLength = 85, minuteLength = 110, secondLength = 115;
	int hour,minute,second,deltaX,deltaY;
	final double pi = 3.14159;
	String period;
	public Clock(){
		super("Clock");
		Canvas c = new Canvas() {
			@Override
			public void paint(Graphics g) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				LocalDateTime ldt = LocalDateTime.now();
				hour = ldt.getHour();
				if(hour/12==1) {
					hour=hour%12;
					period="PM";
					g.setColor(Color.gray);
				}
				else {
					period ="AM";
					g.setColor(Color.yellow);
				}
				minute = ldt.getMinute();
				second = ldt.getSecond();
				
				//digital part of the clock
				String dateTime = ldt.format(dtf);
				g.setFont(new Font("Times New Roman",Font.BOLD,20));
				g.drawString("Date and Time: "+dateTime,200,70);
				//Analog part of the clock
				g.drawArc(clockCentreX, clockCentreY, clockDiameter, clockDiameter, 0, 360);
				for(int i=1;i<=12;i++) g.drawString(Integer.toString(i), clockCentreX+clockDiameter/2-5+(int)(145*Math.sin(i*pi/6)), clockCentreY+clockDiameter/2+7-(int)(145*Math.cos(i*pi/6)));
				//second hand
				deltaX = (int)(secondLength*Math.sin(pi*second/30));
				deltaY= -(int)(secondLength*Math.cos(pi*second/30));
				g.drawLine(clockCentreX+clockDiameter/2, clockCentreY+clockDiameter/2, clockCentreX+clockDiameter/2+deltaX, clockCentreY+clockDiameter/2+deltaY);
				//minute hand
				deltaX = (int)(minuteLength*Math.sin(pi*(minute+(double)second/60)/30));
				deltaY= -(int)(minuteLength*Math.cos(pi*(minute+(double)second/60)/30));
				g.drawLine(clockCentreX+clockDiameter/2, clockCentreY+clockDiameter/2, clockCentreX+clockDiameter/2+deltaX, clockCentreY+clockDiameter/2+deltaY);
				//hour hand
				deltaX = (int)(hourLength*Math.sin(pi*(hour+(double)minute/60)/6));
				deltaY= -(int)(hourLength*Math.cos(pi*(hour+(double)minute/60)/6));
				g.drawLine(clockCentreX+clockDiameter/2, clockCentreY+clockDiameter/2, clockCentreX+clockDiameter/2+deltaX, clockCentreY+clockDiameter/2+deltaY);
			}
		};
		c.setBackground(Color.black);
		add(c);
		setSize(800,600);
		setVisible(true);
		new Timer(1000,e->c.repaint()).start();
	}
	
	public static void main(String[]args) {
		new Clock();
	}
}