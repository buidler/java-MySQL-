package frame;

import entity.User;
import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 玉竹
 */
public class FunctionFrame extends BaseFrame {
  User user = new User();

  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  //按钮
  private JPanel mainPanel = new JPanel();
  private JButton examButton = new JButton("进入考试");
  private JButton practiceButton = new JButton("题目练习");
  private  JButton informationButton = new JButton("个人中心");

  public FunctionFrame() {
    this.init();
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  public FunctionFrame(String tittle) {
    super(tittle);
    this.init();
    //设置logo
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  @Override
  protected void setFontAndSoOn() {
    mainPanel.setLayout(null);
    examButton.setBounds(85, 50, 120, 30);
    examButton.setFont(new Font("黑体", Font.BOLD, 20));
    examButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    practiceButton.setBounds(85, 120, 120, 30);
    practiceButton.setFont(new Font("黑体", Font.BOLD, 20));
    practiceButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    informationButton.setBounds(85, 190, 120, 30);
    informationButton.setFont(new Font("黑体", Font.BOLD, 20));
    informationButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
  }

  @Override
  protected void addElement() {
    mainPanel.add(examButton);
    mainPanel.add(practiceButton);
    mainPanel.add(informationButton);
    this.add(mainPanel);
  }

  @Override
  protected void addListener() {
     //    考试按钮监听器
    examButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FunctionFrame.this.setVisible(false);
        new ExamFrame( "考试页面");
      }
    });


    //    练习题目监听器
    practiceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FunctionFrame.this.setVisible(false);
        new ChapterSelectFrame("章节选择界面");
      }
    });

    //个人中心监听器
    informationButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FunctionFrame.this.setVisible(false);
        InformationFrame informationFrame = new InformationFrame(user.getAccount() + "的个人中心", user);
        informationFrame.setContent(user);
      }
    });
  }

  @Override
  protected void setFrameSelf() {
    this.setBounds(700, 400, 300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }
}
