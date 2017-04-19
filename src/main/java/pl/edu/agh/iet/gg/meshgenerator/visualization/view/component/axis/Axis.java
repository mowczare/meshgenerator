package pl.edu.agh.iet.gg.meshgenerator.visualization.view.component.axis;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
 * @author Bartłomiej Grochal
 */
public abstract class Axis extends Group {

    static final double AXIS_LINE_LENGTH = 1000.0;
    static final double AXIS_LINE_SIZE = 1.0;

    static final float AXIS_HEAD_HEIGHT = 30.0f;
    static final float AXIS_HEAD_SIDE = 20.0f;


    private final AxisLine axisLine;
    private final AxisHead axisHead;
    private final AxisLabel axisLabel;


    public Axis(Point3D rotationVersor, Color color, String label) {
        PhongMaterial material = new PhongMaterial(color);
        double translationFactor = AXIS_LINE_LENGTH / 2 + AXIS_HEAD_HEIGHT;

        axisLine = new AxisLine(material);
        axisHead = new AxisHead(material, new double[]{0, -translationFactor, 0});
        axisLabel = new AxisLabel(label, new double[]{-20, -translationFactor - AXIS_HEAD_HEIGHT, 0});

        initialize(rotationVersor);
    }


    private void initialize(Point3D rotationVersor) {
        getChildren().addAll(axisLine, axisHead, axisLabel);
        setTranslateY(AXIS_HEAD_HEIGHT);
        setRotationAxis(rotationVersor);
        setRotate(90.0);
    }

}
