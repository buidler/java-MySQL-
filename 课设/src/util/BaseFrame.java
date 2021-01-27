package util;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    
    public BaseFrame(){}
    public BaseFrame(String title){
        super(title);
    }

    protected void init(){
        this.setFontAndSoOn();
        this.addElement();
        this.addListener();
        this.setFrameSelf();
    }

    //设置每一个组件的位置 字体 背景等
    protected abstract void setFontAndSoOn();
    //增加元素
    protected abstract void addElement();
    //增加监听器
    protected abstract void addListener();
    //设施窗口
    protected abstract void setFrameSelf();
}
