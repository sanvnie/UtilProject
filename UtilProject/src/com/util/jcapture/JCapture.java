package com.util.jcapture;

/**
 * java实现截图功能
 * @author Administrator
 *
 */
public class JCapture {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 创建程序窗口
        Window win = new Window();
        // 创建窗口监听器
        CaptureObserver obs = new CaptureObserver();
        // 设置窗口的监听器
        win.setListener(obs);
        // 设置监听器要操作的窗口
        obs.observe(win);
    }
}