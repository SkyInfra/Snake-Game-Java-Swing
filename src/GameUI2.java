import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

class GameUI2 {
    JFrame mainMenu, leaderboardFrame, setupFrame, gameFrame, gameOverFrame;
    History history = new History();
    JTextArea leaderboardText;
    String playerName = "";
    String selectedMap = "Forest";
    String selectedDifficulty = "Medium";
    ImageIcon icon = new ImageIcon("C:\\Users\\User\\Documents\\2nd Semester\\OOP\\Snake Game\\Snake Game\\snake.png");
    int score = 0;

    public GameUI2() {
        createMainMenu();
    }

    void createMainMenu() {
    mainMenu = new JFrame("Snake Game - Main Menu");
    mainMenu.setSize(600, 400);
    mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainMenu.setLocationRelativeTo(null);

    
    mainMenu.setIconImage(icon.getImage());

    JPanel panel = new JPanel();
    panel.setBackground(new Color(34, 45, 65)); // Dark background
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel title = new JLabel("SNAKE GAME");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(new Font("Arial", Font.BOLD, 32));
    title.setForeground(Color.GREEN);
    title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

    JButton startButton = new JButton("Start Game");
    JButton leaderboardButton = new JButton("Leaderboard");
    JButton exitButton = new JButton("Exit Game"); // New Exit button

    // Style all buttons the same
    JButton[] buttons = { startButton, leaderboardButton, exitButton };
    for (JButton btn : buttons) {
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.GREEN);
        btn.setFocusPainted(false);
        btn.setMaximumSize(new Dimension(200, 40));
    }

    startButton.addActionListener(e -> {
        mainMenu.dispose();
        showSetupFrame();
    });

    leaderboardButton.addActionListener(e -> showLeaderboard());
    
    exitButton.addActionListener(e -> System.exit(0)); // Exit action

    panel.add(title);
    panel.add(startButton);
    panel.add(Box.createVerticalStrut(20));
    panel.add(leaderboardButton);
    panel.add(Box.createVerticalStrut(20));
    panel.add(exitButton); // Add Exit button

