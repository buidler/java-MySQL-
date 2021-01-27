package frame;

import util.BaseFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import entity.Questions;
import service.QuestionService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author 玉竹
 */
public class AddQuestionsFrame extends BaseFrame {

    private String answer = null;

    private JPanel mainPanel = new JPanel();
    private JLabel tittleLabel = new JLabel("题目：");
    private JLabel selectLabel = new JLabel("选项：");
    private JLabel answerLabel = new JLabel("答案：");

    //题目文本域
    private JTextArea questionArea = new JTextArea();
    //滚动条
    private JScrollPane scrollPane = new JScrollPane(questionArea);

    //A选项按钮
    private JButton aButton = new JButton("A");
    //B选项按钮
    private JButton bButton = new JButton("B");
    //C选项按钮
    private JButton cButton = new JButton("C");
    //D选项按钮
    private JButton dButton = new JButton("D");
    //确定按钮
    private JButton defineButton = new JButton("确定");
    //返回按钮
    private JButton returnButton = new JButton("返回");

    //A选项文本框
    private JTextField aField = new JTextField();
    //B选项文本框
    private JTextField bField = new JTextField();
    //C选项文本框
    private JTextField cField = new JTextField();
    //D选项文本框
    private JTextField dField = new JTextField();

    public AddQuestionsFrame() {
        super();
        this.init();
        //设置logo
        ImageIcon icon = new ImageIcon("src//img//校徽.png");
        Image img = icon.getImage();
        this.setIconImage(img);
    }

    @Override
    protected void setFontAndSoOn() {
        //自定义布局
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);

        tittleLabel.setBounds(30, 30, 100, 35);
        tittleLabel.setFont(new FontUIResource("黑体", Font.BOLD, 20));
        selectLabel.setBounds(460, 40, 100, 35);
        selectLabel.setFont(new FontUIResource("黑体", Font.BOLD, 20));
        answerLabel.setBounds(610, 40, 100, 35);
        answerLabel.setFont(new FontUIResource("黑体", Font.BOLD, 20));

        aButton.setBounds(455, 90, 100, 30);
        aButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));
        bButton.setBounds(455, 170, 100, 30);
        bButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));
        cButton.setBounds(455, 250, 100, 30);
        cButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));
        dButton.setBounds(455, 330, 100, 30);
        dButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));
        defineButton.setBounds(200, 410, 100, 30);
        defineButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));
        returnButton.setBounds(460, 410, 100, 30);
        returnButton.setFont(new FontUIResource("黑体", Font.PLAIN, 20));

        aField.setBounds(600, 90, 170, 30);
        aField.setFont(new FontUIResource("黑体", Font.BOLD, 20));
        bField.setBounds(600, 170, 170, 30);
        bField.setFont(new FontUIResource("黑体", Font.BOLD, 20));
        cField.setBounds(600, 250, 170, 30);
        cField.setFont(new FontUIResource("黑体", Font.BOLD, 20));
        dField.setBounds(600, 330, 170, 30);
        dField.setFont(new FontUIResource("黑体", Font.BOLD, 20));

        questionArea.setFont(new FontUIResource("黑体", Font.PLAIN, 25));
        questionArea.setBounds(16, 80, 400, 300);
        questionArea.setLineWrap(true);
        scrollPane.setBounds(16, 80, 400, 300);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    }

    @Override
    protected void addElement() {
        mainPanel.add(returnButton);
        mainPanel.add(defineButton);
        mainPanel.add(selectLabel);
        mainPanel.add(answerLabel);
        mainPanel.add(tittleLabel);
        mainPanel.add(aButton);
        mainPanel.add(bButton);
        mainPanel.add(cButton);
        mainPanel.add(dButton);
        mainPanel.add(aField);
        mainPanel.add(bField);
        mainPanel.add(cField);
        mainPanel.add(dField);
        mainPanel.add(scrollPane);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {
        /**
         * 鼠标变色效果监听器
         */
        MouseListener optionListenerTwo =
                new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // 获取事件源
                        JButton button = (JButton) e.getSource();
                        // 按钮变色
                        button.setBackground(Color.CYAN);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // 获取事件源
                        JButton button = (JButton) e.getSource();
                        //按钮变色
                        button.setBackground(null);
                    }
                };
        //添加监听器
        returnButton.addMouseListener(optionListenerTwo);
        defineButton.addMouseListener(optionListenerTwo);

        //返回按钮监听器
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加题目界面不可见
                AddQuestionsFrame.this.setVisible(false);
                //弹出查看题目界面
                new ViewTopicFrame();
            }
        });

        //判断选择的（答案）选项
        ActionListener optionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                AddQuestionsFrame.this.clearButtonColor();
                //获取按钮事件源
                JButton button = (JButton) e.getSource();
                //设置按钮颜色
                button.setBackground(new JButton().getBackground());
                button.setBackground(Color.gray);
                //将选项存储在answers中
                answer = button.getText();
            }

        };
        //添加监听器
        aButton.addActionListener(optionListener);
        bButton.addActionListener(optionListener);
        cButton.addActionListener(optionListener);
        dButton.addActionListener(optionListener);

        //增加题目 确定按钮监听器
        defineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //获取题目
                String question = questionArea.getText();
                //获取A选项
                String A = aField.getText();
                //获取B选项
                String B = bField.getText();
                //获取C选项
                String C = cField.getText();
                //获取D选项
                String D = dField.getText();
                QuestionService service = new QuestionService();
                //返回增加题目的结果并显示提醒框
                String result = service.addQuestion(new Questions(question, A, B, C, D, answer));
                JOptionPane.showMessageDialog(null, result);
            }
        });
    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(260, 130, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    //清除按钮颜色，并返回按钮原本的颜色
    private void clearButtonColor() {
        aButton.setBackground(new JButton().getBackground());
        bButton.setBackground(new JButton().getBackground());
        cButton.setBackground(new JButton().getBackground());
        dButton.setBackground(new JButton().getBackground());
    }

}
