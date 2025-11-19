package br.com.erick.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-50, 50, Math.toRadians(135)))
                /* LAUNCH 1 RED
                //shooter e servo
                .lineToY(-54)
                .turn(Math.toRadians(-27.5))
                .waitSeconds(2)
                //human player
                .turn(Math.toRadians(117.5))
                .lineToX(62)
                .waitSeconds(2)
                //shooter e servo
                .lineToX(10)
                .turn(Math.toRadians(-113.5))
                .waitSeconds(2)
                //human player
                .turn(Math.toRadians(113.5))
                .lineToX(62)
                .waitSeconds(2)
                //shooter e servo
                .lineToX(10)
                .turn(Math.toRadians(-113.5))
                 */

                /* LAUNCH 1 BLUE
                //shooter e servo
                .lineToY(-54)
                .turn(Math.toRadians(27.5))
                .waitSeconds(2)
                //human player
                .turn(Math.toRadians(-117.5))
                .lineToX(-62)
                .waitSeconds(2)
                //shooter e servo
                .lineToX(-10)
                .turn(Math.toRadians(113.5))
                .waitSeconds(2)
                //human player
                .turn(Math.toRadians(-113.5))
                .lineToX(-62)
                .waitSeconds(2)
                //shooter e servo
                .lineToX(-10)
                .turn(Math.toRadians(113.5))
                 */

                /* LAUNCH 2 RED
                //shooter e servo
                .lineToY(12)
                .waitSeconds(2)
                //intake
                .turn(Math.toRadians(135))
                .lineToX(48)
                .waitSeconds(1)
                //shooter e servo
                .lineToX(12)
                .turn(Math.toRadians(-135))
                .waitSeconds(2)
                //intake
                .turn(Math.toRadians(45))
                .lineToY(-12)
                .turn(Math.toRadians(90))
                .lineToX(48)
                .waitSeconds(1)
                //shooter e servo
                .lineToX(12)
                .turn(Math.toRadians(-90))
                .lineToY(12)
                .turn(Math.toRadians(-45))
                 */

                //shooter e servo
                .lineToY(12)
                .waitSeconds(2)
                //intake
                .turn(Math.toRadians(-135))
                .lineToX(-48)
                .waitSeconds(1)
                //shooter e servo
                .lineToX(-12)
                .turn(Math.toRadians(135))
                .waitSeconds(2)
                //intake
                .turn(Math.toRadians(-45))
                .lineToY(-12)
                .turn(Math.toRadians(-90))
                .lineToX(-48)
                .waitSeconds(1)
                //shooter e servo
                .lineToX(-12)
                .turn(Math.toRadians(90))
                .lineToY(12)
                .turn(Math.toRadians(45))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}