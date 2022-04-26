package app;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
public abstract class MyTextArea extends JTextArea {
    private int index;
    private String exchangeName;
    public MyTextArea(int index, int activeParagraphIdx) {
        super("collaborate.................");
        this.index = index;
        this.exchangeName = RabbitMQHelpers.getExchangeName(index);
        setEnabled(activeParagraphIdx == index);
        setFont(new Font("Serif", Font.ITALIC, 36));
       // setBorder( new LineBorder(Color.red,1));
        setBackground(Color.CYAN);
      
        attachListener();
    }

    public String getExchangeName() { return exchangeName; }

    protected abstract void attachListener();
}