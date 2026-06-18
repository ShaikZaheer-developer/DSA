class Solution {
    public double angleClock(int hour, int minutes) {
        // 1. Calculate the position of the minute hand in degrees
        double minuteAngle = minutes * 6.0;
        
        // 2. Calculate the position of the hour hand in degrees
        // Note: hour % 12 handles the 12 o'clock edge case (making it 0)
        double hourAngle = (hour % 12) * 30.0 + minutes * 0.5;
        
        // 3. Find the absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // 4. Return the smaller angle between the two possible paths
        return Math.min(diff, 360.0 - diff);
    }
}