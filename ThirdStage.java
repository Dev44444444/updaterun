package Main;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;

public class ThirdStage extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel; 
    private JProgressBar progressBar;
    private int currentProgress = 0; // Track current progress
    private int score = 0;

        public ThirdStage() {
        	loadProgress();
            setTitle("CODEX");
            setSize(816, 590);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            add(createTopPanel(), BorderLayout.NORTH);

            mainPanel = new JPanel(new BorderLayout());
            add(mainPanel, BorderLayout.CENTER);

            switchToPanel(introduction());

            applyHandCursor(introduction());
            applyHandCursor(HtmlQuizPanel1());
            applyHandCursor(finishPanel(score));
        }

        private JPanel createTopPanel() {
            JPanel topPanel = new JPanel(new BorderLayout());
            JPanel topBar = new JPanel(new BorderLayout());
            topBar.setBackground(new Color(245, 245, 255));

            JButton closeButton = new JButton("\u274C");
            closeButton.setBorderPainted(false);
            closeButton.setFocusable(false);
            closeButton.setContentAreaFilled(false);

            closeButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(
                    null, "Are you sure you want to quit?",
                    "Confirm Exit", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE
                );
                if (response == JOptionPane.OK_OPTION) {
                    home main;
					try {
						main = new home();
						main.setVisible(true);
						dispose();
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                }
            });

            topBar.add(closeButton, BorderLayout.WEST);

            progressBar = new JProgressBar(0, 11);
            progressBar.setValue(currentProgress);
            progressBar.setStringPainted(false);
            progressBar.setForeground(new Color(50, 150, 255));
            progressBar.setPreferredSize(new Dimension(816, 10));

            topPanel.add(topBar, BorderLayout.NORTH);
            topPanel.add(progressBar, BorderLayout.SOUTH);

            return topPanel;
        }
    


    private void switchToPanel1(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();

        updateProgress();
    }

    private void updateProgress() {
        if (currentProgress < 10) {
            currentProgress++;
            progressBar.setValue(currentProgress);
            
            SwingUtilities.invokeLater(() -> {
                progressBar.repaint();
                progressBar.revalidate();
            });
        }
    }
    
    private void applyHandCursor(Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                component.setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else if (component instanceof Container) {
                applyHandCursor((Container) component); 
            }
        }
    }

    

    
    private JPanel introduction() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        JPanel animatedLettersPanel = new JPanel();
        animatedLettersPanel.setBackground(Color.WHITE);
        animatedLettersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0)); 

        JLabel welcomeText = new JLabel(
            "<html><center><h1>『 Adding Text & Headings in HTML 』</h1></center></html>",
            SwingConstants.CENTER
        );
        welcomeText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        centerPanel.add(animatedLettersPanel, BorderLayout.NORTH);
        centerPanel.add(welcomeText, BorderLayout.CENTER);
        panel.add(centerPanel, BorderLayout.CENTER);

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(new Color(114, 46, 209));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.addActionListener(e -> switchToPanel1(Explanation()));

        panel.add(continueButton, BorderLayout.SOUTH);
        applyHandCursor(panel);

        return panel;
    }

    private JPanel Explanation() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 

        JLabel welcomeText = new JLabel(
            "<html><div style='text-align: justify; width: 400px;'>"
            + "In <b>HTML</b>, text content is structured using headings and paragraph elements to create a clear and organized webpage.<br><br>"
            + "<div style='font-size: 16px;'><b>1. Headings in HTML</b></div><br>"
            + "HTML provides six levels of headings, from &lt;h1&gt; to &lt;h6&gt;, with &lt;h1&gt; being the largest and most important and &lt;h6&gt; the smallest.<br><br>"
            + "</div></html>",
            SwingConstants.CENTER
        );
        welcomeText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Explanation Text to Center Panel
        centerPanel.add(welcomeText);

        // Continue Button
        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(new Color(114, 46, 209));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Ensure ExamplePanel() exists or replace with the correct panel switch method
        continueButton.addActionListener(e -> switchToPanel1(ExamplePanel()));  

        // Add Components
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel ExamplePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(245, 250, 255));

        // Title Label
        JLabel lblTitle = new JLabel("Different Headings in HTML:", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(new EmptyBorder(20, 20, 10, 0));
        contentPanel.add(lblTitle);

        // Add JSeparator
        JSeparator separator = new JSeparator();
        separator.setBorder(new EmptyBorder(5, 0, 5, 0));
        contentPanel.add(separator);

        // HTML Example using JLabel instead of JTextArea
        JLabel txtExample = new JLabel(
            "<html><body style='width: 300px; text-align: left;'>"
            + "<h1>&lt;h1&gt;Main Heading&lt;/h1&gt;</h1>"
            + "<h2>&lt;h2&gt;Subheading&lt;/h2&gt;</h2>"
            + "<h3>&lt;h3&gt;Smaller Subheading&lt;/h3&gt;</h3>"
            + "<h4>&lt;h4&gt;Section Title&lt;/h4&gt;</h4>"
            + "<h5>&lt;h5&gt;Subsection Title&lt;/h5&gt;</h5>"
            + "<h6><b>&lt;h6&gt;Smallest Heading&lt;/h6&gt;</b></h6>"
            + "</body></html>"
        );
        txtExample.setFont(new Font("SansSerif", Font.PLAIN, 16));
        txtExample.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPanel.add(txtExample);

        // Continue Button
        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(new Color(114, 46, 209));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // Ensure HtmlQuizPanel1() exists before calling
        continueButton.addActionListener(e -> switchToPanel1(HtmlQuizPanel1()));

        // Add Components to Panel
        panel.add(contentPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        return panel;
    }


    class RoundedBorder extends AbstractBorder {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int radius;
        private Color color;
        private int thickness;

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius + 1;
            return insets;
        }
    }

    private JPanel HtmlQuizPanel1() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
            "<html><center>What is the largest heading in HTML?</center>"
            + "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        );
        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h3&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h1&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h4&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h6&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == pTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel2());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }

    
    
    private JPanel HtmlQuizPanel2() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html><p>What is the correct tag for heading 1?</p><br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;HE1&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;heading1&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h1/&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h1&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == hlTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel3());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }


    private JPanel HtmlQuizPanel3() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html><p>What is the Subheading in HTML?</p><br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h3&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h8&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h2&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h2/&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == iTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel4());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }
    
    
    
    private JPanel HtmlQuizPanel4() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html><p>What is the smallest heading in HTML?</p><br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h6&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h8&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h1&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;heading6&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == hTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel5());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }
    
    private JPanel HtmlQuizPanel5() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html>What is the Section Title in HTML<br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h4/&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h4&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;h2&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;/heading2&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == pTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel6());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }
    
    private JPanel HtmlQuizPanel6() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html><p>How many different heading levels are available in HTML?</p><br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>3</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>10</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>8</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>6</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == hlTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(Explanation2());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////
    
    private JPanel Explanation2() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); 

        JLabel welcomeText = new JLabel(
            "<html><div style='text-align: justify; width: 400px;'>"
            + "In <b>HTML</b>, text content is structured using headings and paragraph elements to create a clear and organized webpage.<br><br>"
            + "<div style='font-size: 16px;'><b>2. Adding Paragraph Text</b></div> <br>"
            + "For normal text, the <b>&lt;p&gt;</b> (paragraph) tag is used. This tag helps in structuring content into readable sections.<br><br>"
            + "</div></html>",
            SwingConstants.CENTER
        );
        welcomeText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Explanation Text to Center Panel
        centerPanel.add(welcomeText);

        // Continue Button
        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(new Color(114, 46, 209));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        continueButton.setFocusPainted(false);
        continueButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Ensure ExamplePanel() exists
        continueButton.addActionListener(e -> switchToPanel1(HtmlQuizPanel7()));  

        // Add Components
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        return panel;
    }
    
    private JPanel HtmlQuizPanel7() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html>What does the <b>&lt;p&gt;</b> tag represent in HTML?<br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>primary</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>password</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>Popup</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>paragraph</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == hlTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(HtmlQuizPanel8());
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }
    
    private JPanel HtmlQuizPanel8() {
    	JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 248, 250));

        JLabel questionLabel = new JLabel(
        	    "<html>What is the correct tag of paragraph in HTML?<br>" +
        	    "<span style='color:#5865F2; font-size:12px;'>ⓘ Tap the correct answer</span></html>"
        	);

        questionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Define borders
        RoundedBorder defaultBorder = new RoundedBorder(15, Color.LIGHT_GRAY, 2);
        RoundedBorder selectedBorder = new RoundedBorder(15, Color.BLUE, 2);
        RoundedBorder correctBorder = new RoundedBorder(15, Color.GREEN, 2);
        RoundedBorder incorrectBorder = new RoundedBorder(15, Color.RED, 2);

        // Define buttons
        JButton hTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;paragraph&gt; &lt;/paragraph&gt;</b></center</html>");
        JButton pTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;p&gt; &lt;/p&gt;</b></center</html>");
        JButton iTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;/p&gt; &lt;/p&gt;</b></center</html>");
        JButton hlTagButton = new JButton("<html><center><b style='font-size:12px;'>&lt;p&gt; &lt;p&gt;</b></center</html>");

        JButton[] answerButtons = { hTagButton, pTagButton, iTagButton, hlTagButton };

        for (JButton button : answerButtons) {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Monospaced", Font.BOLD, 14));
            button.setFocusPainted(false);
            button.setBorder(defaultBorder);
        }

        JButton continueButton = new JButton("CONTINUE");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217)); 
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        continueButton.setEnabled(false); 

        // Hover effect
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(130, 56, 230)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(new Color(114, 46, 217)); 
            }
        });

        // State variables
        final boolean[] isCorrectAnswer = { false };
        final boolean[] answerRevealed = { false }; 
        final JButton[] selectedButton = { null };

        // Action listener for answer selection
        ActionListener selectionListener = e -> {
            JButton clickedButton = (JButton) e.getSource();
            selectedButton[0] = clickedButton;
            isCorrectAnswer[0] = (clickedButton == pTagButton);

            // Reset borders and highlight selection
            for (JButton button : answerButtons) {
                button.setBorder(defaultBorder);
            }
            clickedButton.setBorder(selectedBorder);

            continueButton.setEnabled(true); 
            answerRevealed[0] = false; 
        };

        // Attach listener to all buttons
        for (JButton button : answerButtons) {
            button.addActionListener(selectionListener);
        }

        // Continue button action
        continueButton.addActionListener(e -> {
            if (!answerRevealed[0]) { 
                // First click reveals the answer
                if (selectedButton[0] != null) {
                    if (isCorrectAnswer[0]) {
                        selectedButton[0].setBorder(correctBorder);
                        score++;
                    } else {
                        selectedButton[0].setBorder(incorrectBorder);
                    }
                    answerRevealed[0] = true; 
                    
                    hTagButton.setEnabled(false);
                    pTagButton.setEnabled(false);
                    iTagButton.setEnabled(false);
                    hlTagButton.setEnabled(false);
                }
            } else {
                // Second click proceeds to the next panel
                switchToPanel1(finishPanel(score));
            }
        });

        // Organize layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 248, 250));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (JButton button : answerButtons) {
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 250));
        topPanel.add(questionLabel, BorderLayout.NORTH);
        topPanel.add(separator, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }

    private JPanel finishPanel(int score) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.add(Box.createVerticalStrut(10));

        // Determine feedback message, color, and icon based on the score
        String titleText;
        Color titleColor;
        if (score == 8) {
            titleText = "🏆 100% - Perfect Lesson!";
            titleColor = new Color(34, 177, 76); // Green
        } else if (score >= 7) {
            titleText = "🌟 98% Great Job! Almost Perfect!";
            titleColor = new Color(0, 102, 204); // Blue
        } else if (score >= 5) {
            titleText = "👍 95% Well Done! Keep Going!";
            titleColor = new Color(255, 140, 0); // Orange
        } else {
            titleText = "💡 88% Good Effort! Try Again for a Higher Score!";
            titleColor = new Color(200, 0, 0); // Red
        }

        JLabel welcomeText = new JLabel("<html><center><h1><b>" + titleText + "</b></h1></center></html>", SwingConstants.CENTER);
        welcomeText.setFont(new Font("SansSerif", Font.BOLD, 22));
        welcomeText.setForeground(titleColor);
        welcomeText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcomeText);

        panel.add(centerPanel, BorderLayout.CENTER);

        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(new Color(114, 46, 217));
        continueButton.setOpaque(true);
        continueButton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        continueButton.setFocusPainted(false);
        continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Make the button fill the width of the panel
        continueButton.setPreferredSize(new Dimension(panel.getWidth(), 50));

        continueButton.addActionListener(e -> {
            try {
                home mainHome = new home();
                mainHome.unlockNextLesson();
                mainHome.setVisible(true);
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            }

            Window window = SwingUtilities.getWindowAncestor(panel);
            if (window != null) {
                window.dispose();
            }
        });

        // Add directly to SOUTH of the panel
        panel.add(continueButton, BorderLayout.SOUTH);

        applyHandCursor(panel);

        return panel;
    }

    
    private void loadProgress() {
        try (BufferedReader reader = new BufferedReader(new FileReader("progress.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                currentProgress = Integer.parseInt(line);
            }
        } catch (IOException e) {
            currentProgress = 0; 
        }
    }


    private void switchToPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThirdStage().setVisible(true));
    }
}