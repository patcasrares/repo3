
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Font;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements MouseListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // MouseInputListener
    private ImageIcon titleImage;
    private int gs = 0;
    public static int[] a = new int[13];
    private static int n;
    public int mpz = 0, moves = 0, sf = 0;

    private static Random random = new Random();

    public Gameplay() {
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
    }

    public static void initializare() {
        n = 1 + random.nextInt(9);
        // n=10;
        int vf = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = random.nextInt(5);
            if (a[i] > 0)
                vf = 1;
            // a[i]=25;
        }
        if (vf == 0)
            a[1]++;
    }

    public void paint(final Graphics g) {
        if (gs == 0) {
            initializare();
        }

        if (sf == 1) {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("YOU WIN", 300, 300);

            g.setFont(new Font("arial", Font.BOLD, 20));
            g.drawString("Space to RESTART", 350, 340);
            return;
        }

        if (sf == 2) {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 50));
            g.drawString("YOU LOSE", 300, 300);

            g.setFont(new Font("arial", Font.BOLD, 20));
            g.drawString("Space to RESTART", 350, 340);
            return;
        }

        // g.dispose();
        // g.setColor(Color.white);
        // g.drawRect(0,0, 2000, 2000);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, 2000, 2000);
        titleImage = new ImageIcon("az.png");
        titleImage.paintIcon(this, g, 11, 11);

        int dst = 0;
        for (int i = 1; i <= n; i++) {
            ImageIcon z = new ImageIcon("th.jpg");

            Image az = z.getImage();
            Image nz = az.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
            z = new ImageIcon(nz);

            z.paintIcon(this, g, 11 + dst, 711);

            int dht = 60;
            for (int j = 1; j <= a[i]; j++) {
                ImageIcon z2 = new ImageIcon("Square.gif");

                Image az2 = z2.getImage();
                Image nz2 = az2.getScaledInstance(30, 10, java.awt.Image.SCALE_SMOOTH);
                z2 = new ImageIcon(nz2);

                z2.paintIcon(this, g, 11 + dst, 711 - dht);

                dht += 15;
            }

            dst += 50;

        }

        g.drawLine(0, 707, 2000, 707);

        ImageIcon z2 = new ImageIcon("PC.png");

        Image az2 = z2.getImage();
        Image nz2 = az2.getScaledInstance(500, 300, java.awt.Image.SCALE_SMOOTH);
        z2 = new ImageIcon(nz2);

        z2.paintIcon(this, g, 550, 300);

        g.dispose();
    }

    public void mutare(int poz, int val) {
        a[poz] = a[poz] - val;
        a[poz + 1] = a[poz + 1] + val;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

        int mx = e.getX();
        int my = e.getY();

        // a[1]=0;
        // repaint();

        int dst = 0;
        for (int i = 1; i <= n; i++) {
            /*
             * ImageIcon z = new ImageIcon("th.jpg");
             * 
             * Image az = z.getImage(); Image nz = az.getScaledInstance(30, 30,
             * java.awt.Image.SCALE_SMOOTH); z = new ImageIcon(nz);
             */

            // z.paintIcon(this, g, 11 + dst, 711);
            if (mx >= 11 + dst && mx <= 11 + dst + 30)
                if (my >= 711 && my <= 711 + 30) {
                    if (a[i] > 0) {
                        if (mpz == 0)
                            mpz = i;
                        gs = 1;
                        moves = 1;
                        if (i != mpz)
                            continue;

                        mutare(i, 1);
                        int vf = 1;
                        for (int j = 1; j <= n; j++)
                            if (a[j] > 0) {
                                vf = 0;
                                break;
                            }
                        if (vf == 1) {
                            sf = 1;
                            repaint();
                        }
                    }
                }

            dst += 50;
        }

        /*
         * ImageIcon z2 = new ImageIcon("PC.png");
         * 
         * Image az2 = z2.getImage(); Image nz2 = az2.getScaledInstance(500, 300,
         * java.awt.Image.SCALE_SMOOTH); z2 = new ImageIcon(nz2);
         * 
         * z2.paintIcon(this, g, 550, 300);
         * 
         * g.dispose();
         */
        if (mx >= 550 && mx <= 550 + 500)
            if (my >= 300 && my <= 300 + 300)
                if (gs == 0 || mpz != 0) {
                    mpz = 0;
                    gs = 1;
                    int sum = 0;
                    for (int i = 1; i <= n; i++)
                        if (i % 2 == n % 2) {
                            sum ^= a[i];
                        }
                    if (sum > 0) {
                        for (int i = 1; i <= n; i++)
                            if (i % 2 == n % 2) {
                                if (a[i] > (sum ^ a[i])) {
                                    mutare(i, a[i] - (sum ^ a[i]));
                                    break;
                                }
                            }

                        int vf = 1;
                        for (int j = 1; j <= n; j++)
                            if (a[j] > 0) {
                                vf = 0;
                                break;
                            }
                        if (vf == 1) {
                            sf = 2;
                            repaint();
                        }
                    } else {
                        for (int i = 1; i <= n; i++)
                            if (a[i] > 0) {
                                mutare(i, 1);
                                break;
                            }
                    }
                }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

        if(e.getKeyCode()==KeyEvent.VK_SPACE)
        {
            gs=0;
            sf=0;
            mpz=0;
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}