package igor_robo;
import robocode.*;
import java.awt.*;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
/**
 * Blue - a class by (Igor Cavalcanti Moura)
 */
public class Blue extends RateControlRobot {
    int dist = 50;
	
    public void run() {
     // Define cores do robô
		setBodyColor(Color.blue);
		setGunColor(Color.orange);
		setRadarColor(Color.red);
		setScanColor(Color.red);
		setBulletColor(Color.red);

        while (true) {
	// Lógica principal em loop
			ahead(100); // move o robô para frente 100 unidades no campo de batalha.
			turnGunRight(360); //  gira o canhão do robô em um círculo completo de 360 graus
			back(100); // faz com que o robô recue 100 unidades no campo de batalha
			turnGunRight(360); // gira o canhão do robô em um círculo completo de 360 graus
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // Lógica para lidar com a detecção de um inimigo
		double distancia = e.getDistance(); // armazena a distancia do inimigo na variável
		if (distancia < 135) { // se a distancia for menor que 135, atira com força máxima
			fire(3);
		} else { // senão forçca minima
			fire(1);
		}
    }

    public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading()))); //  calcula o ângulo pelo qual o seu robô deve girar após ser atingido
		ahead(dist); //  move o seu robô para a frente (avança) por uma distância especificada pela variável dist.
		dist *= -1; // inverte a direção
		scan(); // faz o robô executar uma ação de escaneamento com o radar
    }

    public void onHitWall(HitWallEvent e) {
    // Lógica para lidar com a colisão com uma parede 
		back(60); // volta 60 unidades 
		turnLeft(100); // vira 100 unidades para esquerda 
    }

    public void onWin(WinEvent e) {
     // Lógica para lidar com a vitória em uma batalha    
		int uhuu = 1;
        while (true) {
            turnRight(180 * uhuu);
            uhuu = uhuu * -1;
        }
    }

    public void onDeath(DeathEvent e) {
    // Lógica para lidar com a derrota em uma batalha
	System.out.println("Fui derrotado!");
    }
}
