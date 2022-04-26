package app;

import java.awt.Color;
import java.util.function.Consumer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ReadTextArea extends MyTextArea {
    ReadTextArea(int idx, int activeParagraphIdx) {
        super(idx, activeParagraphIdx);
        
        setForeground(Color.BLACK);
    }
    @Override
    protected void attachListener() {
        Consumer<String> messageConsumer = message -> {
            try {
                // remove content from document
                Document doc = getDocument();
                doc.remove(0, doc.getLength());
                // insert new content
                doc.insertString(0, message, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        };
        RabbitMQHelpers.readMessage(
            RabbitMQHelpers.createQueue(getExchangeName()), messageConsumer);
    }
}