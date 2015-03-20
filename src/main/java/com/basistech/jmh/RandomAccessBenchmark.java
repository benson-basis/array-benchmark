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

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Random;

/**
 * Compare simple array reads.
 */
@State(Scope.Thread)
public class RandomAccessBenchmark {
    private final static int DIM = 256;
    private double[][][] multiDoubles;
    private Matrix3 matrix3;
    private Random random;

    public RandomAccessBenchmark() {
        random = new Random();
        multiDoubles = new double[DIM][DIM][DIM];
        for (int x = 0; x < multiDoubles.length; x++) {
            double[][] d2 = multiDoubles[x];
            for (int y = 0; y < d2.length; y++) {
                double[] d3 = d2[y];
                for (int z = 0; z < d3.length; z++) {
                    d3[z] = x * y * z;
                }
            }
        }
        matrix3 = new Matrix3(DIM, DIM, DIM);
        for (int x = 0; x < matrix3.d1(); x++) {
            for (int y = 0; y < matrix3.d2(); y++) {
                for (int z = 0; z < matrix3.d3(); z++) {
                    matrix3.set(x, y, z, x * y * z);
                }
            }
        }
    }

    @Benchmark
    public double randomAccessMultiArray() {
        int d1 = random.nextInt(DIM);
        int d2 = random.nextInt(DIM);
        int d3 = random.nextInt(DIM);
        return multiDoubles[d1][d2][d3];

    }

    @Benchmark
    public double randomAccessFlattenedArray() {
        int d1 = random.nextInt(DIM);
        int d2 = random.nextInt(DIM);
        int d3 = random.nextInt(DIM);
        return matrix3.get(d1, d2, d3);
    }
}
