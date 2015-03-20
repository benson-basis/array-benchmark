/******************************************************************************
 ** This data and information is proprietary to, and a valuable trade secret
 ** of, Basis Technology Corp.  It is given in confidence by Basis Technology
 ** and may only be used as permitted under the license agreement under which
 ** it has been distributed, and in no other way.
 **
 ** Copyright (c) 2015 Basis Technology Corporation All rights reserved.
 **
 ** The technical data and information provided herein are provided with
 ** `limited rights', and the computer software provided herein is provided
 ** with `restricted rights' as those terms are defined in DAR and ASPR
 ** 7-104.9(a).
 ******************************************************************************/

package com.basistech.jmh;

/**
 * Flattened matrix
 */
public class Matrix3 {
    private final int d1Count;
    private final int d2Count;
    private final int d3Count;
    private final int d1d2;
    private final double[] values;

    public Matrix3(int d1Count, int d2Count, int d3Count) {
        this.d1Count = d1Count;
        this.d2Count = d2Count;
        this.d3Count = d3Count;
        d1d2 = d1Count * d2Count;
        values = new double[d1Count * d2Count * d3Count];
    }

    private int index(int d1, int d2, int d3) {
        return (d1 * d1d2) + (d2 * d2Count) * d3;
    }

    public final Matrix3 set(int d1, int d2, int d3, double v) {
        values[index(d1, d2, d3)] = v;
        return this;
    }

    public final double get(int d1, int d2, int d3) {
        return values[index(d1, d2, d3)];
    }

    public final int d1() {
        return d1Count;
    }

    public final int d2() {
        return d2Count;
    }

    public final int d3() {
        return d3Count;
    }
}
