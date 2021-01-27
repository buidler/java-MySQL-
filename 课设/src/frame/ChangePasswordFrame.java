package frame;

import entity.User;
import service.UserService;
import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * @author 玉竹
 */
public class ChangePasswordFrame extends BaseFrame {

    //主页面
    private JPanel mainPanel = new JPanel();

    //面板标签
    private JLabel account = new JLabel("用户名：");
    private JLabel passwordOne = new JLabel("原密码：");
    private JLabel passwordTwo = new JLabel("新密码：");
    private JLabel passwordThree = new JLabel("再次输入：");
    private JLabel RadioTypeLabel = new JLabel("账户类型：");

    //单选框  单选框按钮归组
    private ButtonGroup group = new ButtonGroup();
	private JRadioButton teacherRadio = new JRadioButton("教师");
	private JRadioButton studentRadio = new JRadioButton("学生");

	//账户，密码文本框
    private JTextField accountFiled = new JTextField();
    private JPasswordField passwordFieldOne = new JPasswordField();
    private JPasswordField passwordFieldTwo = new JPasswordField();
    private JPasswordField passwordFieldThree = new JPasswordField();

    //按钮
    private JButton defineButton = new JButton("确定修改");
    private JButton exitButton = new JButton("返回");

    public ChangePasswordFrame() {
        super();
        this.init();
        //设置logo
        ImageIcon icon = new ImageIcon("src//img//校徽.png");
        Image img = icon.getImage();
        this.setIconImage(img);
    }

    public ChangePasswordFrame(String title) {
        super(title);
    }

    @Override
    protected void setFontAndSoOn() {
        //自定义布局
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.WHITE);
        
        RadioTypeLabel.setBounds(94, 40, 90, 30);
		RadioTypeLabel.setFont(new Font("黑体", Font.BOLD, 17));
		teacherRadio.setBounds(204, 40, 90, 30);
		teacherRadio.setFont(new Font("黑体", Font.BOLD, 14));
		teacherRadio.setBackground(Color.WHITE);
		studentRadio.setBounds(350, 40, 90, 30);
		studentRadio.setFont(new Font("黑体", Font.BOLD, 14));
		studentRadio.setBackground(Color.WHITE);
		
        account.setBounds(94, 80, 90, 30);
        account.setFont(new Font("黑体", Font.BOLD, 18));
        accountFiled.setBounds(204, 80, 260, 30);
        accountFiled.setFont(new Font("黑体", Font.BOLD, 24));

        passwordOne.setBounds(94, 120, 90, 30);
        passwordOne.setFont(new Font("黑体", Font.BOLD, 18));
        passwordFieldOne.setBounds(204, 120, 260, 30);
        passwordFieldOne.setFont(new Font("黑体", Font.BOLD, 24));
        passwordFieldOne.setEchoChar('*');

        passwordTwo.setBounds(94, 160, 90, 30);
        passwordTwo.setFont(new Font("黑体", Font.BOLD, 18));
        passwordFieldTwo.setBounds(204, 160, 260, 30);
        passwordFieldTwo.setFont(new Font("黑体", Font.BOLD, 24));
        passwordFieldTwo.setEchoChar('*');

        passwordThree.setBounds(94, 200, 100, 30);
        passwordThree.setFont(new Font("黑体", Font.BOLD, 18));
        passwordFieldThree.setBounds(204, 200, 260, 30);
        passwordFieldThree.setFont(new Font("黑体", Font.BOLD, 24));
        passwordFieldThree.setEchoChar('*');

        defineButton.setBounds(150, 250, 100, 30);
        defineButton.setFont(new Font("黑体", Font.BOLD, 15));
        defineButton.setBackground(Color.WHITE);

        exitButton.setBounds(300, 250, 100, 30);
        exitButton.setFont(new Font("黑体", Font.BOLD, 15));
        exitButton.setBackground(Color.WHITE);
    }

    @Override
    protected void addElement() {
    	group.add(teacherRadio);
		group.add(studentRadio);
		mainPanel.add(teacherRadio);
		mainPanel.add(studentRadio);
		mainPanel.add(RadioTypeLabel);
        mainPanel.add(account);
        mainPanel.add(passwordOne);
        mainPanel.add(passwordTwo);
        mainPanel.add(passwordThree);
        mainPanel.add(accountFiled);
        mainPanel.add(passwordFieldOne);
        mainPanel.add(passwordFieldTwo);
        mainPanel.add(passwordFieldThree);
        mainPanel.add(defineButton);
        mainPanel.add(exitButton);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {
        //焦点按钮变色
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
        defineButton.addMouseListener(optionListener);
        exitButton.addMouseListener(optionListener);

        //监听器退出按钮
        exitButton.addActionListener(new ActionListener() {
            @Override
            //修改密码页面不可见，显示登陆页面
            public void actionPerformed(ActionEvent e) {
                ChangePasswordFrame.this.setVisible(false);
                new LoginFrame();
            }
        });


        //创建一个监听器 用于确定按钮
        defineButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method
                //从文本框获得账户
				String account = accountFiled.getText();
				// 获取密码
				String passwordOne = new String(passwordFieldOne.getPassword());
				String passwordTwo = new String(passwordFieldTwo.getPassword());
				String passwordThree = new String(passwordFieldThree.getPassword());
				//教师或学生
				String type = null;
				if(teacherRadio.isSelected()) {
					type = "teacher";
				}else if(studentRadio.isSelected()){
					type = "student"; 
				}else {//单选框未选择
					JOptionPane.showMessageDialog(null, "请选择用户类型");
				}
				//两次修改密码框输入相同
				if(passwordThree.equals(passwordTwo)) {
				    //封装到user对象
					User user = new User();
					user.setAccount(account);
					user.setPassword(passwordOne);
					UserService service = new UserService();
					//返回修改密码的结果
					String result = service.changePassword(user,passwordThree, type);
					JOptionPane.showMessageDialog(null, result);
				}else {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
				}
				
			}
        	
        });

    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(600, 280, 560, 340);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("密码修改页面");
    }
}
