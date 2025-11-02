import java.awt.*;

public class Player {
    private int posX, posY, width, height;
    private int velocityY = 0;
    private Image image;

    // constructor 5 parameter sesuai Logic.java
    public Player(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    // getter dan setter yang dipanggil di Logic.java
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return image;
    }

    // method menggambar burung di layar
    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, width, height, null);
    }
}
