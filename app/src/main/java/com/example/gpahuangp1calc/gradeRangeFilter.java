// Credit: base code from StackOverflow
// Edited to match app's needs
// https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android

package com.example.gpahuangp1calc;

import android.text.InputFilter;
import android.text.Spanned;

class gradeRangeFilter implements InputFilter {

    private float min, max;

    public gradeRangeFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public gradeRangeFilter(String min, String max) {
        this.min = Float.parseFloat(min);
        this.max = Float.parseFloat(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            float input = Float.parseFloat(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(float a, float b, float c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}