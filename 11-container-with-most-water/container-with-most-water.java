class Solution {
    public int maxArea(int[] height) {
        int n=height.length;
        int MaxArea=0;
        int left=0, right=n-1;
        while(left<=right){
            int h=Math.min(height[left],height[right]);
            int w=right-left;
            int area=h*w;
            MaxArea=Math.max(MaxArea, area);
            if(height[left]<height[right]){
                left++;
            }
            else{
                right--;
            }
        }
    return MaxArea;    
    }
}