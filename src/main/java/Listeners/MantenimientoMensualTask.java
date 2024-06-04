package Listeners;

import Servicios.CobrarMantenimiento;
import java.util.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MantenimientoMensualTask implements ServletContextListener {
    private Timer timer;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        Calendar firstExecution = Calendar.getInstance();
        firstExecution.set(Calendar.DAY_OF_MONTH, 1);
        firstExecution.set(Calendar.HOUR_OF_DAY, 0);
        firstExecution.set(Calendar.MINUTE, 0);
        firstExecution.set(Calendar.SECOND, 0);

        // Si el primer día del mes ya ha pasado, programa para el próximo mes
        if (firstExecution.getTime().before(new Date())) {
            firstExecution.add(Calendar.MONTH, 1);
        }

        long period = 1000L * 60 * 60 * 24 * 30; // Aproximadamente un mes
        timer.scheduleAtFixedRate(new MaintenanceTask(), firstExecution.getTime(), period);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
        }
    }

    private class MaintenanceTask extends TimerTask {
        @Override
        public void run() {
            CobrarMantenimiento.cobrarCuotas();
        }
    }
    
}
