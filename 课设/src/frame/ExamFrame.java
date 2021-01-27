package frame;


import util.BaseFrame;

import javax.swing.*;

import entity.Questions;
import service.PictureIn;
import service.QuestionService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author 玉竹
 */
public class ExamFrame extends BaseFrame {
	ImageIcon img = new PictureIn().drawImage("src\\img\\图片.jpg");
	private QuestionService service = new QuestionService();
    //考试有几道题
	private ArrayList<Questions> paper = service.getPaper(5,"questions");
    //当前题目题号""
	private int nowNum = 0;
    //试题总数
	private int totalCount = paper.size();
    //记录当前题数
	private int answerCount = 0;
    //未答题数
	private int unanswerCount = totalCount;
	// 存放随机题目的答案，用于计算成绩
	private String [] answers = new String[paper.size()];
	
	private timeControl timecontrol = new timeControl();

    //时间分钟
	private int time = 10;

    public ExamFrame(){
        this.init();
    }
    public ExamFrame(String title){
        super(title);
        this.init();
        //设置logo
        ImageIcon icon = new ImageIcon("src//img//校徽.png");
        Image img = icon.getImage();
        this.setIconImage(img);
    }

 
    //添加三个panel 区域的分割
    //答题主页面展示
    private JPanel mainPanel = new JPanel();
    //上方信息展示
    private JPanel messagePanel = new JPanel();
    //右侧按钮的展示
    private JPanel buttonPanel = new JPanel();
    //考试文本域，展示题目
    private JTextArea examArea = new JTextArea();
    //滚动条
    private JScrollPane scrollPane = new JScrollPane(examArea);

    //倒计时真实时间
    private JLabel timeLabel = new JLabel("00:00:00");
    //展示图片信息
    private JLabel pictureLabel = new JLabel();
    //提示当前的题号
    private JLabel nowNumLabel = new JLabel("当前题号：");
    //提示题目的总数
    private JLabel totalCountLabel = new JLabel("题目总数：");
    //提示已经答过的题目数量
    private JLabel answerCountLabel = new JLabel("已答题数：");
    //提示未答题数量
    private JLabel unanswerCountLabel = new JLabel("未答题数：");
    //展示题号
    private JTextField nowNumField = new JTextField();
    //展示总数
    private JTextField totalCountField = new JTextField();
    //展示已答数
    private JTextField answerCountField = new JTextField();
    //展示未答数
    private JTextField unanswerCountField = new JTextField();

    private JButton aButton = new JButton("A");
    private JButton bButton = new JButton("B");
    private JButton cButton = new JButton("C");
    private JButton dButton = new JButton("D");
    private JButton prevButton = new JButton("上一题");
    private JButton nextButton = new JButton("下一题");
    private JButton submitButton = new JButton("提交试卷");

    @Override
    protected void setFontAndSoOn() {
        //自定义布局
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(680,10,300,550);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        messagePanel.setLayout(null);
        messagePanel.setBounds(16,14,650,90);
        messagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setBounds(16,120,650,430);
        examArea.setFont(new Font("黑体",Font.BOLD,34));
        examArea.setEnabled(false);
        pictureLabel.setBounds(10,10,280,230);
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pictureLabel.setIcon(img);
        nowNumLabel.setBounds(40,10,120,30);
        nowNumLabel.setFont(new Font("黑体",Font.PLAIN,20));
        nowNumField.setBounds(190,10,120,30);
        nowNumField.setFont(new Font("黑体",Font.BOLD,20));
        nowNumField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        nowNumField.setEnabled(false);
        nowNumField.setHorizontalAlignment(JTextField.CENTER);

        totalCountLabel.setBounds(340,10,120,30);
        totalCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        totalCountField.setBounds(490,10,120,30);
        totalCountField.setFont(new Font("黑体",Font.BOLD,20));
        totalCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        totalCountField.setEnabled(false);
        totalCountField.setHorizontalAlignment(JTextField.CENTER);

        answerCountLabel.setBounds(40,50,120,30);
        answerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        answerCountField.setBounds(190,50,120,30);
        answerCountField.setFont(new Font("黑体",Font.BOLD,20));
        answerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        answerCountField.setEnabled(false);
        answerCountField.setHorizontalAlignment(JTextField.CENTER);

        unanswerCountLabel.setBounds(340,50,120,30);
        unanswerCountLabel.setFont(new Font("黑体",Font.PLAIN,20));
        unanswerCountField.setBounds(490,50,120,30);
        unanswerCountField.setFont(new Font("黑体",Font.BOLD,20));
        unanswerCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        unanswerCountField.setEnabled(false);
        unanswerCountField.setHorizontalAlignment(JTextField.CENTER);
        
        aButton.setBounds(95, 265, 100, 30);
        //改变鼠标形态
        aButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        aButton.setFont(new Font("黑体",Font.PLAIN,25));
        bButton.setBounds(95, 315, 100, 30);
        bButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bButton.setFont(new Font("黑体",Font.PLAIN,25));
        cButton.setBounds(95, 365, 100, 30);
        cButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cButton.setFont(new Font("黑体",Font.PLAIN,25));
        dButton.setBounds(95, 415, 100, 30);
        dButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dButton.setFont(new Font("黑体",Font.PLAIN,25));
        prevButton.setBounds(30, 460, 100, 30);
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        prevButton.setFont(new Font("黑体",Font.PLAIN,16));
        nextButton.setBounds(160, 460, 100, 30);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setFont(new Font("黑体",Font.PLAIN,16));
        submitButton.setBounds(160, 500, 100, 30);
        submitButton.setForeground(Color.magenta);
        submitButton.setFont(new Font("黑体",Font.PLAIN,16));
        timeLabel.setBounds(20,500,120,35);
        timeLabel.setFont(new Font("黑体",Font.BOLD,25));
        timeLabel.setForeground(Color.BLUE);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.showQuestion();
        //数字显示
        nowNumField.setText(nowNum + 1 + "");
        totalCountField.setText(totalCount + "");
        answerCountField.setText(answerCount + "");
        unanswerCountField.setText(unanswerCount + "");
        timeLabel.setText(time + "");
      
    }

