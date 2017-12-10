package cn.vaf714.curriculum_schedule.bean;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 2017/11/18.
 */

public class ColorManager {
    //内部维护一个list，每次从中取一个颜色并标记该颜色已经使用过
    private static List<MyColor> colors = new ArrayList<>();

    static {
        reset();
    }

    /**
     * 重置颜色管理器
     */
    private static void reset(){
        colors.clear();
        colors.add(new MyColor(Color.parseColor("#80FF8C00"), false));
        colors.add(new MyColor(Color.parseColor("#80FF69B4"), false));
        colors.add(new MyColor(Color.parseColor("#80FF00FF"), false));
        colors.add(new MyColor(Color.parseColor("#80FF0000"), false));
        colors.add(new MyColor(Color.parseColor("#80FA8072"), false));
        colors.add(new MyColor(Color.parseColor("#80F08080"), false));
        colors.add(new MyColor(Color.parseColor("#809932CC"), false));
        colors.add(new MyColor(Color.parseColor("#804169E1"), false));
        colors.add(new MyColor(Color.parseColor("#80228B22"), false));
        colors.add(new MyColor(Color.parseColor("#801E90FF"), false));
        colors.add(new MyColor(Color.parseColor("#80FF6347"), false));
        colors.add(new MyColor(Color.parseColor("#80CC99FF"), false));
        colors.add(new MyColor(Color.parseColor("#80CC6633"), false));
        colors.add(new MyColor(Color.parseColor("#80FF8C00"), false));
        colors.add(new MyColor(Color.parseColor("#8033CCCC"), false));
        colors.add(new MyColor(Color.parseColor("#80FF8C00"), false));
        colors.add(new MyColor(Color.parseColor("#800099CC"), false));
        colors.add(new MyColor(Color.parseColor("#80CC0066"), false));
    }

    /**
     * 获得未使用过的颜色
     * @return
     */
    public static int getANoUsedColor(){
        for (int i = 0; i < colors.size(); i++) {
            MyColor color = colors.get(i);
            if (!color.isUse()){
                color.setUse(true);//已经被用
                return color.getValue();
            }
        }
        //如果全部都被使用，那么重置管理器
        reset();
        //递归再查找一次
        return getANoUsedColor();
    }

    @Override
    public String toString() {
        return "ColorManager{" +
                "colors=" + colors +
                '}';
    }

    static class MyColor {
        private int value;
        private boolean isUse;

        public MyColor() {
        }

        @Override
        public String toString() {
            return "Color{" +
                    "value=" + value +
                    ", isUse=" + isUse +
                    '}';
        }

        public MyColor(int value, boolean isUse) {

            this.value = value;
            this.isUse = isUse;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isUse() {
            return isUse;
        }

        public void setUse(boolean use) {
            isUse = use;
        }
    }

}
