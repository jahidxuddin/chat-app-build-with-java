package de.ju.client.gui.components;

import javax.swing.*;
import java.awt.*;

public class RoomTitle extends JPanel {
    public RoomTitle() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(0, 50));
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(28, 28, 36)));
        this.setLayout(new BorderLayout());
        this.setupTitleText();
    }

    private void setupTitleText() {
        JLabel titleLabel = new JLabel("LIVE CHAT");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(
                titleLabel.getFont().getName(),
                Font.ITALIC, 20
                )
        );

        this.add(titleLabel, BorderLayout.CENTER);
    }
}