    @Override
    protected void addElement() {

        buttonPanel.add(pictureLabel);
        buttonPanel.add(aButton);
        buttonPanel.add(bButton);
        buttonPanel.add(cButton);
        buttonPanel.add(dButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(timeLabel);

        messagePanel.add(nowNumLabel);
        messagePanel.add(nowNumField);
        messagePanel.add(totalCountLabel);
        messagePanel.add(totalCountField);
        messagePanel.add(answerCountLabel);
        messagePanel.add(answerCountField);
        messagePanel.add(unanswerCountLabel);
        messagePanel.add(unanswerCountField);
        mainPanel.add(scrollPane);
        mainPanel.add(messagePanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
    }
    
    @Override
    protected void addListener() {
        //焦点按钮变色监听器
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
        prevButton.addMouseListener(optionListenerTwo);
        nextButton.addMouseListener(optionListenerTwo);
        submitButton.addMouseListener(optionListenerTwo);

        //第一题没有上一题，按钮不可编辑
    	prevButton.setEnabled(false);
        //上一题按钮监听器
    	prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //清空按钮颜色
                ExamFrame.this.clearButtonColor();
                //按钮可操作
                ExamFrame.this.setButtonEnable(true);
                //下一题按钮
                nextButton.setEnabled(true);
                //当前题目数减一
                nowNum --;
                //第一题没有上一题
                if(nowNum == 0){
                    prevButton.setEnabled(false);
                }
                //还原上一道题
                ExamFrame.this.restoreButton();
                //展示下一道题
                ExamFrame.this.showQuestion();
                //改变显示的数目
                nowNumField.setText(nowNum + 1 + "");
                answerCountField.setText(-- answerCount + "");
                unanswerCountField.setText(++ unanswerCount + "");
            }
        });
    	//下一题按钮监听器
    	nextButton.addActionListener(new ActionListener(){
    	    @Override
            public void actionPerformed(ActionEvent e) {
    	        //清空按钮颜色
    	        ExamFrame.this.clearButtonColor();
    	        nowNum ++;
    	        //最后一题
    	        if(nowNum == totalCount){
    	            examArea.setText("题已答完,请提交");
    	            //下一题按钮不可用
    	            nextButton.setEnabled(false);
    	            //所有按钮不可用
    	            ExamFrame.this.setButtonEnable(false);
    	        }else{
    	            //展示下一题
    	            ExamFrame.this.showQuestion();
                    //改变显示的数目
    	            nowNumField.setText(nowNum + 1 + "");
    	            //不是第一题，上一题按钮可用
    	        }if(nowNum != 0){
    	             prevButton.setEnabled(true);
    	        }
                //展示上一题 当前题数小于总数
    	        if(nowNum < paper.size()) {
    	            ExamFrame.this.restoreButton();
    	        }
    	        //改变显示的数目
    	        answerCountField.setText(++ answerCount+"");
    	        unanswerCountField.setText(-- unanswerCount+"");
    	    }
    	});

