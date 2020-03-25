package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.path.heading.LinearInterpolator;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.drive.mecanum.SampleMecanumDriveREVOptimized;
import com.acmerobotics.dashboard.config.Config;

import com.qualcomm.robotcore.hardware.Servo;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Config
@Autonomous(group = "drive")
public class AutoTest extends LinearOpMode {
    public static double DISTANCE = 48;
    public static double ANGLE = 90;
    private boolean tabs = true;
    Servo   tabRight, tabLeft;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDriveBase drive = new SampleMecanumDriveREVOptimized(hardwareMap);
        tabRight = hardwareMap.servo.get("tabRight");
        tabLeft = hardwareMap.servo.get("tabLeft");
        tabRight.setPosition(0.85);
        tabLeft.setPosition(0.3);
        waitForStart();

        if (isStopRequested()) return;
        telemetry.addData("Pose Estimate",drive.getPoseEstimate());

        drive.setPoseEstimate(new Pose2d(35, -70, Math.toRadians(270)));

        telemetry.addData("Pose Estimate",drive.getPoseEstimate());

       drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .reverse()
                        .lineTo(new Vector2d(35, -65)) //new LinearInterpolator(Math.toRadians(270), Math.toRadians(270)))
                        .build()
        );

         drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .reverse()
                        .splineTo(new Pose2d(45, -38, Math.toRadians(270)))
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .reverse()
                        .lineTo(new Vector2d(45, -35))
                        .build()
        );

        sleep(500);
        tabRight.setPosition(1);
        tabLeft.setPosition(0);
        sleep(500);



        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(new Pose2d(10, -60, Math.toRadians(180)))
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .reverse()
                        .lineTo(new Vector2d(40, -60))
                        .build()
        );

        sleep(500);
        tabRight.setPosition(0.85);
        tabLeft.setPosition(0.3);
        sleep(500);

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(new Pose2d(-10, -70, Math.toRadians(180)))
                        .build()
        );

                /*drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .strafeTo(new Vector2d(5, -75))
                        .build()
        );*/

       /* drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .reverse()
                        .lineTo(new Vector2d(5, -65))
                        .build()
        );

        drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .lineTo(new Vector2d(-48,-65), new LinearInterpolator(Math.toRadians(270),Math.toRadians(90)))
                        .build()
        );




        /* drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(new Pose2d(30, -50, Math.toRadians(90)))
                        .build()
        );
        sleep(500);

     /*   drive.followTrajectorySync(
                drive.trajectoryBuilder()
                        .splineTo(new Pose2d(45, -50, Math.toRadians(90)))
                        .build()
        ); */

       /* for(int i = 0; i < 16; ++i) {
            drive.followTrajectorySync(
                    drive.trajectoryBuilder()
                            .forward(DISTANCE)
                            .build()
            );

            sleep(2600);
            telemetry.update();

            drive.turnSync(Math.toRadians(ANGLE));

            sleep(2600);
            telemetry.update();
            tabs = !tabs;
            robot.setTabs(tabs);

        }
        telemetry.update(); */
    }
}
