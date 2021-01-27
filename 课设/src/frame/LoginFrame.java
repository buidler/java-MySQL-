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
//runnable 滚动字幕
public class LoginFrame extends BaseFrame implements Runnable {

	public LoginFrame() {
		this.init();
	}

	public LoginFrame(String title) {
		super(title);
		this.init();
		//修改logo
		ImageIcon icon = new ImageIcon("src//img//校徽.png");
		Image img = icon.getImage();
		this.setIconImage(img);
	}

	// 主面板
	private JPanel mainPanel = new JPanel();

	// 显示标题
	private JLabel titleLabel = new JLabel("学  习  系  统");

	// 创建账号和密码的标题
	private JLabel accountLabel = new JLabel("账 户：");
	private JLabel passwordLabel = new JLabel("密 码：");
	private JLabel RadioTypeLabel = new JLabel("账户类型：");

	//滚动
	private JLabel member = new JLabel("1913041122    1913041106");

	// 账户，密码文本框
	private JTextField accountField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();

	//按钮
	private JButton loginButton = new JButton("登 录");
	private JButton registerButton = new JButton("注册");
	private JButton cancellationButton = new JButton("注销");
	private JButton changePassword = new JButton("修改密码");

	//单选框 单选框按钮归组
	private ButtonGroup group = new ButtonGroup();
	private JRadioButton teacherRadio = new JRadioButton("教师");
	private JRadioButton studentRadio = new JRadioButton("学生");

	//负责滚动字幕
	Thread scrollWord = null;

	@Override
	protected void setFontAndSoOn() {
		// TODO Auto-generated method stub
		scrollWord = new Thread(this);
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.WHITE);
		titleLabel.setBounds(160, 30, 340, 35);
		titleLabel.setFont(new Font("黑体", Font.BOLD, 35));
		RadioTypeLabel.setBounds(94, 90, 90, 30);
		RadioTypeLabel.setFont(new Font("黑体", Font.BOLD, 15));
		teacherRadio.setBackground(Color.WHITE);
		teacherRadio.setBounds(204, 90, 90, 30);
		teacherRadio.setFont(new Font("黑体", Font.BOLD, 16));
		studentRadio.setBounds(350, 90, 90, 30);
		studentRadio.setFont(new Font("黑体", Font.BOLD, 16));
		studentRadio.setBackground(Color.WHITE);
		accountLabel.setBounds(94, 134, 90, 30);
		accountLabel.setFont(new Font("黑体", Font.BOLD, 18));
		accountField.setBounds(204, 134, 260, 30);
		accountField.setFont(new Font("黑体", Font.BOLD, 24));
		passwordLabel.setBounds(94, 174, 90, 30);
		passwordLabel.setFont(new Font("黑体", Font.BOLD, 18));
		passwordField.setFont(new Font("黑体",Font.BOLD,24));
		passwordField.setBounds(204,174,260,30);
		passwordField.setEchoChar('*');

		loginButton.setBounds(170, 232, 100, 30);
		loginButton.setFont(new Font("黑体", Font.BOLD, 15));
		loginButton.setBackground(Color.white);
		registerButton.setBounds(50, 232, 100, 30);
		registerButton.setFont(new Font("黑体", Font.BOLD, 15));
		registerButton.setBackground(Color.WHITE);
		registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancellationButton.setBounds(410, 232, 100, 30);
		cancellationButton.setFont(new Font("黑体", Font.BOLD, 15));
		cancellationButton.setBackground(Color.WHITE);
		cancellationButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changePassword.setBounds(290,232,100,30);
		changePassword.setFont(new Font("黑体",Font.BOLD,15));
		changePassword.setBackground(Color.WHITE);
		changePassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	@Override
	protected void addElement() {
		// TODO Auto-generated method stub
		group.add(teacherRadio);
		group.add(studentRadio);
		mainPanel.add(teacherRadio);
		mainPanel.add(studentRadio);
		mainPanel.add(RadioTypeLabel);
		mainPanel.add(titleLabel);
		mainPanel.add(accountLabel);
		mainPanel.add(accountField);
		mainPanel.add(passwordLabel);
		mainPanel.add(passwordField);
		mainPanel.add(loginButton);
		mainPanel.add(registerButton);
		mainPanel.add(cancellationButton);
		mainPanel.add(changePassword);
		mainPanel.add(member);
		this.add(mainPanel);
	}

	@Override
	protected void addListener() {
		// TODO Auto-generated method stub
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
		loginButton.addMouseListener(optionListener);
		registerButton.addMouseListener(optionListener);
		cancellationButton.addMouseListener(optionListener);
		changePassword.addMouseListener(optionListener);

		//绑定事件监听 登录按钮
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取账号信息 从文本框
				String account = accountField.getText();
				//从密码框获取密码
				String password = new String(passwordField.getPassword());
				//老师或学生
				String type ;
				//单选框选择
				if(teacherRadio.isSelected()) {
					type = "teacher";
					UserService service = new UserService();
					//获取登陆结果
					String result = service.login(account, password, type);

					  if("登陆成功".equals(result)){
						  //将登录窗口隐藏
						  LoginFrame.this.setVisible(false);
						  //弹出看题界面
						 ViewTopicFrame viewTopicFrame =  new ViewTopicFrame(account + "教师页面");
						 viewTopicFrame.setContent(new User(account,password,null));
						  System.out.println(account);
					  }else{ //警告框 
						  JOptionPane.showMessageDialog(LoginFrame.this,result); 
						  }
				}else if(studentRadio.isSelected()){
					type = "student";
					UserService service = new UserService();
					//获取登陆结果
					String result = service.login(account, password, type);

					  if("登陆成功".equals(result)){
						  //将登录窗口隐藏
						  LoginFrame.this.setVisible(false);
						  //弹出新的考试界面 
						  FunctionFrame frame = new FunctionFrame(account + "模式选择页面");
						  frame.setUser(new User(account,password,null));
					  }else{ //警告框 
						  JOptionPane.showMessageDialog(LoginFrame.this,result); 
						  }
				}else {
					JOptionPane.showMessageDialog(null, "请选择用户类型");
				}
			}
		});
		//创建一个监听器对象 用于注册按钮
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//登陆窗口不可见
				LoginFrame.this.setVisible(false);
				//弹出注册界面
				new RegisterFrame();
			}

    	});
		//创建一个监听器用于修改密码
		changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//登陆窗口不可见
				LoginFrame.this.setVisible(false);
				//弹出修改密码页面
				new ChangePasswordFrame();
			}
		});
		//创建一个监听器用于注销按钮
		cancellationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//登陆窗口不可见
				LoginFrame.this.setVisible(false);
				//弹出注销页面
				new CancellationFrame();
			}
		});

	}

	@Override
	protected void setFrameSelf() {
		// TODO Auto-generated method stub
		this.setBounds(600, 280, 560, 340);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		scrollWord.start();//在AWT - Window线程里启动scrollWord线程
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			int x = member.getBounds().x;
			x = x + 10;
			
			member.setBounds(x, 280, 200, 15);
			//当字母移动到窗口边界
			if(x > 550) {
				//从头再来
				x =  0;
				member.setLocation(x, 280);
			}
			try {
				Thread.sleep(200);
			}catch(Exception e) {
				e.printStackTrace();
			}
			//界面关闭，线程结束
			if(!this.isVisible()) {
				return;
			}
		}
	}

}