    	//提交按钮监听器
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击“是”  value == 0，点击“否”  value == 1
                int value = JOptionPane.showConfirmDialog(ExamFrame.this, "是否提交？");
                if (value == 0) {

                    timecontrol.stopTimeThread();

                    //按钮不可编辑
                    ExamFrame.this.setButtonEnable(false);
                    prevButton.setEnabled(false);
                    nextButton.setEnabled(false);
                    //清除按钮颜色
                    ExamFrame.this.clearButtonColor();
                    //计算最终成绩
                    float score = ExamFrame.this.checkPaper();
                    //展示
                    examArea.setText("考试结束！\n 你的最终成绩为" + score + "分");
                }
            }
        });
    	//按钮上的监听器
    	ActionListener optionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                //清除按钮颜色
				ExamFrame.this.clearButtonColor();
				// 获取按钮事件源
				JButton button = (JButton) e.getSource();
				//设置按钮背景灰色
				button.setBackground(Color.gray);
				//获取面板上选择的选项
				answers[nowNum] = button.getText();
			}
    	};
    	//按钮添加监听器
    	aButton.addActionListener(optionListener);
    	bButton.addActionListener(optionListener);
    	cButton.addActionListener(optionListener);
    	dButton.addActionListener(optionListener);
    }

    @Override
    protected void setFrameSelf() {
        this.setBounds(260,130,1000,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        //记时线程开始
        timecontrol.start();
    }

    //控制时间的线程
    private class timeControl extends Thread{
            private boolean flage = true;
            public void stopTimeThread(){
                this.flage = false;
            }
    	@Override
        public void run() {
    	    //总分钟除以60 为小时数
    		int hour = time / 60;
            //总分钟模60 为分钟数
    		int minute = time % 60;
    		//秒从0计
    		int second = 0;
    		while(true) {
    			//连接时间
    			StringBuilder timeString = new StringBuilder();
    			//如果小时在0 到10之间，字符串添加0
    			if(hour >= 0 && hour < 10) {
    				timeString.append("0");
    			}
    			timeString.append(hour);
    			timeString.append(":");
    			//如果分钟在0 到10之间，字符串添加0
    			if(minute >= 0 && minute < 10) {
    				timeString.append("0");
    			}
    			timeString.append(minute);
    			timeString.append(":");
    			//如果秒在0 到10之间，字符串添加0
    			if(second >= 0 && second < 10) {
    				timeString.append("0");
    			}
    			timeString.append(second);
    			
    			timeLabel.setText(timeString.toString());
    			try {
    			    //1000ms是1s
    				Thread.sleep(1000);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    			if(second > 0) {
    				second --;
    			}else{//分钟减一
    				if(minute > 0) {
    					minute --;
    					second = 59;
    				}else {
    					if(hour > 0){//小时减一
    						hour --;
    						minute = 59;
    						second = 59;
    					}else {
    					//时间用尽，按钮不可用，时间变成红色
    						timeLabel.setForeground(Color.red);
    						ExamFrame.this.setButtonEnable(false);
    						prevButton.setEnabled(false);
    						nextButton.setEnabled(false);
    						JOptionPane.showMessageDialog(ExamFrame.this,"考试结束，请提交");
    						break;
    					}
    				}
    			}
    		}
    	}
    }

    /**
     * 展示下一题
     */
    private void showQuestion() {
    	Questions question = paper.get(nowNum);
    	examArea.setText((nowNum + 1 )+ "." + question.getTitle());
    	
    }

    /**
     * 清除颜色
     */
    private void clearButtonColor() {
    	aButton.setBackground(null);
    	bButton.setBackground(null);
    	cButton.setBackground(null);
    	dButton.setBackground(null);
    }

    /**
     *  设置按钮是否可编辑
     * @param key true 或 false
     */
    private void setButtonEnable(boolean key){ 
    	aButton.setEnabled(key);
    	bButton.setEnabled(key);
    	cButton.setEnabled(key);
    	dButton.setEnabled(key);
    }

    /**
     * 计算成绩
     * @return  最终成绩
     */
    private float checkPaper() {

        //题目总长度
        int size = paper.size();
        //从满分递减
        float score = 100;
        //选择的答案和正确答案对比，不相等就减分
        for (int i = 0; i < paper.size(); i++) {
            Questions question = paper.get(i);
            String realAnswer = question.getAnswer();
            if (! realAnswer.equals(answers[i])) {
                score -= (100 / size);
            }
        }
        return score;
    }

    /**
     * 还原上一题选择情况
     */
    private void restoreButton() {
    	String answer = answers[nowNum];
    	if(answer == null) {
    		return;
    	}
    	//显示选择过的选项的按钮，按钮背景色改变
    	switch(answer) {
    	case "A":
    		aButton.setBackground(Color.lightGray);
    		break;
    	case "B":
    		bButton.setBackground(Color.lightGray);
    		break;
    	case "C":
    		cButton.setBackground(Color.lightGray);
    		break;
    	case "D":
    		dButton.setBackground(Color.lightGray);
    		break;
    	default:
    		this.clearButtonColor();
    		break;
    	}
    }
}
