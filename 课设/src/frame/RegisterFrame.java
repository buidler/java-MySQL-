package frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import dao.UserDao;
import entity.User;
import util.BaseFrame;

/**
 * @author 玉竹
 */
public class RegisterFrame extends BaseFrame {

    public RegisterFrame() {
        this.init();
        //设置logo
        ImageIcon icon = new ImageIcon("src//img//校徽.png");
        Image img = icon.getImage();
        this.setIconImage(img);
    }
    //主面板
    private JPanel mainPanel = new JPanel();

    //信息标签
    private JLabel titleLabel = new JLabel("账号注册");
    private JLabel radioTypeLabel = new JLabel("账户类型：");
    private JLabel accountLabel = new JLabel("用户名：");
    private JLabel classNumLabel = new JLabel("班级：");
    private JLabel passwordLabel = new JLabel("密码：");
    private JLabel definePasswordLabel = new JLabel("确认密码：");

    //班级，账户，密码文本框
    private JTextField classNumField = new JTextField();
    private JTextField accountField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JPasswordField definePasswordField = new JPasswordField();

    //按钮
    private JButton registerButton = new JButton("注册");
    private JButton rreturnButton = new JButton("返回");

    // 单选框 单选框归组
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton teacherRadio = new JRadioButton("教师");
    private JRadioButton studentRadio = new JRadioButton("学生");


    @Override
    protected void setFontAndSoOn() {
        //自定义布局
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        radioTypeLabel.setBounds(94, 70, 90, 30);
        radioTypeLabel.setFont(new Font("黑体", Font.BOLD, 17));
        teacherRadio.setBackground(Color.WHITE);
        teacherRadio.setBounds(204, 70, 90, 30);
        teacherRadio.setFont(new Font("黑体", Font.BOLD, 14));
        studentRadio.setBounds(350, 70, 90, 30);
        studentRadio.setFont(new Font("黑体", Font.BOLD, 14));
        studentRadio.setBackground(Color.WHITE);
        titleLabel.setBounds(210, 10, 340, 30);
        titleLabel.setFont(new Font("黑体", Font.BOLD, 26));
        accountLabel.setBounds(94, 110, 90, 24);
        accountLabel.setFont(new Font("黑体", Font.BOLD, 17));
        accountField.setBounds(204, 110, 260, 30);
        accountField.setFont(new Font("黑体", Font.BOLD, 17));
        passwordLabel.setBounds(94, 150, 90, 30);
        passwordLabel.setFont(new Font("黑体", Font.BOLD, 17));
        passwordField.setBounds(204, 150, 260, 30);
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("黑体", Font.BOLD, 17));
        definePasswordLabel.setBounds(94, 190, 90, 30);
        definePasswordLabel.setFont(new Font("黑体", Font.BOLD, 17));
        definePasswordField.setBounds(204, 190, 260, 30);
        definePasswordField.setFont(new Font("黑体", Font.BOLD, 20));
        definePasswordField.setEchoChar('*');
        classNumLabel.setBounds(94, 230, 90, 30);
        classNumLabel.setFont(new Font("黑体", Font.BOLD, 17));
        classNumField.setBounds(204, 230, 260, 30);
        classNumField.setFont(new Font("黑体", Font.BOLD, 20));

        registerButton.setBounds(90, 270, 100, 30);
        registerButton.setFont(new Font("黑体", Font.BOLD, 15));
        registerButton.setBackground(Color.WHITE);
        rreturnButton.setBounds(340, 270, 100, 30);
        rreturnButton.setFont(new Font("黑体", Font.BOLD, 15));
        rreturnButton.setBackground(Color.WHITE);

    }

    @Override
    protected void addElement() {
        group.add(teacherRadio);
        group.add(studentRadio);
        mainPanel.add(teacherRadio);
        mainPanel.add(studentRadio);
        mainPanel.add(radioTypeLabel);
        mainPanel.add(titleLabel);
        mainPanel.add(accountField);
        mainPanel.add(accountLabel);
        mainPanel.add(passwordField);
        mainPanel.add(passwordLabel);
        mainPanel.add(definePasswordField);
        mainPanel.add(definePasswordLabel);
        mainPanel.add(rreturnButton);
        mainPanel.add(registerButton);
        mainPanel.add(classNumLabel);
        mainPanel.add(classNumField);

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
        registerButton.addMouseListener(optionListener);
        rreturnButton.addMouseListener(optionListener);

        //给注册按钮增加监听器
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //获取信息 从文本框
                String account = accountField.getText();
                String classNum = classNumField.getText();
                String password ;
                String passwordOne = new String(passwordField.getPassword());
                String passwordTwo = new String(definePasswordField.getPassword());
                String type = null;

                //判断单选框被选择选项
                if (teacherRadio.isSelected()) {
                    type = "teacher";
                } else if (studentRadio.isSelected()) {
                    type = "student";
                } else {
                    JOptionPane.showMessageDialog(null, "请选择用户类型");
                }

                //两次输入的密码相同
                if (passwordOne.equals(passwordTwo)) {
                    password = passwordOne;
                    //封装成对象
                    UserDao dao = new UserDao();
                    //返回注册结果
                    int ok = dao.addAccount(new User(account, password, classNum), type);
                    if (ok == 1) {
                        //提示框
                        JOptionPane.showMessageDialog(null, "注册成功，请登录");
                        //注册界面不可见
                        RegisterFrame.this.setVisible(false);
                        //弹出登陆界面
                        new LoginFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名已经存在！");
                    }
                } else {
                    //警告框
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
                }
            }
        });

        //添加返回按钮监听器
        rreturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //当前界面不可见
                RegisterFrame.this.setVisible(false);
                //弹出登陆界面
                new LoginFrame();
            }
        });
    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(600, 280, 560, 340);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
}