    mainMenu.add(panel);
    mainMenu.setVisible(true);
}

    void showLeaderboard() {
    leaderboardFrame = new JFrame("Leaderboard");
    leaderboardFrame.setSize(400, 350);
    leaderboardFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(34, 45, 65));
    panel.setLayout(new BorderLayout());

    leaderboardText = new JTextArea();
    leaderboardText.setEditable(false);
    leaderboardText.setFont(new Font("Monospaced", Font.PLAIN, 14));
    leaderboardText.setBackground(new Color(20, 25, 35));
    leaderboardText.setForeground(Color.GREEN);

    StringBuilder sb = new StringBuilder("Top 5 Scores:\n\n");
    for (History.Record r : history.getTopScores()) {
        sb.append(String.format("%-10s | %-6s | %-7s | %4d\n", r.name, r.map, r.difficulty, r.score));
    }

    leaderboardText.setText(sb.toString());
    JScrollPane scroll = new JScrollPane(leaderboardText);
    scroll.setBorder(BorderFactory.createEmptyBorder());

    panel.add(scroll, BorderLayout.CENTER);
    leaderboardFrame.add(panel);
    leaderboardFrame.setVisible(true);
}


    void showSetupFrame() {
    setupFrame = new JFrame("Game Setup");
    setupFrame.setSize(400, 300);
    setupFrame.setLocationRelativeTo(null);
    setupFrame.setLayout(new GridLayout(4, 1));

    setupFrame.setIconImage(icon.getImage());

    // Apply dark background
    Color bg = new Color(34, 45, 65);
    Color fg = Color.GREEN;

    JPanel difficultyPanel = new JPanel();
    difficultyPanel.setBackground(bg);
    difficultyPanel.setForeground(fg);

    JCheckBox low = new JCheckBox("Low");
    JCheckBox med = new JCheckBox("Medium", true);
    JCheckBox high = new JCheckBox("High");

    low.setForeground(fg); low.setBackground(bg);
    med.setForeground(fg); med.setBackground(bg);
    high.setForeground(fg); high.setBackground(bg);

    ButtonGroup group = new ButtonGroup();
    group.add(low); group.add(med); group.add(high);

    TitledBorder diffBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(fg),"Difficulty");
    diffBorder.setTitleColor(fg);
    difficultyPanel.setBorder(diffBorder);
    
    difficultyPanel.add(low); difficultyPanel.add(med); difficultyPanel.add(high);

    JPanel userPanel = new JPanel();
    userPanel.setBackground(bg);
    userPanel.setForeground(fg);

    TitledBorder userBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(fg),"Username");
    userBorder.setTitleColor(fg);
    userPanel.setBorder(userBorder);

    JLabel userLabel = new JLabel("Username:");
    userLabel.setForeground(fg);
    userPanel.add(userLabel);

    JTextField nameField = new JTextField(15);
    nameField.setBackground(bg);
    nameField.setForeground(fg);
    userPanel.add(nameField);


    JPanel mapPanel = new JPanel();
    mapPanel.setBackground(bg);
    mapPanel.setForeground(fg);
    JComboBox<String> mapBox = new JComboBox<>(new String[]{"Forest", "Desert", "Snow"});
    mapBox.setBackground(bg);
    mapBox.setForeground(fg);

    TitledBorder mapBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(fg),"Select Map");
    mapBorder.setTitleColor(fg);
    mapPanel.setBorder(mapBorder);
    mapPanel.add(mapBox);

    JButton start = new JButton("Start Game");
    start.setBackground(Color.BLACK);
    start.setForeground(fg);
    start.setFocusPainted(false);
    start.addActionListener(e -> {
        playerName = nameField.getText().isEmpty() ? "Player" : nameField.getText();
        selectedMap = (String) mapBox.getSelectedItem();
        selectedDifficulty = low.isSelected() ? "Low" : high.isSelected() ? "High" : "Medium";
        setupFrame.dispose();
        startGame();
    });

    setupFrame.add(difficultyPanel);
    setupFrame.add(userPanel);
    setupFrame.add(mapPanel);
    setupFrame.add(start);
    setupFrame.getContentPane().setBackground(bg);
    setupFrame.setVisible(true);
}


    void startGame() {

        score = 0;
        gameFrame = new JFrame("Snake Game - Playing");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(500, 520);
        gameFrame.setLocationRelativeTo(null);

        gameFrame.setIconImage(icon.getImage());

        // Choose map
        Location map;
        switch (selectedMap) {
            case "Snow":
                map = new SnowLocation(24, 23);
                break;
            case "Desert":
                map = new DesertLocation(24, 23);
                break;
            default:
                map = new ForestLocation(24, 23);
        }

        // Snake and food setup
        Snake snake;
        if (selectedDifficulty.equals("Low"))
            snake = new Snake(new Position(10, 10), Direction.RIGHT, 0.5);
        else if (selectedDifficulty.equals("Medium"))
            snake = new Snake(new Position(10, 10), Direction.RIGHT, 1);
        else 
            snake = new Snake(new Position(10, 10), Direction.RIGHT, 1.5);
        Food food = new Food();
        food.generate(map, snake);
        int[] foodCount = {0};
        int cellSize = 20;

        // Game panel setup
        JPanel gamePanel = new JPanel() {

            Image cactusImage = new ImageIcon("C:\\Users\\User\\Documents\\2nd Semester\\OOP\\Snake Game\\Snake Game\\Cactus img.jpg").getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Background color
                if (selectedMap.equals("Forest")) {
                    setBackground(new Color(34, 45, 65));
                    g.setColor(Color.GREEN);
                } else if (selectedMap.equals("Snow")) {
                    setBackground(new Color(220, 240, 255));
                    g.setColor(Color.BLUE);
                } else {
                    setBackground(new Color(255, 244, 200)); // Desert
                    g.setColor(new Color(184, 134, 11)); // Sand color
                }

                // Draw grid (optional)
                g.setColor(Color.GRAY);
                for (int i = 0; i < map.width; i++) {
                    for (int j = 0; j < map.height; j++) {
                        g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    }
                }

                // Draw food
                g.setColor(food.isBooster ? Color.MAGENTA : Color.RED);
                g.fillOval(food.position.x * cellSize, food.position.y * cellSize, cellSize, cellSize);


            // Draw circular rock obstacles
            g.setColor(new Color(110, 80, 60));

            for (Obstacle ob : map.obstacles) {
                int x = ob.position.x * cellSize;
                int y = ob.position.y * cellSize;

                //g.fillOval(x, y, cellSize, cellSize);
                g.drawImage(cactusImage, x, y, cellSize, cellSize, this);
            }




                // Draw snake
            for (int i = 0; i < snake.getBody().size(); i++) {
                Position pos = snake.getBody().get(i);
                int x = pos.x * cellSize;
                int y = pos.y * cellSize;

                if (i == 0) {
                    // Snake head - darker green
                    g.setColor(new Color(0, 100, 0));
                    g.fillRoundRect(x, y, cellSize, cellSize, 8, 7);

                    // Add eyes (white + black dot)
                    g.setColor(Color.WHITE);
                    g.fillOval(x + cellSize / 4, y + cellSize / 4, cellSize / 4, cellSize / 4);
                    g.fillOval(x + cellSize / 2, y + cellSize / 4, cellSize / 4, cellSize / 4);

                    g.setColor(Color.BLACK);
                    // x cordinate y cordinate 
                    g.fillOval(x + cellSize / 4 + 2, y + cellSize / 4 + 2, cellSize / 8, cellSize / 8);
                    g.fillOval(x + cellSize / 2 + 2, y + cellSize / 4 + 2, cellSize / 8, cellSize / 8);
                } else {
                    // Snake body - gradient green shades
                    g.setColor(new Color(34, 139, 34)); // Forest Green
                    g.fillRoundRect(x, y, cellSize, cellSize, 10, 10);
                }
            }

                }
        };

        gamePanel.setPreferredSize(new Dimension(400, 400));
        gamePanel.setBackground(Color.BLACK);

        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        gameFrame.setLayout(new BorderLayout());
        gameFrame.add(scoreLabel, BorderLayout.NORTH);
        gameFrame.add(gamePanel, BorderLayout.CENTER);
        gameFrame.setVisible(true);

        // Movement timer
        int delay = (int)(300 / snake.speed); 
        // Declare the timer first
        Timer[] timer = new Timer[1];

        // Then assign it
        timer[0] = new Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                snake.move();
                Position head = snake.getHeadPosition();

                if (map.isBlocked(head) || head.x < 0 || head.y < 0 || head.x >= map.width || head.y >= map.height
                    || snake.getBody().subList(1, snake.getBody().size()).contains(head)) {
                    ((Timer) e.getSource()).stop();
                    gameFrame.dispose();
                    history.addRecord(score, 5, playerName, selectedMap, selectedDifficulty);
                    showGameOver();
                    return;
                }

                if (head.equals(food.position)) {
                    snake.grow();
                    score += food.isBooster ? 50 : 10;
                    foodCount[0]++;
                    double newSpeed = snake.getSpeed() * 1.20;
                    snake.setSpeed(newSpeed);
                    int newDelay = (int)(300 / newSpeed);
                    timer[0].setDelay(newDelay);  // Use the array wrapper to access the variable
                    food.isBooster = food.isBoosterFood(foodCount[0]);
                    food.generate(map, snake);
                    scoreLabel.setText("Score: " + score);
                }

                gamePanel.repaint();
            }
        });

        // Start the timer
        timer[0].start();


        // Optional: arrow key controls

        gameFrame.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            Direction currentDir = snake.getDirection();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (currentDir != Direction.DOWN) {
                        snake.changeDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (currentDir != Direction.UP) {
                        snake.changeDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (currentDir != Direction.RIGHT) {
                        snake.changeDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (currentDir != Direction.LEFT) {
                        snake.changeDirection(Direction.RIGHT);
                    }
                    break;
            }
        }
    });
    gameFrame.setFocusable(true);
    gameFrame.requestFocusInWindow();

}


    void showGameOver() {
    gameOverFrame = new JFrame("Game Over");
    gameOverFrame.setSize(400, 300);
    gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gameOverFrame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(34, 45, 65));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JTextArea result = new JTextArea();
    result.setEditable(false);
    result.setBackground(new Color(34, 45, 65));
    result.setForeground(Color.GREEN);
    result.setFont(new Font("Monospaced", Font.PLAIN, 16));
    result.setAlignmentX(Component.CENTER_ALIGNMENT);

    String message = String.format("Player: %s\nMap: %s\nDifficulty: %s\nScore: %d\n",
        playerName, selectedMap, selectedDifficulty, score);
    if (history.getTopScores().stream().anyMatch(r -> r.name.equals(playerName) && r.score == score)) {
        message += "Congratulations! You made the leaderboard!";
    }
    result.setText(message);

    JButton backButton = new JButton("Back to Main Menu");
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.setBackground(Color.BLACK);
    backButton.setForeground(Color.GREEN);
    backButton.setFont(new Font("Arial", Font.BOLD, 16));
    backButton.setFocusPainted(false);
    backButton.setMaximumSize(new Dimension(200, 40));
    backButton.addActionListener(e -> {
        gameOverFrame.dispose();
        createMainMenu();
    });

    panel.add(Box.createVerticalStrut(20));
    panel.add(result);
    panel.add(Box.createVerticalStrut(20));
    panel.add(backButton);

    gameOverFrame.add(panel);
    gameOverFrame.setVisible(true);
}
}