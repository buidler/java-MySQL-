package frame;


import dao.UserDao;
import entity.Questions;
import entity.User;
import service.QuestionService;
import util.BaseFrame;

import javax.swing.*;

import dao.QuestionDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author 玉竹
 */
public class ViewTopicFrame extends BaseFrame {

    private QuestionService service = new QuestionService();
    private QuestionDao dao = new QuestionDao();

    private ArrayList<Questions> paper = service.showQuestions("questions");

    //试题总数
    private int totalCount = dao.getQuestionRow("questions");
    //添加三个panel 区域的分割
    //题目主页面展示
    private JPanel mainPanel = new JPanel();
    //修改题目区域
    private JPanel revisePanel = new JPanel();

    //添加各种组件
    //考试文本域，展示题目
    private JTextArea examArea = new JTextArea();
    //滚动条
    private JScrollPane scrollPane = new JScrollPane(examArea);

    //面板组件
    private JTextArea reviseArea = new JTextArea();
    private JLabel teacherLabel = new JLabel("老师:");
    private JTextField teacherField = new JTextField();
    private JLabel classLabel = new JLabel("班级:");
    private JTextField classField = new JTextField();

    //提示题目的总数
    private JLabel totalCountLabel = new JLabel("题目总数：");
    //展示总数
    private JTextField totalCountField = new JTextField();

    //信息按钮
    private JButton addQuestionsButton = new JButton("增加题目");
    private JButton reviseQuestionsButton = new JButton("修改题目");
    private JButton returnButton = new JButton("返回");

    public ViewTopicFrame() {
        this.init();
    }

    public ViewTopicFrame(String title) {
        super(title);
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

        revisePanel.setLayout(null);
        revisePanel.setBounds(700, 120, 500, 425);
        revisePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setBounds(16, 80, 650, 430);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        examArea.setFont(new Font("黑体", Font.BOLD, 34));
        examArea.setBounds(16, 80, 650, 430);
        examArea.setEnabled(false);
        examArea.setLineWrap(true);

        totalCountLabel.setBounds(20, 20, 120, 30);
        totalCountLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        totalCountField.setBounds(130, 20, 80, 30);
        totalCountField.setFont(new Font("黑体", Font.BOLD, 20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);
        totalCountField.setHorizontalAlignment(JTextField.LEFT);
        teacherLabel.setBounds(260, 20, 80, 30);
        teacherLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        teacherField.setBounds(350, 20, 80, 30);
        teacherField.setFont(new Font("黑体", Font.BOLD, 20));
        teacherField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        teacherField.setEnabled(false);
        classLabel.setBounds(450, 20, 80, 30);
        classLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        classField.setBounds(540, 20, 150, 30);
        classField.setFont(new Font("黑体", Font.BOLD, 20));
        classField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        classField.setEnabled(false);

        addQuestionsButton.setBounds(60, 540, 100, 30);
        addQuestionsButton.setFont(new Font("黑体", Font.PLAIN, 16));
        addQuestionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addQuestionsButton.setBackground(Color.WHITE);
        reviseQuestionsButton.setBounds(300, 540, 100, 30);
        reviseQuestionsButton.setFont(new Font("黑体", Font.PLAIN, 16));
        reviseQuestionsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reviseQuestionsButton.setBackground(Color.WHITE);
        returnButton.setBounds(540, 540, 100, 30);
        returnButton.setFont(new Font("黑体", Font.PLAIN, 16));
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setBackground(Color.WHITE);

        reviseArea.setBounds(10, 10, 480, 400);
        reviseArea.setFont(new Font("黑体", Font.PLAIN, 20));

        this.showQuestion();
    }

    @Override
    protected void addElement() {
        revisePanel.add(reviseArea);
        mainPanel.add(totalCountLabel);
        mainPanel.add(totalCountField);
        mainPanel.add(scrollPane);
        mainPanel.add(teacherLabel);
        mainPanel.add(teacherField);
        mainPanel.add(classField);
        mainPanel.add(classLabel);
        mainPanel.add(addQuestionsButton);
        mainPanel.add(reviseQuestionsButton);
        mainPanel.add(returnButton);
        mainPanel.add(revisePanel);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {
        //焦点按钮监听器
        MouseListener optionListener =
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
        addQuestionsButton.addMouseListener(optionListener);
        reviseQuestionsButton.addMouseListener(optionListener);
        returnButton.addMouseListener(optionListener);

        //设置 增加题目 监听器
        addQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前界面不可见
                ViewTopicFrame.this.setVisible(false);
                //弹出增加问题界面
                new AddQuestionsFrame();
            }
        });

        //设置 修改题目 监听器
        reviseQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前界面不可见
                ViewTopicFrame.this.setVisible(false);
                //弹出修改问题界面
                new ReviseQuestionFrame();
            }
        });


        //返回按钮监听器
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前界面不可见
                ViewTopicFrame.this.setVisible(false);
                //弹出登陆界面
                new LoginFrame();
            }
        });


    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(260, 130, 700, 630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    /**
     * 展示题库所有题目
     */
    private void showQuestion() {
        //此类设计用作简易替换为StringBuffer在正在使用由单个线程字符串缓冲区的地方（如通常是这种情况）。 在可能的情况下，建议使用这个类别优先于StringBuffer ，因为它在大多数实现中将更快。
        StringBuilder showPaper = new StringBuilder();
        for (int i = 0; i < dao.getQuestionRow("questions"); i++) {
            Questions question = paper.get(i);
            showPaper.append(i + 1).append(".").append(question.getTitle()).append("正确答案：").append(question.getAnswer()).append("\n\n");
        }
        //展示题目
        examArea.setText(String.valueOf((showPaper)));

    }

    /**
     * 展示信息
     * @param user
     */
    public void setContent(User user){
        UserDao dao = new UserDao();
        totalCountField.setText(totalCount + "");
        teacherField.setText(user.getAccount());
        classField.setText(dao.searchClassNum(user.getAccount()));
    }

}
