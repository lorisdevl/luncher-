package fr.looris.buildeurdecorateur.utils;

import com.sun.jdi.connect.LaunchingConnector;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.looris.buildeurdecorateur.Launcher;

public class microsoftThread implements Runnable{
    @Override
    public void run() {
        try {
            Launcher.auth();
        } catch (MicrosoftAuthenticationException e) {
            Launcher.getReporter().catchError(e, "Imposible de se conecter.");
        }
    }
}
