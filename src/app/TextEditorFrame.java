package app;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextEditorFrame extends JFrame {
    private MyTextArea[] textAreas;
    TextEditorFrame(int activeParagraphIdx) {
        super("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        textAreas = new MyTextArea[paragraphNum.NUMBER_OF_PARAGRAPHS];

        for (int i = 0; i < paragraphNum.NUMBER_OF_PARAGRAPHS; i++) {
            textAreas[i] =
                TextAreaFactory.createTextArea(i, activeParagraphIdx);
        }

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        GridLayout layout =
            new GridLayout(paragraphNum.NUMBER_OF_PARAGRAPHS, 1);
        layout.setVgap(0);
        panel.setLayout(layout);

        for (MyTextArea textArea : textAreas) {
            panel.add(textArea);
        }

        add(panel);

        setVisible(true);
    }
}