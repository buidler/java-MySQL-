package frame;

import dao.UserDao;
import entity.User;
import service.UserService;
import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 杨佳雨
 * @date 2021/1/12 9:11
 */
public class InformationFrame extends BaseFrame {
  private User user;
  public InformationFrame() {
    this.init();
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  public InformationFrame(String tittle,User user) {
    super(tittle);
    this.init();
    this.user = user;
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  //标签
  private JPanel mainPanel = new JPanel();
  private JLabel helloLabel = new JLabel("您好：");
  private JLabel signatureLabel = new JLabel("个性签名:");
  private JLabel sexLabel = new JLabel("性   别：");
  private JLabel locationLabel = new JLabel("地   区：");
  private JLabel universityLabel = new JLabel("大   学：");
  //文本框
  private JTextField helloField = new JTextField();
  private JTextField signatureField = new JTextField();
  private JTextField locationField = new JTextField();
  private JTextField universityField = new JTextField();
  //按钮
  private JButton defineButton = new JButton("确定");
  private JButton returnButton = new JButton("返回");

  //单选框 单选框按钮归组
  private ButtonGroup group = new ButtonGroup();
  private JRadioButton maleRadio = new JRadioButton("男");
  private JRadioButton femaleRadio = new JRadioButton("女");

  @Override
  protected void setFontAndSoOn() {
    mainPanel.setLayout(null);
    mainPanel.setBackground(Color.WHITE);
    helloLabel.setBounds(40, 30, 80, 30);
    helloLabel.setFont(new Font("黑体", Font.BOLD, 20));
    helloField.setBounds(110, 30, 130, 30);
    helloField.setFont(new Font("黑体", Font.PLAIN, 18));
    helloField.setBackground(Color.WHITE);
    helloField.setBorder(null);
    signatureLabel.setBounds(100, 80, 100, 30);
    signatureLabel.setFont(new Font("黑体", Font.BOLD, 20));
    signatureField.setBounds(210, 80, 260, 30);
    signatureField.setFont(new Font("黑体", Font.PLAIN, 18));
    signatureField.setBackground(Color.WHITE);
    signatureField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    sexLabel.setBounds(100,170,100,30);
    sexLabel.setFont(new Font("黑体",Font.BOLD,20));
    maleRadio.setBounds(230,170,100,30);
    maleRadio.setFont(new Font("黑体",Font.PLAIN,18));
    maleRadio.setBackground(Color.WHITE);
    femaleRadio.setBounds(360,170,100,30);
    femaleRadio.setFont(new Font("黑体",Font.PLAIN,18));
    femaleRadio.setBackground(Color.WHITE);
    locationLabel.setBounds(100, 260, 100, 30);
    locationLabel.setFont(new Font("黑体", Font.BOLD, 20));
    locationField.setBounds(210, 260, 260, 30);
    locationField.setFont(new Font("黑体", Font.PLAIN, 18));
    locationField.setBackground(Color.WHITE);
    locationField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    universityLabel.setBounds(100,350,100,30);
    universityLabel.setFont(new Font("黑体",Font.BOLD,20));
    universityField.setBounds(210, 350, 260, 30);
    universityField.setFont(new Font("黑体", Font.PLAIN, 18));
    universityField.setBackground(Color.WHITE);
    universityField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
    defineButton.setBounds(130,440,100,30);
    defineButton.setFont(new Font("黑体",Font.BOLD,20));
    defineButton.setBackground(Color.WHITE);
    returnButton.setBounds(300,440,100,30);
    returnButton.setFont(new Font("黑体",Font.BOLD,20));
    returnButton.setBackground(Color.WHITE);
  }

  @Override
  protected void addElement() {
    group.add(maleRadio);
    group.add(femaleRadio);
    mainPanel.add(helloLabel);
    mainPanel.add(helloField);
    mainPanel.add(signatureLabel);
    mainPanel.add(signatureField);
    mainPanel.add(sexLabel);
    mainPanel.add(maleRadio);
    mainPanel.add(femaleRadio);
    mainPanel.add(locationLabel);
    mainPanel.add(locationField);
    mainPanel.add(universityField);
    mainPanel.add(universityLabel);
    mainPanel.add(defineButton);
    mainPanel.add(returnButton);
    this.add(mainPanel);
  }

  @Override
  protected void addListener() {
    //确定按钮鼠标监听器
    defineButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
     defineButton.setBackground(Color.CYAN);
      }
      // 当鼠标离开时,一切又回到了原点
      @Override
      public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        defineButton.setBackground(Color.WHITE);
      }
    });
    //确定按钮事件监听器
    defineButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
         // 从面板获取信息
         String sex;
         String signature = signatureField.getText();
         String location = locationField.getText();
         String university = universityField.getText();
        if(maleRadio.isSelected()){
          sex = "男";
        }else{
          sex = "女";
        }
        UserService service = new UserService();
        //返回修改结果
        String result = service.addIndividualization(signature,sex,location,university,"student",user.getAccount());
        //提示框
        JOptionPane.showMessageDialog(null, result);
        if(result.equals("修改成功")){
          defineButton.setEnabled(false);
        }else {
          JOptionPane.showMessageDialog(null, "请重新操作");
        }
      }
    });
    // 返回按钮鼠标移入移出监听器
    returnButton.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            returnButton.setBackground(Color.CYAN);
          }
          // 当鼠标离开时,一切又回到了原点
          @Override
          public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            returnButton.setBackground(Color.WHITE);
          }
        });
    //返回按钮事件监听器
    returnButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        InformationFrame.this.setVisible(false);
        new LoginFrame();
      }
    });
  }

  @Override
  protected void setFrameSelf() {
    this.setBounds(600, 280, 560, 540);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }

  /**
   *   展示用户名
   * @param user 账户封装类
   */
  public void setContent(User user){
    helloField.setText(user.getAccount());
  }
}
