package fr.looris.buildeurdecorateur;

import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.looris.buildeurdecorateur.utils.microsoftThread;
import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;
import fr.theshark34.swinger.textured.STexturedButton;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static fr.looris.buildeurdecorateur.Frame.*;

public class Panel extends JPanel implements SwingerEventListener {

    private Image backgroud = getImage("Launcher2.jpg");

    private STexturedButton play = new STexturedButton(getBufferedImage("start.png"));
    private STexturedButton microsoft = new STexturedButton(getBufferedImage("microsoft.png"));


    public Panel() throws IOException {
        this.setLayout(null);

        play.setBounds(128, 56);
        play.setLocation(576,576);
        play.addEventListener(this);
        this.add(play);

        microsoft.setBounds(150, 150);
        microsoft.setLocation(120,120);
        microsoft.addEventListener(this);
        this.add(microsoft);
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
                Launcher.update();
            } catch (Exception e) {
                Launcher.getReporter().catchError(e, "Inposible de lenser le jeu");
            }
        }
    }
}
