package com.util.jcapture;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {
    private JFrame win; // 主窗口
    private Rectangle screen;// 屏幕位置大小
    private JPanel preview; // 截图选中区域
    private JDialog tips; // 坐标提示框
    private JLabel tposx, tposy; // 坐标提示框x坐标，y坐标

    public Window() {
        init();
    }

    private void init() {
        win = new JFrame();
        // 坐标提示框的背景色
        Color tbg = new Color(1f, 0.76f, 0.52f, 0.8f);

        tposx = new JLabel();
        tposy = new JLabel();
        tips = new JDialog();
        tips.setLayout(new FlowLayout());
        // 不显示标题栏
        tips.setUndecorated(true);
        // 设置背景色
        tips.getContentPane().setBackground(tbg);

        tips.setSize(80, 48);
        tips.setVisible(true);
        tips.add(tposx);
        tips.add(tposy);

        screen = new Rectangle(win.getToolkit().getScreenSize());
        preview = new JPanel();
        // 给截图区域加上边框，以方便查看
        preview.setBorder(BorderFactory.createLineBorder(Color.RED));

        win.setLayout(null);
        win.add(preview);
        // 不显示标题栏
        win.setUndecorated(true);
        // 设置窗口透明度
        com.sun.awt.AWTUtilities.setWindowOpacity(win,0.2f);
        win.setBounds(screen);
        // 设置鼠标形状为 十字 形状
        win.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        // 响应ESC键退出程序
        win.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        // 关闭窗口时将坐标提示窗口也关闭
        win.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tips.dispose();
            }
        });
        win.setVisible(true);
        win.validate();
    }

    /**
     * 获取窗口对象
     * 
     * @return 程序窗口对象
     */
    public JFrame getWindow() {
        return win;
    }

    /**
     * 设置监听器
     * 
     * @param obs
     *            监听器实现，需要实现MouseListener和MouseMotionListener两个接口
     */
    public void setListener(CaptureObserver obs) {
        win.addMouseListener(obs);
        win.addMouseMotionListener(obs);
    }

    /**
     * 设置坐标提示的坐标值和显示位置
     * 
     * @param p
     *            新的坐标位置
     */
    public void setTipsPosition(Point p) {
        tposx.setText("X坐标:" + p.x);
        tposy.setText("Y坐标:" + p.y);
        tips.setLocation(p);
        tips.repaint();
    }

    /**
     * 更新窗口
     */
    public void validate() {
        win.validate();
    }

    /**
     * 选择截图区域
     * 
     * @param rect
     *            选中的区域
     */
    public void drawRect(Rectangle rect) {
        preview.setBounds(rect);
        win.validate();
    }

    /**
     * 设置程序窗口是否显示
     * 
     * @param b
     *            true显示，false不显示
     */
    public void setVisible(boolean b) {
        win.setVisible(b);
    }

    /**
     * 关闭坐标提示窗口
     */
    public void hideTips() {
        tips.dispose();
    }

    /**
     * 获取选中的截图区域
     * 
     * @return 选中区域
     */
    public JPanel getShotArea() {
        return preview;
    }
}
