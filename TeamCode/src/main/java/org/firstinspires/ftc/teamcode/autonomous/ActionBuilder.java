package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.FollowPathAction;
import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathBuilder;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;

import java.util.ArrayList;

public class ActionBuilder {
    ArrayList<Action> actionList = new ArrayList<Action>();
    Follower follower;

    Point lastPoint;

    public ActionBuilder(Follower follower, Point startPoint) {
        this.follower = follower;
    }

    public ActionBuilder bezierLine(Point endPoint, double degrees) {
        PathBuilder builder = follower.pathBuilder();
        builder.addBezierLine(lastPoint, endPoint)
                        .setConstantHeadingInterpolation(Math.toRadians(degrees));
        actionList.add(new FollowPathAction(follower, builder.build()));
        lastPoint = endPoint;
        return this;
    }

    public ActionBuilder bezierCurve(double degrees, Point... points) {
        PathBuilder builder = follower.pathBuilder();
        builder.addBezierCurve(points)
                .setConstantHeadingInterpolation(Math.toRadians(degrees));
        actionList.add(new FollowPathAction(follower, builder.build()));
        lastPoint = points[points.length-1];
        return this;
    }

    public ActionBuilder stopAndAdd(Action action) {
        actionList.add(action);
        return this;
    }

    public static ActionBuilder actionBuilder(Follower follower, Point startPoint) {
        return new ActionBuilder(follower, startPoint);
    }
}
