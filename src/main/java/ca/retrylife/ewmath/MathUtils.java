package ca.retrylife.ewmath;

import java.util.HashMap;

public class MathUtils {
    
    // A very small number to be used for comparing floats
    public static final double kVerySmallNumber = 1e-12;

    /**
     * This allows the angle to respect 'wrapping', where 360 and 0 are the same
     * value
     * 
     * @param angle Gyroscope angle
     * @return Wrapped value
     */
    public static double wrapGyro(double angle) {
        // Wrap the angle by 360degs
        angle %= 360.0;

        // Handle offset
        if (Math.abs(angle) > 180.0) {
            angle = (angle > 0) ? angle - 360 : angle + 360;
        }

        return angle;
    }

    /**
     * Gets the error between two angles and allows crossing the 360/0 degree
     * boundary
     * 
     * @param currentAngle Current angle
     * @param desiredAngle Desired/goal angle
     * @return Difference
     */
    public static double getWrappedError(double currentAngle, double desiredAngle) {
        double phi = Math.abs(currentAngle - desiredAngle) % 360; // This is either the distance or 360 - distance
        double distance = phi > 180 ? 360 - phi : phi;

        // Determine the sign (is the difference positive of negative)
        int sign = (currentAngle - desiredAngle >= 0 && currentAngle - desiredAngle <= 180)
                || (currentAngle - desiredAngle <= -180 && currentAngle - desiredAngle >= -360) ? 1 : -1;

        // Return the final difference
        return distance * sign;

    }

    /**
     * Returns value clamped between low and high boundaries.
     *
     * @param value Value to clamp.
     * @param low   The lower boundary to which to clamp value.
     * @param high  The higher boundary to which to clamp value.
     */
    public static double clamp(double value, double low, double high) {
        return Math.max(low, Math.min(value, high));
    }

    /**
     * Checks if two values are roughly equal to each other.
     *
     * @param a
     * @param b
     * @param epsilon
     * @return true if a and b are within epsilon
     */
    public static boolean epsilonEquals(double a, double b, double epsilon) {
        return (a - epsilon <= b) && (a + epsilon >= b);
    }

    public static double map(double value, double input_low, double input_high, double output_low, double output_high) {
        return (value - input_low) * (output_high - output_low) / (input_high - input_low) + output_low;
    }

    public static int mode(int[] array) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int max = 1;
        int temp = 0;

        for (int i = 0; i < array.length; i++) {

            if (hm.get(array[i]) != null) {

                int count = hm.get(array[i]);
                count++;
                hm.put(array[i], count);

                if (count > max) {
                    max = count;
                    temp = array[i];
                }
            }

            else
                hm.put(array[i], 1);
        }
        return temp;
    }

}
