package jrdesktop.viewer;

import jrdesktop.Commons;
import jrdesktop.ConnectionInfos;
import jrdesktop.utilities.screenCaptureCompressor.ScreenCapture;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * ViewerOptions.java
 *
 * @author benbac
 */

public class ViewerOptions {

    public ConnectionInfos connectionInfos;
    public BufferedImage screenImage = null;
    public ScreenCapture capture = null;
    public int blocks = 20;
    public boolean screenCompression = true;
    public int refreshRate = 500;
    private boolean changed = false;
    private InetAddress inetAddress;
    private float imageQuality = -1.0f;
    private float screenScale = 1.0f;
    private int colorQuality = Commons.defaultColorQuality;
    private boolean clipboardTransfer = true;
    private Rectangle screenRect = Commons.emptyRect;
    private Hashtable properties = new Hashtable();

    public ViewerOptions(InetAddress inetAddress, Hashtable props) {
        this.inetAddress = inetAddress;
        properties = props;
        capture = new ScreenCapture(imageQuality, blocks, blocks);
        //setNewScreenRect();
        setNewScreenImage(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                colorQuality);
    }

    public ViewerOptions(InetAddress inetAddress) {
        screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        this.inetAddress = inetAddress;
        connectionInfos = new ConnectionInfos(true);
        capture = new ScreenCapture(imageQuality, blocks, blocks);
        setNewScreenImage(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                colorQuality);
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int rate) {
        refreshRate = rate;
    }

    public boolean isScreenCompressionEnabled() {
        return screenCompression;
    }

    public void setScreenCompression(boolean bool) {
        screenCompression = bool;
    }

    public ScreenCapture getCapture() {
        return capture;
    }

    public BufferedImage getScreenImage() {
        return screenImage;
    }

    public void setScreenImage(BufferedImage screenImage) {
        this.screenImage = screenImage;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean bool) {
        changed = bool;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public float getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(float cq) {
        imageQuality = cq;
    }

    public int getColorQuality() {
        return colorQuality;
    }

    public void setColorQuality(int clQuality) {
        colorQuality = clQuality;
    }

    public float getScreenScale() {
        return screenScale;
    }

    public void setScreenScale(float screenScale) {
        this.screenScale = screenScale;
    }

    public Rectangle getScreenRect() {
        return screenRect;
    }

    public void setScreenRect(Rectangle rect) {
        screenRect = rect;
    }

    public boolean getClipboardTransfer() {
        return clipboardTransfer;
    }

    public void setClipboardTransfer(boolean clipboardTransfer) {
        this.clipboardTransfer = clipboardTransfer;
    }

    public Hashtable getProperties() {
        return properties;
    }

    public void setProperties(Hashtable props) {
        properties = props;
    }

    public ArrayList getOptions() {
        ArrayList<Object> data = new ArrayList<Object>();

        data.add(screenRect);
        data.add(imageQuality);
        data.add(colorQuality);
        data.add(clipboardTransfer);

        return data;
    }

    public void setOptions(Object data) {
        ArrayList Options = (ArrayList) data;

        screenRect = (Rectangle) Options.get(Commons.RECT_OPTION);
        imageQuality = (Float) Options.get(Commons.IMAGE_OPTION);
        colorQuality = (Integer) Options.get(Commons.COLOR_OPTION);
        clipboardTransfer = (Boolean) Options.get(Commons.CLIPBOARD_OPTION);
    }

    public Object getOption(int option) {
        Object data = null;

        switch (option) {
            case Commons.RECT_OPTION:
                data = screenRect;
                break;
            case Commons.IMAGE_OPTION:
                data = imageQuality;
                break;
            case Commons.COLOR_OPTION:
                data = colorQuality;
                break;
            case Commons.CLIPBOARD_OPTION:
                data = clipboardTransfer;
                break;
        }

        return data;
    }

    public void setOption(Object data, int option) {
        switch (option) {
            case Commons.RECT_OPTION:
                screenRect = (Rectangle) data;
                break;
            case Commons.IMAGE_OPTION:
                imageQuality = (Float) data;
                break;
            case Commons.COLOR_OPTION:
                colorQuality = (Integer) data;
                break;
            case Commons.CLIPBOARD_OPTION:
                clipboardTransfer = (Boolean) data;
                break;
        }
    }

    public void setNewScreenImage(Rectangle rectangle, int colorQuality) {
        screenImage = (
                new BufferedImage(
                        rectangle.width,
                        rectangle.height,
                        colorQuality)
        );
    }

    public void setNewScreenRect() {
        screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }
}