package com.flycode.jasonfit.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created - Schumakher on  3/14/17.
 */

@Table(name = "StatsData")
public class StatsData extends Model {

    @Column(name = "day")
    public int day;

    @Column(name = "month")
    public int month;

    @Column(name = "year")
    public int year;

    @Column(name = "weight")
    public int weight;

    @Column(name = "burntCalories")
    public Double burntCalories;


    private  static int[] getDate(StatsData statsData) {
        return  new int[] {
                statsData.day,
                statsData.month,
                statsData.year
        };
    }
    private static double[] getBurntCalories(StatsData statsData) {
        return new double[] {
                statsData.burntCalories
        };
    }

    private static int[] getWeight(StatsData statsData) {
        return new int[] {
                statsData.weight
        };
    }
}
