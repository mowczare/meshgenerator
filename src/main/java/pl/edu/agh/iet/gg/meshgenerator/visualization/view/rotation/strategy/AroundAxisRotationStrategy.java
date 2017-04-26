package pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.strategy;

import javafx.scene.transform.Transform;
import pl.edu.agh.iet.gg.meshgenerator.visualization.config.Config;
import pl.edu.agh.iet.gg.meshgenerator.visualization.util.view.RotateDirection;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation.PlaneRotate;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation.XYRotate;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation.XZRotate;
import pl.edu.agh.iet.gg.meshgenerator.visualization.view.rotation.transformation.YZRotate;

import java.util.Arrays;
import java.util.List;

import static pl.edu.agh.iet.gg.meshgenerator.visualization.view.event.EventManager.ROTATION_SPEED;

/**
 * @author Bartłomiej Grochal
 */
@SuppressWarnings("unused")
public class AroundAxisRotationStrategy implements RotationStrategy {

    /**
     * Defines rotation of this group around X axis in YZ plane.
     */
    private final PlaneRotate rotationYZ;

    /**
     * Defines rotation of this group around Y axis in XZ plane.
     */
    private final PlaneRotate rotationXZ;

    /**
     * Defines rotation of this group around Z axis in XY plane.
     */
    private final PlaneRotate rotationXY;


    public AroundAxisRotationStrategy() {
        rotationXY = new XYRotate();
        rotationXZ = new XZRotate();
        rotationYZ = new YZRotate();
    }


    @Override
    public void setInitialValues() {
        setRotationYZ(Config.getDouble("rotation.axis.YZInitialValue"));
        setRotationXY(Config.getDouble("rotation.axis.XYInitialValue"));
    }

    @Override
    public void handleMouseRotation(double mousePositionDeltaX, double mousePositionDeltaY) {
        setRotationYZ(getRotationYZ() - mousePositionDeltaY * ROTATION_SPEED);
        setRotationXY(getRotationXY() + mousePositionDeltaX * ROTATION_SPEED);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void handleKeyboardRotation(RotateDirection direction) {
        switch (direction) {
            case UP:
            case DOWN:
                setKeyboardRotation(rotationYZ, direction);
                break;
            case RIGHT:
            case LEFT:
                setKeyboardRotation(rotationXY, direction);
                break;
            default:
                break;
        }
    }

    @Override
    public List<Transform> getRotations() {
        return Arrays.asList(rotationYZ, rotationXY);   // We use only two rotations.
    }


    public double getRotationXY() {
        return rotationXY.getAngle();
    }

    public void setRotationXY(double angle) {
        rotationXY.setAngle(angle);
    }

    public PlaneRotate getRotationXYTransformation() {
        return rotationXY;
    }

    public double getRotationXZ() {
        return rotationXZ.getAngle();
    }

    public void setRotationXZ(double angle) {
        rotationXZ.setAngle(angle);
    }

    public PlaneRotate getRotationXZTransformation() {
        return rotationXZ;
    }

    public double getRotationYZ() {
        return rotationYZ.getAngle();
    }

    public void setRotationYZ(double angle) {
        rotationYZ.setAngle(angle);
    }

    public PlaneRotate getRotationYZTransformation() {
        return rotationYZ;
    }


    private void setKeyboardRotation(PlaneRotate rotation, RotateDirection direction) {
        rotation.setAngle(rotation.getAngle() + direction.getDirectionSign() * ROTATION_SPEED);
    }

}
