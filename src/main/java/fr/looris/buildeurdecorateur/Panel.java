package fr.looris.buildeurdecorateur;

import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.looris.buildeurdecorateur.utils.microsoftThread;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static fr.looris.buildeurdecorateur.Frame.*;

public class Panel extends JPanel implements SwingerEventListener {

    private Image backgroud = getImage("Launcher2.jpg");

    private STexturedButton play = new STexturedButton(getBufferedImage("start.png"));
    private STexturedButton microsoft = new STexturedButton(getBufferedImage("microsoft.png"));
    private STexturedButton Réglages = new STexturedButton(getBufferedImage("Réglages.png"));
    private STexturedButton réduire = new STexturedButton(getBufferedImage("réduire.png"));
    private STexturedButton quite = new STexturedButton(getBufferedImage("quite.png"));





    public Panel() throws IOException {
        this.setLayout(null);

        play.setBounds(128, 56);
        play.setLocation(1021,592);
        play.addEventListener(this);
        this.add(play);

        microsoft.setBounds(150, 150);
        microsoft.setLocation(100,95);
        microsoft.addEventListener(this);
        this.add(microsoft);

        Réglages.setBounds(128, 128);
        Réglages.setLocation(95,565);
        Réglages.addEventListener(this);
        this.add(Réglages);

        réduire.setBounds(128, 128);
        réduire.setLocation(1205,1);
        réduire.addEventListener(this);
        this.add(réduire);

        quite.setBounds(1028, 128);
        quite.setLocation(1245,1);
        quite.addEventListener(this);
        this.add(quite);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroud, 0,0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void onEvent(SwingerEvent swingerEvent) {
        if (swingerEvent.getSource() == microsoft) {
            Thread t = new Thread(new microsoftThread());
            t.start();
        } else if (swingerEvent.getSource() == play) {
            try {
                Launcher.update();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Impossible de mettre a jour le launcher.");
                e.printStackTrace();
            }

            try {
                Launcher.launch();
                Launcher.auth();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Inposible de lenser le jeu");
            }
        } else if (swingerEvent.getSource() == quite) {
            System.exit(0);
        } else if (swingerEvent.getSource() == réduire) {
            Frame.getFrames()[0].setState(Frame.ICONIFIED);
        }
    }
}
