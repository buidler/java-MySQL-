package frame;

import dao.QuestionDao;
import entity.Questions;
import service.QuestionService;
import util.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author 杨佳雨
 * @date 2021/1/7 18:37
 */
public class ChapterSelectFrame extends BaseFrame {

  public ChapterSelectFrame() {
    this.init();
    //设置logo
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  public ChapterSelectFrame(String tittle) {
    super(tittle);
    this.init();
    //设置logo
    ImageIcon icon = new ImageIcon("src//img//校徽.png");
    Image img = icon.getImage();
    this.setIconImage(img);
  }

  private String obtain;
  private JPanel practicePanel = new JPanel();
  private JButton chapterOneButton = new JButton("question_chapterone");
  private JButton chapterTwoButton = new JButton("question_chaptertwo");
  private JButton chapterThreeButton = new JButton("question_chapterthree");
  private JButton chapterFourAndFiveButton = new JButton("question_chapterfourandfive");
  private JButton chapterSixButton = new JButton("question_chaptersix");
  private JButton chapterSevenButton = new JButton("question_chapterseven");
  private JButton chapterEightAndNineButton = new JButton("question_chaptereightandnine");
  private JButton chapterTenButton = new JButton("question_ten");
  private JButton chapterElevenAndTwelveButton = new JButton("question_elevenandtwelve");
  private JButton defineButton = new JButton("确定");
  private JButton returnButton = new JButton("返回");
  private JButton mainFrameButton = new JButton("主页面");

  @Override
  protected void setFontAndSoOn() {
    practicePanel.setLayout(null);
    chapterOneButton.setBounds(140, 20, 300, 40);
    chapterOneButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterOneButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterTwoButton.setBounds(140, 80, 300, 40);
    chapterTwoButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterTwoButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterThreeButton.setBounds(140, 140, 300, 40);
    chapterThreeButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterThreeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterFourAndFiveButton.setBounds(140, 200, 300, 40);
    chapterFourAndFiveButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterFourAndFiveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterSixButton.setBounds(140, 260, 300, 40);
    chapterSixButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterSixButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterSevenButton.setBounds(140, 320, 300, 40);
    chapterSevenButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterSevenButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterEightAndNineButton.setBounds(140, 380, 300, 40);
    chapterEightAndNineButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterEightAndNineButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterTenButton.setBounds(140, 440, 300, 40);
    chapterTenButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterTenButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    chapterElevenAndTwelveButton.setBounds(140, 500, 300, 40);
    chapterElevenAndTwelveButton.setFont(new Font("黑体", Font.BOLD, 17));
    chapterElevenAndTwelveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    defineButton.setBounds(70, 600, 130, 30);
    defineButton.setFont(new Font("黑体", Font.BOLD, 16));
    defineButton.setBackground(Color.WHITE);
    returnButton.setBounds(235, 600, 130, 30);
    returnButton.setFont(new Font("黑体", Font.BOLD, 16));
    returnButton.setBackground(Color.WHITE);
    mainFrameButton.setBounds(400, 600, 130, 30);
    mainFrameButton.setFont(new Font("黑体", Font.BOLD, 16));
    mainFrameButton.setBackground(Color.WHITE);
  }

  @Override
  protected void addElement() {
    practicePanel.add(chapterOneButton);
    practicePanel.add(chapterTwoButton);
    practicePanel.add(chapterThreeButton);
    practicePanel.add(chapterFourAndFiveButton);
    practicePanel.add(chapterSixButton);
    practicePanel.add(chapterSevenButton);
    practicePanel.add(chapterEightAndNineButton);
    practicePanel.add(chapterTenButton);
    practicePanel.add(chapterElevenAndTwelveButton);
    practicePanel.add(defineButton);
    practicePanel.add(returnButton);
    practicePanel.add(mainFrameButton);
    this.add(practicePanel);
  }

  @Override
  protected void addListener() {

    //按钮上的监听器
    ActionListener optionListenerTwo = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //清除按钮颜色
        ChapterSelectFrame.this.clearButtonColor();
        // 获取按钮事件源
        JButton button = (JButton) e.getSource();
        //设置按钮背景灰色
        button.setBackground(Color.gray);
      }
    };
    //按钮添加监听器
    chapterOneButton.addActionListener(optionListenerTwo);
    chapterTwoButton.addActionListener(optionListenerTwo);
    chapterThreeButton.addActionListener(optionListenerTwo);
    chapterFourAndFiveButton.addActionListener(optionListenerTwo);
    chapterSixButton.addActionListener(optionListenerTwo);
    chapterSevenButton.addActionListener(optionListenerTwo);
    chapterEightAndNineButton.addActionListener(optionListenerTwo);
    chapterTenButton.addActionListener(optionListenerTwo);
    chapterElevenAndTwelveButton.addActionListener(optionListenerTwo);

    //按钮上的监听器
    ActionListener optionListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        // 获取按钮事件源
        JButton button = (JButton) e.getSource();
        //设置按钮背景灰色
        button.setBackground(Color.gray);
        //获取面板上选择的选项
        obtain = button.getText();
      }
    };
    //按钮添加监听器
    chapterOneButton.addActionListener(optionListener);
    chapterTwoButton.addActionListener(optionListener);
    chapterThreeButton.addActionListener(optionListener);
    chapterFourAndFiveButton.addActionListener(optionListener);
    chapterSixButton.addActionListener(optionListener);
    chapterSevenButton.addActionListener(optionListener);
    chapterEightAndNineButton.addActionListener(optionListener);
    chapterTenButton.addActionListener(optionListener);
    chapterElevenAndTwelveButton.addActionListener(optionListener);


    // 返回按钮监听器
    returnButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new FunctionFrame("模式选择页面");
            ChapterSelectFrame.this.setVisible(false);
          }
        });

    // 主页面按钮监听器
    mainFrameButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            new LoginFrame("登录界面");
            ChapterSelectFrame.this.setVisible(false);
          }
        });

    //确定按钮监听器
    defineButton.addActionListener(
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                QuestionDao dao = new QuestionDao();
                new PracticeFrame("练习页面",obtain,dao.getQuestionRow(obtain));
                ChapterSelectFrame.this.setVisible(false);

              }
            }
    );
  }

  @Override
  protected void setFrameSelf() {
    this.setBounds(600, 300, 600, 700);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);
  }
  //清除按钮颜色
  private void clearButtonColor() {
    chapterOneButton.setBackground(null);
    chapterTwoButton.setBackground(null);
    chapterThreeButton.setBackground(null);
    chapterFourAndFiveButton.setBackground(null);
    chapterSixButton.setBackground(null);
    chapterSevenButton.setBackground(null);
    chapterEightAndNineButton.setBackground(null);
    chapterTenButton.setBackground(null);
    chapterElevenAndTwelveButton.setBackground(null);


  }

}
