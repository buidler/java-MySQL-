package frame;

import util.BaseFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import dao.QuestionDao;
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
public class ReviseQuestionFrame extends BaseFrame {

    //只修改答案选项的正确选项
	private String answer = null;
	//只修改选项内容的内容
	private String content = null;
	
    private JPanel mainPanel = new JPanel();

    //信息标签
    private JLabel tittleLabel = new JLabel("题目：(请正确输入题目)");
    private JLabel selectLabel = new JLabel("选项：");
    private JLabel answerLabel = new JLabel("答案：");
    
    private JTextArea questionArea = new JTextArea();
    //滚动条
    private JScrollPane scrollPane = new JScrollPane(questionArea);

    //各种按钮
    private JButton aButton = new JButton("A");
    private JButton bButton = new JButton("B");
    private JButton cButton = new JButton("C");
    private JButton dButton = new JButton("D");
    private JButton defineButton = new JButton("确定");
    private JButton returnButton = new JButton("返回");
    private JButton searchButton = new JButton("检索题目");
    private JButton reviseButton = new JButton("修改选项");
    private JButton reviseAnswerButton = new JButton("修改答案");

    //选项文本框
    private JTextField aField = new JTextField();
    private JTextField bField = new JTextField();
    private JTextField cField = new JTextField();
    private JTextField dField = new JTextField();

    public ReviseQuestionFrame() {
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

        tittleLabel.setBounds(30,30,250,35);
        tittleLabel.setFont(new FontUIResource("黑体",Font.BOLD,20));
        selectLabel.setBounds(460,40,100,35);
        selectLabel.setFont(new FontUIResource("黑体",Font.BOLD,20) );
        answerLabel.setBounds(610,40,100,35);
        answerLabel.setFont(new FontUIResource("黑体",Font.BOLD,20));

        aButton.setBounds(455,90,100,30);
        aButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        bButton.setBounds(455,170,100,30);
        bButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        cButton.setBounds(455,250,100,30);
        cButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        dButton.setBounds(455,330,100,30);
        dButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));

        defineButton.setBounds(30,410,100,30);
        defineButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        defineButton.setBackground(Color.WHITE);
        returnButton.setBounds(180,410,100,30);
        returnButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        returnButton.setBackground(Color.WHITE);
        reviseButton.setBounds(455,410,130,30);
        reviseButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        reviseButton.setBackground(Color.WHITE);
        searchButton.setBounds(300,410,130,30);
        searchButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));
        searchButton.setBackground(Color.WHITE);
        reviseAnswerButton.setBackground(Color.WHITE);
        reviseAnswerButton.setBounds(640,410,130,30);
        reviseAnswerButton.setFont(new FontUIResource("黑体",Font.PLAIN,20));

        aField.setBounds(600,90,170,30);
        aField.setFont(new FontUIResource("黑体",Font.BOLD,20));
        bField.setBounds(600,170,170,30);
        bField.setFont(new FontUIResource("黑体",Font.BOLD,20));
        cField.setBounds(600,250,170,30);
        cField.setFont(new FontUIResource("黑体",Font.BOLD,20));
        dField.setBounds(600,330,170,30);
        dField.setFont(new FontUIResource("黑体",Font.BOLD,20));

        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        aField.setEnabled(false);
        bField.setEnabled(false);
        cField.setEnabled(false);
        dField.setEnabled(false);

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
        mainPanel.add(searchButton);
        mainPanel.add(selectLabel);
        mainPanel.add(answerLabel);
        mainPanel.add(tittleLabel);
        mainPanel.add(aButton);
        mainPanel.add(bButton);
        mainPanel.add(cButton);
        mainPanel.add(dButton);
        mainPanel.add(reviseButton);
        mainPanel.add(reviseAnswerButton);
        mainPanel.add(aField);
        mainPanel.add(bField);
        mainPanel.add(cField);
        mainPanel.add(dField);
        mainPanel.add(scrollPane);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {
        //焦点按钮监听器
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
        reviseButton.addMouseListener(optionListenerTwo);
        returnButton.addMouseListener(optionListenerTwo);
        defineButton.addMouseListener(optionListenerTwo);
        reviseAnswerButton.addMouseListener(optionListenerTwo);
        searchButton.addMouseListener(optionListenerTwo);

        //判断选择的（答案）选项,只修改答案选项
        ActionListener optionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ReviseQuestionFrame.this.clearButtonColor();
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


        //创建一个监听器对象 用于四个文本框
        ActionListener textLIstener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //获取到哪一个文本框
                JTextField textField = (JTextField)e.getSource();
                //获取内容
                content = textField.getText();
            }

        };
        //将这个监听器对象绑定在四个文本框上
        aField.addActionListener(textLIstener);
        bField.addActionListener(textLIstener);
        cField.addActionListener(textLIstener);
        dField.addActionListener(textLIstener);
        
        //返回按钮监听器
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前界面不可见
                ReviseQuestionFrame.this.setVisible(false);
                //弹出展示题目界面
                new ViewTopicFrame();
            }
        });


        //修改答案选项按钮监听器
        reviseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	//选项可以点，文本不可变
                aButton.setEnabled(true);
                bButton.setEnabled(true);
                cButton.setEnabled(true);
                dButton.setEnabled(true);
                aField.setEnabled(false);
                bField.setEnabled(false);
                cField.setEnabled(false);
                dField.setEnabled(false);

            }
        });


        //修改选项文本监听器
        reviseAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //文本框可编辑，选项不可编辑
                aField.setEnabled(true);
                bField.setEnabled(true);
                cField.setEnabled(true);
                dField.setEnabled(true);
                aButton.setEnabled(true);
                bButton.setEnabled(true);
                cButton.setEnabled(true);
                dButton.setEnabled(true);

            }
        });
        
        //检索题目按钮监听器
        searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QuestionDao dao = new QuestionDao();
				//获取问题，文本域
            	String question = questionArea.getText();

				aField.setText(dao.returnOptionContent(question).getA());
            	bField.setText(dao.returnOptionContent(question).getB());
            	cField.setText(dao.returnOptionContent(question).getC());
            	dField.setText(dao.returnOptionContent(question).getD());
			}

        });

        //确定按钮监听器
        defineButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionArea.getText();
                QuestionService service = new QuestionService();
                if(content == null){
                    //修改答案选项
                    //获得修改结果
                    System.out.println(1);
                    String result = service.alterQuestion(new Questions(question,answer), answer, null);
                    //提示框
                    JOptionPane.showMessageDialog(null, result);
                }else{
                    //修改答案内容
                    //获得修改结果
                    String result = service.alterQuestion(new Questions(question,null), answer , content);
                    //提示框
                    JOptionPane.showMessageDialog(null, result);
                }
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
