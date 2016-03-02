package com.util.jcapture;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 鼠标事件监听器
 * 
 * @author hyjiacan
 * 
 */
public class CaptureObserver implements MouseListener, MouseMotionListener {
    private Window win;// 程序窗口对象
    private Point oldpos;// 鼠标按下时的坐标
    private Point newpos;// 鼠标移动后的坐标
    private Rectangle rect;// 选中的区域
    private long lastClick;// 上次按下鼠标左键的时间

    public CaptureObserver() {
        rect = new Rectangle();
        lastClick = 0;
    }

    /**
     * 通过一个要监听的窗口创建一个监听器
     * 
     * @param win
     *            要监听的窗口
     */
    public CaptureObserver(Window win) {
        this();
        observe(win);
    }

    /**
     * 设置要监听的窗口
     * 
     * @param win
     *            要监听的窗口
     */
    public void observe(Window win) {
        this.win = win;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {// 鼠标左键
            if (e.getWhen() - lastClick < 500) { // 此次点击和上次点击时间间隔小于500毫秒，则认为发生了双击事件
                // 因为截图时坐标提示属于多余的，所以需要关闭
                win.hideTips();
                // 销毁程序窗口
                win.getWindow().dispose();
                try {
                    // 根据选择的区域截图
                    BufferedImage img = new Robot().createScreenCapture(win
                            .getShotArea().getBounds());

                    // 保存文件
                    JFileChooser dlg = new JFileChooser();
                    // 图片保存为jpg格式
                    dlg.setFileFilter(new FileFilter() {

                        @Override
                        public boolean accept(File f) {
                            if (f.getName().endsWith("jpg"))
                                return true;
                            else
                                return false;
                        }

                        @Override
                        public String getDescription() {
                            return ".jpg";
                        }

                    });
                    dlg.showSaveDialog(win.getWindow());
                    dlg.setVisible(true);
                    // 写图片数据到硬盘
                    ImageIO.write(img, "jpg", dlg.getSelectedFile());
                    System.exit(0);
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            lastClick = e.getWhen();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 在鼠标按下的时候记录位置
        oldpos = e.getLocationOnScreen();
        rect.setLocation(oldpos.getLocation());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // 设置坐标提示窗口的位置
        win.setTipsPosition(e.getLocationOnScreen());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 设置坐标提示窗口的位置
        win.setTipsPosition(e.getLocationOnScreen());
        // 获取新位置
        newpos = e.getLocationOnScreen();

        if (newpos.x > oldpos.x && newpos.y > oldpos.y)// 右下方向
            rect.setSize(newpos.x - oldpos.x, newpos.y - oldpos.y);
        else if (newpos.x < oldpos.x && newpos.y > oldpos.y) {// 左下方向
            rect.setSize(oldpos.x - newpos.x, newpos.y - oldpos.y);
            // 重新定位
            rect.x = newpos.x;
        } else if (newpos.x < oldpos.x && newpos.y < oldpos.y) {// 左上方向
            rect.setSize(oldpos.x - newpos.x, oldpos.y - newpos.y);
            // 重新定位
            rect.setLocation(newpos);
        } else if (newpos.x > oldpos.x && newpos.y < oldpos.y) {// 右上方向
            rect.setSize(newpos.x - oldpos.x, oldpos.y - newpos.y);
            // 重新定位
            rect.y = newpos.y;
        }

        win.drawRect(rect);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // 设置坐标提示窗口的位置
        win.setTipsPosition(e.getLocationOnScreen());
    }
}